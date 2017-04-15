package scatt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses a project.json file and returns how many script blocks are
 * present in file. This class is not thread safe due to shared counter.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class BlockCounter
{
    private static HashMap<String, Integer> scriptSectionHolder;
    private static Pattern scriptRegex;
    private static Matcher scriptMatcher;
    private static int counter = 0;

    /**
     * Static method to get sounds count from json file STRING.
     * 
     * @param jsonFile String representation of the json file
     * @return number of sounds, -1 if spriteCount not found
     */
    public static int mapBlocks(String jsonFile)
    {
        setUp(jsonFile);

        // int start = 0;
        int endPlus1 = 0;
        int i = 1;

        // Find script sections in the file
        while (scriptMatcher.find())
        {
            // get region
            // start = scriptMatcher.start();
            endPlus1 = scriptMatcher.end();

            // String scriptSection = jsonFile.substring(start, endPlus1);
            // System.out.println(scriptSection);

            int count = countBlocksInScript(jsonFile, endPlus1);

            scriptSectionHolder.put("script" + i++, count);
        }

        int runningTotal = 0;
        for (Integer entryCount : scriptSectionHolder.values())
        {
            runningTotal += entryCount;
        }

        return runningTotal;
    }

    /**
     * Get the number of script blocks for a script section.
     * 
     * @precondition the passed index should be the starting [ of the script
     *               section.
     * @param jsonFile the string representation of a file containing the script
     *            section.
     * @param indexForStartOfBracket the index for the first '[' of the script
     *            section.
     * @return the number of blocks in the script section.
     */
    private static int countBlocksInScript(String jsonFile,
            int indexForStartOfBracket)
    {
        int index = indexForStartOfBracket;
        index++;
        counter = 0;

        // loop over floating scripts
        while (jsonFile.charAt(index) != ']')
        {

            // walk over the two '['s for the block's (X,Y) position
            index = walkOverXChars(jsonFile, index, 2, '[');

            // count internal blocks - position wrapper is stepped over.
            index = countBlocks(jsonFile, index);

            // walk over the closing two ']' for the block's (X,Y) position.
            index = walkOverXChars(jsonFile, index, 2, ']');
        }

        return counter;
    }

    /**
     * Counts the number of blocks in a positioned section.
     * 
     * @precondition The position wrapper has been removed and the first bracket
     *               belongs to the first block.
     * @param jsonFile the file to scan over
     * @param index the index of the first [ of the first block.
     * @return the index after the last ] of the last block.
     */
    private static int countBlocks(String jsonFile, int index)
    {
        // TODO incomplete

        // iterate over all blocks in the subbloc
        while (jsonFile.charAt(index) != ']')
        {
            boolean ignoreCountForNextBracket = false;

            // walk to next block_start or the end_block for current block.
            while (jsonFile.charAt(index) != '['
                    && jsonFile.charAt(index) != ']')
            {
                if (jsonFile.charAt(index) == ',')
                {
                    ignoreCountForNextBracket = true;
                }
                index++;
            }

            // register block and search for internal blocks recursively
            if (jsonFile.charAt(index) == '[')
            {
                if (!ignoreCountForNextBracket)
                {
                    counter++;
                }
                index++;
                index = countBlocks(jsonFile, index);
            }
            // end of a sub-block
            // else if (jsonFile.charAt(index) == ']')
            while (jsonFile.charAt(index) == ']')
            {
                // get passed the , to prevent non-counting flag from happening.
                index++;
                if (jsonFile.charAt(index) == ',')
                {
                    index++;
                }
                return index;
            }
        }
        return index;
    }

    /**
     * Walk over X number of character Y starting at index in the string str.
     * 
     * @param str the string to analyze
     * @param index the start index
     * @param numToWalkOver the number of times to step over a particular
     *            character
     * @param character the target character
     * @return the index immediately after the character to walk over.
     */
    public static int walkOverXChars(String str, int index, int numToWalkOver,
            char character)
    {
        int itemsSeen = 0;
        while (itemsSeen < numToWalkOver)
        {
            if (str.charAt(index) == character)
            {
                itemsSeen++;
                index++;
            }
            else
            {
                index++;
            }
        }
        return index;
    }

    /**
     * Set up the necessary fields.
     * 
     * @param fileToParse the string representation of the file to search.
     */
    private static void setUp(String fileToParse)
    {

        // String interiorString = "[\\[*\\]]*";
        String rawScriptRegexStr = "\"scripts\": ";
        String scriptRegexStr = rawScriptRegexStr;

        // find the script location
        scriptSectionHolder = new HashMap<String, Integer>();
        scriptRegex = Pattern.compile(scriptRegexStr);
        scriptMatcher = scriptRegex.matcher(fileToParse);

    }

    /**
     * Finds the total number of script blocks used within a project.
     * 
     * @param jsonTextFile the File containing the json to parse.
     * @return the number of blocks in various locations. Conditions are
     *         considered blocks.
     */
    public static int getTotalBlocksUsed(File jsonTextFile)
    {
        String fileStr = convertFileToString(jsonTextFile);
        return getTotalBlocksUsed(fileStr);
    }

    /**
     * Finds the total number of script blocks used within a project.
     * 
     * @param jsonTextFileAsStr the File containing the json to parse.
     * @return the number of blocks in various locations. Conditions are
     *         considered blocks.
     */
    private static int getTotalBlocksUsed(String jsonTextFileAsStr)
    {
        int total = mapBlocks(jsonTextFileAsStr);

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
