package scatt.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
    private JDialog popupTest;
    private GraderContext context;

    /**
     * Create the panel.
     * 
     * @param context - an object that is used to store/retrieve grading related
     *            context.
     * @param owner - owner window for the configuratin panel.
     */
    public ConfigurePanel(GraderContext context, JFrame owner)
    {
        this.context = context;
        setLayout(new GridLayout(0, 1, 0, 0));

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane);

        buttonPanel = new JPanel();
        scrollPane.setViewportView(buttonPanel);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        popupTest = new Configurable(owner, "Test", true);
        popupTest = new SpriteConfigurable(owner, "Sprite Configuration");


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
        test.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                System.out.println("testing button press");
                popupTest.setVisible(true);
            }
        });
    }

}
