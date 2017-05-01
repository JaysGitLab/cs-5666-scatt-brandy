package scatt.gui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import scatt.GraderWeightedComponent;
import scatt.Student;
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
    private ArrayList<String[]> gradedPairs = null;

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
     * 
     * @throws WeightedGraderFailureException If grader fails to create, this
     *             exception will be thrown.
     */
    public void grade() throws WeightedGraderFailureException
    {
        configureGrader();
        iterateOverFilesAndStoreGrade();
    }

    /**
     * Grade all files stored in the the fileList.
     * 
     * TODO: upzipper should instead pass exceptions upward - this will require
     * a major change (at least 83 file updates).
     * 
     * 
     * @precondition grader is set up
     * @precondition all file paths are valid files.
     */
    private void iterateOverFilesAndStoreGrade()
    {
        gradedPairs = new ArrayList<String[]>();
        for (String fstr : fileList)
        {
            String[] breakup = fstr.split(Pattern.quote(File.separator));

            // keep like this in case exception occurs
            // (update name to author field if no exception on student creation)
            String stdFileName = breakup[breakup.length - 1];
            double weightGrade = 0.0f;
            try
            {
                Student student = new Student(fstr);
                weightGrade = grader.grade(student);
                // TODO: update stdFileName to author field
            }
            catch (Exception e)
            {
                // TODO currently doesn't throw an exception, change that so the
                // student class will throw an exception if fails to open
                // and update the exception type to match throw exception
                weightGrade = -1;
                stdFileName += "FAILED TO LOAD";
            }

            //@formatter:off
            String[] pair = new String[] {stdFileName,
                    String.format("%6.2f", weightGrade) };
            
            gradedPairs.add(pair);
            //@formatter:on 
        }
    }

    /**
     * Prepares the grader object for grading.
     * 
     * @throws WeightedGraderFailureException - signals that the grader could
     *             not be created with the given configuration.
     */
    private void configureGrader() throws WeightedGraderFailureException
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
                throw new WeightedGraderFailureException(
                        "Could not create a weighted grader"
                                + " - check that weights sum to 1.0");
            }

        }

    }

    /**
     * Get a list of names paired with their grade.
     * 
     * @return An array list of {name, grade} pairs.
     */
    public ArrayList<String[]> getGradedPairList()
    {
        return gradedPairs;
    }

}
