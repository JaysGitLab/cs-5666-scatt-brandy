package scatt.gui;

import scatt.gradermodules.BlockGrader;
import scatt.gradermodules.CostumeGrader;
import scatt.gradermodules.ListGrader;
import scatt.gradermodules.ScriptGrader;
import scatt.gradermodules.SoundGrader;
import scatt.gradermodules.SpriteGrader;
import scatt.gradermodules.TempoGrader;
import scatt.gradermodules.VariableGrader;

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

        float totalNumberModules = 7;
        float equalDistribute = 1 / totalNumberModules;

        // add and configure the sprite grader
        SpriteGrader sg = new SpriteGrader(equalDistribute, 10);
        ret.addComponent(sg);

        SoundGrader sndg = new SoundGrader(equalDistribute, 1, 1, .5f);
        ret.addComponent(sndg);

        ScriptGrader scriptGrader = new ScriptGrader(equalDistribute, 5);
        ret.addComponent(scriptGrader);

        VariableGrader varGrader = new VariableGrader(equalDistribute, 3);
        ret.addComponent(varGrader);

        CostumeGrader costGrader = new CostumeGrader(equalDistribute, 2);
        ret.addComponent(costGrader);

        ListGrader listGrader = new ListGrader(equalDistribute, 0);
        ret.addComponent(listGrader);

        // since tempo is not really used normally, this is initialized to have
        // a 0 weight, which means it isn't contributing to the grade.
        TempoGrader tempoGrader = new TempoGrader(0f, 60);
        ret.addComponent(tempoGrader);

        BlockGrader blockGrader = new BlockGrader(equalDistribute, 1, 2, 3, 4,
                5, 6, 7, 9, 10, 0);
        ret.addComponent(blockGrader);

        return ret;
    }
}
