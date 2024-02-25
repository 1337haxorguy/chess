package chess;

import chess.ReturnPiece.PieceFile;

public class Knight extends Piece {

    public boolean validMove(String from, String to) {

        //checks if knight is at from location
        //returns false if not there
        if (!board.containsKey(from)) {
           
            return false;
        }

        // Grabbing current location of the knight.
        ReturnPiece start = board.get(from);

        //grabbing destination file and rank from String to
        PieceFile toFile = Chess.charToPieceFile(to.charAt(0));
        int toRank = Integer.parseInt(String.valueOf(to.charAt(1)));

        // Calculate differences in file and rank to determine if the move is legal.
        int fileDifference = Math.abs(toFile.ordinal() - start.pieceFile.ordinal());
        int rankDifference = Math.abs(toRank - start.pieceRank);

        // Checking for L-shaped move which is 2 squares one direction, 1 square the other direction vice versa
        boolean isLegalKnightMove = (fileDifference == 2 && rankDifference == 1) || (fileDifference == 1 && rankDifference == 2);

            //if the move corresponds to an L-shaped, returns false if not L-shaped
        if (!isLegalKnightMove) {
            return false;
        }

        // Check if the destination square is occupied by a piece of the same color.
        if (board.containsKey(to)) {
            ReturnPiece destination= board.get(to);
            // Check if the pieces are of the same color
            if (start.pieceType.ordinal() / 6 == destination.pieceType.ordinal() / 6) {
                // The destination is occupied by a piece of the same color, move is not legal.
                return false;
            }
        }

        
        return true;
    }

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
}
