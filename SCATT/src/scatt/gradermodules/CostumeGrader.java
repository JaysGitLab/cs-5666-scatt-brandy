package scatt.gradermodules;

import scatt.Gradeable;
import scatt.Student;

/**
 * A grader for costumes that works with the weighted average grader.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class CostumeGrader extends BasicGrader
{

    private static final String MODULE_NAME = "costume grader";

    /**
     * Create a costume grader.
     * 
     * @param weight the weight of the module in the weighted average.
     * @param requiredCostumes the number of costumes required to get a 100.
     */
    public CostumeGrader(float weight, int requiredCostumes)
    {
        super(weight, requiredCostumes, CostumeGrader.MODULE_NAME);
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
            double uniCostumes = student.getUniqueCostumeCount();

            // template pattern on required
            double grade = uniCostumes / getRequired();
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
