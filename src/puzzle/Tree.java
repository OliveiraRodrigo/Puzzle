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
    //ArrayList<String[]> directions;
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
        /*directions.add(new String[]{"up"});
        directions.add(new String[]{"left"});
        directions.add(new String[]{"right"});
        directions.add(new String[]{"down"});*/
        directions = new String[]{
            "up",   //0
            "left", //1
            "right",//2
            "down"  //3
        };        
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
        
        //ArrayList<boolean[]> effected = new ArrayList<>();
        boolean[] effected;
        ArrayList<String> path = new ArrayList<>();
        
        //path.addAll(Arrays.asList(directions));
        for(int d=0; d<4; d++){
            //System.out.println(d);
            //System.out.println(directions[d]);
            path.add(directions[d]);
            aQueue.enqueue(path);
            path.remove(d);
            //aQueue.enqueue(new String[]{directions[d]});
        }
        while (!currPuzzle.equals(goalPuzzle)
            && !aQueue.isEmpty()){
            
            path = aQueue.dequeue();
            System.out.println(path);
            if(path.size()>0){
                //effected.addAll(0, currPuzzle.move(solution.get(solution.size()-1)));
                /*effected.add(*/
                //currPuzzle.move(path.get(path.size()-1))/*)*/;
                //effected = new boolean[path.size()];
                effected = currPuzzle.move(path)/*)*/; //.clone() ???
                if(currPuzzle.equals(goalPuzzle)){
                    //return path.get(path.size()-1);
                    return path;
                }
                else{
                    for(int d=0; d<path.size(); d++){
                        if(!effected[d])
                            path.remove(d);
                    }
                    for(int d=0; d<4; d++){
                        if(!path.get(path.size()-1).equals(directions[3-d])){
                            path.add(directions[d]);
                            aQueue.enqueue(path);
                            path.remove(path.size()-1);
                        }
                    }
                    currPuzzle = initPuzzle.clonePuzzle();
                }
            }
            else{//path.size() == 0
                
            }
        }
        //return false;
        return path;
    }
    
    @Override
    public void run() {
        
    }
}
