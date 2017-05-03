package scatt.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * @author Mikeal Anderson
 * @author Matt Stone
 * 
 * @version 1.0
 * 
 */
public class MainWindow extends JFrame
{

    private static final long serialVersionUID = -39970827731145361L;
    private static MainWindow singleton;
    private JPanel contentPane;
    private GraderContext context;

    /**
     * Create the frame.
     */
    private MainWindow()
    {
        context = GraderContextFactory.getContext();
        // new ScratchConfigurePanelInitializer().initialize(context);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
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

        JPanel load = new LoaderPanel(context);
        tabbedPane.addTab("Load", null, load, null);

        JPanel configure = new ConfigureControlPanel(context, this);
        tabbedPane.addTab("Configure", null, configure, null);

        final GradePanel GRADE = new GradePanel(context);
        GRADE.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent arg0)
            {
                GRADE.updateFormulaLabel();
            }
        });
        tabbedPane.addTab("Grade", null, GRADE, null);

        tabbedPane.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent arg0)
            {
                GRADE.updateFormulaLabel();
            }
        });

    }

    /**
     * Get the main window object; instantiates object if no such object exists.
     * 
     * @return the singleton object for the main window.
     */
    public static MainWindow getInstance()
    {
        if (singleton == null)
        {
            MainWindow.singleton = new MainWindow();
        }
        return singleton;
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
                    MainWindow frame = MainWindow.getInstance();
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
