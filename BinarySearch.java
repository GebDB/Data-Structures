import java.util.Arrays;
import java.util.Comparator;

/**
 * Binary search.
 */
public class BinarySearch {

    /**
     * Returns the index of the first key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException("a, key, or comparator is null.");
        }
        int start = 0;
        int end = a.length - 1;
        int middle = (start + end) / 2;
        int firstIndex = -1;
        while (start <= end) {
            int cmp = comparator.compare(key, a[middle]);
            if (cmp < 0) {
                end = middle - 1;
                middle = (start + end) / 2;
            }
            else if (cmp > 0) {
                start = middle + 1;
                middle = (start + end) / 2;
            }
            else {
                end = middle - 1;
                firstIndex = middle;
                middle = (start + end) / 2;
            }
        }
        return firstIndex;
        
    }
               
               

    /**
     * Returns the index of the last key in a[] that equals the search key, 
     * or -1 if no such key exists. This method throws a NullPointerException
     * if any parameter is null.
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new NullPointerException("a, key, or comparator is null.");
        }
        int start = 0;
        int end = a.length - 1;
        int middle = (start + end) / 2;
        int lastIndex = -1;
        while (start <= end) {
            int cmp = comparator.compare(key, a[middle]);
            if (cmp < 0) {
                end = middle - 1;
                middle = (start + end) / 2;
            }
            else if (cmp > 0) {
                start = middle + 1;
                middle = (start + end) / 2;
            }
            else {
                start = middle + 1;
                lastIndex = middle;
                middle = (start + end) / 2;
            }
        }
        return lastIndex;

    }

}
