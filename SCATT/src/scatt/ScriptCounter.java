package scatt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class that obtains the script count for a given project.json file.
 * 
 * @author Chad
 * @version 1.0
 * 
 */
public class ScriptCounter
{

    /**
     * Static method to get sprite count from json file STRING.
     * 
     * @param jsonFile String representation of the json file
     * @return number of scripts, -1 if scriptCount not found
     */
    public static int getScriptCount(String jsonFile)
    {
        if (!jsonFile.contains("scriptCount"))
        {
            return -1;
        }
        int scriptIndex = jsonFile.lastIndexOf("\"scriptCount\":");
        //int end = scriptIndex + 16;
        // int comma = jsonFile.indexOf(',', scriptIndex);
        // String ss = jsonFile.substring(scriptIndex + 15, end);
        // return Integer.parseInt(ss);

        String ss = jsonFile.substring(scriptIndex
                + "\"scriptCount\":".length());
        ss = ss.replace(',', ' ');
        Scanner ssScan = new Scanner(ss);
        int value = ssScan.nextInt();
        ssScan.close();
        return value;

    }

    /**
     * Static method that returns the number of script in a json file.
     * 
     * @param jsonTextFile json file
     * @return number of script, -1 if spriteCount not found, -2 if IOException
     */
    public static int getScriptCount(File jsonTextFile)
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
            return getScriptCount(fileStr);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            return -2;
        }
    }

}
