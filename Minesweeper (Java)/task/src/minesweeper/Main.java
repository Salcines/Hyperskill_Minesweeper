package minesweeper;

import java.util.Random;
import java.util.Scanner;

enum CellState {
    MINE ('X'),
    SAFE ('.');

    private final char symbol;

    CellState(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("How many mines do you want on the field? ");
        int mines = input.nextInt();

        Cell[][] field = initializeField();
        placeMines(field, mines);
        showHints(field);
        printField(field);

    }

    private static Cell[][] initializeField() {
        Cell[][] field = new Cell[9][9];

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = new Cell(CellState.SAFE);
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

            if (field[x][y].state == CellState.SAFE) {
                field[x][y] = new Cell(CellState.MINE);
                minesPlaced++;
            }
        }
    }

    private static void showHints(Cell[][] field) {

        Cell[][] hints = new Cell[9][9];

        for (int i = 0; i < hints.length; i++) {
            for (int j = 0; j < hints[i].length; j++) {
                hints[i][j] = new Cell(field[i][j].state);
            }
        }

        for (Cell[] row : field) {
            for (Cell cell : row) {
                if (cell.state == CellState.SAFE) {
                    cell.counter = 0;
                }
            }
        }

        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < hints.length; i++) {
            for (int j = 0; j < hints[i].length; j++) {
                if (hints[i][j].state == CellState.MINE) continue;

                int count = 0;

                for (int k = 0; k < 8; k++) {
                    int x = i + dx[k];
                    int y = j + dy[k];

                    if (x >= 0 && x < hints.length && y >= 0
                            && y < hints[i].length && hints[x][y].state ==
                            CellState.MINE) {

                        count++;
                    }
                }

                field[i][j].counter = count;
            }
        }
    }

    private static void printField(Cell[][] field) {
        for (Cell[] row : field) {
            for (Cell cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}

class Cell {
    CellState state;
    int counter;

    Cell(CellState state) {
        this.state = state;
        this.counter = 0;
    }

    @Override
    public String toString() {
        if (state == CellState.MINE) return String.valueOf(state.getSymbol());
        return counter > 0 ? String.valueOf(counter) : String.valueOf(state.getSymbol());
    }
}
