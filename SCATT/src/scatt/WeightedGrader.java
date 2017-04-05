package scatt;

import java.util.ArrayList;

/**
 * The main command line grader.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 * 
 */
public class WeightedGrader
{

    // private HashMap<GraderWeightedComponent, GraderWeightedComponent>
    // components;
    private ArrayList<GraderWeightedComponent> components;

    /**
     * Create a grader object with a set of components. The weights of
     * components should sum to 1 +- 0.0001.
     * 
     * @param args The pre-configured Weighted Components.
     * @throws IllegalArgumentException Weights do not sum to 1 +- 0.0001
     */
    public WeightedGrader(GraderWeightedComponent... args)
            throws IllegalArgumentException
    {
        components = new ArrayList<GraderWeightedComponent>(args.length);
        float sum = 0f;
        for (GraderWeightedComponent currentComponent : components)
        {
            sum += currentComponent.getWeightFrom0To1();
        }

        if (Math.abs(sum - 1) > 0.0001)
        {
            throw new IllegalArgumentException(String.format(
                    "Grades do not sum to 1, but %f", sum));
        }
    }

    /**
     * @precondition all modules can handle the concrete type of "objectToGrade"
     * @param objectToGrade an object that implements Gradeable
     * @return the weighted average grade.
     */
    public float grade(Gradeable objectToGrade)
    {
        float weightedGrade = 0f;
        // sum up each component of the grade.
        for (GraderWeightedComponent currentComponent : components)
        {
            if (currentComponent.validType(objectToGrade))
            {
                weightedGrade += currentComponent
                        .getWeightedGrade(objectToGrade);
            }
            else
            {
                throw new IllegalArgumentException(
                        "grader module cannot grade passed gradeable object");
            }
        }
        return weightedGrade;
    }
}
