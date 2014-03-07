package material.tree.exceptions;

import java.util.logging.Logger;

/**
 * Runtime exception thrown when one tries to access the root of an
 * empty tree.
 */

public class EmptyTreeException extends RuntimeException { 
    
    /**
     *
     * @param err
     */
    public EmptyTreeException(String err) {
        
        super(err);
  
    }
    private static final Logger LOG = Logger.getLogger(EmptyTreeException.class.getName());

}
