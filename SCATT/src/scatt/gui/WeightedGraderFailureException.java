package scatt.gui;

/**
 * Exception to indicate a problem with the weighted grader.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class WeightedGraderFailureException extends Exception
{
    private static final long serialVersionUID = -8587139509095816637L;

    /**
     * Argument constructor that passes a string message.
     * 
     * @param message the message describing the exception.
     */
    public WeightedGraderFailureException(String message)
    {
        super(message);
    }

    /**
     * No arg constructor.
     */
    public WeightedGraderFailureException()
    {
        super();
    }

}
