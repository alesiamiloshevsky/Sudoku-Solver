/*
    Backtracking Algorithm
        1. Find some empty space
        2. Attempt to place the digits 1-9 in that space
        3. Check if that digit is valid in the current spot based on the current board
        4.
            a. If the digit is valid, recursively attempt to fill the board using steps 1-3
            b. If it is not valid, reset the square you just filled and go back to the previous step
        5. Once the board if full by the definition of this algorithm we have found a solution.
 */
public class SudokuSolver {

        public static void main(String[] args) {
            int board[][] = { { 7,8,0,4,0,0,1,2,0 },
                    { 6,0,0,0,7,5,0,0,9 },
                    { 0,0,0,6,0,1,0,7,8 },
                    { 0,0,7,0,4,0,2,6,0 },
                    { 0,0,1,0,5,0,9,3,0 },
                    { 9,0,4,0,6,0,0,0,5 },
                    { 0,7,0,3,0,0,0,1,2 },
                    { 1,2,0,0,0,7,4,0,0 },
                    { 0,4,9,2,0,6,0,0,7 }
            };
            solver(board);
        }
        public static int[][] solver(int[][] board) {
            printBoard(board);
            solve(board);
            printBoard(board);
            return board;
        }

        // prints the grid
        public static void printBoard(int[][] board) {
            for (int i = 0; i < board.length; i++) {
                if (i % 3 == 0 && i != 0) {
                    System.out.println("---------------------");
                }
                for (int j = 0; j < board.length; j++) {
                    if (j % 3 == 0 && j != 0) {
                        System.out.print("| ");
                    }
                    if (j == 8) {
                        System.out.println(board[i][j]);
                    } else {
                        System.out.print(board[i][j] + " ");
                    }
                }
            }
            System.out.println("\n");
        }

        public static boolean solve(int[][] board) {
            int[] emptyCell = findEmpty(board);
            if (emptyCell == null)
                return true;
            int row = emptyCell[0];
            int column = emptyCell[1];
            for (int i = 1; i < 10; i++) {
                boolean valid = isValid(board, i, row, column);
                if (valid) {
                    board[row][column] = i;
                    if (solve(board))
                        return true;
                    board[row][column] = 0;
                }
            }
            return false;
        }

        // finds an empty cell within the grid
        public static int[] findEmpty(int[][] board) {
            int[] coordinate = new int[2];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 0) {
                        coordinate[0] = i; // row
                        coordinate[1] = j; // column
                        return coordinate;
                    }
                }
            }
            return null;
        }

        public static boolean isValid(int[][] board, int num, int row, int column) {
            // check if we find the same num in the row, return false
            for (int j = 0; j < board.length; j++) {
                if (board[row][j] == num && column != j) {
                    return false;
                }
            }

            // check if we find the same num in the column, return false
            for (int i = 0; i < board.length; i++) {
                if (board[i][column] == num && row != i) {
                    return false;
                }
            }

            // check if we find the same num in the box, return false
            int rowBox = row - row % 3;
            int columnBox = column - column % 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i + rowBox][j + columnBox] == num && board[row][column] != num)
                        return false;
                }
            }

            return true;
        }
}
