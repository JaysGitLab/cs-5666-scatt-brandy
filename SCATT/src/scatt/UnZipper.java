package scatt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class UnZipper
{
    private final static int BUFFER = 2048;
    private File outputDir;
    private String zippedFilePath;
    private String unzippedFilePath;
    private boolean success;

    /**
     * @param zippedDirectory the path to the .sb2
     */
    public UnZipper(String zippedDirectory)
    {
        this.zippedFilePath = zippedDirectory;
    }

    /**
     * 
     * @return String representation of unzippped file path, or null if fail
     */
    public String unZip()
    {
        if (alreadyUnZipped())
        {
            return unzippedFilePath;
        }

        try
        {
            this.unzippedFilePath = zippedFilePath.substring(0,
                    zippedFilePath.length() - 4);
            outputDir = new File(unzippedFilePath);
            outputDir.mkdir();

            // feed bos to file
            BufferedOutputStream dest = null;

            // for reading the zip file
            FileInputStream fis = new FileInputStream(zippedFilePath);
            ZipInputStream zis = new ZipInputStream(
                    new BufferedInputStream(fis));
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null)
            {
                // System.out.println("Extracting: " + entry);
                int count;
                byte data[] = new byte[BUFFER];
                // write the files to the disk
                FileOutputStream fos = new FileOutputStream(unzippedFilePath
                        + File.separator + entry.getName());
                dest = new BufferedOutputStream(fos, BUFFER);
                while ((count = zis.read(data, 0, BUFFER)) != -1)
                {
                    dest.write(data, 0, count);
                }
                dest.flush();
                dest.close();
            }
            zis.close();
            success = true;
            return unzippedFilePath;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Determines there already exists a file path for the unzipped version.
     * 
     * @return true if an unzipped file path exists, false otherwise.
     */
    private boolean alreadyUnZipped()
    {
        return outputDir != null;
    }

    /**
     * Cleans created file path from unzip method.
     */
    public void clean()
    {
        // DO NOT TOUCH
        // System.out.println(unzipedFilePath);

        // only clean if there was a successful unzipping
        if (success)
        {
            // only clean if the file path to remove matches the zipped file.
            if (unzippedFilePath.equals(zippedFilePath.substring(0,
                    zippedFilePath.length() - 4)))
            {
                // System.out.println("to be implmented");
                for (File c : outputDir.listFiles())
                {
                    //System.out.println(c.toString());
                    if (!c.delete())
                    {
                        System.out.println("failed to delete" + c.toString());
                    }
                }
                outputDir.delete();
                outputDir = null;
            }
        }
    }

}
