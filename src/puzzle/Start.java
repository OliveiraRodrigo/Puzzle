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
        
        Puzzle runnable = new Puzzle();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        
    }
    
}
