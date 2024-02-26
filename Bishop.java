package chess;

public class Bishop extends Piece {

    public  boolean validMove(String from, String to) {

        if (!board.containsKey(from)) {
            return false;
        }
        
        ReturnPiece start = board.get(from);
        ReturnPiece destination = new ReturnPiece();
        destination.pieceFile = Chess.charToPieceFile(to.charAt(0));
        destination.pieceRank = Integer.parseInt(String.valueOf(to.charAt(1)));


        if (start.pieceFile == destination.pieceFile && start.pieceRank == destination.pieceRank) {
            return false;
        }


        if (board.containsKey(to) && start.pieceType.ordinal() / 6 == board.get(to).pieceType.ordinal() / 6) { 
            //divide by six for the two different colors
            return false; 
        }


        int fileDiff = Math.abs(start.pieceFile.ordinal() - destination.pieceFile.ordinal());
        int rankDiff = Math.abs(start.pieceRank - destination.pieceRank);
        if (fileDiff != rankDiff) {
            return false; 
        }

        int tempStartFile = start.pieceFile.ordinal();
        int tempStartRank = start.pieceRank;
        if (start.pieceFile.ordinal() < destination.pieceFile.ordinal() && start.pieceRank < destination.pieceRank) { //moving in first quadrant
            tempStartFile++;
            tempStartRank++;    



            while (tempStartRank < destination.pieceRank && tempStartFile < destination.pieceFile.ordinal() + 1) {
                if (board.containsKey(Chess.getPiecePosition(tempStartFile, tempStartRank))) {

                    return false;
                }
                tempStartFile++;
                tempStartRank++;    
            }

        } else if (start.pieceFile.ordinal() > destination.pieceFile.ordinal() && start.pieceRank < destination.pieceRank) { //second quadrant
            tempStartFile--;
            tempStartRank++;

            while (tempStartRank < destination.pieceRank && tempStartFile > destination.pieceFile.ordinal() + 1) {
                if (board.containsKey(Chess.getPiecePosition(tempStartFile, tempStartRank))) {
                    return false;
                }
                tempStartFile--;
                tempStartRank++;
            }


        } else if (start.pieceFile.ordinal() > destination.pieceFile.ordinal() && start.pieceRank > destination.pieceRank) { //third quadrant
            tempStartFile--;
            tempStartRank--;

            while (tempStartRank > destination.pieceRank && tempStartFile > destination.pieceFile.ordinal() + 1) {
                if (board.containsKey(Chess.getPiecePosition(tempStartFile, tempStartRank))) {
                    return false;
                }
                tempStartFile--;
                tempStartRank--;
            }


        } else if (start.pieceFile.ordinal() < destination.pieceFile.ordinal() && start.pieceRank > destination.pieceRank) { //fourth quadrant
            tempStartFile++;
            tempStartRank--;

            while (tempStartRank > destination.pieceRank && tempStartFile < destination.pieceFile.ordinal() + 1) {
                if (board.containsKey(Chess.getPiecePosition(tempStartFile, tempStartRank))) {
                    return false;
                }
                tempStartFile++;
                tempStartRank--;
            }


        }

        return true;
    }


}