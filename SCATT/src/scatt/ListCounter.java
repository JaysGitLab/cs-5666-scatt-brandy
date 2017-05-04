package scatt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * this is the list counter class that uses the jsonparser to grab the entire
 * lists tag and then uses the regex matcher to retrieve the number of instances
 * of lists that are present.
 * 
 * @author Chad
 * @version 1.0
 * 
 */
public class ListCounter
{
    /**
     * this is static method that takes a string and returns count of the list.
     * 
     * @param jsonFile - a string that contains the jsonfile contents
     * @return count - count of instances of a certain string in the jsonfile
     */
    public static int getListCount(String jsonFile)
    {
        if (!jsonFile.contains("lists"))
        {
            return -1;
        }

        int count = 0;
        int listsIndex = jsonFile.indexOf("lists");
        while (listsIndex != -1)
        {
            String str = jsonFile.substring(listsIndex + 1);
            //trim the json file so that next indexOf("lists") will work
            jsonFile = str.substring(str.indexOf("}],"));
            
            //trim string to contain only this section of lists 
            str = str.substring(0, str.indexOf("}],"));
            
            String finder = "listName";
            int lastIndex = 0;

            while (lastIndex != -1)
            {
                lastIndex = str.indexOf(finder, lastIndex);

                if (lastIndex != -1)
                {
                    count++;
                    lastIndex += finder.length();
                }
            }
            listsIndex = jsonFile.indexOf("lists");
        }
        return count;
    }

    /**
     * this is a static get list that takes a file.
     * 
     * @param jsonTextFile - some jsonfile
     * @return getListCount(fr) - passing it to the other get list
     */
    public static int getListCount(File jsonTextFile)
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
            return getListCount(fileStr);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            return -2;
        }
    }
}
