package scatt.test.app;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Paths;

import org.junit.Test;

import scatt.ListCounter;

/**
 * Tests related to list count. - needs more
 * 
 * @version 1.0
 * @author chad
 * @author matt 
 * 
 */
public class TestListCount
{

    /**
     * Tests the sprite count.
     */
    @Test
    public void testListCount()
    {
        String curPath = Paths.get("").toAbsolutePath().toString();

        // fix file path for unix machine
        if (curPath.substring(curPath.length() - 4).equals(
                File.separator + "src"))
        {
            curPath = curPath.substring(0, curPath.length() - 4);
        }

        String fileName = "Everything Demo.json";
        String unzippedDirLocStr = curPath + File.separator + "TestData"
                + File.separator + "unzipped folders" + File.separator
                + fileName;

        File file = new File(unzippedDirLocStr);

        assertEquals("List count didnt match.", 13,
                ListCounter.getListCount(file));
    }

}
