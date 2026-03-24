package minesweeper;

import java.util.Random;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class Main {
    private static final int GRID_SIZE = 9;

    private static void checkEachNeighbor(int row,
                                          int column,
                                          BiConsumer<Integer, Integer> action) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < GRID_SIZE - 1; i++) {
            int newRow = row + dx[i];
            int newColumn = column + dy[i];

            if (isValidCoordinates(newRow,
                    newColumn)) {
                action.accept(newRow, newColumn);
            }
        }
    }


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
            System.out.print("Set/unset mines marks or claim a cell as free: ");
            int x = input.nextInt() - 1;
            int y = input.nextInt() - 1;
            String command = input.next();

            if (command.equals("free")) {
                if (field[y][x].isMine) {
                    Cell.showMines = true;
                    printField(field);
                    System.out.println("You stepped on a mine and failed!");
                    return;
                }

                exploreCell(field, y, x);

            } else if (command.equals("mine")) {
                if (field[y][x].isExploded) {
                    System.out.println("You already " +
                            "explored this cell!");
                } else {
                    field[y][x].isMarked = !field[y][x].isMarked;

                }
            }
            won = checkWinConditions(field, mines);
            printField(field);
        }
        System.out.println("Congratulations! You found all mines!");

    }

    private static void exploreCell(Cell[][] field,
                                    int row, int column) {
        if (field[row][column].isExploded) return;
        field[row][column].isExploded = true;
        field[row][column].isMarked = false;


        field[row][column].isExploded = true;
        field[row][column].isMarked = false;

        if (field[row][column].counter == 0) {
            checkEachNeighbor(row, column,
                    (nrow, ncol) -> {
                Cell neighbor = field[nrow][ncol];
                if (!neighbor.isMine) {
                    exploreCell(field, nrow, ncol);
                }
            });
        }
    }

    private static boolean checkWinConditions(Cell[][] field, int mines) {
        int unmarkedMines = 0;
        int wrongMarks = 0;
        int unexploredSafeCells = 0;

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                Cell cell = field[row][column];

                if (cell.isMine) {
                    if (!cell.isMarked) unmarkedMines++;
                } else {
                    if (cell.isMarked) wrongMarks++;
                    if (!cell.isExploded) unexploredSafeCells++;
                }
            }
        }

        boolean allMinesMarked = (unmarkedMines == 0 && wrongMarks == 0);
        boolean allSafeCellsExplored = (unexploredSafeCells == 0);
        return (allMinesMarked || allSafeCellsExplored);
    }


    private static Cell[][] initializeField() {
        Cell[][] field = new Cell[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                field[i][j] = new Cell();
            }
        }

        return field;
    }

    private static void placeMines(Cell[][] field, int mines) {
        int minesPlaced = 0;

        Random random = new Random();
        while (minesPlaced < mines) {
            int x = random.nextInt(GRID_SIZE);
            int y = random.nextInt(GRID_SIZE);

            if (!field[x][y].isMine) {
                field[x][y].isMine = true;
                minesPlaced++;
            }
        }
    }

    private static void showHints(Cell[][] field) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (field[row][column].isMine) continue;

                final int r = row;
                final int c = column;

                checkEachNeighbor(r, c, (nr, nc) -> {
                    if (field[nr][nc].isMine) {
                        field[r][c].counter++;
                    }
                });
            }
        }
    }

    private static boolean isValidCoordinates(int row, int column) {
        return row >= 0 && row < GRID_SIZE && column >= 0 && column < GRID_SIZE;
    }

    private static void printHeader() {
        System.out.print(" |");
        for (int i = 1; i <= GRID_SIZE; i++) {
            System.out.print(i);
        }
        System.out.println("|");
    }

    private static void printHorizontalBorder() {
        System.out.print("-|");
        for (int i = 0; i < GRID_SIZE; i++) {
            System.out.print("-");
        }
        System.out.println("|");
    }

    private static void printField(Cell[][] field) {
        printHeader();
        printHorizontalBorder();

        for (int row = 0; row < GRID_SIZE; row++) {
            System.out.printf("%d|", row + 1);
            for (int column = 0; column < GRID_SIZE; column++) {
                System.out.print(field[row][column].toString());
            }
            System.out.println("|");
        }

        printHorizontalBorder();
    }
}

class Cell {
    static boolean showMines = false;
    boolean isMine;
    boolean isMarked;
    boolean isExploded;
    int counter;

    Cell() {
        this.isMine = false;
        this.isMarked = false;
        this.isExploded = false;
        this.counter = 0;
    }

    @Override
    public String toString() {
        if (showMines && isMine) return "X";
        if (isMarked) return "*";
        if (!isExploded) return ".";
        if (counter > 0) return String.valueOf(counter);
        return "/";
    }
}
