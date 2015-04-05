package puzzle;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Rodrigo
 */
public class Puzzle {
    
    private int numRows;
    private int numCols;
    private int numPieces;
    private int puzzle[][];
    private ArrayList<Integer> pieces;
    
    public void setPuzzle(int rows, int cols){
        
        numRows = rows;
        numCols = cols;
                    //digitar quantidades em campos na tela
                    //minimos 2, cada
        numPieces = numRows * numCols;
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
    
    public int[][] clonePuzzle(){
        int[][] clone = new int[numRows][numCols];
        for(int r=0; r<numRows; r++){
            for(int c=0; c<numCols; c++){
                clone[r][c] = puzzle[r][c];
            }
        }
        return clone;
    }
    
    public boolean isValid(){
        int count = 0;
        for(int p=0; p<numPieces-1; p++){
            if(pieces.get(p) != 0){
                for(int q=p+1; q<numPieces; q++){
                    if(pieces.get(q) != 0){
                        if(pieces.get(q) < pieces.get(p)){
                            count++;
                        }
                    }
                }
            }
        }
        if(count%2 == 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void shufflePuzzle(){
        
        do{
            Collections.shuffle(pieces);
        }
        while(!isValid());
        
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
    
}
