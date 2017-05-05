package scatt.gradermodules;

import scatt.Gradeable;
import scatt.GraderWeightedComponent;
import scatt.Student;

/**
 * A weighted average grader component for the block counts.
 * 
 * @author Matt stone
 * @version 1.0
 * 
 */
public class BlockGrader extends GraderWeightedComponent
{
    public static final String MODULE_NAME = "Block Grader";
    private double reqMotBlks;
    private double reqLookBlks;
    private double reqSndBlks;
    private double reqPenBlks;
    private double reqDataBlks;
    private double reqEventBlks;
    private double reqConBlks;
    private double reqSenseBlks;
    private double reqOperBlks;
    private double reqMrBlks;

    /**
     * Create a block grader that equally distributes grades between the
     * different type of blocks.
     * 
     * @param initialWeight the weight in the weighted grader
     * @param requiredMotionBlocks the required number of motion blocks
     * @param requiredLooksBlocks the required number of looks blocks
     * @param requiredSoundBlocks the required number of sound blocks
     * @param requiredPenBlocks the required number of pen block
     * @param requiredDataBlocks the required number of data blocks
     * @param requiredEventBlocks the required number of event blocks
     * @param requiredControlBlocks the required number of control blocks
     * @param requiredSenseBlocks the required number of sense blocks
     * @param requiredOperatorBlocks the required number of operator blocks
     * @param requiredMoreBlocks the required number of more blocks
     */
    public BlockGrader(float initialWeight, int requiredMotionBlocks,
            int requiredLooksBlocks, int requiredSoundBlocks,
            int requiredPenBlocks, int requiredDataBlocks,
            int requiredEventBlocks, int requiredControlBlocks,
            int requiredSenseBlocks, int requiredOperatorBlocks,
            int requiredMoreBlocks)
    {
        super(initialWeight, BlockGrader.MODULE_NAME);
        this.setReqMotionBlocks(requiredMotionBlocks);
        this.setRequiredLooksBlocks(requiredLooksBlocks);
        this.setRequiredSoundBlocks(requiredSoundBlocks);
        this.setRequiredPenBlocks(requiredPenBlocks);
        this.setRequiredDataBlocks(requiredDataBlocks);
        this.setRequiredEventBlocks(requiredEventBlocks);
        this.setRequiredControlBlocks(requiredControlBlocks);
        this.setRequiredSenseBlocks(requiredSenseBlocks);
        this.setRequiredOperatorBlocks(requiredOperatorBlocks);
        this.setRequiredMoreSectionBlocks(requiredMoreBlocks);

    }

    @Override
    public double getGradeFrom0To100(Gradeable objectToGrade)
    {
        if (objectToGrade instanceof Student)
        {
            Student std = (Student) objectToGrade;
            double eqWght = 1 / 8.0;
            double grade = 0;

            // calculate grades (can go over 100.
            double motGrade = std.countMotionBlocks() / reqMotBlks * eqWght;
            double lookGrade = std.countLooksBlocks() / reqLookBlks * eqWght;
            double sndGrade = std.countSoundBlocks() / reqSndBlks * eqWght;
            double penGrade = std.countPenBlocks() / reqPenBlks * eqWght;
            double dataGrade = std.countDataBlocks() / reqDataBlks * eqWght;
            double eventGrade = std.countEventBlocks() / reqEventBlks * eqWght;
            double conGrade = std.countControlBlocks() / reqConBlks * eqWght;
            double senseGrade = std.countSenseBlocks() / reqSenseBlks * eqWght;
            double operGrade = std.countOperatorBlocks() / reqOperBlks * eqWght;
            double mrGrade = std.countMoreSectionBlocks() / reqMrBlks * eqWght;

            // if no extra credit, then make grades over 100 equal to 100
            if (!getExtraCreditMode())
            {
                motGrade = motGrade > 1f ? 100 : motGrade;
                lookGrade = lookGrade > 1f ? 100 : lookGrade;
                sndGrade = sndGrade > 1f ? 100 : sndGrade;
                penGrade = penGrade > 1f ? 100 : penGrade;
                dataGrade = dataGrade > 1f ? 100 : dataGrade;
                eventGrade = eventGrade > 1f ? 100 : eventGrade;
                conGrade = conGrade > 1f ? 100 : conGrade;
                senseGrade = senseGrade > 1f ? 100 : senseGrade;
                operGrade = operGrade > 1f ? 100 : operGrade;
                mrGrade = mrGrade > 1f ? 100 : mrGrade;
            }

            grade = motGrade + lookGrade + sndGrade + penGrade + eventGrade
                    + conGrade + senseGrade + operGrade + mrGrade;
            grade *= 100;
            return grade;
        }
        else
        {
            throw new IllegalArgumentException(
                    "non student passed to Block Grader");
        }

    }

    @Override
    public boolean validType(Gradeable objectToGrade)
    {
        return objectToGrade instanceof Student;
    }

    /**
     * Filter negative and zero numbers to 0.0001.
     * 
     * @param value the value to check
     * @return the value, filtered to 0.0001 if needed.
     */
    private double zeroFilter(double value)
    {
        if (value <= 0)
        {
            value = 0.0001;
        }
        return value;
    }

    /**
     * Get the number of motion blocks required.
     * 
     * @return the number of motion blocks.
     */
    public double getReqMotionBlocks()
    {
        return reqMotBlks;
    }

    /**
     * Set the new number of motion blocks required.
     * 
     * @param reqMotBlks the new number of motion blocks to require.
     */
    public void setReqMotionBlocks(double reqMotBlks)
    {

        this.reqMotBlks = zeroFilter(reqMotBlks);
    }

    /**
     * Get then number of blocks in section "looks" required.
     * 
     * @return Get then number of blocks in section "looks" required.
     */
    public double getRequiredLooksBlocks()
    {
        return reqLookBlks;
    }

    /**
     * Set then number of blocks in section "looks" required.
     * 
     * @param reqLookBlks the number of blocks in section "looks" required.
     */
    public void setRequiredLooksBlocks(double reqLookBlks)
    {
        this.reqLookBlks = zeroFilter(reqLookBlks);
    }

    /**
     * Get the number of sound blocks required to receive a 100.
     * 
     * @return Get the number of sound blocks required to receive a 100.
     */
    public double getRequiredSoundBlocks()
    {
        return reqSndBlks;
    }

    /**
     * Set the number of sound blocks required to receive a 100.
     * 
     * @param reqSndBlks the number of blocks required to send blocks
     */
    public void setRequiredSoundBlocks(double reqSndBlks)
    {
        this.reqSndBlks = zeroFilter(reqSndBlks);
    }

    /**
     * Get the number of blocks from pen section required to get a 100.
     * 
     * @return Get the number of sound blocks required to receive a 100.
     */
    public double getRequiredPenBlocks()
    {
        return reqPenBlks;
    }

    /**
     * Set the number of sound blocks required to receive a 100.
     * 
     * @param reqPenBlks the number of sound blocks required to receive a 100.
     */
    public void setRequiredPenBlocks(double reqPenBlks)
    {
        this.reqPenBlks = zeroFilter(reqPenBlks);
    }

    /**
     * Get the number of blocks from the data section required to get a 100.
     * 
     * @return the number of blocks from the data section required to get a 100.
     */
    public double getRequiredDataBlocks()
    {
        return reqDataBlks;
    }

    /**
     * Set the number of blocks from the data section required to get a 100.
     * 
     * @param reqDataBlks Set the number of blocks from the data section
     *            required to get a 100.
     */
    public void setRequiredDataBlocks(double reqDataBlks)
    {
        this.reqDataBlks = zeroFilter(reqDataBlks);
    }

    /**
     * Get the number of blocks in the event section required to get a 100.
     * 
     * @return Get the number of blocks in the event section required to get a
     *         100.
     */
    public double getRequiredEventBlocks()
    {
        return reqEventBlks;
    }

    /**
     * Set the number of blocks in the event section required to get a 100.
     * 
     * @param reqEventBlks the number of blocks in the event section required to
     *            get a 100.
     */
    public void setRequiredEventBlocks(double reqEventBlks)
    {
        this.reqEventBlks = zeroFilter(reqEventBlks);
    }

    /**
     * Get the number of blocks in the control section required to get a 100.
     * 
     * @return Get the number of blocks in the event section required to get a
     *         100.
     */
    public double getRequiredControlBlocks()
    {
        return reqConBlks;
    }

    /**
     * Set the number of blocks in the event section required to get a 100.
     * 
     * @param reqConBlks the number of blocks in the event section required to
     *            get a 100.
     */
    public void setRequiredControlBlocks(double reqConBlks)
    {
        this.reqConBlks = reqConBlks;
    }

    /**
     * Get the number of blocks in the sense section required to get a 100.
     * 
     * @return Get the number of blocks in the sense section required to get a
     *         100.
     */
    public double getRequiredSenseBlocks()
    {
        return reqSenseBlks;
    }

    /**
     * Set the number of blocks in the sense section required to get a 100.
     * 
     * @param reqSenseBlks the number of blocks in the sense section required to
     *            get a 100.
     */
    public void setRequiredSenseBlocks(double reqSenseBlks)
    {
        this.reqSenseBlks = zeroFilter(reqSenseBlks);
    }

    /**
     * Get the number of blocks in the operator section required to get a 100.
     * 
     * @return Get the number of blocks in the operator section required to get
     *         a 100.
     */
    public double getRequiredOperatorBlocks()
    {
        return reqOperBlks;
    }

    /**
     * Set the number of blocks in the operator section required to get a 100.
     * 
     * @param reqOperBlks the number of blocks in the operator section required
     *            to get a 100.
     */
    public void setRequiredOperatorBlocks(double reqOperBlks)
    {
        this.reqOperBlks = zeroFilter(reqOperBlks);
    }

    /**
     * Get the number of blocks in the "more" section required to get a 100.
     * 
     * @return Get the number of blocks in the "more" section required to get a
     *         100.
     */
    public double getRequiredMoreSectionBlocks()
    {
        return reqMrBlks;
    }

    /**
     * Set the number of blocks in the "more" section required to get a 100.
     * 
     * @param reqMrBlks the number of blocks in the "more" section required to
     *            get a 100.
     */
    public void setRequiredMoreSectionBlocks(double reqMrBlks)
    {
        this.reqMrBlks = zeroFilter(reqMrBlks);
    }

}
