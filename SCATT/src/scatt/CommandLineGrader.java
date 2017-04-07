package scatt;

import java.util.Scanner;

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
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        if (args != null && args.length > 0)
        {
            // output grade of that individual student
            makeStudent(args[0]);
        }
        else
        {
            pollUserForSB2Files();
        }

    }

    /**
     * Repeatedly ask the user to provide file paths for an SB2 file. The method
     * terminates when the user types quit.
     */
    private static void pollUserForSB2Files()
    {
        Scanner kb = new Scanner(System.in);
        String answer = null;
        // repeatedly ask the user for students to grade.
        do
        {
            System.out.println("Enter a file path "
                    + "to be graded, or QUIT to quit.");
            answer = kb.nextLine();
            if (!answer.toUpperCase().equals("QUIT"))
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
        System.out.println("Grader closed.");
    }

    /**
     * Method that prints the grade of the student.
     * 
     * @param student the student object to grade.
     */
    private static void grade(Student student)
    {
        System.out.println("Sprite Count: " + student.getSpriteCount());
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
            grade(student);
        }
        else
        {
            System.out.println("Please enter a valid"
                    + " file with a .sb2 extension.");
            System.out.println("your extension:\n" + extension);
        }
    }
}
