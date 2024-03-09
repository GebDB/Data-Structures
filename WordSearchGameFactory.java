import java.util.TreeSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Stack;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;

/**
 * Provides a factory method for creating word search games. 
 */
public class WordSearchGameFactory {

    /**
     * Returns an instance of a class that implements the WordSearchGame
     * interface.
     */
    public static WordSearchGame createGame() {
        // You must return an instance of your solution class here.
        return new WordSearchGameImplementation();
    }
    
    public static class WordSearchGameImplementation implements WordSearchGame {
        private TreeSet<String> lexicon;
        private boolean lexiconIsLoaded;
        private String[][] board;
        private boolean[][] visited;
        private SortedSet<String> scorableWords;
        private List<Integer> positions;
        
        public WordSearchGameImplementation() {
            lexicon = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
            lexiconIsLoaded = false;
            
        }
        
        @Override
        public void loadLexicon(String fileName) {
            if (fileName == null) {
                throw new IllegalArgumentException("File name is null.");
            }
            try {
                File file = new File(fileName);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    String[] words = line.split("\\s+");
                   if (words.length > 0) {
                    String firstWord = words[0];
                    lexicon.add(firstWord);
                   }
                }
                lexiconIsLoaded = true;
            }
            catch (FileNotFoundException e) {
                throw new IllegalArgumentException("File not found.");
            }
        }


        @Override
        public void setBoard(String[] letterArray) {
           if (letterArray == null || Math.sqrt((double)letterArray.length) % 1 != 0) {
              throw new IllegalArgumentException("letterArray is either null or not square");
           }
           int rowLength = (int) Math.sqrt(letterArray.length);
           int j = 0;
           int k = 0;
           board = new String[rowLength][rowLength];
           visited = new boolean[rowLength][rowLength];
           
           for (int i = 0; i < letterArray.length; i++) {   
              int row = i / rowLength;
              int col = i % rowLength;
              board[row][col] = letterArray[i];
           }
        }

        @Override
        public String getBoard() {
            String output = "";
            for (int i = 0; i < board.length; i++) {

               for (int j = 0; j < board[i].length; j++) {

                  String element = board[i][j];

                  output = output + element;
               }
               output = output + "\n";
            }
            return output;
        }

        @Override
        public SortedSet<String> getAllScorableWords(int minimumWordLength) {
            if (minimumWordLength < 1) {
               throw new IllegalArgumentException("Minimum word length must be at least 1.");
            }

            if (!lexiconIsLoaded) {
               throw new IllegalStateException("Lexicon not loaded.");
            }
            scorableWords = new TreeSet<>();
            int length = board.length;
            for (int row = 0; row < length; row++) {
               for (int col = 0; col < length; col++) {
                   visited = new boolean[length][length];
                   depthSearchForWords(row, col, "", board, visited, minimumWordLength);
                   
                  }
             }
            return scorableWords;
         }
         
         private void depthSearchForWords(int x, int y, String temp, String[][] board,
                                  boolean[][] visited, int minLength) {
           
             if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
               return;
             }
             
            if (visited[x][y]) {
                return;
            }
            
            temp += board[x][y];

            if (!isValidPrefix(temp)) {
               return;
            }
            

 
             if (isValidWord(temp) && temp.length() >= minLength) {
                scorableWords.add(temp);
            }

            visited[x][y] = true;

             depthSearchForWords(x + 1, y, temp, board, visited, minLength);
             depthSearchForWords(x - 1, y, temp, board, visited, minLength);
             depthSearchForWords(x, y + 1, temp, board, visited, minLength);
             depthSearchForWords(x, y - 1, temp, board, visited, minLength);
             
             depthSearchForWords(x + 1, y + 1, temp, board, visited, minLength); // Move down-right
             depthSearchForWords(x - 1, y + 1, temp, board, visited, minLength); // Move up-right
             depthSearchForWords(x + 1, y - 1, temp, board, visited, minLength); // Move down-left
             depthSearchForWords(x - 1, y - 1, temp, board, visited, minLength); // Move up-left
            
             visited[x][y] = false;

    
   }

       @Override
       public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
           if (minimumWordLength < 1) {
              throw new IllegalArgumentException("Minimum word length must be at least 1.");
           }
           if (!lexiconIsLoaded) {
               throw new IllegalStateException("Lexicon has not been loaded.");
           }
           
           int score = 0;
           
           for (String word : words) {
              if (word.length() >= minimumWordLength && this.isValidWord(word)) {
                score = score + minimumWordLength + (word.length() - minimumWordLength);
              }
           }
           return score;
       }
       @Override
       public boolean isValidWord(String wordToCheck) {
           if (!lexiconIsLoaded) {
                throw new IllegalStateException("Lexicon not loaded.");
           }
           if (wordToCheck == null) {
               throw new IllegalArgumentException("Word to check is null.");
           }
           return lexicon.contains(wordToCheck);
        }
        
        @Override
        public boolean isValidPrefix(String prefixToCheck) {
           if (!lexiconIsLoaded) {
                throw new IllegalStateException("Lexicon not loaded.");
           }
           if (prefixToCheck == null) {
               throw new IllegalArgumentException("Word to check is null.");
           }
           
           String closestToPrefix = lexicon.ceiling(prefixToCheck);
           return closestToPrefix != null && closestToPrefix.toLowerCase().startsWith(prefixToCheck.toLowerCase());        
           }

        @Override
        public List<Integer> isOnBoard(String wordToCheck) {
            if (wordToCheck == null) {
               throw new IllegalArgumentException("Word to check is null.");
            }
            if (!lexiconIsLoaded) {
               throw new IllegalStateException("Lexicon not loaded.");
            }
            positions = new ArrayList<>();
            int length = board.length;
            for (int row = 0; row < length; row++) {
               for(int col = 0; col < length; col++) {

                  visited = new boolean[length][length];
                  if (depthSearch(row, col, wordToCheck, "", board, visited)) {
                     return positions;
                  }
                }
             }
             return positions;
                  
        }
       

      private boolean depthSearch(int x, int y, String word, String temp, String[][] board,
                            boolean[][] visited) {
          
         if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
             return false;
          }
         
          if (visited[x][y]) {
             return false;
           }
         
          temp += board[x][y];
          if (temp.equals(word)) {
           positions.add((x * board.length) + y);
           visited[x][y] = true;
           return true;
          }
          if (!word.startsWith(temp)) {

             return false;
          }

        visited[x][y] = true;
        positions.add((x * board.length) + y);


        boolean found = 
             depthSearch(x + 1, y, word, temp, board, visited) ||
             depthSearch(x - 1, y, word, temp, board, visited) ||
             depthSearch(x, y + 1, word, temp, board, visited) ||
             depthSearch(x, y - 1, word, temp, board, visited) ||
             depthSearch(x + 1, y + 1, word, temp, board, visited) || // Move down-right
             depthSearch(x - 1, y + 1, word, temp, board, visited) || // Move up-right
             depthSearch(x + 1, y - 1, word, temp, board, visited) || // Move down-left
             depthSearch(x - 1, y - 1, word, temp, board, visited); // Move up-left

            
        if (!found) {
            positions.remove(positions.size() - 1);
        }
        visited[x][y] = false;
        return found;
      }
}
}
