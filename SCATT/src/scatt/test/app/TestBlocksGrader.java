package scatt.test.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import scatt.Student;
import scatt.gradermodules.BlockGrader;

/**
 * All tests related to the Block grader.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class TestBlocksGrader
{

    private static String pianoSb2Path;
    private static String mazeSb2Path;
    private static String hideSb2Path;
    private static String demoSb2Path;
    private static Student student1Piano;
    private static Student student2Maze;
    private static Student student3Hide;
    private static Student student4Demo;

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

        student1Piano = new Student(pianoSb2Path);
        assertEquals("student load failed", 13, student1Piano.getSpriteCount());

        student2Maze = new Student(mazeSb2Path);
        assertEquals("student load failed", 2, student2Maze.getSpriteCount());

        student3Hide = new Student(hideSb2Path);
        assertEquals("student load failed", 1, student3Hide.getSpriteCount());

        student4Demo = new Student(demoSb2Path);
        assertEquals("demo load failed", 11, student4Demo.getSpriteCount());
    }

    /**
     * Tests that the Block grader returns the correct values.
     */
    @Test
    public void testBlockGraderGetWeightedGradeValuesDemo()
    {
        BlockGrader blockGrader = new BlockGrader(1f, 17, 19, 13, 12, 40, 18,
                11, 16, 17 + 4, 22 + 1);

        assertEquals("piano had incorrect grade", 100f,
                blockGrader.getWeightedGrade(student4Demo), 0.001);

        blockGrader.setWeight(0.5f);

        assertEquals("piano had incorrect grade", 50f,
                blockGrader.getWeightedGrade(student4Demo), 0.001);

        //double requirement to make the grade a 50
        blockGrader = new BlockGrader(1f, 17 * 2, 2 * 19, 2 * 13, 2 * 12,
                2 * 40, 2 * 18, 2 * 11, 2 * 16, 2 * (17 + 4), 2 * (22 + 1));

        assertEquals("piano had incorrect grade", 50f,
                blockGrader.getWeightedGrade(student4Demo), 0.001);
        
        //change weight to half, with double rquirement, to make grade 25.
        blockGrader.setWeight(0.5f);
        assertEquals("piano had incorrect grade", 25f,
                blockGrader.getWeightedGrade(student4Demo), 0.001);

    }

    /**
     * Tests the extraCredit mode. Tests that it works when turned on. Tests
     * that you cannot get over 100 when turned off.
     */
    @Test
    public void testExtraCreditModeDemo()
    {
        BlockGrader blockGrader = new BlockGrader(1f, 17, 19, 13, 12, 40, 18,
                11, 16, 17 + 4, 22 + 1);
        blockGrader.setExtraCreditMode(true);

        assertTrue("hide had incorrect grade",
                blockGrader.getWeightedGrade(student4Demo) > 100f);

        blockGrader.setExtraCreditMode(false);
        assertEquals("hide had incorrect grade", 100f,
                blockGrader.getWeightedGrade(student4Demo), 0.001);

    }

}
