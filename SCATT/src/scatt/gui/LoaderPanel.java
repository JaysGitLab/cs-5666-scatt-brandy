package scatt.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

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
    private GraderContext context;
    private JLabel lblLoaded;

    /**
     * Create the panel.
     * 
     * @param context - an object that is used to store/retrieve grading related
     *            context.
     */
    public LoaderPanel(GraderContext context)
    {
        this.context = context;
        fileHandler = new FileHandler();

        btnSinglesb = new JButton("single .sb2");
        btnSinglesb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                handleSingleLoadClick();
            }
        });

        SpringLayout springLayout = new SpringLayout();
        springLayout.putConstraint(SpringLayout.WEST, btnSinglesb, 170,
                SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.EAST, btnSinglesb, -162,
                SpringLayout.EAST, this);
        setLayout(springLayout);
        add(btnSinglesb);

        lblLoaded = new JLabel("Loaded: 0");
        lblLoaded.setHorizontalAlignment(SwingConstants.CENTER);
        springLayout.putConstraint(SpringLayout.NORTH, btnSinglesb, 34,
                SpringLayout.SOUTH, lblLoaded);
        springLayout.putConstraint(SpringLayout.EAST, lblLoaded, 0,
                SpringLayout.EAST, btnSinglesb);
        springLayout.putConstraint(SpringLayout.WEST, lblLoaded, 0,
                SpringLayout.WEST, btnSinglesb);
        springLayout.putConstraint(SpringLayout.NORTH, lblLoaded, 10,
                SpringLayout.NORTH, this);
        add(lblLoaded);

        btnJfilechoser = new JButton(".sb2 directory");
        springLayout.putConstraint(SpringLayout.NORTH, btnJfilechoser, 57,
                SpringLayout.SOUTH, btnSinglesb);
        springLayout.putConstraint(SpringLayout.WEST, btnJfilechoser, 0,
                SpringLayout.WEST, btnSinglesb);
        springLayout.putConstraint(SpringLayout.EAST, btnJfilechoser, -162,
                SpringLayout.EAST, this);

        // The function called when the button is pressed.
        btnJfilechoser.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                handleMultipleButtonClick();

            }
        });
        add(btnJfilechoser);

    }

    /**
     * Action for event of multiple .sb2 button clicked.
     */
    protected void handleMultipleButtonClick()
    {
        ArrayList<String> fileList = fileHandler.openDirectoryFromFileMenu();
        addListToContext(fileList);

        if (fileList != null)
        {
            updateLoadedLabel(fileList.size());
        }
        else
        {
            updateLoadedLabel(0);
        }

    }

    /**
     * Handles the load single .sb2 click.
     */
    protected void handleSingleLoadClick()
    {
        ArrayList<String> arrayList;
        String path = fileHandler.openFileFromFileMenu();
        if (path != null)
        {
            arrayList = new ArrayList<String>();
            arrayList.add(path);
            addListToContext(arrayList);
            updateLoadedLabel(1);
        }
        else
        {
            updateLoadedLabel(0);
            addListToContext(null);
        }
    }

    /**
     * Update the label to say that it as loaded the number of files specified
     * by the parameter.
     * 
     * @param numberLoaded the number to be placed beside the string "Loaded: ".
     */
    private void updateLoadedLabel(int numberLoaded)
    {
        lblLoaded.setText("Loaded: " + numberLoaded);
    }

    /**
     * Add a list of files to the grading context.
     * 
     * @param validPaths A list containing valid paths of .sb2 files.
     */
    protected void addListToContext(ArrayList<String> validPaths)
    {
        context.setFileList(validPaths);
    }

}
