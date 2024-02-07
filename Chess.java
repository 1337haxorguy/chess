package chess;

import java.util.ArrayList;

class ReturnPiece {
	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
		            BP, BR, BN, BB, BK, BQ};
	static enum PieceFile {a, b, c, d, e, f, g, h};
	
	PieceType pieceType;
	PieceFile pieceFile;
	int pieceRank;  // 1..8
	public String toString() {
		return ""+pieceFile+pieceRank+":"+pieceType;
	}
	public boolean equals(Object other) {
		if (other == null || !(other instanceof ReturnPiece)) {
			return false;
		}
		ReturnPiece otherPiece = (ReturnPiece)other;
		return pieceType == otherPiece.pieceType &&
				pieceFile == otherPiece.pieceFile &&
				pieceRank == otherPiece.pieceRank;
	}
}

class ReturnPlay {
	enum Message {ILLEGAL_MOVE, DRAW, 
				  RESIGN_BLACK_WINS, RESIGN_WHITE_WINS, 
				  CHECK, CHECKMATE_BLACK_WINS,	CHECKMATE_WHITE_WINS, 
				  STALEMATE};
	
	ArrayList<ReturnPiece> piecesOnBoard;
	Message message;
}



public class Chess {
	
	enum Player { white, black }
	
	/**
	 * Plays the next move for whichever player has the turn.
	 * 
	 * @param move String for next move, e.g. "a2 a3"
	 * 
	 * @return A ReturnPlay instance that contains the result of the move.
	 *         See the section "The Chess class" in the assignment description for details of
	 *         the contents of the returned ReturnPlay instance.
	 */
	public static ReturnPlay play(String move) {

		/* FILL IN THIS METHOD */
		
		/* FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY */
		/* WHEN YOU FILL IN THIS METHOD, YOU NEED TO RETURN A ReturnPlay OBJECT */
		return null;
	}

	static Piece[][] gameBoard = new Piece[8][8];

	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {

		pieces = new ArrayList<>();


		ReturnPiece WhitePawn1 = new ReturnPiece();
		temp.pieceRank = 2;
		temp.pieceFile = pieceFile.a;
		temp.pieceType = pieceType.WP;
		pieces.add(WhitePawn1);

		ReturnPiece WhitePawn2 = new ReturnPiece();
		temp.pieceRank = 2;
		temp.pieceFile = pieceFile.b;
		temp.pieceType = pieceType.WP;
		pieces.add(WhitePawn1);

		ReturnPiece WhitePawn3 = new ReturnPiece();
		temp.pieceRank = 2;
		temp.pieceFile = pieceFile.c;
		temp.pieceType = pieceType.WP;
		pieces.add(WhitePawn1);

		ReturnPiece WhitePawn4 = new ReturnPiece();
		temp.pieceRank = 2;
		temp.pieceFile = pieceFile.d;
		temp.pieceType = pieceType.WP;
		pieces.add(WhitePawn1);

		ReturnPiece WhitePawn5 = new ReturnPiece();
		temp.pieceRank = 2;
		temp.pieceFile = pieceFile.e;
		temp.pieceType = pieceType.WP;
		pieces.add(WhitePawn1);

		ReturnPiece WhitePawn6 = new ReturnPiece();
		temp.pieceRank = 2;
		temp.pieceFile = pieceFile.d;
		temp.pieceType = pieceType.WP;
		pieces.add(WhitePawn1);
		
		ReturnPiece WhitePawn7 = new ReturnPiece();
		temp.pieceRank = 2;
		temp.pieceFile = pieceFile.e;
		temp.pieceType = pieceType.WP;
		pieces.add(WhitePawn1);

		ReturnPiece WhitePawn8 = new ReturnPiece();
		temp.pieceRank = 2;
		temp.pieceFile = pieceFile.f;
		temp.pieceType = pieceType.WP;
		pieces.add(WhitePawn1);

		


		ReturnPiece whiteRook = new ReturnPiece();
		whiteRook.pieceFile = 1;
		whiteRook.pieceRank = 'a';

		/* FILL IN THIS METHOD */
	}
}
