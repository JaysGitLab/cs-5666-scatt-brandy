package scatt.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

/**
 * Abstract class that represents a configuration window for components of
 * weighted graders.
 * 
 * A configurable is a GUI window for configuring a specific component of the
 * grader. For example, configuring how many sprites are required to receive a
 * grade of 100 is done in a configurable window.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class Configurable extends JDialog
{

    private static final long serialVersionUID = 9037989089269466819L;
    private final JPanel contentPanel = new JPanel();

    /**
     * Create a Configurable.
     * 
     * @param windowToLock window to freeze when this menu is opened.
     * @param title The title for the window.
     * @param modal The modality of the specified window owner. True value locks
     *            the "windowToLock" gui window, false doesn't lock the
     *            windowToLock.
     */
    public Configurable(JFrame windowToLock, String title, boolean modal)
    {
        super(windowToLock, title, modal);
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
                okButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0)
                    {
                        pressedOK();
                    }
                });
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
                cancelButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0)
                    {
                        pressedCancel();
                    }
                });
            }

        }
    }

    /**
     * Make the no-arg constructor locked from extension.
     */
    @SuppressWarnings("unused")
    private Configurable()
    {
        getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
        System.out.println("This constructor should never be used.");
    }

    /**
     * A callback for the OK button being pressed.
     */
    public void pressedOK()
    {
        commitChoices();
        hideWindow();
    }

    /**
     * A callaback for the cancel button being pressed.
     */
    public void pressedCancel()
    {
        resetOptionsToDefault();
        hideWindow();
    }

    /**
     * Hide the current window.
     */
    protected void hideWindow()
    {
        this.setVisible(false);
    }

    // ------------ Methods to make abstract after debugging ------------------

    /**
     * 
     */
    public void resetOptionsToDefault()
    {

    }

    /**
     * 
     */
    public void commitChoices()
    {

    }
}
