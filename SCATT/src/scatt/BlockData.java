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
    private static HashMap<String, Boolean> motionBlockID;
    private static HashMap<String, Boolean> looksBlocksID;
    private static HashMap<String, Boolean> soundBlocksID;
    private static HashMap<String, Boolean> penBlockIDs;
    private static HashMap<String, Boolean> dataBlockIDs;
    private static HashMap<String, Boolean> eventBlockIDs;
    private static HashMap<String, Boolean> controlBlockID;
    private static HashMap<String, Boolean> senseBlockIDs;
    private static HashMap<String, Boolean> operBlockIDs;
    private static HashMap<String, Boolean> moreBlockIDs;

    /** Script : <"name" : count>. */
    private HashMap<String, HashMap<String, Integer>> scripts;
    private Integer motionBlockCount = null;
    private Integer lookBlockCount = null;
    private Integer soundBlockCount = null;
    private Integer penBlockCount = null;
    private Integer dataBlockCount = null;
    private Integer eventBlockCount = null;
    private Integer controlBlockCount = null;
    private Integer senseBlockCount = null;
    private Integer operBlockCount = null;
    private Integer moreBlockCount = null;

    /**
     * The constructor for the block data.
     * 
     * @param scripts the raw extracted data from json.
     */
    public BlockData(HashMap<String, HashMap<String, Integer>> scripts)
    {
        BlockData.initAllBlockTypeIdentifierData();
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
     * Returns the analyzed block number.
     * 
     * @return the true number of blocks in the data.
     */
    public int getTotalCount()
    {
        int countTotal = 0;
        countTotal += countMotionBlocks();
        countTotal += countLooksBlocks();
        countTotal += countSoundBlocks();
        countTotal += countPenBlocks();
        countTotal += countDataBlocks();
        countTotal += countEventBlocks();
        countTotal += countControlBlocks();
        countTotal += countSenseBlocks();
        countTotal += countOperatorBlocks();
        countTotal += countMoreSectionBlocks();

        return countTotal;
    }

    /**
     * Count up the control blocks within all scripts.
     * 
     * @return the number of identified control blocks
     */
    private int countControlBlocks()
    {
        if (controlBlockCount == null)
        {
            controlBlockCount = countBlocksIn(BlockData.controlBlockID);
        }
        return controlBlockCount;
    }

    /**
     * Get the number of blocks that occurred in the section cateogry.
     * 
     * @return the number of blocks that occurred in the section cateogry.
     */
    private int countMoreSectionBlocks()
    {
        if (moreBlockCount == null)
        {
            moreBlockCount = countBlocksIn(BlockData.moreBlockIDs);
        }
        return moreBlockCount;
    }

    /**
     * Get the number of blocks that occured in the operator category.
     * @return the number of blocks that occured in the operator category.
     */
    private int countOperatorBlocks()
    {
        if (operBlockCount == null)
        {
            operBlockCount = countBlocksIn(BlockData.operBlockIDs);
        }
        return operBlockCount;
    }

    /**
     * Get the number of blocks that occurred in the sense category.
     * @return the number of blocks that occurred in the sense category.
     */
    private int countSenseBlocks()
    {
        if (senseBlockCount == null)
        {
            senseBlockCount = countBlocksIn(BlockData.senseBlockIDs);
        }
        return senseBlockCount;
    }

    /**
     * Get the number of blocks that occurred in the event category.
     * @return the number of blocks that occurred in the event category.
     */
    private int countEventBlocks()
    {
        if (eventBlockCount == null)
        {
            eventBlockCount = countBlocksIn(BlockData.eventBlockIDs);
        }
        return eventBlockCount;
    }

    /**
     * Get the number of blocks that occurred in the data category.
     * @return the number of blocks that occurred in the data category.
     */
    private int countDataBlocks()
    {
        if (dataBlockCount == null)
        {
            dataBlockCount = countBlocksIn(BlockData.dataBlockIDs);
        }
        return dataBlockCount;
    }

    /**
     * Get the number of block types in the pen category that occurred.
     * 
     * @return the number of block types in the pen category that occurred.
     */
    private int countPenBlocks()
    {
        if (penBlockCount == null)
        {
            penBlockCount = countBlocksIn(BlockData.penBlockIDs);
        }
        return penBlockCount;
    }

    /**
     * Get the number of blocks in the sound category.
     * 
     * @return the number of blocks in the sound category.
     */
    private int countSoundBlocks()
    {
        if (soundBlockCount == null)
        {
            soundBlockCount = countBlocksIn(BlockData.soundBlocksID);
        }
        return soundBlockCount;
    }

    /**
     * Get the number of blocks that occurred in the looks category.
     * 
     * @return the number of blocks in the looks category
     */
    private int countLooksBlocks()
    {
        if (lookBlockCount == null)
        {
            lookBlockCount = countBlocksIn(BlockData.looksBlocksID);
        }
        return lookBlockCount;
    }

    /**
     * Count the number of times a motion block appeared in the file.
     * 
     * @return the number of motion blocks.
     */
    public int countMotionBlocks()
    {
        if (motionBlockCount == null)
        {
            motionBlockCount = countBlocksIn(BlockData.motionBlockID);
        }
        return motionBlockCount;
    }

    /**
     * Counts blocks in the scripts based on a subset of block types.
     * 
     * @param subsetOfBlockIDs hash map containing all block IDs that are to be
     *            counted.
     * @return the number of blocks in the set of blockIds
     */
    private int countBlocksIn(HashMap<String, Boolean> subsetOfBlockIDs)
    {
        int blockCount = 0;
        for (HashMap<String, Integer> script : scripts.values())
        {
            for (String blockName : script.keySet())
            {
                if (subsetOfBlockIDs.get(blockName) != null)
                {
                    // add to the count the number of times this block occurred.
                    blockCount += script.get(blockName);
                }
            }
        }
        return blockCount;
    }

    /**
     * Sets up the static fields to contain data usable in determine a blocks
     * category.
     */
    public static void initAllBlockTypeIdentifierData()
    {
        if (controlBlockID == null)
        {
            loadMotionBlockDataToMemory();
            loadLooksBlocksDataToMemeory();
            loadSoundBlocksDataToMemory();
            loadPenBlocksDataToMemory();
            loadDataSectionBlocksToMemory();
            loadEventBlockDataToMemory();
            loadSensingBlockDataToMemory();
            loadControlBlockDataToMemory();
            loadOperatorBlockDataToMemory();
            loadMoreBlocksSectionToMemory();

        }
    }

    /**
     * Load the "more blocks" section to memory for parsing.
     */
    private static void loadMoreBlocksSectionToMemory()
    {
        moreBlockIDs = new HashMap<String, Boolean>();

        moreBlockIDs.put("procDef", true);
        moreBlockIDs.put("LEGO WeDo\u001fwhenDistance", true);
        moreBlockIDs.put("LEGO WeDo\u001fmotorOnFor", true);
        moreBlockIDs.put("LEGO WeDo\u001fmotorOn", true);
        moreBlockIDs.put("LEGO WeDo\u001fmotorOff", true);
        moreBlockIDs.put("LEGO WeDo\u001fstartMotorPower", true);
        moreBlockIDs.put("LEGO WeDo\u001fsetMotorDirection", true);
        moreBlockIDs.put("LEGO WeDo\u001fwhenTilt", true);
        moreBlockIDs.put("LEGO WeDo\u001fsetMotorDirection", true);
        moreBlockIDs.put("LEGO WeDo 2.0\u001fwhenDistance", true);
        moreBlockIDs.put("LEGO WeDo 2.0\u001fmotorOnFor", true);
        moreBlockIDs.put("LEGO WeDo 2.0\u001fmotorOn", true);
        moreBlockIDs.put("LEGO WeDo 2.0\u001fmotorOff", true);
        moreBlockIDs.put("LEGO WeDo 2.0\u001fstartMotorPower", true);
        moreBlockIDs.put("LEGO WeDo 2.0\u001fsetLED", true);
        moreBlockIDs.put("LEGO WeDo 2.0\u001fplayNote", true);
        moreBlockIDs.put("LEGO WeDo 2.0\u001fwhenTilted", true);
        moreBlockIDs.put("LEGO WeDo 2.0\u001fisTilted", true);
        moreBlockIDs.put("LEGO WeDo 2.0\u001fgetTilt", true);
        moreBlockIDs.put("PicoBoard\u001fwhenSensorConnected", true);
        moreBlockIDs.put("PicoBoard\u001fwhenSensorPass", true);
        moreBlockIDs.put("PicoBoard\u001fsensorPressed", true);
        moreBlockIDs.put("PicoBoard\u001fsensor", true);

    }

    /**
     * Loads the operator ID data to the static hashmap.
     */
    private static void loadOperatorBlockDataToMemory()
    {
        operBlockIDs = new HashMap<String, Boolean>();

        operBlockIDs.put("+", true);
        operBlockIDs.put("-", true);
        operBlockIDs.put("*", true);
        operBlockIDs.put("\\/", true);
        operBlockIDs.put("randomFrom:to:", true);
        operBlockIDs.put("<", true);
        operBlockIDs.put("=", true);
        operBlockIDs.put(">", true);
        operBlockIDs.put("&", true);
        operBlockIDs.put("|", true);
        operBlockIDs.put("not", true);
        operBlockIDs.put("concatenate:with:", true);
        operBlockIDs.put("letter:of:", true);
        operBlockIDs.put("stringLength:", true);
        operBlockIDs.put("%", true);
        operBlockIDs.put("rounded", true);
        operBlockIDs.put("computeFunction:of:", true);
    }

    /**
     * Load block IDs for the sensing section into the static hashmap.
     */
    private static void loadSensingBlockDataToMemory()
    {
        senseBlockIDs = new HashMap<String, Boolean>();

        senseBlockIDs.put("doAsk", true);
        senseBlockIDs.put("touching:", true);
        senseBlockIDs.put("touchingColor:", true);
        senseBlockIDs.put("color:sees:", true);
        senseBlockIDs.put("distanceTo:", true);
        senseBlockIDs.put("keyPressed:", true);
        senseBlockIDs.put("senseVideoMotion", true);
        senseBlockIDs.put("soundLevel", true);
        senseBlockIDs.put("mousePressed", true);
        senseBlockIDs.put("mouseX", true);
        senseBlockIDs.put("mouseY", true);
        senseBlockIDs.put("timer", true);
        senseBlockIDs.put("getAttribute:of:", true);
        senseBlockIDs.put("timestamp", true);
        senseBlockIDs.put("getUserName", true);
        senseBlockIDs.put("doAsk", true);
        senseBlockIDs.put("timerReset", true);

    }

    /**
     * Load block IDs for events into static hash map.
     */
    private static void loadEventBlockDataToMemory()
    {
        eventBlockIDs = new HashMap<String, Boolean>();

        eventBlockIDs.put("whenGreenFlag", true);
        eventBlockIDs.put("whenKeyPressed", true);
        eventBlockIDs.put("whenClicked", true);
        eventBlockIDs.put("whenSceneStarts", true);
        eventBlockIDs.put("whenSensorGreaterThan", true);
        eventBlockIDs.put("whenIReceive", true);
        eventBlockIDs.put("broadcast:", true);
        eventBlockIDs.put("doBroadcastAndWait", true);
    }

    /**
     * Load block IDs for the data section to the static hash maps.
     */
    private static void loadDataSectionBlocksToMemory()
    {

        dataBlockIDs = new HashMap<String, Boolean>();

        dataBlockIDs.put("setVar:to:", true);
        dataBlockIDs.put("changeVar:by:", true);
        dataBlockIDs.put("showVariable:", true);
        dataBlockIDs.put("hideVariable:", true);
        dataBlockIDs.put("append:toList:", true);
        dataBlockIDs.put("deleteLine:ofList:", true);
        dataBlockIDs.put("insert:at:ofList:", true);
        dataBlockIDs.put("setLine:ofList:to:", true);
        dataBlockIDs.put("showList:", true);
        dataBlockIDs.put("hideList:", true);
        dataBlockIDs.put("getLine:ofList:", true);
        dataBlockIDs.put("lineCountOfList:", true);
        dataBlockIDs.put("list:contains:", true);
        dataBlockIDs.put("showList:", true);
        dataBlockIDs.put("contentsOfList:", true);
        dataBlockIDs.put("readVariable", true);

    }

    /**
     * load the blocks IDs for blocks in the "pen" section.
     */
    private static void loadPenBlocksDataToMemory()
    {
        penBlockIDs = new HashMap<String, Boolean>();

        penBlockIDs.put("stampCostume", true);
        penBlockIDs.put("clearPenTrails", true);
        penBlockIDs.put("putPenDown", true);
        penBlockIDs.put("putPenUp", true);
        penBlockIDs.put("penColor:", true);
        penBlockIDs.put("setPenHueTo:", true);
        penBlockIDs.put("changePenHueBy:", true);
        penBlockIDs.put("setPenHueTo:", true);
        penBlockIDs.put("changePenShadeBy:", true);
        penBlockIDs.put("setPenShadeTo:", true);
        penBlockIDs.put("changePenSizeBy:", true);
        penBlockIDs.put("penSize:", true);
    }

    /**
     * Load the string ID for all sound blocks into memory.
     */
    /**
     * 
     */
    private static void loadSoundBlocksDataToMemory()
    {
        soundBlocksID = new HashMap<String, Boolean>();

        soundBlocksID.put("playSound:", true);
        soundBlocksID.put("doPlaySoundAndWait", true);
        soundBlocksID.put("stopAllSounds", true);
        soundBlocksID.put("playDrum", true);
        soundBlocksID.put("rest:elapsed:from:", true);
        soundBlocksID.put("noteOn:duration:elapsed:from:", true);
        soundBlocksID.put("instrument:", true);
        soundBlocksID.put("changeVolumeBy:", true);
        soundBlocksID.put("setVolumeTo:", true);
        soundBlocksID.put("changeTempoBy:", true);
        soundBlocksID.put("setTempoTo:", true);
        soundBlocksID.put("volume", true);
        soundBlocksID.put("tempo", true);
    }

    /**
     * Load the "looks" blocks ID strings into memory.
     */
    private static void loadLooksBlocksDataToMemeory()
    {
        looksBlocksID = new HashMap<String, Boolean>();

        looksBlocksID.put("say:duration:elapsed:from:", true);
        looksBlocksID.put("say:", true);
        looksBlocksID.put("think:duration:elapsed:from:", true);
        looksBlocksID.put("think:", true);
        looksBlocksID.put("show", true);
        looksBlocksID.put("hide", true);
        looksBlocksID.put("lookLike:", true);
        looksBlocksID.put("nextCostume", true);
        looksBlocksID.put("startScene", true);
        looksBlocksID.put("changeGraphicEffect:by:", true);
        looksBlocksID.put("setGraphicEffect:to:", true);
        looksBlocksID.put("filterReset", true);
        looksBlocksID.put("changeSizeBy:", true);
        looksBlocksID.put("setSizeTo:", true);
        looksBlocksID.put("comeToFront", true);
        looksBlocksID.put("goBackByLayers:", true);

        looksBlocksID.put("costumeIndex", true);
        looksBlocksID.put("sceneName", true);
        looksBlocksID.put("scale", true);
    }

    /**
     * Load the motion blocks identifiers into static hashmap.
     */
    private static void loadMotionBlockDataToMemory()
    {
        motionBlockID = new HashMap<String, Boolean>();

        motionBlockID.put("forward:", true);
        motionBlockID.put("turnRight:", true);
        motionBlockID.put("turnLeft:", true);
        motionBlockID.put("heading:", true);
        motionBlockID.put("pointTowards:", true);
        motionBlockID.put("gotoX:y:", true);
        motionBlockID.put("gotoSpriteOrMouse:", true);
        motionBlockID.put("glideSecs:toX:y:elapsed:from:", true);
        motionBlockID.put("changeXposBy:", true);
        motionBlockID.put("xpos:", true);
        motionBlockID.put("changeYposBy:", true);
        motionBlockID.put("ypos:", true);
        motionBlockID.put("bounceOffEdge", true);
        motionBlockID.put("setRotationStyle", true);
        // motionBlockID.put("xpos", true);
        motionBlockID.put("ypos", true);
        motionBlockID.put("heading", true);
    }

    /**
     * Load the extracted control block tags into memory.
     */
    public static void loadControlBlockDataToMemory()
    {
        controlBlockID = new HashMap<String, Boolean>();
        controlBlockID.put("wait:elapsed:from", true);
        controlBlockID.put("doRepeat", true);
        controlBlockID.put("doIf", true);
        controlBlockID.put("doIfElse", true);
        controlBlockID.put("doWaitUntil", true);
        controlBlockID.put("doUntil", true);
        controlBlockID.put("createCloneOf", true);
        controlBlockID.put("doForever", true);
        controlBlockID.put("stopScripts", true);
        controlBlockID.put("whenCloned", true);
        controlBlockID.put("deleteClone", true);
    }
}
