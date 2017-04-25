package scatt.gui;

import java.util.HashMap;

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
    //@formatter:off
    private static final long serialVersionUID = 4234616041214753769L;
    private static HashMap<String, ConfigurablePanel> 
        allPanels = new HashMap<String, ConfigurablePanel>();
    protected GraderContext context;
    //@formatter:on

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
     * @param panelKey the string key for the panel
     */
    protected ConfigurablePanel(GraderContext context, String panelKey)
    {
        this.context = context;
        ConfigurablePanel.allPanels.put(panelKey, this);
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

    /**
     * Get a panel for a given key. 
     * @param moduleName the key of the module to get.
     * @return the module for the given key, or null if there is no object for
     *         the associated key.
     */
    public static ConfigurablePanel get(String moduleName)
    {
        return allPanels.get(moduleName);
    }
}
