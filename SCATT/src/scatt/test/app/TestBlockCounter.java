package scatt.test.app;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

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

    // private static String pianoSb2Path;
    private static String mazeSb2Path;
    private static String hideSb2Path;
    // private static Student student1Piano;
    private static Student student2Maze;
    private static Student student3Hide;

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

        // student1Piano = new Student(pianoSb2Path);
        // assertEquals("student load failed", 13,
        // student1Piano.getSpriteCount());

        student2Maze = new Student(mazeSb2Path);
        assertEquals("student load failed", 2, student2Maze.getSpriteCount());

        student3Hide = new Student(hideSb2Path);
        assertEquals("student load failed", 1, student3Hide.getSpriteCount());
    }

    /**
     * Tests that the block grader returns the correct values number of blocks
     * for maze sb2.
     */
    @Test
    public void testBlockCountMaze()
    {
        assertEquals("Block counts didn't match.", 19 + 5,
                student2Maze.getBlockCount());
    }

    /**
     * Tests that the block grader returns the correct values number of blocks
     * for hide and seek.
     */
    @Test
    public void testBlockCountHide()
    {
        assertEquals("Block counts didn't match.", 16,
                student3Hide.getBlockCount());
    }
}
