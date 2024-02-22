package chess;



public class Pawn extends Piece {

    public boolean validMove(String from, String to) {

        if (!board.containsKey((from))){
            System.out.println("L");
            return false;
        }
        //grabbing current location
        ReturnPiece start = board.get(from);

        //grabbing next location 
        ReturnPiece destination= new ReturnPiece();
        destination.pieceFile = Chess.charToPieceFile(to.charAt(0));
        destination.pieceRank = Integer.parseInt(String.valueOf(to.charAt(1)));


        //if pieceType oridnal is 0-5, it is white
        //if pieceType ordinal is 6-11, it is black
        int direction = 0;
        if (start.pieceType.ordinal()<6){
            direction= 1; //direction will move forward
        }
        else{
            direction = -1;
        }

        //check if it their first move
        // if (start.pieceRank == 2){
        //     //they are are to move one or two spaces up
        //     // if (Math.abs(destination.pieceFile.ordinal()-start.pieceFile.ordinal()) )
        // }

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
                System.out.println("YES");
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
            System.out.println("HEllo");
            System.out.println(board.get(to).toString());
            board.remove(to);
        }

        ReturnPiece wow = board.remove(from);
        board.put(to, wow);
        wow.pieceRank = Integer.parseInt(String.valueOf(to.charAt(1)));
        wow.pieceFile = Chess.charToPieceFile(to.charAt(0));
        
        

        return true;


    }
}
