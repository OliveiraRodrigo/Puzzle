package puzzle;

import java.util.ArrayList;

/**
 *
 * @author Rodrigo
 */
public class Stack {
    
    private final ArrayList<Puzzle> stack;
    private int stPointer;
    
    public Stack(){
        stPointer = 0;
        stack = new ArrayList<>();
        stack.add(new Puzzle(1,1));
    }
    
    public boolean push(Puzzle state){
        stPointer++;
        stack.add(state);
        return true;
    }
    
    public Puzzle pop(){
        Puzzle state;
        if(stPointer > 0){
            state = stack.get(stPointer);
            stack.remove(stPointer);
            stPointer--;
            return state;
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
