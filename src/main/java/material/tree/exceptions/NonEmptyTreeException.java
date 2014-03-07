package material.tree.exceptions;

import java.util.logging.Logger;

/**
 * Runtime exception thrown when one tries to create the root of a tree that is
 * not empty.
 */

public class NonEmptyTreeException extends RuntimeException {

	/**
     *
     * @param err
     */
    public NonEmptyTreeException(String err) {
        
            super(err);
	
        }
    private static final Logger LOG = Logger.getLogger(NonEmptyTreeException.class.getName());

}
