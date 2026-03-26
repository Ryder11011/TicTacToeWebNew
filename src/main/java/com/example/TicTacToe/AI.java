package com.example.TicTacToe;

import java.util.ArrayList;

public class AI {
    int[][] playGround = new int[3][3];
    ArrayList<Integer> scores = new ArrayList<>();
    ArrayList<Integer> rows = new ArrayList<>();
    ArrayList<Integer> columns = new ArrayList<>();

    public int checkWin() {
        for (int i = 0; i <= 2; i++) {
            if (playGround[i][0] == 1 && playGround[i][1] == 1 && playGround[i][2] == 1) {
                return 10;
            }
            else if (playGround[i][0] == -1 && playGround[i][1] == -1 && playGround[i][2] == -1) {
                return -10;
            }

            if (playGround[0][i] == 1 && playGround[1][i] == 1 && playGround[2][i] == 1) {
                return 10;
            }
            else if (playGround[0][i] == -1 && playGround[1][i] == -1 && playGround[2][i] == -1) {
                return -10;
            }
        }

        if (playGround[0][0] == 1 && playGround[1][1] == 1 && playGround[2][2] == 1) {
            return 10;
        }
        else if (playGround[0][0] == -1 && playGround[1][1] == -1 && playGround[2][2] == -1) {
            return - 10;
        }
        if (playGround[0][2] == 1 && playGround[1][1] == 1 && playGround[2][0] == 1) {
            return 10;
        }
        else if (playGround[0][2] == -1 && playGround[1][1] == -1 && playGround[2][0] == -1) {
            return -10;
        }
        return 0;
    }



    public boolean draw() {
        int count = 0;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (playGround[i][j] == 1 || playGround[i][j] == -1) {
                    count++;
                }
            }
        }
        if (count == 9) {
            return true;
        }
        else {
            return false;
        }
    }

    public void printBackend() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                System.out.print(playGround[i][j]);
            }
            System.out.print("\n");
        }
    }


    public int algorithm(int numberOfPredictions, boolean xTurn, int[][] ground, boolean firstMove, int row, int column) {

        if (checkWin() == 10) {
            int score = 10 - numberOfPredictions;
//            System.out.println("\nWin Found");
//            printBackend();
//            System.out.println("Score: " + score);
//            if (score == 7) {
//                System.out.println("\nWin Found");
//                printBackend();
//                System.out.println("Score: " + score);
//            }
            return score;
        }
        else if (checkWin() == -10) {
            int score = (-10) + numberOfPredictions;
//            System.out.println("\nLoss found");
//            printBackend();
//            System.out.println("Score: " + score);
            return score;
        }

        if (draw()) {
//            System.out.println("\nDraw Found");
            //printBackend();
            return 0;
        }

        if (xTurn) {
            int score = -4567890;
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (playGround[i][j] == 0) {
                        ground[i][j] = 1;
                        int currentScore = algorithm(numberOfPredictions + 1, false, ground, false, row, column);
                        if (score < currentScore) {
                            score = currentScore;
                        }
                        ground[i][j] = 0;
                    }
                }
            }
            return score;
        }
        else if (!xTurn) {
            int score = 9876567;
            for (int i = 0; i <= 2; i++) {
                for (int j = 0; j <= 2; j++) {
                    if (playGround[i][j] == 0) {
                        ground[i][j] = -1;
                        int currentScore = algorithm(numberOfPredictions + 1, true, ground, false, row, column);
                        if (score > currentScore) {
                            score = currentScore;
                        }
                        ground[i][j] = 0;
                    }
                }
            }
            return score;
        }
        return 0;
    }

    public void play() {
        int row = 0;
        int column = 0;
        int score1 = -5678667;

        if (draw()) {
            return;
        }

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (playGround[i][j] == 0) {
                    playGround[i][j] = 1;
                    int currentScore = algorithm(0, false, playGround, false, 0, 0);
                    if (score1 < currentScore) {
                        score1 = currentScore;
                        row = i;
                        column = j;
                    }
                    playGround[i][j] = 0;
                }
            }
        }
        playGround[row][column] = 1;
        //System.out.println("Rows: " + row);
        //System.out.println("Column: " + column);
    }

    public void reset() {
        scores = new ArrayList<>();
        rows = new ArrayList<>();
        columns = new ArrayList<>();
    }

//    public static void main(String[] args) {
//        AI play = new AI();
////        play.playGround[0][0] = 1;
////        play.playGround[0][2] = 1;
////        play.playGround[2][0] = 1;
////        play.playGround[1][1] = -1;
////        play.playGround[1][0] = -1;
////        play.playGround[0][1] = -1;
////        play.printBackend();
////
//        play.play();
//        System.out.println("\n");
//        play.printBackend();
//    }
}