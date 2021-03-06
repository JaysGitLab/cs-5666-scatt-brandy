package scatt.gui;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import scatt.gradermodules.BlockGrader;

/**
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class BlockConfigurablePanel extends ConfigurablePanel
{
    private static final long serialVersionUID = -1163580773363401330L;
    private JTextField motionField;
    private JTextField weightTextField;
    private JLabel numSprReqLabel;
    private JLabel weightLabel;
    private JTextField soundField;
    private JTextField penField;
    private JTextField dataField;
    private JTextField eventField;
    private JTextField controlField;
    private JTextField senseField;
    private JTextField operatorField;
    private JTextField looksField;
    private JLabel lblRequiredSenseBlocks;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel lblRequiredmoreBlocks;
    private JTextField mrSecField;
    private SpringLayout springLayout;

    /**
     * Instantiate a Block Grader Configuration Panel.
     * 
     * @param context the context used to obtain grading information.
     */
    public BlockConfigurablePanel(GraderContext context)
    {
        super(context, BlockGrader.MODULE_NAME);

        constructorPt1();
        constructorPt2();
        constructorPt3();
        constructorPt4();

        mrSecField = new JTextField();
        mrSecField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processMoreBlocksField();
            }
        });
        springLayout.putConstraint(SpringLayout.SOUTH, weightTextField, 37,
                SpringLayout.SOUTH, mrSecField);
        springLayout.putConstraint(SpringLayout.WEST, mrSecField, 22,
                SpringLayout.EAST, lblRequiredmoreBlocks);
        springLayout.putConstraint(SpringLayout.NORTH, weightTextField, 15,
                SpringLayout.SOUTH, mrSecField);
        springLayout.putConstraint(SpringLayout.NORTH, mrSecField, -3,
                SpringLayout.NORTH, lblRequiredmoreBlocks);
        mrSecField.setColumns(10);
        add(mrSecField);

        setTextboxesToContainedData();
    }

    /**
     * breaking constructor up due to checkstyle limitations.
     */
    private void constructorPt4()
    {

        label5 = new JLabel("Required Control Blocks:");
        springLayout.putConstraint(SpringLayout.WEST, controlField, 62,
                SpringLayout.EAST, label5);
        springLayout.putConstraint(SpringLayout.EAST, controlField, 178,
                SpringLayout.EAST, label5);
        springLayout.putConstraint(SpringLayout.NORTH, label5, 12,
                SpringLayout.SOUTH, label4);
        springLayout.putConstraint(SpringLayout.WEST, label5, 29,
                SpringLayout.WEST, this);
        add(label5);

        label6 = new JLabel("Required Sense Blocks:");
        springLayout.putConstraint(SpringLayout.NORTH, label6, 3,
                SpringLayout.NORTH, senseField);
        springLayout.putConstraint(SpringLayout.WEST, label6, 29,
                SpringLayout.WEST, this);
        add(label6);

        label7 = new JLabel("Required Operator Blocks:");
        springLayout.putConstraint(SpringLayout.WEST, operatorField, 52,
                SpringLayout.EAST, label7);
        springLayout.putConstraint(SpringLayout.EAST, operatorField, 168,
                SpringLayout.EAST, label7);
        springLayout.putConstraint(SpringLayout.NORTH, label7, 13,
                SpringLayout.SOUTH, label6);
        springLayout.putConstraint(SpringLayout.SOUTH, label7, 29,
                SpringLayout.SOUTH, label6);
        springLayout.putConstraint(SpringLayout.WEST, label7, 29,
                SpringLayout.WEST, this);
        add(label7);

        lblRequiredmoreBlocks = new JLabel("Required 'More Section' Blocks:");
        springLayout.putConstraint(SpringLayout.NORTH, weightLabel, 21,
                SpringLayout.SOUTH, lblRequiredmoreBlocks);
        springLayout.putConstraint(SpringLayout.NORTH, lblRequiredmoreBlocks,
                12, SpringLayout.SOUTH, label7);
        springLayout.putConstraint(SpringLayout.WEST, lblRequiredmoreBlocks, 0,
                SpringLayout.WEST, numSprReqLabel);
        add(lblRequiredmoreBlocks);

    }

    /**
     * Breaking constructor up do to checkstyle limitations.
     */
    private void constructorPt3()
    {
        operatorField = new JTextField();
        operatorField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processOperatorField();
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, operatorField, 6,
                SpringLayout.SOUTH, senseField);
        springLayout.putConstraint(SpringLayout.SOUTH, operatorField, 28,
                SpringLayout.SOUTH, senseField);
        operatorField.setColumns(10);
        add(operatorField);

        looksField = new JTextField();
        looksField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processLooksField();
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, looksField, 8,
                SpringLayout.SOUTH, motionField);
        springLayout.putConstraint(SpringLayout.WEST, looksField, 0,
                SpringLayout.WEST, motionField);
        springLayout.putConstraint(SpringLayout.SOUTH, looksField, 30,
                SpringLayout.SOUTH, motionField);
        springLayout.putConstraint(SpringLayout.EAST, looksField, 116,
                SpringLayout.WEST, motionField);
        looksField.setColumns(10);
        add(looksField);

        lblRequiredSenseBlocks = new JLabel("Required Looks Blocks:");
        springLayout.putConstraint(SpringLayout.NORTH, lblRequiredSenseBlocks,
                14, SpringLayout.SOUTH, numSprReqLabel);
        springLayout.putConstraint(SpringLayout.SOUTH, lblRequiredSenseBlocks,
                30, SpringLayout.SOUTH, numSprReqLabel);
        springLayout.putConstraint(SpringLayout.WEST, lblRequiredSenseBlocks,
                29, SpringLayout.WEST, this);
        add(lblRequiredSenseBlocks);

        label1 = new JLabel("Required Sound Blocks:");
        springLayout.putConstraint(SpringLayout.WEST, soundField, 67,
                SpringLayout.EAST, label1);
        springLayout.putConstraint(SpringLayout.EAST, soundField, 183,
                SpringLayout.EAST, label1);
        springLayout.putConstraint(SpringLayout.NORTH, label1, 71,
                SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, label1, 29,
                SpringLayout.WEST, this);
        add(label1);

        label2 = new JLabel("Required Pen Blocks:");
        springLayout.putConstraint(SpringLayout.NORTH, label2, 3,
                SpringLayout.NORTH, penField);
        springLayout.putConstraint(SpringLayout.WEST, label2, 29,
                SpringLayout.WEST, this);
        add(label2);

        label3 = new JLabel("Required Data Blocks:");
        springLayout.putConstraint(SpringLayout.WEST, dataField, 77,
                SpringLayout.EAST, label3);
        springLayout.putConstraint(SpringLayout.EAST, dataField, 193,
                SpringLayout.EAST, label3);
        springLayout.putConstraint(SpringLayout.NORTH, label3, 12,
                SpringLayout.SOUTH, label2);
        springLayout.putConstraint(SpringLayout.WEST, label3, 29,
                SpringLayout.WEST, this);
        add(label3);

        label4 = new JLabel("Required Event Blocks:");
        springLayout.putConstraint(SpringLayout.NORTH, label4, 3,
                SpringLayout.NORTH, eventField);
        springLayout.putConstraint(SpringLayout.WEST, label4, 29,
                SpringLayout.WEST, this);
        add(label4);

    }

    /**
     * Breaking the constructor up due to check style limitations.
     */
    private void constructorPt2()
    {
        penField = new JTextField();
        penField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processPenField();
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, penField, 7,
                SpringLayout.SOUTH, soundField);
        springLayout.putConstraint(SpringLayout.WEST, penField, 231,
                SpringLayout.WEST, this);
        penField.setColumns(10);
        add(penField);

        dataField = new JTextField();
        dataField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processDataField();
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, dataField, 6,
                SpringLayout.SOUTH, penField);
        springLayout.putConstraint(SpringLayout.SOUTH, dataField, 28,
                SpringLayout.SOUTH, penField);
        dataField.setColumns(10);
        add(dataField);

        eventField = new JTextField();
        eventField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processEventsField();
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, eventField, 153,
                SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, eventField, 231,
                SpringLayout.WEST, this);
        eventField.setColumns(10);
        add(eventField);

        controlField = new JTextField();
        controlField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processControlField();
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, controlField, 6,
                SpringLayout.SOUTH, eventField);
        controlField.setColumns(10);
        add(controlField);

        senseField = new JTextField();
        senseField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processSenseField();
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, senseField, 8,
                SpringLayout.SOUTH, controlField);
        springLayout.putConstraint(SpringLayout.WEST, senseField, 231,
                SpringLayout.WEST, this);
        senseField.setColumns(10);
        add(senseField);


    }

    /**
     * Breaking constructor up due to checkstyle limitations.
     */
    private void constructorPt1()
    {
        springLayout = new SpringLayout();
        setLayout(springLayout);

        motionField = new JTextField();
        motionField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processMotionTextField();
            }
        });
        add(motionField);
        motionField.setColumns(10);

        numSprReqLabel = new JLabel("Required Motion Blocks");
        springLayout.putConstraint(SpringLayout.WEST, motionField, 70,
                SpringLayout.EAST, numSprReqLabel);
        springLayout.putConstraint(SpringLayout.NORTH, numSprReqLabel, 13,
                SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.NORTH, motionField, -3,
                SpringLayout.NORTH, numSprReqLabel);
        springLayout.putConstraint(SpringLayout.WEST, numSprReqLabel, 29,
                SpringLayout.WEST, this);
        add(numSprReqLabel);

        weightTextField = new JTextField();
        weightTextField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processWeightTextField();
            }
        });
        add(weightTextField);
        weightTextField.setColumns(10);

        weightLabel = new JLabel("Weight For Weighted Average:");
        springLayout.putConstraint(SpringLayout.WEST, weightTextField, 25,
                SpringLayout.EAST, weightLabel);
        springLayout.putConstraint(SpringLayout.EAST, weightTextField, 141,
                SpringLayout.EAST, weightLabel);
        springLayout.putConstraint(SpringLayout.WEST, weightLabel, 0,
                SpringLayout.WEST, numSprReqLabel);
        add(weightLabel);

        soundField = new JTextField();
        soundField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processSoundsField();
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, soundField, 68,
                SpringLayout.NORTH, this);
        soundField.setColumns(10);
        add(soundField);
    }

    /**
     * Update the grader based on the looks field.
     */
    protected void processLooksField()
    {
        String text = looksField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            double extracted = Double.parseDouble(text);

            // probably should use a better float comparison
            if (extracted >= 0.f)
            {
                BlockGrader blockGrader = (BlockGrader) context
                        .getComponent(BlockGrader.MODULE_NAME);
                blockGrader.setRequiredLooksBlocks(extracted);
            }
            else
            {
                updateBlockTextFieldToStored("Value should be "
                        + "greater than 0");
            }

        }
        catch (NumberFormatException e)
        {
            updateBlockTextFieldToStored("Value should be " + "greater than 0");
        }
        catch (IllegalArgumentException e)
        {
            updateBlockTextFieldToStored("Value should be" + " greater than 0");
        }
    }

    /**
     * Update the grader based on the sounds field.
     */
    protected void processSoundsField()
    {
        String text = soundField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            double extracted = Double.parseDouble(text);

            // probably should use a better float comparison
            if (extracted >= 0.f)
            {
                BlockGrader blockGrader = (BlockGrader) context
                        .getComponent(BlockGrader.MODULE_NAME);
                blockGrader.setRequiredSoundBlocks(extracted);
            }
            else
            {
                updateBlockTextFieldToStored("Value should be "
                        + "greater than 0");
            }

        }
        catch (NumberFormatException e)
        {
            updateBlockTextFieldToStored("Value should be " + "greater than 0");
        }
        catch (IllegalArgumentException e)
        {
            updateBlockTextFieldToStored("Value should be" + " greater than 0");
        }

    }

    /**
     * Update the grader based on the looks field.
     */
    protected void processPenField()
    {
        String text = penField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            double extracted = Double.parseDouble(text);

            // probably should use a better float comparison
            if (extracted >= 0.f)
            {
                BlockGrader blockGrader = (BlockGrader) context
                        .getComponent(BlockGrader.MODULE_NAME);
                blockGrader.setRequiredPenBlocks(extracted);
            }
            else
            {
                updateBlockTextFieldToStored("Value should be "
                        + "greater than 0");
            }

        }
        catch (NumberFormatException e)
        {
            updateBlockTextFieldToStored("Value should be " + "greater than 0");
        }
        catch (IllegalArgumentException e)
        {
            updateBlockTextFieldToStored("Value should be" + " greater than 0");
        }
    }

    /**
     * Update the grader based on the Data field.
     */
    protected void processDataField()
    {
        String text = dataField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            double extracted = Double.parseDouble(text);

            // probably should use a better float comparison
            if (extracted >= 0.f)
            {
                BlockGrader blockGrader = (BlockGrader) context
                        .getComponent(BlockGrader.MODULE_NAME);
                blockGrader.setRequiredDataBlocks(extracted);
            }
            else
            {
                updateBlockTextFieldToStored("Value should be "
                        + "greater than 0");
            }

        }
        catch (NumberFormatException e)
        {
            updateBlockTextFieldToStored("Value should be " + "greater than 0");
        }
        catch (IllegalArgumentException e)
        {
            updateBlockTextFieldToStored("Value should be" + " greater than 0");
        }
    }

    /**
     * Update the grader based on the event field.
     */
    protected void processEventsField()
    {
        String text = eventField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            double extracted = Double.parseDouble(text);

            // probably should use a better float comparison
            if (extracted >= 0.f)
            {
                BlockGrader blockGrader = (BlockGrader) context
                        .getComponent(BlockGrader.MODULE_NAME);
                blockGrader.setRequiredEventBlocks(extracted);
            }
            else
            {
                updateBlockTextFieldToStored("Value should be "
                        + "greater than 0");
            }

        }
        catch (NumberFormatException e)
        {
            updateBlockTextFieldToStored("Value should be " + "greater than 0");
        }
        catch (IllegalArgumentException e)
        {
            updateBlockTextFieldToStored("Value should be" + " greater than 0");
        }
    }

    /**
     * Update the grader based on the Control field.
     */
    protected void processControlField()
    {
        String text = controlField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            double extracted = Double.parseDouble(text);

            // probably should use a better float comparison
            if (extracted >= 0.f)
            {
                BlockGrader blockGrader = (BlockGrader) context
                        .getComponent(BlockGrader.MODULE_NAME);
                blockGrader.setRequiredControlBlocks(extracted);
            }
            else
            {
                updateBlockTextFieldToStored("Value should be "
                        + "greater than 0");
            }

        }
        catch (NumberFormatException e)
        {
            updateBlockTextFieldToStored("Value should be " + "greater than 0");
        }
        catch (IllegalArgumentException e)
        {
            updateBlockTextFieldToStored("Value should be" + " greater than 0");
        }
    }

    /**
     * Update the grader based on the Sense field.
     */
    protected void processSenseField()
    {
        String text = senseField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            double extracted = Double.parseDouble(text);

            // probably should use a better float comparison
            if (extracted >= 0.f)
            {
                BlockGrader blockGrader = (BlockGrader) context
                        .getComponent(BlockGrader.MODULE_NAME);
                blockGrader.setRequiredSenseBlocks(extracted);
            }
            else
            {
                updateBlockTextFieldToStored("Value should be "
                        + "greater than 0");
            }

        }
        catch (NumberFormatException e)
        {
            updateBlockTextFieldToStored("Value should be " + "greater than 0");
        }
        catch (IllegalArgumentException e)
        {
            updateBlockTextFieldToStored("Value should be" + " greater than 0");
        }
    }

    /**
     * Update the grader based on the operator field.
     */
    protected void processOperatorField()
    {
        String text = operatorField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            double extracted = Double.parseDouble(text);

            // probably should use a better float comparison
            if (extracted >= 0.f)
            {
                BlockGrader blockGrader = (BlockGrader) context
                        .getComponent(BlockGrader.MODULE_NAME);
                blockGrader.setRequiredOperatorBlocks(extracted);
            }
            else
            {
                updateBlockTextFieldToStored("Value should be "
                        + "greater than 0");
            }

        }
        catch (NumberFormatException e)
        {
            updateBlockTextFieldToStored("Value should be " + "greater than 0");
        }
        catch (IllegalArgumentException e)
        {
            updateBlockTextFieldToStored("Value should be" + " greater than 0");
        }
    }

    /**
     * Update the grader based on the "More Section" field.
     */
    protected void processMoreBlocksField()
    {
        String text = mrSecField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            double extracted = Double.parseDouble(text);

            // probably should use a better float comparison
            if (extracted >= 0.f)
            {
                BlockGrader blockGrader = (BlockGrader) context
                        .getComponent(BlockGrader.MODULE_NAME);
                blockGrader.setRequiredMoreSectionBlocks(extracted);
            }
            else
            {
                updateBlockTextFieldToStored("Value should be "
                        + "greater than 0");
            }

        }
        catch (NumberFormatException e)
        {
            updateBlockTextFieldToStored("Value should be " + "greater than 0");
        }
        catch (IllegalArgumentException e)
        {
            updateBlockTextFieldToStored("Value should be" + " greater than 0");
        }
    }

    /**
     * Evaluates text in the weight text field to see check for valid float.
     * 
     * @return true if the text in the text box is a valid format, false
     *         otherwise.
     */
    protected boolean processWeightTextField()
    {
        String text = weightTextField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            float x = Float.parseFloat(text);

            // probably should use a better float comparison
            Boolean result = x >= 0.f && x <= 1.f;

            if (!result)
            {
                throw new IllegalArgumentException();
            }
            return true;

        }
        catch (NumberFormatException e)
        {
            updateWeightTextFieldToStored("Value should be"
                    + " between 0.0 and 1.0");
        }
        catch (IllegalArgumentException e)
        {
            updateWeightTextFieldToStored("Value should be"
                    + " between 0.0 and 1.0");

        }
        return false;
    }

    /**
     * Evaluates text in the required Block count text field to see check for
     * valid float.
     */
    protected void processMotionTextField()
    {
        String text = motionField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            double extracted = Double.parseDouble(text);

            // probably should use a better float comparison
            if (extracted >= 0.f)
            {
                BlockGrader blockGrader = (BlockGrader) context
                        .getComponent(BlockGrader.MODULE_NAME);
                blockGrader.setReqMotionBlocks(extracted);
            }
            else
            {
                updateBlockTextFieldToStored("Value should be "
                        + "greater than 0");
            }

        }
        catch (NumberFormatException e)
        {
            updateBlockTextFieldToStored("Value should be " + "greater than 0");
        }
        catch (IllegalArgumentException e)
        {
            updateBlockTextFieldToStored("Value should be" + " greater than 0");
        }
    }

    /**
     * Initialize fields for grader.
     */
    private void setTextboxesToContainedData()
    {
        updateWeightTextFieldToStored("failure loading default");
        updateBlockTextFieldToStored("failure loading default");
    }

    /**
     * Attempts to load the number of required blocks from the grader and place
     * it in the required field.
     * 
     * @param errorMsg - the message to display in text field should an error
     *            occur.
     */
    private void updateBlockTextFieldToStored(String errorMsg)
    {
        BlockGrader blockGrader = (BlockGrader) context
                .getComponent(BlockGrader.MODULE_NAME);
        if (blockGrader != null)
        {
            // update the required Blocks text box
            motionField.setText("" + blockGrader.getReqMotionBlocks());
            looksField.setText("" + blockGrader.getRequiredLooksBlocks());
            soundField.setText("" + blockGrader.getRequiredSoundBlocks());
            penField.setText("" + blockGrader.getRequiredPenBlocks());
            dataField.setText("" + blockGrader.getRequiredDataBlocks());
            eventField.setText("" + blockGrader.getRequiredEventBlocks());
            controlField.setText("" + blockGrader.getRequiredControlBlocks());
            senseField.setText("" + blockGrader.getRequiredSenseBlocks());
            operatorField.setText("" + blockGrader.getRequiredOperatorBlocks());
            mrSecField.setText("" + blockGrader.getRequiredMoreSectionBlocks());

        }
        else
        {
            System.out.println("Cannot open the Block Grader "
                    + "Weighted Component in gui.");

            motionField.setText(errorMsg);
        }
    }

    /**
     * Attempts to load the weight from the grader and place it in the weight
     * field.
     * 
     * @param errorMsg - the message to display in text field should an error
     *            occur.
     */
    private void updateWeightTextFieldToStored(String errorMsg)
    {
        BlockGrader blockGrader = (BlockGrader) context
                .getComponent(BlockGrader.MODULE_NAME);
        if (blockGrader != null)
        {
            // update the weight text box
            weightTextField.setText(String.format("%5.3f",
                    blockGrader.getWeightFrom0To1()));
        }
        else
        {
            System.out.println("Cannot open the Block Grader "
                    + "Weighted Component in gui.");

            weightTextField.setText(errorMsg);
        }
    }

    @Override
    public void commitChoices(GraderContext context)
    {
        BlockGrader bg = (BlockGrader) context
                .getComponent(BlockGrader.MODULE_NAME);
        if (bg != null)
        {
            processMotionTextField();
            processLooksField();
            processSoundsField();
            processPenField();
            processDataField();
            processEventsField();
            processControlField();
            processSenseField();
            processOperatorField();
            processMoreBlocksField();

            // @precondition: text boxes have been validated to be parsable as
            // floats/ints @formatter:off
            bg.setWeight(Float.parseFloat(weightTextField.getText()));
            bg.setReqMotionBlocks(
                    Double.parseDouble(motionField.getText()));
            bg.setRequiredLooksBlocks(
                    Double.parseDouble(looksField.getText()));
            bg.setRequiredSoundBlocks(
                    Double.parseDouble(soundField.getText()));
            bg.setRequiredPenBlocks(
                    Double.parseDouble(penField.getText()));
            bg.setRequiredDataBlocks(
                    Double.parseDouble(dataField.getText()));
            bg.setRequiredControlBlocks(
                    Double.parseDouble(controlField.getText()));
            bg.setRequiredSenseBlocks(
                    Double.parseDouble(senseField.getText()));
            bg.setRequiredOperatorBlocks(
                    Double.parseDouble(operatorField.getText()));
            bg.setRequiredMoreSectionBlocks(
                    Double.parseDouble(mrSecField.getText()));
            //@formatter:on
        }
        else
        {
            // while data is valid, an error should be printed related to
            // loading
            resetOptionsToDefault(context);
        }
    }

    @Override
    public void resetOptionsToDefault(GraderContext context)
    {
        setTextboxesToContainedData();
    }

}
