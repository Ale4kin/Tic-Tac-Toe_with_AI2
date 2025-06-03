# Tic-Tac-Toe Game with AI

A classic Tic-Tac-Toe game implemented in Java with a Swing-based Graphical User Interface (GUI). Challenge a friend or test your skills against an AI with varying difficulty levels!

## Features

- **Player vs Player Mode:** Play against another human on the same computer.
- **Player vs AI Mode:** Play against an Artificial Intelligence opponent.
  - **Easy Difficulty:** The AI makes random valid moves.
  - **Medium Difficulty:** The AI tries to win, then block, then plays center, then random.
  - **Hard Difficulty:** The AI uses a Minimax algorithm to determine the optimal move.
- **Graphical User Interface:** A user-friendly interface built with Java Swing.
- **"New Game" Functionality:** Easily start a new game with different player type configurations.

## How to Play (Running the Game)

1.  **Download the Game:**

    - You can download the executable JAR file (`Tic-Tac-Toe_with_AI-task.jar`) from the "Releases" section of this GitHub repository. (Alternatively, if you build from source, you can find it in `Tic-Tac-Toe with AI/task/build/libs/`).

2.  **Prerequisites:**

    - Ensure you have Java Runtime Environment (JRE) installed on your computer. You can download it from [Oracle's Java download page](https://www.java.com/download/) or use an open-source alternative like OpenJDK.

3.  **Run the Game:**
    - Open a terminal or command prompt.
    - Navigate to the directory where you downloaded `Tic-Tac-Toe_with_AI-task.jar`.
    - Execute the command: `java -jar Tic-Tac-Toe_with_AI-task.jar`
    - On some systems, you might be able to run the JAR file by simply double-clicking it.

## Building and Running from Source

If you want to build the game from the source code:

1.  **Clone the Repository:**

    ```bash
    git clone <repository_url>
    cd <repository_directory>
    ```

2.  **Prerequisites:**

    - Java Development Kit (JDK) - Version 16 or compatible is recommended (as per `gradle.properties`).
    - Gradle (the project uses the Gradle wrapper, so it should download automatically if not present).

3.  **Build the Executable JAR:**

    - From the project root directory, run:
      ```bash
      ./gradlew :Tic-Tac-Toe_with_AI-task:jar
      ```
    - The JAR file will be located in `Tic-Tac-Toe with AI/task/build/libs/Tic-Tac-Toe_with_AI-task.jar`.

4.  **Run the Application (via Gradle):**
    - From the project root directory, run:
      ```bash
      ./gradlew :Tic-Tac-Toe_with_AI-task:run
      ```

## Project Structure

- `Tic-Tac-Toe with AI/task/src/tictactoe/`: Contains the main Java source code.
  - `Main.java`: Original console-based game logic (kept for reference/history).
  - `TicTacToeGUI.java`: Implements the Swing-based GUI and main game window.
  - `GameLogic.java`: Encapsulates the game board, rules, player turn management, and AI logic.
  - `PlayerType.java`: Enum for defining player types (Human, AI Easy/Medium/Hard).
- `build.gradle`: Gradle build script for managing dependencies and build tasks.
- `gradle.properties`: Specifies Gradle properties, including the Java home for compilation.
