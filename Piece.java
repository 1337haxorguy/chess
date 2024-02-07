package chess;

public interface Piece {

    public boolean canMove(int startFile, int startRank, int endFile, int endRank, boolean occupied); 
    
    
}
