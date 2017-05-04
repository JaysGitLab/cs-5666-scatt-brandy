package scatt.gui;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import scatt.gradermodules.CostumeGrader;

/**
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class CostumeConfigurablePanel extends ConfigurablePanel
{
    private static final long serialVersionUID = -1163580773363401330L;
    private JTextField costumeTextField;
    private JTextField weightTextField;
    private JLabel numCostReqLabel;
    private JLabel weightLabel;

    /**
     * Instantiate a Sprite Grader Configuration Panel.
     * 
     * @param context the context used to obtain grading information.
     */
    public CostumeConfigurablePanel(GraderContext context)
    {
        super(context, CostumeGrader.MODULE_NAME);
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);

        costumeTextField = new JTextField();
        costumeTextField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processCostumeTextField();
            }
        });
        add(costumeTextField);
        costumeTextField.setColumns(10);

        numCostReqLabel = new JLabel("Number Of Required Costumes");
        springLayout.putConstraint(SpringLayout.WEST, costumeTextField, 41,
                SpringLayout.EAST, numCostReqLabel);
        springLayout.putConstraint(SpringLayout.WEST, numCostReqLabel, 29,
                SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, costumeTextField, -3,
                SpringLayout.NORTH, numCostReqLabel);
        springLayout.putConstraint(SpringLayout.NORTH, numCostReqLabel, 43,
                SpringLayout.NORTH, this);
        add(numCostReqLabel);

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
                SpringLayout.EAST, costumeTextField);
        add(weightTextField);
        weightTextField.setColumns(10);

        weightLabel = new JLabel("Weight For Weighted Average:");
        springLayout.putConstraint(SpringLayout.NORTH, weightTextField, -3,
                SpringLayout.NORTH, weightLabel);
        springLayout.putConstraint(SpringLayout.WEST, weightLabel, 29,
                SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, weightLabel, 19,
                SpringLayout.SOUTH, numCostReqLabel);
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
    protected boolean processCostumeTextField()
    {
        String text = costumeTextField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            float x = Integer.parseInt(text);

            // probably should use a better float comparison
            return x >= 0.f;

        }
        catch (NumberFormatException e)
        {
            updateCostumeTextFieldToStored("Value should be greater than 0");
        }
        catch (IllegalArgumentException e)
        {
            updateCostumeTextFieldToStored("Value should be greater than 0");
        }
        return false;
    }

    /**
     * Initialize fields for grader.
     */
    private void setTextboxesToContainedData()
    {
        updateWeightTextFieldToStored("failure loading default");
        updateCostumeTextFieldToStored("failure loading default");
    }

    /**
     * Attempts to load the number of required Costumes from the grader and
     * place it in the required field.
     * 
     * @param errorMsg - the message to display in text field should an error
     *            occur.
     */
    private void updateCostumeTextFieldToStored(String errorMsg)
    {
        CostumeGrader costGrader = (CostumeGrader) context
                .getComponent(CostumeGrader.MODULE_NAME);
        if (costGrader != null)
        {
            // update the required scripts text box
            costumeTextField.setText("" + costGrader.getRequired());
        }
        else
        {
            System.out.println("Cannot open the Script Grader "
                    + "Weighted Component in gui.");

            costumeTextField.setText(errorMsg);
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
        CostumeGrader costGrader = (CostumeGrader) context
                .getComponent(CostumeGrader.MODULE_NAME);
        if (costGrader != null)
        {
            // update the weight text box
            weightTextField.setText(String.format("%5.3f",
                    costGrader.getWeightFrom0To1()));
        }
        else
        {
            System.out.println("Cannot open the Costume Grader "
                    + "Weighted Component in gui.");

            weightTextField.setText(errorMsg);
        }
    }

    @Override
    public void commitChoices(GraderContext context)
    {
        CostumeGrader cg = (CostumeGrader) context
                .getComponent(CostumeGrader.MODULE_NAME);
        if (cg != null)
        {
            // @precondition: text boxes have been validated to be parsable as
            // floats/ints @formatter:off
            cg.setWeight(Float.parseFloat(weightTextField.getText()));
            cg.setNewRequired(
                    Integer.parseInt(costumeTextField.getText()));
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
