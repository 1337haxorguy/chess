package chess;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public interface Piece {



    public boolean move(int startFile, int startRank, int endFile, int endRank, boolean occupied); 

    
}
