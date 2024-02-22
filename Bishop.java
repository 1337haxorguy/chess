package chess;

public class Bishop extends Piece {

    public  boolean validMove(int startFile, int startRank, int endFile, int endRank) {

        if (startFile == endFile && startRank == endRank) {
            return false; // Bishop cannot stay in the same position
        }
        
        int step = Integer.compare(endFile, startFile);
        ReturnPiece startPiece = board.get(Chess.getPiecePosition(startFile, startRank));
        ReturnPiece endPiece = board.get(Chess.getPiecePosition(endFile, endRank));

        if (startPiece == null) {
            return false;
        }

        if (endPiece != null && startPiece.pieceType.ordinal() / 6 == endPiece.pieceType.ordinal() / 6) { 
            //divide by six for the two different colors
            return false; // Piece in the end position is of the same color
        }

        int fileDiff = Math.abs(endFile - startFile);
        int rankDiff = Math.abs(endRank - startRank);
        if (fileDiff != rankDiff) {
            return false; // Bishop can only move diagonally
        }


        for (int i = startRank)







        return true;
    }

    public boolean move(int startFile, int startRank, int endFile, int endRank) {

        return false;
    }




}