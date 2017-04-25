package scatt.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

/**
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class SpriteConfigurable extends Configurable
{
    private static final long serialVersionUID = -5305156039992663561L;

    /**
     * Create a configuration window for grading sprites.
     * 
     * @param windowToLock - the window to lock while the dialog is open.
     * @param title - the title of the window
     */
    public SpriteConfigurable(JFrame windowToLock, String title)
    {
        super(windowToLock, title, true);
    }

}
