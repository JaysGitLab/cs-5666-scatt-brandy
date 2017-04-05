package scatt.test.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import scatt.GraderWeightedComponent;
import scatt.Student;
import scatt.gradermodules.SpriteGrader;

/**
 * Tests the non-abstract methods in WeightGraderComponent.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class TestWeightGraderComponentMethods
{

    private static String pianoSb2Path;
    private static String mazeSb2Path;
    private static String hideSb2Path;
    private static Student student1Piano;
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
        pianoSb2Path = pathToTestDataFolder + "Piano.sb2";
        mazeSb2Path = pathToTestDataFolder + "Maze Starter.sb2";
        hideSb2Path = pathToTestDataFolder + "Hide And Seek.sb2";

        student1Piano = new Student(pianoSb2Path);
        assertEquals("student load failed", 13, student1Piano.getSpriteCount());

        student2Maze = new Student(mazeSb2Path);
        assertEquals("student load failed", 2, student2Maze.getSpriteCount());

        student3Hide = new Student(hideSb2Path);
        assertEquals("student load failed", 1, student3Hide.getSpriteCount());
    }

    /**
     * Tests the extra credit methods.
     */
    @Test
    public void testExtraCredit()
    {
        GraderWeightedComponent wgc = new SpriteGrader(1f, 10);

        // test that extracredit wasn't enable by default
        assertTrue(wgc.getExtraCreditMode() != true);

        wgc.setExtraCreditMode(true);
        assertTrue(wgc.getExtraCreditMode());

        wgc.setExtraCreditMode(false);
        assertTrue(!wgc.getExtraCreditMode());

        wgc.setExtraCreditMode(true);
        assertTrue(wgc.getExtraCreditMode());

    }
    
    /**
     * Tests the extra credit methods.
     */
    @Test
    public void testModuleNameNotNull()
    {
        GraderWeightedComponent wgc = new SpriteGrader(1f, 10);
        assertTrue(wgc.getModuleName() != null);
    }

    /**
     * Tests setWeight.
     */
    @Test
    public void testWeightSetterGetter()
    {
        GraderWeightedComponent wgc = new SpriteGrader(1f, 10);

        inBoundsTest(wgc);
        wgc.setWeight(0.5f);

        inBoundsTest(wgc);
        wgc.setWeight(10f);

        inBoundsTest(wgc);
        wgc.setWeight(-10f);

        inBoundsTest(wgc);
        wgc.setWeight(0.10f);

        inBoundsTest(wgc);
        wgc.setWeight(-0.10f);

    }

    /**
     * Tests that the wgc returns a weight between 0 and 1 (inclusive).
     * 
     * @param wgc the WeightedGraderComponent to test.
     */
    public void inBoundsTest(GraderWeightedComponent wgc)
    {
        assertTrue(wgc.getWeightFrom0To1() <= 1.0);
        assertTrue(wgc.getWeightFrom0To1() >= 0);
    }
}
