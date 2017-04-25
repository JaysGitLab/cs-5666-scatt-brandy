package scatt.gui;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import scatt.gradermodules.SpriteGrader;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class SpriteConfigurablePanel extends ConfigurablePanel
{
    private static final long serialVersionUID = -1163580773363401330L;
    private JTextField spriteTextField;
    private JTextField weightTextField;
    private JLabel numSprReqLabel;
    private JLabel weightLabel;

    /**
     * Instantiate a Sprite Grader Configuration Panel.
     * 
     * @param context the context used to obtain grading information.
     */
    public SpriteConfigurablePanel(GraderContext context)
    {
        super(context, SpriteGrader.MODULE_NAME);
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);

        spriteTextField = new JTextField();
        spriteTextField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processSpriteTextField();
            }
        });
        add(spriteTextField);
        spriteTextField.setColumns(10);

        numSprReqLabel = new JLabel("Number of required Sprites:");
        springLayout.putConstraint(SpringLayout.WEST, spriteTextField, 41,
                SpringLayout.EAST, numSprReqLabel);
        springLayout.putConstraint(SpringLayout.WEST, numSprReqLabel, 29,
                SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, spriteTextField, -3,
                SpringLayout.NORTH, numSprReqLabel);
        springLayout.putConstraint(SpringLayout.NORTH, numSprReqLabel, 43,
                SpringLayout.NORTH, this);
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
        springLayout.putConstraint(SpringLayout.EAST, weightTextField, 0,
                SpringLayout.EAST, spriteTextField);
        add(weightTextField);
        weightTextField.setColumns(10);

        weightLabel = new JLabel("Weight For Weighted Average:");
        springLayout.putConstraint(SpringLayout.NORTH, weightTextField, -3,
                SpringLayout.NORTH, weightLabel);
        springLayout.putConstraint(SpringLayout.WEST, weightLabel, 29,
                SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, weightLabel, 19,
                SpringLayout.SOUTH, numSprReqLabel);
        add(weightLabel);

        setTextboxesToContainedData();
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
     * Evaluates text in the required sprite count text field to see check for
     * valid float.
     * 
     * @return true if the text in the text box is a valid format, false
     *         otherwise.
     */
    protected boolean processSpriteTextField()
    {
        String text = spriteTextField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            float x = Integer.parseInt(text);

            // probably should use a better float comparison
            return x >= 0.f;

        }
        catch (NumberFormatException e)
        {
            updateSpriteTextFieldToStored("Value should be greater than 0");
        }
        catch (IllegalArgumentException e)
        {
            updateSpriteTextFieldToStored("Value should be greater than 0");
        }
        return false;
    }

    /**
     * Initialize fields for grader.
     */
    private void setTextboxesToContainedData()
    {
        updateWeightTextFieldToStored("failure loading default");
        updateSpriteTextFieldToStored("failure loading default");
    }

    /**
     * Attempts to load the number of required sprites from the grader and place
     * it in the required field.
     * 
     * @param errorMsg - the message to display in text field should an error
     *            occur.
     */
    private void updateSpriteTextFieldToStored(String errorMsg)
    {
        SpriteGrader spriteGrader = (SpriteGrader) context
                .getComponent(SpriteGrader.MODULE_NAME);
        if (spriteGrader != null)
        {
            // update the required sprites text box
            spriteTextField.setText("" + spriteGrader.getRequiredSprites());
        }
        else
        {
            System.out.println("Cannot open the Sprite Grader "
                    + "Weighted Component in gui.");

            spriteTextField.setText(errorMsg);
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
        SpriteGrader spriteGrader = (SpriteGrader) context
                .getComponent(SpriteGrader.MODULE_NAME);
        if (spriteGrader != null)
        {
            // update the weight text box
            weightTextField.setText(String.format("%5f",
                    spriteGrader.getWeightFrom0To1()));
        }
        else
        {
            System.out.println("Cannot open the Sprite Grader "
                    + "Weighted Component in gui.");

            weightTextField.setText(errorMsg);
        }
    }

    @Override
    public void commitChoices(GraderContext context)
    {
        SpriteGrader sg = (SpriteGrader) context
                .getComponent(SpriteGrader.MODULE_NAME);
        if (sg != null)
        {
            // @precondition: text boxes have been validated to be parsable as
            // floats/ints @formatter:off
            sg.setWeight(Float.parseFloat(weightTextField.getText()));
            sg.setNewRequiredSprites(
                    Integer.parseInt(spriteTextField.getText()));
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
