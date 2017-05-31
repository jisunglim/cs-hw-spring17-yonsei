package utils;

import java.io.*;

/**
 * This class is designed focusing on a few basic file-related tasks:
 * creating IO stream, reading and writing files, and managing resources.
 *
 * Created by jaylim on 23/03/2017.
 */
public abstract class IOManager<S> {

  private final BufferedReader reader;
  private final BufferedWriter writer;

  /**
   * Constructor.
   *
   * @param inputPath Absolute path to input file
   * @param outputPath Absolute path to output file
   * @throws FileNotFoundException is thrown if cannot find any file.
   */
  public IOManager(String inputPath, String outputPath) throws IOException {
    reader = getReader(inputPath);
    writer = getWriter(outputPath);
  }

  /**  Read input from input stream, and store them in proper data structure.
   *
   * @return R Structured data to be returned.
   */
  public abstract S readInput() throws IOException;

  /**
   * Return input buffer. It is recommended to only use the object, not to
   * reference the buffer directly. It may cause memory leak.
   *
   * @return {@link BufferedReader} Input buffer
   */
  public BufferedReader reader() {
    return reader;
  }

  /**
   * Return output printer. It is recommended to only use the object, not to
   * reference the printer directly. It may cause memory leak.
   *
   * @return {@link PrintWriter} Output printer
   */
  public BufferedWriter writer() {
    return writer;
  }

  /** Release  */
  public void clearResource() throws IOException {
    reader.close();
    writer.close();
  }

  private static BufferedReader getReader(String path) throws FileNotFoundException {
    // File input
    final FileReader fr = new FileReader(path);
    return new BufferedReader(fr);
  }

  private static BufferedWriter getWriter(String path) throws IOException {
    // File output
    final FileWriter fw= new FileWriter(path, false);
    return new BufferedWriter(fw);
  }
}
