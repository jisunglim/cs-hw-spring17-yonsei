package edu.yonsei.csi2103;

import edu.yonsei.csi2103.bintree.BinTree;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CSI2103-01 Data structures
 * Programming Homework #1
 *
 * Due Date: March 14th (Tuesday) by 5:00 PM
 *
 * Created by Jisung Lim on 11/03/2017.
 */
public class Main {

  /* I/O File path (OS's file system dependent) */
  private static final String INPUT_FILE_NAME = "/Users/jaylim/Developers/java/hw_assignment/HW1/rsc/input.txt";
  private static final String OUTPUT_FILE_NAME = "/Users/jaylim/Developers/java/hw_assignment/HW1/out/output.txt";

  public static void main(String[] args) throws Exception {

    // File input
    final InputStream inputStream = new FileInputStream(INPUT_FILE_NAME);
    final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

    // File output
    final OutputStream outputStream = new FileOutputStream(OUTPUT_FILE_NAME);
    final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
    final PrintWriter printWriter = new PrintWriter(outputStreamWriter);

    // What we will use.
    int nCase = 0;                                // Case number
    String firstLine;                             // First line of each case
    BinTree<Integer> binTree = new BinTree<>();   // Binary tree for sorting

    // Read first line of each test case. if "End Of Line" or "Illegal Form", escape loop.
    while ((firstLine = bufferedReader.readLine()) != null && firstLine.matches("[1-9][0-9]*")) {

      int N;    // The # of rows (or columns) of succeeding matrix.
      int[] A;  // the 1-D array, which represents N by N matrix.

      // 1. Set the case number. (increment by 1 for each successive case)
      nCase++;

      // 2. get integer from first line, which represents the # of rows of matrix.
      N = Integer.parseInt(firstLine);

      // 3. Read n by n matrix.
      A = readMatrix(bufferedReader, N);

      // 4. Initialize the binary tree to store the results.
      binTree.clear();

      // 5. Count all blocks of empty slots and store it in non-decreasing order.
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          int sol = count(N, A, i, j);
          if (sol != 0) binTree.insert(sol);
        }
      }

      // Traverse the binary tree and print out each element.
      printWriter.printf("#%d %d ", nCase, binTree.length());
      binTree.inorder(el -> printWriter.printf("%d ", el));
      printWriter.println();
    }

    // disconnect input source
    bufferedReader.close();
    inputStreamReader.close();
    inputStream.close();

    // disconnect output source
    printWriter.close();
    outputStreamWriter.close();
    outputStream.close();
  }

  /**
   * Read N by N matrix from N lines of input file.
   *
   * @param buffer BufferedReader object which provides input stream from a file.
   * @param N The number of columns (or rows) of square matrix.
   * @return 1-D array which represents N by N matrix.
   *
   * @throws IOException can be caused by {@link BufferedReader}
   */
  public static int[] readMatrix(BufferedReader buffer, int N) throws IOException {
    int[] A = new int[N * N];

    for (int i = 0; i < N; i++) { // Read matrix line by line

      final AtomicInteger cnt = new AtomicInteger(i * N); // The offset point of i-th row

      Arrays.stream(buffer.readLine().trim().split("")) // Read each line as a string, split each characters into string array.
          .forEach(literal -> {                               //
            int val = Integer.parseInt(literal);              // For each element in string array, parse them into integer.
            A[cnt.getAndIncrement()] = val;                   // Put each parsed integer into the int array.
          });
    }

    return A; // return the matrix
  }

  /**
   * This method is started from each element we traverse.
   * When this method process an element, it travels four adjacent elements recursively.
   *
   * @param N The number of columns of a square matrix.
   * @param A A N by N square matrix.
   * @param i The index number of row.
   * @param j The index number of column.
   *
   * @return The solution of problem or sub-problem.
   */
  private static int count(int N, int[] A, int i, int j) {
    int val = A[N * i + j];

    int sum = 0;

    // Base case
    if (val == 0) sum++;
    else return sum;

    A[N * i + j] = 2; // To preventing redundant counting

    // When the block is empty, check whether the adjacent blocks are empty.
    for (int k = 0; k < 4; ++k) {
      sum += count(N, A, i + NEAR[k][0], j + NEAR[k][1]);
    }

    return sum;
  }

  private static final int[][] NEAR =
      {
          {-1, 0},  // up
          {+1, 0},  // down
          {0, -1},  // left
          {0, +1}   // right
      };
}
