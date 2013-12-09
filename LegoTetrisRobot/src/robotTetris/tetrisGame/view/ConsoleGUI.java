package robotTetris.tetrisGame.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import robotTetris.tetrisGame.modele.Game;

import java.io.PrintStream;

public class ConsoleGUI extends GUI implements KeyListener
{
	private JFrame					frame;
	private TextAreaOutputStream	taos;
	private Game					game;
	private static ConsoleGUI instance;

	public static ConsoleGUI getInstance()
	{
		if (instance == null)
			instance = new ConsoleGUI();
		return instance;
	}
	
	private ConsoleGUI()
	{
		frame = new JFrame();
		frame.add(new JLabel(" Tetris"), BorderLayout.NORTH);
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTextArea ta = new JTextArea();
		ta.setFont(new Font("Monospaced", Font.PLAIN, 15));
		ta.setEditable(false);
		ta.addKeyListener(this);

		taos = new TextAreaOutputStream(ta);
		PrintStream ps = new PrintStream(taos);
		System.setOut(ps);

		frame.add(ta);

		frame.pack();
		frame.setSize(300, 500);
		frame.setVisible(true);

		game = new Game();
	}

	public static void main(String[] args)
	{
		ConsoleGUI gui = getInstance();

		while (gui.game.isPlaying())
		{
			gui.game.tick();
			System.out.println(gui.game);
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_UP:
			game.turnClockWise();
			break;
		case KeyEvent.VK_DOWN:
			game.speedDown();
			break;
		case KeyEvent.VK_RIGHT:
			game.moveRight();
			break;
		case KeyEvent.VK_LEFT:
			game.moveLeft();
			break;
		}
		System.out.println(getInstance().game);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{

	}
}
