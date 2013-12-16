package robotTetris.tetrisGame.modele;

import robotTetris.tetrisGame.modele.events.GameOverEvent;
import robotTetris.tetrisGame.modele.events.GameOverListener;
import robotTetris.tetrisGame.modele.events.LineBreakEvent;
import robotTetris.tetrisGame.modele.events.LineBreakListener;

public class Game implements GameOverListener, LineBreakListener
{
	private Board	board;
	private boolean	gameOver;
	private boolean	paused;
	private long	score;

	public Game()
	{
		this.board = new Board();
		board.addListener((GameOverListener)this);
		board.addListener((LineBreakListener)this);
		gameOver = false;
		paused = false;
		score = 0;
	}

	public boolean isPlaying()
	{
		return !gameOver && !paused;
	}

	public void tick()
	{
		
		if (paused)
			return;
		if (gameOver)
			return;
		if (!board.moveDown())
			board.lockTetromino();
	}

	public void start()
	{
		paused = true;
	}

	public void pause()
	{
		paused = false;
	}

	public void turnClockWise()
	{
		board.turnClockwise();
	}

	public void turnCounterClockWise()
	{
		board.turnCounterClockwise();
	}

	public void speedDown()
	{
		board.moveDown();
	}

	public void moveRight()
	{
		board.moveRight();
	}

	public void moveLeft()
	{
		board.moveLeft();
	}

	public String toString()
	{
		return board.toString();
	}

	public void gameOver(GameOverEvent e)
	{
		gameOver = true;
		System.out.println("Game Over");
	}

	public long getScore()
	{
		return score;
	}

	@Override
	public void LineBreak(LineBreakEvent e)
	{
		score += 10 * e.getNbLines();
		System.err.println("->"+score);
	}
	
	public int getLvl()
	{
		if (score > 1000)
			return 10;
		return (int) (score / 100);
	}
}
