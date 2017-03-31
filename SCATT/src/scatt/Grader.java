package scatt;

import java.util.Scanner;

/**
 * The main command line grader.
 * 
 * @author Matt Stone
 * 
 * @version 1.0
 * 
 */
public class Grader
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        if (args.length > 1)
        {
            // output grade of that individual student
            Student student = new Student(args[1]);
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
                String extension = answer.substring(answer.length() - 4);
                if (extension.equals(".sb2"))
                {
                    // change this so that it creates a student
                    // object and grades the object
                    
                    // TODO create a student object and print
                    // output of a .grade() method
                    System.out.println("entered: " + answer);
                }
                else
                {
                    System.out.println("Please enter a valid"
                            + " file with a .sb2 extension.");
                    System.out.println("your extension:\n" + extension);
                }
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
}
