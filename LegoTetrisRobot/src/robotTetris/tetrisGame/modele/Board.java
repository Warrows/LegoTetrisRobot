package robotTetris.tetrisGame.modele;

public class Board
{

	private final int	WIDTH	= 10;
	private final int	HEIGHT	= 22;
	private Cell[][]	board;
	private Tetromino	tetromino;

	public Board()
	{
		board = new Cell[WIDTH][HEIGHT];
		for (int col = 0; col < WIDTH; col++)
			for (int row = 0; row < HEIGHT; row++)
				board[col][row] = new Cell(col, row);
		updateTetromino();
	}

	/**
	 * 
	 * @param newTetromino
	 *            - Nouveau tetromino mis à jour par Game
	 */
	public void updateTetromino()
	{
		tetromino = new Tetromino();
	}

	public Cell getCell(int row, int col)
	{
		return board[col][row];
	}

	public boolean moveDown()
	{
		for (Cell cell : tetromino.getCells(this))
			if (isOccupied(cell.getCol(), cell.getRow()+1))
				return false;
		tetromino.fall();
		return true;
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
			return true;
		if (row < 0)
			return true;
		if (row >= HEIGHT)
			return true;
		if (col >= WIDTH)
			return true;

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
				if (getCell(row, col).isOccupied()
						|| tetromino.getCells(this).contains(getCell(row, col)))
					str += " X ";
				else
					str += " . ";
			}
			str += "|\n";
			if (row == HEIGHT - 1)
				str += "\\______________________________/";
		}

		return str;
	}

	public void lockTetromino()
	{
		for (Cell c: tetromino.getCells(this))
			c.occupy();
		updateTetromino();
	}
}
