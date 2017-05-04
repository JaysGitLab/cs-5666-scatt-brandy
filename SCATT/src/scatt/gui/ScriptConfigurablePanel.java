package scatt.gui;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import scatt.gradermodules.ScriptGrader;

/**
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class ScriptConfigurablePanel extends ConfigurablePanel
{
    private static final long serialVersionUID = -1163580773363401330L;
    private JTextField scriptTextField;
    private JTextField weightTextField;
    private JLabel numSprReqLabel;
    private JLabel weightLabel;

    /**
     * Instantiate a Sprite Grader Configuration Panel.
     * 
     * @param context the context used to obtain grading information.
     */
    public ScriptConfigurablePanel(GraderContext context)
    {
        super(context, ScriptGrader.MODULE_NAME);
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);

        scriptTextField = new JTextField();
        scriptTextField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processScriptTextField();
            }
        });
        add(scriptTextField);
        scriptTextField.setColumns(10);

        numSprReqLabel = new JLabel("Number Of Required Scripts:");
        springLayout.putConstraint(SpringLayout.WEST, scriptTextField, 41,
                SpringLayout.EAST, numSprReqLabel);
        springLayout.putConstraint(SpringLayout.WEST, numSprReqLabel, 29,
                SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, scriptTextField, -3,
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
                SpringLayout.EAST, scriptTextField);
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
     * Evaluates text in the required script count text field to see check for
     * valid float.
     * 
     * @return true if the text in the text box is a valid format, false
     *         otherwise.
     */
    protected boolean processScriptTextField()
    {
        String text = scriptTextField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            float x = Integer.parseInt(text);

            // probably should use a better float comparison
            return x >= 0.f;

        }
        catch (NumberFormatException e)
        {
            updateScriptTextFieldToStored("Value should be greater than 0");
        }
        catch (IllegalArgumentException e)
        {
            updateScriptTextFieldToStored("Value should be greater than 0");
        }
        return false;
    }

    /**
     * Initialize fields for grader.
     */
    private void setTextboxesToContainedData()
    {
        updateWeightTextFieldToStored("failure loading default");
        updateScriptTextFieldToStored("failure loading default");
    }

    /**
     * Attempts to load the number of required scripts from the grader and place
     * it in the required field.
     * 
     * @param errorMsg - the message to display in text field should an error
     *            occur.
     */
    private void updateScriptTextFieldToStored(String errorMsg)
    {
        ScriptGrader spriteGrader = (ScriptGrader) context
                .getComponent(ScriptGrader.MODULE_NAME);
        if (spriteGrader != null)
        {
            // update the required scripts text box
            scriptTextField.setText("" + spriteGrader.getRequiredScripts());
        }
        else
        {
            System.out.println("Cannot open the Script Grader "
                    + "Weighted Component in gui.");

            scriptTextField.setText(errorMsg);
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
        ScriptGrader scriptGrader = (ScriptGrader) context
                .getComponent(ScriptGrader.MODULE_NAME);
        if (scriptGrader != null)
        {
            // update the weight text box
            weightTextField.setText(String.format("%5.3f",
                    scriptGrader.getWeightFrom0To1()));
        }
        else
        {
            System.out.println("Cannot open the Script Grader "
                    + "Weighted Component in gui.");

            weightTextField.setText(errorMsg);
        }
    }

    @Override
    public void commitChoices(GraderContext context)
    {
        ScriptGrader sg = (ScriptGrader) context
                .getComponent(ScriptGrader.MODULE_NAME);
        if (sg != null)
        {
            // @precondition: text boxes have been validated to be parsable as
            // floats/ints @formatter:off
            sg.setWeight(Float.parseFloat(weightTextField.getText()));
            sg.setNewRequiredScripts(
                    Integer.parseInt(scriptTextField.getText()));
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
