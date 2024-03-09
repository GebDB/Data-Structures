import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Your Name (you@auburn.edu)
 */
public class Doublets implements WordLadderGame {

    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
    /////////////////////////////////////////////////////////////////////////////
    // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
    // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
    // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
    // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
    // table with chaining).
    /////////////////////////////////////////////////////////////////////////////
    private HashSet<String> lexicon;
    private boolean lexiconIsLoaded;
    private String next = "", current = "";
    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
    public Doublets(InputStream in) {

        lexiconIsLoaded = false;
        try {
            //////////////////////////////////////
            // INSTANTIATE lexicon OBJECT HERE  //
            //////////////////////////////////////
            lexicon = new HashSet<>();
            Scanner s =
                new Scanner(new BufferedReader(new InputStreamReader(in)));
                
            while (s.hasNext()) {
                String str = s.nextLine().trim();
                String[] words = str.split("\\s+");
                if (str.length() > 0) {
                    String firstWord = words[0];
                    lexicon.add(firstWord);
                   }
                s.nextLine();
            }
            lexiconIsLoaded = true;
            in.close();
        }
        catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }
    
    //////////////////////////////////////////////////////////////
    // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
    //////////////////////////////////////////////////////////////

   /**
    * Returns a minimum-length word ladder from start to end. If multiple
    * minimum-length word ladders exist, no guarantee is made regarding which
    * one is returned. If no word ladder exists, this method returns an empty
    * list.
    *
    * Breadth-first search must be used in all implementing classes.
    *
    * @param  start  the starting word
    * @param  end    the ending word
    * @return        a minimum length word ladder from start to end
    */
   public List<String> getMinLadder(String start, String end) {
     
      //Queue<Node> queue = new LinkedList<>();
      Queue<List<String>> queue = new LinkedList<>(); //
      HashSet<String> visited = new HashSet<>();
      //HashMap<String, String> map = new HashMap<>();
      
      //queue.add(new Node(start, null));
      queue.add(new ArrayList<>(Arrays.asList(start))); //
      //visited.add(start);
      
      while (!queue.isEmpty()) {
         //Node current = queue.remove();
         List<String> ladder = queue.poll(); //
         //String word = current.word;
         String word = ladder.get(ladder.size() - 1); //
         
         if (word.equals(end)) {
           /* List<String> ladder = new ArrayList<>();
            
            while (current != null) {
               ladder.add(0, current.word);
               current = current.parent;
            }*/
            return ladder;
         }
         List<String> adjacents = getAdjacents(word);
         
         for (String adjacent: adjacents) {
            if (!visited.contains(adjacent)) {
               //queue.add(new Node(adjacent, current));
               visited.add(adjacent);
               List<String> ladder2 = new ArrayList<>(ladder); //
               //map.add(adjacent, word);
               queue.add(ladder2); //
            }
          }
      }
      return new ArrayList<>();   
   }
   private List<String> getAdjacents(String word) {
        List<String> adjacents = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char newChar = chars[i];
            // replace each char of word with a-z and check if the resulting word is in lexicon
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != newChar) {
                    chars[i] = c;
                    String adjacent = new String(chars);
                    if (lexicon.contains(adjacent)) {
                        adjacents.add(adjacent);
                    }
                }
            }
            chars[i] = newChar; 
        }
        return adjacents;
    }
/*
   private class Node {
        String word;
        Node parent;

        Node(String word, Node parent) {
            this.word = word;
            this.parent = parent;
        }
    }
   */ 
   
    /**
     * Checks to see if the given sequence of strings is a valid word ladder.
     *
     * @param  sequence the given sequence of strings
     * @return          true if the given sequence is a valid word ladder,
     *                       false otherwise
     */
   public boolean isWordLadder(List<String> sequence) {
    if (sequence.isEmpty()) {
        return false;
    }
    for (int i = 0; i < sequence.size() - 1; i++) {
        String current = sequence.get(i);
        String next = sequence.get(i + 1);
        if (getHammingDistance(current, next) != 1) {
            return false;
        }
    }
    return true;
   }
    /**
     * Returns all the words that have a Hamming distance of one relative to the
     * given word.
     *
     * @param  word the given word
     * @return the neighbors of the given word
     */
   public List<String> getNeighbors(String word) {
    List<String> neighbors = new ArrayList<>();
    for (String w : lexicon) {
        if (getHammingDistance(word, w) == 1) {
            neighbors.add(w);
        }
    }
    return neighbors;
   }
  
    /**
     * Returns the Hamming distance between two strings, str1 and str2. The
     * Hamming distance between two strings of equal length is defined as the
     * number of positions at which the corresponding symbols are different. The
     * Hamming distance is undefined if the strings have different length, and
     * this method returns -1 in that case. See the following link for
     * reference: https://en.wikipedia.org/wiki/Hamming_distance
     *
     * @param  str1 the first string
     * @param  str2 the second string
     * @return      the Hamming distance between str1 and str2 if they are the
     *                  same length, -1 otherwise
     */
   public int getHammingDistance(String str1, String str2) {
    if (str1.length() != str2.length()) {
        return -1;
    }
    int distance = 0;
    for (int i = 0; i < str1.length(); i++) {
        if (str1.charAt(i) != str2.charAt(i)) {
            distance++;
        }
    }
    return distance;
   }
   
   
   public boolean isWord(String str) {
       return lexicon.contains(str);
   }
   public int getWordCount() {
    return lexicon.size();
   }
   
}
