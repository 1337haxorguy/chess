package chess;

import java.util.Map;

import chess.Chess;
import chess.ReturnPiece;

public class Rook implements Piece {

    public boolean validMove(int startFile, int startRank, int endFile, int endRank) {

        Map<String, ReturnPiece> board = Chess.board; // Accessing the board from Chess class
        int step = Integer.compare(endFile, startFile);



        ReturnPiece startPiece = board.get(Chess.getPiecePosition(startFile, startRank));
        ReturnPiece endPiece = board.get(Chess.getPiecePosition(endFile, endRank));

        if (endPiece != null && startPiece.pieceType.ordinal() / 6 == endPiece.pieceType.ordinal() / 6) { 
            //divide by six for the two different colors
            return false; // Piece in the end position is of the same color
        }

        // Rook can move horizontally or vertically
        if (startFile == endFile || startRank == endRank) {
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
            }
            return true; // Valid move
        }

        return false; // Invalid move for a Rook
    }

    public boolean move(int startFile, int startRank, int endFile, int endRank) {

        Map<String, ReturnPiece> board = Chess.board; // Accessing the board from Chess class


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
