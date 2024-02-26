package chess;



public class Pawn extends Piece {

    public boolean validMove(String from, String to) {

        //Checks if no pawn in from location is there
        if (!board.containsKey((from))){

            return false;
        }
        //grabbing current location
        ReturnPiece start = board.get(from);

        //grabbing next location 
        ReturnPiece destination= new ReturnPiece();
        destination.pieceFile = Chess.charToPieceFile(to.charAt(0));
        destination.pieceRank = Integer.parseInt(String.valueOf(to.charAt(1)));


        //checking if theres already a piece in the destination location and checking if they the same color
        //returns false if same color
        if (board.containsKey(to)){
            if (start.pieceType.ordinal()/6 == board.get(to).pieceType.ordinal()/6){
                return false;
            }
        }


        //if pieceType oridnal is 0-5, it is white
        //if pieceType ordinal is 6-11, it is black
        int direction = 0;
        if (start.pieceType.ordinal()<6){
            direction= 1; //direction will move forward
            if (destination.pieceRank<start.pieceRank){
                return false;
            }
        }
        else{
            direction = -1;
            if (destination.pieceRank>start.pieceRank){
                System.out.println("yes");
                return false;
            }
        }

        boolean pawnFirstMove= (start.pieceType.toString().charAt(0) == 'W' && start.pieceRank ==2) ||
                                (start.pieceType.toString().charAt(0) == 'B' && start.pieceRank==7);

        if (pawnFirstMove && Math.abs(destination.pieceRank- start.pieceRank)==2){
            String oneSquarePosition= Chess.getPiecePosition(start.pieceFile, start.pieceRank + direction);
            String twoSquarePosition= Chess.getPiecePosition(start.pieceFile, start.pieceRank + direction);

            if (!board.containsKey(oneSquarePosition)&& !board.containsKey(twoSquarePosition)){
                //this will mean that the pawn has two clear squares in front of it
                return true;
            }
            else{
                //there is a piece blocking the pawn from going two squares foward
                return false;
            }
        }

    
        // Check for a valid one-square forward move
        if (destination.pieceFile.ordinal() == start.pieceFile.ordinal() && ((destination.pieceRank - start.pieceRank) == direction)) {
            // The target space must be empty
            String toPosition = destination.pieceFile.toString() + destination.pieceRank;
            return !board.containsKey(toPosition);
        }

        // Check for a valid capture move (one square diagonally)
        if (Math.abs(destination.pieceFile.ordinal() - start.pieceFile.ordinal()) == 1) {
        // Diagonal move
            if ((destination.pieceRank - start.pieceRank) == direction) {
                // Move is in the correct direction (forward for the pawn's color)
                String toPosition = destination.pieceFile.toString() + destination.pieceRank;
                if (board.containsKey(toPosition)) {
                    // Destination must be occupied to consider capture
                    ReturnPiece targetPiece = board.get(toPosition);
                    // The target piece must be of the opposite color
                    if (targetPiece != null && (start.pieceType.ordinal() / 6 != targetPiece.pieceType.ordinal() / 6)) {
                        return true;
                    }
                }
                return false; // Diagonal move without capture is invalid
            }
        }


        //if everything fails
        return false;
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

    public boolean move(String from, String to) {


        if (!board.containsKey(from)) {
            System.out.println("cannot find start piece");
            return false;
        }

        if (!this.validMove(from, to)) {
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

        
        return true;


    }
}
