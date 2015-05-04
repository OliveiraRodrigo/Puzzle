package puzzle;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Rodrigo
 */
public class Tree implements Runnable {
    
    private       Puzzle inputPuzzle;
    private       Puzzle initPuzzle;
    private       Puzzle currPuzzle;
    private final Stack  aStack;
    private final Queue  aQueue;
    private       Screen screen;
    private final ArrayList<Character> directions;
    
    public Tree(Puzzle inPuzzle){
        
        initPuzzle   = inPuzzle.clonePuzzle();
        inputPuzzle  = inPuzzle.clonePuzzle();
        //currPuzzle   = inPuzzle.clonePuzzle();
        aStack       = new Stack();
        aQueue       = new Queue();
        directions   = new ArrayList<>();
        directions.add('U');//0
        directions.add('L');//1
        directions.add('R');//2
        directions.add('D');//3
    }
    
    public ArrayList<Character> breadthSearch(){
        
        boolean[] effected;
        boolean[] buttons = new boolean[2];
        boolean init = true;
        int counter = 0;
        ArrayList<Character> path = new ArrayList<>();
        initPuzzle.setBackTracking(false);
        currPuzzle.setBackTracking(false);
        
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
            counter++;
            
            //System.out.println(path);
            if(init){
                buttons = screen.render(counter, initPuzzle, initPuzzle, Color.lightGray);
            }
            if(screen.next){
                buttons = screen.render(counter, initPuzzle, currPuzzle, Color.lightGray);
                init = false;
            }
            
            if(!buttons[0]){
                counter = 0;
                return path;
            }
        
            if(currPuzzle.isSorted()){
                if(path.size()>min)
                    System.out.println(path.size());
                System.out.println("Tested States: "+counter);
                System.out.println("Success!");
                screen.render(counter, initPuzzle, currPuzzle, Color.orange);
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
                            //if not reverse move
                            path.add(directions.get(d));
                            if(path.size() >= min)
                                aQueue.enqueue(path);
                            path.remove(path.size()-1);
                        }
                    }
                }
                currPuzzle = initPuzzle.clonePuzzle();
            }
        }
        counter = 0;
        return path;
    }
    
    public ArrayList<Character> depthSearch(){
        
        boolean effected;
        boolean init = true;
        ArrayList<Character> path = new ArrayList<>();
        initPuzzle.setBackTracking(true);
        currPuzzle.setBackTracking(true);
        int level = 0;
        final int maxLevel = 22;
        
        aStack.push(initPuzzle);
        
        while (!currPuzzle.isSorted()
            && !aStack.isEmpty()){
//System.out.println("--1--");
            currPuzzle = aStack.getTop();
            //level--;
            
            boolean[] dir = new boolean[4];
            for(int i=0; i<4; i++){
                dir[i] = false;
            }
            for(int i=0; i<4; i++){
                int r;
                do{
                    r = new Random().nextInt(4);
//System.out.println("--"+directions.get(r)+"--");
                }
                while(dir[r]);
                dir[r] = true;
                
                //if(path.size()>0){
                //if(!path.get(path.size()-1).equals(directions.get(3-r))){
System.out.println(directions.get(r)+" : "+currPuzzle.nextMove[r]);
                if(!currPuzzle.nextMove[r]){
//System.out.println("--2--");
                    if(currPuzzle.move(directions.get(r), 'b')){
//System.out.println("--3--");
                        if(init){
                            screen.render(0,initPuzzle, initPuzzle, Color.lightGray);
                        }
                        if(screen.next){
                            screen.render(0,initPuzzle, currPuzzle, Color.lightGray);
                            init = false;
                        }
                        
                //if(path.size()>0){
                //if(!path.get(path.size()-1).equals(directions.get(3-r))){
                        level++;
                        path.add(directions.get(r));
                        System.out.println(path);
                //}
                //}
                        if(currPuzzle.isSorted()){
//System.out.println("--98--");
                            return path;
                        }
                        else{
                            if(level < maxLevel){
//System.out.println("--4--");
                                aStack.push(currPuzzle);
                            }
                            else{
                                //currPuzzle = currPuzzle.parentPuzzle;
                                //aStack.push(currPuzzle);
                                aStack.pop();
                                if(path.size()>0)
                                    path.remove(path.size()-1);
                                level--;
                            }
//System.out.println("--4.5--");
                            break;
                        }
                    }
                    else{
//System.out.println("--5--");
//System.out.println("****"+r+"****");
                        currPuzzle.nextMove[r] = true;
                        //currPuzzle = currPuzzle.parentPuzzle;
                        //if(path.size()>0)
                        //    path.remove(path.size()-1);
                        //level--;
                    }
                }
                else{
//System.out.println("--6--");
                    if(i==3){
//System.out.println("--7--");
                        //currPuzzle = currPuzzle.parentPuzzle;
                        aStack.pop();
                        if(path.size()>0)
                            path.remove(path.size()-1);
                        level--;
                    }
                    else{
                        //aStack.push(currPuzzle);
                    }
                }
                //}
                //}
            }
        }
//System.out.println("--99--");
        return path;
    }
    
    @Override
    public void run() {
        
        boolean[] buttons = new boolean[2];
        Color stopColor = Color.lightGray;
        screen = new Screen(initPuzzle.getNumRows(), initPuzzle.getNumCols());
        //buttons = screen.render(inputPuzzle, currPuzzle, Color.lightGray);
        ArrayList<Character> path = new ArrayList<>();
        
        while(true){
            
        //Puzzle inputPuzzle = initPuzzle.clonePuzzle();
        //initPuzzle = inputPuzzle.clonePuzzle();
        currPuzzle = inputPuzzle.clonePuzzle();
        
        while(!currPuzzle.isSorted()){
        
        initPuzzle = inputPuzzle.clonePuzzle();
        currPuzzle = inputPuzzle.clonePuzzle();
        
        do{
            buttons = screen.render(-1, inputPuzzle, currPuzzle, Color.lightGray);
            screen.next = true;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tree.class.getName())
                    .log(Level.SEVERE, null, ex);
            }
        }
        while(!buttons[0] && !buttons[1]);
        
        //initPuzzle = inputPuzzle.clonePuzzle();
        aQueue.clear();
        path.clear();
        if(buttons[0])
            path.addAll(this.breadthSearch());
        if(buttons[1])
            path.addAll(this.depthSearch());
        aQueue.clear();
        //aStack.clear();
        
        if(currPuzzle.isSorted()){
            stopColor = Color.orange;
        }
        else{
            stopColor = Color.lightGray;
        }
        
        initPuzzle.printPuzzle();
        System.out.println();
        currPuzzle.printPuzzle();
        
        }
        
        for(int d=0; d<path.size(); d++){
            while(!screen.next){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Tree.class.getName())
                        .log(Level.SEVERE, null, ex);
                }
                screen.render(-1, inputPuzzle, initPuzzle, stopColor);
            }
            initPuzzle.move(path.get(d));
            screen.render(-1, inputPuzzle, initPuzzle, stopColor);
            screen.next = false;
        }
        screen.buttons[0] = false;
        screen.buttons[1] = false;
        screen.next = false;
        do{
            buttons = screen.render(-1, inputPuzzle, initPuzzle, Color.lightGray);
            screen.next = true;
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tree.class.getName())
                    .log(Level.SEVERE, null, ex);
            }
        }
        while(!buttons[0] && !buttons[1]);
        }
    }
}
