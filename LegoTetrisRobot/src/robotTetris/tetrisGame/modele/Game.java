package robotTetris.tetrisGame.modele;

public class Game implements TetrisListener
{
	private Board	board;
	private boolean playing;

	public Game()
	{
		this.board = new Board();
		playing = true;
		while (playing)
		{
			tick();
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void tick()
	{
		if (!board.moveDown())
			board.lockTetromino();
		System.out.println(board);
	}

	public void start()
	{
	}

	public void pause()
	{
	}
}
