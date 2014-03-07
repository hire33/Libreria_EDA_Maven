package material.tree.exceptions;

import java.util.logging.Logger;

/**
 * Thrown when a position is determined to be invalid.
 */

// A run-time exception for invalid positions
public class InvalidPositionException extends RuntimeException {  
  
    /**
     *
     * @param err
     */
    public InvalidPositionException(String err) {
    
        super(err);
  
    }
    
    /**
     *
     */
    public InvalidPositionException() {
        
        /* default constructor */
    
    }
    private static final Logger LOG = Logger.getLogger(InvalidPositionException.class.getName());
  
}
