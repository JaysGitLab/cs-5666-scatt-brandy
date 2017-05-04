package scatt.test.app;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import scatt.Student;

//import static org.junit.Assert.fail;

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
    
    
    private static String demoSb2Path;
    private static Student student4Demo;

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
        demoSb2Path = pathToTestDataFolder + "Everything Demo.sb2";

        // howlerSb2Path = pathToTestDataFolder +
        // "Introducing Howler! Remix.sb2";

        // MAKE SURE PATHS VALID
        student1Piano = new Student(pianoSb2Path);
        assertEquals("student load failed", 13, student1Piano.getSpriteCount());

        student2Maze = new Student(mazeSb2Path);
        assertEquals("student load failed", 2, student2Maze.getSpriteCount());

        student3Hide = new Student(hideSb2Path);
        assertEquals("student load failed", 1, student3Hide.getSpriteCount());

        student4Demo = new Student(demoSb2Path);
        assertEquals("demo load failed", 11, student4Demo.getSpriteCount());

        // Note: this causes an issue!
        // student4Howler = new Student(howlerSb2Path);
        // assertEquals("student load failed", 3,
        // student4Howler.getSpriteCount());
    }

    /**
     * A test that checks if TestvariablesCount correctly identifies the number
     * of variables.
     */
    @Test
    public void testUniqueVariableCounts()
    {
        assertEquals("incorrect variables for piano", 1,
                student1Piano.getUniqueVariableCount());
        assertEquals("incorrect variables for maze", 0,
                student2Maze.getUniqueVariableCount());
        assertEquals("incorrect variables for hide", 1,
                student3Hide.getUniqueVariableCount());
    }


    /**
     * A test that checks if Testvariables correctly identifies the number of
     * variables.
     */
    @Test
    public void testTotalVariabeCounts()
    {

        assertEquals("incorrect variables for piano", 1,
                student1Piano.getTotalVariableCount());
        assertEquals("incorrect variables for maze", 0,
                student2Maze.getTotalVariableCount(), 0);

        // notice this one is different from the unique variables count
        assertEquals("incorrect variables for hide", 1,
                student3Hide.getTotalVariableCount());
    }
    
    
    /**
     * A test that checks if TestvariablesCount correctly identifies the number
     * of variables.
     */
    @Test
    public void testUniqueVariableCountsDemo()
    {
        assertEquals("incorrect variables for hide", 13,
                student4Demo.getUniqueVariableCount());
    }
    
    /**
     * A test that checks if Testvariables correctly identifies the number of
     * variables.
     */
    @Test
    public void testTotalVariabeCountsDemoSb2()
    {
        assertEquals("incorrect variables for hide", 13,
                student4Demo.getTotalVariableCount());
    }
}
