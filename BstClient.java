import java.util.Iterator;
/**
 * BstClient.java. Provides sample client code for the BinarySearchTree class.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 */

public class BstClient {

    /** Drives execution. */
    public static void main(String[] args) {
        Integer[] values = {7,1,10,6,9,8,4,2,5,3};
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (Integer value : values) {
            bst.add(value);
        }
        System.out.println(bst);
        System.out.println(bst.size());

        bst = new BinarySearchTree<>();
        for (int i = 0; i < 10; i++) {
            bst.put(99);
        }
        System.out.println(bst);
        System.out.println(bst.size());
    }
}