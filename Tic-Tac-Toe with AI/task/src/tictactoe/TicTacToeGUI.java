package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {

    private JButton[][] buttons = new JButton[3][3];
    private JLabel statusLabel;
    private GameLogic game; // Changed from commented out to actual instance
    private JComboBox<String> player1TypeSelector;
    private JComboBox<String> player2TypeSelector;

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe Game"); // Slightly different title to confirm change
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Add some gaps between components

        // Initialize game logic first, so UI components can query initial state if needed
        game = new GameLogic();

        // Status Label (initialized before startNewGame updates it)
        statusLabel = new JLabel("Initializing...", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Serif", Font.BOLD, 18)); // Slightly larger font
        add(statusLabel, BorderLayout.NORTH);

        // Panel for the Tic-Tac-Toe grid
        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 5, 5)); // Add gaps to gridlayout
        initializeButtons(gridPanel);
        add(gridPanel, BorderLayout.CENTER);
        
        // Control Panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)); // Centered flow with gaps
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> startNewGame());

        String[] playerTypes = {"Human", "AI Easy", "AI Medium", "AI Hard"};
        player1TypeSelector = new JComboBox<>(playerTypes);
        player2TypeSelector = new JComboBox<>(playerTypes);

        controlPanel.add(new JLabel("Player X:"));
        controlPanel.add(player1TypeSelector);
        controlPanel.add(Box.createHorizontalStrut(10)); // Spacer
        controlPanel.add(new JLabel("Player O:"));
        controlPanel.add(player2TypeSelector);
        controlPanel.add(Box.createHorizontalStrut(10)); // Spacer
        controlPanel.add(newGameButton);
        add(controlPanel, BorderLayout.SOUTH);
        
        // Call startNewGame to initialize board state & labels based on default JComboBox selections
        startNewGame(); 

        pack(); // Pack the frame to fit components based on their preferred sizes
        setMinimumSize(getPreferredSize()); // Optional: prevent resizing too small
        setLocationRelativeTo(null); // Center on screen
        setVisible(true); // Make the frame visible LAST
    }

    private void initializeButtons(JPanel gridPanel) {
        Dimension buttonSize = new Dimension(100, 100); // Define a good size for buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setPreferredSize(buttonSize); // Set preferred size
                buttons[i][j].setMinimumSize(buttonSize); // Optional: ensure minimum size
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 80));
                buttons[i][j].setFocusPainted(false); // Remove focus border
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)); // Thin border for cell separation
                // buttons[i][j].setContentAreaFilled(false); // Optional: for more custom look if panel bg is used
                // buttons[i][j].setBackground(Color.WHITE); // Example background
                
                final int row = i;
                final int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleCellClick(row, col);
                    }
                });
                gridPanel.add(buttons[i][j]);
            }
        }
    }

    private void handleCellClick(int row, int col) {
        System.out.println("GUI.handleCellClick: Clicked (" + row + "," + col + "). Current player mark in logic: " + game.getCurrentPlayerMark() + ", Type: " + game.getCurrentPlayerType());
        if (game.isGameActive() && game.isCellEmpty(row, col)) {
            // Check if the current player (fetched from game logic) is actually human before processing click as a human move
            if (game.getCurrentPlayerType() != PlayerType.HUMAN) {
                System.out.println("GUI.handleCellClick: Click processed, but current player in logic is AI (" + game.getCurrentPlayerType() + "). Human clicks should be ignored during AI turn. Status: " + game.getGameStatusMessage());
                // Optionally provide feedback to user, e.g. statusLabel.setText("Wait, it's AI's turn!");
                return; // Ignore click if it's AI's turn
            }

            char playerMark = game.getCurrentPlayerMark();
            if (game.makeMove(row, col)) {
                buttons[row][col].setText(String.valueOf(playerMark));
                buttons[row][col].setEnabled(false);
                updateStatusLabel();

                if (!game.isGameActive()) {
                    System.out.println("GUI.handleCellClick: Game ended after human move.");
                } else {
                    System.out.println("GUI.handleCellClick: Human move done. About to check if next player (" + game.getCurrentPlayerMark() + ", Type: " + game.getCurrentPlayerType() + ") is AI.");
                    if (game.getCurrentPlayerType() != PlayerType.HUMAN) {
                        System.out.println("GUI.handleCellClick: Next player is AI. Calling triggerAIMove().");
                        triggerAIMove();
                    } else {
                        System.out.println("GUI.handleCellClick: Next player is Human.");
                    }
                }
            }
        } else if (!game.isGameActive()){
             System.out.println("Game is over. Please start a new game.");
             // Optionally show a dialog
             // JOptionPane.showMessageDialog(this, "Game is over. Please start a new game.");
        } else if (!game.isCellEmpty(row, col)) {
            System.out.println("Cell already occupied: " + row + ", " + col);
            // Optionally show a message to the user if they click an occupied cell
            // JOptionPane.showMessageDialog(this, "This cell is already occupied!");
        }
    }
    
    private void startNewGame() {
        String p1SelectedString = (String) player1TypeSelector.getSelectedItem();
        String p2SelectedString = (String) player2TypeSelector.getSelectedItem();

        System.out.println("GUI.startNewGame: Player X selected string: \"" + p1SelectedString + "\"");
        System.out.println("GUI.startNewGame: Player O selected string: \"" + p2SelectedString + "\"");

        PlayerType player1Type;
        switch (p1SelectedString) {
            case "AI Easy": player1Type = PlayerType.AI_EASY; break;
            case "AI Medium": player1Type = PlayerType.AI_MEDIUM; break;
            case "AI Hard": player1Type = PlayerType.AI_HARD; break;
            case "Human":
            default: player1Type = PlayerType.HUMAN; break;
        }

        PlayerType player2Type;
        switch (p2SelectedString) {
            case "AI Easy": player2Type = PlayerType.AI_EASY; break;
            case "AI Medium": player2Type = PlayerType.AI_MEDIUM; break;
            case "AI Hard": player2Type = PlayerType.AI_HARD; break;
            case "Human":
            default: player2Type = PlayerType.HUMAN; break;
        }
        
        System.out.println("GUI.startNewGame: Converted Player X Type: " + player1Type);
        System.out.println("GUI.startNewGame: Converted Player O Type: " + player2Type);
        
        game.resetGame(player1Type, player2Type);

        // Reset buttons visuals
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        updateStatusLabel(); // Update status after types are set in game logic

        // If Player X is AI, trigger its first move
        System.out.println("GUI.startNewGame: Checking if Player X (" + game.getCurrentPlayerMark() + ") is AI to make first move. Type: " + game.getCurrentPlayerType());
        if (game.isGameActive() && game.getCurrentPlayerMark() == GameLogic.PLAYER_X && game.getCurrentPlayerType() != PlayerType.HUMAN) {
            System.out.println("GUI.startNewGame: Player X is AI, calling triggerAIMove().");
            triggerAIMove();
        } else {
            System.out.println("GUI.startNewGame: Player X is Human or game not active for AI first move.");
        }
    }

    private void triggerAIMove() {
        System.out.println("GUI.triggerAIMove: Entered. Current player type: " + game.getCurrentPlayerType() + ", Game active: " + game.isGameActive());
        if (!game.isGameActive() || game.getCurrentPlayerType() == PlayerType.HUMAN) {
            System.out.println("GUI.triggerAIMove: Exiting because game not active or player is Human.");
            return;
        }
        
        // Disable board during AI "thinking"
        setBoardEnabled(false);
        statusLabel.setText("Player " + game.getCurrentPlayerMark() + " (" + game.getCurrentPlayerType() + ") is thinking...");

        // Delay AI move for better UX
        Timer aiTimer = new Timer(750, e -> { // 750ms delay
            System.out.println("GUI: AI Timer fired. Attempting to trigger AI move for: " + game.getCurrentPlayerMark() + " (" + game.getCurrentPlayerType() + ")");
            boolean aiMoved = game.performAIMove();

            if (aiMoved) {
                System.out.println("GUI: AI move performed. Updating board.");
                updateBoardFromGameLogic(); // This will also re-enable buttons if next is human
                updateStatusLabel();

                if (!game.isGameActive()) {
                    // AI made a winning or draw move. Status label is updated.
                    // Buttons will be disabled by updateBoardFromGameLogic because game is inactive
                } else {
                    // Check if the next player is also AI (AI vs AI)
                    if (game.getCurrentPlayerType() != PlayerType.HUMAN) {
                        // No InvokeLater needed here as Timer already runs on EDT
                        triggerAIMove(); // Recursive call for next AI, will have its own delay
                    } else {
                        setBoardEnabled(true); // Re-enable board for human player
                    }
                }
            } else {
                System.out.println("GUI: AI did not make a move. This might be a draw or an error.");
                updateStatusLabel(); 
                if (game.isBoardFull() && !game.checkWinForPlayer(GameLogic.PLAYER_X) && !game.checkWinForPlayer(GameLogic.PLAYER_O)) {
                    // Draw
                }
                setBoardEnabled(true); // Re-enable board if AI failed and game didn't end
            }
        });
        aiTimer.setRepeats(false); // Execute only once
        aiTimer.start();
    }

    private void setBoardEnabled(boolean enabled) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Only enable if the cell is empty and game is active
                if (enabled && game.getBoard()[i][j] == GameLogic.EMPTY_CELL && game.isGameActive()) {
                    buttons[i][j].setEnabled(true);
                } else {
                    buttons[i][j].setEnabled(false);
                }
            }
        }
    }

    private void updateBoardFromGameLogic() {
        char[][] boardState = game.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(String.valueOf(boardState[i][j]));
                if (boardState[i][j] == GameLogic.PLAYER_X) {
                    buttons[i][j].setForeground(new Color(0, 0, 150)); // Dark Blue for X
                } else if (boardState[i][j] == GameLogic.PLAYER_O) {
                    buttons[i][j].setForeground(new Color(150, 0, 0)); // Dark Red for O
                } else {
                    buttons[i][j].setForeground(Color.BLACK); // Default for empty or other states
                }

                if (boardState[i][j] == GameLogic.EMPTY_CELL && game.isGameActive()) {
                    buttons[i][j].setEnabled(true);
                } else {
                    buttons[i][j].setEnabled(false);
                }
            }
        }
    }

    private void updateStatusLabel() {
        statusLabel.setText(game.getGameStatusMessage());
    }

    public static void main(String[] args) {
        // Ensure GUI updates are on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToeGUI();
            }
        });
    }
} 