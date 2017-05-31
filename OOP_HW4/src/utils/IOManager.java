package utils;

import java.io.*;
import java.util.Scanner;

/**
 * This class is designed focusing on a few basic file-related tasks:
 * creating IO stream, reading and writing files, and managing resources.
 *
 * Created by jaylim on 23/03/2017.
 */
public class IOManager {

  private final BufferedReader rBuffer;
  private final BufferedWriter wBuffer;
  private final Scanner scanner;

  /**
   * Constructor.
   *
   * @param inputPath Absolute path to input file
   * @param outputPath Absolute path to output file
   * @throws FileNotFoundException is thrown if cannot find any file.
   */
  public IOManager(String inputPath, String outputPath, boolean withScanner) throws IOException {
    rBuffer = getReader(inputPath);
    wBuffer = getWriter(outputPath);

    if (withScanner) {
      scanner = new Scanner(System.in);
    } else {
      scanner = null;
    }
  }

  /**
   * Return input buffer. It is recommended to only use the object, not to
   * reference the buffer directly. It may cause memory leak.
   *
   * @return {@link BufferedReader} Input buffer
   */
  public BufferedReader reader() {
    return rBuffer;
  }

  /**
   * Return output printer. It is recommended to only use the object, not to
   * reference the printer directly. It may cause memory leak.
   *
   * @return {@link PrintWriter} Output printer
   */
  public BufferedWriter writer() {
    return wBuffer;
  }

  public Scanner scanner() {
    return scanner;
  }

  /** Release  */
  public void clearResource() throws IOException {
    scanner.close();
    rBuffer.close();
    wBuffer.close();
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
