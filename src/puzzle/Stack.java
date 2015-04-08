package puzzle;

import java.util.ArrayList;

/**
 *
 * @author Rodrigo
 */
public class Stack {
    
    private final ArrayList<Character> stack;
    private int stPointer;
    
    public Stack(){
        stPointer = 0;
        stack = new ArrayList<>();
        stack.add(' ');
    }
    
    public boolean push(char direction){
        stPointer++;
        stack.add(direction);
        return true;
    }
    
    public char pop(){
        char direction;
        if(stPointer > 0){
            direction = stack.get(stPointer);
            stack.remove(stPointer);
            stPointer--;
            return direction;
        }
        return stack.get(0);
    }
    
    public boolean isEmpty(){
        if(stPointer == 0){
            return true;
        }
        return false;
    }
}
