package chess;

public class Bishop extends Piece {

    public  boolean validMove(String from, String to) {

        if (!board.containsKey(from)) {
            System.out.println("cannot find start piece");
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

        System.out.println("Moving like a bishop");

        int fileDiff = Math.abs(start.pieceFile.ordinal() - destination.pieceFile.ordinal());
        int rankDiff = Math.abs(start.pieceRank - destination.pieceRank);
        if (fileDiff != rankDiff) {
            System.out.println(start.pieceRank + " " + destination.pieceRank);

            System.out.println(fileDiff + " " + rankDiff);
            System.out.println("diffs are not the same");
            return false; 
        }

        int tempStartFile = start.pieceFile.ordinal();
        int tempStartRank = start.pieceRank;
        if (start.pieceFile.ordinal() < destination.pieceFile.ordinal() && start.pieceRank < destination.pieceRank) { //moving in first quadrant
            System.out.println("First quad movement");
            System.out.println(Chess.getPiecePosition(tempStartFile, tempStartRank));
            tempStartFile++;
            tempStartRank++;    



            while (tempStartRank < destination.pieceRank && tempStartFile < destination.pieceFile.ordinal() + 1) {
                if (board.containsKey(Chess.getPiecePosition(tempStartFile, tempStartRank))) {
                    System.out.println(Chess.getPiecePosition(tempStartFile, tempStartRank));

                    System.out.println("wowee");

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