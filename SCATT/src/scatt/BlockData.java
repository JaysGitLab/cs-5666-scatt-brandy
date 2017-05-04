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
        
        operBlockIDs.put("+",  true);
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
