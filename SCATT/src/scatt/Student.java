package scatt;

/**
 * This class encapsulates an individual student's submission.
 * 
 * GUI's should make their calls to instances of this class to obtain
 * information about a student.
 * 
 * For example, to obtain the sprite count - the GUI would simply call
 * studentObj.getSpriteCount();
 * 
 * Processing of .sb2 files happen internally within this class.
 * 
 * @author Matt Stone
 * @author Mikeal Anderson
 * @author Chad Halversen
 * @author Broderick DeSantis
 * 
 * @version 1.0
 */
public class Student
{
    /**
     * Construct a student object from a .sb2 file path.
     * 
     * @param zippedSb2FilePath the absolute path to a zipped sb2 file.
     */
    public Student(String zippedSb2FilePath)
    {
        // TODO - .json through the pipeline
    }

    /**
     * @return the number of sprites in the student's .sb2 file.
     */
    public int getSpriteCount()
    {
        // TODO implment this
        return -1;
    }
}
