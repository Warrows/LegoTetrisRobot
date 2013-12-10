package robotTetris.tetrisGame.modele;

public class Game implements GameOverListener
{
	private Board	board;
	private boolean playing;

	public Game()
	{
		this.board = new Board();
		board.addListener(this);
		playing = true;
	}
	
	public boolean isPlaying()
	{
		return playing;
	}

	public void tick()
	{
		if (!board.moveDown())
			board.lockTetromino();
	}

	public void start()
	{
	}

	public void pause()
	{
	}// TODO Auto-generated method stub
	

	public void turnClockWise()
	{
		board.turnClockwise();
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
		playing = false;
		System.out.println("Game Over");
	}
}
