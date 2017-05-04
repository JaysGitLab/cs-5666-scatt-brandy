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
    private int scriptCount = 0;
    private int tempo = 0;
    private int uniqueSoundCount = 0;
    private int totalSoundsUsed;
    private int uniqueCostumeCount = 0;
    private int totalCostumesUsed;
    private int uniqueVariableCount = 0;
    private int totalVariablesUsed;

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
        spriteCount = SpriteCounter.getSpriteCount(jsonFile);
        scriptCount = ScriptCounter.getScriptCount(jsonFile);
        tempo = Tempo.getTempo(jsonFile);

        // inefficient to call both simultaneously
        uniqueSoundCount = SoundCounter.getUniqueSounds(jsonFile);
        totalSoundsUsed = SoundCounter.getTotalNonUniqueSounds(jsonFile);

        // costumes
        uniqueCostumeCount = CostumeCounter.getUniqueCostumes(jsonFile);
        totalCostumesUsed = CostumeCounter.getTotalNonUniqueCostumes(jsonFile);

        // variables
        uniqueVariableCount = VariableCounter.getUniqueVariables(jsonFile);
        totalVariablesUsed = VariableCounter
                .getTotalNonUniqueVariables(jsonFile);

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
     * @return the number of sprites in the student's .sb2 file.
     */
    public int getScriptCount()
    {
        return scriptCount;
    }

    /**
     * @return the tempo in the student's .sb2 file.
     */
    public int getTempo()
    {
        return tempo;
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

    /**
     * Get the number of sounds in the student's project.json.
     * 
     * @return the number of sounds in the student's project.json.
     */
    public int getUniqueSoundCount()
    {

        return uniqueSoundCount;
    }

    /**
     * Get the number of costumes added to scripts. This isn't the unique number
     * of costumes. If a project has two sounds "x" and "y", and "x" is used 2
     * times and "y" is used 100 times, then this method returns 102.
     * 
     * @return the total number of times any sound is used.
     */
    public int getTotalSoundCount()
    {
        return totalSoundsUsed;
    }

    /**
     * Get the number of costumes in the student's project.json.
     * 
     * @return the number of costumes in the student's project.json.
     */
    public int getUniqueCostumeCount()
    {

        return uniqueCostumeCount;
    }

    /**
     * Get the number of costumes added to scripts. This isn't the unique number
     * of costumes. If a project has two sounds "x" and "y", and "x" is used 2
     * times and "y" is used 100 times, then this method returns 102.
     * 
     * @return the total number of times any costume is used.
     */
    public int getTotalCostumeCount()
    {
        return totalCostumesUsed;
    }

    /**
     * Get the number of variables in the student's project.json.
     * 
     * @return the number of variables in the student's project.json.
     */
    public int getUniqueVariableCount()
    {

        return uniqueVariableCount;
    }

    /**
     * Get the number of variables added to scripts. This isn't the unique
     * number of variables. If a project has two sounds "x" and "y", and "x" is
     * used 2 times and "y" is used 100 times, then this method returns 102.
     * 
     * @return the total number of times any variable is used.
     */
    public int getTotalVariableCount()
    {
        return totalVariablesUsed;
    }
}
