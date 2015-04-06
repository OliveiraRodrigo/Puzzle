package puzzle;

import java.util.ArrayList;
import java.util.Arrays;

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
    
    public boolean enqueue(/*String[]*/ArrayList<String> directions){
        qLast++;
        //queue.add(directions/*.clone()*/);
        //String[] newArray = new String[directions.size()]; 
        //directions.toArray(newArray);
        //queue.add(newArray);
        queue.add(directions.toArray(new String[directions.size()]));
        //System.out.println("Enfileirei: "+Arrays.toString(queue.get(1)));
        return true;
    }
    
    public /*String[]*/ArrayList<String> dequeue(){
        /*String[]*/ArrayList<String> directions = new ArrayList();
        //directions = queue.get(0);
        directions.addAll(Arrays.asList(queue.get(1)));
        if(qLast > 0){
            queue.remove(1);
            qLast--;
            return directions/*.clone()*/;
        }
        return directions;
        //return queue.get(0);
    }
    
    public boolean isEmpty(){
        if(qLast == 0){
            return true;
        }
        return false;
    }
}
