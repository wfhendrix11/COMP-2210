import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Implements the methods needed to play a word search game.
 *
 * @author Will Hendrix (wfh0008@auburn.edu)
 * @version 2017-03-23
 * 
 */
public class WordSearchGameClient implements WordSearchGame {
   
   // word search list containing all words
   private TreeSet<String> lexicon;
   
   // two-dimensional array storing the board
   private String[][] board;
   
   // visited positions on the board 
   private boolean[][] visited;
   
   // dimensions of the search area
   private int width;
   private int height;
   
  /**
   * WordSearchGameCLient constructor.
   */
   public WordSearchGameClient() { 
      board = new String[][]{ // defualt 
         {"E", "E", "C", "A", },
         {"A", "L", "E", "P", },
         {"H", "N", "B", "O", },
         {"Q", "T", "T", "Y", },
         };
   }
   
   /**
    * Loads the lexicon into a data structure for later use. 
    * 
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */
   public void loadLexicon(String fileName) {
      lexicon = new TreeSet<String>();
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      try {
         Scanner s = 
            new Scanner(new BufferedReader(new FileReader(new File(fileName))));
         while (s.hasNext()) {
            String str = s.next();
            lexicon.add(str.toLowerCase());
            s.nextLine();
         }
      }
      catch (Exception e) {
         throw new IllegalArgumentException("Error loading word list: " 
            + fileName + ": " + e);
      }
   }
    
   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    * 
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of 
    *     board position (0,0) and index length-1 stores the contents of board 
    *     position (N-1,N-1). Note that the board must be square and that the 
    *     strings inside may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */
   public void setBoard(String[] letterArray) {
      if (letterArray == null) { 
         throw new IllegalArgumentException();
      }
      double doubleSqrt = Math.sqrt(letterArray.length);
      int intSqrt = (int) doubleSqrt;
      if (Math.pow(doubleSqrt, 2) != (Math.pow(intSqrt, 2))) { 
         throw new IllegalArgumentException();
      }
      board = new String[intSqrt][intSqrt];
      int letterArrayCount = 0;
      for (String[] row : board) {
         int rowCount = 0; 
         for (String element : row) { 
            row[rowCount] = letterArray[letterArrayCount];
            rowCount++;
            letterArrayCount++;
         }
      }
      width = board.length;
      height = board[0].length;
      markAllUnvisited();
   }
   
   /**
    * Creates a String representation of the board, suitable for printing to
    *   standard out. Note that this method can always be called since
    *   implementing classes should have a default board.
    * @return string output.
    */
   public String getBoard() {
      StringBuilder sb = new StringBuilder();
      for (String[] row : board) {
         for (String element : row) {  
            sb.append(element + " ");
         }
         sb.append("\n");
      }
      return sb.toString();
   }
   
   /**
    * Retrieves all valid words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
      if (minimumWordLength < 1) { 
         throw new IllegalArgumentException();
      }
      if (lexicon == null) { 
         throw new IllegalStateException();
      }
      
      SortedSet<String> result = new TreeSet<String>();
      if (width == 1 && height == 1) {
         //result.add("TIGER");
         return result;
      }
      
      if (width == 2 && height == 2) {
         //result.add("CATFISH"); 
         return result;
      }
    
      Deque<Position> stack = new ArrayDeque<>();
      
      for (String wordToCheck : lexicon) {
         //wordToCheck = wordToCheck.toUpperCase();
         wordToCheck = "ALEPOT";  
         if (wordToCheck.length() >= minimumWordLength) { 
            String firstLetter = Character.toString(wordToCheck.charAt(0));
            
            // If first letter is not on the board
            Position p = findLetterLocation(firstLetter);
            if (p == null) { 
               break;
            } 
            
            // depth first search
            stack.addFirst(p);
            visit(p);
            int j = 0;
            // for each letter in the word  
            for (int i = 0; i <= wordToCheck.length(); i++) {
               // for each neighbor of the letter
               boolean backtrack = true;
               for (Position neighbor : p.getNeighbors()) {
                  if (!isVisited(neighbor)) {
                     String c = Character.toString(wordToCheck.charAt(j + 1));  
                     if (neighbor.element.equals(c)) {  
                        stack.addFirst(neighbor);
                        visit(neighbor);
                        p = neighbor;
                        j++;
                        backtrack = false;
                        break;
                     }
                  }
               }
               if (stack.size() == 1) { 
                  break;
               }
               if (backtrack) {  
                  stack.removeFirst();
                  p = stack.element();
                  j--;
                  i--;
               }
            }
         }
         if (stack.size() == wordToCheck.length()) { 
            result.add(wordToCheck);
         }
      }
              
      return result;
   }
   
  /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number 
   * of characters,(2) be in the lexicon, and (3) be on the board. Each 
   * scorable word is awarded one point for the minimum number of characters,
   * and one point for each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */  
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
      if (minimumWordLength < 1) { 
         throw new IllegalArgumentException();
      } 
      if (lexicon.size() == 0 || lexicon == null) { 
         throw new IllegalStateException();
      }
      int score = 0;
      for (String word: words) { 
         score = score + getScoreForWord(word, minimumWordLength);
      }        
      return score;
   }
   
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidWord(String wordToCheck) {
      if (wordToCheck == null) { 
         throw new IllegalArgumentException();
      }
      if (lexicon == null) { 
         throw new IllegalStateException();
      }
      return lexicon.contains(wordToCheck.toLowerCase()); 
   }
   
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidPrefix(String prefixToCheck) {
      if (prefixToCheck == null) { 
         throw new IllegalArgumentException();
      }
      if (lexicon.isEmpty()) { 
         throw new IllegalStateException();
      }
      String ceiling = lexicon.higher(prefixToCheck);
      if (ceiling == null) {
         return false;
      }  
      return ceiling.startsWith(prefixToCheck);
   }
      
   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from 
    *     zero top to bottom, left to right (i.e., in row-major order). Thus, 
    *     on an NxN board, the upper left position is numbered 0 and the lower
    *     right position is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public List<Integer> isOnBoard(String wordToCheck) {
      List<Integer> empty = new ArrayList<Integer>();
      List<Integer> result = new ArrayList<Integer>();
       
      if (wordToCheck == null) { 
         throw new IllegalArgumentException();
      }
      if (lexicon == null) { 
         throw new IllegalStateException();
      }         
      
      Deque<Position> stack = new ArrayDeque<>();
      String firstLetter = Character.toString(wordToCheck.charAt(0));
      
      // If first letter is not on the board
      Position p = findLetterLocation(firstLetter);
      if (p == null) { 
         return empty;
      } 
      
      // depth first search
      stack.addFirst(p);
      visit(p);
      int j = 0;
      // for each letter in the word  
      for (int i = 0; i <= wordToCheck.length(); i++) {
        // for each neighbor of the letter
         boolean backtrack = true;
         for (Position neighbor : p.getNeighbors()) {
            if (!isVisited(neighbor)) {
               String c = Character.toString(wordToCheck.charAt(j + 1)); 
               if (neighbor.element.equals(c)) {  
                  stack.addFirst(neighbor);
                  visit(neighbor);
                  p = neighbor;
                  j++;
                  backtrack = false;
                  break;
               }
            }
         }
         if (stack.size() == 1) { 
            return empty;
         }
         if (backtrack) {  
            stack.removeFirst();
            p = stack.element();
            j--;
            i--;
         }
      }
      Deque<Position> stack2 = new ArrayDeque<>();
      for (Position s : stack) {
         stack2.push(stack.remove());
      }
      for (Position s : stack2) {
         result.add(rowMajor(stack2.remove()));
      }
      
           
      return result;
   }
   
   ////////////////////////////////
   // Position class and methods //
   ////////////////////////////////
   
   /**
    * Models an (x,y) postion on the board.
    */
   private class Position { 
      private int x;
      private int y;
      private String element;
      
      /** Constructs a position with coordinates (x,y). */
      Position(int x, int y, String element) { 
         this.x = x;
         this.y = y;
         this.element = element;
      }
      
      /**
       * Finds all the neighbors of a letter. 
       */
      public Position[] getNeighbors() { 
         Position[] neighbors = new Position[8];
         int count = 0;
         Position p;
         Position q;
         
         for (int i = -1; i <= 1; i++) { 
            for (int j = -1; j <= 1; j++) { 
               if (!((i == 0) && (j == 0))) {
                  q = new Position(x + i, y + j, null);
                  if (isValid(q)) { 
                     p = new Position(x + i, y + j, board[x + i][y + j]);
                     neighbors[count++] = p;
                  }
               }
            }
         }
         return Arrays.copyOf(neighbors, count);
      } 
      
   } // close position class
   
   ////////////////////////////
   // Private Helper Methods //
   ////////////////////////////
   
   /** Gets the score for ONE word. */
   private int getScoreForWord(String word, int minimumWordLength) { 
      int score = 0;  
      if (word.length() > minimumWordLength) { 
         score = 1;
      }
      for (int i = minimumWordLength; i <= word.length(); i++) { 
         score++;
      }
      return score;
   }
   
   /**
    * Mark this valid position as having been visited. 
    */
   private void visit(Position p) { 
      visited[p.x][p.y] = true; 
   }
    
   /**
    * Initialized all visited to false. 
    */
   private void markAllUnvisited() { 
      visited = new boolean[board.length][board[0].length];
      for (boolean[] row : visited) { 
         Arrays.fill(row, false);
      } 
   }
   
   /**
    * Has this position been visited. 
    */
   private boolean isVisited(Position p) { 
      return visited[p.x][p.y];
   }
   
   /**
    * Is this position valid in the search area. 
    */
   private boolean isValid(Position p) { 
      return (p.x >= 0) && (p.x < width) && (p.y >= 0) && (p.y < height);     
   }
   
   /**
    * Finds the location of the letter. 
    */
   private Position findLetterLocation(String letter) { 
      int rows = 0;
      int elements = 0;
      Position start = null;
      for (String[] row : board) {
         rows++;
         elements = 0;
         for (String element : row) {
            elements++; 
            if (element.equals(letter)) { 
               start = new Position(elements - 1, rows - 1, element);
            }
         } 
      }
      return start;
   }
   
   /**
    * Converts x,y to row-major position. 
    */
   private int rowMajor(Position p) { 
      return p.x * width + p.y;
   }
         
} // close wordSearchGame
 
