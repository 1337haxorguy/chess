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
            if (start.pieceRank>destination.pieceRank){
                return false;
            }
        }

        boolean pawnFirstMove= (start.pieceType.toString().startsWith('W') && start.pieceRank ==2) ||
                                (start.pieceType.toString().startsWith('B') && start.pieceRank==7);

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
        if (Math.abs(destination.pieceFile.ordinal() - start.pieceFile.ordinal()) == 1 && (destination.pieceRank - start.pieceRank) == direction) {
            // There must be an opponent's piece at the target space
            String toPosition = destination.pieceFile.toString()+ destination.pieceRank;
            ReturnPiece targetPiece = board.get(toPosition);
            //if the piece is there and not the same color
            if (targetPiece != null && targetPiece.pieceType != start.pieceType) {
                return true;
            }
        }

        //if everything fails
        return false;
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
