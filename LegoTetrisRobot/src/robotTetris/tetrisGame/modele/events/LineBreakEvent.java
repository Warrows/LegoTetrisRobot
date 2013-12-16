package robotTetris.tetrisGame.modele.events;

public class LineBreakEvent extends TetrisEvent
{
	int nbLines;
	
	public LineBreakEvent(int nbLines)
	{
		this.nbLines = nbLines;
	}
	
	public int getNbLines()
	{
		return nbLines;
	}
}