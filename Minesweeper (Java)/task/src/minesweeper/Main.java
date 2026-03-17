package minesweeper;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("How many mines do you want on the field? ");
        int mines = input.nextInt();

        Cell[][] field = initializeField();
        placeMines(field, mines);
        showHints(field);
        printField(field);

        boolean won = false;

        while (!won) {
            //printField(field);
            System.out.print("Set/delete mines marks " +
                    "(x and y coordinates): ");
            int x = input.nextInt() - 1;
            int y = input.nextInt() - 1;

            if (field[y][x].counter == 0) {
                field[y][x].isMarked =
                        !field[y][x].isMarked;
            } else {
                System.out.println("There is a number here!");
                continue;
            }

            won = checkWinConditions(field, mines);
            printField(field);
        }
        System.out.println("Congratulations! You found all mines!");

    }

    private static boolean checkWinConditions(Cell[][] field, int mines) {
        for (Cell[] row : field) {
            for (Cell cell : row) {
                if ((!cell.isMine && cell.isMarked) || (cell.isMine && !cell.isMarked)) {
                    return false;
                }
            }
        }
        return true;
    }


    private static Cell[][] initializeField() {
        Cell[][] field = new Cell[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                field[i][j] = new Cell();
            }
        }

        return field;
    }

    private static void placeMines(Cell[][] field, int mines) {
        int minesPlaced = 0;

        Random random = new Random();
        while (minesPlaced < mines) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);

            if (!field[x][y].isMine) {
                field[x][y].isMine = true;
                minesPlaced++;
            }
        }
    }

    private static void showHints(Cell[][] field) {
        int rows = field.length;
        int cols = field[0].length;

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (field[i][j].isMine) continue;

                int count = 0;
                for (int k = 0; k < 8; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];

                    if (x >= 0 && x < rows && y >= 0 && y < cols) {
                        if (field[x][y].isMine) {
                            count++;
                        }
                    }
                }
                field[i][j].counter = count;
            }
        }
    }

    private static void printField(Cell[][] field) {
        int rows = field.length;
        int cols = field[0].length;

        printHeader(cols);
        printHorizontalBorder(cols);

        for (int i = 0; i < rows; i++) {
            System.out.printf("%d|", i + 1);
            for (int j = 0; j < cols; j++) {
                System.out.print(field[i][j].toString());
            }
            System.out.println("|");
        }

        printHorizontalBorder(cols);
    }

    private static void printHeader(int size) {
        System.out.print(" |");
        for (int i = 1; i <= size; i++) {
            System.out.print(i);
        }
        System.out.println("|");
    }

    private static void printHorizontalBorder(int size) {
        System.out.print("-|");
        for (int i = 0; i < size; i++) {
            System.out.print("-");
        }
        System.out.println("|");
    }
}

class Cell {
    boolean isMine;
    boolean isMarked;
    int counter;

    Cell() {
        this.isMine = false;
        this.isMarked = false;
        this.counter = 0;
    }

    @Override
    public String toString() {
        if (isMarked) return "*";
        if (isMine) return ".";
        if (counter > 0) return String.valueOf(counter);
        return ".";
    }
}
