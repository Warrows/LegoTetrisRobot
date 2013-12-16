package robotTetris.tetrisGame.modele;

import java.util.HashSet;
import java.util.Set;

public class Tetromino
{
	private TetrominoType	type;
	private int				rowOffset;
	private int				colOffset;
	private boolean[][]		representation;

	public Tetromino()
	{
		this.type = TetrominoType.getRandomTetrominoType();
		colOffset = Board.WIDTH/2;
		rowOffset = 0;
		switch (type)
		{
		case O:
			representation = new boolean[2][2];
			representation[0][0] = true;
			representation[0][1] = true;
			representation[1][0] = true;
			representation[1][1] = true;
			break;
		case I:
			representation = new boolean[3][4];
			representation[1][0] = true;
			representation[1][1] = true;
			representation[1][2] = true;
			representation[1][3] = true;
			break;
		case J:
			representation = new boolean[3][3];
			representation[0][2] = true;
			representation[1][0] = true;
			representation[1][1] = true;
			representation[1][2] = true;
			break;
		case L:
			representation = new boolean[3][3];
			representation[1][0] = true;
			representation[1][1] = true;
			representation[1][2] = true;
			representation[2][2] = true;
			break;
		case S:
			representation = new boolean[3][2];
			representation[1][0] = true;
			representation[2][0] = true;
			representation[0][1] = true;
			representation[1][1] = true;
			break;
		case T:
			representation = new boolean[3][3];
			representation[1][0] = true;
			representation[1][1] = true;
			representation[1][2] = true;
			representation[0][1] = true;
			break;
		case Z:
			representation = new boolean[3][2];
			representation[0][0] = true;
			representation[1][0] = true;
			representation[1][1] = true;
			representation[2][1] = true;
			break;
		}
	}

	public boolean turnClockwise(Board board)
	{
		final int M = representation.length;
		final int N = representation[0].length;
		boolean[][] ret = new boolean[N][M];
		for (int r = 0; r < M; r++)
		{
			for (int c = 0; c < N; c++)
			{
				if (board.isOccupied(M - 1 - r + colOffset, c + rowOffset))
					return false;
				ret[c][M - 1 - r] = representation[r][c];
			}
		}
		representation = ret;
		return true;
	}

	public boolean turnCounterClockwise(Board board)
	{
		final int M = representation.length;
		final int N = representation[0].length;
		boolean[][] ret = new boolean[N][M];
		for (int r = 0; r < M; r++)
		{
			for (int c = 0; c < N; c++)
			{
				if (board.isOccupied(r + colOffset, M - 1 - c + rowOffset))
					return false;
				ret[M - 1 - c][r] = representation[r][c];
			}
		}
		representation = ret;
		return true;
	}

	/**
	 * This does not check anything, only change data
	 */
	public void fall()
	{
		rowOffset += 1;
	}

	/**
	 * This does not check anything, only change data
	 */
	public void moveRight()
	{
		colOffset += 1;
	}

	/**
	 * This does not check anything, only change data
	 */
	public void moveLeft()
	{
		colOffset -= 1;
	}

	public Set<Cell> getCells(Board board)
	{
		HashSet<Cell> ret = new HashSet<Cell>();
		for (int row = 0; row < representation[0].length; row++)
		{
			for (int col = 0; col < representation.length; col++)
				if (representation[col][row]
						&& isAcceptable(col + colOffset, row + rowOffset))
				{
					ret.add(board.getCell(rowOffset + row, colOffset + col));
				}
		}
		return ret;
	}

	private boolean isAcceptable(int col, int row)
	{
		if (col < 0)
			return false;
		if (col > Board.WIDTH)
			return false;
		if (row < 0)
			return false;
		if (row > Board.HEIGHT)
			return false;
		return true;
	}

	public boolean isOut()
	{
		for (int row = 0; row < representation[0].length; row++)
			for (int col = 0; col < representation.length; col++)
			{
				if (!isAcceptable(col + colOffset, row + rowOffset))
					if (representation[col][row])
						return true;
			}
		return false;
	}

	public String toString()
	{
		String s = "";
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)

				if (representation[i][j])
					s += "X";
				else
					s += ".";
			s += "\n";
		}
		return s;
	}
}
