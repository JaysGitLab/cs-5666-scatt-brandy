package scatt.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private GraderContext context;

    /**
     * Create the panel.
     * 
     * @param context - an object that is used to store/retrieve grading related
     *            context.
     */
    public LoaderPanel(GraderContext context)
    {
        this.context = context;
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        fileHandler = new FileHandler();

        btnJfilechoser = new JButton(".sb2 directory");

        // The function called when the button is pressed.
        btnJfilechoser.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                addListToContext(fileHandler.openDirectoryFromFileMenu());
            }
        });

        btnSinglesb = new JButton("single .sb2");
        btnSinglesb.addActionListener(new ActionListener()
        {
            private ArrayList<String> arrayList;

            public void actionPerformed(ActionEvent arg0)
            {
                String path = fileHandler.openFileFromFileMenu();
                arrayList = new ArrayList<String>();
                arrayList.add(path);
                addListToContext(arrayList);
            }
        });
        add(btnSinglesb);
        add(btnJfilechoser);

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

    /**
     * A method that simply prints the word TEST. To be used for debugging GUI
     * behavior.
     */
    public void test()
    {
        System.out.println("TEST");
    }
}
