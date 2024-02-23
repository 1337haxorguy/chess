package chess;

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


        if (this.validMove(from, to) == false) {
            System.out.println("invalid move!");
            return false;
        } 

        if (!board.containsKey(from)) {
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
        System.out.println(board.get(to).toString());
        
        
        

        return true;


    }
}
