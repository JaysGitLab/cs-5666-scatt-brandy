package scatt.gui;

import javax.swing.JButton;
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

    /**
     * Create the panel.
     */
    public SpriteConfigurablePanel()
    {
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);

        JButton btnNewButton = new JButton("New button");
        springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 75,
                SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 32,
                SpringLayout.WEST, this);
        add(btnNewButton);

        JButton btnNewButton1 = new JButton("New button");
        springLayout.putConstraint(SpringLayout.NORTH, btnNewButton1, 165,
                SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.WEST, btnNewButton1, 205,
                SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton1, -379,
                SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.EAST, btnNewButton1, -148,
                SpringLayout.EAST, this);
        add(btnNewButton1);

        textField = new JTextField();
        springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, -422,
                SpringLayout.NORTH, textField);
        springLayout.putConstraint(SpringLayout.NORTH, textField, 528,
                SpringLayout.NORTH, this);
        springLayout.putConstraint(SpringLayout.SOUTH, textField, -50,
                SpringLayout.SOUTH, this);
        springLayout.putConstraint(SpringLayout.WEST, textField, 98,
                SpringLayout.WEST, this);
        add(textField);
        textField.setColumns(10);

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
