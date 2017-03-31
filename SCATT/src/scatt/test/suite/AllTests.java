package scatt.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import scatt.test.app.TestSpriteCount;
import scatt.test.app.unzipper.TestUnZipper;

/**
 * @author Matt Stone
 * @author Mikeal
 * @version 1.0
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ TestSpriteCount.class, TestUnZipper.class })
public class AllTests
{

}
