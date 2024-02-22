package chess;

import java.util.Map;

public abstract class Piece {

    Map<String, ReturnPiece> board = Chess.board;

    public abstract boolean move(String from, String to);

    public abstract boolean validMove(String from, String to);
    
}
