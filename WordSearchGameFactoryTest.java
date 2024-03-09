import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.SortedSet;

public class WordSearchGameFactoryTest{

    private WordSearchGame game;

    @Before
    public void setUp() {
        game = WordSearchGameFactory.createGame();
        game.loadLexicon("words_medium.txt");
        
        // Adjust board dimensions and content for your large board
        String[] board = 
            {"O","Y","D","D","T","P","N","R","A","H","E","L","C","S","B","P","S","U","B",
            "G","U","P","Y","H","R","R","X","R","E","F","H","D","H","T","K","X","K","O",
            "Z","F","W","Y","H","Y","T","C","H","M","V","P","R","T","A","K","N","E","S","I",
            "B","T","M","V","Y","Q","E","U","O","E","F","A","K","J","C","W","I","K","I","U","K",
            "T","P","O","F","E","G","Z","T","X","O","Z","T","H","K","B","M","G","D","P","P","P",
            "G","U","E","S","C","J","C","B","Q","F","T","R","I","P","N","I","E","W","P","K","H",
            "K","G","B","B","L","Y","J","P","J","E","O","N","Q","V","N","B","S","H","R","N","Z",
            "R","G","A","E","W","P","L","L","Z","R","G","I","E","T","U","N","R","L","I","K","T",
            "J","K","J","F","C","I","T","M","R","D","T","R","E","G","L","J","G","I","K","H","L","C",
            "V","P","P","D","S","Q","E","W","O","C","R","L","V","L","P","T","A","T","N","O","R","M",
            "W","K","O","D","O","U","O","V","F","M","H","V","V","S","I","X","Z","L","O","T","Z","L","B",
            "R","G","F","Q","P","A","Y","P","D","L","B","K","S","N","C","H","O","P","Y","K","H","C","R",
            "R","I","C","S","B","J","X","R","F","I","Y","R","H","B","Z","I","P","C","K","I","N","O","E",
            "C","C","U","C","P","I","J","R","E","Y","E","Z","U","R","R","M","F","S","M","R","N","J","I","B",
            "T","Q","O","C","V","R","O","T","X","H","C","R","W","S","A","V","T","N","U","I","O","W","X","C",
            "O","R","X","Q","A","S","A","S","S","E","M","B","L","Y","O","Z","F","P","L","S","C","I","T","L",
            "U","M","O","N","I","T","O","R","J","W","I","N","L","L","L","E","L","J","R","R","E","M","M","O",
            "B","D","X","I","J","D","S","R","L","C","H","S","H","Y","U","L","P","M","O","U","S","E","C","B",
            "I","I","U","I"};            // Add the rest of the elements here

        game.setBoard(board);
    }

    @Test
    public void testGetAllScorableWords_5() {
        SortedSet<String> scorableWords = game.getAllScorableWords(10); // Adjust the minimum word length as needed
        Assert.assertNotNull(scorableWords); // Ensure the returned set is not null

        // Add assertions for the correctness and efficiency testing on the large board
        // For example:
        // Assert.assertTrue(scorableWords.contains("SOME_EXPECTED_WORD"));
        // Assert.assertTrue(scorableWords.contains("ANOTHER_EXPECTED_WORD"));

        // Ensure the set doesn't contain any unexpected words
        // Assert.assertEquals(EXPECTED_SIZE, scorableWords.size());
    }

   // @Test
    public void testIsOnBoard_WordExists() {
        //List<Integer> result = game.isOnBoard("CHURCHGOER");
       // Assert.assertEquals(0, result.size());
        // Assert that "APPLE" is found in some orientation
        // The expected positions might vary depending on the orientation
        // but the positions will be continuous (e.g., (2,1), (2,2), (2,3), (2,4), (2,5))
        // Check for any continuous sequence of 5 positions horizontally, vertically, or diagonally
    }

  //  @Test
    public void testIsOnBoard_WordDoesNotExist() {
       // List<Integer> result = game.isOnBoard("STACK");
      //  Assert.assertEquals(0, result.size());
        // Assert that "STACK" is not found on the board
    }

  // @Test
    public void testIsOnBoard_NullWord() {
       // try {
         //   game.isOnBoard(null);
        //    Assert.fail("Expected IllegalArgumentException for null word.");
      //  } catch (IllegalArgumentException e) {
            // Expected
      //  }
    }

  //  @Test
    public void testIsOnBoard_EmptyWord() {
      //  List<Integer> result = game.isOnBoard("");
      //  Assert.assertEquals(0, result.size());
        // Assert that an empty word returns an empty list of positions
    }

   // @Test
    public void testIsOnBoard_LexiconNotLoaded() {
        //WordSearchGame gameWithoutLexicon = WordSearchGameFactory.createGame();
        //try {
         //   gameWithoutLexicon.isOnBoard("TEST");
          //  Assert.fail("Expected IllegalStateException for lexicon not loaded.");
       // } catch (IllegalStateException e) {
            // Expected
        }
    }
