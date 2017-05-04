package scatt.test.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import scatt.Student;
import scatt.gradermodules.ListGrader;

/**
 * All tests related to the costume grader.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class TestListGrader
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
        hideSb2Path = pathToTestDataFolder + "Hide And Seek.sb2";
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
    public void testCostumeGraderGetWeightedGradeValuesDemo()
    {
        // weight = 1 --------------------------------------------------
        ListGrader listGrader = new ListGrader(1f, 13);

        // 13 variables in demo
        assertEquals("hide had incorrect grade", 100f,
                listGrader.getWeightedGrade(student4Demo), 0.001);

        // weight = 0.5 --------------------------------------------------
        listGrader.setWeight(0.5f);

        // 13 variables in demo
        assertEquals("hide had incorrect grade", 50f,
                listGrader.getWeightedGrade(student4Demo), 0.001);

        // set required to 14 and weight to 1 --------------------------

        listGrader.setNewRequired(26);
        listGrader.setWeight(1f);

        // 13 vars in demo
        assertEquals("hide had incorrect grade", 50f,
                listGrader.getWeightedGrade(student4Demo), 0.001);

    }

    /**
     * Tests the extraCredit mode. Tests that it works when turned on. Tests
     * that you cannot get over 100 when turned off.
     */
    @Test
    public void testExtraCreditModeDemo()
    {
        ListGrader listGrader = new ListGrader(1f, 10);
        listGrader.setExtraCreditMode(true);

        // 1 variable in hide
        assertTrue("hide had incorrect grade",
                listGrader.getWeightedGrade(student4Demo) > 100f);
        
        listGrader.setExtraCreditMode(false);
        assertEquals("hide had incorrect grade", 100f,
                listGrader.getWeightedGrade(student4Demo), 0.001);

    }

}
