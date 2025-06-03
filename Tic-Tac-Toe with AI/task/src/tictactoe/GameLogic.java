package tictactoe;

// We will also need to import AI-related classes/methods or define them here later.
// For now, just the basic structure.

public class GameLogic {

    public static final char PLAYER_X = 'X';
    public static final char PLAYER_O = 'O';
    public static final char EMPTY_CELL = ' ';
    private static final int BOARD_SIZE = 3;

    private char[][] board;
    private char currentPlayerMark;
    private boolean gameActive;
    private PlayerType playerXType;
    private PlayerType playerOType;
    // We'll add player types (Human, AI levels) and AI logic integration later.

    // Inner class for Minimax to store row and col of a move
    private static class MinimaxMove {
        int row, col;
        // Constructor can be added if needed, or direct assignment
        MinimaxMove() { row = -1; col = -1; } // Default invalid move
        MinimaxMove(int r, int c) { this.row = r; this.col = c; }
    }

    public GameLogic() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        // Default to Human vs Human if not specified otherwise
        resetGame(PlayerType.HUMAN, PlayerType.HUMAN);
    }

    public void resetGame(PlayerType p1Type, PlayerType p2Type) {
        System.out.println("LOGIC.resetGame: Received p1Type=" + p1Type + ", p2Type=" + p2Type);
        this.playerXType = p1Type;
        this.playerOType = p2Type;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
        currentPlayerMark = PLAYER_X; 
        gameActive = true;
        // Note: Initial AI move for Player X will be handled by TicTacToeGUI calling performAIMove after reset
        System.out.println("LOGIC.resetGame: Stored this.playerXType=" + this.playerXType + ", this.playerOType=" + this.playerOType);
    }

    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayerMark() {
        return currentPlayerMark;
    }

    public boolean isGameActive() {
        return gameActive;
    }

    public boolean isCellEmpty(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return false; // Out of bounds
        }
        return board[row][col] == EMPTY_CELL;
    }

    public boolean makeMove(int row, int col) {
        System.out.println("LOGIC.makeMove: Attempting move for " + currentPlayerMark + " at (" + row + "," + col + "). gameActive=" + gameActive + ", cellEmpty=" + (board[row][col] == EMPTY_CELL));
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE || board[row][col] != EMPTY_CELL || !gameActive) {
            System.out.println("LOGIC.makeMove: Invalid move conditions. Returning false.");
            return false; // Invalid move
        }
        // Ensure it's a human making a direct move or AI is calling it internally after choosing a move.
        // If called directly for an AI player without pre-selecting a move, it's a misuse.
        // However, performAIMove will call this correctly.

        board[row][col] = currentPlayerMark;
        char movedPlayer = currentPlayerMark; // Store who made the move

        if (checkWin(row, col)) {
            gameActive = false;
            System.out.println("LOGIC.makeMove: Player " + movedPlayer + " wins. gameActive=false.");
        } else if (isBoardFull()) {
            gameActive = false;
            System.out.println("LOGIC.makeMove: Board is full. It's a draw. gameActive=false.");
        } else {
            System.out.println("LOGIC.makeMove: Move valid, no win/draw. Switching player from " + movedPlayer);
            switchPlayer();
            System.out.println("LOGIC.makeMove: Player switched. New currentPlayerMark = " + currentPlayerMark);
        }
        return true;
    }

    private void switchPlayer() {
        currentPlayerMark = (currentPlayerMark == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }

    public boolean checkWin(int lastMoveRow, int lastMoveCol) {
        // Check row
        if (board[lastMoveRow][0] == currentPlayerMark && board[lastMoveRow][1] == currentPlayerMark && board[lastMoveRow][2] == currentPlayerMark) {
            return true;
        }
        // Check column
        if (board[0][lastMoveCol] == currentPlayerMark && board[1][lastMoveCol] == currentPlayerMark && board[2][lastMoveCol] == currentPlayerMark) {
            return true;
        }
        // Check diagonals
        if ((lastMoveRow == lastMoveCol) && (board[0][0] == currentPlayerMark && board[1][1] == currentPlayerMark && board[2][2] == currentPlayerMark)) {
            return true;
        }
        if ((lastMoveRow + lastMoveCol == 2) && (board[0][2] == currentPlayerMark && board[1][1] == currentPlayerMark && board[2][0] == currentPlayerMark)) {
            return true;
        }
        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    return false; // Found an empty cell
                }
            }
        }
        return true; // No empty cells
    }

    // Placeholder for game status string for the GUI
    public String getGameStatusMessage() {
        if (!gameActive) {
            // Check again who won because checkWin was called with the player who just moved
            if (checkWinForPlayer(PLAYER_X)) return "Player X (" + playerXType + ") wins!";
            if (checkWinForPlayer(PLAYER_O)) return "Player O (" + playerOType + ") wins!";
            if (isBoardFull()) return "It's a draw!";
        }
        return "Player " + currentPlayerMark + " (" + getCurrentPlayerType() + ")'s turn.";
    }

    // Helper for status message if game ended on win
    // Made public for GUI to check draw status reliably
    public boolean checkWinForPlayer(char playerMark) {
        // Check rows
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][0] == playerMark && board[i][1] == playerMark && board[i][2] == playerMark) return true;
        }
        // Check columns
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (board[0][j] == playerMark && board[1][j] == playerMark && board[2][j] == playerMark) return true;
        }
        // Check diagonals
        if (board[0][0] == playerMark && board[1][1] == playerMark && board[2][2] == playerMark) return true;
        if (board[0][2] == playerMark && board[1][1] == playerMark && board[2][0] == playerMark) return true;
        return false;
    }

    public PlayerType getCurrentPlayerType() {
        PlayerType typeToReturn = (currentPlayerMark == PLAYER_X) ? playerXType : playerOType;
        System.out.println("LOGIC.getCurrentPlayerType: mark=" + currentPlayerMark +
                           ", pXType=" + playerXType + ", pOType=" + playerOType +
                           ", returning=" + typeToReturn);
        return typeToReturn;
    }

    // AI related methods
    private int[] generateEasyMove() {
        if (!gameActive) return null;
        java.util.List<int[]> emptyCells = new java.util.ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }
        if (emptyCells.isEmpty()) {
            return null; // Should not happen if game is active and board is not full
        }
        return emptyCells.get(new java.util.Random().nextInt(emptyCells.size()));
    }

    // --- Minimax (Hard AI) specific methods --- 
    private boolean isMovesLeftOnBoard(char[][] boardState) {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                if (boardState[i][j] == EMPTY_CELL)
                    return true;
        return false;
    }

    private int evaluateBoard(char[][] boardState, char aiMark, char opponentMark) {
        // Checking for Rows for victory.
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (boardState[row][0] == boardState[row][1] && boardState[row][1] == boardState[row][2]) {
                if (boardState[row][0] == aiMark) return +10;
                else if (boardState[row][0] == opponentMark) return -10;
            }
        }
        // Checking for Columns for victory.
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (boardState[0][col] == boardState[1][col] && boardState[1][col] == boardState[2][col]) {
                if (boardState[0][col] == aiMark) return +10;
                else if (boardState[0][col] == opponentMark) return -10;
            }
        }
        // Checking for Diagonals for victory.
        if (boardState[0][0] == boardState[1][1] && boardState[1][1] == boardState[2][2]) {
            if (boardState[0][0] == aiMark) return +10;
            else if (boardState[0][0] == opponentMark) return -10;
        }
        if (boardState[0][2] == boardState[1][1] && boardState[1][1] == boardState[2][0]) {
            if (boardState[0][2] == aiMark) return +10;
            else if (boardState[0][2] == opponentMark) return -10;
        }
        return 0; // No one has won
    }

    // Minimax function
    private int minimax(char[][] boardState, int depth, boolean isMaximizingPlayer, char aiMark, char opponentMark) {
        int score = evaluateBoard(boardState, aiMark, opponentMark);

        if (score == 10) return score - depth; // Prioritize faster wins
        if (score == -10) return score + depth; // Prioritize delaying losses
        if (!isMovesLeftOnBoard(boardState)) return 0; // Tie

        if (isMaximizingPlayer) { // AI's turn (Maximizer)
            int bestScore = -1000;
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (boardState[i][j] == EMPTY_CELL) {
                        boardState[i][j] = aiMark;
                        bestScore = Math.max(bestScore, minimax(boardState, depth + 1, false, aiMark, opponentMark));
                        boardState[i][j] = EMPTY_CELL; // Undo move
                    }
                }
            }
            return bestScore;
        } else { // Opponent's turn (Minimizer)
            int bestScore = 1000;
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (boardState[i][j] == EMPTY_CELL) {
                        boardState[i][j] = opponentMark;
                        bestScore = Math.min(bestScore, minimax(boardState, depth + 1, true, aiMark, opponentMark));
                        boardState[i][j] = EMPTY_CELL; // Undo move
                    }
                }
            }
            return bestScore;
        }
    }

    // Method to find the best move for Hard AI
    private MinimaxMove findBestMove(char aiMark, char opponentMark) {
        int bestVal = -10000; // Initialize with a very low value
        MinimaxMove bestMove = new MinimaxMove();

        // Create a copy of the current board to pass to minimax, to avoid issues with direct modification
        char[][] boardCopy = new char[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++) {
            for(int j=0; j<BOARD_SIZE; j++) {
                boardCopy[i][j] = this.board[i][j];
            }
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (boardCopy[i][j] == EMPTY_CELL) {
                    boardCopy[i][j] = aiMark; // Make the AI's move on the copy
                    // AI is the maximizer, opponent is the minimizer. So, after AI moves, it's minimizer's turn (false for isMaximizingPlayer).
                    int moveVal = minimax(boardCopy, 0, false, aiMark, opponentMark);
                    boardCopy[i][j] = EMPTY_CELL; // Undo the move on the copy

                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        System.out.println("Minimax best move: (" + bestMove.row + "," + bestMove.col + ") with score: " + bestVal);
        return bestMove;
    }

    private int[] generateHardMove() {
        if (!gameActive) return null;
        char aiMark = this.currentPlayerMark;
        char opponentMark = (aiMark == PLAYER_X) ? PLAYER_O : PLAYER_X;
        
        MinimaxMove bestMove = findBestMove(aiMark, opponentMark);
        if (bestMove.row != -1) { // Check if a valid move was found
             return new int[]{bestMove.row, bestMove.col};
        }
        // Fallback if no move found (should ideally not happen in a playable game state)
        // This could indicate an issue or an already ended game state passed to AI.
        System.out.println("Warning: Minimax did not find a best move. Falling back to easy.");
        return generateEasyMove(); 
    }

    // --- Medium AI specific methods ---
    private int[] checkForImmediateWinOrBlock(char markToCheck, char opponentMark) {
        // Check if AI (markToCheck) can win
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    board[i][j] = markToCheck; // Try move
                    // Need to check win for markToCheck specifically, not currentPlayerMark
                    boolean wins = false;
                    // Simplified checkWin logic for a specific player after a hypothetical move
                    // Check row i
                    if (board[i][0] == markToCheck && board[i][1] == markToCheck && board[i][2] == markToCheck) wins = true;
                    // Check col j
                    if (!wins && board[0][j] == markToCheck && board[1][j] == markToCheck && board[2][j] == markToCheck) wins = true;
                    // Check diagonals if (i,j) is on a diagonal
                    if (!wins && (i == j) && (board[0][0] == markToCheck && board[1][1] == markToCheck && board[2][2] == markToCheck)) wins = true;
                    if (!wins && (i + j == 2) && (board[0][2] == markToCheck && board[1][1] == markToCheck && board[2][0] == markToCheck)) wins = true;
                    
                    board[i][j] = EMPTY_CELL; // Undo move
                    if (wins) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null; // No immediate win for markToCheck
    }

    private int[] generateMediumMove() {
        if (!gameActive) return null;
        char aiMark = this.currentPlayerMark;
        char opponentMark = (aiMark == PLAYER_X) ? PLAYER_O : PLAYER_X;

        // 1. Check if AI can win in the next move
        int[] winMove = checkForImmediateWinOrBlock(aiMark, opponentMark); // Pass opponentMark for completeness, though not used in this version of check
        if (winMove != null) {
            System.out.println("Medium AI: Found winning move: (" + winMove[0] + "," + winMove[1] + ")");
            return winMove;
        }

        // 2. Check if opponent can win in the next move, and block it
        int[] blockMove = checkForImmediateWinOrBlock(opponentMark, aiMark); // Check for opponent's win
        if (blockMove != null) {
            System.out.println("Medium AI: Found blocking move: (" + blockMove[0] + "," + blockMove[1] + ")");
            return blockMove;
        }

        // 3. Take the center if available
        if (board[1][1] == EMPTY_CELL) {
            System.out.println("Medium AI: Taking center.");
            return new int[]{1, 1};
        }

        // 4. Fallback to Easy (random) move
        System.out.println("Medium AI: No win/block/center, using Easy move.");
        return generateEasyMove();
    }

    public boolean performAIMove() {
        System.out.println("LOGIC.performAIMove: Entered. Current player type from getCurrentPlayerType(): " + getCurrentPlayerType() + ", Game active: " + gameActive);
        if (!gameActive || getCurrentPlayerType() == PlayerType.HUMAN) {
            System.out.println("LOGIC.performAIMove: Exiting because game not active or player is Human.");
            return false;
        }

        int[] move = null;
        PlayerType currentAIMode = getCurrentPlayerType();
        System.out.println("AI Type: " + currentAIMode + " is making a move.");

        switch (currentAIMode) {
            case AI_EASY:
                move = generateEasyMove();
                break;
            case AI_MEDIUM:
                move = generateMediumMove();
                break;
            case AI_HARD:
                move = generateHardMove();
                break;
            default:
                System.out.println("Unknown AI Type: " + currentAIMode);
                return false; // Should not happen
        }

        if (move != null && move.length == 2) {
            System.out.println("AI chose move: row " + move[0] + ", col " + move[1]);
            return makeMove(move[0], move[1]); // makeMove handles switching player and game state
        } else {
            System.out.println("AI failed to generate a move.");
             // This might happen if board is full but gameActive was somehow true, or AI logic error
            if (isBoardFull() && gameActive) { // Check if board is full and game is still active
                gameActive = false; // Set game to inactive (draw)
            }
        }
        return false;
    }
} 