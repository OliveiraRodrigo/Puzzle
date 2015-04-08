package puzzle;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Rodrigo
 */
public class Tree implements Runnable {
    
    Puzzle   initPuzzle;
    Puzzle   currPuzzle;
    Puzzle   goalPuzzle;
    Stack    aStack;
    Queue    aQueue;
    ArrayList<Character> directions;
    
    public Tree(int rows, int cols){
        
        initPuzzle = new Puzzle(rows, cols);
        initPuzzle.shufflePuzzle();
        
        currPuzzle = initPuzzle.clonePuzzle();
        
        goalPuzzle = new Puzzle(rows, cols);
        
        aStack = new Stack();
        aQueue = new Queue();
        directions = new ArrayList<>();
        directions.add('u');
        directions.add('l');
        directions.add('r');
        directions.add('d');
        
        currPuzzle.printPuzzle();
        System.out.println();
    }
    
    /*public boolean depthSearch(int maxDepth){
        
        if(currPuzzle.equals(goalPuzzle)){
            return true;
        }
        for(int d=0; d<4; d++){
            while(currPuzzle.move(directions[d])){
                aStack.push(directions[d]);
                if(currPuzzle.equals(goalPuzzle)){
                    return true;
                }
            }
            if(!"empty".equals(aStack.pop())){
                currPuzzle.move(directions[3-d]);//inverse move
            }
        }
        return false;
    }*/
    
    public ArrayList<Character> breadthSearch(){
        
        boolean[] effected;
        ArrayList<Character> path = new ArrayList<>();
        
        double numTests = 0.0;
        double maxTests = 4.0;
        double exp = 1.0;
        
        for(int d=0; d<4; d++){
            path.add(directions.get(d));
            aQueue.enqueue(path);
            path.remove(path.size()-1);
        }
        
        //while (!currPuzzle.isEqual(goalPuzzle)
        while (!currPuzzle.isSorted()
            && !aQueue.isEmpty()/*
            && !(maxTests > 500000)*/){
            
            path = aQueue.dequeue();
            
            effected = currPuzzle.move(path)/*)*/; //.clone() ???
            
            System.out.println(path);
            //currPuzzle.printPuzzle();
            //System.out.println();
        
            //if(currPuzzle.isEqual(goalPuzzle)){
            if(currPuzzle.isSorted()){
                System.out.println("oooopaaaaa");
                currPuzzle.printPuzzle();
                return path;
            }
            else{
                for(int d=0; d<path.size(); d++){
                    if(!effected[d])
                        path.remove(d);
                }
                
                
                if(numTests >= maxTests){
                    maxTests = Math.pow(4, exp++);
                    numTests = 0;
                    System.out.println(maxTests);
                }
                if(numTests < maxTests){
                numTests++;
                
                
                for(int d=0; d<4; d++){
                    if(path.size()>0){
                        if(!path.get(path.size()-1).equals(directions.get(3-d))){
                            path.add(directions.get(d));
                            aQueue.enqueue(path);
                            path.remove(path.size()-1);
                        }
                    }
                }
                }
                currPuzzle = initPuzzle.clonePuzzle();
            }
        }
        currPuzzle.printPuzzle();
        return path;
    }
    
    @Override
    public void run() {
        
    }
}
