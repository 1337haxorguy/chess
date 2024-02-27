package chess;

import java.util.Map;

public abstract class Piece {

    public boolean hasMoved = false;

    static Map<String, ReturnPiece> board = Chess.board;

    public abstract boolean validMove(String from, String to);

    public boolean move(String from, String to) {


        if (!board.containsKey(from)) {
            return false;
        }

        if (!validMove(from, to)) {
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


        if (Chess.isOwnKingInCheck(wow)) {
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

    public boolean hypotheticalMove (String from, String to) {

        boolean isMovePossible = true;

        if (!board.containsKey(from)) {
            return false;
        }

        if (!validMove(from, to)) {
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


        if (Chess.isOwnKingInCheck(wow)) {
            isMovePossible = false;

        }

        board.remove(to);
        board.put(from, wow);
        wow.pieceRank = Integer.parseInt(String.valueOf(from.charAt(1)));
        wow.pieceFile = Chess.charToPieceFile(from.charAt(0));


        if (pieceRemoved) {
            board.put(to, removedPiece);
            Chess.pieces.add(board.get(to));

        }


        
        return isMovePossible;



    }

    public boolean doesMovePutOpposingKingInCheck (String from, String to) {

        boolean doesmoveputkingincheck = false;

        if (!board.containsKey(from)) {
            return false;
        }

        if (!validMove(from, to)) {
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


        if (Chess.isOwnKingInCheck(!Chess.checkPieceColor(wow))) {
            doesmoveputkingincheck = true;

        }

        board.remove(to);
        board.put(from, wow);
        wow.pieceRank = Integer.parseInt(String.valueOf(from.charAt(1)));
        wow.pieceFile = Chess.charToPieceFile(from.charAt(0));


        if (pieceRemoved) {
            board.put(to, removedPiece);
            Chess.pieces.add(board.get(to));

        }


        return doesmoveputkingincheck;




    }

    public boolean canPieceBlockPath(String to, boolean Color) {

        boolean isMovePossible = false;

        if (!board.containsKey(to)) {
            return false;
        }

        Piece currPieceType = new Pawn();

		for (ReturnPiece setPiece : board.values()) {

            if (Chess.checkPieceColor(setPiece) != Color) {
                continue;
            }

			currPieceType = Chess.checkPieceType(setPiece);

			currPieceType.hypotheticalMove(Chess.getPiecePosition(setPiece.pieceFile, setPiece.pieceRank), to);
			
		}

        return isMovePossible;


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

