class Solution {
    public boolean isSafe(char[][] board, int row, int col, int number) {
        // codition for the rows and the columns, that the number doesnt contain in row
        // and col wise.
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == (char) (number = '0')) {
                return false;
            }
            if (board[row][i] == (char) (number = '0')) {
                return false;
            }
        }
        // condition of the grid . to know the starting point of the grid
        int starting_row = (row / 3) * 3;
        int starting_col = (col / 3) * 3;

        // checking the matrix that the number will exist or not
        for (int i = starting_row; i < starting_row + 3; i++) {
            for (int j = starting_col; i < starting_col + 3; j++) {
                if (board[i][j] == (char) (number = '0')) {
                    return false;
                }
            }
        }
        return true;
    }

    // created a recursive function, which returns the boolean
    public boolean helper(char[][] board, int row, int col) {
        // cover all the cells
        if (row == board.length) {
            return true;
        }
        // In the next recursive call, what will be the new row and new column?
        int newrow = 0;
        int newcol = 0;
        if (col != board.length - 1) {
            newrow = row;
            newcol = col + 1;
        } else {
            newrow = row + 1;
            newcol = 0;
        }
        // if there is number exit , then call the next number
        if (board[row][col] != '-') {
            if (helper(board, newrow, newcol)) {
                return true;
            }
        } else {
            for (int i = 1; i <= 9; i++) {
                // if the number is doesn't exit , then it is safe that this number is correct
                // to filled
                if (isSafe(board, row, col, i)) {
                    board[row][col] = (char) (i + '0');// if it is safe we placed it
                    // call for other cells
                    if (helper(board, newrow, newcol)) {
                        return true;
                    } else {
                        board[row][col] = '-';// if it is wrong then try with other numbers
                    }
                }
            }
        }
        return false;
    }

    // in this fuction there is a board where character is written
    public void sudoko_sol(char[][] board) {

        helper(board, 0, 0);
    }
}