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

        if (startFile - endFile < 0 && startRank - endRank < 0) { //moving in first quadrant

        }

        if (startFile - endFile > 0 && startRank - endRank < 0) { //second quadrant

        }

        if (startFile - endFile > 0 && startRank - endRank > 0) { //third quadrant

        }

        if (startFile - endFile < 0 && startRank - endRank > 0) { //second quadrant

        }

        











        return true;
    }

    public boolean move(int startFile, int startRank, int endFile, int endRank) {

        return false;
    }




}