package puzzle;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo
 */
public class Tree implements KeyListener, Runnable {
    
    Puzzle   initPuzzle;
    Puzzle   currPuzzle;
    Puzzle   goalPuzzle;
    Stack    aStack;
    Queue    aQueue;
    Screen   screen;
    ArrayList<Character> directions;
    boolean  next;
    
    public Tree(Puzzle inPuzzle){
        
        initPuzzle = inPuzzle.clonePuzzle();
        currPuzzle = inPuzzle.clonePuzzle();
        goalPuzzle = new Puzzle(
            inPuzzle.getNumRows(),
            inPuzzle.getNumCols());
        
        aStack = new Stack();
        aQueue = new Queue();
        //screen = new Screen();
        directions = new ArrayList<>();
        directions.add('U');//0
        directions.add('L');//1
        directions.add('R');//2
        directions.add('D');//3
        addKeyListener(this);
        next = false;
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
            //screen.render(currPuzzle);
        
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
        
        //currPuzzle.printPuzzle();
        //currPuzzle.move(this.breadthSearch());
        //currPuzzle.printPuzzle();
        
        screen = new Screen();
        screen.render(currPuzzle);
        ArrayList<Character> path = new ArrayList<>();
        path.addAll(this.breadthSearch());
        currPuzzle.printPuzzle();
        for(int d=0; d<path.size(); d++){
            while(!screen.next){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Tree.class.getName()).log(Level.SEVERE, null, ex);
                }
                screen.render(initPuzzle);
            }
            initPuzzle.move(path.get(d));
            screen.render(initPuzzle);
            screen.next = false;
            //timer();
        }
    }
    
    @Override
    public void keyPressed(KeyEvent evt) {

        switch(evt.getKeyCode()) {

            case KeyEvent.VK_SHIFT:
                //visual = !visual;
                break;
            case KeyEvent.VK_NUMPAD0:
                //desenhaGrade = !desenhaGrade;
                break;
            case KeyEvent.VK_ENTER:
                //novo = true;
                //this.setVisible(false);
                break;
            case KeyEvent.VK_CONTROL:
                //jogo1.pausado = !jogo1.pausado;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(1);
                break;
            case KeyEvent.VK_LEFT:
                //jogo1.formaAtual.praEsquerda();
                //jogo1.praLeft = true;
                break;
            case KeyEvent.VK_UP:
                //jogo1.formaAtual.gira();
                //jogo1.girou = true;
                break;
            case KeyEvent.VK_RIGHT:
                //jogo1.formaAtual.praDireita();
                //jogo1.praRight = true;
                break;
            case KeyEvent.VK_DOWN:
                //jogo1.formaAtual.praBaixo();
                break;
            case KeyEvent.VK_SPACE:
                //jogo1.usaTrunfo();
                next = true;
                System.out.println("qwe");
                break;
            case KeyEvent.VK_T:
                //jogo1.trollar();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
