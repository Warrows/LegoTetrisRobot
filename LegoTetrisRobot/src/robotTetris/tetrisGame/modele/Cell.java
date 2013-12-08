package robotTetris.tetrisGame.modele;

public class Cell
{
	boolean occupied;

	public Cell()
	{
		this.occupied = false;
	}

	public boolean occupy()
	{
		if (isOccupied())
			return false;
		occupied = true;
		return true;
	}

	public boolean free()
	{
		if (!isOccupied())
			return false;
		occupied = false;
		return true;
	}

	public boolean isOccupied()
	{
		return occupied;
	}

}