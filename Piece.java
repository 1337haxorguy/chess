package chess;

import java.util.Map;

public abstract class Piece {

    static Map<String, ReturnPiece> board = Chess.board;

    public abstract boolean move(String from, String to);

    public abstract boolean validMove(String from, String to);

    public void undoMoveIfCheck(String from, String to, ReturnPiece movedPiece, ReturnPiece removedPiece) {
        // Undo the move
        board.remove(to);
        board.put(from, movedPiece);
        movedPiece.pieceRank = Integer.parseInt(String.valueOf(from.charAt(1)));
        movedPiece.pieceFile = Chess.charToPieceFile(from.charAt(0));
        
        // Restore the removed piece if there was one
        if (removedPiece != null) {
            board.put(to, removedPiece);
            Chess.pieces.add(removedPiece);
        }
    }
    
    
}
