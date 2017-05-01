package scatt.test.app;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import scatt.Student;

/**
 * Test the reading of the variable count. 
 * 
 * @author Broderick DeSantis
 * @author
 * @version 0.5
 *
 */
public class TestVariableCount
{
    /**
     * The first test of the costume tests.
     */
    private static String pianoSb2Path;
    private static String mazeSb2Path;
    private static String hideSb2Path;
    // private static String howlerSb2Path;
    private static Student student1Piano;
    private static Student student2Maze;
    private static Student student3Hide;

    // private static Student student4Howler;

    /**
     * Set up for the tests.
     */
    @BeforeClass
    public static void setUp()
    {
        String pathToTestDataFolder = TestUtils.getFilePathMultiOSSafe()
                + File.separator + "TestData" + File.separator;
        pianoSb2Path = pathToTestDataFolder + "Piano.sb2";
        mazeSb2Path = pathToTestDataFolder + "Maze Starter.sb2";
        hideSb2Path = pathToTestDataFolder + "Hide and Seek.sb2";
        // howlerSb2Path = pathToTestDataFolder +
        // "Introducing Howler! Remix.sb2";

        // MAKE SURE PATHS VALID
        student1Piano = new Student(pianoSb2Path);
        assertEquals("student load failed", 13, student1Piano.getSpriteCount());

        student2Maze = new Student(mazeSb2Path);
        assertEquals("student load failed", 2, student2Maze.getSpriteCount());

        student3Hide = new Student(hideSb2Path);
        assertEquals("student load failed", 1, student3Hide.getSpriteCount());

        // Note: this causes an issue!
        // student4Howler = new Student(howlerSb2Path);
        // assertEquals("student load failed", 3,
        // student4Howler.getSpriteCount());
    }

    /**
     * A test that checks if TestCostumeCount correctly identifies the number of
     * Costumes.
     */
    @Test
    public void testUniqueCostumeCounts()
    {
        assertTrue("incorrect costumes for piano",
                student1Piano.getUniqueVariableCount() == 1);
        assertTrue("incorrect costumes for maze",
                student2Maze.getUniqueVariableCount() == 0);
        assertTrue("incorrect costumes for hide",
                student3Hide.getUniqueVariableCount() == 1);
    }
    
    /**
     * A test that checks if TestCostumeCount correctly identifies the number of
     * sounds.
     */
    @Test
    public void testTotalCostumeCounts()
    {
       
        assertTrue("incorrect costumes for piano",
                student1Piano.getTotalVariableCount() == 1);
        assertTrue("incorrect costumes for maze",
                student2Maze.getTotalVariableCount() == 0);
        
        //notice this one is different from the unique costume count
        assertTrue("incorrect costumes for hide",
                student3Hide.getTotalVariableCount() == 1);
    }
}
