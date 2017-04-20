package scatt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;
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
    //@formatter:off
    private static HashMap<String,
        HashMap<String, Integer>> scriptSectionHolder;
    private static HashMap<String, Integer> blockHolder;
    //@formatter:on

    private static Pattern scriptRegex;
    private static Matcher scriptMatcher;

    // private static int counter = 0;

    /**
     * Static method to get sounds count from json file STRING.
     * 
     * @param jsonFile String representation of the json file
     * @return number of sounds, -1 if spriteCount not found
     */
    public static BlockData mapBlocks(String jsonFile)
    {
        setUp(jsonFile);

        // int start = 0;
        int endPlus1 = 0;
        int i = 1;

        // Find script sections in the file
        while (scriptMatcher.find())
        {
            // get region
            endPlus1 = scriptMatcher.end();

            HashMap<String, Integer> scriptData = getBlockNamesInScript(
                    jsonFile, endPlus1);

            scriptSectionHolder.put("script" + i++, scriptData);
        }

        // int runningTotal = 0;
        // for (Integer entryCount : scriptSectionHolder.values())
        // {
        // runningTotal += entryCount;
        // }

        return new BlockData(scriptSectionHolder);
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
    private static HashMap<String, Integer> getBlockNamesInScript(
            String jsonFile, int indexForStartOfBracket)
    {
        int index = indexForStartOfBracket;

        index = countBlocksOfPositionedScript(jsonFile, index);
        HashMap<String, Integer> result = blockHolder;

        // prepare block holder for next round
        blockHolder = new HashMap<String, Integer>();

        return result;

        // counter = 0;
        // for (Integer count : blockHolder.values())
        // {
        // counter += count;
        // }
    }

    /**
     * Count blocks that have "names" surrounded in quotes.
     * 
     * @precondition fist index is first opening bracket of the script section.
     * @param jsonFile the file to count blocks on
     * @param index the index of the of the first opening bracket ('[') for a
     *            script section
     * @return the INDEX of the last immediately following the last ']' of the
     *         script collection of blocks.
     */
    private static int countBlocksOfPositionedScript(String jsonFile, int index)
    {

        Stack<Character> brackets = new Stack<Character>();
        brackets.push(jsonFile.charAt(index++));

        Stack<Character> quotes = new Stack<Character>();
        int startIndex = -1;
        int endIndex = -1;

        // loop until all brackets have been matched
        while (brackets.size() != 0)
        {
            if (jsonFile.charAt(index) == '[')
            {
                brackets.push('[');
            }
            else if (jsonFile.charAt(index) == ']')
            {
                brackets.pop();
            }
            else if (jsonFile.charAt(index) == '"')
            {
                if (quotes.size() == 0)
                {
                    // block name started
                    quotes.push('"');
                    startIndex = index;
                }
                else if (quotes.size() == 1)
                {
                    // block name end, pop and
                    quotes.pop();
                    endIndex = index;

                    // store the name with quotes trimmed off.
                    addToHashMap(jsonFile.substring(startIndex + 1, endIndex));
                }
            }
            index++;
        }

        return index;
    }

    /**
     * Intelligently adds a script's name to the HashMap. Either adds with count
     * of 1 or increments the previous count.
     * 
     * @param scriptName the scripts name to add.
     */
    private static void addToHashMap(String scriptName)
    {
        if (blockHolder.get(scriptName) == null)
        {
            blockHolder.put(scriptName, 1);
        }
        else
        {
            int count = blockHolder.get(scriptName);
            blockHolder.put(scriptName, ++count);
        }
    }

    /**
     * Set up the necessary fields.
     * 
     * @param fileToParse the string representation of the file to search.
     */
    private static void setUp(String fileToParse)
    {
        String rawScriptRegexStr = "\"scripts\": ";
        String scriptRegexStr = rawScriptRegexStr;

        // find the script location
        createNewHashMaps();

        scriptRegex = Pattern.compile(scriptRegexStr);
        scriptMatcher = scriptRegex.matcher(fileToParse);

    }

    /**
     * Creates new hash maps for the calculation.
     */
    private static void createNewHashMaps()
    {
        scriptSectionHolder = new HashMap<String, HashMap<String, Integer>>();
        blockHolder = new HashMap<String, Integer>();
    }

    /**
     * Creates new hash maps for the calculation.
     */
    private static void dereferenceHashMaps()
    {
        scriptSectionHolder = null;
        blockHolder = null;
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

    /**
     * Get the block data for the file.
     * 
     * @param jsonFile the file to parse
     * @return the block data for the file.
     */
    public static BlockData getBlockData(File jsonFile)
    {
        String fileStr = convertFileToString(jsonFile);
        return getBlockData(fileStr);
    }

    /**
     * Get the block data for the file.
     * 
     * @param jsonTextFileAsStr the file to parse
     * @return the block data for the file.
     */
    public static BlockData getBlockData(String jsonTextFileAsStr)
    {
        BlockData data = mapBlocks(jsonTextFileAsStr);
        dereferenceHashMaps();
        return data;
    }
}
