import heap.Goods;
import heap.MedianHeap;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * Created by jaylim on 03/05/2017.
 */
public class Main {

  private static final String INPUT_FILE_NAME = "/Users/jaylim/Developers/java/hw_assignment/HW4/rsc/input.txt";
  private static final String OUTPUT_FILE_NAME = "/Users/jaylim/Developers/java/hw_assignment/HW4/out/output.txt";

  public static void main(String[] args) throws Exception {

    IOManager<Stream<Goods>> io = new IOManager<Stream<Goods>>(INPUT_FILE_NAME, OUTPUT_FILE_NAME) {

      /**
       * Reading text file char by char, count the number of occurrence for each character.
       * The frequency for each character is stored in the array using the character itself as an index of array.
       *
       * @return The character occurrence frequency table.
       */
      @Override
      public Stream<Goods> readInput(int n) throws IOException {

        // Goods array to be filled with parsed items
        Goods[] goods = new Goods[n];
        int p = 0;  //  index for goods array

        // Parse input lines into tokens.
        String[] tokens = reader().readLine().split(" ");

        for (int i = 0; i < tokens.length; i++) {
          String token = tokens[i].trim(); // Check i-th token whether it is "i" or "d"

          if (token.equals("i")) {
            // If "i", parse following two tokens additionally.
            int key = Integer.parseInt(tokens[i+1].trim());
            String val = tokens[i+2].trim();
            // Add goods instance into the array.
            goods[p++] = new Goods(key, val);
            // Take additional parsing into account.
            i += 2;

          } else if (token.equals("d")) {
            // If "d", put null object, for delete sign.
            goods[p++] = null;
          }
        }

        // Return the array as a stream.
        return Arrays.stream(goods);  // Return the frequency table
      }
    };

    int nCase = 0;    // Case number
    String firstLine; // First line of each case

    // For each case, read first line. If non-null, also validate format.
    while ((firstLine = io.reader().readLine()) != null && firstLine.matches("[1-9][0-9]*")) {

      // Print out case number
      io.writer().write(String.format("#%d ", ++nCase));

      // Create median heap
      final MedianHeap<Goods> mHeap = new MedianHeap<>(10000, Goods.class, 1000);

      // Parse first line of each test case, which denotes the number of input instructions
      final int N = Integer.parseInt(firstLine);

      // Parse next line into a Goods stream
      io.readInput(N).forEach(g -> {

        if (g != null) { // if non-null object is encountered, enqueue the object into the Median Heap.
          mHeap.enqueue(g);
        } else {  // if null object is encountered, dequeue the object from the Median Heap.
          try {
            io.writer().write(String.format("%s ", mHeap.dequeue().name));
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      });

      // crlf
      io.writer().write("\n");
    }

    // Clear resource
    io.clearResource();
  }
}
