package robotTetris.tetrisGame.modele;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import robotTetris.tetrisGame.modele.events.GameOverEvent;
import robotTetris.tetrisGame.modele.events.GameOverListener;
import robotTetris.tetrisGame.modele.events.LineBreakEvent;
import robotTetris.tetrisGame.modele.events.LineBreakListener;

public class Board
{

	public static final int			WIDTH				= 10;
	public static final int			HEIGHT				= 22;
	private Cell[][]				board;
	private Tetromino				tetromino;
	private List<GameOverListener>	gameOverListeners	= new ArrayList<GameOverListener>();
	private List<LineBreakListener>	lineBreakListeners	= new ArrayList<LineBreakListener>();

	public Board()
	{
		board = new Cell[WIDTH][HEIGHT];
		for (int col = 0; col < WIDTH; col++)
			for (int row = 0; row < HEIGHT; row++)
				board[col][row] = new Cell(col, row);
		updateTetromino();
	}

	public void addListener(GameOverListener toAdd)
	{
		gameOverListeners.add(toAdd);
	}

	public void addListener(LineBreakListener toAdd)
	{
		lineBreakListeners.add(toAdd);
	}

	/**
	 * 
	 * @param newTetromino
	 */
	public void updateTetromino()
	{
		tetromino = new Tetromino();
		for (Cell c : tetromino.getCells(this))
			if (c.isOccupied())
			{
				gameOver();
				return;
			}
	}

	public Cell getCell(int row, int col)
	{
		return board[col][row];
	}

	public boolean moveDown()
	{
		for (Cell cell : tetromino.getCells(this))
			if (isOccupied(cell.getCol(), cell.getRow() + 1))
				return false;
		tetromino.fall();
		return true;
	}

	/**
	 * Déplace le tetromino vers la gauche
	 * 
	 * @return
	 */
	public boolean moveLeft()
	{
		for (Cell cell : tetromino.getCells(this))
			if (isOccupied(cell.getCol() - 1, cell.getRow()))
				return false;
		tetromino.moveLeft();
		return true;
	}

	/**
	 * Déplace le tetromino vers la droite
	 */
	public boolean moveRight()
	{
		for (Cell cell : tetromino.getCells(this))
			if (isOccupied(cell.getCol() + 1, cell.getRow()))
				return false;
		tetromino.moveRight();
		return true;
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
		TreeSet<Integer> rows = new TreeSet<Integer>();
		if (tetromino.isOut())
		{
			gameOver();
			return;
		}
		for (Cell c : tetromino.getCells(this))
		{
			c.occupy();
			rows.add(c.getRow());
		}
		breakLines(rows);
		updateTetromino();
	}
	
	private void breakLines(TreeSet<Integer> rows)
	{
		TreeSet<Integer> rows2 = new TreeSet<Integer>();
		rows2.addAll(rows);
		for (int row : rows)
		{
			for (int i = 0; i < WIDTH; i++)
			{
				if (!getCell(row, i).isOccupied())
				{
					rows2.remove(row);
					break;
				}
			}
		}
		for (LineBreakListener listener : lineBreakListeners)
			listener.LineBreak(new LineBreakEvent(rows2.size()));
		for (int row : rows2)
			breakLine(row);
	}

	private boolean breakLine(int rowToClear)
	{
		for (int i = 0; i < WIDTH; i++)
		{
			getCell(rowToClear, i).free();
		}
		for (int row = rowToClear; row >= 0; row--)
			for (int col = 0; col < WIDTH; col++)
				if (getCell(row, col).isOccupied())
				{
					getCell(row + 1, col).occupy();
					getCell(row, col).free();
				}
		return true;
	}

	private void gameOver()
	{
		for (GameOverListener listener : gameOverListeners)
			listener.gameOver(new GameOverEvent());
	}
}
