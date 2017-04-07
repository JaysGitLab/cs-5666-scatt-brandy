package scatt.test.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import scatt.Student;
import scatt.WeightedGrader;
import scatt.gradermodules.SpriteGrader;

/**
 * All tests related to the WeightedGrader.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class TestWeightedGrader
{

    private static String pianoSb2Path;
    private static String mazeSb2Path;
    private static String hideSb2Path;
    private static Student student1Piano;
    private static Student student2Maze;
    private static Student student3Hide;
    private static SpriteGrader third25;
    private static SpriteGrader second50;
    private static SpriteGrader first100;

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

        student1Piano = new Student(pianoSb2Path);
        assertEquals("student load failed", 13, student1Piano.getSpriteCount());

        student2Maze = new Student(mazeSb2Path);
        assertEquals("student load failed", 2, student2Maze.getSpriteCount());

        student3Hide = new Student(hideSb2Path);
        assertEquals("student load failed", 1, student3Hide.getSpriteCount());

        // first = 100%, second = 50%, third = 25%
        first100 = new SpriteGrader(0.50f, 13);
        second50 = new SpriteGrader(0.50f, 26);
        third25 = new SpriteGrader(0.50f, 52);
    }

    /**
     * Restores the weights to .50for the next test.
     */
    @After
    public void restoreWeightsAfterEachTest()
    {
        first100.setWeight(0.50f);
        second50.setWeight(0.50f);
        third25.setWeight(0.50f);
    }

    /**
     * Tests related to the variable argument constructor.
     */
    @Test
    public void testWeightedGraderConstructors()
    {
        WeightedGrader shouldGive100 = new WeightedGrader(first100, first100);
        WeightedGrader shouldGive75 = new WeightedGrader(first100, second50);
        WeightedGrader shouldGive37p5 = new WeightedGrader(second50, third25);

        assertEquals(100f, shouldGive100.grade(student1Piano), 0.001);
        assertEquals(75f, shouldGive75.grade(student1Piano), 0.001);
        assertEquals(37.5f, shouldGive37p5.grade(student1Piano), 0.001);

        // test exception if sum isn't 1
        // test lower than 1
        try
        {
            // will only sum to 0.5
            new WeightedGrader(first100);
            fail("didn't throw exception");
        }
        catch (IllegalArgumentException e)
        {
            // do nothing -- an exception was thrown as expected
        }
        // test higher than 1
        try
        {
            // will over sum to 1.5
            new WeightedGrader(first100, first100, first100);
            fail("didn't throw exception");
        }
        catch (IllegalArgumentException e)
        {
            // do nothing -- an exception was thrown as expected
        }
    }

    /**
     * Tests the grade method.
     */
    @Test
    public void testGradeMethod()
    {
        // test complicated weights (make sure there is enough radex places)
        first100.setWeight(0.33333);
        second50.setWeight(0.33333);
        third25.setWeight(0.33333);

        WeightedGrader xx = new WeightedGrader(first100, second50, third25);
        WeightedGrader yy = new WeightedGrader(first100, first100, third25);
        WeightedGrader zz = new WeightedGrader(third25, second50, third25);
        WeightedGrader ww = new WeightedGrader(first100, second50, first100);
        
        assertEquals(58.333f, xx.grade(student1Piano), 0.001);
        assertEquals(74.999f, yy.grade(student1Piano), 0.001);
        assertEquals(33.333f, zz.grade(student1Piano), 0.001);
        assertEquals(83.33325f, ww.grade(student1Piano), 0.001);

    }

}
