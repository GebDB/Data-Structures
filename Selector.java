import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Daniel Becirevic (dzb0104@auburn.edu)
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
    private Selector() { }


    /**
     * Returns the minimum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the minimum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T min(Collection<T> coll, Comparator<T> comp) {
        if (coll == null || comp == null) {
            throw new IllegalArgumentException("Collection or Comparator is null.");
        }
        Iterator<T> itr = coll.iterator();
        if (!itr.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        T minimum = itr.next();
        while (itr.hasNext()) {
            T temp = itr.next();
            if (comp.compare(temp, minimum) < 0) {
                minimum = temp;
            }
        }
                
        return minimum;
    }


    /**
     * Selects the maximum value in the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty, this method throws a
     * NoSuchElementException. This method will not change coll in any way.
     *
     * @param coll    the Collection from which the maximum is selected
     * @param comp    the Comparator that defines the total order on T
     * @return        the maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T max(Collection<T> coll, Comparator<T> comp) {

        if (coll == null || comp == null) {
            throw new IllegalArgumentException("Collection or Comparator is null.");
        }
        Iterator<T> itr = coll.iterator();
        if (!itr.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        T maximum = itr.next();
        while (itr.hasNext()) {
            T temp = itr.next();
            if (comp.compare(temp, maximum) > 0) {
                maximum = temp;
            }
        }
                
        return maximum;
    }


    /**
     * Selects the kth minimum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth minimum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth minimum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth minimum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
        
        if (coll == null || comp == null) {
            throw new IllegalArgumentException("Collection or Comparator is null.");
        }
        Iterator<T> itr = coll.iterator();
        if (!itr.hasNext() || k < 1) {
            throw new NoSuchElementException("Collection is empty or illegal k value.");
        }
            
        T kminimum = min(coll, comp);
        if (k == 1) {
            return kminimum;
        }

        List<T> copy = new ArrayList<>(coll);
        
        for (int i = 0; i < copy.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (comp.compare(copy.get(j), copy.get(j-1)) < 0) {
                    T temp1 = copy.get(j);
                    copy.set(j, copy.get(j-1));
                    copy.set(j - 1, temp1);
                }
            }
        }

        Iterator<T> copyitr = copy.iterator();    
        int kth = 1;
        kminimum = copyitr.next();
        while (kth < k && copyitr.hasNext()) {
            T temp2 = copyitr.next();
            if (!temp2.equals(kminimum)) {     
                kminimum = temp2;
                kth++;
            }
        }

        if (kth != k) {
            throw new NoSuchElementException("No kth element");
        }
        else {
            return kminimum;
        }
    }

    /**
     * Selects the kth maximum value from the Collection coll as defined by the
     * Comparator comp. If either coll or comp is null, this method throws an
     * IllegalArgumentException. If coll is empty or if there is no kth maximum
     * value, this method throws a NoSuchElementException. This method will not
     * change coll in any way.
     *
     * @param coll    the Collection from which the kth maximum is selected
     * @param k       the k-selection value
     * @param comp    the Comparator that defines the total order on T
     * @return        the kth maximum value in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {

        if (coll == null || comp == null) {
            throw new IllegalArgumentException("Collection or Comparator is null.");
        }
        Iterator<T> itr = coll.iterator();
        if (!itr.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
            
        T kmaximum = max(coll, comp);
        if (k == 1) {
            return kmaximum;
        }

        List<T> copy = new ArrayList<>(coll);
        
        for (int i = 0; i < copy.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (comp.compare(copy.get(j), copy.get(j-1)) > 0) {
                    T temp1 = copy.get(j);
                    copy.set(j, copy.get(j-1));
                    copy.set(j - 1, temp1);
                }
            }
        }
        Iterator<T> copyitr = copy.iterator();    
        int kth = 1;
        kmaximum = copyitr.next();
        while (kth < k && copyitr.hasNext()) {
            T temp2 = copyitr.next();
            if (!temp2.equals(kmaximum)) {     
                kmaximum = temp2;
                kth++;
            }
        }
        if (kth != k) {
            throw new NoSuchElementException("No kth element");
        }
        else {
            return kmaximum;
        }
    }
 


    /**
     * Returns a new Collection containing all the values in the Collection coll
     * that are greater than or equal to low and less than or equal to high, as
     * defined by the Comparator comp. The returned collection must contain only
     * these values and no others. The values low and high themselves do not have
     * to be in coll. Any duplicate values that are in coll must also be in the
     * returned Collection. If no values in coll fall into the specified range or
     * if coll is empty, this method throws a NoSuchElementException. If either
     * coll or comp is null, this method throws an IllegalArgumentException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the range values are selected
     * @param low     the lower bound of the range
     * @param high    the upper bound of the range
     * @param comp    the Comparator that defines the total order on T
     * @return        a Collection of values between low and high
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                                      Comparator<T> comp) {

        if (coll == null || comp == null) {
            throw new IllegalArgumentException("Collection or Comparator is null.");
        }
        Iterator<T> itr = coll.iterator();
        if (!itr.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        List<T> copy = new ArrayList<>();
        while (itr.hasNext()) {
            T temp = itr.next();
            if (comp.compare(temp, low) >= 0 && comp.compare(temp, high) <= 0) {
                copy.add(temp);
            }
        }
        if (copy.isEmpty()) {
            throw new NoSuchElementException("No element in the range found.");
        }
            
                
        return copy;
    }


    /**
     * Returns the smallest value in the Collection coll that is greater than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the ceiling value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the ceiling value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {

        if (coll == null || comp == null) {
            throw new IllegalArgumentException("Collection or Comparator is null.");
        }
        Iterator<T> itr = coll.iterator();
        if (!itr.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        T result = null;
        while (itr.hasNext()) {
            T temp = itr.next();
            if (comp.compare(temp, key) >= 0 && result == null) {
                result = temp;                
            }
            else if (comp.compare(temp, key) >= 0 && comp.compare(temp, result) < 0) {
                result = temp;
            }
        }
        if (result == null) {
            throw new NoSuchElementException("No qualifying value.");
        }
                
        return result;
    }


    /**
     * Returns the largest value in the Collection coll that is less than
     * or equal to key, as defined by the Comparator comp. The value of key
     * does not have to be in coll. If coll or comp is null, this method throws
     * an IllegalArgumentException. If coll is empty or if there is no
     * qualifying value, this method throws a NoSuchElementException. This
     * method will not change coll in any way.
     *
     * @param coll    the Collection from which the floor value is selected
     * @param key     the reference value
     * @param comp    the Comparator that defines the total order on T
     * @return        the floor value of key in coll
     * @throws        IllegalArgumentException as per above
     * @throws        NoSuchElementException as per above
     */
    public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {

        if (coll == null || comp == null) {
            throw new IllegalArgumentException("Collection or Comparator is null.");
        }
        Iterator<T> itr = coll.iterator();
        if (!itr.hasNext()) {
            throw new NoSuchElementException("Collection is empty.");
        }
        T result = null;
        while (itr.hasNext()) {
            T temp = itr.next();
            if (comp.compare(temp, key) <= 0 && result == null) {
                result = temp;                
            }
            else if (comp.compare(temp, key) <= 0 && comp.compare(temp, result) > 0) {
                result = temp;
            }
        }
        if (result == null) {
            throw new NoSuchElementException("No qualifying value.");
        }
                
        return result;
    }

}
