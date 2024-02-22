package chess;

import java.util.Map;

public class Pawn extends Piece {

    public static boolean isValidMove(Map<String, ReturnPiece> board, String from, String to, ReturnPiece.PieceType pieceType) {
        //grabbing current location
        PieceFile fromFile = PieceFile.valueOf(from.substring(0, 1));
        int fromRank = Integer.parseInt(from.substring(1));

        //grabbing next location 
        PieceFile toFile = PieceFile.valueOf(to.substring(0, 1));
        int toRank = Integer.parseInt(to.substring(1));

        //if direction is 1, the piece is White so we move up rank
        //if direction is -1, the piece is Black so we move down rank
        int direction = pieceType.toString().startsWith("W") ? 1 : -1;

        // Check for a valid one-square forward move
        if (fromFile == toFile && ((toRank - fromRank) == direction)) {
            // The target space must be empty
            String toPosition = toFile.toString() + toRank;
            return !board.containsKey(toPosition);
        }

        // Check for a valid capture move (one square diagonally)
        if (Math.abs(fromFile.ordinal() - toFile.ordinal()) == 1 && (toRank - fromRank) == direction) {
            // There must be an opponent's piece at the target space
            String toPosition = toFile.toString()+ toRank;
            ReturnPiece targetPiece = board.get(toPosition);
            //if the piece is there and not the same color
            if (targetPiece != null && targetPiece.pieceType != pieceType) {
                return true;
            }
        }

        //if everything fails
        return false;
    }
}
