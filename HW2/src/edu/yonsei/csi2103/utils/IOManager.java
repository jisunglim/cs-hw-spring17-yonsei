package edu.yonsei.csi2103.utils;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class is designed focusing on a few basic file-related tasks:
 * creating IO stream, reading and writing files, and managing resources.
 *
 * Created by jaylim on 23/03/2017.
 */
public class IOManager {

  private final BufferedReader buffer;
  private final PrintWriter printer;

  /**
   * Constructor.
   *
   * @param inputPath Absolute path to input file
   * @param outputPath Absolute path to output file
   * @throws FileNotFoundException is thrown if cannot find any file.
   */
  public IOManager(String inputPath, String outputPath) throws FileNotFoundException {
    buffer = getInputBuffer(inputPath);
    printer = getOutputPrinter(outputPath);
  }

  /**
   * Return input buffer. It is recommended to only use the object, not to
   * reference the buffer directly. It may cause memory leak.
   *
   * @return {@link BufferedReader} Input buffer
   */
  public BufferedReader buffer() {
    return buffer;
  }

  /**
   * Return output printer. It is recommended to only use the object, not to
   * reference the printer directly. It may cause memory leak.
   *
   * @return {@link PrintWriter} Output printer
   */
  public PrintWriter printer() {
    return printer;
  }

  /**  Read input from input stream, and store them in proper data structure. */
  public int[][][] readInput(int N) throws IOException {
    return readInput(N, buffer);
  }

  /** Release  */
  public void clearResource() throws IOException {
    buffer.close();
    printer.close();
  }

  private static int[][][] readInput(int N, BufferedReader buffer)  throws IOException {

    int[][][] A = new int[N][N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        final int ci = i;
        final int cj = j;
        final AtomicInteger ck = new AtomicInteger(0);
        Arrays.stream(buffer.readLine().trim().split(""))
            .forEach(str -> A[ci][cj][ck.getAndIncrement()] = Integer.parseInt(str));
      }
    }

    return A;
  }

  private static BufferedReader getInputBuffer(String path) throws FileNotFoundException {
    // File input
    final InputStream inputStream = new FileInputStream(path);
    final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

    return new BufferedReader(inputStreamReader);
  }

  private static PrintWriter getOutputPrinter(String path) throws FileNotFoundException {
    // File output
    final OutputStream outputStream = new FileOutputStream(path);
    final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

    return new PrintWriter(outputStreamWriter);
  }
}
