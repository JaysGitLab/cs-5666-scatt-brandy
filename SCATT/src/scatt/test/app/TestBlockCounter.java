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
        assertEquals("Block counts didn't match.", 19 + 5, s2mData.getCount());
    }

    /**
     * Tests that the block grader returns the correct values number of blocks
     * for hide and seek.
     */
    @Test
    public void testBlockCountHide()
    {
        assertEquals("Block counts didn't match.", 16, s3hData.getCount());
    }

    /**
     * Tests that the block counter returns the correct number of control blocks
     * for the demo class.
     */
    @Test
    public void testControlBlockDemo()
    {
        fail("not implemented");
    }
}
