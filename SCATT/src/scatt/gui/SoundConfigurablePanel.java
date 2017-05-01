package scatt.gui;

import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

import scatt.gradermodules.SoundGrader;

/**
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class SoundConfigurablePanel extends ConfigurablePanel
{
    private static final long serialVersionUID = -1163580773363401330L;
    private JTextField uniSoundTextField;
    private JTextField weightTextField;
    private JLabel numUniSndReqLabel;
    private JLabel weightLabel;
    private JLabel lblTotalNumSnds;
    private JTextField totSoundsTextField;
    private JLabel lblUniqueSoundsGrade;
    private JLabel lblTotalSoundsGrade;
    private JTextField uniSndGrdWeight;
    private JTextField totSndGrdWeight;
    private SpringLayout springLayout;

    /**
     * Instantiate a Sprite Grader Configuration Panel.
     * 
     * @param context the context used to obtain grading information.
     * 
     * 
     */
    public SoundConfigurablePanel(GraderContext context)
    {
        super(context, SoundGrader.MODULE_NAME);
        springLayout = new SpringLayout();
        setLayout(springLayout);

        uniSoundTextField = new JTextField();
        uniSoundTextField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                commitChangesToUniqueSounds();
            }
        });
        add(uniSoundTextField);
        uniSoundTextField.setColumns(10);

        initForCheckstyle1();

        initForCheckstyle2();

        uniSndGrdWeight = new JTextField();
        uniSndGrdWeight.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                commitChangesToUniWeight();
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, uniSndGrdWeight, 8,
                SpringLayout.SOUTH, totSoundsTextField);
        springLayout.putConstraint(SpringLayout.NORTH, lblUniqueSoundsGrade, 3,
                SpringLayout.NORTH, uniSndGrdWeight);
        springLayout.putConstraint(SpringLayout.EAST, uniSndGrdWeight, 0,
                SpringLayout.EAST, uniSoundTextField);
        uniSndGrdWeight.setColumns(10);
        add(uniSndGrdWeight);

        totSndGrdWeight = new JTextField();
        totSndGrdWeight.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                commitChangeToTotalSoundsWeight();
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, weightTextField, 6,
                SpringLayout.SOUTH, totSndGrdWeight);
        springLayout.putConstraint(SpringLayout.SOUTH, weightTextField, 28,
                SpringLayout.SOUTH, totSndGrdWeight);
        springLayout.putConstraint(SpringLayout.NORTH, totSndGrdWeight, 9,
                SpringLayout.SOUTH, uniSndGrdWeight);
        springLayout.putConstraint(SpringLayout.SOUTH, totSndGrdWeight, 31,
                SpringLayout.SOUTH, uniSndGrdWeight);
        springLayout.putConstraint(SpringLayout.WEST, totSndGrdWeight, 0,
                SpringLayout.WEST, uniSoundTextField);
        totSndGrdWeight.setColumns(10);
        add(totSndGrdWeight);

        initInstructions();

        setTextboxesToContainedData();
    }

    /**
     * Make chekstyle happy by breaking up a method.
     */
    private void initForCheckstyle2()
    {

        totSoundsTextField = new JTextField();
        totSoundsTextField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                commitChangesToTotalSounds();
            }
        });
        springLayout.putConstraint(SpringLayout.SOUTH, totSoundsTextField, 28,
                SpringLayout.SOUTH, uniSoundTextField);
        springLayout.putConstraint(SpringLayout.NORTH, totSoundsTextField, 6,
                SpringLayout.SOUTH, uniSoundTextField);
        springLayout.putConstraint(SpringLayout.WEST, totSoundsTextField, 0,
                SpringLayout.WEST, uniSoundTextField);
        totSoundsTextField.setColumns(10);
        add(totSoundsTextField);

        lblUniqueSoundsGrade = new JLabel("Unique Sounds Grade Weight:");
        springLayout.putConstraint(SpringLayout.WEST, lblUniqueSoundsGrade, 0,
                SpringLayout.WEST, numUniSndReqLabel);
        add(lblUniqueSoundsGrade);

        lblTotalSoundsGrade = new JLabel("Total Sounds Grade Weight:");
        springLayout.putConstraint(SpringLayout.WEST, lblTotalSoundsGrade, 0,
                SpringLayout.WEST, numUniSndReqLabel);
        springLayout.putConstraint(SpringLayout.SOUTH, lblTotalSoundsGrade,
                -12, SpringLayout.NORTH, weightLabel);
        add(lblTotalSoundsGrade);
    }

    /**
     * Set up the instructions for how to use the panel.
     */
    private void initInstructions()
    {
        JTextPane txtpnBlahBlahBalh = new JTextPane();
        springLayout.putConstraint(SpringLayout.SOUTH, weightLabel, -9,
                SpringLayout.NORTH, txtpnBlahBlahBalh);
        springLayout.putConstraint(SpringLayout.NORTH, txtpnBlahBlahBalh, 185,
                SpringLayout.NORTH, this);
        txtpnBlahBlahBalh.setFont(new Font("Tahoma", Font.PLAIN, 12));
        txtpnBlahBlahBalh.setEditable(false);
        springLayout.putConstraint(SpringLayout.WEST, txtpnBlahBlahBalh, 20,
                SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, txtpnBlahBlahBalh, -10,
                SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.EAST, txtpnBlahBlahBalh, -22,
                SpringLayout.EAST, this);
        txtpnBlahBlahBalh.setText("Number of unique sounds determines "
                + "how many unique types of "
                + "sound are used in the project. Total"
                + " number of sounds represents how many"
                + " times sounds are called in the script"
                + ". Unique Sounds and Total Sounds grade"
                + " weights represent how much those parameters"
                + " contribute to the overall grade. Weight "
                + "for Weighted average determines how much"
                + " the sound grade weights in the entire"
                + " weighted average.");
        add(txtpnBlahBlahBalh);
    }

    /**
     * Make checkstyle happy by breaking up a method.
     */
    private void initForCheckstyle1()
    {
        numUniSndReqLabel = new JLabel("Number of Unique Sounds:");
        springLayout.putConstraint(SpringLayout.WEST, uniSoundTextField, 41,
                SpringLayout.EAST, numUniSndReqLabel);
        springLayout.putConstraint(SpringLayout.WEST, numUniSndReqLabel, 29,
                SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, uniSoundTextField, -3,
                SpringLayout.NORTH, numUniSndReqLabel);
        springLayout.putConstraint(SpringLayout.NORTH, numUniSndReqLabel, 43,
                SpringLayout.NORTH, this);
        add(numUniSndReqLabel);

        weightTextField = new JTextField();
        springLayout.putConstraint(SpringLayout.WEST, weightTextField, 0,
                SpringLayout.WEST, uniSoundTextField);
        weightTextField.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusLost(FocusEvent arg0)
            {
                commitChangesToWeightAvgField();
            }
        });
        add(weightTextField);
        weightTextField.setColumns(10);

        weightLabel = new JLabel("Weight For Weighted Average:");
        springLayout.putConstraint(SpringLayout.WEST, weightLabel, 0,
                SpringLayout.WEST, numUniSndReqLabel);
        add(weightLabel);

        lblTotalNumSnds = new JLabel("Total number of Sounds:");
        springLayout.putConstraint(SpringLayout.NORTH, lblTotalNumSnds, 12,
                SpringLayout.SOUTH, numUniSndReqLabel);
        springLayout.putConstraint(SpringLayout.WEST, lblTotalNumSnds, 0,
                SpringLayout.WEST, numUniSndReqLabel);
        add(lblTotalNumSnds);

    }

    /**
     * save changes in the total sounds weight field.
     */
    protected void commitChangeToTotalSoundsWeight()
    {
        SoundGrader soundGrader = (SoundGrader) context
                .getComponent(SoundGrader.MODULE_NAME);
        if (validTextFieldEntry(totSndGrdWeight, 0f, Float.MAX_VALUE))
        {
            String text = totSndGrdWeight.getText();
            float value = Float.parseFloat(text);
            soundGrader.setTotalSoundsGradeFractionalWeight(value);
            soundGrader.setUniqueSoundFractionalWeight(1 - value);
            uniSndGrdWeight.setText(String.format("%.3f", 1 - value));

        }
        else
        {
            totSndGrdWeight.setText(""
                    + soundGrader.getTotalSoundsGradeFraction());
        }
    }

    /**
     * save changes in the unique weight field.
     */
    protected void commitChangesToUniWeight()
    {
        SoundGrader soundGrader = (SoundGrader) context
                .getComponent(SoundGrader.MODULE_NAME);
        if (validTextFieldEntry(uniSndGrdWeight, 0f, Float.MAX_VALUE))
        {
            String text = uniSndGrdWeight.getText();
            float value = Float.parseFloat(text);
            soundGrader.setUniqueSoundFractionalWeight(value);
            soundGrader.setTotalSoundsGradeFractionalWeight(1 - value);
            totSndGrdWeight.setText(String.format("%.3f", 1 - value));

        }
        else
        {
            uniSndGrdWeight.setText("" + soundGrader.getUniqueGradeFraction());
        }
    }

    /**
     * Save changes in the total sounds field.
     */
    protected void commitChangesToTotalSounds()
    {
        SoundGrader soundGrader = (SoundGrader) context
                .getComponent(SoundGrader.MODULE_NAME);
        if (validTextFieldEntry(totSoundsTextField, 0, Integer.MAX_VALUE))
        {
            String text = totSoundsTextField.getText();
            int value = Integer.parseInt(text);
            soundGrader.setTotalNumberRequired(value);
        }
        else
        {
            totSoundsTextField.setText("" + soundGrader.getTotalNumRequired());
        }
    }

    /**
     * Save changes in the unique sounds field.
     */
    protected void commitChangesToUniqueSounds()
    {
        SoundGrader soundGrader = (SoundGrader) context
                .getComponent(SoundGrader.MODULE_NAME);
        if (validTextFieldEntry(uniSoundTextField, 0, Integer.MAX_VALUE))
        {
            String text = uniSoundTextField.getText();
            int value = Integer.parseInt(text);
            soundGrader.setNumberUniqueRequired(value);
        }
        else
        {
            uniSoundTextField.setText("" + soundGrader.getNumUniqueRequired());
        }
    }

    /**
     * Save changes in the total sounds field.
     */
    protected void commitChangesToWeightAvgField()
    {
        SoundGrader soundGrader = (SoundGrader) context
                .getComponent(SoundGrader.MODULE_NAME);
        if (validTextFieldEntry(weightTextField, 0f, Float.MAX_VALUE))
        {
            String text = weightTextField.getText();
            double value = Double.parseDouble(text);
            soundGrader.setWeight(value);
        }
        else
        {
            weightTextField.setText("" + soundGrader.getWeightFrom0To1());
        }
    }

    /**
     * Validates that a text field entry is valid and parsable.
     * 
     * @param fld the text field to retreive text from.
     * @param min the minimum value the text field should contain
     * @param max the maximum value the text field should contain
     * @return true if the text field has appropriate data, false otherwise.
     */
    private boolean validTextFieldEntry(JTextField fld, double min, double max)
    {
        String text = fld.getText();
        try
        {
            double value = Double.parseDouble(text);
            if (value < min || value > max)
            {
                return false;
            }
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    /**
     * Validates that a text field entry is valid and parsable.
     * 
     * @param fld the text field to retreive text from.
     * @param min the minimum value the text field should contain
     * @param max the maximum value the text field should contain
     * @return true if the text field has appropriate data, false otherwise.
     */
    private boolean validTextFieldEntry(JTextField fld, int min, int max)
    {
        String text = fld.getText();
        try
        {
            double value = Integer.parseInt(text);
            if (value < min || value > max)
            {
                return false;
            }
        }
        catch (NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    /**
     * Initialize fields for grader.
     */
    private void setTextboxesToContainedData()
    {
        updateWeightFieldsToStoredValues("failure loading default");
        updateSndReqTxtFieldsToStored("failure loading default");
    }

    /**
     * Attempts to load the number of required sprites from the grader and place
     * it in the required field.
     * 
     * @param errorMsg - the message to display in text field should an error
     *            occur.
     */
    private void updateSndReqTxtFieldsToStored(String errorMsg)
    {
        SoundGrader soundGrader = (SoundGrader) context
                .getComponent(SoundGrader.MODULE_NAME);
        if (soundGrader != null)
        {
            // update the required sprites text box
            uniSoundTextField.setText("" + soundGrader.getNumUniqueRequired());
            totSoundsTextField.setText("" + soundGrader.getTotalNumRequired());

        }
        else
        {
            System.out.println("Cannot open the Sound Grader "
                    + "Weighted Component in gui.");

            uniSoundTextField.setText(errorMsg);
            totSoundsTextField.setText(errorMsg);
        }
    }

    /**
     * Attempts to load the weight from the grader and place it in the weight
     * field.
     * 
     * @param errorMsg - the message to display in text field should an error
     *            occur.
     */
    private void updateWeightFieldsToStoredValues(String errorMsg)
    {
        SoundGrader soundGrader = (SoundGrader) context
                .getComponent(SoundGrader.MODULE_NAME);
        if (soundGrader != null)
        {
            // update the weight text box
            weightTextField.setText(String.format("%5.3f",
                    soundGrader.getWeightFrom0To1()));
            uniSndGrdWeight.setText(String.format("%5.3f",
                    soundGrader.getUniqueGradeFraction()));
            totSndGrdWeight.setText(String.format("%5.3f",
                    soundGrader.getTotalSoundsGradeFraction()));
        }
        else
        {
            System.out.println("Cannot open the Sprite Grader "
                    + "Weighted Component in gui.");

            weightTextField.setText(errorMsg);
            uniSndGrdWeight.setText(errorMsg);
            totSndGrdWeight.setText(errorMsg);
        }
    }

    @Override
    public void commitChoices(GraderContext context)
    {
        SoundGrader sg = (SoundGrader) context
                .getComponent(SoundGrader.MODULE_NAME);
        if (sg != null)
        {
            // @precondition: text boxes have been validated to be parsable as
            // floats/ints @formatter:off
            sg.setWeight(Float.parseFloat(weightTextField.getText()));
            sg.setNumberUniqueRequired(
                    Integer.parseInt(uniSoundTextField.getText()));
            sg.setTotalNumberRequired(
                    Integer.parseInt(totSoundsTextField.getText()));
            sg.setUniqueSoundFractionalWeight(
                    Float.parseFloat(uniSndGrdWeight.getText()));
            sg.setTotalSoundsGradeFractionalWeight(
                    Float.parseFloat(
                            totSndGrdWeight.getText()));                    
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
