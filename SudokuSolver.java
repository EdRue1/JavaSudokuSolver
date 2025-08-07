public class SudokuSolver {

    //save the length of the grid
    private static final int GRID_SIZE = 9;


    public static void main(String[] args) {

        //Current board to test
        int[][] board = {
            // {7, 0, 2, 0, 5, 0, 6, 0, 0},
            // {0, 0, 0, 0, 0, 3, 0, 0, 0},
            // {1, 0, 0, 0, 0, 9, 5, 0, 0},
            // {8, 0, 0, 0, 0, 0, 0, 9, 0},
            // {0, 4, 3, 0, 0, 0, 7, 5, 0},
            // {0, 9, 0, 0, 0, 0, 0, 0, 8},
            // {0, 0, 9, 7, 0, 0, 0, 0, 5},
            // {0, 0, 0, 2, 0, 0, 0, 0, 0},
            // {0, 0, 7, 0, 4, 0, 2, 0, 3} 
            {0, 9, 7, 0, 8, 0, 4, 0, 0},
            {0, 4, 0, 0, 0, 5, 7, 8, 0},
            {0, 6, 0, 0, 0, 7, 0, 1, 0},
            {9, 0, 6, 0, 7, 0, 0, 4, 3},
            {4, 0, 3, 2, 0, 6, 0, 0, 8},
            {8, 0, 1, 3, 0, 9, 0, 0, 0},
            {6, 8, 5, 1, 3, 0, 0, 0, 0},
            {0, 3, 4, 5, 9, 0, 2, 6, 0},
            {0, 1, 0, 0, 6, 0, 0, 0, 0} 
        };

        printBoard(board);

        if (solveBoard(board)) {
            System.out.println("Solved successfully");
        }
        else {
            System.out.println("Unsolvable board");
        }

        printBoard(board);

    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0){
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    //Checks if the number is in the row that is being tested
    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    //Checks if the number is in the column that is being tested
    private static boolean isNumberIColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    //Checks if the number is in the 3x3 square we are currently testing
    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    //This will check if the number is a valid placement
    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) && 
        !isNumberIColumn(board, number, column) && 
        !isNumberInBox(board, number, row, column);
    }

    //begin solving the board
    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) { //go through rows
            for (int column = 0; column < GRID_SIZE; column++) { //go through columns
                if (board[row][column] == 0) { //if the space is empty
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) { //for loop to test numbers 1-9
                        if (isValidPlacement(board, numberToTry, row, column)) { //check if it's a valid placement
                            board[row][column] = numberToTry; //place it

                            //recursively find where to fill in numbers, backtrack if something is wrong
                            if (solveBoard(board)) {
                                return true;
                            }
                            else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    //Can't solve this board with the current numberToTry
                    return false;
                }
            }
        }
        return true;
    }

}
