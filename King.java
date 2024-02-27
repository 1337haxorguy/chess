package chess;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class King extends Piece {

    public boolean castlingRight = false;
    public boolean castlingLeft = false;

    public boolean validMove(String from, String to) {

        if (!board.containsKey(from)) {
            return false;
        }
        
        ReturnPiece start = board.get(from);
        ReturnPiece destination = new ReturnPiece();
        destination.pieceFile = Chess.charToPieceFile(to.charAt(0));
        destination.pieceRank = Integer.parseInt(String.valueOf(to.charAt(1)));



        if (board.containsKey(to) && start.pieceType.ordinal() / 6 == board.get(to).pieceType.ordinal() / 6) { 
            //divide by six for the two different colors

            return false; // Piece in the end position is of the same color
        } else if (!Chess.hasWhiteKingMoved && from.equals("e1")) { //White castling
            //System.out.println(Chess.hasWhiteKingMoved);
            if (board.get(from).pieceType == PieceType.WK) {

                if (to.equals("g1")) {

                    if (board.containsKey("h1")) {

                        ReturnPiece maybeRook = board.get("h1");
                        if (maybeRook.pieceType == PieceType.WR) {

                            if (!Chess.hasWhiteRookKingSideMoved) {

                                if (!board.containsKey("f1") && !board.containsKey("g1")) {
                                    castlingRight = true;
                                    return true;

                                }
                            }
                        }
                    }

                } else if (!Chess.hasWhiteKingMoved && to.equals("c1")) {
                    if (board.containsKey("a1")) {
                        ReturnPiece maybeRook = board.get("a1");
                        if (maybeRook.pieceType == PieceType.WR) {
                            if (!Chess.hasWhiteRookQueenSideMoved) {
                                if (!board.containsKey("b1") && !board.containsKey("c1") && !board.containsKey("d1")) {
                                    castlingLeft = true;
                                    return true;

                                }
                            }
                        }
                    }
                }
            }
        } else if (!Chess.hasBlackKingMoved && from.equals("e8")) { //BLACK KINGGGG
            if (board.get(from).pieceType == PieceType.BK) {

                if (to.equals("c8")) {

                    if (board.containsKey("a8")) {
                        ReturnPiece maybeRook = board.get("a8");
                        if (maybeRook.pieceType == PieceType.BR) {

                            if (!Chess.hasBlackRookQueenSideMoved) {

                                if (!board.containsKey("b8") && !board.containsKey("c8") && !board.containsKey("d8")) {
                                    castlingLeft = true;
                                    return true;

                                }
                            }
                        }
                    }

                } else if (!Chess.hasBlackKingMoved && to.equals("g8")) {
                    if (board.containsKey("h8")) {

                        ReturnPiece maybeRook = board.get("h8");
                        if (maybeRook.pieceType == PieceType.BR) {

                            if (!Chess.hasBlackRookKingSideMoved) {
                                if (!board.containsKey("f8") && !board.containsKey("g8")) {
                                    castlingRight = true;
                                    return true;

                                }
                            }
                        }
                    }
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

        if (castlingLeft) {
            ReturnPiece rook = board.get(Chess.getPiecePosition(PieceFile.a, Integer.parseInt(String.valueOf(from.charAt(1)))));
            Chess.pieces.remove(rook);
            board.remove(Chess.getPiecePosition(PieceFile.a, Integer.parseInt(String.valueOf(from.charAt(1)))));
            board.put(Chess.getPiecePosition(PieceFile.values()[(Chess.charToPieceFile(to.charAt(0)).ordinal() + 1)], Integer.parseInt(String.valueOf(to.charAt(1)))), rook);
            rook.pieceRank = wow.pieceRank;
            rook.pieceFile = PieceFile.values()[(Chess.charToPieceFile(to.charAt(0)).ordinal() + 1)];
            Chess.pieces.add(rook);
            castlingLeft = false;
        }

        if (castlingRight) {
            ReturnPiece rook = board.get(Chess.getPiecePosition(PieceFile.h, Integer.parseInt(String.valueOf(from.charAt(1)))));
            Chess.pieces.remove(rook);
            board.remove(Chess.getPiecePosition(PieceFile.h, Integer.parseInt(String.valueOf(from.charAt(1)))));
            board.put(Chess.getPiecePosition(PieceFile.values()[(Chess.charToPieceFile(to.charAt(0)).ordinal() - 1)], Integer.parseInt(String.valueOf(to.charAt(1)))), rook);
            rook.pieceRank = wow.pieceRank;
            rook.pieceFile = PieceFile.values()[(Chess.charToPieceFile(to.charAt(0)).ordinal() - 1)];
            Chess.pieces.add(rook);
            castlingRight = false;

        }


        if (from == "e1") {
            Chess.hasWhiteKingMoved = true;
        } else if (from == "e8") {
            Chess.hasBlackKingMoved = true;
        }
    
        return true;


    }

}