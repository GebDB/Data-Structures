import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class SelectorTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testKMin() {
        List<Integer> inputList = Arrays.asList(-4, -4);
        int k = 2;
        Comparator<Integer> comparator = Comparator.naturalOrder();

        try {
            Integer result = Selector.kmin(inputList, k, comparator);
            fail("Expected NoSuchElementException not thrown");
        } catch (NoSuchElementException e) {
            System.out.println("caught");
        }
    }
}
