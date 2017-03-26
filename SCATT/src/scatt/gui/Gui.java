package scatt.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
public class Gui
{

    private JFrame frame;
    private JFileChooser dirChooser;
    private JFileChooser fileChooser;

    /**
     * Create the GUI application.
     */
    public Gui()
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
                    Gui window = new Gui();
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
     */
    protected void openDirectoryFromFileMenu()
    {
        // open file chooser and then check if they approved a file
        if (dirChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File dir = dirChooser.getSelectedFile();
            System.out.println("opened: " + dir.getAbsolutePath());
            System.out.println("printing found .sb2 files in directory");

            // find every .sb2 in this directory and load them into memory
            // TODO (INCOMPLETE)
            for (File file : dir.listFiles())
            {
                String path = file.getAbsolutePath();
                if (path.substring(path.length() - 4).equals(".sb2"))
                {
                    // create a student object? printing for now
                    System.out.println(file.getAbsolutePath());
                }
            }
        }
    }

    /**
     * Test method that opens up a single file from the file menu.
     */
    protected void openFileFromFileMenu()
    {
        // open file chooser and then check if they approved a file
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File openedSb2 = fileChooser.getSelectedFile();
            // below is an example of how it might look to create a student
            // Student student = new Student(openedSb2.getAbsolutePath());
            System.out.println(openedSb2.getAbsolutePath());

        }

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
