package chess;

import chess.ReturnPiece.PieceFile;

public class Queen extends Piece {

    public boolean validMove(String from, String to) {

        if (!board.containsKey(from)) {
            return false;
        }
        
        ReturnPiece start = board.get(from);
        ReturnPiece destination = new ReturnPiece();
        destination.pieceFile = Chess.charToPieceFile(to.charAt(0));
        destination.pieceRank = Integer.parseInt(String.valueOf(to.charAt(1)));



        if (board.containsKey(to) && start.pieceType.ordinal() / 6 == board.get(to).pieceType.ordinal() / 6) { 
            //divide by six for the two different colors

            return false; // Piece in the end position is of the same color
        }


        if (start.pieceFile == destination.pieceFile || start.pieceRank == destination.pieceRank) {

            // Check if there are any pieces blocking the path
            if (start.pieceFile == destination.pieceFile) { // Moving vertically
                int step = Integer.compare(destination.pieceRank, start.pieceRank);
                for (int rank = start.pieceRank + step; rank != destination.pieceRank; rank += step) {
                    if (board.containsKey(Chess.getPiecePosition(start.pieceFile, rank))) {
                        return false; // Path is blocked
                    }
                }
            } else { // Moving horizontally

                int destinationOrd = destination.pieceFile.ordinal();
                int startOrd = start.pieceFile.ordinal();

                int step = Integer.compare(destinationOrd, startOrd); // Use Integer.compare method
                for (int file = start.pieceFile.ordinal() + step; file != destination.pieceFile.ordinal(); file += step) {
                    if (board.containsKey(Chess.getPiecePosition(PieceFile.values()[file], start.pieceRank))) {
                        return false; // Path is blocked
                    }
                }
            }
                        return true; // Valid move
        } else {
    
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
    
    
    
                while (tempStartRank < destination.pieceRank && tempStartFile < destination.pieceFile.ordinal() ) {
                    if (board.containsKey(Chess.getPiecePosition(tempStartFile, tempStartRank))) {
    
                        return false;
                    }
                    tempStartFile++;
                    tempStartRank++;    
                }
    
            } else if (start.pieceFile.ordinal() > destination.pieceFile.ordinal() && start.pieceRank < destination.pieceRank) { //second quadrant
                tempStartFile--;
                tempStartRank++;

                while (tempStartRank < destination.pieceRank && tempStartFile > destination.pieceFile.ordinal()) {
                    if (board.containsKey(Chess.getPiecePosition(tempStartFile, tempStartRank))) {
                        return false;
                    }
                    tempStartFile--;
                    tempStartRank++;
                }
    
    
            } else if (start.pieceFile.ordinal() > destination.pieceFile.ordinal() && start.pieceRank > destination.pieceRank) { //third quadrant
                tempStartFile--;
                tempStartRank--;
    
                while (tempStartRank > destination.pieceRank && tempStartFile > destination.pieceFile.ordinal()) {
                    if (board.containsKey(Chess.getPiecePosition(tempStartFile, tempStartRank))) {
                        return false;
                    }
                    tempStartFile--;
                    tempStartRank--;
                }
    
    
            } else if (start.pieceFile.ordinal() < destination.pieceFile.ordinal() && start.pieceRank > destination.pieceRank) { //fourth quadrant
                tempStartFile++;
                tempStartRank--;
    
                while (tempStartRank > destination.pieceRank && tempStartFile < destination.pieceFile.ordinal() ) {
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

}