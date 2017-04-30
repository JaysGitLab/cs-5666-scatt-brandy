package scatt.gradermodules;

/**
 * The grader responsible for grading the sound count.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class SoundGrader extends BasicGrader
{
    public static final String MODULE_NAME = "Sound Grader";

    /**
     * Construct a sound grader.
     * @param weight the weight of the grader.
     * @param requiredSounds the sound needed to get a 100.
     */
    public SoundGrader(float weight, int requiredSounds)
    {
        super(weight, requiredSounds, SoundGrader.MODULE_NAME);
    }
}
