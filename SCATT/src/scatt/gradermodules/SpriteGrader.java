package scatt.gradermodules;

import scatt.Gradeable;
import scatt.GraderWeightedComponent;
import scatt.Student;

/**
 * The WeightedGraderComponent responsible for grading sprites.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class SpriteGrader extends GraderWeightedComponent
{
    public static final String MODULE_NAME = "Sprite Grader";
    private int required = 0;

    /**
     * @param weight - the weight of the sprite module for the entire grading
     *            process.
     * @param requiredSprites - the number of sprites to receive a 100.
     */
    public SpriteGrader(float weight, int requiredSprites)
    {
        // must call abstract constructor
        super(weight, SpriteGrader.MODULE_NAME);
        setNewRequiredSprites(requiredSprites);
    }

    /**
     * Updates the denominator used for grading. The calculation for this
     * concrete grader is: (number of sprites) / (required sprites) * 100.
     * 
     * For example, say a grade of 100 is to have 8 sprites. If the user has 6
     * sprites, then their grade will be equal to 6/8 * 100. The 8 in that
     * fraction is the denominator. This method is used to configure how the
     * sprite grader works.
     * 
     * @param newRequired denominator of the fraction used for grading.
     */
    public void setNewRequiredSprites(int newRequired)
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

        if (objectToGrade instanceof Student)
        {
            Student toGrade = (Student) objectToGrade;
            double numberOfSprites = toGrade.getSpriteCount();
            double grade = 100 * numberOfSprites / getRequiredSprites();

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
     * Get the number of required sprites.
     * 
     * @return the number of required sprites to get a grade of 100.
     */
    public int getRequiredSprites()
    {
        return required;
    }

}
