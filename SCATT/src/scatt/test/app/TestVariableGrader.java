package scatt.test.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import scatt.Student;

/**
 * All tests related to the sprite grader.
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
    public void testSpriteGraderGetWeightedGradeValues()
    {
        // // constructor(weight, #sprites for 100)
        // // 100 should give 100
        // GraderWeightedComponent wgcForPiano = new SpriteGrader(1.0f, 13);
        // assertEquals(100f, wgcForPiano.getWeightedGrade(student1Piano),
        // 0.001);
        //
        // // 100 should give 50 (by weight)
        // GraderWeightedComponent wgcForMaze = new SpriteGrader(.5f, 2);
        // assertEquals(50f, wgcForMaze.getWeightedGrade(student2Maze), 0.001);
        //
        // // 100 should give 10 (by weight)
        // GraderWeightedComponent wgcForHide = new SpriteGrader(.1f, 1);
        // assertEquals(10f, wgcForHide.getWeightedGrade(student3Hide), 0.001);
        //
        // //CHANGE WEIGHTS
        // wgcForPiano.setWeight(0.1f);
        // wgcForMaze.setWeight(0.2f);
        // wgcForHide.setWeight(1.0f);
        //
        // //Current grades should be 100, weight of .2 * 100 = 20.
        // assertEquals(10f, wgcForPiano.getWeightedGrade(student1Piano),
        // 0.001);
        // assertEquals(20f, wgcForMaze.getWeightedGrade(student2Maze), 0.001);
        // assertEquals(100f, wgcForHide.getWeightedGrade(student3Hide), 0.001);
        //
        // //CHANGE REQUIRED SCORE (casts are known to be true)
        // SpriteGrader wgcP = (SpriteGrader) wgcForPiano;
        // SpriteGrader wgcM = (SpriteGrader) wgcForMaze;
        // SpriteGrader wgcH = (SpriteGrader) wgcForHide;
        //
        // //set wgcP to a number lower (to give extra credit)
        // //set others to number higher to give non-100 score.
        // wgcP.setNewRequiredSprites(10);
        // wgcM.setNewRequiredSprites(20);
        // wgcH.setNewRequiredSprites(2);
        //
        // //set all weights to 1 for easy calculations
        // wgcP.setWeight(1f);
        // wgcM.setWeight(1f);
        // wgcH.setWeight(1f);
        //
        // //ensure non-extra credit mode (should be default but may change
        // later)
        // wgcP.setExtraCreditMode(false);
        // wgcM.setExtraCreditMode(false);
        // wgcH.setExtraCreditMode(false);
        //
        // //test that extra credit doesn't allow over 100 (student:13 req:10)
        // assertEquals(100f, wgcP.getWeightedGrade(student1Piano), 0.001);
        // //test that student doesn't have 100 (required more than obtained)
        // assertTrue(wgcM.getGradeFrom0To100(student2Maze) < 100);
        // assertEquals(50f, wgcH.getWeightedGrade(student3Hide), 0.001);
        //
        // //test allowing extra credit (13f / 10f > 1f)
        // wgcP.setExtraCreditMode(true);
        // assertTrue(wgcP.getWeightedGrade(student1Piano) > 100);
        fail("not implemented");
    }

    /**
     * Tests that the sprite grader returns the correct values.
     */
    @Test
    public void testSpriteGraderGetGrade0To100Values()
    {
        // // constructor(weight, #sprites for 100)
        // // 100 should give 100 (weight doesn't matter in test)
        // GraderWeightedComponent wgcForPiano = new SpriteGrader(1.0f, 13);
        // assertEquals(100f, wgcForPiano.getGradeFrom0To100(student1Piano),
        // 0.001);
        //
        // // 100 should give 50 (weight doesn't matter in this test)
        // GraderWeightedComponent wgcForMaze = new SpriteGrader(.5f, 2);
        // assertEquals(100f, wgcForMaze.getGradeFrom0To100(student2Maze),
        // 0.001);
        //
        // // 100 should give 10 (weight doesn't matter in this test)
        // GraderWeightedComponent wgcForHide = new SpriteGrader(.1f, 1);
        // assertEquals(100f, wgcForHide.getGradeFrom0To100(student3Hide),
        // 0.001);
        //
        // //CHANGE WEIGHTS (tests are same since this isn't the weighted score)
        // wgcForPiano.setWeight(0.1f);
        // wgcForMaze.setWeight(0.2f);
        // wgcForHide.setWeight(1.0f);
        //
        // //since weight shouldn't affect grade, they're the same as last test
        // assertEquals(100f, wgcForPiano.getGradeFrom0To100(student1Piano),
        // 0.001);
        // assertEquals(100f, wgcForMaze.getGradeFrom0To100(student2Maze),
        // 0.001);
        // assertEquals(100f, wgcForHide.getGradeFrom0To100(student3Hide),
        // 0.001);
        //
        // //CHANGE REQUIRED SCORE (casts are known to be true)
        // SpriteGrader wgcP = (SpriteGrader) wgcForPiano;
        // SpriteGrader wgcM = (SpriteGrader) wgcForMaze;
        // SpriteGrader wgcH = (SpriteGrader) wgcForHide;
        // wgcP.setNewRequiredSprites(10);
        // wgcM.setNewRequiredSprites(20);
        // wgcH.setNewRequiredSprites(2);
        // wgcP.setExtraCreditMode(false);
        // wgcM.setExtraCreditMode(false);
        // wgcH.setExtraCreditMode(false);
        //
        // //test that extra credit doesn't allow over 100 (student:13 req:10)
        // assertEquals(100f, wgcP.getGradeFrom0To100(student1Piano), 0.001);
        // //test that student doesn't have 100 (required more than obtained)
        // assertTrue(wgcM.getGradeFrom0To100(student2Maze) < 100);
        // assertEquals(50f, wgcH.getGradeFrom0To100(student3Hide), 0.001);
        //
        // //test allowing extra credit (13f / 10f > 1f)
        // wgcP.setExtraCreditMode(true);
        // assertTrue(wgcP.getGradeFrom0To100(student1Piano) > 100);
        //
        // //@formatter:on
        fail("not implemented");
    }

    /**
     * Tests the extraCredit mode. Tests that it works when turned on. Tests
     * that you cannot get over 100 when turned off.
     */
    @Test
    public void testExtraCreditMode()
    {
        // you may have tested this in another section, which is fine - delete
        // this method.
        fail("not implemented");
    }
}
