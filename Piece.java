package chess;

import java.util.Map;

public abstract class Piece {

    Map<String, ReturnPiece> board = Chess.board;


    public abstract boolean move(int startFile, int startRank, int endFile, int endRank);

    public abstract boolean validMove(int startFile, int startRank, int endFile, int endRank);
    
}
