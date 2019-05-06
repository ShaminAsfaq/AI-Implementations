package NQueen;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by shamin on 10/5/18.
 */
public class HillClimbingNQueen {
    private int numberOfQueens;

    public HillClimbingNQueen(int numberOfQueens) {
        this.numberOfQueens = numberOfQueens;
    }
    public HillClimbingNQueen() {
    }

    public void setNumberOfQueens(int numberOfQueens) {
        this.numberOfQueens = numberOfQueens;
    }

    public void SolveNQueen() {
        int globalDimension = this.numberOfQueens;

//        Timer starts
        long start = System.currentTimeMillis();

        int[][] board = new int[globalDimension][globalDimension];
        int[] flag = new int[(globalDimension * globalDimension)];

        for (int r = 0; r < globalDimension; r++) {
            for (int col = 0; col < globalDimension; col++) {
                int random = (int) Math.floor(Math.random() * (globalDimension - 1));
                board[random][r] = 1;
                break;
            }
        }

        int totalIterations = 0;

        int[][] heuristicTable = new int[globalDimension][globalDimension];
        int lowestHeuristic = 100000;

        int[][] bestSolution = new int[globalDimension][globalDimension];
        int[][] newBoard = new int[globalDimension][globalDimension];

        for (int i = 0; i < globalDimension; i++) {
            for (int j = 0; j < globalDimension; j++) {
                bestSolution[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < globalDimension; i++) {
            for (int j = 0; j < globalDimension; j++) {
                newBoard[i][j] = board[i][j];
            }
        }

        while (lowestHeuristic != 0) {
            ++totalIterations;
            for (int i = 0; i < globalDimension; i++) {
                for (int j = 0; j < globalDimension; j++) {
                    newBoard[i][j] = bestSolution[i][j];
                }
            }
            
            for (int row = 0; row < globalDimension; row++) {
                for (int col = 0; col < globalDimension; col++) {
                    if (newBoard[row][col] == 1) {
                        for (int temp = 0; temp < globalDimension; temp++) {
                            if (temp == row) continue;
                            newBoard[temp][col] = 1;
                            newBoard[row][col] = 0;
                            int tempHeuristic = findHeuristic(newBoard);
                            heuristicTable[temp][col] = tempHeuristic;

                            if (tempHeuristic < lowestHeuristic) {
                                lowestHeuristic = tempHeuristic;
                                for (int i = 0; i < globalDimension; i++) {
                                    for (int j = 0; j < globalDimension; j++) {
                                        bestSolution[i][j] = newBoard[i][j];
                                    }
                                }

                                heuristicTable[row][col] = tempHeuristic;
                            }
                            newBoard[temp][col] = 0;
                            newBoard[row][col] = 1;
                        }
                    }                    
                }
            }

            ++flag[lowestHeuristic];
            if (flag[lowestHeuristic] == 5) {
                for (int it = 0; it < (globalDimension * globalDimension); it++) {
                    flag[it] = 0;
                }
                totalIterations = 0;
                lowestHeuristic = 100000;

                for (int r = 0; r < globalDimension; r++) {
                    for (int col = 0; col < globalDimension; col++) {
                        board[r][col] = 0;
                        heuristicTable[r][col] = 0;
                    }
                }

                for (int r = 0; r < globalDimension; r++) {
                    for (int col = 0; col < globalDimension; col++) {
                        int random = (int) Math.floor(Math.random() * (globalDimension - 1));
                        board[random][r] = 1;
                        break;
                    }
                }

                for (int i = 0; i < globalDimension; i++) {
                    for (int j = 0; j < globalDimension; j++) {
                        bestSolution[i][j] = board[i][j];
                    }
                }
            }
        }

        System.out.println(globalDimension + " Queens in their positions:");
        System.out.println("--------------------------------------");
        for (int[] value : bestSolution) {
            for (int denim : value) {
                System.out.print((denim == 1) ? "Q" : ".");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------");

        long end = System.currentTimeMillis();

        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
    }

    private int findHeuristic(int[][] board) {
        int globalDimension = this.numberOfQueens;
        int foundHeuristic = 0;
        for (int row = 0; row < globalDimension; row++) {
            for (int col = 0; col < globalDimension; col++) {
                if (board[row][col] == 1) {
//                    System.out.println("This is the ROW: " + row + " and COLUMN: " + col);
                    for (int rc = col + 1; rc < globalDimension; rc++) {
                        if (board[row][rc] == 1) {
                            ++foundHeuristic;
//                            System.out.println("    --> Found RIGHT one in: " + row + "," + rc);
                        }
                    }
                    for (int cr = row + 1; cr < globalDimension; cr++) {
                        if (board[cr][col] == 1) {
                            ++foundHeuristic;
//                            System.out.println("    --> Found DOWN one in: " + cr + "," + col);
                        }
                    }
                    for (int rc = col + 1, cr = row + 1; rc < globalDimension & cr < globalDimension; rc++, cr++) {
                        if (board[cr][rc] == 1) {
//                            System.out.println("    --> Found DIAGONAL one in: " + cr + "," + rc);
                            ++foundHeuristic;
                        }
                    }
                    for (int rc = col + 1, cr = row - 1; rc < globalDimension & cr >= 0; cr--, rc++) {
                        if (board[cr][rc] == 1) {
//                            System.out.println("    --> Found RIGHT DIAGONAL one in: " + cr + "," + rc);
                            ++foundHeuristic;
                        }
                    }
                }
            }
        }
        return foundHeuristic;
    }
}
