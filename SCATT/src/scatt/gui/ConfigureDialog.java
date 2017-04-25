package scatt.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * A dialog represents a pop up window that prevents access to the main window
 * until closed.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class ConfigureDialog extends JDialog
{
    private static final long serialVersionUID = 2254627677540930088L;
    private JPanel contentPanel = new JPanel();
    private ConfigurablePanel panelUsedToSetConfiguration;
    private GraderContext context;

    /**
     * Create the dialog.
     * 
     * @param lockWindowWhileOpen - The owner window that can optionally be
     *            locked when the dialog is opened.
     * @param windowTitle - the title of the window.
     * @param ownerWindowToLock - set true if owner window should be locked
     *            until dialog closed.
     * @param mainPanel - the panel to be displayed within the dialog.
     * @param context - the context responsible for containing the graderModules
     *            and grader.
     */
    public ConfigureDialog(JFrame ownerWindowToLock, String windowTitle,
            boolean lockWindowWhileOpen, ConfigurablePanel mainPanel,
            GraderContext context)
    {
        super(ownerWindowToLock, windowTitle, lockWindowWhileOpen);
        this.panelUsedToSetConfiguration = mainPanel;
        this.context = context;
        setBounds(100, 100, 600, 400);
        // getContentPane().setLayout(new BorderLayout());
        // contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        // getContentPane().add(contentPanel, BorderLayout.CENTER);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        SpringLayout slcontentPanel = new SpringLayout();
        contentPanel.setLayout(slcontentPanel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0)
                    {
                        pressedOK();
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0)
                    {
                        pressedCancel();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }

        // force user to go through either the OK or Cancel interface.
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    /**
     * A callback for the OK button being pressed.
     */
    public void pressedOK()
    {
        panelUsedToSetConfiguration.commitChoices(context);
        hideWindow();
    }

    /**
     * A callback for the cancel button being pressed.
     */
    public void pressedCancel()
    {
        panelUsedToSetConfiguration.resetOptionsToDefault(context);
        hideWindow();
    }

    /**
     * Hide the current window.
     */
    protected void hideWindow()
    {
        this.setVisible(false);
    }

    @Override
    public void setVisible(boolean arg0)
    {
        super.setVisible(arg0);

        //re-load defaults if making the dialog visible again.
        if (arg0)
        {
            panelUsedToSetConfiguration.resetOptionsToDefault(context);
        }
    }

    // /**
    // * Launch the application.
    // *
    // * @param args command line arguments
    // */
    // public static void main(String[] args)
    // {
    // try
    // {
    // BasicDialog dialog = new BasicDialog(null, null, false);
    // dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    // dialog.setVisible(true);
    // }
    // catch (Exception e)
    // {
    // e.printStackTrace();
    // }
    // }
}
