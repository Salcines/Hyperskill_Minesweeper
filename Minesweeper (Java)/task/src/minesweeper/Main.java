package minesweeper;

import java.util.Arrays;

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
    public static final int MINES_COUNT = 10;

    public static void main(String[] args) {
        CellState[][] field = initializeField();
        placeMines(field);
        printField(field);

    }

    private static void placeMines(CellState[][] field) {
        int mines = 0;
        int index = 0;
        int skip = 5;

        int totalCells = field.length * field[0].length;

        while (mines < MINES_COUNT && index < totalCells) {
            int row = index / field[0].length;
            int column = index % field[0].length;

            if (index % skip == 0 && field[row][column] == CellState.SAFE) {
                field[row][column] = CellState.MINE;
                mines++;
            }

            index++;
        }
    }

    private static CellState[][] initializeField() {
        CellState[][] field = new CellState[9][9];

        for (CellState[] row : field) {
            Arrays.fill(row, CellState.SAFE);
        }
        return field;
    }

    private static void printField(CellState[][] field) {
        for (CellState[] row : field) {
            for (CellState cell : row) {
                System.out.print(cell.getSymbol());
            }

            System.out.println();
        }
    }
}

