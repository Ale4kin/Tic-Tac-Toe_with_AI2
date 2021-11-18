package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner (System.in);

        int free = 9;

        boolean gameOver = false;

        while (!gameOver) {

            System.out.println ("Input command:");

            String inputCommand = scanner.nextLine ().replaceAll ("> ", "");

            if ("start easy easy".equals (inputCommand)) {

                char[][] arr = getGameField ();

                printGameField (arr);

                boolean player1 = true;

                boolean finished = false;

                while (!finished) {
                    System.out.println ("Making move level \"easy\"");

                    int rand1;
                    int rand2;

                    rand1 = (int) (Math.random () * 3);
                    rand2 = (int) (Math.random () * 3);

                    if (rand1 > 2 || rand2 > 2 || rand1 < 0 || rand2 < 0) {
                        continue;
                    }

                    if (arr[rand1][rand2] != ' ') {
                        continue;
                    }

                    char c;
                    if (player1) {
                        c = 'X';
                        player1 = false;
                        free = free - 1;
                    } else {
                        c = 'O';
                        player1 = true;
                        free = free - 1;
                    }

                    arr[rand1][rand2] = c;
                    free = free - 1;

                    printGameField (arr);

                    finished = hasWon (free, arr, finished);


                }

                continue;
            }

            if ("start easy user".equals (inputCommand)) {

                char[][] arr = getGameField ();

                printGameField (arr);

                boolean finished = false;

                while (!finished) {
                    System.out.println ("Making move level \"easy\"");

                    int rand1;
                    int rand2;

                    rand1 = (int) (Math.random () * 3);
                    rand2 = (int) (Math.random () * 3);

                    arr[rand1][rand2] = 'X';
                    free = free - 1;

                    printGameField (arr);

                    finished = hasWon (free, arr, finished);

                    System.out.println ("Enter the coordinates:");

                    String input = scanner.nextLine ().replaceAll ("> ", "").replaceAll ("_", " ");

                    int row = getX (input);
                    int column = getY (input);

                    if (row > 2 || column > 2 || row < 0 || column < 0) {
                        System.out.println ("Coordinates should be from 1 to 3!");
                        continue;
                    }

                    if (arr[row][column] != ' ') {
                        System.out.println ("This cell is occupied! Choose another one!");
                        continue;
                    }


                    arr[row][column] = 'O';
                    free = free - 1;

                    printGameField (arr);

                    // проверяем на победу

                    finished = hasWon (free, arr, finished);


                }

                continue;
            }

            if ("start user easy".equals (inputCommand)) {

                char[][] arr = getGameField ();

                printGameField (arr);


                boolean finished = false;

                while (!finished) {


                    System.out.println ("Enter the coordinates:");

                    String input = scanner.nextLine ().replaceAll ("> ", "").replaceAll ("_", " ");

                    int row = getX (input);
                    int column = getY (input);

                    if (row > 2 || column > 2 || row < 0 || column < 0) {
                        System.out.println ("Coordinates should be from 1 to 3!");
                        continue;
                    }

                    if (arr[row][column] != ' ') {
                        System.out.println ("This cell is occupied! Choose another one!");
                        continue;
                    }


                    arr[row][column] = 'X';
                    free = free - 1;

                    printGameField (arr);

                    // проверяем на победу

                    finished = hasWon (free, arr, finished);


                    System.out.println ("Making move level \"easy\"");

                    int rand1;
                    int rand2;

                    rand1 = (int) (Math.random () * 3);
                    rand2 = (int) (Math.random () * 3);

                    arr[rand1][rand2] = 'O';
                    free = free - 1;

                    printGameField (arr);

                    finished = hasWon (free, arr, finished);

                }

                continue;
            }

            if ("start user user".equals (inputCommand)) {

                char[][] arr = getGameField ();
                boolean player1 = true;

                printGameField (arr);

                boolean finished = false;

                while (!finished) {
                    System.out.print ("Enter the coordinates: ");
                    String input = scanner.nextLine ().replaceAll ("> ", "").replaceAll ("_", " ");

                    int row = getX (input);
                    int column = getY (input);

                    if (row > 2 || column > 2 || row < 0 || column < 0) {
                        System.out.println ("Coordinates should be from 1 to 3!");
                        continue;
                    }

                    if (arr[row][column] != ' ') {
                        System.out.println ("This cell is occupied! Choose another one!");
                        continue;
                    }

                    // ставим

                    char c;
                    if (player1) {
                        c = 'X';
                        player1 = false;
                        free = free - 1;
                    } else {
                        c = 'O';
                        player1 = true;
                        free = free - 1;
                    }

                    arr[row][column] = c;

                    printGameField (arr);

                    // проверяем на победу

                    finished = hasWon (free, arr, finished);

                }
                continue;
            }

            if ("start medium user".equals (inputCommand)) {
                char[][] arr = getGameField ();

                printGameField (arr);

                boolean finished = false;

                while (!finished) {
                    System.out.println ("Making move level \"medium\"");

                    int rand1;
                    int rand2;

                    if ((arr[0][0] == 'X' && arr[0][1] == 'X') || (arr[2][0] == 'X' && arr[1][1] == 'X') || arr[2][2] == 'X' && arr[1][2] == 'X' || (arr[0][0] == 'O' && arr[0][1] == 'O') || (arr[2][0] == 'O' && arr[1][1] == 'O') || arr[2][2] == 'O' && arr[1][2] == 'O') {
                        rand1 = 0;
                        rand2 = 2;
                        arr[rand1][rand2] = 'X';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);

                    } else if (arr[1][0] == 'X' && arr[1][1] == 'X' || arr[0][2] == 'X' && arr[2][0] == 'X' || arr[1][0] == 'O' && arr[1][1] == 'O' || arr[0][2] == 'O' && arr[2][0] == 'O') {
                        rand1 = 1;
                        rand2 = 2;
                        arr[rand1][rand2] = 'X';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else if (arr[2][0] == 'X' && arr[2][1] == 'X' || arr[0][0] == 'X' && arr[1][1] == 'X' || arr[0][2] == 'X' && arr[1][2] == 'X' || arr[2][0] == 'O' && arr[2][1] == 'O' || arr[0][0] == 'O' && arr[1][1] == 'O' || arr[0][2] == 'O' && arr[1][2] == 'O') {
                        rand1 = 2;
                        rand2 = 2;
                        arr[rand1][rand2] = 'X';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else if (arr[2][0] == 'X' && arr[2][2] == 'X' || arr[0][1] == 'X' && arr[1][1] == 'X' || arr[2][0] == 'O' && arr[2][2] == 'O' || arr[0][1] == 'O' && arr[1][1] == 'O') {
                        rand1 = 2;
                        rand2 = 1;
                        arr[rand1][rand2] = 'X';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else if (arr[1][0] == 'X' && arr[1][2] == 'X' || arr[0][1] == 'X' && arr[2][1] == 'X' || arr[0][0] == 'X' && arr[2][2] == 'X' || arr[1][0] == 'O' && arr[1][2] == 'O' || arr[0][1] == 'O' && arr[2][1] == 'O' || arr[0][0] == 'O' && arr[2][2] == 'O') {
                        rand1 = 1;
                        rand2 = 1;
                        arr[rand1][rand2] = 'X';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else if (arr[0][0] == 'X' && arr[0][2] == 'X' || arr[1][1] == 'X' && arr[2][1] == 'X' || arr[0][0] == 'O' && arr[0][2] == 'O' || arr[1][1] == 'O' && arr[2][1] == 'O') {
                        rand1 = 0;
                        rand2 = 1;
                        arr[rand1][rand2] = 'X';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else if (arr[0][1] == 'X' && arr[0][2] == 'X' || arr[1][0] == 'X' && arr[2][0] == 'X' || arr[1][1] == 'X' && arr[2][2] == 'X' || arr[0][1] == 'O' && arr[0][2] == 'O' || arr[1][0] == 'O' && arr[2][0] == 'O' || arr[1][1] == 'O' && arr[2][2] == 'O') {
                        rand1 = 0;
                        rand2 = 0;
                        arr[rand1][rand2] = 'X';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else if (arr[0][0] == 'X' && arr[2][0] == 'X' || arr[1][1] == 'X' && arr[1][2] == 'X' || arr[0][0] == 'O' && arr[2][0] == 'O' || arr[1][1] == 'O' && arr[1][2] == 'O') {
                        rand1 = 1;
                        rand2 = 0;
                        arr[rand1][rand2] = 'X';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else if (arr[0][0] == 'X' && arr[1][0] == 'X' || arr[2][1] == 'X' && arr[2][2] == 'X' || arr[0][2] == 'X' && arr[1][1] == 'X' || arr[0][0] == 'O' && arr[1][0] == 'O' || arr[2][1] == 'O' && arr[2][2] == 'O' || arr[0][2] == 'O' && arr[1][1] == 'O') {
                        rand1 = 2;
                        rand2 = 0;
                        arr[rand1][rand2] = 'X';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else {

                        rand1 = (int) (Math.random () * 3);
                        rand2 = (int) (Math.random () * 3);

                        arr[rand1][rand2] = 'X';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    }

                    System.out.println ("Enter the coordinates:");

                    String input = scanner.nextLine ().replaceAll ("> ", "").replaceAll ("_", " ");

                    int row = getX (input);
                    int column = getY (input);

                    if (row > 2 || column > 2 || row < 0 || column < 0) {
                        System.out.println ("Coordinates should be from 1 to 3!");
                        continue;
                    }

                    if (arr[row][column] != ' ') {
                        System.out.println ("This cell is occupied! Choose another one!");
                        continue;
                    }


                    arr[row][column] = 'O';
                    free = free - 1;

                    printGameField (arr);

                    // проверяем на победу

                    finished = hasWon (free, arr, finished);


                }

                continue;

            }

            if ("start user medium".equals (inputCommand)) {

                char[][] arr = getGameField ();

                printGameField (arr);


                boolean finished = false;

                while (!finished) {


                    System.out.println ("Enter the coordinates:");

                    String input = scanner.nextLine ().replaceAll ("> ", "").replaceAll ("_", " ");

                    int row = getX (input);
                    int column = getY (input);

                    if (row > 2 || column > 2 || row < 0 || column < 0) {
                        System.out.println ("Coordinates should be from 1 to 3!");
                        continue;
                    }

                    if (arr[row][column] != ' ') {
                        System.out.println ("This cell is occupied! Choose another one!");
                        continue;
                    }


                    arr[row][column] = 'X';
                    free = free - 1;

                    printGameField (arr);

                    // проверяем на победу

                    finished = hasWon (free, arr, finished);


                    System.out.println ("Making move level \"medium\"");

                    int rand1;
                    int rand2;

                    if ((arr[0][0] == 'X' && arr[0][1] == 'X') || (arr[2][0] == 'X' && arr[1][1] == 'X') || arr[2][2] == 'X' && arr[1][2] == 'X' || (arr[0][0] == 'O' && arr[0][1] == 'O') || (arr[2][0] == 'O' && arr[1][1] == 'O') || arr[2][2] == 'O' && arr[1][2] == 'O') {
                        rand1 = 0;
                        rand2 = 2;
                        arr[rand1][rand2] = 'O';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);

                    } else if (arr[1][0] == 'X' && arr[1][1] == 'X' || arr[0][2] == 'X' && arr[2][0] == 'X' || arr[1][0] == 'O' && arr[1][1] == 'O' || arr[0][2] == 'O' && arr[2][0] == 'O') {
                        rand1 = 1;
                        rand2 = 2;
                        arr[rand1][rand2] = 'O';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else if (arr[2][0] == 'X' && arr[2][1] == 'X' || arr[0][0] == 'X' && arr[1][1] == 'X' || arr[0][2] == 'X' && arr[1][2] == 'X' || arr[2][0] == 'O' && arr[2][1] == 'O' || arr[0][0] == 'O' && arr[1][1] == 'O' || arr[0][2] == 'O' && arr[1][2] == 'O') {
                        rand1 = 2;
                        rand2 = 2;
                        arr[rand1][rand2] = 'O';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else if (arr[2][0] == 'X' && arr[2][2] == 'X' || arr[0][1] == 'X' && arr[1][1] == 'X' || arr[2][0] == 'O' && arr[2][2] == 'O' || arr[0][1] == 'O' && arr[1][1] == 'O') {
                        rand1 = 2;
                        rand2 = 1;
                        arr[rand1][rand2] = 'O';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else if (arr[1][0] == 'X' && arr[1][2] == 'X' || arr[0][1] == 'X' && arr[2][1] == 'X' || arr[0][0] == 'X' && arr[2][2] == 'X' || arr[1][0] == 'O' && arr[1][2] == 'O' || arr[0][1] == 'O' && arr[2][1] == 'O' || arr[0][0] == 'O' && arr[2][2] == 'O') {
                        rand1 = 1;
                        rand2 = 1;
                        arr[rand1][rand2] = 'O';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else if (arr[0][0] == 'X' && arr[0][2] == 'X' || arr[1][1] == 'X' && arr[2][1] == 'X' || arr[0][0] == 'O' && arr[0][2] == 'O' || arr[1][1] == 'O' && arr[2][1] == 'O') {
                        rand1 = 0;
                        rand2 = 1;
                        arr[rand1][rand2] = 'O';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else if (arr[0][1] == 'X' && arr[0][2] == 'X' || arr[1][0] == 'X' && arr[2][0] == 'X' || arr[1][1] == 'X' && arr[2][2] == 'X' || arr[0][1] == 'O' && arr[0][2] == 'O' || arr[1][0] == 'O' && arr[2][0] == 'O' || arr[1][1] == 'O' && arr[2][2] == 'O') {
                        rand1 = 0;
                        rand2 = 0;
                        arr[rand1][rand2] = 'O';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else if (arr[0][0] == 'X' && arr[2][0] == 'X' || arr[1][1] == 'X' && arr[1][2] == 'X' || arr[0][0] == 'O' && arr[2][0] == 'O' || arr[1][1] == 'O' && arr[1][2] == 'O') {
                        rand1 = 1;
                        rand2 = 0;
                        arr[rand1][rand2] = 'O';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else if (arr[0][0] == 'X' && arr[1][0] == 'X' || arr[2][1] == 'X' && arr[2][2] == 'X' || arr[0][2] == 'X' && arr[1][1] == 'X' || arr[0][0] == 'O' && arr[1][0] == 'O' || arr[2][1] == 'O' && arr[2][2] == 'O' || arr[0][2] == 'O' && arr[1][1] == 'O') {
                        rand1 = 2;
                        rand2 = 0;
                        arr[rand1][rand2] = 'O';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    } else {

                        rand1 = (int) (Math.random () * 3);
                        rand2 = (int) (Math.random () * 3);

                        arr[rand1][rand2] = 'O';
                        free = free - 1;

                        printGameField (arr);

                        finished = hasWon (free, arr, finished);
                    }

                }

                continue;
            }

            if ("start hard user".equals (inputCommand)) {
                char[][] arr = getGameField ();

                printGameField (arr);


                boolean finished = false;

                while (!finished) {
                    System.out.println ("Making move level \"hard\"");

                    Move bestMove = findBestMove (arr);

                    arr[bestMove.row][bestMove.col] = 'X';

                    free = free - 1;
                    printGameField (arr);

                    finished = hasWon (free, arr, finished);


                    System.out.println ("Enter the coordinates:");

                    String input = scanner.nextLine ().replaceAll ("> ", "").replaceAll ("_", " ");

                    int row = getX (input);
                    int column = getY (input);

                    if (row > 2 || column > 2 || row < 0 || column < 0) {
                        System.out.println ("Coordinates should be from 1 to 3!");
                        continue;
                    }

                    if (arr[row][column] != ' ') {
                        System.out.println ("This cell is occupied! Choose another one!");
                        continue;
                    }


                    arr[row][column] = 'O';
                    free = free - 1;

                    printGameField (arr);

                    // проверяем на победу

                    finished = hasWon (free, arr, finished);

                }
                continue;
            }

            if ("start user hard".equals (inputCommand)) {

                char[][] arr = getGameField ();

                printGameField (arr);


                boolean finished = false;

                while (!finished) {


                    System.out.println ("Enter the coordinates:");

                    String input = scanner.nextLine ().replaceAll ("> ", "").replaceAll ("_", " ");

                    int row = getX (input);
                    int column = getY (input);

                    if (row > 2 || column > 2 || row < 0 || column < 0) {
                        System.out.println ("Coordinates should be from 1 to 3!");
                        continue;
                    }

                    if (arr[row][column] != ' ') {
                        System.out.println ("This cell is occupied! Choose another one!");
                        continue;
                    }


                    arr[row][column] = 'X';
                    free = free - 1;

                    printGameField (arr);

                    // проверяем на победу

                    finished = hasWon (free, arr, finished);

                    System.out.println ("Making move level \"hard\"");

                    Move1 bestMove1 = findBestMove1(arr);

                    arr[bestMove1.row1][bestMove1.col1] = 'O';

                    free = free - 1;
                    printGameField (arr);

                    finished = hasWon (free, arr, finished);


                }
                continue;
            }

            if ("start hard hard".equals ((inputCommand))) {
                char[][] arr = getGameField ();

                printGameField (arr);

               // boolean player1 = true;
                boolean finished = false;

                while (!finished) {

                    System.out.println ("Making move level \"hard\"");

                    Move bestMove = findBestMove (arr);

                    arr[bestMove.row][bestMove.col] = 'X';

                    free = free - 1;
                    printGameField (arr);

                    finished = hasWon (free, arr, finished);

                    System.out.println ("Making move level \"hard\"");

                    Move1 bestMove1 = findBestMove1(arr);

                    arr[bestMove1.row1][bestMove1.col1] = 'O';

                    free = free - 1;
                    printGameField (arr);

                    finished = hasWon (free, arr, finished);


                }

                continue;
            }

            if ("exit".equals (inputCommand)) {
                gameOver = true;
            }
            if (!("exit".equals (inputCommand)) && !("start user user".equals (inputCommand)) && !("start easy user".equals (inputCommand)) && !("start easy easy".equals (inputCommand)) && !("start user easy".equals (inputCommand)) && !("start user medium".equals (inputCommand)) && !("start medium user".equals (inputCommand)) && !("start hard user".equals (inputCommand)) && !("start user hard".equals (inputCommand) && !("start hard hard".equals (inputCommand)))) {

                System.out.println ("Bad parameters!");
                continue;
            }

        }

    }


    public static char[][] getGameField() {
        char[][] arr = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = ' ';
            }
        }
        return arr;
    }


    public static boolean hasWon(int free, char[][] arr, boolean finished) {
        if ((arr[0][2] == 'X' && arr[1][2] == 'X' && arr[2][2] == 'X' || arr[0][1] == 'X' && arr[1][1] == 'X' && arr[2][1] == 'X' || arr[0][0] == 'X' && arr[1][0] == 'X' && arr[2][0] == 'X' || arr[0][2] == 'X' && arr[0][1] == 'X' && arr[0][0] == 'X' || arr[1][2] == 'X' && arr[1][1] == 'X' && arr[1][0] == 'X' || arr[0][2] == 'X' && arr[1][1] == 'X' && arr[2][0] == 'X' || arr[0][0] == 'X' && arr[1][1] == 'X' && arr[2][2] == 'X')) {
            System.out.println ("X wins");
            finished = true;
        } else if ((arr[0][2] == 'O' && arr[1][2] == 'O' && arr[2][2] == 'O' || arr[0][1] == 'O' && arr[1][1] == 'O' && arr[2][1] == 'O' || arr[0][0] == 'O' && arr[1][0] == 'O' && arr[2][0] == 'O' || arr[0][2] == 'O' && arr[0][1] == 'O' && arr[0][0] == 'O' || arr[1][2] == 'O' && arr[1][1] == 'O' && arr[1][0] == 'O' || arr[0][2] == 'O' && arr[1][1] == 'O' && arr[2][0] == 'O' || arr[0][0] == 'O' && arr[1][1] == 'O' && arr[2][2] == 'O')) {
            System.out.println ("O wins");
            finished = true;
        } else {

            if (free == 0) {
                System.out.println ("Draw");
                finished = true;
            }

        }
        return finished;
    }


    public static void printGameField(char[][] arr) {
        System.out.println ("---------");
        System.out.println ("|" + " " + arr[0][0] + " " + arr[0][1] + " " + arr[0][2] + " " + "|");
        System.out.println ("|" + " " + arr[1][0] + " " + arr[1][1] + " " + arr[1][2] + " " + "|");
        System.out.println ("|" + " " + arr[2][0] + " " + arr[2][1] + " " + arr[2][2] + " " + "|");
        System.out.println ("---------");
    }


    public static int getX(String s) {
        int x = 0;
        if (s.contains (" ")) {
            String[] str = s.split (" ");
            if ("1".equals (str[0])) {
                x = 0;
            } else if ("2".equals (str[0])) {
                x = 1;
            } else if ("3".equals (str[0])) {
                x = 2;
            } else if (!(str[0].matches ("-?\\d+")) || !(str[1].matches ("-?\\d+"))) {
                System.out.println ("You should enter numbers!");
                System.out.println ("Enter the coordinates:");
            } else {
                System.out.println ("Coordinates should be from 1 to 3!");
                System.out.println ("Enter the coordinates:");
            }
        } else {
            System.out.println ("You should enter numbers!");
            System.out.println ("Enter the coordinates:");
        }


        return x;
    }

    public static int getY(String s) {
        int y = 0;
        if (s.contains (" ")) {
            String[] str = s.split (" ");

            if ("1".equals (str[1])) {
                y = 0;
            } else if ("2".equals (str[1])) {
                y = 1;
            } else if ("3".equals (str[1])) {
                y = 2;
            } else if (!(str[1].matches ("-?\\d+")) || !(str[0].matches ("-?\\d+"))) {
                System.out.println ("You should enter numbers!");
                System.out.println ("Enter the coordinates:");
            } else {
                System.out.println ("Coordinates should be from 1 to 3!");
                System.out.println ("Enter the coordinates:");
            }
        } else {
            System.out.println ("You should enter numbers!");
            System.out.println ("Enter the coordinates:");
        }


        return y;
    }

    static class Move {
        int row, col;
    }

    static char player = 'X', opponent = 'O';

    // This function returns true if there are moves
// remaining on the board. It returns false if
// there are no moves left to play.
    static Boolean isMovesLeft(char board[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return true;
        return false;
    }

    // This is the evaluation function as discussed
// in the previous article ( http://goo.gl/sJgv68 )
    static int evaluate(char b[][]) {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2]) {
                if (b[row][0] == player)
                    return +10;
                else if (b[row][0] == opponent)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < 3; col++) {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col]) {
                if (b[0][col] == player)
                    return +10;

                else if (b[0][col] == opponent)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == player)
                return +10;
            else if (b[0][0] == opponent)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == player)
                return +10;
            else if (b[0][2] == opponent)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    // This is the minimax function. It considers all
// the possible ways the game can go and returns
// the value of the board
    static int minimax(char board[][],
                       int depth, Boolean isMax) {
        int score = evaluate (board);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (isMovesLeft (board) == false)
            return 0;

        // If this maximizer's move
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == ' ') {
                        // Make the move
                        board[i][j] = player;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max (best, minimax (board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == ' ') {
                        // Make the move
                        board[i][j] = opponent;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min (best, minimax (board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    // This will return the best possible
// move for the player
    static Move findBestMove(char board[][]) {
        int bestVal = -1000;
        Move bestMove = new Move ();
        bestMove.row = 0;
        bestMove.col = 0;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j] == ' ') {
                    // Make the move
                    board[i][j] = player;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax (board, 0, false);

                    // Undo the move
                    board[i][j] = ' ';

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }


        return bestMove;
    }


    static class Move1 {
        int row1, col1;
    }

    static char player1 = 'O', opponent1 = 'X';

    // This function returns true if there are moves
// remaining on the board. It returns false if
// there are no moves left to play.
    static Boolean isMovesLeft1(char board[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return true;
        return false;
    }

    // This is the evaluation function as discussed
// in the previous article ( http://goo.gl/sJgv68 )
    static int evaluate1(char b[][]) {
        // Checking for Rows for X or O victory.
        for (int row1 = 0; row1 < 3; row1++) {
            if (b[row1][0] == b[row1][1] &&
                    b[row1][1] == b[row1][2]) {
                if (b[row1][0] == player1)
                    return +10;
                else if (b[row1][0] == opponent1)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col1 = 0; col1 < 3; col1++) {
            if (b[0][col1] == b[1][col1] &&
                    b[1][col1] == b[2][col1]) {
                if (b[0][col1] == player1)
                    return +10;

                else if (b[0][col1] == opponent1)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == player1)
                return +10;
            else if (b[0][0] == opponent1)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == player1)
                return +10;
            else if (b[0][2] == opponent1)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    // This is the minimax function. It considers all
// the possible ways the game can go and returns
// the value of the board
    static int minimax1(char board[][],
                       int depth, Boolean isMax) {
        int score = evaluate1 (board);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (isMovesLeft1 (board) == false)
            return 0;

        // If this maximizer's move
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == ' ') {
                        // Make the move
                        board[i][j] = player1;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max (best, minimax1 (board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == ' ') {
                        // Make the move
                        board[i][j] = opponent1;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min (best, minimax1 (board,
                                depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    // This will return the best possible
// move for the player
    static Move1 findBestMove1(char[][] board) {
        int bestVal1 = -1000;
        Move1 bestMove1 = new Move1 ();
        bestMove1.row1 = 0;
        bestMove1.col1 = 0;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                if (board[i][j] == ' ') {
                    // Make the move
                    board[i][j] = player1;

                    // compute evaluation function for this
                    // move.
                    int moveVal1 = minimax1 (board, 0, false);

                    // Undo the move
                    board[i][j] = ' ';

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal1 > bestVal1) {
                        bestMove1.row1 = i;
                        bestMove1.col1 = j;
                        bestVal1 = moveVal1;
                    }
                }
            }
        }


        return bestMove1;
    }
}
