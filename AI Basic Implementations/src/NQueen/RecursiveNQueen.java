package NQueen;

/**
 * Created by shamin on 10/5/18.
 */
public class RecursiveNQueen {
    int numberOfQueens;

    public RecursiveNQueen(int numberOfQueens) {
        this.numberOfQueens = numberOfQueens;
    }

    public RecursiveNQueen() {
    }

    public void setNumberOfQueens(int numberOfQueens) {
        this.numberOfQueens = numberOfQueens;
    }

    public void SolveNQueen() {
        int[][] board = new int[this.numberOfQueens][this.numberOfQueens];

        boolean flag = OptimizeSolution(board, 0);
        if(!flag) System.out.println("No solution available");
        else {
            printBoard(board);
        }
    }

    private void printBoard(int[][] board) {
        System.out.println(this.numberOfQueens + " Queens in their positions:");
        System.out.println("--------------------------------------");
        for(int row=0; row<this.numberOfQueens; row++){
            for(int col=0; col<this.numberOfQueens; col++){
                System.out.print((board[row][col]==1)?"Q":".");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------");
    }

    private boolean OptimizeSolution(int[][] board, int col) {
        if(col>=this.numberOfQueens) return true;

        for(int row = 0; row<this.numberOfQueens; row++) {
            if(isValid(board, row, col)){
                board[row][col] = 1;
                if(OptimizeSolution(board, col+1)){
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    private boolean isValid(int[][] board, int row, int col) {

        for(int c = 0; c < col; c++ ){
            if(board[row][c]==1)
                return false;
        }

        for(int r = row, c = col; r >= 0 && c >= 0; r--, c--) {
            if(board[r][c]==1)
                return false;
        }

        for(int r = row, c = col; r < this.numberOfQueens && c >= 0; r++, c--){
            if(board[r][c]==1)
                return false;
        }

        return true;
    }


}

