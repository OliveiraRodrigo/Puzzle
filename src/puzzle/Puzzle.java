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
    private int puzzle[][];
    private ArrayList<Integer> pieces;
    
    public void setPuzzle(){
        
        int numPieces, p;
        
        numRows = 3;
        numCols = 3;
        //digitar quantidades em campos na tela
        //minimos 2, cada
        numPieces = numRows * numCols;
        //a peca zero sera a posicao vazia
        
        puzzle = new int[numRows][numCols];
        pieces = new ArrayList<>();
        
        for(p=0; p<numPieces; p++){
            pieces.add(p);
        }
        Collections.shuffle(pieces);
        
        p = 0;
        for(int r=0; r<numRows; r++){
            for(int c=0; c<numCols; c++){
                puzzle[r][c] = pieces.get(p);
                p++;
                if(puzzle[r][c] == 0){
                    //if(numPieces>10){
                    //    System.out.print(" ");
                    //}
                    System.out.print("   ");
                }
                else{
                    //if(numPieces>10 && puzzle[r][c]<10){
                    //    System.out.print("[0" + puzzle[r][c] + "]");
                    //}
                    //else{
                        System.out.print("[" + puzzle[r][c] + "]");
                    //}
                }
            }
            System.out.println();
        }
    }
    
    @Override
    public void run() {
        
        setPuzzle();
    }
    
}
