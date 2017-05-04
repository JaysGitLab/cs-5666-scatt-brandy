package scatt.gradermodules;

import scatt.Gradeable;
import scatt.Student;

/**
 * Class responsible for grading based on variables.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class VariableGrader extends BasicGrader
{
    public static final String MODULE_NAME = "var grader";

    /**
     * Creates a grader for variables with the associated weight and the
     * required variables.
     * 
     * @param weight the weight the module should have in the calculated
     *            weighted average.
     * @param requiredVariables the number of variables required to get a 100.
     */
    public VariableGrader(float weight, int requiredVariables)
    {
        super(weight, requiredVariables, VariableGrader.MODULE_NAME);
    }

    @Override
    public double getGradeFrom0To100(Gradeable objectToGrade)
    {
        if (getRequired() == 0)
        {
            return 100;
        }

        if (objectToGrade instanceof Student)
        {

            Student student = (Student) objectToGrade;
            double uniVars = student.getUniqueVariableCount();

            // template pattern on required
            double grade = uniVars / getRequired();
            grade *= 100;

            if (!getExtraCreditMode() && grade > 100)
            {
                grade = 100;
            }

            return grade;
        }
        else
        {
            throw new IllegalArgumentException("not a student object"
                    + " passed to the variable grader");
        }
    }

    @Override
    public void setNewRequired(int newRequired)
    {
        this.required = newRequired;

        if (this.required < 0)
        {
            this.required = 0;
        }
    }

}
