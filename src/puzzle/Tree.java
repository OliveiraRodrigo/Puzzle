package puzzle;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;

/**
 *
 * @author Rodrigo
 */
public class Tree implements Runnable {
    
    Puzzle   initPuzzle;
    Puzzle   currPuzzle;
    Stack    aStack;
    Queue    aQueue;
    Screen   screen;
    ArrayList<Character> directions;
    
    public Tree(Puzzle inPuzzle){
        
        initPuzzle = inPuzzle.clonePuzzle();
        currPuzzle = inPuzzle.clonePuzzle();
        aStack = new Stack();
        aQueue = new Queue();
        directions = new ArrayList<>();
        directions.add('U');//0
        directions.add('L');//1
        directions.add('R');//2
        directions.add('D');//3
    }
    
    public ArrayList<Character> breadthSearch(){
        
        boolean[] effected;
        boolean init = true;
        ArrayList<Character> path = new ArrayList<>();
        
        double numTests = 0.0;
        double maxTests = 4.0;
        double exp = 1.0;
        
        for(int d=0; d<4; d++){
            path.add(directions.get(d));
            aQueue.enqueue(path);
            path.remove(path.size()-1);
        }
        
        int min = 0;
        while (!currPuzzle.isSorted()
            && !aQueue.isEmpty()){
            
            if((path.size()) > min){
                min = path.size();
                System.out.println(min);
            }
            do{
                path = aQueue.dequeue();
            }
            while(path.size() < min);
            effected = currPuzzle.move(path);
            
            //System.out.print(path.size()+" - ");
            //System.out.println(path);
            //screen.render(currPuzzle);
            if(init){
                screen.render(initPuzzle, Color.GRAY);
            }
            if(screen.next){
                screen.render(currPuzzle, Color.GRAY);
                init = false;
            }
        
            if(currPuzzle.isSorted()){
                System.out.println("Success!");
                return path;
            }
            else{
                for(int d=0; d<path.size(); d++){
                    if(!effected[d])
                        path.remove(d);
                }
                //if(numTests >= maxTests){
                //    maxTests = Math.pow(4, ++exp) - Math.pow(4, exp-1);
                //    numTests = 0;
                //}
                //else{
                //    numTests++;
                    for(int d=0; d<4; d++){
                        if(path.size()>0){
                            if(!path.get(path.size()-1).equals(directions.get(3-d))){
                                //if not reverse move
                                path.add(directions.get(d));
                                if(path.size() >= min)
                                    aQueue.enqueue(path);
                                path.remove(path.size()-1);
                            }
                        }
                    }
                //}
                currPuzzle = initPuzzle.clonePuzzle();
            }
        }
        return path;
    }
    
    /*public boolean depthSearch(int maxDepth){
        return false;
    }*/
    
    @Override
    public void run() {
        
        //currPuzzle.printPuzzle();
        //currPuzzle.move(this.breadthSearch());
        //currPuzzle.printPuzzle();
        
        screen = new Screen(initPuzzle.getNumRows(), initPuzzle.getNumCols());
        screen.render(currPuzzle, Color.darkGray);
        ArrayList<Character> path = new ArrayList<>();
        path.addAll(this.breadthSearch());
        initPuzzle.printPuzzle();
        System.out.println();
        currPuzzle.printPuzzle();
        for(int d=0; d<path.size(); d++){
            while(!screen.next){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Tree.class.getName()).log(Level.SEVERE, null, ex);
                }
                screen.render(initPuzzle, Color.orange);
            }
            initPuzzle.move(path.get(d));
            screen.render(initPuzzle, Color.orange);
            screen.next = false;
            //timer();
        }
    }
}
