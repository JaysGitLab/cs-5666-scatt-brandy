package scatt;

import java.util.HashMap;

/**
 * A class that contains the raw block data extracted from block counter.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class BlockData
{
    /** Script : <"name" : count>. */
    private HashMap<String, HashMap<String, Integer>> scripts;
    private int controlBlocks = 0;

    /**
     * The constructor for the block data.
     * 
     * @param scripts the raw extracted data from json.
     */
    public BlockData(HashMap<String, HashMap<String, Integer>> scripts)
    {
        this.scripts = scripts;
        analyzeData();
    }

    /**
     * Analyzes scripts and determines aspects (removes non-valid blocks etc.).
     */
    private void analyzeData()
    {
        countControlBlocks();
    }

    /**
     * Count up the control blocks within all scripts.
     * 
     * @return the number of identified control blocks
     */
    private int countControlBlocks()
    {
        int blockCount = 0;
        for (HashMap<String, Integer> script : scripts.values())
        {
            for (String blockName : script.keySet())
            {
                if (validControlBlock(blockName))
                {
                    blockCount += script.get(blockName);
                }
            }
        }
        return blockCount;
    }

    /**
     * Determines if the block name is within the control block category.
     * 
     * @param blockName the name of the block to be tested.
     * @return true if the block is a control block type, false otherwise
     */
    private boolean validControlBlock(String blockName)
    {
        // TODO Auto-generated method stub
        System.out.println("validControlBlock not implemented");
        return false;
    }

    /**
     * Returns the analyzed block number.
     * 
     * @return the true number of blocks in the data.
     */
    public int getCount()
    {
        // TODO fix
        return -1;
    }

    /**
     * TODO: remove.
     */
    public void stopCheckstyleComplaining()
    {
        System.out.println(scripts.toString());
    }
}
