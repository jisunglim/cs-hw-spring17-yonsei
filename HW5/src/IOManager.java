import java.io.*;

/**
 * This class is designed focusing on a few basic file-related tasks:
 * creating IO stream, reading and writing files, and managing resources.
 *
 * Created by jaylim on 23/03/2017.
 */
public abstract class IOManager<E> {

  private final BufferedReader reader;
  private final BufferedWriter writer;

  /**
   * Constructor.
   *
   * @param inputPath Absolute path to input file
   * @param outputPath Absolute path to output file
   * @throws FileNotFoundException is thrown if cannot find any file.
   */
  public IOManager(String inputPath, String outputPath) throws FileNotFoundException {
    reader = getReader(inputPath);
    writer = getWriter(outputPath);
  }

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

  /**  Read input from input stream, and store them in proper data structure. */
  public abstract E setup(E item) throws IOException;

  /** Release  */
  public void clearResource() throws IOException {
    reader.close();
    writer.close();
  }

  private static BufferedReader getReader(String path) throws FileNotFoundException {
    // File input
    final InputStream inputStream = new FileInputStream(path);
    final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

    return new BufferedReader(inputStreamReader);
  }

  private static BufferedWriter getWriter(String path) throws FileNotFoundException {
    // File output
    final OutputStream outputStream = new FileOutputStream(path);
    final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

    return new BufferedWriter(outputStreamWriter);
  }
}
