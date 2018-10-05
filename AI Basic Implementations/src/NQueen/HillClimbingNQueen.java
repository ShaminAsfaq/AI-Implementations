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

//         Time delay to watch the process output
//        int conspiracy = 1000000000;
//        int theory = 1000000000;
//        int intact = 1000000000;
//        int together = 1000000000;
//        while (conspiracy >= 0) {
//            while (theory >= 0) {
//                while(intact >= 0){
//                    while(together >= 0){
//                        --together;
//                    }
//                    --intact;
//                }
//                --theory;
//            }
//            --conspiracy;
//        }

//        Timer starts
        long start = System.currentTimeMillis();

        int[][] board = new int[globalDimension][globalDimension];
        int[] flag = new int[(globalDimension * globalDimension)];

//        System.out.println("--------------------------------------");
//        System.out.println("EMPTY BOARD");
//        System.out.println("--------------------------------------");
//        for (int[] value : board) {
//            for (int denim : value) {
//                System.out.print(denim + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------------------------");

//        for (int r = 0; r < globalDimension; r++) {
//            for (int col = 0; col < globalDimension; col++) {
//                board[r][col] = 0;
//            }
//        }

        for (int r = 0; r < globalDimension; r++) {
            for (int col = 0; col < globalDimension; col++) {
                int random = (int) Math.floor(Math.random() * (globalDimension - 1));
//                        System.out.println(" ////////////////// " + random);
                board[random][r] = 1;
                break;
            }
        }


//        System.out.println("--------------------------------------");
//        System.out.println("First Random BOARD");
//        System.out.println("--------------------------------------");
//        for (int[] value : board) {
//            for (int denim : value) {
//                System.out.print(denim + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------------------------");

        int totalIterations = 0;

//        int heuristic = findHeuristic(board);
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

//            if (lowestHeuristic == 0) {
//                System.out.println("Current ITERATION " + totalIterations);
//                System.out.println("Lowest heuristic found: " + lowestHeuristic);
//                System.out.println("--------------------------------------");
//                System.out.println("BEST solution so far:");
//                System.out.println("--------------------------------------");
//                for (int[] value : bestSolution) {
//                    for (int denim : value) {
//                        System.out.print(denim + " ");
//                    }
//                    System.out.println();
//                }
//                System.out.println("--------------------------------------");
//                System.out.println("--------------------------------------");
//                for (int[] value : heuristicTable) {
//                    for (int denim : value) {
//                        System.out.print(denim + " ");
//                    }
//                    System.out.println();
//                }
//                System.out.println("--------------------------------------");
//            }

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
//                                bestSolution[temp][col] = 1;
//                                bestSolution[row][col] = 0;
                                for (int i = 0; i < globalDimension; i++) {
                                    for (int j = 0; j < globalDimension; j++) {
                                        bestSolution[i][j] = newBoard[i][j];
                                    }
                                }

                                heuristicTable[row][col] = tempHeuristic;
//                                System.out.println(" ----> Lowest heuristic found: " + lowestHeuristic);
//                                System.out.println("--------------------------------------");
//                                System.out.println("----------------(BOARD)---------------");
//                                System.out.println("--------------------------------------");
//                                for (int[] value : bestSolution) {
//                                    for (int denim : value) {
//                                        System.out.print(denim + " ");
//                                    }
//                                    System.out.println();
//                                }
//                                System.out.println("--------------------------------------");
//                                System.out.println("--------------(HEURISTIC)-------------");
//                                System.out.println("--------------------------------------");
//                                for (int[] value : heuristicTable) {
//                                    for (int denim : value) {
//                                        System.out.print(denim + " ");
//                                    }
//                                    System.out.println();
//                                }
//                                System.out.println("--------------------------------------");
                            }
                            newBoard[temp][col] = 0;
                            newBoard[row][col] = 1;
                        }
                        //                    break;
                    }
                    //                break;
                }
            }

            ++flag[lowestHeuristic];
//                if (totalIterations == 5 && lowestHeuristic == 1) {
            if (flag[lowestHeuristic] == 5) {
                for (int it = 0; it < (globalDimension * globalDimension); it++) {
                    flag[it] = 0;
                }
//                if (flag) {
                totalIterations = 0;
                lowestHeuristic = 100000;

                for (int r = 0; r < globalDimension; r++) {
                    for (int col = 0; col < globalDimension; col++) {
                        board[r][col] = 0;
                        heuristicTable[r][col] = 0;
                    }
                }

//                System.out.println("--------------------------------------");
//                System.out.println("BOARD re-set");
//                System.out.println("--------------------------------------");
//                for (int[] value : board) {
//                    for (int denim : value) {
//                        System.out.print(denim + " ");
//                    }
//                    System.out.println();
//                }
//                System.out.println("--------------------------------------");

                for (int r = 0; r < globalDimension; r++) {
                    for (int col = 0; col < globalDimension; col++) {
                        int random = (int) Math.floor(Math.random() * (globalDimension - 1));
//                            System.out.println(" ////////////////// " + random);
                        board[random][r] = 1;
                        break;
                    }
                }

                for (int i = 0; i < globalDimension; i++) {
                    for (int j = 0; j < globalDimension; j++) {
                        bestSolution[i][j] = board[i][j];
                    }
                }

//                System.out.println("--------------------------------------");
//                System.out.println("BOARD randomized");
//                System.out.println("--------------------------------------");
//                for (int[] value : board) {
//                    for (int denim : value) {
//                        System.out.print(denim + " ");
//                    }
//                    System.out.println();
//                }
//                System.out.println("--------------------------------------");
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
