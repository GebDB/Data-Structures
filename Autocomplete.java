import java.util.Arrays;


/**
 * Autocomplete.
 */
public class Autocomplete {

   private Term[] terms;

	/**
	 * Initializes a data structure from the given array of terms.
	 * This method throws a NullPointerException if terms is null.
	 */
   public Autocomplete(Term[] terms) {
   
      if (terms == null) {
         throw new NullPointerException("terms is null");
      }
      this.terms = terms;
   }

	/** 
	 * Returns all terms that start with the given prefix, in descending order of weight. 
	 * This method throws a NullPointerException if prefix is null.
	 */
   public Term[] allMatches(String prefix) {
      Term[] tempArray = new Term[terms.length];
      int prefixLength = prefix.length();
      int i = 0;
      for (Term term : terms) {
         if (term.getQuery().startsWith(prefix)) {
            tempArray[i] = term;
            i++;
         }
      }
      Term[] termArray = Arrays.copyOf(tempArray, i);
      Arrays.sort(termArray, Term.byDescendingWeightOrder());
      return termArray;
   }

}