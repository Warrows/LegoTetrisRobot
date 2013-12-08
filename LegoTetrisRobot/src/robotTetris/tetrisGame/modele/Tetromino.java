package robotTetris.tetrisGame.modele;

public class Tetromino
{
	private TetrominoType type;
	private boolean[][] representation =
	{
	{ false, false, false, false },
	{ false, false, false, false },
	{ false, false, false, false },
	{ false, false, false, false } };

	public Tetromino(TetrominoType type)
	{
		this.type = type;
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
		return false;
	}

	public boolean turnCounterClockwise(Board board)
	{
		return false;
	}

	public static void main(String[] args)
	{
		for (int i = 0; i < 7; i++)
		{
			System.out.println(new Tetromino(TetrominoType.values()[i]));
			System.out.println("\n\n");
		}
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