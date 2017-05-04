package scatt.gradermodules;

import scatt.Gradeable;
import scatt.GraderWeightedComponent;
import scatt.Student;

/**
 * The WeightedGraderComponent responsible abstract class that is responsible
 * for simplistic grading (similar to sprite and script).
 * 
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public abstract class BasicGrader extends GraderWeightedComponent
{
    // public static final String MODULE_NAME;
    protected int required = 0;

    /**
     * @param weight - the weight of the script module for the entire grading
     *            process.
     * @param requiredItems - the number of items (eg sprites, scripts, tempos,
     *            sounds, etc) to receive a 100.
     * @param moduleName - a gui appropriate name for the module.
     */
    public BasicGrader(float weight, int requiredItems, String moduleName)
    {
        // must call abstract constructor
        super(weight, moduleName);
        setNewRequired(requiredItems);
    }

    /**
     * Updates the denominator used for grading. The calculation for this
     * concrete grader is: (number of scripts) / (required scripts) * 100.
     * 
     * For example, say a grade of 100 is to have 8 scripts. If the user has 6
     * scripts, then their grade will be equal to 6/8 * 100. The 8 in that
     * fraction is the denominator. This method is used to configure how the
     * script grader works.
     * 
     * @param newRequired denominator of the fraction used for grading.
     */
    public abstract void setNewRequired(int newRequired);


    /**
     * Please refer to GraderWeightedComponent javadoc.
     * 
     * (non-Javadoc)
     * 
     * @see scatt.GraderWeightedComponent#getWeightedGrade(scatt.Gradeable)
     * @param objectToGrade is the object that will be graded.
     * @return returns the weight * grade for a weighted component grade.
     */
    public double getWeightedGrade(Gradeable objectToGrade)
    {
        return getWeightFrom0To1() * getGradeFrom0To100(objectToGrade);
    }

    /**
     * Please refer to GraderWeightedComponent javadoc.
     * 
     * (non-Javadoc)
     * 
     * @see scatt.GraderWeightedComponent#validType(scatt.Gradeable)
     * @param objectToGrade is the object that will be graded.
     * @return returns true if the gradeable object is of type Student.
     */
    public boolean validType(Gradeable objectToGrade)
    {
        return objectToGrade instanceof Student;
    }

    /**
     * Get the number of required scripts.
     * 
     * @return the number of required scripts to get a grade of 100.
     */
    public int getRequired()
    {
        return required;
    }

}
