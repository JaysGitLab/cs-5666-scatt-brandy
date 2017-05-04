package scatt.gui;

/**
 * The concrete class responsible for initializing all of the configuration
 * panels.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class ScratchConfigurePanelInitializer implements CFPanelInitializer
{
    /**
     * Initializes the configuration panels, for more information see interface
     * documentation.
     * 
     * @see scatt.gui.CFPanelInitializer#initialize(scatt.gui.GraderContext)
     * @param context - the context obj initialize the the configuration panels
     */
    public void initialize(GraderContext context)
    {
        // simply initializing panels will store them in a static field
        // (multition pattern)
        new SpriteConfigurablePanel(context);
        new SoundConfigurablePanel(context);
        new ScriptConfigurablePanel(context);
        new VariableConfigurablePanel(context);
        new CostumeConfigurablePanel(context);
        new ListConfigurablePanel(context);
    }

}
