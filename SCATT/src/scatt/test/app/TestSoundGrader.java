package scatt.test.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import scatt.GraderWeightedComponent;
import scatt.Student;
import scatt.gradermodules.SoundGrader;

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
    public void testSoundGraderGetWeightedGradeValuesPiano()
    {

        // constructor(weight, #scripts for 100)
        // 100 should give 100
        GraderWeightedComponent wgcForPiano = new SoundGrader(1, 1, 1, .5f);
        assertEquals(100f, wgcForPiano.getWeightedGrade(student1Piano), 0.001);

        // CHANGE WEIGHTS
        wgcForPiano.setWeight(0.1f);
        assertEquals(10f, wgcForPiano.getWeightedGrade(student1Piano), 0.001);
        wgcForPiano.setWeight(1.f);

        // Current grades should be 100, weight of .2 * 100 = 20.
        assertEquals(100f, wgcForPiano.getWeightedGrade(student1Piano), 0.001);

        // CHANGE REQUIRED SCORE (casts are known to be true)
        SoundGrader wgcP = (SoundGrader) wgcForPiano;

        wgcP.setNumberUniqueRequired(2);
        wgcP.setTotalNumberRequired(2);

        // test that weights work properly
        wgcP.setWeight(.5f);

        // grade is 50, at .5 weight == .25 weighted grade
        assertEquals(25, wgcP.getWeightedGrade(student1Piano), 0.001);

    }

    /**
     * Tests that the sprite grader returns the correct values.
     */
    @Test
    public void testSoundGraderGetGrade0To100ValuesPiano()
    {
        // 100 should give 100 (weight doesn't matter in test)
        GraderWeightedComponent wgcPiano = new SoundGrader(1, 1, 1, .5f);
        assertEquals(100f, wgcPiano.getGradeFrom0To100(student1Piano), 0.001);

        // CHANGE WEIGHTS (tests are same since this isn't the weighted score)
        wgcPiano.setWeight(0.1f);

        // since weight shouldn't affect grade, they're the same as last test
        assertEquals(100f, wgcPiano.getGradeFrom0To100(student1Piano), 0.001);

        // CHANGE REQUIRED SCORE (casts are known to be true)
        SoundGrader wgcP = (SoundGrader) wgcPiano;
        wgcP.setNumberUniqueRequired(2);
        wgcP.setTotalNumberRequired(2);

        assertEquals(50f, wgcP.getGradeFrom0To100(student1Piano), 0.001);

    }

    /**
     * Tests that the sprite grader returns the correct values.
     */
    @Test
    public void testSoundGraderGetWeightedGradeValuesMaze()
    {
        GraderWeightedComponent wgcForMaze = new SoundGrader(.1f, 2, 2, .5f);
        assertEquals(10f, wgcForMaze.getWeightedGrade(student2Maze), 0.001);

        // CHANGE WEIGHTS
        wgcForMaze.setWeight(1.0f);

        // Current grades should be 100, weight of .2 * 100 = 20.
        assertEquals(100f, wgcForMaze.getWeightedGrade(student2Maze), 0.001);

        // CHANGE REQUIRED SCORE (casts are known to be true)
        SoundGrader wgcM = (SoundGrader) wgcForMaze;

        // TEST UNIQUE AND TOTAL WEIGHTS
        wgcM.setTotalNumberRequired(4);
        wgcM.setTotalSoundsGradeFractionalWeight(0.75f);
        
        //50*.75 + 100 * .25 = 62.5
        assertEquals(62.5f, wgcForMaze.getWeightedGrade(student2Maze), 0.001);

        // will update total weight too
        wgcM.setUniqueSoundFractionalWeight(0.5f);
        wgcM.setTotalNumberRequired(2);
        assertEquals(100f, wgcForMaze.getWeightedGrade(student2Maze), 0.001);

        // ensure non-extra credit mode (should be default but may change later)
        wgcM.setExtraCreditMode(false);

        // test that student doesn't have 100 (required more than obtained)
        wgcM.setWeight(0.5);
        assertEquals(50f, wgcM.getWeightedGrade(student2Maze), 0.001);
        
    }

    /**
     * Tests that the sprite grader returns the correct values.
     */
    @Test
    public void testSoundGraderGetGrade0To100ValuesMaze()
    {
        GraderWeightedComponent wgcForMaze = new SoundGrader(.1f, 2, 2, .5f);
        //should still be 10, even thought weight is .1
        assertEquals(100f, wgcForMaze.getGradeFrom0To100(student2Maze), 0.001);

        // CHANGE WEIGHTS
        wgcForMaze.setWeight(1.0f);

        // Current grades should be 100, weight of .2 * 100 = 20.
        assertEquals(100f, wgcForMaze.getGradeFrom0To100(student2Maze), 0.001);

        // CHANGE REQUIRED SCORE (casts are known to be true)
        SoundGrader wgcM = (SoundGrader) wgcForMaze;

        // TEST UNIQUE AND TOTAL WEIGHTS
        wgcM.setTotalNumberRequired(4);
        wgcM.setTotalSoundsGradeFractionalWeight(0.75f);
        
        //50*.75 + 100 * .25 = 62.5
        assertEquals(62.5f, wgcForMaze.getGradeFrom0To100(student2Maze), 0.001);

        // will update total weight too
        wgcM.setUniqueSoundFractionalWeight(0.5f);
        wgcM.setTotalNumberRequired(2);
        assertEquals(100f, wgcForMaze.getGradeFrom0To100(student2Maze), 0.001);

        // ensure non-extra credit mode (should be default but may change later)
        wgcM.setExtraCreditMode(false);

        // test that overall weight doesn't affectFrom0To100
        wgcM.setWeight(0.5);
        assertEquals(100f, wgcM.getGradeFrom0To100(student2Maze), 0.001);

    }

    /**
     * Tests that the sprite grader returns the correct values.
     */
    @Test
    public void testSoundGraderGetWeightedGradeValuesHide()
    {

        // constructor(weight, #scripts for 100)
        // 100 should give 10 (by weight)
        GraderWeightedComponent wgcForHide = new SoundGrader(.1f, 3, 4, .5f);
        assertEquals(10f, wgcForHide.getWeightedGrade(student3Hide), 0.001);

        // CHANGE WEIGHTS
        wgcForHide.setWeight(1.0f);

        // Current grades should be 100, weight of .2 * 100 = 20.
        assertEquals(100f, wgcForHide.getWeightedGrade(student3Hide), 0.001);

        // CHANGE REQUIRED SCORE (casts are known to be true)
        SoundGrader wgcH = (SoundGrader) wgcForHide;

        // TEST UNIQUE AND TOTAL WEIGHTS
        wgcH.setTotalNumberRequired(8);
        wgcH.setTotalSoundsGradeFractionalWeight(0.75f);
        assertEquals(62.5f, wgcForHide.getWeightedGrade(student3Hide), 0.001);

        // will update total weight too
        wgcH.setUniqueSoundFractionalWeight(0.5f);
        wgcH.setTotalNumberRequired(4);
        assertEquals(100f, wgcForHide.getWeightedGrade(student3Hide), 0.001);

        // ensure non-extra credit mode (should be default but may change later)
        wgcH.setExtraCreditMode(false);

        // test that student doesn't have 100 (required more than obtained)
        wgcH.setWeight(0.5);
        assertEquals(50f, wgcH.getWeightedGrade(student3Hide), 0.001);
    }

    /**
     * Tests that the sprite grader returns the correct values.
     */
    @Test
    public void testSoundGraderGetGrade0To100ValuesHide()
    {
        
        //Almost identical test, but weight shouldn't change
        GraderWeightedComponent wgcForHide = new SoundGrader(.1f, 3, 4, .5f);
        wgcForHide.setWeight(0.1f);
        assertEquals(100f, wgcForHide.getGradeFrom0To100(student3Hide), 0.001);

        // CHANGE WEIGHTS
        //wgcForHide.setWeight(1.0f);

        // Current grades should be 100, weight of .2 * 100 = 20.
        assertEquals(100f, wgcForHide.getGradeFrom0To100(student3Hide), 0.001);

        // CHANGE REQUIRED SCORE (casts are known to be true)
        SoundGrader wgcH = (SoundGrader) wgcForHide;

        // TEST UNIQUE AND TOTAL WEIGHTS
        wgcH.setTotalNumberRequired(8);
        wgcH.setTotalSoundsGradeFractionalWeight(0.75f);
        assertEquals(62.5f, wgcForHide.getGradeFrom0To100(student3Hide), 0.001);

        // will update total weight too
        wgcH.setUniqueSoundFractionalWeight(0.5f);
        wgcH.setTotalNumberRequired(4);
        assertEquals(100f, wgcForHide.getGradeFrom0To100(student3Hide), 0.001);

        // ensure non-extra credit mode (should be default but may change later)
        wgcH.setExtraCreditMode(false);

        // test that student doesn't have 100 (required more than obtained)
        wgcH.setWeight(0.5);
        assertEquals(100f, wgcH.getGradeFrom0To100(student3Hide), 0.001);
        

    }

    /**
     * Tests the extraCredit mode. Tests that it works when turned on. Tests
     * that you cannot get over 100 when turned off.
     */
    @Test
    public void testExtraCreditModeMaze()
    {
        SoundGrader wgcP = new SoundGrader(1.0f, 1, 1, .5f);
        wgcP.setExtraCreditMode(true);

        // has more than 1 sound, so should have extra credit
        assertTrue(wgcP.getWeightedGrade(student2Maze) > 100);

    }

    /**
     * Tests the extraCredit mode. Tests that it works when turned on. Tests
     * that you cannot get over 100 when turned off.
     */
    @Test
    public void testExtraCreditModeHide()
    {
        SoundGrader wgcP = new SoundGrader(1.0f, 1, 1, .5f);
        wgcP.setExtraCreditMode(true);

        // has more than 1 sound, so should have extra credit points
        assertTrue(wgcP.getWeightedGrade(student3Hide) > 100);

    }
}
