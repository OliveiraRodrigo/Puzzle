package puzzle;

/**
 *
 * @author Rodrigo
 */
public class Tree implements Runnable {
    
    Puzzle goalPuzzle;
    Puzzle aPuzzle;
    Stack aStack;
    String[] directions;
    
    public void Tree(int rows, int cols){
        
        goalPuzzle = new Puzzle();
        goalPuzzle.setPuzzle(rows, cols);
        aPuzzle = new Puzzle();
        aPuzzle.setPuzzle(rows, cols);
        aPuzzle.shufflePuzzle();
        aStack = new Stack();
        directions = new String[]{
            "up",   //0
            "left", //1
            "right",//2
            "down"  //3
        };
    }
    
    public boolean depthSearch(int maxDepth){
        
        if(aPuzzle.equals(goalPuzzle)){
            return true;
        }
        for(int d=0; d<4; d++){
            while(aPuzzle.move(directions[d])){
                aStack.push(directions[d]);
                if(aPuzzle.equals(goalPuzzle)){
                    return true;
                }
            }
            if(!"empty".equals(aStack.pop())){
                aPuzzle.move(directions[3-d]);//inverse move
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
