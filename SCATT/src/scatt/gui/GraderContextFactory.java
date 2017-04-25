package scatt.gui;

import scatt.gradermodules.SpriteGrader;

/**
 * Factory class for creating a pre-configured GraderContext. This is designed
 * to allow the main window to be closed for modification. 
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class GraderContextFactory
{

    /**
     * Get a context. This allows the main window to be close for modification
     * and open for extension.
     * 
     * @return a pre-configured context
     */
    public static GraderContext getContext()
    {
        GraderContext ret = new GraderContext();
        
        //add and configure the sprite grader
        SpriteGrader sg = new SpriteGrader(1.0f, 10);
        ret.addComponent(sg);

        return ret;
    }

}
