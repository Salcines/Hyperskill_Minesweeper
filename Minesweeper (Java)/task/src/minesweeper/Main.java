package minesweeper;

import java.util.Arrays;
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

        CellState[][] field = initializeField();
        placeMines(field, mines);
        printField(field);

    }

    private static void placeMines(CellState[][] field, int mines) {
        int minesPlaced = 0;

        Random random = new Random();
        while (minesPlaced < mines) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);

            if (field[x][y] == CellState.SAFE) {
                field[x][y] = CellState.MINE;
                minesPlaced++;
            }
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
