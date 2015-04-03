package puzzle;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Rodrigo
 */
public class Puzzle implements Runnable {
    
    private int numRows;
    private int numCols;
    private int numPieces;
    private int puzzle[][];
    private ArrayList<Integer> pieces;
    
    public void setPuzzle(){
        
        numRows = 3;
        numCols = 3; //digitar quantidades em campos na tela
                     //minimos 2, cada
        //numPieces = numRows * numCols;
                     //a peca zero sera a posicao vazia
        
        puzzle = new int[numRows][numCols];
        pieces = new ArrayList<>();
        
        int p = 0;
        for(int r=0; r<numRows; r++){
            for(int c=0; c<numCols; c++){
                pieces.add(p);
                puzzle[r][c] = pieces.get(p);
                p++;
            }
        }
    }
    
    public int[][] getPuzzle(){
        return puzzle;
    }
    
    public void shufflePuzzle(){
        Collections.shuffle(pieces);
        int p = 0;
        for(int r=0; r<numRows; r++){
            for(int c=0; c<numCols; c++){
                puzzle[r][c] = pieces.get(p);
                p++;
            }
        }
    }
    
    public void printPuzzle(){
        int p = 0;
        for(int r=0; r<numRows; r++){
            for(int c=0; c<numCols; c++){
                //puzzle[r][c] = pieces.get(p);
                //p++;
                if(puzzle[r][c] == 0){
                    System.out.print("   ");
                }
                else{
                    System.out.print("[" + puzzle[r][c] + "]");
                }
            }
            System.out.println();
        }
    }
    
    public int[] getFreePosition(){
        int[] freePosition = new int[2];
        for(int r=0; r<numRows; r++){
            for(int c=0; c<numCols; c++){
                if(puzzle[r][c] == 0){
                    freePosition[0] = r;
                    freePosition[1] = c;
                    return freePosition;
                }
            }
        }
        freePosition[0] = 0;
        freePosition[1] = 0;
        return freePosition;
    }
    
    public boolean move(String direction){
        int r, c;
        r = getFreePosition()[0];
        c = getFreePosition()[1];
        switch (direction){
            
            case "up":
                if(r+1 < numRows){
                    puzzle[r]  [c] = puzzle[r+1][c];
                    puzzle[r+1][c] = 0;
                    return true;
                }
                return false;
            case "down":
                if(r-1 >= 0){
                    puzzle[r]  [c] = puzzle[r-1][c];
                    puzzle[r-1][c] = 0;
                    return true;
                }
                return false;
            case "left":
                if(c+1 < numCols){
                    puzzle[r][c]   = puzzle[r][c+1];
                    puzzle[r][c+1] = 0;
                    return true;
                }
                return false;
            case "right":
                if(c-1 >= 0){
                    puzzle[r][c]   = puzzle[r][c-1];
                    puzzle[r][c-1] = 0;
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
    
    @Override
    public void run() {
        
        setPuzzle();
        printPuzzle();
        shufflePuzzle();
        System.out.println("");
        printPuzzle();
        System.out.println("");
    /*    
        System.out.println(
                "{"+getFreePosition()[0]+
                ","+getFreePosition()[1]+"}"
        );
    */    
        System.out.println(move("up"));
        System.out.println(move("left"));
        System.out.println(move("down"));
        System.out.println(move("right"));
        printPuzzle();
    }
    
}
