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
    heapsortTopDown(topDownCopy);
    end = System.nanoTime();
    System.out.println("\nTop-down heapsort result:");
        printArray(topDownCopy);
        System.out.println("Time: " + (end - start) / 1000.0 + " µs");
    }
  private static void runTimingComparison(ArrayList<String> wordList) {
        System.out.println("\n--- TIMING COMPARISON ON FULL DATASET ---");

        int  Repetitions = 10;
        double bottomUpTotalTime = 0;
        double topDownTotalTime = 0;
    Node[] originalNodes = new Node[wordList.size()];
        for (int i = 0; i < wordList.size(); i++) {
            originalNodes[i] = new Node(wordList.get(i));
        }
    System.out.println("Running " + repetitions + " repetitions on " + originalNodes.length + " words...");
    for (int rep = 0; rep < repetitions; rep++) {
            // Bottom-up heapsort
            Node[] bottomUpCopy = originalNodes.clone();
            long start = System.nanoTime();
            heapsortBottomUp(bottomUpCopy);
            long end = System.nanoTime();
            double time = (end - start) / 1_000_000.0; // Convert to milliseconds
            bottomUpTotalTime += time;

            // Top-down heapsort
            Node[] topDownCopy = originalNodes.clone();
            start = System.nanoTime();
            heapsortTopDown(topDownCopy);
            end = System.nanoTime();
            time = (end - start) / 1_000_000.0;
            topDownTotalTime += time;

            if ((rep + 1) % 2 == 0) {
                System.out.println("  Completed " + (rep + 1) + " repetitions...");
            }
        }
        double bottomUpAvg =bottomUpTotalTime / repetitions;
  
        double topDownAvg = topDownTotalTime / repetitions;
        System.out.println("\n=== RESULTS ===");
        System.out.println("Dataset size: " + originalNodes.length + " words");
        System.out.println("Repetitions: " + repetitions);
        System.out.println("\nBottom-Up Heapsort (O(n) construction):");
        System.out.println("  Average time: " + String.format("%.4f", bottomUpAvg) + " ms");
        System.out.println("  Total time: " + String.format("%.4f", bottomUpTotalTime) + " ms");
        System.out.println("\nTop-Down Heapsort (O(n log n) construction):");
        System.out.println("  Average time: " + String.format("%.4f", topDownAvg) + " ms");
        System.out.println("  Total time: " + String.format("%.4f", topDownTotalTime) + " ms");
        System.out.println("\nSpeedup factor: " +
                String.format("%.2f", topDownAvg / bottomUpAvg) + "x");
    }
  private static ArrayList<String> readWordsFromFile(String filename) {
        ArrayList<String> words = new ArrayList<>();
        HashSet<String> uniqueWords = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int lineCount = 0;
            System.out.println("Reading words from " + filename + "...");
            while ((line = reader.readLine()) != null) {
                lineCount++;
                String[] rawWords = line.split("\\s+");
                for (String rawWord : rawWords) {
                    String cleaned = cleanWord(rawWord);
                    if (!cleaned.isEmpty()) {
                        words.add(cleaned);
                        uniqueWords.add(cleaned);
                    }
                }
                if (lineCount % 1000 == 0) {
                    System.out.println("  Processed " + lineCount + " lines...");
                }
            }
            reader.close();
            System.out.println("Done! Read " + words.size() + " words (" +
                    uniqueWords.size() + " unique)");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return words;
    }
  private static String cleanWord(String word) {
    String cleaned = word.replaceAll("^[^a-zA-Z0-9']+|[^a-zA-Z0-9']+$", "");
        cleaned = cleaned.toLowerCase();
    if (cleaned.length() < 2) return "";
    return cleaned ;
  }
  public static void heapsortBottomUp(Node[]array){
    buildHeapBottomUp(array);
    sortFromHeap(array);
  }
  private static void buildHeapBottomUp(Node[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(array, n, i);
        }
    }
  public static void heapsortTopDown( Node [] array ){
    buildHeapTopDown(array);
    sortFromHeap(array);
  }
   private static void buildHeapTopDown(Node[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            int current = i;
            int parent = (current - 1) / 2;
            while (current > 0 &&
                    array[current].compareTo(array[parent]) > 0) {
                swap(array, current, parent);
                current = parent;
                parent = (current - 1) / 2;
            }
        }
    }
    private static void sortFromHeap(Node[] array) {
        int n = array.length;
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            heapifyDown(array, i, 0);
        }
    }
    private static void heapifyDown(Node[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
  
  
  
    
    

    
  
  
    
    
    
    
    
    
  
  
    
    
    
    
      
  
    
    
    
  
