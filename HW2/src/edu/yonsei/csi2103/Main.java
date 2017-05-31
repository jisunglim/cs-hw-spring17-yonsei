package edu.yonsei.csi2103;

import edu.yonsei.csi2103.algorithm.Algorithm;
import edu.yonsei.csi2103.algorithm.Recursive;
import edu.yonsei.csi2103.algorithm.StackBased;
import edu.yonsei.csi2103.utils.CacheUtils;
import edu.yonsei.csi2103.utils.IOManager;

/**
 * Created by jaylim on 24/03/2017.
 */
public class Main {

  /* I/O File path (OS's file system dependent) */
  private static final String INPUT_FILE_NAME = "/Users/jaylim/Developers/java/hw_assignment/HW2/rsc/input.txt";
  private static final String OUTPUT_FILE_NAME = "/Users/jaylim/Developers/java/hw_assignment/HW2/out/output.txt";


  public static void main(String[] args) throws Exception {
    // Class for managing IO buffer and printer
    IOManager io = new IOManager(INPUT_FILE_NAME, OUTPUT_FILE_NAME);

    int nCase = 0;    // Case number
    String firstLine; // First line of each case

    // For each case, read first line. If non-null, also validate format.
    while ((firstLine = io.buffer().readLine()) != null && firstLine.matches("[1-9][0-9]*")) {
      nCase++; // case number incr. by 1.

      // Initialize input
      final int N = Integer.parseInt(firstLine);
      final int[][][] A = io.readInput(N);

      /*
       * 1. Reduce problem by caching the obviously shortest path
       */
      final int[][][] CACHE = CacheUtils.genCache(N);
      CacheUtils.minCaching(N, A, CACHE);

      /*
       *  2. Algorithm using stack
       *  It has two algorithm, one is based on Stack data structure, and the other one is recursive function.
       */
      Algorithm algorithm = new StackBased();
      // Algorithm algorithm = new Recursive();

      int solution = algorithm.solution(N, A, CACHE);


      // print out result
      io.printer().printf("#%d %d\n", nCase, solution);
    }

    // release IO resources
    io.clearResource();
  }
}
