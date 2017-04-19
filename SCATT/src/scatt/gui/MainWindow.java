package scatt.gui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

/**
 * @author Mikeal Anderson
 * @author Matt Stone
 * 
 * @version 1.0
 * 
 */
public class MainWindow extends JFrame
{

    /**
	 * 
	 */
    private static final long serialVersionUID = -39970827731145361L;
    private JPanel contentPane;

    // private JFileChooser dirChooser;
    // private JFileChooser fileChooser;

    /**
     * Create the frame.
     */
    public MainWindow()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gblContentPane = new GridBagLayout();
        //@formatter:off
        gblContentPane.columnWidths = new int[] {0, 0 };
        gblContentPane.rowHeights = new int[] {0, 0 };
        gblContentPane.columnWeights = new double[] {1.0, Double.MIN_VALUE };
        gblContentPane.rowWeights = new double[] {1.0, Double.MIN_VALUE };
        //@formatter:on
        contentPane.setLayout(gblContentPane);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        GridBagConstraints gbcTabbedPane = new GridBagConstraints();
        gbcTabbedPane.fill = GridBagConstraints.BOTH;
        gbcTabbedPane.gridx = 0;
        gbcTabbedPane.gridy = 0;
        contentPane.add(tabbedPane, gbcTabbedPane);

        JPanel load = new JPanel();
        tabbedPane.addTab("Load", null, load, null);
        load.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JPanel loader = new LoaderPanel();
        load.add(loader);

        // JPanel configure = new JPanel();
        JPanel configure = new ConfigurePanel();
        tabbedPane.addTab("Configure", null, configure, null);

        // JPanel grade = new JPanel();
        JPanel grade = new GradePanel();
        tabbedPane.addTab("Grade", null, grade, null);
        
    }

    /**
     * Launch the application.
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
                    MainWindow frame = new MainWindow();
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
