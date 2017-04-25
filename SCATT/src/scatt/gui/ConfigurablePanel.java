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
    protected GraderContext context;

    /**
     * Lock the no-arg constructor.
     */
    @SuppressWarnings("unused")
    private ConfigurablePanel()
    {
        System.out.println("this constructor is forbidden.");
    }

    /**
     * Argument constructor required for instantiation.
     * 
     * @param context the grader context for the field to use.
     */
    protected ConfigurablePanel(GraderContext context)
    {
        this.context = context;
    }

    /**
     * Used to signal that changes should be updated in the grader.
     * 
     * @param context - the object encompassing the grader and grader modules.
     */
    public abstract void commitChoices(GraderContext context);

    /**
     * Used to reset the panel to the default/previous values. Used in cancel
     * operations.
     * 
     * @param context - the object encompassing the grader and grader modules.
     */
    public abstract void resetOptionsToDefault(GraderContext context);
}
