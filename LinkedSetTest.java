import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class LinkedSetTest {

    private LinkedSet<Integer> set;

    @Before
    public void setUp() {
        set = new LinkedSet<>();
    }

    @Test
    public void testAdd() {
        assertTrue(set.add(5));
        assertTrue(set.add(10));
        assertTrue(set.add(6));
        assertFalse(set.add(5)); // Adding duplicate element, should return false
        assertFalse(set.add(null)); // Adding null element, should return false
        assertEquals(3, set.size());
    }
    @Test
    public void testRemove() {
        set.add(5);
        set.add(10);
        assertTrue(set.remove(5));
        assertFalse(set.remove(7)); // Removing non-existing element, should return false
        assertEquals(1, set.size());
    }
    @Test
    public void testContains() {
        set.add(5);
        set.add(10);
        assertTrue(set.contains(5));
        assertFalse(set.contains(7)); // Element not in set, should return false
    }
        @Test
    public void testEquals() {
        LinkedSet<Integer> otherSet = new LinkedSet<>();
        set.add(5);
        set.add(10);
        otherSet.add(5);
        otherSet.add(10);
        assertTrue(set.equals(otherSet)); // Same elements, should return true
    }
        @Test
    public void testUnion() {
        set.add(5);
        set.add(10);
        LinkedSet<Integer> otherSet = new LinkedSet<>();
        otherSet.add(10);
        otherSet.add(15);
        Set<Integer> unionSet = set.union(otherSet);
        assertTrue(unionSet.contains(5));
        assertTrue(unionSet.contains(10));
        assertTrue(unionSet.contains(15));
    }
    @Test
    public void testIntersection() {
      LinkedSet<Integer> set1 = new LinkedSet<>();
       set1.add(1);
      set1.add(2);
       set1.add(3);

       LinkedSet<Integer> set2 = new LinkedSet<>();
       set2.add(2);
       set2.add(3);
       set2.add(4);

       Set<Integer> intersectionSet = set1.intersection(set2);
       assertTrue(intersectionSet.contains(2));
       assertTrue(intersectionSet.contains(3));
       assertFalse(intersectionSet.contains(1)); // 1 is not in both sets, should return false
      assertFalse(intersectionSet.contains(4)); // 4 is not in both sets, should return false
   }
}
