package scatt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses a project.json file and returns how many unique variables
 * are present in file.
 * 
 * @author Broderick DeSantis
 * @version 1.0
 * 
 */
public class VariableCounter
{
    private static HashMap<String, Integer> variableCounter;
    private static Pattern regex;
    private static Matcher matcher;

    /**
     * Static method to get variables count from json file STRING.
     * 
     * @param jsonFile String representation of the json file
     * @return number of variables, -1 if spriteCount not found
     */
    public static int mapvariables(String jsonFile)
    {
        setUp(jsonFile);
        int startName = 0;
        int i = 0;

        //
        while (matcher.find())
        {
            startName = i = matcher.end();

            // build the name of the variable file after the matched text
            while (jsonFile.charAt(i) != '\"')
            {
                ++i;
            }
            String variableName = jsonFile.substring(startName, i);

            // Add word to the HashMap.
            int occurances = 1;
            if (variableCounter.get(variableName) != null)
            {
                occurances = variableCounter.get(variableName);
                ++occurances;
            }
            variableCounter.put(variableName, occurances);
        }

        return variableCounter.size();
    }

    /**
     * Set up the necessary fields.
     * 
     * @param fileToParse the string representation of the file to search.
     */
    private static void setUp(String fileToParse)
    {
        variableCounter = new HashMap<String, Integer>();
        regex = Pattern.compile("\"name\": \"");
        matcher = regex.matcher(fileToParse);

    }

    /**
     * Get the number of unique variable clips.
     * 
     * @param jsonFile a string representation of the json file
     * @return the number of unique variable clips.
     */
    public static int getUniqueVariables(String jsonFile)
    {
        // set up the hash map
        mapvariables(jsonFile);

        return variableCounter.size();
    }

    /**
     * Static method that returns the number of variables in the sb2 file.
     * 
     * @param jsonTextFile json file
     * @return number of variables, -1 if spriteCount not found, -10 if
     *         IOException
     */
    public static int getUniqueVariables(File jsonTextFile)
    {
        String fileStr = convertFileToString(jsonTextFile);
        return getUniqueVariables(fileStr);
    }

    /**
     * Finds the total number of times any variable is used within a project.
     * 
     * @param jsonTextFile the File containing the json to parse.
     * @return the number of variables in various locations. This counts the
     *         number of times any variable is used, not the number of unique
     *         variables. For example, if the user has 1 variable "pop" that is
     *         used 3 times in the project, then this method will return 3.
     */
    public static int getTotalNonUniqueVariables(File jsonTextFile)
    {
        String fileStr = convertFileToString(jsonTextFile);
        return getTotalNonUniqueVariables(fileStr);
    }

    /**
     * Finds the total number of times any variable is used within a project.
     * 
     * @param jsonTextFileAsStr the File containing the json to parse.
     * @return the number of variables in various locations. This counts the
     *         number of times any variable is used, not the number of unique
     *         variables. For example, if the user has 1 variable "pop" that is
     *         used 3 times in the project, then this method will return 3.
     */
    private static int getTotalNonUniqueVariables(String jsonTextFileAsStr)
    {
        mapvariables(jsonTextFileAsStr);
        int total = 0;
        for (Integer entryCount : variableCounter.values())
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
