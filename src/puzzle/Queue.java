package puzzle;

import java.util.ArrayList;

/**
 *
 * @author Rodrigo
 */
public class Queue {
    
    private final ArrayList<String[]> queue;
    private int qLast;
    
    public Queue(){
        qLast = 0;
        queue = new ArrayList<>();
        queue.add(new String[]{"empty"});
    }
    
    public boolean enqueue(String[] directions){
        qLast++;
        queue.add(directions);
        return true;
    }
    
    public String[] dequeue(){
        String[] directions;
        if(qLast > 0){
            directions = queue.get(0);
            queue.remove(0);
            qLast--;
            return directions;
        }
        return queue.get(0);
    }
    
    public boolean isEmpty(){
        if(qLast == 0){
            return true;
        }
        return false;
    }
}
