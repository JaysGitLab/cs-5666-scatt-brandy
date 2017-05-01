package scatt;

/**
 * An interface for an individual component of a weighted average. Concrete
 * grading classes should implement this interface so that the grader method can
 * accurately calculate grades.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 * 
 */
public abstract class GraderWeightedComponent
{
    /** A module name suitable for graphical user interfaces. */
    private final String moduleName;

    /** The variable that represents the grade weight of this module. */
    private double weight;

    /** A flag that allows modules to give scores over 100, if implemented. */
    private boolean allowExtraCredit = false;

    /**
     * This requires any subclass to call super to set the initial weight.
     * 
     * @param initialWeight the initial weight for module is required.
     * @param moduleName the name of the module for GUI based applications.
     */
    public GraderWeightedComponent(float initialWeight, String moduleName)
    {
        setWeight(initialWeight);
        this.moduleName = moduleName;
    }

    /**
     * Get a grade for a given component. Grades should be between 0 and 100.
     * 
     * This method can be used in conjunction with with the component's weight
     * value.
     * 
     * @param objectToGrade is the object that will be graded.
     * 
     * @return a grade from 0 to 100 based on the concrete implementation
     */
    public abstract double getGradeFrom0To100(Gradeable objectToGrade);

    /**
     * Get the weight of the given component. Weight represents what fraction of
     * 100 this component contributes to the overall grade.
     * 
     * For example, consider the weighted average of components A, B, and C.
     * 
     * Grade = 0.25 * A + 0.5 * B + 0.25 * C;
     * 
     * 0.25 is the weight of A, 0.5 is the weight of B, and 0.25 is the weight
     * of C.
     * 
     * @return the weight of this component.
     */
    public double getWeightFrom0To1()
    {
        return weight;
    }

    /**
     * Changes the weight of a component.
     * 
     * Grade = 0.25 * A + 0.5 * B + 0.25 * C;
     * 
     * 0.25 is the weight of A, 0.5 is the weight of B, and 0.25 is the weight
     * of C.
     * 
     * if the newWeight is below zero, the weight will be set to 0. If the
     * newWeight is greater than 1, then the weight will be set to.
     * 
     * @precondition weight is between 0 (inclusive) and 1 (inclusive).
     * @param newWeight the new value of the weight.
     * 
     */
    public void setWeight(double newWeight)
    {
        if (newWeight < 0)
        {
            newWeight = 0;
        }
        else if (newWeight > 1)
        {
            newWeight = 1;
        }

        this.weight = newWeight;
    }

    /**
     * Get the weighted grade of the component.
     * 
     * Consider the example with components A and B: WeightedAverageGrade =
     * A_Grade * A_weight + B_Grade * B_Weight
     * 
     * If this method is called on component A, then the return value is:
     * A_Grade * A_Weight.
     * 
     * @param objectToGrade the object that contains the required information
     *            for grading.
     * 
     * @return The weighted grade of this component; ie Weight * Grade.
     */
    public double getWeightedGrade(Gradeable objectToGrade)
    {      
        //simply return the weight * unweighted grade.
        return getWeightFrom0To1() * getGradeFrom0To100(objectToGrade);
    }

    /**
     * Determines if the argument's concrete type is valid for grading within
     * this module.
     * 
     * @param objectToGrade is the object to check.
     * @return true if concrete class can grade the object based on type.
     */
    public abstract boolean validType(Gradeable objectToGrade);

    /**
     * The concrete title set by programmer for this module.
     * 
     * @return The title of the module that is suitable for a column header in
     *         the graphical user interface.
     */
    public String getModuleName()
    {
        return moduleName;
    }

    /**
     * @return true if module is allowing extra credit, false otherwise
     */
    public boolean getExtraCreditMode()
    {
        return allowExtraCredit;
    }

    /**
     * Sets this module to allow extra credit (scores over 100) or no allow
     * extra credit.
     * 
     * @param allowExtraCredit set the value of extra credit.
     */
    public void setExtraCreditMode(boolean allowExtraCredit)
    {
        this.allowExtraCredit = allowExtraCredit;
    }

}
