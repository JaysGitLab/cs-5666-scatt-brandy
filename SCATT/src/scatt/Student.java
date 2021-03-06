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
    private BlockData blockData;
    private int spriteCount = 0;
    private int scriptCount = 0;
    private int tempo = 0;
    private int uniqueSoundCount = 0;
    private int totalSoundsUsed;
    private int uniqueCostumeCount = 0;
    private int totalCostumesUsed;
    private int uniqueVariableCount = 0;
    private int totalVariablesUsed;
    private int numberOfLists;

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
        blockData = BlockCounter.getBlockData(jsonFile);
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

        numberOfLists = ListCounter.getListCount(jsonFile);

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
     * Get the number of script blocks that is in the student's project.
     * 
     * @return the number of blocks found in the student's file
     */
    public int getBlockCount()
    {
        return blockData.getTotalCount();
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

    /**
     * Get the number of lists in the student's project.
     * 
     * @return the number of lists that were in the student's project. i
     */
    public int getNumberOfLists()
    {
        return numberOfLists;
    }

    /**
     * Get the motion block count.
     * 
     * @return get the motion block count.
     */
    public int countMotionBlocks()
    {
        return blockData.countMotionBlocks();
    }

    /**
     * Get the look block count.
     * 
     * @return Get the look block count.
     */
    public int countLooksBlocks()
    {
        return blockData.countLooksBlocks();
    }

    /**
     * Get the number of sound blocks.
     * 
     * @return get the number of sound blocks.
     */
    public int countSoundBlocks()
    {
        return blockData.countSoundBlocks();
    }

    /**
     * Get the number of pen blocks.
     * 
     * @return get the number of pen blocks
     */
    public int countPenBlocks()
    {
        return blockData.countPenBlocks();
    }

    /**
     * Get the number of data blocks.
     * 
     * @return the number of data blocks
     */
    public int countDataBlocks()
    {
        return blockData.countDataBlocks();
    }

    /**
     * Get the number of event blocks.
     * 
     * @return get the number of event blocks.
     */
    public int countEventBlocks()
    {
        return blockData.countEventBlocks();
    }

    /**
     * Get the number of control blocks.
     * 
     * @return get the number of control blocks.
     */
    public int countControlBlocks()
    {
        return blockData.countControlBlocks();
    }

    /**
     * Get the number of blocks in the sense category.
     * 
     * @return Get the look block count.
     */
    public int countSenseBlocks()
    {
        return blockData.countSenseBlocks();
    }

    /**
     * Get the number of special blocks that are in the "more section" e.g. lego
     * blocks
     * 
     * @return Get the number of special blocks that are in the "more section"
     *         e.g. lego blocks
     */
    public int countMoreSectionBlocks()
    {
        return blockData.countMoreSectionBlocks();
    }

    /**
     * get the number of operator blocks in the file. Note, some blocks
     * inherently have operators within (e.g. "more section" blocks); thus, this
     * number may seem inflated.
     * 
     * @return the number of operator blocks that are in the project file.
     */
    public int countOperatorBlocks()
    {
        return blockData.countOperatorBlocks();
    }

}
