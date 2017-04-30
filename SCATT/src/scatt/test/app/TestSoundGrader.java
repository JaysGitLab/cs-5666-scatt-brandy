package scatt.test.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import scatt.GraderWeightedComponent;
import scatt.Student;
import scatt.gradermodules.ScriptGrader;

/**
 * All tests related to the sprite grader.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class TestSoundGrader
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
     * Tests that the sprite grader returns the correct values.
     */
    @Test
    public void testSpriteGraderGetWeightedGradeValuesPiano()
    {
        System.out.println("piano total" + student1Piano.getUniqueSoundCount());
        System.out.println("piano Unique" + student1Piano.getTotalSoundCount());

        // constructor(weight, #scripts for 100)
        // 100 should give 100
        GraderWeightedComponent wgcForPiano = new ScriptGrader(1.0f, 13);
        assertEquals(100f, wgcForPiano.getWeightedGrade(student1Piano), 0.001);
        // CHANGE WEIGHTS
        wgcForPiano.setWeight(0.1f);

        // Current grades should be 100, weight of .2 * 100 = 20.
        assertEquals(10f, wgcForPiano.getWeightedGrade(student1Piano), 0.001);

        // CHANGE REQUIRED SCORE (casts are known to be true)
        ScriptGrader wgcP = (ScriptGrader) wgcForPiano;

        // set wgcP to a number lower (to give extra credit)
        // set others to number higher to give non-100 score.
        wgcP.setNewRequiredScripts(10);

        // set all weights to 1 for easy calculations
        wgcP.setWeight(1f);

        // ensure non-extra credit mode (should be default but may change later)
        wgcP.setExtraCreditMode(false);

        // test that extra credit doesn't allow over 100 (student:13 req:10)
        assertEquals(100f, wgcP.getWeightedGrade(student1Piano), 0.001);
        
        fail("not implmented - change to sounds not scripts");
    }

    /**
     * Tests that the sprite grader returns the correct values.
     */
    @Test
    public void testSpriteGraderGetGrade0To100ValuesPiano()
    {
        // constructor(weight, #scripts for 100)
        // 100 should give 100 (weight doesn't matter in test)
        GraderWeightedComponent wgcPiano = new ScriptGrader(1.0f, 13);
        assertEquals(100f, wgcPiano.getGradeFrom0To100(student1Piano), 0.001);

        // CHANGE WEIGHTS (tests are same since this isn't the weighted score)
        wgcPiano.setWeight(0.1f);

        // since weight shouldn't affect grade, they're the same as last test
        assertEquals(100f, wgcPiano.getGradeFrom0To100(student1Piano), 0.001);

        // CHANGE REQUIRED SCORE (casts are known to be true)
        ScriptGrader wgcP = (ScriptGrader) wgcPiano;
        wgcP.setNewRequiredScripts(10);
        wgcP.setExtraCreditMode(false);

        // test that extra credit doesn't allow over 100 (student:13 req:10)
        assertEquals(100f, wgcP.getGradeFrom0To100(student1Piano), 0.001);
        // test that student doesn't have 100 (required more than obtained)
        fail("not implmented - change to sounds not scripts");


    }
    /**
     * Tests that the sprite grader returns the correct values.
     */
    @Test
    public void testSpriteGraderGetWeightedGradeValuesMaze()
    {
        System.out.println("maze " + student2Maze.getScriptCount());

        // constructor(weight, #scripts for 100)
        // 100 should give 100

        // 100 should give 50 (by weight)
        GraderWeightedComponent wgcForMaze = new ScriptGrader(.5f, 7);
        assertEquals(50f, wgcForMaze.getWeightedGrade(student2Maze), 0.001);

        // CHANGE WEIGHTS
        wgcForMaze.setWeight(0.2f);

        // Current grades should be 100, weight of .2 * 100 = 20.
        assertEquals(20f, wgcForMaze.getWeightedGrade(student2Maze), 0.001);

        // CHANGE REQUIRED SCORE (casts are known to be true)
        ScriptGrader wgcM = (ScriptGrader) wgcForMaze;

        // set wgcP to a number lower (to give extra credit)
        // set others to number higher to give non-100 score.
        wgcM.setNewRequiredScripts(70);

        // set all weights to 1 for easy calculations
        wgcM.setWeight(1f);

        // ensure non-extra credit mode (should be default but may change later)
        wgcM.setExtraCreditMode(false);

        // test that student doesn't have 100 (required more than obtained)
        assertTrue(wgcM.getGradeFrom0To100(student2Maze) < 100);
        
        fail("not implmented - change to sounds not scripts");

    }

    /**
     * Tests that the sprite grader returns the correct values.
     */
    @Test
    public void testSpriteGraderGetGrade0To100ValuesMaze()
    {
        // 100 should give 50 (weight doesn't matter in this test)
        GraderWeightedComponent wgcForMaze = new ScriptGrader(.5f, 7);
        assertEquals(100f, wgcForMaze.getGradeFrom0To100(student2Maze), 0.001);

        // CHANGE WEIGHTS (tests are same since this isn't the weighted score)
        wgcForMaze.setWeight(0.2f);

        // since weight shouldn't affect grade, they're the same as last test
        assertEquals(100f, wgcForMaze.getGradeFrom0To100(student2Maze), 0.001);

        // CHANGE REQUIRED SCORE (casts are known to be true)
        ScriptGrader wgcM = (ScriptGrader) wgcForMaze;
        wgcM.setNewRequiredScripts(70);
        wgcM.setExtraCreditMode(false);

        // test that student doesn't have 100 (required more than obtained)
        assertTrue(wgcM.getGradeFrom0To100(student2Maze) < 100);
        fail("not implmented - change to sounds not scripts");

    }

    /**
     * Tests that the sprite grader returns the correct values.
     */
    @Test
    public void testSpriteGraderGetWeightedGradeValuesHide()
    {
        System.out.println("hide " + student3Hide.getScriptCount());

        // constructor(weight, #scripts for 100)
        // 100 should give 10 (by weight)
        GraderWeightedComponent wgcForHide = new ScriptGrader(.1f, 2);
        assertEquals(10f, wgcForHide.getWeightedGrade(student3Hide), 0.001);

        // CHANGE WEIGHTS
        wgcForHide.setWeight(1.0f);

        // Current grades should be 100, weight of .2 * 100 = 20.
        assertEquals(100f, wgcForHide.getWeightedGrade(student3Hide), 0.001);

        // CHANGE REQUIRED SCORE (casts are known to be true)
        ScriptGrader wgcH = (ScriptGrader) wgcForHide;

        // set wgcP to a number lower (to give extra credit)
        // set others to number higher to give non-100 score.
        wgcH.setNewRequiredScripts(4);

        // set all weights to 1 for easy calculations
        wgcH.setWeight(1f);

        // ensure non-extra credit mode (should be default but may change later)
        wgcH.setExtraCreditMode(false);

        // test that student doesn't have 100 (required more than obtained)
        assertEquals(50f, wgcH.getWeightedGrade(student3Hide), 0.001);
        
        fail("not implmented - change to sounds not scripts");

    }

    /**
     * Tests that the sprite grader returns the correct values.
     */
    @Test
    public void testSpriteGraderGetGrade0To100ValuesHide()
    {
        // 100 should give 10 (weight doesn't matter in this test)
        GraderWeightedComponent wgcForHide = new ScriptGrader(.1f, 2);
        assertEquals(100f, wgcForHide.getGradeFrom0To100(student3Hide), 0.001);

        // CHANGE WEIGHTS (tests are same since this isn't the weighted score)
        wgcForHide.setWeight(1.0f);

        // since weight shouldn't affect grade, they're the same as last test
        assertEquals(100f, wgcForHide.getGradeFrom0To100(student3Hide), 0.001);

        // CHANGE REQUIRED SCORE (casts are known to be true)
        ScriptGrader wgcH = (ScriptGrader) wgcForHide;
        wgcH.setNewRequiredScripts(4);
        wgcH.setExtraCreditMode(false);

        // test that extra credit doesn't allow over 100 (student:13 req:10)
        // test that student doesn't have 100 (required more than obtained)
        assertEquals(50f, wgcH.getGradeFrom0To100(student3Hide), 0.001);
        fail("not implmented - change to sounds not scripts");

    }

    /**
     * Tests the extraCredit mode. Tests that it works when turned on. Tests
     * that you cannot get over 100 when turned off.
     */
    @Test
    public void testExtraCreditModePiano()
    {
        ScriptGrader wgcP = new ScriptGrader(1.0f, 10);
        wgcP.setExtraCreditMode(true);
        
        // test allowing extra credit (13f / 10f > 1f)
        assertTrue(wgcP.getWeightedGrade(student1Piano) > 100);
        fail("not implmented - change to sounds not scripts");

    }
    /**
     * Tests the extraCredit mode. Tests that it works when turned on. Tests
     * that you cannot get over 100 when turned off.
     */
    @Test
    public void testExtraCreditModeMaze()
    {
        ScriptGrader wgcP = new ScriptGrader(1.0f, 5);
        wgcP.setExtraCreditMode(true);
        
        // test allowing extra credit (5 / 7f > 1f)
        assertTrue(wgcP.getWeightedGrade(student2Maze) > 100);
        fail("not implmented - change to sounds not scripts");

    }    /**
     * Tests the extraCredit mode. Tests that it works when turned on. Tests
     * that you cannot get over 100 when turned off.
     */
    @Test
    public void testExtraCreditModeHide()
    {
        ScriptGrader wgcP = new ScriptGrader(1.0f, 1);
        wgcP.setExtraCreditMode(true);
        
        // test allowing extra credit (2 / 1f > 1f)
        assertTrue(wgcP.getWeightedGrade(student3Hide) > 100);
        fail("not implmented - change to sounds not scripts");

    }
}
