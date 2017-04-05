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
public interface GraderWeightedComponent
{
    /**
     * Get a grade for a given component. Grades should be between 0 and 100.
     * 
     * This method can be used in conjunction with with the component's weight
     * value.
     * 
     * @return a grade from 0 to 100 based on the concrete implementation
     */
    public float getGradeFrom0To100();

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
    public float getWeightFrom0To1();

    /**
     * Changes the weight of a component.
     * 
     * Grade = 0.25 * A + 0.5 * B + 0.25 * C;
     * 
     * 0.25 is the weight of A, 0.5 is the weight of B, and 0.25 is the weight
     * of C.
     * 
     * @precondition weight is between 0 (inclusive) and 1 (inclusive).
     * @param newWeight the new value of the weight.
     * @throws IllegalArgumentException new eight was either: newWeight < 0 ||
     *             newWeight > 1.
     * 
     */
    public void setWeight(float newWeight) throws IllegalArgumentException;

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
    public float getWeightedGrade(Gradeable objectToGrade);

    /**
     * Determines if the argument's concrete type is valid for grading within
     * this module.
     * 
     * @param objectToGrade is the object to check.
     * @return true if concrete class can grade the object based on type.
     */
    public boolean validType(Gradeable objectToGrade);

}
