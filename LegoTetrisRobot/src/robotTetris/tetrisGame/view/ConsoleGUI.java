package robotTetris.tetrisGame.view;


import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import robotTetris.tetrisGame.modele.Game;

import java.io.PrintStream;

public class ConsoleGUI extends GUI
{
	private JFrame					frame;
	private TextAreaOutputStream	taos;

	public ConsoleGUI()
	{
		frame = new JFrame();
		frame.add(new JLabel(" Tetris"), BorderLayout.NORTH);

		JTextArea ta = new JTextArea();
		ta.setFont(new Font("Monospaced", Font.PLAIN, 15));
		taos = new TextAreaOutputStream(ta);
		PrintStream ps = new PrintStream(taos);
		System.setOut(ps);
		System.setErr(ps);

		frame.add(ta);

		frame.pack();
		frame.setSize(300, 500);
		frame.setVisible(true);

		new Game();
	}

	public static void main(String[] args)
	{
		new ConsoleGUI();
	}
}
