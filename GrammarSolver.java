import java.util.*;
/** This class stores rules and makes random occurrence of the grammar file
  * @author Cat Mai, CS 145, Winter 2018, 2pm
  * @version 2/9/18
  */
public class GrammarSolver {

   private static Map<String,String[]> rulesMap;
   private static Random rand;
   
   /** This constructor initializes a new grammar solver over the given BNF grammar rules,
     * where each rule corresponds to one line of text
     * @param rules a List of String containing rules
     * @throws IllegalArgumentException if rules is empty
     * @throws IllegalArgumentException if there are repeated rules
     */
   public GrammarSolver(List<String> rules) {
      
      if (rules.isEmpty())
         throw new IllegalArgumentException("empty rules");
      
      rand = new Random();
      rulesMap = new TreeMap<String,String[]>();
      
      for (String i: rules) {
         
         String[] temp = i.split("::=");
         
         if (rulesMap.containsKey(temp[0]))
            throw new IllegalArgumentException("repeated rules");
         
         String[] term = temp[1].trim().split("[|]");
         
         for (int j = 0; j < term.length; j++)
            term[j] = term[j].trim();
         
         rulesMap.put(temp[0].trim(),term);
         
      }  
   }
   /** This method should return true if the given symbol is a non-terminal in the grammar and false otherwise.
     * @param symbol the symbol being checked
     * @return a boolean value of whether the symbol is a non-terminal
     * @throws IllegalArgumentException if symbol is empty
     */
   public boolean contains(String symbol) {
      
      if (symbol.length() < 1)
         throw new IllegalArgumentException("invalid symbol");
      
      return rulesMap.containsKey(symbol);  
   }
   /** This method should return all non-terminal symbols of the grammar as a sorted set of strings. 
     * @return a sorted set of non-terminal symbols
     */
   public Set<String> getSymbols() {
      return rulesMap.keySet();
   }
  
   /** This method should use the grammar to generate a random occurrence of the given symbol and
     * should return it as a String
     * @param symbol a symbol
     * @return a sentence or a random occurence of symbol
     * @throws IllegalArgumentException if symbol is empty
     */
   public String generate(String symbol) {
      
      if (symbol.length() < 1)
         throw new IllegalArgumentException("invalid symbol");
         
      String outp = "";
      String[] terminal = rulesMap.get(symbol); //pull out the rules associated with the symbol
      String rules = terminal[rand.nextInt(terminal.length)]; //pick randomly a symbol within the array
      String[] ruleArray = rules.split("[ \t]+");
      //split the "rules" into smaller part
      //it can be a non-terminal, which will enter the recursion, or a terminal, which will get added to the output

      for(String i : ruleArray) {
            
         if (contains(i)) { //if it is a nonterminal
            outp += generate(i);
         } 
         else { //if it is a terminal
            outp += i + " "; //only add space to the sentence once the recursion hits the terminal case ie base case
         }
      }     
      return outp;
      
   }
}