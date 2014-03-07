package material.tree.exceptions;

/**
 * Signals that the boundaries of a data structure have been illegally
 * traversed (e.g. past the end of a list).
 */

public class BoundaryViolationException  extends RuntimeException {
  
    public BoundaryViolationException (String message) {
    
        super (message);
  
    }

}
