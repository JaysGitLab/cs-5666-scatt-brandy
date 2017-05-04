package scatt.gui;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import scatt.gradermodules.VariableGrader;

/**
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class VariableConfigurablePanel extends ConfigurablePanel
{
    private static final long serialVersionUID = -1163580773363401330L;
    private JTextField variableTextField;
    private JTextField weightTextField;
    private JLabel numVarReqLabel;
    private JLabel weightLabel;

    /**
     * Instantiate a Sprite Grader Configuration Panel.
     * 
     * @param context the context used to obtain grading information.
     */
    public VariableConfigurablePanel(GraderContext context)
    {
        super(context, VariableGrader.MODULE_NAME);
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);

        variableTextField = new JTextField();
        variableTextField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processVariableTextField();
            }
        });
        add(variableTextField);
        variableTextField.setColumns(10);

        numVarReqLabel = new JLabel("Number Of Required Variables");
        springLayout.putConstraint(SpringLayout.WEST, variableTextField, 41,
                SpringLayout.EAST, numVarReqLabel);
        springLayout.putConstraint(SpringLayout.WEST, numVarReqLabel, 29,
                SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, variableTextField, -3,
                SpringLayout.NORTH, numVarReqLabel);
        springLayout.putConstraint(SpringLayout.NORTH, numVarReqLabel, 43,
                SpringLayout.NORTH, this);
        add(numVarReqLabel);

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
                SpringLayout.EAST, variableTextField);
        add(weightTextField);
        weightTextField.setColumns(10);

        weightLabel = new JLabel("Weight For Weighted Average:");
        springLayout.putConstraint(SpringLayout.NORTH, weightTextField, -3,
                SpringLayout.NORTH, weightLabel);
        springLayout.putConstraint(SpringLayout.WEST, weightLabel, 29,
                SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, weightLabel, 19,
                SpringLayout.SOUTH, numVarReqLabel);
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
    protected boolean processVariableTextField()
    {
        String text = variableTextField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            float x = Integer.parseInt(text);

            // probably should use a better float comparison
            return x >= 0.f;

        }
        catch (NumberFormatException e)
        {
            updateVarTextFieldToStored("Value should be greater than 0");
        }
        catch (IllegalArgumentException e)
        {
            updateVarTextFieldToStored("Value should be greater than 0");
        }
        return false;
    }

    /**
     * Initialize fields for grader.
     */
    private void setTextboxesToContainedData()
    {
        updateWeightTextFieldToStored("failure loading default");
        updateVarTextFieldToStored("failure loading default");
    }

    /**
     * Attempts to load the number of required scripts from the grader and place
     * it in the required field.
     * 
     * @param errorMsg - the message to display in text field should an error
     *            occur.
     */
    private void updateVarTextFieldToStored(String errorMsg)
    {
        VariableGrader varGrader = (VariableGrader) context
                .getComponent(VariableGrader.MODULE_NAME);
        if (varGrader != null)
        {
            // update the required scripts text box
            variableTextField.setText("" + varGrader.getRequired());
        }
        else
        {
            System.out.println("Cannot open the Script Grader "
                    + "Weighted Component in gui.");

            variableTextField.setText(errorMsg);
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
        VariableGrader varGrader = (VariableGrader) context
                .getComponent(VariableGrader.MODULE_NAME);
        if (varGrader != null)
        {
            // update the weight text box
            weightTextField.setText(String.format("%5.3f",
                    varGrader.getWeightFrom0To1()));
        }
        else
        {
            System.out.println("Cannot open the Variable Grader "
                    + "Weighted Component in gui.");

            weightTextField.setText(errorMsg);
        }
    }

    @Override
    public void commitChoices(GraderContext context)
    {
        VariableGrader vg = (VariableGrader) context
                .getComponent(VariableGrader.MODULE_NAME);
        if (vg != null)
        {
            // @precondition: text boxes have been validated to be parsable as
            // floats/ints @formatter:off
            vg.setWeight(Float.parseFloat(weightTextField.getText()));
            vg.setNewRequired(
                    Integer.parseInt(variableTextField.getText()));
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
