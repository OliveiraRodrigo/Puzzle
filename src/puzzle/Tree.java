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
    Screen   screen;
    ArrayList<Character> directions;
    
    public Tree(Puzzle inPuzzle){
        
        initPuzzle = inPuzzle.clonePuzzle();
        currPuzzle = inPuzzle.clonePuzzle();
        goalPuzzle = new Puzzle(
            inPuzzle.getNumRows(),
            inPuzzle.getNumCols());
        
        aStack = new Stack();
        aQueue = new Queue();
        screen = new Screen(currPuzzle);
        directions = new ArrayList<>();
        directions.add('U');//0
        directions.add('L');//1
        directions.add('R');//2
        directions.add('D');//3
    }
    
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
        
        while (!currPuzzle.isSorted()
            && !aQueue.isEmpty()){
            
            path = aQueue.dequeue();
            effected = currPuzzle.move(path)/*)*/; //.clone() ???
            
            //System.out.println(path);
            screen.render(currPuzzle);
        
            //if(currPuzzle.isEqual(goalPuzzle)){
            if(currPuzzle.isSorted()){
                System.out.println("Success!");
                return path;
            }
            else{
                for(int d=0; d<path.size(); d++){
                    if(!effected[d])
                        path.remove(d);
                }
                if(numTests >= maxTests){
                    maxTests = Math.pow(4, ++exp) - Math.pow(4, exp-1);
                    numTests = 0;
                }
                else{
                    numTests++;
                    for(int d=0; d<4; d++){
                        if(path.size()>0){
                            if(!path.get(path.size()-1).equals(directions.get(3-d))){
                                //if not reverse move
                                path.add(directions.get(d));
                                if(path.size() > exp)
                                    aQueue.enqueue(path);
                                path.remove(path.size()-1);
                            }
                        }
                    }
                }
                currPuzzle = initPuzzle.clonePuzzle();
            }
        }
        return path;
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
    
    @Override
    public void run() {
        
        currPuzzle.printPuzzle();
        currPuzzle.move(this.breadthSearch());
        currPuzzle.printPuzzle();
    }
}
