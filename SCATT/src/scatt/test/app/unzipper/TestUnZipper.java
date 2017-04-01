package scatt.test.app.unzipper;

import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import scatt.UnZipper;


/**
 * @author Matt, Broderick
 * @version 1.0
 */
public class TestUnZipper
{

    private static String zippedDirectory;

    /**
     * Set up for tests.
     */
    @BeforeClass
    public static void setUp()
    {
        
        String curPath = Paths.get("").toAbsolutePath().toString();
        String fileName = "Pong Starter.sb2";
        TestUnZipper.zippedDirectory = curPath + File.separator + "TestData" + File.separator + fileName;
        //System.out.println(TestUnZipper.zippedDirectory);

    }

    /**
     * Tests unZip Method.
     */
    @Test
    public void testUnZip()
    {
        // pass the sb2 file
        UnZipper unZipper = new UnZipper(zippedDirectory);

        // extract the zip
        String unZippedDir = unZipper.unZip();
        
        System.out.println(zippedDirectory);
        System.out.println(unZippedDir);

        // test if the extraction path exists (zip = /test.sb2, unzip = /test
        String expectedDir = zippedDirectory.substring(0,
                zippedDirectory.length() - 4);
        assertTrue("the directories didn't match",
                expectedDir.equals(unZippedDir));
        
        unZipper.clean();
    }

    /**
     * Tests clean Method.
     */
    @Test
    public void testClean()
    {
        // pass the sb2 file
        UnZipper unZipper = new UnZipper(zippedDirectory);

        // extract the zip
        String unZippedDir = unZipper.unZip();
        assertTrue("Path does not exist",
                Files.isDirectory(Paths.get(unZippedDir)));

        unZipper.clean();

        assertTrue("Path Still exists",
                !Files.isDirectory(Paths.get(unZippedDir)));
    }

}
