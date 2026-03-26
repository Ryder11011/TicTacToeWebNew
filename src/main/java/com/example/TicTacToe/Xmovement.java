package com.example.TicTacToe;

public class Xmovement {
    int[][] playGround = new int[3][3];
    int counter = 0;
    boolean win = false;
    boolean moveMake = false;
    int topLeftWeight = 0;
    int bottomRightWeight = 0;

    public String Oplay(int row, int column) {
        if (playGround[row][column] == 0) {
            playGround[row][column] = -1;
            return "success";
        }
        else {
            return "used";
        }
    }

    public boolean xMove() {
        moveMake = false;
        play(1);
        if (win) {
            return true;
        }
        else {
            play(-1);
            if (counter == 0) {
                counter++;
                playGround[0][0] = 1;
            }
            else if (counter == 1) {
                counter++;
                play1();
            }
            else if (counter == 2) {
                counter++;
                play2();
            }
        }
        return false;
    }

    public void weights() {
        int count = 2;

        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= count; j++) {
                if (playGround[i][j] == -1) {
                    topLeftWeight++;
                }
            }
            for (int j = count; j <= 2; j++) {
                if (playGround[i][j] == -1) {
                    bottomRightWeight++;
                }
            }
            count--;
        }
    }

    public void play1() {
        for (int i = 1; i <= 2; i++) {
            if (playGround[0][i] == -1) {
                playGround[2][0] = 1;
                return;
            }
            if (playGround[i][0] == -1) {
                playGround[0][2] = 1;
                return;
            }
        }

        if (playGround[1][2] == -1) {
            playGround[2][0] = 1;
            return;
        }
        if (playGround[2][1] == -1 || playGround[2][2] == -1) {
            playGround[0][2] = 1;
            return;
        }

        if (playGround[1][1] == -1) {
            playGround[2][2] = 1;
        }
    }

    public void play2() {
        if (!moveMake) {
            weights();
            if (playGround[2][0] == 0 && topLeftWeight <= bottomRightWeight) {
                playGround[2][0] = 1;
            }
            else if (playGround[2][2] == 0 && topLeftWeight >= bottomRightWeight) {
                playGround[2][2] = 1;
            }
            moveMake = true;
        }
    }

    public void play(int number) {
        int count = 0;
        int tempRow = -1;
        int tempColumn = -1;
        boolean done = false;

        for (int i = 0; i <= 2; i++) {    //ROWS
            for (int j = 0; j <= 2; j++) {
                if (playGround[i][j] == number) {
                    count++;
                }
                else if (playGround[i][j] == 0) {
                    tempRow = i;
                    tempColumn = j;
                }
            }
            if (count == 2 && tempColumn != -1 && tempRow != -1 & playGround[tempRow][tempColumn] == 0) {
                //System.out.println("Nah bruh");
                playGround[tempRow][tempColumn] = 1;
                if (number == 1) {
                    win = true;
                }
                moveMake = true;
                return;
            }
            count = 0;
            tempRow = -1;
            tempColumn = -1;
        }

        for (int i = 0; i <= 2; i++) {    //COLUMNS
            for (int j = 0; j <= 2; j++) {
                if (playGround[j][i] == number) {
                    count++;
                }
                else if (playGround[j][i] == 0) {
                    tempRow = j;
                    tempColumn = i;
                }
            }
            if (count == 2 && tempColumn != -1 && tempRow != -1 & playGround[tempRow][tempColumn] == 0) {
                //System.out.println("Nah bruh");
                playGround[tempRow][tempColumn] = 1;
                //count = 0;
                if (number == 1) {
                    win = true;
                }
                moveMake = true;
                return;
            }
            count = 0;
            tempRow = -1;
            tempColumn = -1;
        }

        for (int i = 0; i <= 2; i++) {    //DIAGONAL L TO R
            if (playGround[i][i] == number) {
                count++;
            }
            else if (playGround[i][i] == 0) {
                tempRow = i;
            }
        }
        if (count == 2 && tempRow != -1 && playGround[tempRow][tempRow] == 0) {
            playGround[tempRow][tempRow] = 1;
            if (number == 1) {
                win = true;
            }
            moveMake = true;
            return;
        }
        count = 0;
        tempRow = -1;

        for (int i = 0; i <= 2; i++) {    //DIAGONAL R TO L
            if (playGround[2 - i][i] == number) {
                count++;
            }
            else if (playGround[2 - i][i] == 0) {
                tempRow = i;
            }
        }
        if (count == 2 && tempRow != -1  && playGround[2 - tempRow][tempRow] == 0) {
            playGround[2 - tempRow][tempRow] = 1;
            moveMake = true;
            if (number == 1) {
                win = true;
            }
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
}
