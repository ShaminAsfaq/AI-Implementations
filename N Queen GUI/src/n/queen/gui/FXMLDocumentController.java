/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package n.queen.gui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author shamin
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private AnchorPane anchorPane;    
    private GridPane gridPane;
    @FXML
    private TextField numberOfQueenField;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        numberOfQueenField.setText("0");
        gridPane = new GridPane();
        anchorPane.getChildren().add(gridPane);
    }
    
    public void SolveNQueen(int numberOfQueens) {
        
        try {
            File black = new File("black.png");
            File white = new File("white.png");
            gridPane = new GridPane();
            anchorPane.getChildren().remove(0);
            anchorPane.getChildren().add(gridPane);
            
            Image image;
            ImageView imageView;
            for(int rr= 0; rr<numberOfQueens; rr++) {                                        
                for(int cc= 0; cc<numberOfQueens; cc++) {
                    if((rr+cc)%2==0)
                        image = new Image(white.toURI().toURL().toString(), 40, 40, false, false);
                    else image = new Image(black.toURI().toURL().toString(), 40, 40, false, false);
                    imageView = new ImageView(image);
                    gridPane.add(imageView, rr, cc);
                }
           }
        } catch (MalformedURLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
        int globalDimension = numberOfQueens;

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
                            int tempHeuristic = findHeuristic(newBoard, numberOfQueens);
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

        File queen = new File("queen.png");
        File black = new File("black.png");
        File white = new File("white.png");
        File queen_white = new File("queen_white.png");
        File queen_black = new File("queen_black.png");
        
        Image image;
        ImageView imageView;
        try {
            for(int rr= 0; rr<numberOfQueens; rr++) {                                        
                for(int cc= 0; cc<numberOfQueens; cc++) {
                    if((rr+cc)%2==0)
                        image = new Image(white.toURI().toURL().toString(), 40, 40, false, false);
                    else image = new Image(black.toURI().toURL().toString(), 40, 40, false, false);
                    
                    if(bestSolution[rr][cc]==1){
                        if((rr+cc)%2==0)
                            image = new Image(queen_white.toURI().toURL().toString(), 40, 40, false, false);
                        else image = new Image(queen_black.toURI().toURL().toString(), 40, 40, false, false);
                    }
                    imageView = new ImageView(image);
                    gridPane.add(imageView, rr, cc);
                }
            }
        } catch (MalformedURLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }                                

        long end = System.currentTimeMillis();

        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
    }

    private int findHeuristic(int[][] board, int numberOfQueens) {
        int globalDimension = numberOfQueens;
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

    @FXML
    private void handelSolveNQueenAction(ActionEvent event) {
        int numberOfQueens = Integer.parseInt(numberOfQueenField.getText());
        SolveNQueen(numberOfQueens);
    }

    
}
