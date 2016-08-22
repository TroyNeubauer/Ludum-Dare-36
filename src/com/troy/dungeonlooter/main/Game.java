package com.troy.dungeonlooter.main;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import com.troy.dungeonlooter.cilent.*;
import com.troy.dungeonlooter.entity.*;
import com.troy.dungeonlooter.gamestate.*;
import com.troy.dungeonlooter.graphics.*;
import com.troy.dungeonlooter.util.*;
import com.troy.dungeonlooter.world.*;
import com.troy.troyberry.logging.*;
import com.troy.troyberry.math.*;

public class Game extends Canvas {

	private static final long serialVersionUID = 2783110120763573889L;
	public static CrashReport crashReport;
	private boolean firstRender = true;

	public static volatile boolean running;

	private static Game game;

	public JFrame frame;

	public int width = 300, height = width / 16 * 9;
	public int windowWidth = width * 3, windowHeight = width * 3;
	public Screen screen;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public GameStateManager gameStateManager;
	public GameState dungeonLevelState;
	public GameState titleScreen;
	private WindowHandler windowHandler = new WindowHandler();

	public static Camera camera;
	public World world;

	public Game(String[] args) throws Exception {

		screen = new Screen(width, height, camera);

		dungeonLevelState = new DungeonLevelState();
		titleScreen = new TitleScreenState();
		gameStateManager = new GameStateManager(titleScreen, this);

	}

	public void loop() throws Exception {
		
		running = true;

		double timePerTick = 1000000000 / 60;
		int updateCount = 0;

		int frameUpdatesPerSecond = 5;
		boolean firstFrameRun = true;

		long updateTimer = 0, frameTimer = 0;
		int currentFramesAverage = 60, currentUpdatesAverage = 60;
		int[] pastFewFPS = new int[frameUpdatesPerSecond];

		double delta = 0;
		int updates = 0, frames = 0;
		long now, lastTime = System.nanoTime();
		while (running) {
			now = System.nanoTime();
			long nowMinusLastTime = (now - lastTime);

			delta += nowMinusLastTime / timePerTick;
			updateTimer += nowMinusLastTime;
			frameTimer += nowMinusLastTime;
			lastTime = now;

			if (delta >= 1) {
				update();
				gameStateManager.update(this);
				updates++;
				delta--;
			}
			if (updateTimer >= 1000000000) {
				
				currentUpdatesAverage = updates;
				updates = 0;
				updateTimer = 0;
			}

			if (frameTimer >= (1000000000L / (long) frameUpdatesPerSecond)) {
				if (firstFrameRun) {
					firstFrameRun = false;
					for (int i : pastFewFPS) {
						pastFewFPS[i] = frames;
					}
				}
				
				for (int i = pastFewFPS.length - 1; i > 0; i--) {
					pastFewFPS[i] = pastFewFPS[i - 1];
				}
				pastFewFPS[0] = frames;
				
				currentFramesAverage = Math.round(Maths.average(pastFewFPS)  * ((float)frameUpdatesPerSecond));
				frames = 0;
				frameTimer = 0;
			}
			
			this.frame.setTitle(Version.getWindowTitle() + "  |  " + currentUpdatesAverage + " ups, " + currentFramesAverage + " fps");
			render();
			frames++;

		}
		frame.setVisible(false);
		frame.dispose();
		if (crashReport == null) {
			Log.info("The Dungeon Looter completed sucscfully!");
		}
		System.exit(0);
	}

	/** This method is called 60 times per second to update the **/
	private void update() {
		KeyHandler.update();
	}

	/** This method draws the entire game onto the window **/
	public void render() throws Exception {
		BufferStrategy bs = getBufferStrategy();
		Graphics g = bs.getDrawGraphics();

		screen.clear(g);
		gameStateManager.render(screen, this);

		/** Copy the pixels from the screen class to the pixels in the image **/
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		/** Draw the image to the screen. This image is the one that was modified by indexing the pixels array and contains all of the current frame's data **/
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		/** Show the buffer and then dispose of the graphics context to avoid memory leaks **/
		bs.show();
		g.dispose();
	}

	public static void main(String[] args) throws Exception {
		Thread.currentThread().setName("Game-Thread");
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

		try {
			game = new Game(args);
		} catch (Exception e) {
			crashReport = new CrashReport("Initalizing Game", e).print();
		}

		try {
			game.setupDisplay();
		} catch (Exception e) {
			crashReport = new CrashReport("Setting Up Display", e).print();
		}

		try {
			game.loop();
		} catch (Exception e) {
			crashReport = new CrashReport("Running Game Loop", e).print();
		}
	}

	public void loadAssets() {
		try {
			Assets.init();
		} catch (Exception e) {
			crashReport = new CrashReport("Loading Textures", e).print();
		}
	}

	public void setupDisplay() throws Exception {
		frame = new JFrame();
		frame.setVisible(false);
		frame.setResizable(true);

		DisplayMode ge = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

		windowWidth = Maths.celi((float) ge.getWidth() * 0.6f);
		windowHeight = Maths.celi((float) ge.getHeight() * 0.6f);

		frame.setPreferredSize(new Dimension(windowWidth, windowHeight));
		frame.setSize(windowWidth, windowHeight);
		frame.setMinimumSize(new Dimension(windowWidth / 3, windowHeight / 3));
		frame.setTitle(Version.getWindowTitle());
		frame.setLocationRelativeTo(null);

		frame.add(this);
		frame.addKeyListener(new Keyboard());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowFocusListener(windowHandler);
		frame.addWindowListener(windowHandler);

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		frame.pack();
		frame.setVisible(true);

		boolean hasSetUpGraphics = false;
		BufferStrategy bs = null;

		while (!hasSetUpGraphics) {
			bs = getBufferStrategy();
			if (bs == null) {
				createBufferStrategy(3);
			} else {
				hasSetUpGraphics = true;
			}
		}

	}
}