package scatt.gui;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import scatt.gradermodules.TempoGrader;

/**
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class TempoConfigurablePanel extends ConfigurablePanel
{
    private static final long serialVersionUID = -1163580773363401330L;
    private JTextField tempoTextField;
    private JTextField weightTextField;
    private JLabel tempoReqLabel;
    private JLabel weightLabel;

    /**
     * Instantiate a Sprite Grader Configuration Panel.
     * 
     * @param context the context used to obtain grading information.
     */
    public TempoConfigurablePanel(GraderContext context)
    {
        super(context, TempoGrader.MODULE_NAME);
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);

        tempoTextField = new JTextField();
        tempoTextField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                processTempoField();
            }
        });
        add(tempoTextField);
        tempoTextField.setColumns(10);

        tempoReqLabel = new JLabel("The Required Tempo:");
        springLayout.putConstraint(SpringLayout.WEST, tempoTextField, 41,
                SpringLayout.EAST, tempoReqLabel);
        springLayout.putConstraint(SpringLayout.WEST, tempoReqLabel, 29,
                SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, tempoTextField, -3,
                SpringLayout.NORTH, tempoReqLabel);
        springLayout.putConstraint(SpringLayout.NORTH, tempoReqLabel, 43,
                SpringLayout.NORTH, this);
        add(tempoReqLabel);

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
                SpringLayout.EAST, tempoTextField);
        add(weightTextField);
        weightTextField.setColumns(10);

        weightLabel = new JLabel("Weight For Weighted Average:");
        springLayout.putConstraint(SpringLayout.NORTH, weightTextField, -3,
                SpringLayout.NORTH, weightLabel);
        springLayout.putConstraint(SpringLayout.WEST, weightLabel, 29,
                SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, weightLabel, 19,
                SpringLayout.SOUTH, tempoReqLabel);
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
    protected boolean processTempoField()
    {
        String text = tempoTextField.getText();
        try
        {
            // If parsing causes no errors, then return true.
            float x = Integer.parseInt(text);

            // probably should use a better float comparison
            return x >= 0.f;

        }
        catch (NumberFormatException e)
        {
            updateTempoTextFieldToStored("Value should be greater than 0");
        }
        catch (IllegalArgumentException e)
        {
            updateTempoTextFieldToStored("Value should be greater than 0");
        }
        return false;
    }

    /**
     * Initialize fields for grader.
     */
    private void setTextboxesToContainedData()
    {
        updateWeightTextFieldToStored("failure loading default");
        updateTempoTextFieldToStored("failure loading default");
    }

    /**
     * Attempts to load the number of required Costumes from the grader and
     * place it in the required field.
     * 
     * @param errorMsg - the message to display in text field should an error
     *            occur.
     */
    private void updateTempoTextFieldToStored(String errorMsg)
    {
        TempoGrader tempoGrader = (TempoGrader) context
                .getComponent(TempoGrader.MODULE_NAME);
        if (tempoGrader != null)
        {
            // update the required scripts text box
            tempoTextField.setText("" + tempoGrader.getRequired());
        }
        else
        {
            System.out.println("Cannot open the Script Grader "
                    + "Weighted Component in gui.");

            tempoTextField.setText(errorMsg);
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
        TempoGrader tempoGrader = (TempoGrader) context
                .getComponent(TempoGrader.MODULE_NAME);
        if (tempoGrader != null)
        {
            // update the weight text box
            weightTextField.setText(String.format("%5.3f",
                    tempoGrader.getWeightFrom0To1()));
        }
        else
        {
            System.out.println("Cannot open the list Grader "
                    + "Weighted Component in gui.");

            weightTextField.setText(errorMsg);
        }
    }

    @Override
    public void commitChoices(GraderContext context)
    {
        TempoGrader tg = (TempoGrader) context
                .getComponent(TempoGrader.MODULE_NAME);
        if (tg != null)
        {
            // @precondition: text boxes have been validated to be parsable as
            // floats/ints @formatter:off
            tg.setWeight(Float.parseFloat(weightTextField.getText()));
            tg.setNewRequired(
                    Integer.parseInt(tempoTextField.getText()));
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
