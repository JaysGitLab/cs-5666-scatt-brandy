package scatt.gradermodules;

import scatt.Gradeable;
import scatt.Student;

/**
 * A grader for the tempo property in student's projects.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class TempoGrader extends BasicGrader
{

    public static final String MODULE_NAME = "Tempo Grader";

    /**
     * Construct a grader for tempos.
     * 
     * @param weight the weight the grader should have in the weighted average
     *            calcuation.
     * @param requiredTempo the required tempo of the file.
     */
    public TempoGrader(float weight, int requiredTempo)
    {
        super(weight, requiredTempo, TempoGrader.MODULE_NAME);
    }

    /**
     * Set the required tempo for the project file.
     * 
     * @param newTempo the new tempo to be required.
     */
    @Override
    public void setNewRequired(int newTempo)
    {
        if (newTempo < 0)
        {
            newTempo = 0;
        }
        this.required = newTempo;
    }

    /**
     * Get a grade from 0 to 100 based on tempo. Either the student receives a
     * 100 for having the correct tempo, or the student receives a 0 for not
     * having the correct tempo.
     * 
     * @precondition objectToGrade is of type student.
     * 
     * @param objectToGrade the student object to be graded.
     * @return the grade for the student.
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
            double tempoVal = student.getTempo();

            // template pattern on required
            double grade = tempoVal == this.required ? 100 : 0;

            return grade;
        }
        else
        {
            throw new IllegalArgumentException("not a student object"
                    + " passed to the variable grader");
        }
    }

}
