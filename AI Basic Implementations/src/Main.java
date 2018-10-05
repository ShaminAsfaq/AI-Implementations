import NQueen.HillClimbingNQueen;
import NQueen.RecursiveNQueen;

public class Main{
    public static void main(String[] args) {

//        HillClimbingNQueen hillClimbingNQueen = new HillClimbingNQueen();
        int queensToStartWith = 4;
        int queensToEndWith = 6;
//
//        for(int queen=queensToStartWith; queen<=queensToEndWith; queen++){
//            hillClimbingNQueen.setNumberOfQueens(queen);
//            hillClimbingNQueen.SolveNQueen();
//        }

        RecursiveNQueen recursiveNQueen = new RecursiveNQueen();
        for(int queen=queensToStartWith; queen<=queensToEndWith; queen++) {
            recursiveNQueen.setNumberOfQueens(queen);
            recursiveNQueen.SolveNQueen();
        }

    }
}


