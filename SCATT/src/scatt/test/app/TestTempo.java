package scatt.test.app;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Paths;

import org.junit.Test;

import scatt.Student;

/**
 * @author Mikeal Tests getTempo.
 * @author Matt Stone Piano throughMusical buttons
 * @version 1.0
 */
public class TestTempo
{

    /**
     * Test 1 for Tempo count.
     */
    @Test
    public void testTempo1()
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
        assertEquals("Tempo didn't match.", 60, test.getTempo());
    }
    

    /**
     * Tests the tempo count for piano. 
     */
    @Test
    public void testgetTempoPiano()
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
        assertEquals("Tempo count didnt match.", 60, test.getTempo());
    }
    
    /**
     * Tests the Tempo count for soundFlower. 
     */
    @Test
    public void testGetTempoSoundFlower()
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
        assertEquals("Tempo count didnt match.", 60, test.getTempo());
    }
    
    /**
     * Tests the Tempo count for spiralMaker. 
     */
    @Test
    public void testGetTempoSprialMaker()
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
        assertEquals("Tempo count didnt match.", 60, test.getTempo());
    }
    
    /**
     * Tests the Tempo count for DressUpTera.
     */
    @Test
    public void testGetTempoDressUpTera()
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
        assertEquals("Tempo count didnt match.", 60, test.getTempo());
    }
    
    /**
     * Tests the Tempo count for HideAndSeek.
     */
    @Test
    public void testGetTempoHideAndSeek()
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
        assertEquals("Tempo count didnt match.", 60, test.getTempo());
    }
    
    /**
     * Tests the Tempo count for Howler.
     */
    @Test
    public void testGetTempoHowler()
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
        assertEquals("Tempo count didnt match.", 60, test.getTempo());
    }

    /**
     * Tests the Tempo count for Maze.
     */
    @Test
    public void testGetTempoMaze()
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
        assertEquals("Tempo count didnt match.", 60, test.getTempo());
    }
    

    /**
     * Tests the Tempo count for MusicalButtons.
     */
    @Test
    public void testGetTempoMusicalButtons()
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
        assertEquals("Tempo count didnt match.", 60, test.getTempo());
    }

}
