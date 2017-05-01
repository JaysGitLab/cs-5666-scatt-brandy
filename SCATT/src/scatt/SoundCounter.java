package scatt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses a project.json file and returns how many unique sounds are
 * present in file.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class SoundCounter
{
    private static HashMap<String, Integer> soundCounter;
    private static Pattern regex;
    private static Matcher matcher;

    /**
     * Static method to get sounds count from json file STRING.
     * 
     * @param jsonFile String representation of the json file
     * @return number of sounds, -1 if spriteCount not found
     */
    public static int mapSounds(String jsonFile)
    {
        setUp(jsonFile);
        int startName = 0;
        int i = 0;

        //
        while (matcher.find())
        {
            startName = i = matcher.end();

            // build the name of the sound file after the matched text
            while (jsonFile.charAt(i) != '\"')
            {
                ++i;
            }
            String soundName = jsonFile.substring(startName, i);

            // Add word to the HashMap.
            int occurances = 1;
            if (soundCounter.get(soundName) != null)
            {
                occurances = soundCounter.get(soundName);
                ++occurances;
            }
            soundCounter.put(soundName, occurances);
        }

        return soundCounter.size();
    }

    /**
     * Set up the necessary fields.
     * 
     * @param fileToParse the string representation of the file to search.
     */
    private static void setUp(String fileToParse)
    {
        soundCounter = new HashMap<String, Integer>();
        regex = Pattern.compile("\"soundName\": \"");
        matcher = regex.matcher(fileToParse);

    }

    /**
     * Get the number of unique sound clips.
     * 
     * @param jsonFile a string representatin of the json file
     * @return the number of unique sound clips.
     */
    public static int getUniqueSounds(String jsonFile)
    {
        // set up the hash map
        mapSounds(jsonFile);

        return soundCounter.size();
    }

    /**
     * Static method that returns the number of sounds in the sb2 file.
     * 
     * @param jsonTextFile json file
     * @return number of sounds, -1 if spriteCount not found, -10 if IOException
     */
    public static int getUniqueSounds(File jsonTextFile)
    {
        String fileStr = convertFileToString(jsonTextFile);
        return getUniqueSounds(fileStr);
    }

    /**
     * Finds the total number of times any sound is used within a project.
     * 
     * @param jsonTextFile the File containing the json to parse.
     * @return the number of sounds in various locations. This counts the number
     *         of times any sound is used, not the number of unique sounds. For
     *         example, if the user has 1 sound "pop" that is used 3 times in
     *         the project, then this method will return 3.
     */
    public static int getTotalNonUniqueSounds(File jsonTextFile)
    {
        String fileStr = convertFileToString(jsonTextFile);
        return getTotalNonUniqueSounds(fileStr);
    }

    /**
     * Finds the total number of times any sound is used within a project.
     * 
     * @param jsonTextFileAsStr the File containing the json to parse.
     * @return the number of sounds in various locations. This counts the number
     *         of times any sound is used, not the number of unique sounds. For
     *         example, if the user has 1 sound "pop" that is used 3 times in
     *         the project, then this method will return 3.
     */
    private static int getTotalNonUniqueSounds(String jsonTextFileAsStr)
    {
        mapSounds(jsonTextFileAsStr);
        int total = 0;
        for (Integer entryCount : soundCounter.values())
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
