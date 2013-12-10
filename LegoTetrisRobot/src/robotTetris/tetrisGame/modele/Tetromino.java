package robotTetris.tetrisGame.modele;

import java.util.HashSet;
import java.util.Set;

public class Tetromino
{
	private TetrominoType	type;
	private int				rowOffset;
	private int				colOffset;
	private boolean[][]		representation	= { { false, false, false, false },
			{ false, false, false, false }, { false, false, false, false },
			{ false, false, false, false }	};

	public Tetromino()
	{
		this.type = TetrominoType.getRandomTetrominoType();
		colOffset = 3;
		rowOffset = -2;
		switch (type)
		{
		case O:
			representation[2][2] = true;
			representation[2][1] = true;
			representation[1][2] = true;
			representation[1][1] = true;
			break;
		case I:
			representation[1][0] = true;
			representation[1][1] = true;
			representation[1][2] = true;
			representation[1][3] = true;
			break;
		case J:
			representation[2][0] = true;
			representation[2][1] = true;
			representation[2][2] = true;
			representation[1][2] = true;
			break;
		case L:
			representation[1][0] = true;
			representation[1][1] = true;
			representation[1][2] = true;
			representation[2][2] = true;
			break;
		case S:
			representation[1][1] = true;
			representation[1][2] = true;
			representation[2][0] = true;
			representation[2][1] = true;
			break;
		case T:
			representation[1][1] = true;
			representation[1][2] = true;
			representation[1][3] = true;
			representation[2][2] = true;
			break;
		case Z:
			representation[1][1] = true;
			representation[1][2] = true;
			representation[2][2] = true;
			representation[2][3] = true;
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
		for (int row = 0; row < 4; row++)
		{
			for (int col = 0; col < 4; col++)
				if (representation[col][row]
						&& isAcceptable(col + colOffset, row + rowOffset))
				{
					System.err.println("-->" + (colOffset + col) + " " + col
							+ " " + colOffset);
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
		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++)
			{
				System.err.println(col);
				System.err.println(colOffset);
				System.err.println(col + colOffset);
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
