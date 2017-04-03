package scatt.test.app;

import java.io.File;
import java.nio.file.Paths;

/**
 * @author Matt Stone
 * @version 1.0
 */
public class TestUtils
{
    /**
     * Because compiling happens at different folders for different builds
     * (Eclipse vs unix) the path to the data needed for tests. On unix, the src
     * folder is present at the end of the path. This method returns the current
     * directory, but removes the src portion if it is present.
     * 
     * This method should ONLY be used for junit test and should not be used
     * within the actual application.
     * 
     * @return the current path that is operating system independent.
     */
    public static String getFilePathMultiOSSafe()
    {
        String curPath = Paths.get("").toAbsolutePath().toString();

        // fix file path for unix machine
        if (curPath.substring(curPath.length() - 4).equals(
                File.separator + "src"))
        {
            curPath = curPath.substring(0, curPath.length() - 4);
        }

        return curPath;
    }
}
