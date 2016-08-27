package com.troy.ludumdare;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import com.troy.ludumdare.gamestate.*;
import com.troy.ludumdare.graphics.*;
import com.troy.ludumdare.input.*;
import com.troy.ludumdare.util.*;
import com.troy.troyberry.logging.*;
import com.troy.troyberry.math.*;

public class Game extends Canvas {

	private static final long serialVersionUID = 2783110120763573889L;
	public static CrashReport crashReport;

	public static volatile boolean running;

	public static Game game;
	public static Screen screen;

	public JFrame frame;
	public static Input input;

	public int width = 300, height = (int) (width / 16f * 9f);
	public int windowWidth = width * 4, windowHeight = height * 4;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public GameStateManager gameStateManager;
	public GameState levelState;
	public GameState titleScreenState;
	private WindowHandler windowHandler = new WindowHandler();

	public Game(String[] args) throws Exception {
		screen = new Screen(width, height);
		input = new Input();
		levelState = new LevelState();
		titleScreenState = new TitleScreenState();
		gameStateManager = new GameStateManager(titleScreenState, this);

	}

	/** This method is called 60 times per second to update the **/
	private void update(int updateCount) {

	}

	/** This method draws the entire game onto the window **/
	public void render() throws Exception {
		BufferStrategy bs = getBufferStrategy();
		Graphics g = bs.getDrawGraphics();

		screen.clear(g);
		gameStateManager.render(screen, this);
		if (Input.mouseX + Input.mouseY * screen.width < screen.pixels.length && Input.mouseX + Input.mouseY * screen.width > 0) {
			screen.pixels[Input.mouseX + Input.mouseY * screen.width] = 0x0;
		}

		/** Copy the pixels from the screen class to the pixels in the image **/
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		/** Draw the image to the screen. This image is the one that was modified by indexing the pixels array and contains all of the current frame's data **/
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		/** Show the buffer and then dispose of the graphics context to avoid memory leaks **/
		TextMaster.render(g, screen);
		TextMaster.clear();
		bs.show();
		g.dispose();
	}

	public static void main(String[] args) throws Exception {
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

	public void loop() throws Exception {

		running = true;

		double timePerTick = 1000000000d / 60d;
		int updateCount = 1;

		int frameUpdatesPerSecond = 15;
		boolean firstFrameRun = true;

		long updateTimer = 0, frameTimer = 0;
		int currentFramesAverage = 60, currentUpdatesAverage = 60;
		int[] pastFewFPS = new int[frameUpdatesPerSecond * 2];

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
				update(updateCount);
				gameStateManager.update(this, updateCount);
				updateCount++;
				updates++;
				delta--;
			}
			if (updateTimer >= 1000000000) {

				currentUpdatesAverage = updates;
				updates = 0;
				updateTimer = 0;
			}

			if (frameTimer >= (1000000000L / (long) frameUpdatesPerSecond)) {

				for (int i = pastFewFPS.length - 1; i > 0; i--) {
					pastFewFPS[i] = pastFewFPS[i - 1];
				}
				pastFewFPS[0] = frames;

				currentFramesAverage = Math.round(Maths.average(pastFewFPS) * ((float) frameUpdatesPerSecond));
				frames = 0;
				frameTimer = 0;
			}

			this.frame.setTitle(Version.getWindowTitle() + "  |  " + currentUpdatesAverage + " ups, " + currentFramesAverage + " fps");
			render();
			frames++;

		}
		frame.setVisible(false);
		frame.dispose();
		LevelState.save();
		if (crashReport == null) {
			Log.info(Version.getWindowTitle() + " completed successfully!");
		}
		System.exit(0);
	}

	public void setupDisplay() throws Exception {

		frame = new JFrame();
		frame.setVisible(false);
		frame.setResizable(true);
		frame.setPreferredSize(new Dimension(windowWidth, windowHeight));
		frame.setSize(windowWidth, windowHeight);
		frame.setMinimumSize(new Dimension(windowWidth / 3, windowHeight / 3));
		frame.setTitle(Version.getWindowTitle());
		frame.setLocationRelativeTo(null);

		frame.add(this);
		frame.addKeyListener(input);
		this.addMouseListener(input);
		this.addMouseMotionListener(input);
		frame.addWindowFocusListener(windowHandler);
		frame.addWindowListener(windowHandler);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/textures/icon.png")));

		frame.pack();
		frame.setVisible(true);
		frame.requestFocus();
		frame.requestFocusInWindow();

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