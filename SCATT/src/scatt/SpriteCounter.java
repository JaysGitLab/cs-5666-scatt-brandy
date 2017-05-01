package scatt;

import java.io.BufferedReader; 
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class that obtains the sprite count for a given project.json file.
 * 
 * @author Mikeal
 * @version 1.0
 * 
 */
public class SpriteCounter
{

    /**
     * Static method to get sprite count from json file STRING.
     * 
     * @param jsonFile String representation of the json file
     * @return number of sprites, -1 if spriteCount not found
     */
    public static int getSpriteCount(String jsonFile)
    {
        if (!jsonFile.contains("spriteCount"))
        {
            return -1;
        }
        int scIndex = jsonFile.lastIndexOf("\"spriteCount\":");

        int end = scIndex + 16;
        String ss = jsonFile.substring(scIndex + 15, end);
        return Integer.parseInt(ss);

    }

    /**
     * Static method that returns the number of sprites in a json file.
     * 
     * @param jsonTextFile json file
     * @return number of sprites, -1 if spriteCount not found, -2 if IOException
     */
    public static int getSpriteCount(File jsonTextFile)
    {
        try
        {
            FileReader fr = new FileReader(jsonTextFile);
            BufferedReader br = new BufferedReader(fr);
            String fileStr = "";
            String line = br.readLine();
            while (line != null)
            {
                fileStr += line;
                line = br.readLine();
            }
            br.close();
            fr.close();
            return getSpriteCount(fileStr);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            return -2;
        }
    }

}
