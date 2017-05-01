package scatt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses a project.json fileand returns how many unique costumes are
 * present in file.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class CostumeCounter
{
    private static HashMap<String, Integer> costumeCounter;
    private static Pattern regex;
    private static Matcher matcher;

    /**
     * Static method to get costumes count from json file STRING.
     * 
     * @param jsonFile String representation of the json file
     * @return number of costumes, -1 if spriteCount not found
     */
    public static int mapCostumes(String jsonFile)
    {
        setUp(jsonFile);
        int startName = 0;
        int i = 0;

        //
        while (matcher.find())
        {
            startName = i = matcher.end();

            // build the name of the costume file after the matched text
            while (jsonFile.charAt(i) != '\"')
            {
                ++i;
            }
            String costumeName = jsonFile.substring(startName, i);

            // Add word to the HashMap.
            int occurances = 1;
            if (costumeCounter.get(costumeName) != null)
            {
                occurances = costumeCounter.get(costumeName);
                ++occurances;
            }
            costumeCounter.put(costumeName, occurances);
        }

        return costumeCounter.size();
    }

    /**
     * Set up the necessary fields.
     * 
     * @param fileToParse the string representation of the file to search.
     */
    private static void setUp(String fileToParse)
    {
        costumeCounter = new HashMap<String, Integer>();
        regex = Pattern.compile("\"costumeName\": \"");
        matcher = regex.matcher(fileToParse);

    }

    /**
     * Get the number of unique costume clips.
     * 
     * @param jsonFile a string representation of the json file
     * @return the number of unique costume clips.
     */
    public static int getUniqueCostumes(String jsonFile)
    {
        // set up the hash map
        mapCostumes(jsonFile);

        return costumeCounter.size();
    }

    /**
     * Static method that returns the number of costumes in the sb2 file.
     * 
     * @param jsonTextFile json file
     * @return number of costumes, -1 if spriteCount not found, -10 if IOException
     */
    public static int getUniqueCostumes(File jsonTextFile)
    {
        String fileStr = convertFileToString(jsonTextFile);
        return getUniqueCostumes(fileStr);
    }

    /**
     * Finds the total number of times any costume is used within a project.
     * 
     * @param jsonTextFile the File containing the json to parse.
     * @return the number of costumes in various locations. This counts the number
     *         of times any costume is used, not the number of unique costumes. For
     *         example, if the user has 1 costume "pop" that is used 3 times in
     *         the project, then this method will return 3.
     */
    public static int getTotalNonUniqueCostumes(File jsonTextFile)
    {
        String fileStr = convertFileToString(jsonTextFile);
        return getTotalNonUniqueCostumes(fileStr);
    }

    /**
     * Finds the total number of times any costume is used within a project.
     * 
     * @param jsonTextFileAsStr the File containing the json to parse.
     * @return the number of costumes in various locations. This counts the number
     *         of times any costume is used, not the number of unique costumes. For
     *         example, if the user has 1 costume "pop" that is used 3 times in
     *         the project, then this method will return 3.
     */
    private static int getTotalNonUniqueCostumes(String jsonTextFileAsStr)
    {
        mapCostumes(jsonTextFileAsStr);
        int total = 0;
        for (Integer entryCount : costumeCounter.values())
        {
            total += entryCount;
        }
        return total;
    }

    /**
     * Converts the contents of a file into a large String.
     * 
     * @param jsonTextFile the File to be converted to a String.
     * @return A string representation of the file.
     */
    private static String convertFileToString(File jsonTextFile)
    {
        try
        {
            FileReader fr = new FileReader(jsonTextFile);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer fileStr = new StringBuffer();
            String line = br.readLine();
            while (line != null)
            {
                fileStr.append(line);
                line = br.readLine();
            }
            br.close();
            fr.close();
            return fileStr.toString();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
