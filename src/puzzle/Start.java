package puzzle;

/**
 *
 * @author Rodrigo
 */
public class Start {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Puzzle puzzle8 = new Puzzle();
        puzzle8.setPuzzle(3,3);
        //printPuzzle();
        //System.out.println();
        puzzle8.shufflePuzzle();
        puzzle8.printPuzzle();
        System.out.println();
        System.out.print(puzzle8.move("up")+" - ");
        System.out.print(puzzle8.move("left")+" - ");
        System.out.print(puzzle8.move("down")+" - ");
        System.out.print(puzzle8.move("right"));
        System.out.println();
        System.out.println();
        puzzle8.printPuzzle();
        
        //Tree runnable = new Tree();
        //Thread thread1 = new Thread(runnable);
        //thread1.start();
        
    }
    
}
