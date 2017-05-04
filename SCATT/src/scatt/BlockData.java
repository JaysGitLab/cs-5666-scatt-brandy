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
