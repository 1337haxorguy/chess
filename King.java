package chess;

public class King extends Piece {




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

        int differenceInHori = Math.abs(start.pieceFile.ordinal() - destination.pieceFile.ordinal());
        int differenceInVerti = Math.abs(start.pieceRank - destination.pieceRank);

        if (differenceInHori <= 1 && differenceInVerti <= 1) {
            return true;
        }

        
        return false;


    }

    public boolean move(String from, String to) {

        if (!validMove(from, to)) {
            System.out.println("Invalid move!");
            return false;
        } 
    
        if (!board.containsKey(from)) {
            return false;
        }
    
        // Check if there's a piece at the destination
        boolean removingAPiece = board.containsKey(to);
        ReturnPiece removedPiece = null;
        if (removingAPiece) {
            // Remove the piece from the board and remember it
            removedPiece = board.remove(to);
            Chess.pieces.remove(removedPiece);
        }
    
        // Remove the piece from the original position
        ReturnPiece movedPiece = board.remove(from);
    
        // Put the moved piece in the new position
        board.put(to, movedPiece);
        movedPiece.pieceRank = Integer.parseInt(String.valueOf(to.charAt(1)));
        movedPiece.pieceFile = Chess.charToPieceFile(to.charAt(0));
    
        // Check if the move puts the king in check
        if (Chess.isInCheck()) {
            // If the move puts the king in check, undo the move
            undoMoveIfCheck(from, to, movedPiece, removedPiece);   

            System.out.println("Move puts the king in check!");
            return false;
        }
    
        return true;
    }
    

}