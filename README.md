# Chess Recreation in Terminal

## Overview
This project is a **Chess** game recreation that runs in the terminal. It allows players to input chess moves in algebraic notation and supports common gameplay mechanics such as **check**, **checkmate**, **draw**, and **resign**. The game logic is implemented in Java, and the main gameplay function is handled through the `play(String move)` method.

## Features
- **Full Chess Game Simulation**: The game can simulate an entire chess match, including all major mechanics like **check**, **checkmate**, **resign**, and **draw**.
- **Move Validation**: The game validates each move to ensure it follows the rules of chess (e.g., legal moves for each piece, turn-based play).
- **Pawn Promotion**: The game includes logic for promoting pawns when they reach the opposite side of the board.
- **Game State Reset**: You can reset the game at any time by typing `reset`.
- **Turn-Based Gameplay**: Players alternate turns, with the game maintaining internal state tracking.
  
## How to Play
1. The game accepts chess moves in the form of **algebraic notation**. For example:  
   - `e2 e4`: Move a piece from square `e2` to `e4`.
   - `resign`: Resign the match.
   - `reset`: Reset the board and start a new game.
   - `e7 e8 Q`: Promote a pawn on `e7` to a Queen when it reaches `e8`.
   - `draw?`: Propose a draw.

2. The game handles both **check** and **checkmate** conditions. If a player’s king is in check, the player must make a move to resolve the check. If checkmate occurs, the game will declare the winner.

3. **Example Commands**:
   - `e2 e4`: Move a piece from `e2` to `e4`.
   - `resign`: Resign the game.
   - `reset`: Reset the game and start over.

4. The game returns different messages based on the game state, such as:
   - **ILLEGAL_MOVE**: When a player makes an invalid move.
   - **CHECK**: When a player's king is in check.
   - **CHECKMATE_BLACK_WINS**: Black wins by checkmate.
   - **CHECKMATE_WHITE_WINS**: White wins by checkmate.
   - **DRAW**: The game ends in a draw.

## Game Flow
The `play(String move)` function governs the flow of the game:
- Parses the move input (e.g., `e2 e4`).
- Validates if the move is legal based on the piece and current game state.
- Handles special moves like **pawn promotion**, **resign**, and **draw** proposals.
- Alternates turns between players.
- Detects game-ending conditions such as **checkmate**, **resign**, and **stalemate**.

## Code Structure
- **Board Setup**: The game initializes with a standard chess board setup. The state of the game (pieces and their positions) is stored in a collection called `pieces` and the board.
- **Game Logic**: The `play(String move)` method handles most of the game logic, including resetting the board, moving pieces, and handling special rules (e.g., king and rook movement for castling).
- **Error Handling**: The game will return `Message.ILLEGAL_MOVE` if a move is invalid, allowing for quick feedback and correction.
