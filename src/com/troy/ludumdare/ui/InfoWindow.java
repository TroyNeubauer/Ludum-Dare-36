package com.troy.ludumdare.ui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import com.troy.ludumdare.util.*;

public class InfoWindow {

	private InfoWindow() {
	}

	private static JFrame frame;
	private static boolean open = false;
	private static final Font FONT = new Font("Serif", Font.PLAIN, 18);
	private static final String INFO = "August 28 2016  " + Version.getName() + "\n"
		+ " is a game made for Ludium Dare 36. The goal of the game is to";
	private static final String CREDITS = "Troy Neubauer";
	
	private static final String GITHUB_PROFILE_LINK = "https://github.com/TroyNeubauer?tab=repositories";

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public static void initialize() {
		frame = new JFrame();
		frame.setSize(900, 650);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		frame.addWindowListener(new java.awt.event.WindowAdapter() {

			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				frame.dispose();
			}
		});
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		frame.setTitle("Info");
		System.out.println("creating indo window");

		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JLabel title = new JLabel("  " + Version.getName() + "  ");
		title.setFont(FONT);
		frame.getContentPane().add(title);

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setFont(FONT);
		textArea.setEditable(false);
		textArea.setText(INFO);
		textArea.setPreferredSize(new Dimension(400, 120));
		frame.getContentPane().add(textArea);

		JLabel creditsLabel = new JLabel("Credits:");
		creditsLabel.setFont(FONT);
		frame.getContentPane().add(creditsLabel);

		JTextArea creditsText = new JTextArea();
		creditsText.setFont(FONT);
		creditsText.setEditable(false);
		creditsText.setText(CREDITS);
		frame.getContentPane().add(creditsText);

		JPanel panel = new JPanel();
		panel.setFont(FONT);
		frame.getContentPane().add(panel);

		JButton btnNewButton = new JButton("        Exit         ");
		btnNewButton.setFont(FONT);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frame.dispose();

			}
		});
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("To my GitHub profile!");
		btnNewButton_1.setFont(FONT);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(new URI(GITHUB_PROFILE_LINK));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		panel.add(btnNewButton_1);
		

		frame.setLocationRelativeTo(null);
		
		frame.pack();
		frame.setVisible(true);
	}

}
