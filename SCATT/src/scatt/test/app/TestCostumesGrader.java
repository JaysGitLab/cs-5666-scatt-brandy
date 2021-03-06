package scatt.test.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import scatt.Student;
import scatt.gradermodules.CostumeGrader;

/**
 * All tests related to the costume grader.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class TestCostumesGrader
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
     * Tests that the costume grader returns the correct values.
     */
    @Test
    public void testCostumeGraderGetWeightedGradeValuesPiano()
    {
        CostumeGrader costGrader = new CostumeGrader(1f, 25);

        assertEquals("piano had incorrect grade", 100f,
                costGrader.getWeightedGrade(student1Piano), 0.001);

        costGrader.setWeight(0.5f);

        assertEquals("piano had incorrect grade", 50f,
                costGrader.getWeightedGrade(student1Piano), 0.001);


        costGrader.setNewRequired(0);

        assertEquals("piano had incorrect grade", 50f,
                costGrader.getWeightedGrade(student1Piano), 0.001);

    }

    /**
     * Tests that the costume grader returns the correct values.
     */
    @Test
    public void testCostumeGraderGetWeightedGradeValuesMaze()
    {
        // weight = 1 --------------------------------------------------
        CostumeGrader costGrader = new CostumeGrader(1f, 4);

        assertEquals("maze had incorrect grade", 100f,
                costGrader.getWeightedGrade(student2Maze), 0.001);

        // weight = 0.5 --------------------------------------------------
        costGrader.setWeight(0.5f);
        costGrader.setNewRequired(8);
        assertEquals("maze had incorrect grade", 25f,
                costGrader.getWeightedGrade(student2Maze), 0.001);

        // ------------------ set required to 0

        costGrader.setNewRequired(0);

        assertEquals("maze had incorrect grade", 50f,
                costGrader.getWeightedGrade(student2Maze), 0.001);

    }

    /**
     * Tests that the costume grader returns the correct values.
     */
    @Test
    public void testCostumeGraderGetWeightedGradeValuesHide()
    {
        // weight = 1 --------------------------------------------------
        CostumeGrader costGrader = new CostumeGrader(1f, 5);

        assertEquals("hide had incorrect grade", 100f,
                costGrader.getWeightedGrade(student3Hide), 0.001);

        // weight = 0.5 --------------------------------------------------
        costGrader.setWeight(0.5f);

        assertEquals("hide had incorrect grade", 50f,
                costGrader.getWeightedGrade(student3Hide), 0.001);

        // ------------------ set required to 0

        costGrader.setNewRequired(0);

        assertEquals("hide had incorrect grade", 50f,
                costGrader.getWeightedGrade(student3Hide), 0.001);

    }

    /**
     * Tests that the costume grader returns the correct values.
     */
    @Test
    public void testCostumeGraderGetWeightedGradeValuesDemo()
    {
        // weight = 1 --------------------------------------------------
        CostumeGrader costGrader = new CostumeGrader(1f, 24);

        assertEquals("hide had incorrect grade", 100f,
                costGrader.getWeightedGrade(student4Demo), 0.001);

        // weight = 0.5 --------------------------------------------------
        costGrader.setWeight(0.5f);

        assertEquals("hide had incorrect grade", 50f,
                costGrader.getWeightedGrade(student4Demo), 0.001);

        // set required to 14 and weight to 1 --------------------------

        costGrader.setNewRequired(48);
        costGrader.setWeight(1f);

        assertEquals("hide had incorrect grade", 50f,
                costGrader.getWeightedGrade(student4Demo), 0.001);

    }

    /**
     * Tests the extraCredit mode. Tests that it works when turned on. Tests
     * that you cannot get over 100 when turned off.
     */
    @Test
    public void testExtraCreditModeDemo()
    {
        CostumeGrader costGrader = new CostumeGrader(1f, 13);
        costGrader.setExtraCreditMode(true);

        assertTrue("hide had incorrect grade",
                costGrader.getWeightedGrade(student4Demo) > 100f);
        
        costGrader.setExtraCreditMode(false);
        assertEquals("hide had incorrect grade", 100f,
                costGrader.getWeightedGrade(student4Demo), 0.001);

    }

}
