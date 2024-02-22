package chess;

public class Queen extends Piece {

    public boolean validMove(int startFile, int startRank, int endFile, int endRank) {

        int fileDiff = Math.abs(startFile - endFile);
        int rankDiff = Math.abs(startRank - endRank);

        ReturnPiece startPiece = board.get(Chess.getPiecePosition(startFile, startRank));
        ReturnPiece endPiece = board.get(Chess.getPiecePosition(endFile, endRank));

        if (startPiece == null) {
            return false;
        }

        if (endPiece != null && startPiece.pieceType.ordinal() / 6 == endPiece.pieceType.ordinal() / 6) { 
            //divide by six for the two different colors
            return false; 
        }


        if (startFile == endFile || startRank == endRank) { //moves like a rook

            int step = Integer.compare(endFile, startFile);
    
            // Rook can move horizontally or vertically
                // Check if there are any pieces blocking the path
                if (startFile == endFile) { // Moving vertically
                    for (int rank = startRank + step; rank != endRank; rank += step) {
                        if (board.containsKey(Chess.getPiecePosition(startFile, rank))) {
                            return false; // Path is blocked
                        }
                    }
                } else { // Moving horizontally
                    for (int file = startFile + step; file != endFile; file += step) {
                        if (board.containsKey(Chess.getPiecePosition(file, startRank))) {
                            return false; // Path is blocked
                        }
                    }
                
                return true; // Valid move
            }
    
            return false; // Invalid move for a Rook
        } else if (fileDiff == rankDiff) {

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

        return false;

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