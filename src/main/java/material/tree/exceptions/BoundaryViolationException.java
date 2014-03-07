package material.tree.exceptions;

import java.util.logging.Logger;

/**
 * Signals that the boundaries of a data structure have been illegally
 * traversed (e.g. past the end of a list).
 */

public class BoundaryViolationException  extends RuntimeException {
  
    /**
     *
     * @param message
     */
    public BoundaryViolationException (String message) {
    
        super (message);
  
    }
    private static final Logger LOG = Logger.getLogger(BoundaryViolationException.class.getName());

}
