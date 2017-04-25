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

import scatt.GraderWeightedComponent;

/**
 * A class that represents the configuration panel in the GUI.
 * http://stackoverflow
 * .com/questions/15913623/dynamic-buttons-with-scrollpane-java
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class ConfigureControlPanel extends JPanel
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
    public ConfigureControlPanel(GraderContext context, JFrame owner)
    {
        this.context = context;
        setLayout(new GridLayout(0, 1, 0, 0));

        JScrollPane scrollPane = new JScrollPane();
        add(scrollPane);

        buttonPanel = new JPanel();
        scrollPane.setViewportView(buttonPanel);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // popupTest = new ConfigurableOld(owner, "Test", true);
        // popupTest = new SpriteConfigurableOld(owner, "Sprite Configuration");
        popupTest = new ConfigureDialog(owner, "Sprite Configuration", true,
                new SpriteConfigurablePanel(context), context);

        // for now, demo that buttons can be generated
        // will be done based on grader.
        Object[] graderComponents = context.getAllPanels();
        // for (int i = 0; i < 10; ++i)
        for (Object compObj : graderComponents)
        {
            //@formatter:off
            GraderWeightedComponent comp = (GraderWeightedComponent) compObj;
            String name = comp.getModuleName();
            ConfigurablePanel panel = ConfigurablePanel.get(name);
            insertButtonRow(panel, name);
            
            //@formatter:on
        }

    }

    /**
     * Insert a new row into the table.
     * 
     * @param panel the configuration panel that should have a button generated.
     * @param name - the name to give the new button. 
     */
    protected void insertButtonRow(ConfigurablePanel panel, String name)
    {
        //@formatter:off
        JButton test = new JButton(name);
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
