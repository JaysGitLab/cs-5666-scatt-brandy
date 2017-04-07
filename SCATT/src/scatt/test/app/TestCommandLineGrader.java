package scatt.test.app;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import scatt.CommandLineGrader;

/**
 * The class responsible for testing grader.
 * 
 * @author Matt Stone
 * @version 1.0
 * 
 */
public class TestCommandLineGrader
{
    ByteArrayOutputStream baos;
    PrintStream originalOut;
    PrintStream altOutStream;

    InputStream originalIn;
    InputStream is;

    /**
     * Called before each method is called.
     */
    @Before
    public void setUp()
    {
        originalOut = System.out;
        originalIn = System.in;

        baos = new ByteArrayOutputStream();
        altOutStream = new PrintStream(baos);
        System.setOut(altOutStream);

        String inputString = generateInputForTestPollMulti();
        is = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(is);

    }

    /**
     * Called after each method is called.
     */
    @After
    public void tearDown()
    {
        System.out.flush();
        System.setOut(originalOut);

        System.setIn(originalIn);
    }

    /**
     * Generates some input for when testing the when grader polls user for file
     * names.
     * 
     * @return a string with mocked user input.
     */
    private String generateInputForTestPollMulti()
    {
        // valid
        String userTypedArgumentsSimulated = TestUtils.getFilePathMultiOSSafe()
                + File.separator + "TestData" + File.separator
                + "Pong Starter.sb2" + "\n";

        // valid
        userTypedArgumentsSimulated += TestUtils.getFilePathMultiOSSafe()
                + File.separator + "TestData" + File.separator
                + "Pong Starter.sb2" + "\n";
        // invalid
        userTypedArgumentsSimulated += TestUtils.getFilePathMultiOSSafe()
                + File.separator + "TestData" + File.separator
                + "BadFile.abcdefg" + "\n";

        // user asks to quit
        userTypedArgumentsSimulated += "QUIT";
        return userTypedArgumentsSimulated;
    }

    /**
     * Test that the grader can handle a single file. Grading is highly
     * contingent, so this method passes if the grader outputs anything at all.
     */
    @Test
    public void testCMDGraderArgumentCorrect()
    {
        String zippedDataPath = TestUtils.getFilePathMultiOSSafe()
                + File.separator + "TestData" + File.separator
                + "Pong Starter.sb2";

        // emulate the command prompt @formatter:off
        // (java first argument is the word after classname)
        CommandLineGrader.main(new String[] {zippedDataPath });
        // @formatter:on

        String captured = baos.toString().trim().split("\n")[0];
        if (captured.charAt(captured.length() - 1) == '\r')
        {
            // trim off \r
            captured = captured.substring(0, captured.length() - 1);
        }
        String badPathMsh = "Please enter a valid file with a .sb2 extension.";
        System.err.println(captured);
        assertTrue(!captured.equals(badPathMsh));

    }

    /**
     * Test that the grader can handle a single file. Grading is highly
     * contingent, so this method passes if the grader outputs anything at all.
     */
    @Test
    public void testCMDGraderArgumentInCorrect()
    {
        String zippedDataPath = TestUtils.getFilePathMultiOSSafe()
                + File.separator + "TestData" + File.separator
                + "BadFile.abcdefg";

        // emulate the command prompt @formatter:off
        // (java first argument is the word after classname)
        CommandLineGrader.main(new String[] {zippedDataPath });
        // @formatter:on

        String captured = baos.toString().trim().split("\n")[0];
        if (captured.charAt(captured.length() - 1) == '\r')
        {
            // trim off \r
            captured = captured.substring(0, captured.length() - 1);
        }
        String badPathMsh = "Please enter a valid file with a .sb2 extension.";
        System.err.println(captured);
        assertTrue(captured.equals(badPathMsh));

    }

    /**
     * Test that the grader can handle multiple files.
     */
    @Test
    public void testCMDGraderPollUser()
    {
        // main will poll user until quit is accessed
        CommandLineGrader.main(null);

        // process what was output by the main method.
        String[] captured = baos.toString().trim().split("\n");
        if (captured[0].charAt(captured[0].length() - 1) == '\r')
        {
            for (int i = 0; i < captured.length; ++i)
            {
                // trim off \r
                captured[i] = captured[i]
                        .substring(0, captured[i].length() - 1);
            }
        }

        // check that strings were valid.
        String badPathMsh = "Please enter a valid file with a .sb2 extension.";
        // 1 not 0 to skip enter file prompt
        assertTrue(!captured[1].equals(badPathMsh));
        // 3 not 2 to skip enter file prompt
        assertTrue(!captured[3].equals(badPathMsh));
        // 5 not 4 to skip enter file prompt
        assertTrue(captured[5].equals(badPathMsh));

    }

}
