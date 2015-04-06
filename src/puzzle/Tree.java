package puzzle;

/**
 *
 * @author Rodrigo
 */
public class Tree implements Runnable {
    
    Puzzle   initPuzzle;
    Puzzle   currPuzzle;
    Puzzle   goalPuzzle;
    Stack    aStack;
    String[] directions;
    
    public void Tree(int rows, int cols){
        
        initPuzzle = new Puzzle();
        initPuzzle.setPuzzle(rows, cols);
        initPuzzle.shufflePuzzle();
        
        currPuzzle = initPuzzle.clonePuzzle();
        
        goalPuzzle = new Puzzle();
        goalPuzzle.setPuzzle(rows, cols);
        
        aStack = new Stack();
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
    
    public boolean breadthSearch(){
        return false;
    }
    
    @Override
    public void run() {
        
    }
}
