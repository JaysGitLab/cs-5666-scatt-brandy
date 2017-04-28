package scatt.gui;

import java.util.ArrayList;
import java.util.HashMap;

import scatt.GraderWeightedComponent;
import scatt.WeightedGrader;

/**
 * A class that wraps all grading related fields. The responsibility of this
 * class is to act as a container for grading related data. Examples of such
 * data is the .sb2 files to grade, the configuration parameters of the grader,
 * and the actual grader.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class GraderContext
{
    private ArrayList<String> fileList = null;
    private ArrayList<GraderWeightedComponent> components = null;
    private HashMap<String, GraderWeightedComponent> componentMap = null;
    private WeightedGrader grader = null;

    /**
     * No arg constructor.
     */
    public GraderContext()
    {
        components = new ArrayList<GraderWeightedComponent>();
        componentMap = new HashMap<String, GraderWeightedComponent>();
    }

    /**
     * Set a list of valid .sb2 files.
     * 
     * @param fileListOfValidSb2 an array list containing paths to valid .sb2
     *            files.
     */
    public void setFileList(ArrayList<String> fileListOfValidSb2)
    {
        fileList = fileListOfValidSb2;
    }

    /**
     * Simply prints out the valid files.
     */
    public void printFilePaths()
    {
        if (fileList != null)
        {
            for (String item : fileList)
            {
                System.out.println(item);
            }
        }
    }

    /**
     * @param newComponent a new grader component to add to the grader.
     */
    public void addComponent(GraderWeightedComponent newComponent)
    {
        components.add(newComponent);
        componentMap.put(newComponent.getModuleName(), newComponent);

        // new grader will need to be constructed, set to null to be detected.
        if (grader != null)
        {
            grader = null;
        }
    }

    /**
     * Finds a module with the given name.
     * 
     * @param moduleName the name of the component to attempt to retrieve.
     * @return a reference to the module or null if there is no module.
     */
    public GraderWeightedComponent getComponent(String moduleName)
    {
        GraderWeightedComponent ret = componentMap.get(moduleName);
        return ret;
    }

    /**
     * Get all components in a raw array.
     * 
     * @return All components contained in an array.
     */
    public Object[] getAllComponents()
    {
        return (Object[]) components.toArray();
    }

    /**
     * Grades the files with the configured grader.
     */
    public void grade()
    {
        configureGrader();
        gradeFiles();
    }

    /**
     * Grade all files stored in the the fileList.
     */
    private void gradeFiles()
    {

    }

    /**
     * Prepares the grader object for grading.
     */
    private void configureGrader()
    {
        if (grader == null)
        {
            // convert arraylist to raw array //@formatter:off
            GraderWeightedComponent[] comps =
                    new GraderWeightedComponent[components.size()];
            //@formatter:on

            for (int i = 0; i < components.size(); ++i)
            {
                comps[i] = components.get(i);
            }

            try
            {
                grader = new WeightedGrader(comps);
            }
            catch (IllegalArgumentException e)
            {
                System.err.println(e.getMessage());
                grader = null;
            }

        }

    }

}
