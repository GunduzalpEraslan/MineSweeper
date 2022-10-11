
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    Scanner keyboard = new Scanner(System.in);
    Random random = new Random();

    private int row;
    private int column;
    private int selectionRow;
    private int selectionColumn;
    private int mineCounter;
    private String[][] mineFree;
    private String[][] mineMap;

    MineSweeper(int row, int column) {
        this.row = row;
        this.column = column;
        this.mineFree = createArray();
        this.selectionRow = 0;
        this.selectionColumn = 0;
        this.mineCounter = 0;
    }

    public void playGame() {
        int gameRound = this.row * (this.column - 1); // Determines how many rounds the user will play.

        System.out.println();
        System.out.println("======== WELCOME TO MINE SWEEPER GAME ========");
        printArray(mineFree);
        System.out.println("====================");
        System.out.println();

        createMineField(mineMap); // Mined field is created.

        while (gameRound > 0) {

            System.out.print("Enter row number: ");
            this.selectionRow = keyboard.nextInt() - 1;
            System.out.print("Enter column number: ");
            this.selectionColumn = keyboard.nextInt() - 1;

            if (isValid(this.selectionRow, this.selectionColumn)) {
                if (isMined(this.selectionRow, this.selectionColumn) == true) {
                    System.out.println();
                    System.out.println("====================");
                    System.out.println("Boom!! You stepped on a mine.. Game Over!");
                    System.out.println();
                    printArray(this.mineMap);
                    break;
                } else {
                    this.mineMap[this.selectionRow][this.selectionColumn] = String
                            .valueOf(mineCount(selectionRow, selectionColumn));

                    System.out.println();
                    printArrayMineless(mineMap);
                }
            } else {
                System.out.println("Please enter valid value!");
            }
            System.out.println("===============");
            gameRound--;
            if (gameRound == 0) {
                System.out.println("Congratulations! You Won!");
                printArray(this.mineMap);
            }

        }

    }
    private void printArrayMineless(String[][] minedMap) {
        for (int i = 0; i < minedMap.length; i++) {
            for (int j = 0; j < minedMap[i].length; j++) {
                if (minedMap[i][j].equals("*")) {
                    System.out.print("-" + " ");
                } else {
                    System.out.print(minedMap[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    private boolean isValid(int row, int column) {
        if (!((row >= 0 && column >= 0) && (row < this.mineMap.length && column < this.mineMap[0].length))) {
            return false;
        }
        return true;
    }

    private boolean isMined(int row, int column) {
        if (this.mineMap[row][column].equals("*")) {

            return true;
        }
        return false;
    }
    private int mineCount(int row, int column) {
        this.mineCounter = 0;
        if (this.row >= row && this.column >= column && row >= 0 && column >= 0) {
            if (row - 1 >= 0 && column - 1 >= 0) {
                if (this.mineMap[row - 1][column - 1].equals("*")) {
                    this.mineCounter++;
                }
            }
            if (row - 1 >= 0) {
                if (this.mineMap[row - 1][column].equals("*")) {
                    this.mineCounter++;
                }
            }
            if (row - 1 >= 0 && column + 1 < this.column) {
                if ((column - 1 > 0) && this.mineMap[row][column - 1].equals("*")) {
                    this.mineCounter++;
                }
            }
            if (column - 1 > 0) {
                if (this.mineMap[row][column - 1].equals("*")) {
                    this.mineCounter++;
                }
            }
            if (column + 1 < this.column) {
                if (this.mineMap[row][column + 1].equals("*")) {
                    this.mineCounter++;
                }
            }
            if (row + 1 < this.row && column - 1 >= 0) {
                if (this.mineMap[row + 1][column - 1].equals("*")) {
                    this.mineCounter++;
                }
            }
            if (row + 1 < this.row) {
                if (this.mineMap[row + 1][column].equals("*")) {
                    this.mineCounter++;
                }
            }
            if (row + 1 < this.row && column + 1 < this.column) {
                if (this.mineMap[row + 1][column + 1].equals("*")) {
                    this.mineCounter++;
                }
            }
        } else {
            return -1;
        }
        return this.mineCounter;
    }

    private String[][] createArray() {

        this.mineMap = new String[this.row][this.column];
        for (int i = 0; i < this.mineMap.length; i++) {
            for (int j = 0; j < this.mineMap[i].length; j++) {
                this.mineMap[i][j] = "-";
            }
        }
        return this.mineMap;
    }

    private String[][] createMineField(String[][] mineFree) {
        this.mineMap = mineFree;
        int randomRow = random.nextInt(0, mineFree.length);
        int randomColumn = random.nextInt(0, mineFree.length);
        int counter = 0;
        while (randomRow < mineFree.length && randomColumn < mineFree[0].length) {
            if (counter != mineNumbers()) {
                counter++;
                this.mineMap[randomRow][randomColumn] = "*";
            } else {
                break;
            }
            randomRow = random.nextInt(0, mineFree.length);
            randomColumn = random.nextInt(0, mineFree.length);
        }
        return this.mineMap;
    }
    public void printArray(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
    private int mineNumbers() {
        int mineNumber = (this.row * this.column) / 4;
        return mineNumber;
    }

}