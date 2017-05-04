package scatt.gui;

import scatt.gradermodules.ScriptGrader;
import scatt.gradermodules.SoundGrader;
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
        
        float totalNumberModules = 3;
        float equalDistribute = 1 / totalNumberModules;
        
        
        //add and configure the sprite grader
        SpriteGrader sg = new SpriteGrader(equalDistribute, 10);
        ret.addComponent(sg);
        
        SoundGrader sndg = new SoundGrader(equalDistribute, 1, 1, .5f);
        ret.addComponent(sndg);
        
        ScriptGrader scriptGrader = new ScriptGrader(equalDistribute, 5);
        ret.addComponent(scriptGrader);

        return ret;
    }

}