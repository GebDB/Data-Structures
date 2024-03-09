import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Provides an implementation of the Set interface.
 * A doubly-linked list is used as the underlying data structure.
 * Although not required by the interface, this linked list is
 * maintained in ascending natural order. In those methods that
 * take a LinkedSet as a parameter, this order is used to increase
 * efficiency.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @author Daniel Becirevic (dzb0104@auburn.edu)
 *
 */
public class LinkedSet<T extends Comparable<T>> implements Set<T> {

    //////////////////////////////////////////////////////////
    // Do not change the following three fields in any way. //
    //////////////////////////////////////////////////////////

    /** References to the first and last node of the list. */
    Node front;
    Node rear;

    /** The number of nodes in the list. */
    int size;

    /////////////////////////////////////////////////////////
    // Do not change the following constructor in any way. //
    /////////////////////////////////////////////////////////

    /**
     * Instantiates an empty LinkedSet.
     */
    public LinkedSet() {
        front = null;
        rear = null;
        size = 0;
    }


    //////////////////////////////////////////////////
    // Public interface and class-specific methods. //
    //////////////////////////////////////////////////

    ///////////////////////////////////////
    // DO NOT CHANGE THE TOSTRING METHOD //
    ///////////////////////////////////////
    /**
     * Return a string representation of this LinkedSet.
     *
     * @return a string representation of this LinkedSet
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (T element : this) {
            result.append(element + ", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }


    ///////////////////////////////////
    // DO NOT CHANGE THE SIZE METHOD //
    ///////////////////////////////////
    /**
     * Returns the current size of this collection.
     *
     * @return  the number of elements in this collection.
     */
    public int size() {
        return size;
    }

    //////////////////////////////////////
    // DO NOT CHANGE THE ISEMPTY METHOD //
    //////////////////////////////////////
    /**
     * Tests to see if this collection is empty.
     *
     * @return  true if this collection contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return (size == 0);
    }


    /**
     * Ensures the collection contains the specified element. Neither duplicate
     * nor null values are allowed. This method ensures that the elements in the
     * linked list are maintained in ascending natural order.
     *
     * @param  element  The element whose presence is to be ensured.
     * @return true if collection is changed, false otherwise.
     */
    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        
        Node newNode = new Node (element);
        if (size == 0) {
            front = rear = newNode;
            size++;
            return true;
        }
        
        Node currentNode = front;
        while (currentNode != null) 
        {
            
            if (currentNode.element.compareTo(element) == 0) {
               return false;
            }
            
            else if (currentNode.element.compareTo(element) > 0) {
               newNode.prev = currentNode.prev;
               newNode.next = currentNode;

               if (currentNode.prev == null) {
                   front = newNode;
               }
               
               else {
                   Node temp = currentNode.prev;
                   currentNode.prev.next = newNode;
                   newNode.prev = temp;
               }
               currentNode.prev = newNode;
               newNode.next = currentNode;
               size++;
               return true;
             }
             else if (currentNode.next == null) {
               currentNode.next = newNode;
               newNode.prev = currentNode;
               rear = newNode;
               size++;
               return true;
             }
             
             currentNode = currentNode.next;
         }      
   
        return false;
    }

    /**
     * Ensures the collection does not contain the specified element.
     * If the specified element is present, this method removes it
     * from the collection. This method, consistent with add, ensures
     * that the elements in the linked lists are maintained in ascending
     * natural order.
     *
     * @param   element  The element to be removed.
     * @return  true if collection is changed, false otherwise.
     */
    public boolean remove(T element) {
        Node currentNode = front;
        while (currentNode != null) 
        {
            if (currentNode.element.compareTo(element) == 0) {
               if (currentNode.next == null) {
                  rear = currentNode.prev;
                  if (rear != null) { //
                      rear.next = null;
                  }
                  else {
                      front = null;
                  } //
               }
               else if (currentNode.prev != null) {
                  currentNode.prev.next = currentNode.next;
                  currentNode.next.prev = currentNode.prev;
               }
               else if (currentNode.prev == null) {
                  front = currentNode.next;
                  front.prev = null;
               }
               size--;
               return true;
            }
            currentNode = currentNode.next;
        }
        return false;  
    }


    /**
     * Searches for specified element in this collection.
     *
     * @param   element  The element whose presence in this collection is to be tested.
     * @return  true if this collection contains the specified element, false otherwise.
     */
    public boolean contains(T element) {
      Node currentNode = front;
      while (currentNode != null) 
        {
            if (currentNode.element.compareTo(element) == 0) {
               return true;
           }
           currentNode = currentNode.next;
        }
        return false;
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(Set<T> s) {
        int counter = 0;
        int counter2 = 0;
        Node currentNode = front;
        while (currentNode != null) 
        {
           T element = currentNode.element;
           if (s.contains(element)) {
             counter++;
           }
           currentNode = currentNode.next;
        }
        Iterator<T> iterator = s.iterator();
        while (iterator.hasNext()) {
           iterator.next();
           counter2++;
        }
            
        return (counter == size && counter2 == counter);
    }


    /**
     * Tests for equality between this set and the parameter set.
     * Returns true if this set contains exactly the same elements
     * as the parameter set, regardless of order.
     *
     * @return  true if this set contains exactly the same elements as
     *               the parameter set, false otherwise
     */
    public boolean equals(LinkedSet<T> s) {
        int counter = 0;
        int counter2 = 0;
        Node currentNode = front;
        while (currentNode != null) 
        {
           T element = currentNode.element;
           if (s.contains(element)) {
             counter++;
           }
           currentNode = currentNode.next;
        }
        Iterator<T> iterator = s.iterator();
        while (iterator.hasNext()) {
           iterator.next();
           counter2++;
        }
            
        return (counter == size && counter2 == counter);
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(Set<T> s){
        Set<T> newSet = new LinkedSet<T>();

        Node currentNode = front;
        while (currentNode != null) {
           T element = currentNode.element;
           
           newSet.add(element);
           
           currentNode = currentNode.next;
        }
        Iterator<T> iterator = s.iterator();
        while (iterator.hasNext()) {
           T element2 = iterator.next();
           if (!newSet.contains(element2)) {
             newSet.add(element2);
           }
        }
        return newSet;
    }


    /**
     * Returns a set that is the union of this set and the parameter set.
     *
     * @return  a set that contains all the elements of this set and the parameter set
     */
    public Set<T> union(LinkedSet<T> s){
        Set<T> newSet = new LinkedSet<T>();

        Node currentNode = front;
        while (currentNode != null) {
           T element = currentNode.element;
           
           newSet.add(element);
           
           currentNode = currentNode.next;
        }
        Iterator<T> iterator = s.iterator();
        while (iterator.hasNext()) {
           T element2 = iterator.next();
           if (!newSet.contains(element2)) {
             newSet.add(element2);
           }
        }
        return newSet;
    }


    /**
     * Returns a set that is the intersection of this set and the parameter set.
     *
     * @return  a set that contains elements that are in both this set and the parameter set
     */
    public Set<T> intersection(Set<T> s) {
        Set<T> newSet = new LinkedSet<T>();

        Node currentNode = front;
        while (currentNode != null) {
           T element = currentNode.element;
           if (s.contains(element)) {          
               newSet.add(element);
           }
           currentNode = currentNode.next;
        }

        return newSet;
    }

    /**
     * Returns a set that is the intersection of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in both
     *            this set and the parameter set
     */
    public Set<T> intersection(LinkedSet<T> s) {
        Set<T> newSet = new LinkedSet<T>();

        Node currentNode = front;
        while (currentNode != null) {
           T element = currentNode.element;
           if (s.contains(element)) {          
               newSet.add(element);
           }
           currentNode = currentNode.next;
        }

        return newSet;
    }


    /**
     * Returns a set that is the complement of this set and the parameter set.
     *
     * @return  a set that contains elements that are in this set but not the parameter set
     */
    public Set<T> complement(Set<T> s) {
        Set<T> newSet = new LinkedSet<T>();

        Node currentNode = front;
        while (currentNode != null) {
           T element = currentNode.element;
           if (!s.contains(element)) {          
               newSet.add(element);
           }
           currentNode = currentNode.next;
        }

        return newSet;
    }


    /**
     * Returns a set that is the complement of this set and
     * the parameter set.
     *
     * @return  a set that contains elements that are in this
     *            set but not the parameter set
     */
    public Set<T> complement(LinkedSet<T> s) {
        Set<T> newSet = new LinkedSet<T>();

        Node currentNode = front;
        while (currentNode != null) {
           T element = currentNode.element;
           if (!s.contains(element)) {          
               newSet.add(element);
           }
           currentNode = currentNode.next;
        }

        return newSet;
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in ascending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> iterator() {
        return new AscendingIterator();
        
    }


    /**
     * Returns an iterator over the elements in this LinkedSet.
     * Elements are returned in descending natural order.
     *
     * @return  an iterator over the elements in this LinkedSet
     */
    public Iterator<T> descendingIterator() {
        return new DescendingIterator();
    }


    /**
     * Returns an iterator over the members of the power set
     * of this LinkedSet. No specific order can be assumed.
     *
     * @return  an iterator over members of the power set
     */
    public Iterator<Set<T>> powerSetIterator() {
        return new PowerSetIterator();
    }



    //////////////////////////////
    // Private utility methods. //
    //////////////////////////////


    
    private class AscendingIterator implements Iterator<T> {
        private Node current;

        public AscendingIterator() {
            if (!isEmpty()) {
                current = front;
            } else {
            current = null;
            }
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T element = current.element;
            current = current.next;
            return element;
        }
    }

    private class DescendingIterator implements Iterator<T> {
        private Node current;

        public DescendingIterator() {
            current = rear;;
        }


        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T element = current.element;
            current = current.prev;
            return element;
        }
    }

    private class PowerSetIterator implements Iterator<Set<T>> {

        
        public PowerSetIterator() {
            if (!isEmpty()) {
                Set<T> subset = new LinkedSet<>();
            } else {
                Set<T> subset = new LinkedSet<>();
            }
            
        }
        
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Set<T> next() {        
            return null;
        }
    }

    // Feel free to add as many private methods as you need.

    ////////////////////
    // Nested classes //
    ////////////////////

    //////////////////////////////////////////////
    // DO NOT CHANGE THE NODE CLASS IN ANY WAY. //
    //////////////////////////////////////////////

    /**
     * Defines a node class for a doubly-linked list.
     */
    class Node {
        /** the value stored in this node. */
        T element;
        /** a reference to the node after this node. */
        Node next;
        /** a reference to the node before this node. */
        Node prev;

        /**
         * Instantiate an empty node.
         */
        public Node() {
            element = null;
            next = null;
            prev = null;
        }

        /**
         * Instantiate a node that containts element
         * and with no node before or after it.
         */
        public Node(T e) {
            element = e;
            next = null;
            prev = null;
        }
    }

}
