import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;

public class GreaterThanSubsetTest {

    @Test
    public void testGreaterThanSubset() {
        // Create a collection of integers
        Collection<Integer> numbers = new ArrayList<>();
        numbers.add(9);
        numbers.add(1);
        numbers.add(5);
        numbers.add(3);
        numbers.add(7);

        // Define the value to compare against
        int value = 1;

        // Call the greaterThanSubset method
        int Count = CountOdds.countOdds(numbers, value);

        
        // Check if each element is greater than the value
        for (Integer element : result) {
            System.out.println(result);
            assertTrue(element > value);
        }
    }
}