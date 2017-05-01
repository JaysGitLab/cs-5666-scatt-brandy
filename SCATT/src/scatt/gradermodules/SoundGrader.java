package scatt.gradermodules;

import scatt.Gradeable;
import scatt.GraderWeightedComponent;
import scatt.Student;

/**
 * The grader responsible for grading the sound count.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class SoundGrader extends GraderWeightedComponent
{
    public static final String MODULE_NAME = "Sound Grader";
    private int numberUniqueRequired;
    private int totalNumberRequired;
    private float uniqueGradeFractionalWeight;

    // perhaps create two extra credit modes, one for unique and one for total.

    /**
     * Construct a sound grader.
     * 
     * @param initialWeight the weight of this module in the weighted average.
     * @param numberUniqueRequired the number of different unique sounds used.
     * @param totalNumberRequired totalNumber of sounds calls required.
     * @param uniqueGradeFractionalWeight Since there are two types of grade
     *            parameters (unique and total), each parameter can thought to
     *            contribute a percentage to the total grade. For example, if
     *            your grade depends .5 (50%) upon the unique, then the other
     *            50% is dependent upon the total sounds. This means, if you got
     *            100% on the required unique sounds, but got a 50% on the total
     *            sounds needed, then your grade would be 0.5 * uniqueGrade +
     *            0.5 totalSoundGrade, which is 75% in this example.
     * 
     *            Another example is 25% uniqueSounds and 75% totalSounds. let
     *            the grade for uniqueSounds be 100% and let the grade for
     *            totalSounds be 67% grade0To100 will be equal to 0.25*100 +
     *            0.75*66 = 74.5.
     * 
     *            values should be between 0 and 1
     * 
     */
    public SoundGrader(float initialWeight, int numberUniqueRequired,
            int totalNumberRequired, float uniqueGradeFractionalWeight)
    {
        super(initialWeight, SoundGrader.MODULE_NAME);
        setUniqueSoundFractionalWeight(uniqueGradeFractionalWeight);
        setTotalNumberRequired(totalNumberRequired);
        setNumberUniqueRequired(numberUniqueRequired);

    }

    /**
     * Return the grade for the sprites. Grade values range between 0 and 100.
     * (non-Javadoc)
     * 
     * @see scatt.GraderWeightedComponent#getGradeFrom0To100(scatt.Gradeable)
     * @param objectToGrade the object to grade.
     * @return the un-weighted grade (0 to 100)
     */
    public double getGradeFrom0To100(Gradeable objectToGrade)
    {
        if (validType(objectToGrade))
        {
            Student toGrade = (Student) objectToGrade;
            float uniSnd = toGrade.getUniqueSoundCount();
            float totSnd = toGrade.getTotalSoundCount();

            // calculate the fractions of needed 
            int uniReq = getNumUniqueRequired();
            int totReq = getTotalNumRequired();
            
            //calculate fraction, if 0 required then give 100
            float uniFrac = uniReq != 0 ? uniSnd / getNumUniqueRequired() : 1;
            float totFrac = totReq != 0 ? totSnd / getTotalNumRequired() : 1;

            // filter if extra credit is off (over 100 becomes 100)
            if (!getExtraCreditMode())
            {
                uniFrac = uniFrac > 1 ? 1 : uniFrac;
                totFrac = totFrac > 1 ? 1 : totFrac;
            }

            // get the actual fractional weight
            float uniPortion = uniFrac * getUniqueGradePercentage();
            float totPortion = totFrac * getTotalSoundsGradePercentage();

            // simply return the sum.
            return 100 * (uniPortion + totPortion);
        }
        else
        {
            throw new IllegalArgumentException(
                    "Type to Sound Grader was not a student");
        }
    }

    /**
     * Returns the grade accounting for the weight.(so a 100 with weight 0.5
     * will return 50). (non-Javadoc)
     * 
     * @see scatt.GraderWeightedComponent#getWeightedGrade(scatt.Gradeable)
     * @param objectToGrade the student object to be graded.
     * @return the weighted grade.
     */
    public double getWeightedGrade(Gradeable objectToGrade)
    {
        // simply return the weight * unweighted grade.
        return getWeightFrom0To1() * getGradeFrom0To100(objectToGrade);
    }

    /**
     * Ensures that the type of the object to be graded is a Student object.
     * (non-Javadoc)
     * 
     * @see scatt.GraderWeightedComponent#validType(scatt.Gradeable)
     * @param objectToGrade the object that will be passed to be graded.
     * @return true if the type is of student, false otherwise.
     */
    public boolean validType(Gradeable objectToGrade)
    {
        return objectToGrade instanceof Student;
    }

    /**
     * Get the number of unique sounds that should be in the file.
     * 
     * @return the number of unique sounds to receive a 100.
     */
    public int getNumUniqueRequired()
    {
        return numberUniqueRequired;
    }

    /**
     * Set the number of unique sounds required to get a 100 on the unique sound
     * grading portion.
     * 
     * 
     * 
     * @param numberUniqueRequired the new number of unique sounds to require.
     */
    public void setNumberUniqueRequired(int numberUniqueRequired)
    {
        this.numberUniqueRequired = numberUniqueRequired;
    }

    /**
     * Get the total number of sounds to occur in file. This is different from
     * unique sounds because the sound sound file can be used multiple times.
     * 
     * 
     * @return the total number of sound calls in scripts required to receive a
     *         100 on the total sound portin of the grade.
     */
    public int getTotalNumRequired()
    {
        return totalNumberRequired;
    }

    /**
     * Get the total number of sounds to occur in file. This is different from
     * unique sounds because the sound sound file can be used multiple times.
     * 
     * Throws illegal argument exception if number is set to zero.
     * 
     * @param totalNumberRequired the new total number of sound calls in scripts
     *            required to receive a 100 on the total sound portion of the
     *            grade.
     */
    public void setTotalNumberRequired(int totalNumberRequired)
    {
        this.totalNumberRequired = totalNumberRequired;
    }

    /**
     * Get the weight for unique sounds. If unique sounds contribute to 25%,
     * then this returns .25.
     * 
     * @return the fractional weight for gradePercetage.
     */
    public float getUniqueGradePercentage()
    {
        return uniqueGradeFractionalWeight;
    }

    /**
     * Set a new weight for the fractional percentage that unique sounds play in
     * the total grade.
     * 
     * @sideEffect changes the fractional weight of totalSounds
     * @param newUniqueSoundFractionalWeight the new fractional weight for
     *            unique sounds.
     */
    public void setUniqueSoundFractionalWeight(
            float newUniqueSoundFractionalWeight)
    {
        if (newUniqueSoundFractionalWeight < 0)
        {
            newUniqueSoundFractionalWeight = 0;
        }
        else if (newUniqueSoundFractionalWeight > 1)
        {
            newUniqueSoundFractionalWeight = 1;
        }
        this.uniqueGradeFractionalWeight = newUniqueSoundFractionalWeight;
    }

    /**
     * Get the fractional weight that the total number of sounds plays in the
     * overall grade of the grader.
     * 
     * @return the fractional
     */
    public float getTotalSoundsGradePercentage()
    {
        return 1 - this.uniqueGradeFractionalWeight;
    }

    /**
     * Sets the fractional weight for how total sounds factor into the grade.
     * 
     * @sideEffect changes the fractional wegith for unique sounds.
     * @param newTotalSoundsGradeFractionalWeight the new fractional wegiht for
     *            total sounds.
     */
    public void setTotalSoundsGradeFractionalWeight(
            float newTotalSoundsGradeFractionalWeight)
    {
        if (newTotalSoundsGradeFractionalWeight < 0)
        {
            newTotalSoundsGradeFractionalWeight = 0;
        }
        else if (newTotalSoundsGradeFractionalWeight > 1)
        {
            newTotalSoundsGradeFractionalWeight = 1;
        }

        //@formatter:off
        this.uniqueGradeFractionalWeight = 
                1 - newTotalSoundsGradeFractionalWeight;
        //@formatter:on
    }
}
