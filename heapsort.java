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
  private static void testWithSmallSample() {
    System.out.println("\n---TESTING WITH SMALL SAMPLE ---");
    String[] sampleWords= {
      "the","quick", "brown" ,"fox", "jumps" ,"over",
      "the", "lazy" , "dog" , "hello", "world" ," algorithm",
      "heap" , "sort", "binary" , "tree" , "arrary" , "list" 
    };
    Node[] nodes = new Node [sampleWords.length];
    for (int i =0; i<sampleWords.length ;i++) {
      nodes [i] = new Node ( sampleWords[i]);
    }
    System.out.println("original array ( " + nodes.length + "words):");
    printArray(nodes);
    Node [] bottomUpCopy = nodes .clone();
    long start = System.nanoTime();
    heapsortBottomUp(bottomUpCopy);
    long end = System .nanoTime();
    System.out.println ( "\nBottom-up heapsort rresult :");
    printArray(bottomUpCopy);
    System.out.println(" Time :" + ( end - start ) / 1000.0 + " µs");
    Node[] topDownCopy = nodes.clone();
    start = System.nanoTime();
    heapsortTopDown(
    
    
    
      
  
    
    
    
  
