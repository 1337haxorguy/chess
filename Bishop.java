package chess;

public class Bishop extends Piece {

    public  boolean validMove(int startFile, int startRank, int endFile, int endRank) {

        if (startFile == endFile && startRank == endRank) {
            return false;
        }

        ReturnPiece startPiece = board.get(Chess.getPiecePosition(startFile, startRank));
        ReturnPiece endPiece = board.get(Chess.getPiecePosition(endFile, endRank));

        if (startPiece == null) {
            return false;
        }

        if (endPiece != null && startPiece.pieceType.ordinal() / 6 == endPiece.pieceType.ordinal() / 6) { 
            //divide by six for the two different colors
            return false; 
        }

        int fileDiff = Math.abs(startFile - endFile);
        int rankDiff = Math.abs(startRank - endRank);
        if (fileDiff != rankDiff) {
            return false; 
        }

        int tempStartFile = startFile;
        int tempStartRank = startRank;
        if (startFile < endFile && startRank < endRank) { //moving in first quadrant

            while (tempStartRank < endRank && tempStartFile < endFile) {
                if (board.containsKey(Chess.getPiecePosition(tempStartFile, tempStartRank))) {
                    return false;
                }
                tempStartFile++;
                tempStartRank++;    
            }

        } else if (startFile > endFile && startRank < endRank) { //second quadrant

            while (tempStartRank < endRank && tempStartFile > endFile) {
                if (board.containsKey(Chess.getPiecePosition(tempStartFile, tempStartRank))) {
                    return false;
                }
                tempStartFile--;
                tempStartRank++;
            }


        } else if (startFile > endFile && startRank > endRank) { //third quadrant

            while (tempStartRank > endRank && tempStartFile > endFile) {
                if (board.containsKey(Chess.getPiecePosition(tempStartFile, tempStartRank))) {
                    return false;
                }
                tempStartFile--;
                tempStartRank--;
            }


        } else if (startFile < endFile && startRank > endRank) { //fourth quadrant

            while (tempStartRank > endRank && tempStartFile < endFile) {
                if (board.containsKey(Chess.getPiecePosition(tempStartFile, tempStartRank))) {
                    return false;
                }
                tempStartFile++;
                tempStartRank--;
            }


        }

        return true;
    }

    public boolean move(int startFile, int startRank, int endFile, int endRank) {

        if (this.validMove(startFile, startRank, endFile, endRank) == false) {
            return false;
        } 

        if (!board.containsKey(Chess.getPiecePosition(startFile, startRank))) {
            return false;
        }

        if (board.containsKey(Chess.getPiecePosition(endFile, endRank))) {
            board.remove(Chess.getPiecePosition(endFile, endRank));
        }

        ReturnPiece wow = board.remove(Chess.getPiecePosition(startFile, startRank));
        board.put(Chess.getPiecePosition(endFile, endRank), wow);

        return true;

    }




}