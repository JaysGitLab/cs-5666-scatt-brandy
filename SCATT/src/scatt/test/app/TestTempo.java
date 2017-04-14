package scatt.test.app;

import static org.junit.Assert.assertEquals; 

import java.io.File;
import java.nio.file.Paths;

import org.junit.Test;

import scatt.Student;


/**
 * @author Mikeal
 * Tests getTempo.
 */
public class TestTempo 
{


	/**
	 * Test 1 for script count.
	 */
	@Test
	public void testTempo1()
	{
		String curPath = Paths.get("").toAbsolutePath().toString();

		// fix file path for unix machine
		if (curPath.substring(curPath.length() - 4).equals(
				File.separator + "src"))
		{
			curPath = curPath.substring(0, curPath.length() - 4);
		}

		String fileName = "Dress Up Tera.sb2";
		String zippedDirLocStr = curPath + File.separator + "TestData"
				+ File.separator + fileName;

		Student test = new Student(zippedDirLocStr);
		assertEquals("Tempo didn't match.", 60, test.getTempo());
	}

}
