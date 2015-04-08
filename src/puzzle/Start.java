package puzzle;

/**
 *
 * @author Rodrigo
 */
public class Start {
    
    public static void main(String[] args) {
        
        Puzzle puzzle8 = new Puzzle(2, 3);
        puzzle8.shufflePuzzle();
        
        Tree   tree    = new Tree(puzzle8);
        
        Thread thread1 = new Thread(tree);
        thread1.start();
    }
    
}
