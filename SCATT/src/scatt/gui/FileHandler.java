package scatt.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Matt Stone
 * @author
 * @author
 * @author
 * @version 1.0
 * 
 */
public class FileHandler extends JFrame
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 931158606870083108L;
    private JFrame frame;
    private JFileChooser dirChooser;
    private JFileChooser fileChooser;

    /**
     * Create the GUI application.
     */
    public FileHandler()
    {
        dirChooser = new JFileChooser();
        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        fileChooser = new JFileChooser();
        fileChooser
                .setFileFilter(new FileNameExtensionFilter("sb2 file", "sb2"));

        initialize();

    }

    /**
     * Launch the GUI application.
     * 
     * @param args command line arguments.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    FileHandler window = new FileHandler();
                    window.frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Test method that opens up a file chooser.
     * 
     * @return returns a list of valid .sb2 paths contained in the folder.
     */
    protected ArrayList<String> openDirectoryFromFileMenu()
    {
        ArrayList<String> validPaths = new ArrayList<String>();
        // open file chooser and then check if they approved a file
        if (dirChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File dir = dirChooser.getSelectedFile();
            System.out.println("opened: " + dir.getAbsolutePath());

            // find every .sb2 in this directory and load them into memory
            for (File file : dir.listFiles())
            {
                String path = file.getAbsolutePath();
                int dotIndex = path.lastIndexOf('.');

                // if there is a period
                if (dotIndex != -1)
                {
                    String extension = path.substring(dotIndex);
                    if (extension.equals(".sb2"))
                    {
                        // System.out.println(file.getAbsolutePath());
                        validPaths.add(file.getAbsolutePath());

                    }
                }
            }
        }

        return validPaths;
    }

    /**
     * Test method that opens up a single file from the file menu.
     * 
     * @return the file path to the selected .sb2 if it is valid or null if
     *         invalid path.
     */
    protected String openFileFromFileMenu()
    {
        String result = null;
        // open file chooser and then check if they approved a file
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File openedSb2 = fileChooser.getSelectedFile();
            // System.out.println(openedSb2.getAbsolutePath());
            int dotIndex = openedSb2.getAbsolutePath().lastIndexOf(".");

            // check if file has an extension
            if (dotIndex != -1)
            {
                String temp = openedSb2.getAbsolutePath();
                // check if appropriate extension
                if (temp.substring(dotIndex).equals(".sb2"))
                {
                    // set up result to return the path.
                    result = temp;
                }
            }
        }

        return result;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize()
    {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnJfilechoser = new JButton(".sb2 directory");

        // The function called when the button is pressed.
        btnJfilechoser.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                openDirectoryFromFileMenu();
            }
        });
        btnJfilechoser.setBounds(157, 46, 133, 25);
        frame.getContentPane().add(btnJfilechoser);

        JButton btnSinglesb = new JButton("single .sb2");
        btnSinglesb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                openFileFromFileMenu();
            }
        });
        btnSinglesb.setBounds(173, 121, 97, 25);
        frame.getContentPane().add(btnSinglesb);
    }
}
