package scatt.test.app;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import scatt.Student;
import scatt.gradermodules.TempoGrader;

/**
 * All tests related to the costume grader.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class TestTempoGrader
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
    public void testTempoGraderGetWeightedGradeValuesPiano()
    {
        TempoGrader tempoGrader = new TempoGrader(1f, 60);
        // 1 variable in piano
        assertEquals("piano had incorrect grade", 100f,
                tempoGrader.getWeightedGrade(student1Piano), 0.001);

        tempoGrader.setWeight(0.5f);
        // 1 variable in piano
        assertEquals("piano had incorrect grade", 50f,
                tempoGrader.getWeightedGrade(student1Piano), 0.001);


        tempoGrader.setNewRequired(0);
        // 1 variable in piano
        assertEquals("piano had incorrect grade", 50f,
                tempoGrader.getWeightedGrade(student1Piano), 0.001);

    }

  
    /**
     * Tests that the costume grader returns the correct values.
     */
    @Test
    public void testTempoGraderGetWeightedGradeValuesDemo()
    {
        // weight = 1 --------------------------------------------------
        TempoGrader tempoGrader = new TempoGrader(1f, 60);

        assertEquals("demo had incorrect grade", 100f,
                tempoGrader.getWeightedGrade(student4Demo), 0.001);

        // weight = 0.5 --------------------------------------------------
        tempoGrader.setWeight(0.5f);

        assertEquals("demo had incorrect grade", 50f,
                tempoGrader.getWeightedGrade(student4Demo), 0.001);

    }


}
