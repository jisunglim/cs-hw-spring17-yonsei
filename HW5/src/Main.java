import sp.SP;

import java.io.IOException;

/**
 *
 */
public class Main {

  private static final String INPUT_FILE_NAME = "/Users/jaylim/Developers/java/hw_assignment/HW5/rsc/input.txt";
  private static final String OUTPUT_FILE_NAME = "/Users/jaylim/Developers/java/hw_assignment/HW5/out/output.txt";

  public static void main(String[] args) throws Exception {

    IOManager<SP> io = new IOManager<SP>(INPUT_FILE_NAME, OUTPUT_FILE_NAME) {

      /**
       * Reading text file char by char, count the number of occurrence for each character.
       * The frequency for each character is stored in the array using the character itself as an index of array.
       *
       * @return The character occurrence frequency table.
       */
      @Override
      public SP setup(SP sp) throws IOException {
        String[] tokens;

        // set F
        tokens = reader().readLine().trim().split(" ");
        for (int i = 0; i < tokens.length; i++) {
          sp.F[i] = Integer.parseInt( tokens[i].trim() );
        }

        // set edges
        while (true) {
          tokens = reader().readLine().split(" ");
          final int s = Integer.parseInt(tokens[0].trim()); // Start
          final int e = Integer.parseInt(tokens[1].trim()); // End
          final int d = Integer.parseInt(tokens[2].trim()); // Distance

          if (s == -1 && e == -1 && d == -1) break;
          else sp.A[s][e] = d;
        }

        return sp;  // Return the frequency table
      }
    };

    String firstLine; // First line of each case

    // For each case, read first line. Check nullity and validity.
    while ((firstLine = io.reader().readLine()) != null) {

      // Setup variables
      String[] strs = firstLine.trim().split(" ");
      final int N = Integer.parseInt(strs[0].trim()); // Num
      final int S = Integer.parseInt(strs[1].trim()); // Src
      final int D = Integer.parseInt(strs[2].trim()); // Dst

      // flight distance

      final SP sp = new SP(N, S, D);
      io.setup(sp);

      // Parse next line into a Goods stream
      sp.Astar();
      sp.out().printoutResult();
      sp.out().result(io.writer());

      sp.Dijkstra();
      sp.out().printoutResult();
      sp.out().result(io.writer());

    }

    // Clear resource
    io.clearResource();
  }
}
