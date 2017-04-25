package scatt.gui;

import java.util.ArrayList;

import scatt.GraderWeightedComponent;

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
    }

}
