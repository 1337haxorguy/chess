package chess;

public class King extends Piece {




    public boolean validMove(String from, String to) {
        System.out.println("running valid move with king " + from + " " + to);

        if (!board.containsKey(from)) {
            System.out.println("cannot find start piece");
            return false;
        }
        
        ReturnPiece start = board.get(from);
        ReturnPiece destination = new ReturnPiece();
        destination.pieceFile = Chess.charToPieceFile(to.charAt(0));
        destination.pieceRank = Integer.parseInt(String.valueOf(to.charAt(1)));



        if (board.containsKey(to) && start.pieceType.ordinal() / 6 == board.get(to).pieceType.ordinal() / 6) { 
            //divide by six for the two different colors
            System.out.println("bruh");

            return false; // Piece in the end position is of the same color
        }

        int differenceInHori = Math.abs(start.pieceFile.ordinal() - destination.pieceFile.ordinal());
        int differenceInVerti = Math.abs(start.pieceRank - destination.pieceRank);

        if (differenceInHori <= 1 && differenceInVerti <= 1) {
            return true;
        }

        return false;


    }

    public boolean move(String from, String to) {


        System.out.println("called move with type king");

        if (!board.containsKey(from)) {
            System.out.println("cannot find start piece");
            return false;
        }

        if (!validMove(from, to)) {
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

        System.out.println(wow.pieceRank + "" + wow.pieceFile);

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

        System.out.println("Moved king to " + wow.pieceFile + wow.pieceRank);
        
        return true;


    }

}