package scatt.gradermodules;

import scatt.Gradeable;
import scatt.Student;

/**
 * A grader for number of lists in the weighted average calculation.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class ListGrader extends BasicGrader
{

    private static final String MODULE_NAME = "list grader";

    /**
     * Construct a grader for lsits.
     * 
     * @param weight the weight the module should have in the weighted
     *            calculation.
     * @param requiredListCount number of required lists to receive a 100.
     */
    public ListGrader(float weight, int requiredListCount)
    {
        super(weight, requiredListCount, ListGrader.MODULE_NAME);
    }

    /** (non-Javadoc).
     * @see scatt.gradermodules.BasicGrader#setNewRequired(int)
     */
    @Override
    public void setNewRequired(int newRequired)
    {
        if (newRequired < 0)
        {
            newRequired = 0;
        }
        this.required = newRequired;
    }

    /** (non-Javadoc).
     * @see scatt.GraderWeightedComponent#getGradeFrom0To100(scatt.Gradeable)
     */
    @Override
    public double getGradeFrom0To100(Gradeable objectToGrade)
    {
        if (objectToGrade instanceof Student)
        {
            if (getRequired() == 0)
            {
                return 100;
            }

            Student student = (Student) objectToGrade;
            double uniVars = student.getNumberOfLists();

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

}
