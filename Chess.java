package chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

	//this hashmap will store the specified piece and location
	static Map<String, ReturnPiece> board = new HashMap<>();

	//this will organize the pieceFile and rank into one string
	private static String getPiecePosition(PieceFile letter, int rank){
		return "" + letter.toString() + rank;
	}

	static ArrayList<ReturnPiece> pieces = new ArrayList<>();


	static boolean isSpaceEmpty(PieceFile pieceFile, int pieceRank) {
        for (int i = 0; i < pieces.size(); i++) {

            if (pieces.get(i).pieceFile == pieceFile && pieces.get(i).pieceRank == pieceRank) {
                return false;
            }

        }

        return true;
        
    }

	
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


		pieces.clear();
		board.clear();

		//KINGS

		ReturnPiece whiteKing = new ReturnPiece();
		whiteKing.pieceRank = 1;
		whiteKing.pieceFile = PieceFile.e;
		whiteKing.pieceType = PieceType.WK;
		pieces.add(whiteKing);
		board.put(getPiecePosition(whiteKing.pieceFile,whiteKing.pieceRank), whiteKing);

		ReturnPiece blackKing = new ReturnPiece();
		blackKing.pieceRank = 8;
		blackKing.pieceFile = PieceFile.e;
		blackKing.pieceType = PieceType.BK;
		pieces.add(blackKing);
		board.put(getPiecePosition(blackKing.pieceFile,blackKing.pieceRank), blackKing);

		//WHITE PAWNS

		ReturnPiece WhitePawn1 = new ReturnPiece();
		WhitePawn1.pieceRank = 2;
		WhitePawn1.pieceFile = PieceFile.a;
		WhitePawn1.pieceType = PieceType.WP;
		pieces.add(WhitePawn1);
		board.put(getPiecePosition(WhitePawn1.pieceFile,WhitePawn1.pieceRank), WhitePawn1);

		ReturnPiece WhitePawn2 = new ReturnPiece();
		WhitePawn2.pieceRank = 2;
		WhitePawn2.pieceFile = PieceFile.b;
		WhitePawn2.pieceType = PieceType.WP;
		pieces.add(WhitePawn2);
		board.put(getPiecePosition(WhitePawn2.pieceFile,WhitePawn2.pieceRank), WhitePawn2);

		ReturnPiece WhitePawn3 = new ReturnPiece();
		WhitePawn3.pieceRank = 2;
		WhitePawn3.pieceFile = PieceFile.c;
		WhitePawn3.pieceType = PieceType.WP;
		pieces.add(WhitePawn3);
		board.put(getPiecePosition(WhitePawn3.pieceFile,WhitePawn3.pieceRank), WhitePawn3);

		ReturnPiece WhitePawn4 = new ReturnPiece();
		WhitePawn4.pieceRank = 2;
		WhitePawn4.pieceFile = PieceFile.d;
		WhitePawn4.pieceType = PieceType.WP;
		pieces.add(WhitePawn4);
		board.put(getPiecePosition(WhitePawn4.pieceFile,WhitePawn4.pieceRank), WhitePawn4);

		ReturnPiece WhitePawn5 = new ReturnPiece();
		WhitePawn5.pieceRank = 2;
		WhitePawn5.pieceFile = PieceFile.e;
		WhitePawn5.pieceType = PieceType.WP;
		pieces.add(WhitePawn5);
		board.put(getPiecePosition(WhitePawn5.pieceFile,WhitePawn5.pieceRank), WhitePawn5);

		ReturnPiece WhitePawn6 = new ReturnPiece();
		WhitePawn6.pieceRank = 2;
		WhitePawn6.pieceFile = PieceFile.f;
		WhitePawn6.pieceType = PieceType.WP;
		pieces.add(WhitePawn6);
		board.put(getPiecePosition(WhitePawn6.pieceFile,WhitePawn6.pieceRank), WhitePawn6);
		
		ReturnPiece WhitePawn7 = new ReturnPiece();
		WhitePawn7.pieceRank = 2;
		WhitePawn7.pieceFile = PieceFile.g;
		WhitePawn7.pieceType = PieceType.WP;
		pieces.add(WhitePawn7);
		board.put(getPiecePosition(WhitePawn7.pieceFile,WhitePawn7.pieceRank), WhitePawn7);

		ReturnPiece WhitePawn8 = new ReturnPiece();
		WhitePawn8.pieceRank = 2;
		WhitePawn8.pieceFile = PieceFile.h;
		WhitePawn8.pieceType = PieceType.WP;
		pieces.add(WhitePawn8);
		board.put(getPiecePosition(WhitePawn8.pieceFile,WhitePawn8.pieceRank), WhitePawn8);

		//BLACK PAWNS

		ReturnPiece BlackPawn1 = new ReturnPiece();
		BlackPawn1.pieceRank = 7;
		BlackPawn1.pieceFile = PieceFile.a;
		BlackPawn1.pieceType = PieceType.BP;
		pieces.add(BlackPawn1);
		board.put(getPiecePosition(BlackPawn1.pieceFile,BlackPawn1.pieceRank), BlackPawn1);

		ReturnPiece BlackPawn2 = new ReturnPiece();
		BlackPawn2.pieceRank = 7;
		BlackPawn2.pieceFile = PieceFile.b;
		BlackPawn2.pieceType = PieceType.BP;
		pieces.add(BlackPawn2);
		board.put(getPiecePosition(BlackPawn2.pieceFile,BlackPawn2.pieceRank), BlackPawn2);

		ReturnPiece BlackPawn3 = new ReturnPiece();
		BlackPawn3.pieceRank = 7;
		BlackPawn3.pieceFile = PieceFile.c;
		BlackPawn3.pieceType = PieceType.BP;
		pieces.add(BlackPawn3);
		board.put(getPiecePosition(BlackPawn3.pieceFile,BlackPawn3.pieceRank), BlackPawn3);

		ReturnPiece BlackPawn4 = new ReturnPiece();
		BlackPawn4.pieceRank = 7;
		BlackPawn4.pieceFile = PieceFile.d;
		BlackPawn4.pieceType = PieceType.BP;
		pieces.add(BlackPawn4);
		board.put(getPiecePosition(BlackPawn4.pieceFile,BlackPawn4.pieceRank), BlackPawn4);

		ReturnPiece BlackPawn5 = new ReturnPiece();
		BlackPawn5.pieceRank = 7;
		BlackPawn5.pieceFile = PieceFile.e;
		BlackPawn5.pieceType = PieceType.BP;
		pieces.add(BlackPawn5);
		board.put(getPiecePosition(BlackPawn5.pieceFile,BlackPawn5.pieceRank), BlackPawn5);

		ReturnPiece BlackPawn6 = new ReturnPiece();
		BlackPawn6.pieceRank = 7;
		BlackPawn6.pieceFile = PieceFile.f;
		BlackPawn6.pieceType = PieceType.BP;
		pieces.add(BlackPawn6);
		board.put(getPiecePosition(BlackPawn6.pieceFile,BlackPawn6.pieceRank), BlackPawn6);
		
		ReturnPiece BlackPawn7 = new ReturnPiece();
		BlackPawn7.pieceRank = 7;
		BlackPawn7.pieceFile = PieceFile.g;
		BlackPawn7.pieceType = PieceType.BP;
		pieces.add(BlackPawn7);
		board.put(getPiecePosition(BlackPawn7.pieceFile,BlackPawn7.pieceRank), BlackPawn7);

		ReturnPiece BlackPawn8 = new ReturnPiece();
		BlackPawn8.pieceRank = 7;
		BlackPawn8.pieceFile = PieceFile.h;
		BlackPawn8.pieceType = PieceType.BP;
		pieces.add(BlackPawn8);
		board.put(getPiecePosition(BlackPawn8.pieceFile,BlackPawn8.pieceRank), BlackPawn8);

		//ROOKS

		ReturnPiece whiteRook1 = new ReturnPiece();
		whiteRook1.pieceRank = 1;
		whiteRook1.pieceFile = PieceFile.a;
		whiteRook1.pieceType = PieceType.WR;
		pieces.add(whiteRook1);
		board.put(getPiecePosition(whiteRook1.pieceFile,whiteRook1.pieceRank), whiteRook1);

		ReturnPiece whiteRook2 = new ReturnPiece();
		whiteRook2.pieceRank = 1;
		whiteRook2.pieceFile = PieceFile.h;
		whiteRook2.pieceType = PieceType.WR;
		pieces.add(whiteRook2);
		board.put(getPiecePosition(whiteRook2.pieceFile,whiteRook2.pieceRank), whiteRook2);

		ReturnPiece blackRook1 = new ReturnPiece();
		blackRook1.pieceRank = 8;
		blackRook1.pieceFile = PieceFile.a;
		blackRook1.pieceType = PieceType.BR;
		pieces.add(blackRook1);
		board.put(getPiecePosition(blackRook1.pieceFile,blackRook1.pieceRank), blackRook1);

		ReturnPiece blackRook2 = new ReturnPiece();
		blackRook2.pieceRank = 8;
		blackRook2.pieceFile = PieceFile.h;
		blackRook2.pieceType = PieceType.BR;
		pieces.add(blackRook2);
		board.put(getPiecePosition(blackRook2.pieceFile,blackRook2.pieceRank), blackRook2);

		//KNIGHTS

		ReturnPiece whiteKnight1 = new ReturnPiece();
		whiteKnight1.pieceRank = 1;
		whiteKnight1.pieceFile = PieceFile.b;
		whiteKnight1.pieceType = PieceType.WK;
		pieces.add(whiteKnight1);
		board.put(getPiecePosition(whiteKnight1.pieceFile,whiteKnight1.pieceRank), whiteKnight1);

		ReturnPiece whiteKnight2 = new ReturnPiece();
		whiteKnight2.pieceRank = 1;
		whiteKnight2.pieceFile = PieceFile.g;
		whiteKnight2.pieceType = PieceType.WK;
		pieces.add(whiteKnight2);
		board.put(getPiecePosition(whiteKnight2.pieceFile,whiteKnight2.pieceRank), whiteKnight2);

		ReturnPiece blackKnight1 = new ReturnPiece();
		blackKnight1.pieceRank = 8;
		blackKnight1.pieceFile = PieceFile.b;
		blackKnight1.pieceType = PieceType.BK;
		pieces.add(blackKnight1);
		board.put(getPiecePosition(blackKnight1.pieceFile,blackKnight1.pieceRank), blackKnight1);

		ReturnPiece blackKnight2 = new ReturnPiece();
		blackKnight2.pieceRank = 8;
		blackKnight2.pieceFile = PieceFile.g;
		blackKnight2.pieceType = PieceType.BK;
		pieces.add(blackKnight2);
		board.put(getPiecePosition(blackKnight2.pieceFile,blackKnight2.pieceRank), blackKnight2);

		//BISHOPS

		ReturnPiece whiteBishop1 = new ReturnPiece();
		whiteBishop1.pieceRank = 1;
		whiteBishop1.pieceFile = PieceFile.c;
		whiteBishop1.pieceType = PieceType.WB;
		pieces.add(whiteBishop1);
		board.put(getPiecePosition(whiteBishop1.pieceFile,whiteBishop1.pieceRank), whiteBishop1);

		ReturnPiece whiteBishop2 = new ReturnPiece();
		whiteBishop2.pieceRank = 1;
		whiteBishop2.pieceFile = PieceFile.f;
		whiteBishop2.pieceType = PieceType.WB;
		pieces.add(whiteBishop2);
		board.put(getPiecePosition(whiteBishop2.pieceFile,whiteBishop2.pieceRank), whiteBishop2);

		ReturnPiece blackBishop1 = new ReturnPiece();
		blackBishop1.pieceRank = 8;
		blackBishop1.pieceFile = PieceFile.c;
		blackBishop1.pieceType = PieceType.BB;
		pieces.add(blackBishop1);
		board.put(getPiecePosition(blackBishop1.pieceFile,blackBishop1.pieceRank), blackBishop1);

		ReturnPiece blackBishop2 = new ReturnPiece();
		blackBishop2.pieceRank = 8;
		blackBishop2.pieceFile = PieceFile.f;
		blackBishop2.pieceType = PieceType.BB;
		pieces.add(blackBishop2);
		board.put(getPiecePosition(blackBishop2.pieceFile,blackBishop2.pieceRank), blackBishop2);

		//QUEENS

		ReturnPiece whiteQueen = new ReturnPiece();
		whiteQueen.pieceRank = 1;
		whiteQueen.pieceFile = PieceFile.d;
		whiteQueen.pieceType = PieceType.WQ;
		pieces.add(whiteQueen);
		board.put(getPiecePosition(whiteQueen.pieceFile,whiteQueen.pieceRank), whiteQueen);

		ReturnPiece blackQueen = new ReturnPiece();
		blackQueen.pieceRank = 8;
		blackQueen.pieceFile = PieceFile.d;
		blackQueen.pieceType = PieceType.BQ;
		pieces.add(blackQueen);
		board.put(getPiecePosition(blackQueen.pieceFile,blackQueen.pieceRank), blackQueen);


		System.out.println(getPiecePosition(blackQueen.pieceFile,blackQueen.pieceRank));


		/* FILL IN THIS METHOD */
	}
}