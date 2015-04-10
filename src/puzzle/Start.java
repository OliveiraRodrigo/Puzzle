package puzzle;

/**
 *
 * @author Rodrigo
 */
public class Start {
    
    public static void main(String[] args) {
        
        Puzzle puzzle8 = new Puzzle(3, 3);
        puzzle8.setBackTracking(true);
        puzzle8.shufflePuzzle();
        
        Tree   tree    = new Tree(puzzle8);
        
        Thread thread1 = new Thread(tree);
        thread1.start();
        
        //Testes:
        /*puzzle8.printPuzzle();
        System.out.println();
        System.out.println("U "+puzzle8.nextMove[0]);
        System.out.println("L "+puzzle8.nextMove[1]);
        System.out.println("R "+puzzle8.nextMove[2]);
        System.out.println("D "+puzzle8.nextMove[3]);
        
        puzzle8.move('U');
        puzzle8.printPuzzle();
        System.out.println();
        System.out.println("U "+puzzle8.parentPuzzle.nextMove[0]);
        System.out.println("L "+puzzle8.parentPuzzle.nextMove[1]);
        System.out.println("R "+puzzle8.parentPuzzle.nextMove[2]);
        System.out.println("D "+puzzle8.parentPuzzle.nextMove[3]);
        
        puzzle8.move('L');
        puzzle8.printPuzzle();
        System.out.println();
        System.out.println("U "+puzzle8.parentPuzzle.nextMove[0]);
        System.out.println("L "+puzzle8.parentPuzzle.nextMove[1]);
        System.out.println("R "+puzzle8.parentPuzzle.nextMove[2]);
        System.out.println("D "+puzzle8.parentPuzzle.nextMove[3]);
        
        puzzle8.move('R');
        puzzle8.printPuzzle();
        System.out.println();
        System.out.println("U "+puzzle8.parentPuzzle.nextMove[0]);
        System.out.println("L "+puzzle8.parentPuzzle.nextMove[1]);
        System.out.println("R "+puzzle8.parentPuzzle.nextMove[2]);
        System.out.println("D "+puzzle8.parentPuzzle.nextMove[3]);
        
        puzzle8.move('D');
        puzzle8.printPuzzle();
        System.out.println();
        System.out.println("U "+puzzle8.parentPuzzle.nextMove[0]);
        System.out.println("L "+puzzle8.parentPuzzle.nextMove[1]);
        System.out.println("R "+puzzle8.parentPuzzle.nextMove[2]);
        System.out.println("D "+puzzle8.parentPuzzle.nextMove[3]);
        
        System.out.println("Parents:");
        puzzle8 = puzzle8.parentPuzzle;
        puzzle8.printPuzzle();
        System.out.println();
        
        puzzle8 = puzzle8.parentPuzzle;
        puzzle8.printPuzzle();
        System.out.println();
        
        puzzle8 = puzzle8.parentPuzzle;
        puzzle8.printPuzzle();
        System.out.println();
        
        puzzle8 = puzzle8.parentPuzzle;
        puzzle8.printPuzzle();
        System.out.println();
        
        System.out.println("U "+puzzle8.nextMove[0]);
        System.out.println("L "+puzzle8.nextMove[1]);
        System.out.println("R "+puzzle8.nextMove[2]);
        System.out.println("D "+puzzle8.nextMove[3]);
        
        puzzle8.move('L');
        puzzle8.printPuzzle();
        System.out.println();
        System.out.println("U "+puzzle8.parentPuzzle.nextMove[0]);
        System.out.println("L "+puzzle8.parentPuzzle.nextMove[1]);
        System.out.println("R "+puzzle8.parentPuzzle.nextMove[2]);
        System.out.println("D "+puzzle8.parentPuzzle.nextMove[3]);
        
        puzzle8.move('U');
        puzzle8.printPuzzle();
        System.out.println();
        System.out.println("U "+puzzle8.parentPuzzle.nextMove[0]);
        System.out.println("L "+puzzle8.parentPuzzle.nextMove[1]);
        System.out.println("R "+puzzle8.parentPuzzle.nextMove[2]);
        System.out.println("D "+puzzle8.parentPuzzle.nextMove[3]);
        
        puzzle8.move('D');
        puzzle8.printPuzzle();
        System.out.println();
        System.out.println("U "+puzzle8.parentPuzzle.nextMove[0]);
        System.out.println("L "+puzzle8.parentPuzzle.nextMove[1]);
        System.out.println("R "+puzzle8.parentPuzzle.nextMove[2]);
        System.out.println("D "+puzzle8.parentPuzzle.nextMove[3]);
        
        puzzle8.move('R');
        puzzle8.printPuzzle();
        System.out.println();
        System.out.println("U "+puzzle8.parentPuzzle.nextMove[0]);
        System.out.println("L "+puzzle8.parentPuzzle.nextMove[1]);
        System.out.println("R "+puzzle8.parentPuzzle.nextMove[2]);
        System.out.println("D "+puzzle8.parentPuzzle.nextMove[3]);
        */
}
    
}
