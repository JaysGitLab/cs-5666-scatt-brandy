package scatt;

import java.io.File;
import java.nio.file.Paths;

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
public class Student implements Gradeable
{
    private UnZipper unZipper;
    private int spriteCount = 0;

    /**
     * Construct a student object from a .sb2 file path.
     * 
     * @param zippedSb2FilePath the absolute path to a zipped sb2 file.
     */
    public Student(String zippedSb2FilePath)
    {
        // TODO - .json through the pipeline
        unZipper = new UnZipper(zippedSb2FilePath);
        String folderPath = unZipper.unZip();
        File jsonFile = new File(folderPath + "/project.json");

        // pipeline starts
        spriteCount = Sprite.getSpriteCount(jsonFile);

        // clean up created directory
        unZipper.clean();
    }

    /**
     * @return the number of sprites in the student's .sb2 file.
     */
    public int getSpriteCount()
    {
        return spriteCount;
    }

    /**
     * Main method for in-class example.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        String curPath = Paths.get("").toAbsolutePath().toString();
        String fileName = "Pong Starter.sb2";
        String zippedDirLocStr = curPath + File.separator + "TestData"
                + File.separator + fileName;

        Student test = new Student(zippedDirLocStr);
        System.out.printf("Sprite Count: %d", test.getSpriteCount());
    }
}
