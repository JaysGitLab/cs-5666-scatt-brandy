package scatt.test.app;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Paths;

import org.junit.Test;

import scatt.Student;

/**
 * Tests related to sprite count.
 * 
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
    public void testSpriteCountPongStarter()
    {
        String curPath = Paths.get("").toAbsolutePath().toString();

        // fix file path for unix machine
        if (curPath.substring(curPath.length() - 4).equals(
                File.separator + "src"))
        {
            curPath = curPath.substring(0, curPath.length() - 4);
        }

        String fileName = "Pong Starter.sb2";
        String zippedDirLocStr = curPath + File.separator + "TestData"
                + File.separator + fileName;

        Student test = new Student(zippedDirLocStr);
        assertEquals("Sprite count didnt match.", 2, test.getSpriteCount());
    }
    

    /**
     * Tests the sprite count for piano. 
     */
    @Test
    public void testSpriteCountPiano()
    {
        String curPath = Paths.get("").toAbsolutePath().toString();

        // fix file path for unix machine
        if (curPath.substring(curPath.length() - 4).equals(
                File.separator + "src"))
        {
            curPath = curPath.substring(0, curPath.length() - 4);
        }

        String fileName = "Piano.sb2";
        String zippedDirLocStr = curPath + File.separator + "TestData"
                + File.separator + fileName;

        Student test = new Student(zippedDirLocStr);
        assertEquals("Sprite count didnt match.", 13, test.getSpriteCount());
    }
    
    /**
     * Tests the sprite count for soundFlower. 
     */
    @Test
    public void testSpriteCountSoundFlower()
    {
        String curPath = Paths.get("").toAbsolutePath().toString();

        // fix file path for unix machine
        if (curPath.substring(curPath.length() - 4).equals(
                File.separator + "src"))
        {
            curPath = curPath.substring(0, curPath.length() - 4);
        }

        String fileName = "SoundFlower.sb2";
        String zippedDirLocStr = curPath + File.separator + "TestData"
                + File.separator + fileName;

        Student test = new Student(zippedDirLocStr);
        //5 sprites, 6 scripts, 60 tempo
        assertEquals("Sprite count didnt match.", 5, test.getSpriteCount());
    }
    
    /**
     * Tests the sprite count for spiralMaker. 
     */
    @Test
    public void testSpriteCountSprialMaker()
    {
        String curPath = Paths.get("").toAbsolutePath().toString();

        // fix file path for unix machine
        if (curPath.substring(curPath.length() - 4).equals(
                File.separator + "src"))
        {
            curPath = curPath.substring(0, curPath.length() - 4);
        }

        String fileName = "Spiral Maker.sb2";
        String zippedDirLocStr = curPath + File.separator + "TestData"
                + File.separator + fileName;

        Student test = new Student(zippedDirLocStr);
        //1 sprites, 3 scripts, 60 tempo
        assertEquals("Sprite count didnt match.", 1, test.getSpriteCount());
    }
    
    /**
     * Tests the sprite count for DressUpTera.
     */
    @Test
    public void testSpriteCountDressUpTera()
    {
        String curPath = Paths.get("").toAbsolutePath().toString();

        // fix file path for unix machine
        if (curPath.substring(curPath.length() - 4).equals(
                File.separator + "src"))
        {
            curPath = curPath.substring(0, curPath.length() - 4);
        }

        String fileName = "Dress Up Tera.sb2";
        String zippedDirLocStr = curPath + File.separator + "TestData"
                + File.separator + fileName;

        Student test = new Student(zippedDirLocStr);
        //8 sprites, 15 scripts, 60 tempo
        assertEquals("Sprite count didnt match.", 8, test.getSpriteCount());
    }
    
    /**
     * Tests the sprite count for HideAndSeek.
     */
    @Test
    public void testSpriteCountHideAndSeek()
    {
        String curPath = Paths.get("").toAbsolutePath().toString();

        // fix file path for unix machine
        if (curPath.substring(curPath.length() - 4).equals(
                File.separator + "src"))
        {
            curPath = curPath.substring(0, curPath.length() - 4);
        }

        String fileName = "Hide And Seek.sb2";
        String zippedDirLocStr = curPath + File.separator + "TestData"
                + File.separator + fileName;

        Student test = new Student(zippedDirLocStr);
        //1 sprites, 2 scripts, 60 tempo
        assertEquals("Sprite count didnt match.", 1, test.getSpriteCount());
    }
    
    /**
     * Tests the sprite count for Howler.
     */
    @Test
    public void testSpriteCountHowler()
    {
        String curPath = Paths.get("").toAbsolutePath().toString();

        // fix file path for unix machine
        if (curPath.substring(curPath.length() - 4).equals(
                File.separator + "src"))
        {
            curPath = curPath.substring(0, curPath.length() - 4);
        }

        String fileName = "Introducing Howler! Remix.sb2";
        String zippedDirLocStr = curPath + File.separator + "TestData"
                + File.separator + fileName;

        Student test = new Student(zippedDirLocStr);
        //3 sprites, 8 scripts, 60 tempo
        assertEquals("Sprite count didnt match.", 3, test.getSpriteCount());
    }

    /**
     * Tests the sprite count for Maze.
     */
    @Test
    public void testSpriteCountMaze()
    {
        String curPath = Paths.get("").toAbsolutePath().toString();

        // fix file path for unix machine
        if (curPath.substring(curPath.length() - 4).equals(
                File.separator + "src"))
        {
            curPath = curPath.substring(0, curPath.length() - 4);
        }

        String fileName = "Maze Starter.sb2";
        String zippedDirLocStr = curPath + File.separator + "TestData"
                + File.separator + fileName;

        Student test = new Student(zippedDirLocStr);
        //2 sprites, 7 scripts, 60 tempo
        assertEquals("Sprite count didnt match.", 2, test.getSpriteCount());
    }
    

    /**
     * Tests the sprite count for MusicalButtons.
     */
    @Test
    public void testSpriteCountMusicalButtons()
    {
        String curPath = Paths.get("").toAbsolutePath().toString();

        // fix file path for unix machine
        if (curPath.substring(curPath.length() - 4).equals(
                File.separator + "src"))
        {
            curPath = curPath.substring(0, curPath.length() - 4);
        }

        String fileName = "Musical Buttons.sb2";
        String zippedDirLocStr = curPath + File.separator + "TestData"
                + File.separator + fileName;

        Student test = new Student(zippedDirLocStr);
        //8 sprites, 9 scripts, 60 tempo
        assertEquals("Sprite count didnt match.", 8, test.getSpriteCount());
    }
}
