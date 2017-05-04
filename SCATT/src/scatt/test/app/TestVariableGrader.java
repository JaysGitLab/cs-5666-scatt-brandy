package scatt.test.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import scatt.Student;
import scatt.gradermodules.VariableGrader;

/**
 * All tests related to the variable grader.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class TestVariableGrader
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
     * Tests that the variable grader returns the correct values.
     */
    @Test
    public void testVariableGraderGetWeightedGradeValuesPiano()
    {
        // weight = 1 --------------------------------------------------
        VariableGrader varGrader = new VariableGrader(1f, 1);
        // 1 variable in piano
        assertEquals("piano had incorrect grade", 100f,
                varGrader.getWeightedGrade(student1Piano), 0.001);

        // weight = 0.5 --------------------------------------------------
        varGrader.setWeight(0.5f);
        // 1 variable in piano
        assertEquals("piano had incorrect grade", 50f,
                varGrader.getWeightedGrade(student1Piano), 0.001);

        // ------------------ set required to 0

        varGrader.setNewRequired(0);
        // 1 variable in piano
        assertEquals("piano had incorrect grade", 50f,
                varGrader.getWeightedGrade(student1Piano), 0.001);

    }

    /**
     * Tests that the variable grader returns the correct values.
     */
    @Test
    public void testVariableGraderGetWeightedGradeValuesMaze()
    {
        // weight = 1 --------------------------------------------------
        VariableGrader varGrader = new VariableGrader(1f, 1);
        // 0 variables in maze
        assertEquals("maze had incorrect grade", 0f,
                varGrader.getWeightedGrade(student2Maze), 0.001);

        // weight = 0.5 --------------------------------------------------
        // 0 variables in maze
        varGrader.setWeight(0.5f);
        assertEquals("maze had incorrect grade", 0f,
                varGrader.getWeightedGrade(student2Maze), 0.001);

        // ------------------ set required to 0

        varGrader.setNewRequired(0);
        // 0 variables in maze (NOTICE NOW THIS GETS A POSITIVE GRADE)
        assertEquals("maze had incorrect grade", 50f,
                varGrader.getWeightedGrade(student2Maze), 0.001);

    }

    /**
     * Tests that the variable grader returns the correct values.
     */
    @Test
    public void testVariableGraderGetWeightedGradeValuesHide()
    {
        // weight = 1 --------------------------------------------------
        VariableGrader varGrader = new VariableGrader(1f, 1);

        // 1 variable in hide
        assertEquals("hide had incorrect grade", 100f,
                varGrader.getWeightedGrade(student3Hide), 0.001);

        // weight = 0.5 --------------------------------------------------
        varGrader.setWeight(0.5f);

        // 1 variable in hide
        assertEquals("hide had incorrect grade", 50f,
                varGrader.getWeightedGrade(student3Hide), 0.001);

        // ------------------ set required to 0

        varGrader.setNewRequired(0);

        // 1 variable in hide
        assertEquals("hide had incorrect grade", 50f,
                varGrader.getWeightedGrade(student3Hide), 0.001);

    }

    /**
     * Tests that the variable grader returns the correct values.
     */
    @Test
    public void testVariableGraderGetWeightedGradeValuesDemo()
    {
        // weight = 1 --------------------------------------------------
        VariableGrader varGrader = new VariableGrader(1f, 13);

        // 13 variables in demo
        assertEquals("hide had incorrect grade", 100f,
                varGrader.getWeightedGrade(student4Demo), 0.001);

        // weight = 0.5 --------------------------------------------------
        varGrader.setWeight(0.5f);

        // 13 variables in demo
        assertEquals("hide had incorrect grade", 50f,
                varGrader.getWeightedGrade(student4Demo), 0.001);

        // set required to 14 and weight to 1 --------------------------

        varGrader.setNewRequired(26);
        varGrader.setWeight(1f);

        // 13 vars in demo
        assertEquals("hide had incorrect grade", 50f,
                varGrader.getWeightedGrade(student4Demo), 0.001);

    }

    /**
     * Tests the extraCredit mode. Tests that it works when turned on. Tests
     * that you cannot get over 100 when turned off.
     */
    @Test
    public void testExtraCreditModeDemo()
    {
        VariableGrader varGrader = new VariableGrader(1f, 10);
        varGrader.setExtraCreditMode(true);

        // 1 variable in hide
        assertTrue("hide had incorrect grade",
                varGrader.getWeightedGrade(student4Demo) > 100f);
        
        varGrader.setExtraCreditMode(false);
        assertEquals("hide had incorrect grade", 100f,
                varGrader.getWeightedGrade(student4Demo), 0.001);

    }

}
