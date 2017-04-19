package scatt.gui;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * A class that represents the configuration panel in the GUI.
 * http://stackoverflow
 * .com/questions/15913623/dynamic-buttons-with-scrollpane-java
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class ConfigurePanel extends JPanel
{

    private static final long serialVersionUID = 8757410256026038342L;
    private JPanel buttonPanel;

    /**
     * Create the panel.
     */
    public ConfigurePanel()
    {
        setLayout(new GridLayout(0, 1, 0, 0));

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane);

        buttonPanel = new JPanel();
        scrollPane.setViewportView(buttonPanel);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // for now, demo that buttons can be generated
        // will be done based on grader.
        for (int i = 0; i < 10; ++i)
        {
            insertButtonRow();
        }

    }

    /**
     * Insert a new row into the table.
     */
    protected void insertButtonRow()
    {
        //@formatter:off
        JButton test = new JButton("test");
        test.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(test, null);
        //@formatter:on
    }

}
