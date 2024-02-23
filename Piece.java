package chess;

import java.util.Map;

public abstract class Piece {

    static Map<String, ReturnPiece> board = Chess.board;

    public abstract boolean validMove(String from, String to);

    public boolean move(String from, String to) {

        System.out.println("called move");

        if (!board.containsKey(from)) {
            System.out.println("cannot find start piece");
            return false;
        }

        if (this.validMove(from, to) == false) {
            System.out.println("this is invalid!");
            return false;
        } 

        if (doesMovePutsOwnKingInCheck(from, to)) {
            System.out.println("king in check");
            return false;
        }

        if (board.containsKey(to)) {
            Chess.pieces.remove(board.get(to));
            board.remove(to);
        }

        ReturnPiece wow = board.remove(from);
        board.put(to, wow);
        wow.pieceRank = Integer.parseInt(String.valueOf(to.charAt(1)));
        wow.pieceFile = Chess.charToPieceFile(to.charAt(0));
        
        return true;


    }

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

    public boolean doesMovePutsOwnKingInCheck(String from, String to){

        ReturnPiece movedPiece = board.remove(from);
        ReturnPiece tempPiece = movedPiece;
        ReturnPiece removedPiece = new ReturnPiece();
        boolean pieceRemoved = false;

        if (board.containsKey(to)) {
            removedPiece = board.remove(to);
            pieceRemoved = true;
            
        }
        board.put(to, movedPiece);
        movedPiece.pieceRank = Integer.parseInt(String.valueOf(from.charAt(1)));
        movedPiece.pieceFile = Chess.charToPieceFile(from.charAt(0));

        if (Chess.isOwnKingInCheck(movedPiece)) {
            board.remove(to);

            board.put(from, tempPiece);

            if (pieceRemoved) {
                board.put(to, removedPiece);
            }
    
            return true;
        }

        board.remove(to);

        board.put(from, tempPiece);

        if (pieceRemoved) {
            board.put(to, removedPiece);
        }


        return false;
        
    }
    
    
}
