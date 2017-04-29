package scatt.gui;

/**
 * Abstraction that allows custom defined initialization of all configuration
 * panels. Configuration panels are graphical GUI jpanels that are responsible
 * for configuring specific modules that are a WeightedGraderComponents.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public interface CFPanelInitializer
{
    /**
     * Initialize graders based on the context.
     * 
     * @param context the context to be used to initialize.
     */
    public void initialize(GraderContext context);
}
