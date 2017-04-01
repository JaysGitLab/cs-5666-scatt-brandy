package scatt.test.app;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Paths;

import org.junit.Test;

import scatt.Student;

/**
 * Tests related to sprite count.
 * @author Matt
 * @author Mikeal
 * @version 1.0
 *
 */
public class TestSpriteCount
{

    /**
     * Tests the sprite count.
     */
    @Test
    public void testSpriteCount()
    {
        String curPath = Paths.get("").toAbsolutePath().toString();
        String fileName = "Pong Starter.sb2";
        String zippedDirLocStr = curPath + File.separator + "TestData"
                + File.separator + fileName;

        Student test = new Student(zippedDirLocStr);
        assertEquals("Sprite count didnt match.", 2, test.getSpriteCount());
    }

}