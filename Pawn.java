package chess;

public class Pawn extends Piece {

    boolean enPassant = false;

    public boolean validMove(String from, String to) {

        // Checks if no pawn in from location is there
        if (!board.containsKey((from))) {
            return false;
        }

        // grabbing current location
        ReturnPiece start = board.get(from);

        // grabbing next location
        ReturnPiece destination = new ReturnPiece();
        destination.pieceFile = Chess.charToPieceFile(to.charAt(0));
        destination.pieceRank = Integer.parseInt(String.valueOf(to.charAt(1)));

        // checking if theres already a piece in the destination location and checking
        // if they the same color
        // returns false if same color
        if (board.containsKey(to)) {
            if (start.pieceType.ordinal() / 6 == board.get(to).pieceType.ordinal() / 6) {
                return false;
            }
        }

        // if pieceType oridnal is 0-5, it is white
        // if pieceType ordinal is 6-11, it is black
        int direction = 0;
        if (start.pieceType.ordinal() < 6) {
            direction = 1; // direction will move forward
            if (destination.pieceRank < start.pieceRank) {
                return false;
            }
        } else {
            direction = -1;
            if (destination.pieceRank > start.pieceRank) {
                return false;
            }
        }

        boolean pawnFirstMove = (start.pieceType.toString().charAt(0) == 'W' && start.pieceRank == 2) ||
                (start.pieceType.toString().charAt(0) == 'B' && start.pieceRank == 7);

        if (pawnFirstMove && Math.abs(destination.pieceRank - start.pieceRank) == 2) {
            String oneSquarePosition = Chess.getPiecePosition(start.pieceFile, start.pieceRank + direction);
            String twoSquarePosition = Chess.getPiecePosition(start.pieceFile, start.pieceRank + (2 * direction));

            if (!board.containsKey(oneSquarePosition) && !board.containsKey(twoSquarePosition)) {
                if (to.equals(Chess.getPiecePosition(start.pieceFile, start.pieceRank + (2 * direction)))) {
                    // this will mean that the pawn has two clear squares in front of it
                    return true;

                }

            } else {
                // there is a piece blocking the pawn from going two squares foward
                return false;
            }
        }

        // Check for a valid one-square forward move
        if (destination.pieceFile.ordinal() == start.pieceFile.ordinal()
                && ((destination.pieceRank - start.pieceRank) == direction)) {
            // The target space must be empty
            String toPosition = destination.pieceFile.toString() + destination.pieceRank;
            return !board.containsKey(toPosition);
        }

        // Check for a valid capture move (one square diagonally)
        if (Math.abs(destination.pieceFile.ordinal() - start.pieceFile.ordinal()) == 1) {
            // Diagonal move
            if (Math.abs(destination.pieceRank - start.pieceRank) == 1) {
                // Move is in the correct direction (forward for the pawn's color)
                if (board.containsKey(to)) {
                    // Destination must be occupied to consider capture
                    ReturnPiece targetPiece = board.get(to);
                    // The target piece must be of the opposite color
                    if (targetPiece != null && (start.pieceType.ordinal() / 6 != targetPiece.pieceType.ordinal() / 6)) {
                        return true;
                    }
                }
            }
        }

        // CHECK FOR EN PASSANT

        if (from.charAt(1) == '5' && Chess.checkPieceColor(board.get(from)) == true && Chess.wasLastMoveDouble) { //white en passant

            if (Chess.lastDouble.pieceFile.ordinal() == board.get(from).pieceFile.ordinal() + 1 ||
            Chess.lastDouble.pieceFile.ordinal() == board.get(from).pieceFile.ordinal() - 1) {
                char digitChar = to.charAt(1);
                int digit = digitChar - '0';

                if (board.get(Chess.getPiecePosition(Chess.charToPieceFile(to.charAt(0)), digit-1)) == Chess.lastDouble) {
                    board.remove(Chess.getPiecePosition(Chess.charToPieceFile(to.charAt(0)), digit-1));
                    Chess.pieces.remove(Chess.lastDouble);
                    enPassant = true;
                    return true;
                }

            }
        } else if (from.charAt(1) == '4' && Chess.checkPieceColor(board.get(from)) == false && Chess.wasLastMoveDouble) { //black en passant

            if (Chess.lastDouble.pieceFile.ordinal() == board.get(from).pieceFile.ordinal() + 1 ||
            Chess.lastDouble.pieceFile.ordinal() == board.get(from).pieceFile.ordinal() - 1) {
                char digitChar = to.charAt(1);
                int digit = digitChar - '0';

                if (board.get(Chess.getPiecePosition(Chess.charToPieceFile(to.charAt(0)), digit+1)) == Chess.lastDouble) {
                    board.remove(Chess.getPiecePosition(Chess.charToPieceFile(to.charAt(0)), digit+1));
                    Chess.pieces.remove(Chess.lastDouble);
                    enPassant = true;
                    return true;
                }

            }

        }

            // if everything fails
            return false;
    }

    public void pawnPromotion(String promotionType, ReturnPiece pawn) {
        // see if promotionType is null
        if (promotionType == null) {
            promotionType = "Q";
        }
        switch (promotionType.toUpperCase()) {
            case "Q":
                pawn.pieceType = (pawn.pieceType == ReturnPiece.PieceType.WP) ? ReturnPiece.PieceType.WQ
                        : ReturnPiece.PieceType.BQ;
                break;
            case "R":
                pawn.pieceType = (pawn.pieceType == ReturnPiece.PieceType.WP) ? ReturnPiece.PieceType.WR
                        : ReturnPiece.PieceType.BR;
                break;
            case "B":
                pawn.pieceType = (pawn.pieceType == ReturnPiece.PieceType.WP) ? ReturnPiece.PieceType.WB
                        : ReturnPiece.PieceType.BB;
                break;
            case "N":
                pawn.pieceType = (pawn.pieceType == ReturnPiece.PieceType.WP) ? ReturnPiece.PieceType.WN
                        : ReturnPiece.PieceType.BN;
                break;

        }
    }

    public boolean move(String from, String to) {

        if (!board.containsKey(from)) {
            return false;
        }

        if (!this.validMove(from, to)) {
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

        if (board.get(to).pieceRank == 8 || board.get(to).pieceRank == 1) {
            Chess.pawnPromotion = true;
        }

        if (Math.abs(from.charAt(1) - to.charAt(1)) == 2) {
            Chess.wasLastMoveDouble = true;
            Chess.lastDouble = board.get(to);
            Chess.pieceThatMovedDoubleColor = Chess.checkPieceColor(board.get(to));
        }

        return true;

    }

    public boolean move(String from, String to, String promotionType) {

        if (!board.containsKey(from)) {
            return false;
        }

        if (!this.validMove(from, to)) {
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

        pawnPromotion(promotionType, board.get(to));
        return true;
        // if (!hypotheticalMove(from, to)){
        // return;
        // }

        // Chess.pieces.remove(board.get(from));
        // ReturnPiece pawn= board.remove(from);
        // Chess.pieces.add(pawn);
        // board.put(from, pawn);

        // move(from,to);
    }
}
