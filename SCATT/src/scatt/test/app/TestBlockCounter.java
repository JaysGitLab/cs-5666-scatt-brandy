package scatt.test.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.lang.reflect.Field;

import org.junit.BeforeClass;
import org.junit.Test;

import scatt.BlockData;
import scatt.Student;

/**
 * All tests related to the sprite grader.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class TestBlockCounter
{

    private static String demoSb2Path;
    private static Student student4Demo;

    // private static String pianoSb2Path;
    // private static Student student1Piano;
    private static String mazeSb2Path;
    private static String hideSb2Path;
    private static Student student2Maze;
    private static Student student3Hide;
    private static BlockData s2mData;
    private static BlockData s3hData;
    private static BlockData s4dData;

    /**
     * Set up for the tests.
     */
    @BeforeClass
    public static void setUp()
    {
        String pathToTestDataFolder = TestUtils.getFilePathMultiOSSafe()
                + File.separator + "TestData" + File.separator;
        // pianoSb2Path = pathToTestDataFolder + "Piano.sb2";
        mazeSb2Path = pathToTestDataFolder + "Maze Starter.sb2";
        hideSb2Path = pathToTestDataFolder + "Hide and Seek.sb2";
        demoSb2Path = pathToTestDataFolder + "Everything Demo.sb2";

        student2Maze = new Student(mazeSb2Path);
        assertEquals("student load failed", 2, student2Maze.getSpriteCount());

        student3Hide = new Student(hideSb2Path);
        assertEquals("student load failed", 1, student3Hide.getSpriteCount());

        student4Demo = new Student(demoSb2Path);
        assertEquals("demo load failed", 11, student4Demo.getSpriteCount());

        s2mData = getBlockDataFromStudent(student2Maze);
        s3hData = getBlockDataFromStudent(student3Hide);
        s4dData = getBlockDataFromStudent(student4Demo);

    }

    /**
     * Obtain a referece to the private field of student that contains the block
     * data.
     * 
     * @param student the student to get the block data from.
     * @return a reference to the the block data from the student object that is
     *         declared as private.
     */
    private static BlockData getBlockDataFromStudent(Student student)
    {
        try
        {
            Field blockDataField = Student.class.getDeclaredField("blockData");
            blockDataField.setAccessible(true);
            BlockData data = (BlockData) blockDataField.get(student);
            return data;
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
            fail("could not obtain blockData field.");
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
            fail("security exception.");
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
            fail("illegal argument when trying to obtain blockData");
        }
        catch (IllegalAccessException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Tests that the block grader returns the correct values number of blocks
     * for maze sb2.
     */
    @Test
    public void testBlockCountMaze()
    {
        assertEquals("Block counts didn't match.", 19 + 5,
                s2mData.getTotalCount());
    }

    /**
     * Tests that the block grader returns the correct values number of blocks
     * for hide and seek.
     */
    @Test
    public void testBlockCountHide()
    {
        // s3hData.verbose = true; //this will allow printing out of all counts
        assertEquals("Block counts didn't match.", 16, s3hData.getTotalCount());
    }

    /**
     * Tests that the block counter returns the correct number of different type
     * of blocks for the demo class.
     */
    @Test
    public void testControlBlockDemoAllDirectly()
    {
        assertEquals("incorrect number of motion blocks", 17,
                s4dData.countMotionBlocks());
        assertEquals("incorrect number of look blocks", 19,
                s4dData.countLooksBlocks());
        assertEquals("incorrect number of sound blocks", 13,
                s4dData.countSoundBlocks());
        assertEquals("incorrect number of pen blocks", 12,
                s4dData.countPenBlocks());
        assertEquals("incorrect number of data blocks", 40,
                s4dData.countDataBlocks());
        // NOTE: there are other events blocks in almost every script
        assertEquals("incorrect number of cvent blocks", 18,
                s4dData.countEventBlocks());
        assertEquals("incorrect number of control blocks", 11,
                s4dData.countControlBlocks());
        assertEquals("incorrect number of sense blocks", 16,
                s4dData.countSenseBlocks());
        //22 in apple and 1 in bass
        assertEquals("incorrect number of more blocks", 22 + 1,
                s4dData.countMoreSectionBlocks());
        //s4dData.verbose = true;
        //note: the more blocks sections has operators built in
        assertEquals("incorrect number of operator blocks", 17 + 4,
                s4dData.countOperatorBlocks());
    }
    
    /**
     * Tests that the block counter returns the correct number of different type
     * of blocks for the demo class.
     */
    @Test
    public void testControlBlockDemoAllThroughStudent()
    {
        assertEquals("incorrect number of motion blocks", 17,
                student4Demo.countMotionBlocks());
        assertEquals("incorrect number of look blocks", 19,
                student4Demo.countLooksBlocks());
        assertEquals("incorrect number of sound blocks", 13,
                student4Demo.countSoundBlocks());
        assertEquals("incorrect number of pen blocks", 12,
                student4Demo.countPenBlocks());
        assertEquals("incorrect number of data blocks", 40,
                student4Demo.countDataBlocks());
        // NOTE: there are other events blocks in almost every script
        assertEquals("incorrect number of cvent blocks", 18,
                student4Demo.countEventBlocks());
        assertEquals("incorrect number of control blocks", 11,
                student4Demo.countControlBlocks());
        assertEquals("incorrect number of sense blocks", 16,
                student4Demo.countSenseBlocks());
        //22 in apple and 1 in bass
        assertEquals("incorrect number of more blocks", 22 + 1,
                student4Demo.countMoreSectionBlocks());
        //note: the more blocks sections has operators built in
        assertEquals("incorrect number of operator blocks", 17 + 4,
                student4Demo.countOperatorBlocks());
    }
}
