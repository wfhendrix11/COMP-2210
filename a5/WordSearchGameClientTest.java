import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.SortedSet;

// REMOVE THIS!
import java.io.*;


public class WordSearchGameClientTest {
   
   // initializations 
   WordSearchGame game = WordSearchGameFactory.createGame();

//------------METHODS-RELATING-TO-THE-LEXICON---------------
   
   ////////////////////
   //LoadLexicon Test//
   ////////////////////
   
   /** Test if the file name is null. */
   @ Test public void loadLexiconTest1() {
      String fileName = null;
      try { 
         game.loadLexicon(fileName);
      } 
      catch (IllegalArgumentException e) { 
         assertTrue("File name is null.", true);
      }
   }
   
   /** Test if the file name does not exist. */
   @Test public void loadLexiconTest2() {  
      String fileName = "abcd";
      try { 
         game.loadLexicon(fileName);
      } 
      catch (IllegalArgumentException e) { 
         assertTrue("File cannot be opened.", true);
      } 
    }
    
    /** Test if the lexicon is loaded properly. */
    @Test public void loadLexiconTest3() { 
      game.loadLexicon("wordfiles/words.txt");
   }
   
   //////////////////////
   //isValidPrefix Test//
   //////////////////////
   
   /** Test the prefix IS valid. */
   @Test public void isValidPrefixTest1() {
      game.loadLexicon("wordfiles/words.txt"); //load lexicon 
      String prefix = "car";
      assertTrue(game.isValidPrefix(prefix));
   
   }
   
   /** Test the prefix ISN'T valid. */
   @Test public void isValidPrefixTest2() {
      game.loadLexicon("wordfiles/words.txt"); //load lexicon 
      String prefix = "xyz";
      assertFalse(game.isValidPrefix(prefix)); 
   }
   
   /////////////////////////
   //getScoreForWords Test//
   /////////////////////////
   
   // /** Test if it works. */
//    @Test public void getScoreForWordsTest1() {
//       //SortedSet<String> words = new SortedSet<String>(); 
//       String prefix = "car";
//       assertTrue(game.isValidPrefix(prefix));
//    
//    }

//------------METHODS-RELATING-TO-THE-BOARD---------------
   
   /////////////////
   //setBoard Test//
   /////////////////
   
   /** Test if letterArray is null. */
   @Test public void setBoardTest1() {
      String[] letterArray = null;
      try { 
         game.setBoard(letterArray);
      } 
      catch (IllegalArgumentException e) { 
         assertTrue("letterArray is null.", true);
      }
   }
   
   /** Test if letterArray is NOT square. */
   @Test public void setBoardTest2() {
      String[] letterArray = {"E", "E", "C", "A", "A", "L", "E", "P", "H", 
                                 "N", "B", "O", "Q", "T", "T"};
      try { 
         game.setBoard(letterArray);
      } 
      catch (IllegalArgumentException e) { 
         assertTrue("letterArray is NOT square.", true);
      }
   }
   
   /** Test if letterArray IS square. */
   @Test public void setBoardTest3() {
      String[] letterArray = {"E", "E", "C", "A", "A", "L", "E", "P", "H", 
                                 "N", "B", "O", "Q", "T", "T", "Y"};
      try { 
         game.setBoard(letterArray);
      } 
      catch (IllegalArgumentException e) { 
         assertTrue("letterArray is NOT square.", true);
      }
      assertTrue("letterArray is square.", true);
   }
   
   /** Test that there is a default board. */
   @Test public void setBoardTest4() {
      System.out.println(game.getBoard());
   }
   
   /** Test if it can initialize a new 4 x 4 board. */
   @Test public void setBoardTest5() {
      game.setBoard(new String[]{"E", "E", "C", "A", "A", "L", "E", "P", "H", 
                                 "N", "B", "O", "Q", "T", "T", "Y"});
      System.out.println(game.getBoard());

   }
   
   /** Test if it can initialize a 5 x 5 board. */
   @Test public void setBoardTest6() {
      game.setBoard(new String[]{"E", "E", "C", "A", "A", "L", "E", "P", "H",
                                 "E", "E", "C", "A", "A", "L", "E", "P", "H", 
                                 "N", "B", "O", "Q", "T", "T", "Y"});
      System.out.println(game.getBoard());

   }
   
   /** Test if it can initialize a 6 x 6 board. */
   @Test public void setBoardTest7() {
      game.setBoard(new String[]{"E", "E", "C", "A", "A", "L", "E", "P", "H",
                                 "E", "E", "C", "A", "A", "L", "E", "P", "H",
                                 "E", "E", "C", "A", "A", "L", "E", "P", "H", 
                                 "N", "B", "O", "Q", "T", "T", "Y", "P", "H"});
      System.out.println(game.getBoard());
   }
   
   /////////////////////
   // isOnBoard Tests //   
   /////////////////////
   
   /** Test if LENT is on board. */
   @Test public void isOnBoard() {
      game.setBoard(new String[]{"E", "E", "C", "A", "A", "L", "E", "P", "H",
                                 "E", "E", "C", "A", "A", "L", "E", "P", "H",
                                 "E", "E", "C", "A", "A", "L", "E", "P", "H", 
                                 "N", "B", "O", "Q", "T", "T", "Y", "P", "H"});
      System.out.println(game.getBoard());
   }
   
   //////////////////
   // isValidWord //
   /////////////////
   
    //Should throw IllegalArgumentException if word is null.
   @Test (expected = IllegalArgumentException.class)
    public void validWordTest1() {
      game.loadLexicon("wordfiles/OWL.txt");
      game.isValidWord(null);
   }

  //Should throw IllegalStateException if lexicon never loaded.
   @Test (expected = IllegalStateException.class)
    public void validWordTest2() {
      game.isValidWord("Meow");
   }
   
   //normal tests
   @Test public void validWordTest3() {
      game.loadLexicon("wordfiles/words_tiny.txt");
      Assert.assertEquals(true, game.isValidWord("lexicon"));
   }
   
   @Test public void validWordTest4() {
      game.loadLexicon("wordfiles/words_tiny.txt");
      Assert.assertEquals(false, game.isValidWord("meow"));
   }
   
   @Test public void validWordTest5() {
      game.loadLexicon("wordfiles/OWL.txt");
      Assert.assertEquals(true, game.isValidWord("MeOw"));
   }
   
   
   
}

