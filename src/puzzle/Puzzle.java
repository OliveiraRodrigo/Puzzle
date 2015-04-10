package puzzle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Rodrigo
 */
public class Puzzle {
    
    private   final int numRows;
    private   final int numCols;
    private   final int numPieces;
    protected final int puzzle[][];
    private   final ArrayList<Integer> pieces;
    private         boolean   backTracking;
    protected       Puzzle    parentPuzzle;
    protected       boolean[] nextMove;
    
    public Puzzle(int rows, int cols){
        
        numRows = rows;
        numCols = cols;
                    //digitar quantidades em campos na tela
                    //minimos 2, cada
        numPieces = numRows * numCols;
                    //a peca zero sera a posicao vazia
        
        puzzle   = new int[numRows][numCols];
        pieces   = new ArrayList<>();
        
        int p = 0;
        for(int r=0; r<numRows; r++){
            for(int c=0; c<numCols; c++){
                pieces.add(p);
                puzzle[r][c] = pieces.get(p);
                p++;
            }
        }
        this.backTracking = false;
    }
    
    public int getNumRows(){
        return numRows;
    }
    
    public int getNumCols(){
        return numCols;
    }
    
    public int[][] getPuzzle(){
        return puzzle.clone();
    }
    
    public void setBackTracking(boolean bt){
        backTracking = bt;
        if(backTracking){
            parentPuzzle.backTracking = true;
            nextMove = new boolean[4];
            for(int n=0; n<4; n++){
                nextMove[n] = false;
                //0=U, 1=L, 2=R, 3=D}
            }
        }
    }
    
    public Puzzle clonePuzzle(){
        Puzzle clone = new Puzzle(numRows, numCols);
        for(int r=0; r<numRows; r++){
            for(int c=0; c<numCols; c++){
                clone.puzzle[r][c]
                =this.puzzle[r][c];
            }
        }
        if(backTracking){
            clone.setBackTracking(true);
            clone.parentPuzzle = this.parentPuzzle;
            clone.nextMove = this.nextMove.clone();
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
    
    public boolean isSorted(){
        int[] array;
        array = new int[numPieces+1];
        array[0] = 0;
        int i = 1;
        for(int r=0; r<numRows; r++){
            for(int c=0; c<numCols; c++){
                if(puzzle[r][c] != 0){
                    array[i] = puzzle[r][c];
                    if(array[i] < array[i-1]){
                        return false;
                    }
                    i++;
                }
            }
        }
        return true;
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
        if(isSorted()){
            shufflePuzzle();
        }
    }
    
    public void printPuzzle(){
        for(int r=0; r<numRows; r++){
            for(int c=0; c<numCols; c++){
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
    
    public Color[][] showPuzzle(Color boxColor){
        if(this.isSorted()){
            boxColor = Color.GREEN;
        }
        Color[][] matrix = new Color[numRows][numCols];
        for (int i=0; i<numRows; i++) {
            for (int j=0; j<numCols; j++) {
                if(puzzle[i][j] == 0){
                    matrix[i][j] = Color.BLACK;
                }
                else{
                    matrix[i][j] = boxColor;
                }
            }
        }
        return matrix;
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
        return new int[]{0,0};
    }
    
    //Sem backTracking
    public boolean move(char direction){
        int r, c;
        r = getFreePosition()[0];
        c = getFreePosition()[1];
        switch (direction){
            
            case 'U':
                if(r+1 < numRows){
                    puzzle[r]  [c] = puzzle[r+1][c];
                    puzzle[r+1][c] = 0;
                    return true;
                }
                return false;
            case 'D':
                if(r-1 >= 0){
                    puzzle[r]  [c] = puzzle[r-1][c];
                    puzzle[r-1][c] = 0;
                    return true;
                }
                return false;
            case 'L':
                if(c+1 < numCols){
                    puzzle[r][c]   = puzzle[r][c+1];
                    puzzle[r][c+1] = 0;
                    return true;
                }
                return false;
            case 'R':
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
    
    //Usando backTracking
    public boolean move(char direction, char b){
        int r, c;
        parentPuzzle = this.clonePuzzle();
        r = getFreePosition()[0];
        c = getFreePosition()[1];
        switch (direction){
            
            case 'U':
                if(r+1 < numRows){
                    puzzle[r]  [c] = puzzle[r+1][c];
                    puzzle[r+1][c] = 0;
                    parentPuzzle.nextMove[0] = true;
                    return true;
                }
                return false;
            case 'D':
                if(r-1 >= 0){
                    puzzle[r]  [c] = puzzle[r-1][c];
                    puzzle[r-1][c] = 0;
                    parentPuzzle.nextMove[3] = true;
                    return true;
                }
                return false;
            case 'L':
                if(c+1 < numCols){
                    puzzle[r][c]   = puzzle[r][c+1];
                    puzzle[r][c+1] = 0;
                    parentPuzzle.nextMove[1] = true;
                    return true;
                }
                return false;
            case 'R':
                if(c-1 >= 0){
                    puzzle[r][c]   = puzzle[r][c-1];
                    puzzle[r][c-1] = 0;
                    parentPuzzle.nextMove[2] = true;
                    return true;
                }
                return false;
            default:
                return false;
        }
    }
    public boolean[] move(ArrayList<Character> directions){
        int size;
        size = directions.size();
        boolean effected[] = new boolean[size];
        int i = 0;
        while(i < size){
            effected[i] = move(directions.get(i));
            i++;
        }
        return effected;
    }
    
    public boolean isEqual(Puzzle test){
        if(numRows == test.getNumRows() &&
           numCols == test.getNumCols()){
            for(int r=0; r<numRows; r++){
                for(int c=0; c<numCols; c++){
                    if(puzzle[r][c] != test.getPuzzle()[r][c]){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
