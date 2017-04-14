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
 * @author
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

}
