package scatt;

import java.util.ArrayList;
import java.util.Scanner;

import scatt.gradermodules.SpriteGrader;

/**
 * The main command line grader.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 * 
 */
public class CommandLineGrader
{
    private static boolean allowExtraCreditForSprite;
    private static double totalSpriteRequired;
    private static SpriteGrader sg;
    private static ArrayList<Student> students = new ArrayList<Student>();
    private static Scanner kb = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        kb = new Scanner(System.in);

        pollUserForConfiguration();
        setUpSpriteGrading();

        if (args != null && args.length > 0)
        {
            // output grade of that individual student
            makeStudent(args[0]);
        }
        else
        {
            pollUserForSB2Files();
        }

        // grade the students
        for (Student student : students)
        {
            grade(student);
        }

        kb.close();

    }

    /**
     * Sets up the sprite weighted grader.
     * 
     * @precondition the fields for sprite grading have been polled from user
     *               and set before this method is called. see
     *               pollUserForConfiguration();
     * 
     * 
     */
    private static void setUpSpriteGrading()
    {
        sg = new SpriteGrader(1.0f, 
                (int) CommandLineGrader.totalSpriteRequired);
        sg.setExtraCreditMode(allowExtraCreditForSprite);
    }

    /**
     * Obtain information from the user about grading configuration.
     */
    private static void pollUserForConfiguration()
    {
        System.out.println("How many sprites required for a grade of 100?");
        totalSpriteRequired = kb.nextDouble();

        // clear buffer of new line
        kb.nextLine();

        System.out.println("Allow extra credit? (type yes or no)");
        String response = kb.nextLine();

        allowExtraCreditForSprite = response.toLowerCase().charAt(0) == 'y';
        

    }

    /**
     * Repeatedly ask the user to provide file paths for an SB2 file. The method
     * terminates when the user types quit.
     */
    private static void pollUserForSB2Files()
    {
        String answer = null;
        // repeatedly ask the user for students to grade.
        do
        {
            System.out.println("Enter student file path "
                    + "to be graded, or DONE to stop "
                    + "(grades displayed after).");
            answer = kb.nextLine();
            if (!answer.toUpperCase().equals("DONE"))
            {
                makeStudent(answer);
            }
            else
            {
                // break the loop.
                answer = null;
            }
        } while (answer != null);
        kb.close();
        System.out.println("Students loaded.");
    }

    /**
     * Method that prints the grade of the student.
     * 
     * @param student the student object to grade.
     */
    private static void grade(Student student)
    {
        //System.out.println("Sprite Count: " + student.getSpriteCount());
        sg.getGradeFrom0To100(student);
    }

    /**
     * Attemtps to create a student if the file path is valid.
     * 
     * @param filePath the path to the .sb2 file
     */
    private static void makeStudent(String filePath)
    {
        String extension = filePath.substring(filePath.length() - 4);
        if (extension.equals(".sb2"))
        {
            Student student = new Student(filePath);
            // grade(student);
            students.add(student);
        }
        else
        {
            System.out.println("Please enter a valid"
                    + " file with a .sb2 extension.");
            System.out.println("your extension:\n" + extension);
        }
    }
}
