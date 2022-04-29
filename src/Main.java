import java.util.Scanner;

public class Main {
    private static final int BOARD_SIZE = 9;
    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0, 7, 9, 0, 0, 5, 0},
                {3, 5, 2, 0, 0, 8, 0, 4, 0},
                {0, 0, 0, 0, 0, 0, 0, 8, 0},
                {0, 1, 0, 0, 7, 0, 0, 0, 4},
                {6, 0, 0, 3, 0, 1, 0, 0, 8},
                {9, 0, 0, 0, 8, 0, 0, 1, 0},
                {0, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 4, 0, 5, 0, 0, 8, 9, 1},
                {0, 8, 0, 0, 3, 7, 0, 0, 0}
        };
        printBoard(board);
        System.out.println("Type \"GO\" to Solve");
        Scanner scanner = new Scanner(System.in);
        if (scanner.next().equals("go")) {
            if (solve(board)) {
                System.out.println("Solved Successfully");
            } else {
                System.out.println("Unsolvable");
            }
            printBoard(board);
        }
    }

    public static void printBoard(int[][] board) {
        System.out.println("-------------------------");
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (j % 3 == 0) {
                    System.out.print("| ");
                }
                if (board[i][j] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println("|");
            if (i % 3 == 2) {
                System.out.println("-------------------------");
            }
        }
    }

    private static boolean solve(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (validNumber(board, i, j, num)) {
                            board[i][j] = num;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkBox(int[][] board, int row, int column, int number) {
        for (int i = row - (row % 3); i < row - (row % 3) + 3; i++) {
            for (int j = column - (column % 3); j < column - (column % 3) + 3; j++) {
                if (number == board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkRow(int[] row, int number) {
        for (int num : row) {
            if (num == number) {
                return false;
            }
        }
        return true;
    }
    private static boolean checkColumn(int[][] board, int column, int number) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][column] == number){
                return false;
            }
        }
        return true;
    }

    private static boolean validNumber(int[][] board, int row, int column, int number) {
        return checkBox(board, row, column, number) &&
                checkRow(board[row], number) && checkColumn(board, column, number);
    }

}
