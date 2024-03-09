import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class CountOddsTest {


   /** Fixture initialization (common initialization for all tests). **/
   @Before public void setUp() {
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {


      int[] test = {1, 1, 1, 1, 5};
      int Count = CountOdds.countOdds(test);
      Assert.assertEquals("", 5, Count);
   }
}
