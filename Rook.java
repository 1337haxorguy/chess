package chess;

import chess.ReturnPiece.PieceFile;

public class Rook extends Piece {

    public boolean validMove(String from, String to) {

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
        }

        // Rook can move horizontally or vertically
        if (start.pieceFile == destination.pieceFile || start.pieceRank == destination.pieceRank) {

            // Check if there are any pieces blocking the path
            if (start.pieceFile == destination.pieceFile) { // Moving vertically
                System.out.println("Moving vertically");


                int step = Integer.compare(destination.pieceRank, start.pieceRank);

                for (int rank = start.pieceRank + step; rank != destination.pieceRank; rank += step) {
                    if (board.containsKey(Chess.getPiecePosition(start.pieceFile, rank))) {
                        System.out.println("path blocked");
                        return false; // Path is blocked
                    }
                }
            } else { // Moving horizontally

                int step = destination.pieceFile.compareTo(start.pieceFile);

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


        if (this.validMove(from, to) == false) {
            System.out.println("invalid move!");
            return false;
        } 

        if (!board.containsKey(from)) {
            return false;
        }

        if (board.containsKey(to)) {
            board.remove(to);
        }

        ReturnPiece wow = board.remove(from);
        board.put(to, wow);
        wow.pieceRank = Integer.parseInt(String.valueOf(to.charAt(1)));
        wow.pieceFile = Chess.charToPieceFile(to.charAt(0));
        
        

        return true;


    }
}
