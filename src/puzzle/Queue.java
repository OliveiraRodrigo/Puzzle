package puzzle;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Rodrigo
 */
public class Queue {
    
    private final ArrayList<Character[]> queue;
    private int qLast;
    
    public Queue(){
        qLast = 0;
        queue = new ArrayList<>();
        queue.add(new Character[]{' '});
    }
    
    public boolean enqueue(ArrayList<Character> directions){
        qLast++;
        queue.add(directions.toArray(new Character[directions.size()]));
        return true;
    }
    
    public ArrayList<Character> dequeue(){
        ArrayList<Character> directions = new ArrayList<>();
        directions.addAll(Arrays.asList(queue.get(1)));
        if(qLast > 0){
            queue.remove(1);
            qLast--;
            return directions;
        }
        return directions;
    }
    
    public boolean isEmpty(){
        if(qLast == 0){
            return true;
        }
        return false;
    }
    
    public void clear(){
        queue.clear();
        qLast = 0;
    }
}
