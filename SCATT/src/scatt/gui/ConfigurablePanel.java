package scatt.gui;

import javax.swing.JPanel;

/**
 * A polymorphic type that can be passed as a JPanel.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public abstract class ConfigurablePanel extends JPanel
{
    private static final long serialVersionUID = 4234616041214753769L;

    /**
     * Used to signal that changes should be updated in the grader.
     */
    public abstract void commitChoices();

    /**
     * Used to reset the panel to the default/previous values. Used in cancel
     * operations.
     */
    public abstract void resetOptionsToDefault();

}
