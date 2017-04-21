package scatt.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The panel responsible for loading files to be graded.
 * 
 * @author Mikeal Anderson
 * @author Matt Stone
 * 
 * @version 1.0
 * 
 */
public class LoaderPanel extends JPanel
{

    private static final long serialVersionUID = -6559354884306907463L;
    public FileHandler fileHandler;
    public JButton btnJfilechoser;
    public JButton btnSinglesb;

    /**
     * Create the panel.
     */
    public LoaderPanel()
    {
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        fileHandler = new FileHandler();

        btnJfilechoser = new JButton(".sb2 directory");

        // The function called when the button is pressed.
        btnJfilechoser.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                fileHandler.openDirectoryFromFileMenu();
                // test();
            }
        });

        btnSinglesb = new JButton("single .sb2");
        btnSinglesb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                fileHandler.openFileFromFileMenu();
            }
        });
        add(btnSinglesb);
        add(btnJfilechoser);

    }

    /**
     * A method that simply prints the word TEST. To be used for debugging GUI
     * behavior.
     */
    public void test()
    {
        System.out.println("TEST");
    }
}
