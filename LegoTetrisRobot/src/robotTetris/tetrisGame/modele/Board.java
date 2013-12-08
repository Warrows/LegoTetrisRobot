package robotTetris.tetrisGame.modele;

public class Board
{

	private final int WIDTH = 10;
	private final int HEIGHT = 22;
	private Cell[][] board;
	private Tetromino tetromino;

	public Board()
	{
		board = new Cell[WIDTH][HEIGHT];
		for (int col = 0; col < WIDTH; col++)
			for (int row = 0; row < HEIGHT; row++)
				board[col][row] = new Cell(col, row);
	}

	/**
	 * 
	 * @param newTetromino
	 *            - Nouveau tetromino mis à jour par Game
	 */
	public void updateTetromino(Tetromino newTetromino)
	{
		tetromino = newTetromino;
	}

	public Cell getCell(int row, int col)
	{
		return board[col][row];
	}

	public void moveDown()
	{
		/* if () */
	}

	/**
	 * Déplace le tetromino vers la gauche
	 */
	public void moveLeft()
	{

	}

	/**
	 * Déplace le tetromino vers la droite
	 */
	public void moveRight()
	{

	}

	/**
	 * Tourne le tetromino dans le sens horaire
	 */
	public void turnClockwise()
	{
		tetromino.turnClockwise(this);
	}

	/**
	 * Tourne le tetromino dans le sens hiraire inverse
	 */
	public void turnCounterClockwise()
	{
		tetromino.turnCounterClockwise(this);
	}

	/**
	 * Vérifie si le tetromino peut se déplacer suivant les coordonnées x, y
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isOccupied(int col, int row)
	{
		if (col < 0)
			return false;
		if (row < 0)
			return false;
		if (row < HEIGHT)
			return false;
		if (row < WIDTH)
			return false;

		return board[col][row].isOccupied();
	}

	public String toString()
	{

		String str = "\n";
		for (int row = 0; row < HEIGHT; row++)
		{
			str += "|";
			for (int col = 0; col < WIDTH; col++)
			{
				if (!this.getCell(row, col).isOccupied())
					str += " . ";
				else
					str += "X";
			}
			str += "|\n";
			if (row == HEIGHT-1)
				str += "________________________________";
		}

		return str;
	}

	/* *
	 * à virer sur le long terme
	 */
	public static void main(String[] arg)
	{
		Board board = new Board();
		System.out.println(board);
	}
}
