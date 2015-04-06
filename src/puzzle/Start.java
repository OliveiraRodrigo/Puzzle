package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Rodrigo
 */
public class Start {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Testes:
        /*Puzzle puzzle8 = new Puzzle();
        puzzle8.setPuzzle(3, 3);
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
        puzzle8.printPuzzle();*/
        
        //String[] path = new String[]{"up","left","right","down"};
        //ArrayList<String> path = new ArrayList();
        //path.add("up");path.add("left");path.add("right");path.add("down");
        //ArrayList<String> saida/* = new ArrayList()*/;
        //Queue fila = new Queue();
        //fila.enqueue(path);
        //saida = fila.dequeue();
        //System.out.println("$"+path[0]+"$");
        //System.out.println("#"+Arrays.toString(path)+"#");
        //System.out.println("$"+path.get(0)+"$");
        //System.out.println("#"+path+"#");
        //System.out.println("$"+saida.get(0)+"$");
        //System.out.println("#"+saida+"#");
        
        Tree tree = new Tree();
        System.out.println("asdf");
        System.out.println(
                tree.breadthSearch()
        );
        
        //Tree runnable = new Tree();
        //Thread thread1 = new Thread(runnable);
        //thread1.start();
        
    }
    
}
