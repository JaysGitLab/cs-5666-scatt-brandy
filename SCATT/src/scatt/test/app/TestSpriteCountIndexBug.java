package scatt.test.app;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import scatt.Student;

/**
 * Tests the soundsCount class.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class TestSpriteCountIndexBug
{
    private static String howlerSb2Path;
    private static Student student4Howler;

    /**
     * Set up for the tests.
     */
    @BeforeClass
    public static void setUp()
    {
        String pathToTestDataFolder = TestUtils.getFilePathMultiOSSafe()
                + File.separator + "TestData" + File.separator;
        howlerSb2Path = pathToTestDataFolder + "Introducing Howler! Remix.sb2";

        // Note: this causes an issue!
        student4Howler = new Student(howlerSb2Path);
        System.out.println("Load successful!");
    }

    /**
     * Test a the bug we found when creating the sound counter!
     */
    @Test
    public void indexOutOfBoundsBugTest()
    {
        assertEquals("student load failed", 3, student4Howler.getSpriteCount());
    }

}
