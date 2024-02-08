package chess;

import java.util.ArrayList;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

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
	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {

		ArrayList<ReturnPiece> pieces = new ArrayList<>();

		ReturnPiece WhitePawn1 = new ReturnPiece();
		WhitePawn1.pieceRank = 2;
		WhitePawn1.pieceFile = PieceFile.a;
		WhitePawn1.pieceType = PieceType.WP;
		pieces.add(WhitePawn1);

		ReturnPiece WhitePawn2 = new ReturnPiece();
		WhitePawn2.pieceRank = 2;
		WhitePawn2.pieceFile = PieceFile.b;
		WhitePawn2.pieceType = PieceType.WP;
		pieces.add(WhitePawn2);

		ReturnPiece WhitePawn3 = new ReturnPiece();
		WhitePawn3.pieceRank = 2;
		WhitePawn3.pieceFile = PieceFile.c;
		WhitePawn3.pieceType = PieceType.WP;
		pieces.add(WhitePawn3);

		ReturnPiece WhitePawn4 = new ReturnPiece();
		WhitePawn4.pieceRank = 2;
		WhitePawn4.pieceFile = PieceFile.d;
		WhitePawn4.pieceType = PieceType.WP;
		pieces.add(WhitePawn4);

		ReturnPiece WhitePawn5 = new ReturnPiece();
		WhitePawn5.pieceRank = 2;
		WhitePawn5.pieceFile = PieceFile.e;
		WhitePawn5.pieceType = PieceType.WP;
		pieces.add(WhitePawn5);

		ReturnPiece WhitePawn6 = new ReturnPiece();
		WhitePawn6.pieceRank = 2;
		WhitePawn6.pieceFile = PieceFile.f;
		WhitePawn6.pieceType = PieceType.WP;
		pieces.add(WhitePawn6);
		
		ReturnPiece WhitePawn7 = new ReturnPiece();
		WhitePawn7.pieceRank = 2;
		WhitePawn7.pieceFile = PieceFile.g;
		WhitePawn7.pieceType = PieceType.WP;
		pieces.add(WhitePawn7);

		ReturnPiece WhitePawn8 = new ReturnPiece();
		WhitePawn8.pieceRank = 2;
		WhitePawn8.pieceFile = PieceFile.h;
		WhitePawn8.pieceType = PieceType.WP;
		pieces.add(WhitePawn8);

		//BLACK PAWNS

		ReturnPiece BlackPawn1 = new ReturnPiece();
		BlackPawn1.pieceRank = 7;
		BlackPawn1.pieceFile = PieceFile.a;
		BlackPawn1.pieceType = PieceType.BP;
		pieces.add(BlackPawn1);

		ReturnPiece BlackPawn2 = new ReturnPiece();
		BlackPawn2.pieceRank = 7;
		BlackPawn2.pieceFile = PieceFile.b;
		BlackPawn2.pieceType = PieceType.BP;
		pieces.add(BlackPawn2);

		ReturnPiece BlackPawn3 = new ReturnPiece();
		BlackPawn3.pieceRank = 7;
		BlackPawn3.pieceFile = PieceFile.c;
		BlackPawn3.pieceType = PieceType.BP;
		pieces.add(BlackPawn3);

		ReturnPiece BlackPawn4 = new ReturnPiece();
		BlackPawn4.pieceRank = 7;
		BlackPawn4.pieceFile = PieceFile.d;
		BlackPawn4.pieceType = PieceType.BP;
		pieces.add(BlackPawn4);

		ReturnPiece BlackPawn5 = new ReturnPiece();
		BlackPawn5.pieceRank = 7;
		BlackPawn5.pieceFile = PieceFile.e;
		BlackPawn5.pieceType = PieceType.BP;
		pieces.add(BlackPawn5);

		ReturnPiece BlackPawn6 = new ReturnPiece();
		BlackPawn6.pieceRank = 7;
		BlackPawn6.pieceFile = PieceFile.f;
		BlackPawn6.pieceType = PieceType.BP;
		pieces.add(BlackPawn6);
		
		ReturnPiece BlackPawn7 = new ReturnPiece();
		BlackPawn7.pieceRank = 7;
		BlackPawn7.pieceFile = PieceFile.g;
		BlackPawn7.pieceType = PieceType.BP;
		pieces.add(BlackPawn7);

		ReturnPiece BlackPawn8 = new ReturnPiece();
		BlackPawn8.pieceRank = 7;
		BlackPawn8.pieceFile = PieceFile.h;
		BlackPawn8.pieceType = PieceType.BP;
		pieces.add(BlackPawn8);

		//ROOKS

		ReturnPiece whiteRook1 = new ReturnPiece();
		whiteRook1.pieceRank = 1;
		whiteRook1.pieceFile = PieceFile.a;
		whiteRook1.pieceType = PieceType.WR;
		pieces.add(whiteRook1);

		ReturnPiece whiteRook2 = new ReturnPiece();
		whiteRook2.pieceRank = 1;
		whiteRook2.pieceFile = PieceFile.h;
		whiteRook2.pieceType = PieceType.WR;
		pieces.add(whiteRook2);

		ReturnPiece blackRook1 = new ReturnPiece();
		blackRook1.pieceRank = 8;
		blackRook1.pieceFile = PieceFile.a;
		blackRook1.pieceType = PieceType.BR;
		pieces.add(blackRook1);

		ReturnPiece blackRook2 = new ReturnPiece();
		blackRook2.pieceRank = 8;
		blackRook2.pieceFile = PieceFile.h;
		blackRook2.pieceType = PieceType.BR;
		pieces.add(blackRook2);

		//KNIGHTS

		ReturnPiece whiteKnight1 = new ReturnPiece();
		whiteKnight1.pieceRank = 1;
		whiteKnight1.pieceFile = PieceFile.b;
		whiteKnight1.pieceType = PieceType.WK;
		pieces.add(whiteKnight1);

		ReturnPiece whiteKnight2 = new ReturnPiece();
		whiteKnight2.pieceRank = 1;
		whiteKnight2.pieceFile = PieceFile.g;
		whiteKnight2.pieceType = PieceType.WK;
		pieces.add(whiteKnight2);

		ReturnPiece blackKnight1 = new ReturnPiece();
		blackKnight1.pieceRank = 8;
		blackKnight1.pieceFile = PieceFile.b;
		blackKnight1.pieceType = PieceType.BK;
		pieces.add(blackKnight1);

		ReturnPiece blackKnight2 = new ReturnPiece();
		blackKnight2.pieceRank = 8;
		blackKnight2.pieceFile = PieceFile.g;
		blackKnight2.pieceType = PieceType.BK;
		pieces.add(blackKnight2);

		//BISHOPS

		ReturnPiece whiteBishop1 = new ReturnPiece();
		whiteBishop1.pieceRank = 1;
		whiteBishop1.pieceFile = PieceFile.c;
		whiteBishop1.pieceType = PieceType.WB;
		pieces.add(whiteBishop1);

		ReturnPiece whiteBishop2 = new ReturnPiece();
		whiteBishop2.pieceRank = 1;
		whiteBishop2.pieceFile = PieceFile.f;
		whiteBishop2.pieceType = PieceType.WB;
		pieces.add(whiteBishop2);

		ReturnPiece blackBishop1 = new ReturnPiece();
		blackBishop1.pieceRank = 8;
		blackBishop1.pieceFile = PieceFile.c;
		blackBishop1.pieceType = PieceType.BB;
		pieces.add(blackBishop1);

		ReturnPiece blackBishop2 = new ReturnPiece();
		blackBishop2.pieceRank = 8;
		blackBishop2.pieceFile = PieceFile.f;
		blackBishop2.pieceType = PieceType.BB;
		pieces.add(blackBishop2);

		//QUEENS

		ReturnPiece whiteQueen = new ReturnPiece();
		whiteQueen.pieceRank = 1;
		whiteQueen.pieceFile = PieceFile.d;
		whiteQueen.pieceType = PieceType.WQ;
		pieces.add(whiteQueen);

		ReturnPiece blackQueen = new ReturnPiece();
		blackQueen.pieceRank = 1;
		blackQueen.pieceFile = PieceFile.d;
		blackQueen.pieceType = PieceType.BQ;
		pieces.add(blackQueen);

		//KINGS

		ReturnPiece whiteKing = new ReturnPiece();
		whiteKing.pieceRank = 1;
		whiteKing.pieceFile = PieceFile.e;
		whiteKing.pieceType = PieceType.WK;
		pieces.add(whiteKing);

		ReturnPiece blackKing = new ReturnPiece();
		blackKing.pieceRank = 8;
		blackKing.pieceFile = PieceFile.e;
		blackKing.pieceType = PieceType.BK;
		pieces.add(blackKing);




		/* FILL IN THIS METHOD */
	}
}