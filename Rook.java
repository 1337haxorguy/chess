package chess;

import chess.ReturnPiece.PieceFile;

public class Rook extends Piece {

    public boolean hasMoved = false;

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
        }

        // Rook can move horizontally or vertically
        if (start.pieceFile == destination.pieceFile || start.pieceRank == destination.pieceRank) {

            // Check if there are any pieces blocking the path
            if (start.pieceFile == destination.pieceFile) { // Moving vertically
                int step = Integer.compare(destination.pieceRank, start.pieceRank);
                for (int rank = start.pieceRank + step; rank != destination.pieceRank; rank += step) {
                    if (board.containsKey(Chess.getPiecePosition(start.pieceFile, rank))) {
                        return false; // Path is blocked
                    }
                }
            } else { // Moving horizontally

                int destinationOrd = destination.pieceFile.ordinal();
                int startOrd = start.pieceFile.ordinal();

                int step = Integer.compare(destinationOrd, startOrd); // Use Integer.compare method
                for (int file = start.pieceFile.ordinal() + step; file != destination.pieceFile.ordinal(); file += step) {
                    if (board.containsKey(Chess.getPiecePosition(PieceFile.values()[file], start.pieceRank))) {
                        return false; // Path is blocked
                    }
                }
            }
                        return true; // Valid move
        }

        return false; // Invalid move for a Rook
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

        if (from.equals("a1")) {
            Chess.hasWhiteRookQueenSideMoved = true;
        } else if (from.equals("h1")) {
            Chess.hasWhiteRookKingSideMoved = true;
        } else if (from.equals("a8")) {
            Chess.hasBlackRookQueenSideMoved = true;
        } else if (from.equals("h8")) {
            Chess.hasBlackRookKingSideMoved = true;
        }
        return true;


    }


}
