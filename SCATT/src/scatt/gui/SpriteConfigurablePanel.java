package scatt.gui;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class SpriteConfigurablePanel extends ConfigurablePanel
{
    private static final long serialVersionUID = -1163580773363401330L;
    private JTextField textField;
    private JTextField textField1;
    private JLabel lblNumberOfRequired;
    private JLabel weight;

    /**
     * Create the panel.
     */
    public SpriteConfigurablePanel()
    {
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);

        textField = new JTextField();
        add(textField);
        textField.setColumns(10);

        lblNumberOfRequired = new JLabel("Number of required Sprites:");
        springLayout.putConstraint(SpringLayout.WEST, textField, 41,
                SpringLayout.EAST, lblNumberOfRequired);
        springLayout.putConstraint(SpringLayout.WEST, lblNumberOfRequired, 29,
                SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, textField, -3,
                SpringLayout.NORTH, lblNumberOfRequired);
        springLayout.putConstraint(SpringLayout.NORTH, lblNumberOfRequired, 43,
                SpringLayout.NORTH, this);
        add(lblNumberOfRequired);

        textField1 = new JTextField();
        springLayout.putConstraint(SpringLayout.EAST, textField1, 0,
                SpringLayout.EAST, textField);
        add(textField1);
        textField1.setColumns(10);

        weight = new JLabel("Weight For Weighted Average:");
        springLayout.putConstraint(SpringLayout.NORTH, textField1, -3,
                SpringLayout.NORTH, weight);
        springLayout.putConstraint(SpringLayout.WEST, weight, 29,
                SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, weight, 19,
                SpringLayout.SOUTH, lblNumberOfRequired);
        add(weight);

    }

    @Override
    public void commitChoices(GraderContext context)
    {

    }

    @Override
    public void resetOptionsToDefault(GraderContext context)
    {

    }
}
