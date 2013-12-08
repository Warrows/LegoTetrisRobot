package robotTetris.tetrisGame.modele;

import java.util.Vector;

public class Board {

	private final int WIDTH = 10;
	private final int HEIGHT = 22;
	private Cell[][] board ;
	private Tetromino tetromino;
	
    /*public Vector  myCell;
    public Vector  myCell;
    public Vector  myGame;
    public Vector  myGame;
    public Vector  myCell;
    public Vector  myTetromino;*/

    public Board () {
    	board = new Cell[WIDTH][HEIGHT];
    }
    
    /**
     * 
     * @param newTetromino - Nouveau tetromino mis à jour par Game
     */
    public void updateTetromino(Tetromino newTetromino) {
    	tetromino = newTetromino;
    }
    
    public void moveDown () {
    	if ()
    }
    
    /**
     * Déplace le tetromino vers la gauche
     */
    public void moveLeft () {
    	
    }
    
    /**
     * Déplace le tetromino vers la droite
     */
    public void moveRight () {
    	
    }
    
    /**
     * Tourne le tetromino dans le sens horaire
     */
    public void turnClockwise () {
    	tetromino.turnClockwise (this);
    }
    
    /**
     * Tourne le tetromino dans le sens hiraire inverse
     */
    public void turnCounterClockwise () {
    	tetromino.turnCounterClockwise(this);
    }
    
    /**
     * Vérifie si le tetromino peut se déplacer suivant les coordonnées x, y
     * @param x
     * @param y
     * @return
     */
    public boolean isOccupied (int x, int y) {
    	
    	return board[x][y].isOccupied();
    }
    
    /* *
     * à virer sur le long terme
     */
    public void main () {
    	
    }
}




























