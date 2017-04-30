package scatt.gradermodules;

import scatt.Gradeable;
import scatt.GraderWeightedComponent;
import scatt.Student;

/**
 * The WeightedGraderComponent responsible for grading scripts. Modeled after
 * the sprite grader.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class ScriptGrader extends GraderWeightedComponent
{
    public static final String MODULE_NAME = "Script Grader";
    private int required = 0;

    /**
     * @param weight - the weight of the script module for the entire grading
     *            process.
     * @param requiredScripts - the number of scripts to receive a 100.
     */
    public ScriptGrader(float weight, int requiredScripts)
    {
        // must call abstract constructor
        super(weight, ScriptGrader.MODULE_NAME);
        setNewRequiredScripts(requiredScripts);
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
    public void setNewRequiredScripts(int newRequired)
    {
        if (newRequired < 1)
        {
            newRequired = 1;
        }
        this.required = newRequired;
    }

    /**
     * Please refer to GraderWeightedComponent javadoc.
     * 
     * @see scatt.GraderWeightedComponent#getGradeFrom0To100()
     * @param objectToGrade is the object that will be graded.
     * 
     * @return A grade between 0 and 100.
     */
    public double getGradeFrom0To100(Gradeable objectToGrade)
    {
        //downcast necessary to keep other classes closed
        if (objectToGrade instanceof Student)
        {
            Student toGrade = (Student) objectToGrade;
            double numberOfScripts = toGrade.getScriptCount();
            double grade = 100 * numberOfScripts / getRequiredScripts();

            if (!getExtraCreditMode() && grade > 100f)
            {
                grade = 100;
            }

            return grade;
        }
        else
        {
            throw new IllegalArgumentException(
                    "The object trying to be graded isn't a student object");
        }
    }

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
    public int getRequiredScripts()
    {
        return required;
    }

}
