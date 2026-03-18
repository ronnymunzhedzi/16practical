import java.io.*;
import java.util.*;
public class heapsort {
  static class Node implements Comparable <Node> {
    String word;
    Node ( String word) {
      this.word = word ;
    }
    @Override
    public int compareTo(Node other) {
      return this .word.compareTo(other.word);
    }
    @Override
    public String toString () {
      return word;
    }
  }
  public static void main (Striing [] args) {
    System.out.println ("\n===HEAPSORT PERFORMANCE COMPARISON ===");
    System.out.println("Comparing Bottom up vs Top Down Heap construction\n");
    ArrayList <String> wordList = readWordsFromFile("Joyce1922_ulysses(1).txt");
    if (wordList.isEmpty()) {
      system.out.println("No words found.Exiting.");
      return;
    }
    System.out.println ("Total words read: " +wordList.size());
    System.out.println("Unique words : " +wordList.size());
    testWithSmallSample();
    runTimingComparison(WordList);
  }
  
    
    
    
  
