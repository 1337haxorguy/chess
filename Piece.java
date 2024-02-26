package chess;

import java.util.Map;

public abstract class Piece {

    public boolean hasMoved = false;

    static Map<String, ReturnPiece> board = Chess.board;

    public abstract boolean validMove(String from, String to);

    public boolean move(String from, String to) {


        if (!board.containsKey(from)) {
            System.out.println("cannot find start piece");
            return false;
        }

        if (!validMove(from, to)) {
            System.out.println("this is invalid!");
            return false;
        } 

        boolean pieceRemoved = false;
        ReturnPiece removedPiece = new ReturnPiece();

        if (board.containsKey(to)) {
            Chess.pieces.remove(board.get(to));
            removedPiece = board.remove(to);
            pieceRemoved = true;
        }

        ReturnPiece wow = board.remove(from);
        board.put(to, wow);
        wow.pieceRank = Integer.parseInt(String.valueOf(to.charAt(1)));
        wow.pieceFile = Chess.charToPieceFile(to.charAt(0));

        System.out.println(wow.pieceRank + "" + wow.pieceFile);

        if (Chess.isOwnKingInCheck(wow)) {
            System.out.println("invalid move because your king is in check");
            board.remove(to);
            board.put(from, wow);
            wow.pieceRank = Integer.parseInt(String.valueOf(from.charAt(1)));
            wow.pieceFile = Chess.charToPieceFile(from.charAt(0));
    

            if (pieceRemoved) {
                board.put(to, removedPiece);
                Chess.pieces.add(board.get(to));

            }

            return false;

        }

        
        return true;


    }


    public void undoMove(String from, String to, ReturnPiece movedPiece, ReturnPiece removedPiece) {
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

    public void undoMove(String from, String to, ReturnPiece movedPiece) {
        // Undo the move
        board.remove(to);
        board.put(from, movedPiece);
        movedPiece.pieceRank = Integer.parseInt(String.valueOf(from.charAt(1)));
        movedPiece.pieceFile = Chess.charToPieceFile(from.charAt(0));
        
    }



    public void pawnPromotion(String promotionType, ReturnPiece pawn){
        //see if promotionType is null
        if (promotionType== null){
            promotionType= "Q";
        }
        switch(promotionType.toUpperCase()){
            case "Q":
                pawn.pieceType = (pawn.pieceType == ReturnPiece.PieceType.WP) ? ReturnPiece.PieceType.WQ : ReturnPiece.PieceType.BQ;
                break;
            case "R":
                pawn.pieceType = (pawn.pieceType == ReturnPiece.PieceType.WP) ? ReturnPiece.PieceType.WR : ReturnPiece.PieceType.BR;
                break;
            case "B":
                pawn.pieceType = (pawn.pieceType == ReturnPiece.PieceType.WP) ? ReturnPiece.PieceType.WB : ReturnPiece.PieceType.BB;
                break;
            case "N":
                pawn.pieceType = (pawn.pieceType == ReturnPiece.PieceType.WP) ? ReturnPiece.PieceType.WN : ReturnPiece.PieceType.BN;
                break;

        }
    }
}

