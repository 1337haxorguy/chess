package chess;

import chess.ReturnPiece.PieceType;

public class King extends Piece {

    public boolean hasMoved = false;

    public boolean validMove(String from, String to) {
        System.out.println("running valid move with king " + from + " " + to);

        if (!board.containsKey(from)) {
            System.out.println("cannot find start piece");
            return false;
        }
        
        ReturnPiece start = board.get(from);
        ReturnPiece destination = new ReturnPiece();
        destination.pieceFile = Chess.charToPieceFile(to.charAt(0));
        destination.pieceRank = Integer.parseInt(String.valueOf(to.charAt(1)));



        if (board.containsKey(to) && start.pieceType.ordinal() / 6 == board.get(to).pieceType.ordinal() / 6) { 
            //divide by six for the two different colors
            System.out.println("bruh");

            return false; // Piece in the end position is of the same color
        } else if (!Chess.hasWhiteKingMoved && from == "e1") { //White castling

            if (board.get(from).pieceType == PieceType.WK) {
                if (to == "g1") {
                    if (board.containsKey("a1")) {
                        ReturnPiece maybeRook = board.get("a1");
                        if (maybeRook.pieceType == PieceType.WR) {

                            if (!Chess.hasWhiteRookQueenSideMoved) {

                                if (!board.containsKey("b1") && !board.containsKey("c1") && !board.containsKey("d1")) {

                                    for (ReturnPiece setPiece : board.values()) {

                                        Piece currPiece = Chess.checkPieceType(setPiece);
                            
                                        if (setPiece != maybeRook) {
                            
                                            if (setPiece.pieceType.ordinal() / 6 == 1) { // Piece is white
                            
                                                if (currPiece.validMove(Chess.getPiecePosition(setPiece.pieceFile, setPiece.pieceRank), "c1")) {
                                                    System.out.println("IN CHECK BY TYPE" + setPiece.pieceType);
                                                    return true; //I FINISHED WORKING HEREEEEE
                                                }
                                            }
                                        }
                                    }   

                                }

                                

                            }
                            
                        }
                    }

                } else if (to == "c1") {

                }
            }

        }

        int differenceInHori = Math.abs(start.pieceFile.ordinal() - destination.pieceFile.ordinal());
        int differenceInVerti = Math.abs(start.pieceRank - destination.pieceRank);

        if (differenceInHori <= 1 && differenceInVerti <= 1) {
            return true;
        }

        return false;


    }

    public boolean move(String from, String to) {


        System.out.println("called move with type king");

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

        System.out.println("Moved king to " + wow.pieceFile + wow.pieceRank);

        if (from == "e1") {
            Chess.hasWhiteKingMoved = true;
        } else if (from == "e8") {
            Chess.hasBlackKingMoved = true;
        }
    
        return true;


    }

}