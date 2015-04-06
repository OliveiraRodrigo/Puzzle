package puzzle;

import java.util.ArrayList;

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
    String[] directions;
    
    public void Tree(int rows, int cols){
        
        initPuzzle = new Puzzle();
        initPuzzle.setPuzzle(rows, cols);
        initPuzzle.shufflePuzzle();
        
        currPuzzle = initPuzzle.clonePuzzle();
        
        goalPuzzle = new Puzzle();
        goalPuzzle.setPuzzle(rows, cols);
        
        aStack = new Stack();
        aQueue = new Queue();
        directions = new String[]{
            "up",   //0
            "left", //1
            "right",//2
            "down"  //3
        };
    }
    
    public boolean depthSearch(int maxDepth){
        
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
    }
    
    public String[] breadthSearch(){
        
        ArrayList<boolean[]> effected = new ArrayList<>();
        ArrayList<String []> solution = new ArrayList<>();
        
        for(int d=0; d<4; d++){
            aQueue.enqueue(new String[]{directions[d]});
        }
        while (!currPuzzle.equals(goalPuzzle)
            && !aQueue.isEmpty()){
            
            solution.add(aQueue.dequeue());
            if(solution.size()>0){
                //effected.addAll(0, currPuzzle.move(solution.get(solution.size()-1)));
                effected.add(currPuzzle.move(solution.get(solution.size()-1)));
                if(currPuzzle.equals(goalPuzzle)){
                    return solution.get(solution.size()-1);
                }
                else{
                    //for(int d=0; d<4; d++){
                        solution.add(directions);
                        aQueue.enqueue(solution);
                    //}
                    currPuzzle = initPuzzle.clonePuzzle();
                }
            }
            else{//solution == 0
                
            }
        }
        //return false;
    }
    
    @Override
    public void run() {
        
    }
}
