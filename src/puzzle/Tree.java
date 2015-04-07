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
    ArrayList<String> directions;
    //String[] directions;
    
    public Tree(int rows, int cols){
        
        initPuzzle = new Puzzle(rows, cols);
        //initPuzzle.setPuzzle(rows, cols);
        initPuzzle.shufflePuzzle();
        
        currPuzzle = initPuzzle.clonePuzzle();
        
        goalPuzzle = new Puzzle(rows, cols);
        //goalPuzzle.setPuzzle(rows, cols);
        
        aStack = new Stack();
        aQueue = new Queue();
        directions = new ArrayList<>();
        directions.add("up");
        directions.add("left");
        directions.add("right");
        directions.add("down");
        /*directions = new String[4];
        directions[0] = "up";   //0
        directions[1] = "left"; //1
        directions[2] = "right";//2
        directions[3] = "down";  //3*/
        
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
    
    public ArrayList<String> breadthSearch(){
        
        boolean[] effected;
        ArrayList<String> path = new ArrayList<>();
        
        for(int d=0; d<4; d++){
            path.add(directions.get(d));
            aQueue.enqueue(path);
            path.remove(path.size()-1);
        }
        //while (!currPuzzle.isEqual(goalPuzzle)
        while (!currPuzzle.isSorted()
            && !aQueue.isEmpty()){
            path = aQueue.dequeue();
            if(path.size()>0){
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
                    for(int d=0; d<4; d++){
                        if(path.size()>0){
                            if(!path.get(path.size()-1).equals(directions.get(3-d))){
                                path.add(directions.get(d));
                                aQueue.enqueue(path);
                                path.remove(path.size()-1);
                            }
                        }
                    }
                    currPuzzle = initPuzzle.clonePuzzle();
                }
            }
            else{//path.size() == 0
                /*for(int d=0; d<4; d++){
                    path.add(directions.get(d));
                    aQueue.enqueue(path);
                    path.remove(path.size()-1);
                }*/
            }
        }
        currPuzzle.printPuzzle();
        //return false;
        return path;
    }
    
    @Override
    public void run() {
        
    }
}
