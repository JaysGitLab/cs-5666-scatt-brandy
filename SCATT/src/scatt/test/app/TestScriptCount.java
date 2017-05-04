package scatt.test.app;

import static org.junit.Assert.assertEquals; 

import java.io.File;
import java.nio.file.Paths;

import org.junit.Test;

import scatt.Student;

/**
 * Class that tests the script count.
 * 
 * @author Matt Stone
 * @version 0.5
 * 
 */
public class TestScriptCount
{

    /**
     * Test 1 for script count.
     */
    @Test
    public void testBasicScriptCount1()
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
        assertEquals("Script count didnt match.", 4, test.getScriptCount());
    }
    

    /**
     * Tests the script count for piano. 
     */
    @Test
    public void testScriptCountPiano()
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
        assertEquals("script count didnt match.", 13, test.getScriptCount());
    }
    
    /**
     * Tests the script count for soundFlower. 
     */
    @Test
    public void testScriptCountSoundFlower()
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
        assertEquals("script count didnt match.", 6, test.getScriptCount());
    }
    
    /**
     * Tests the script count for spiralMaker. 
     */
    @Test
    public void testScriptCountSprialMaker()
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
        assertEquals("script count didnt match.", 3, test.getScriptCount());
    }
    
    /**
     * Tests the script count for DressUpTera.
     */
    @Test
    public void testScriptCountDressUpTera()
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
        assertEquals("script count didnt match.", 15, test.getScriptCount());
    }
    
    /**
     * Tests the script count for HideAndSeek.
     */
    @Test
    public void testScriptCountHideAndSeek()
    {
        String curPath = Paths.get("").toAbsolutePath().toString();

        // fix file path for unix machine
        if (curPath.substring(curPath.length() - 4).equals(
                File.separator + "src"))
        {
            curPath = curPath.substring(0, curPath.length() - 4);
        }

        String fileName = "Hide and Seek.sb2";
        String zippedDirLocStr = curPath + File.separator + "TestData"
                + File.separator + fileName;

        Student test = new Student(zippedDirLocStr);
        //1 sprites, 2 scripts, 60 tempo
        assertEquals("script count didnt match.", 2, test.getScriptCount());
    }
    
    /**
     * Tests the script count for Howler.
     */
    @Test
    public void testScriptCountHowler()
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
        assertEquals("script count didnt match.", 8, test.getScriptCount());
    }

    /**
     * Tests the script count for Maze.
     */
    @Test
    public void testScriptCountMaze()
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
        assertEquals("script count didnt match.", 7, test.getScriptCount());
    }
    

    /**
     * Tests the script count for MusicalButtons.
     */
    @Test
    public void testScriptCountMusicalButtons()
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
        //8 scripts, 9 scripts, 60 tempo
        assertEquals("script count didnt match.", 9, test.getScriptCount());
    }

}
