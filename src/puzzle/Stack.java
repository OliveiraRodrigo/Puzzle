package puzzle;

import java.util.ArrayList;

/**
 *
 * @author Rodrigo
 */
public class Stack {
    //private int[] stack_;
    private final ArrayList<String> stack;
    private int stPointer;
    //private String direction_;
    
    public Stack(){
        stPointer = 0;
        stack = new ArrayList<>();
        stack.add("empty");
    }
    
    public boolean push(String direction){
        stPointer++;
        stack.add(direction);
        return true;
    }
    
    public String pop(){
        String direction;
        if(stPointer > 0){
            direction = stack.get(stPointer);
            stack.remove(stPointer);
            stPointer--;
            return direction;
        }
        return "empty";
    }
}
