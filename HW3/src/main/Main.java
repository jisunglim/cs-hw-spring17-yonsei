package main;

import heap.Heap;
import huffman.HuffNode;
import huffman.HuffTree;
import huffman.IntlNode;
import huffman.LeafNode;
import utils.IOManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * Created by jaylim on 06/04/2017.
 */
public class Main {

  private static final String INPUT_FILE_NAME = "/Users/jaylim/Developers/java/hw_assignment/HW3/rsc/input.txt";
  private static final String OUTPUT_FILE_NAME = "/Users/jaylim/Developers/java/hw_assignment/HW3/out/output.txt";

  public static void main(String[] args) throws Exception{

    IOManager<int[]> io = new IOManager<int[]>(INPUT_FILE_NAME, OUTPUT_FILE_NAME) {

      /**
       * Reading text file char by char, count the number of occurrence for each character.
       * The frequency for each character is stored in the array using the character itself as an index of array.
       *
       * @return The character occurrence frequency table.
       */
      @Override
      public int[] readInput() throws IOException {
        // Array for 128 ASCII [0...127]
        int[] freq = new int[128];

        int ch;
        do {
          ch = reader().read(); // read char
          // Read only ASCII codes [0x00...0x7F], we will ignore the '#' character.
          if ('\u0000' <= ch && ch < '\u007F' && ch != '\u0023') freq[ch]++;

        } while(ch != -1 && ch != '\u0023');  // Loop will iterate until '#' of EOF are encountered.

        return freq;  // Return the frequency table
      }
    };

    io.reader().skip(1L);  // In this program, I wll use '#' character as if it is the end sign for each test case.
                              // Hence, for the first test case, skip '#' character.

    String firstLine; // First line of each case
    PrintWriter pw = new PrintWriter(io.writer());

    // For each case, read first line, which consists of '#' and following numbers. regex: "#[1-9][0-9]*"
    // Of course, the '#' character was skipped or already consumed playing the role of end sign of previous test case.
    // Hence, in this line, we only encounter the following numbers.
    while ((firstLine = io.reader().readLine()) != null && firstLine.matches("[1-9][0-9]*")) {

      // Count the frequency for each characters
      io.reader().mark(4000);
      int[] frequency = io.readInput(); // Read and configure the frequency map.
      io.reader().reset();

      // Build huffman tree
      Heap<HuffNode> builder = new Heap<>(new HuffNode[128], 0);
      HuffTree huffTree = HuffTree.build(builder, frequency);

      // Assigning huffman code to the character in each leaf node
      int maxLen = huffTree.encoding();

      // Get raw String
      String origString = getRawString(io.reader());
      io.reader().reset();

      /* Output starts at here. */

      // Output 0 : Test case number
      pw.printf("#%d\n", Integer.parseInt(firstLine));  // Test case number


      // Output 1 : Printout code scheme
      printCodeScheme(huffTree, pw);


      // Output 2 : Encode the raw string into huffman code
      String[] encodeTable = huffTree.createEncodeTable();
      String huffmanCode = encodeHuffmanCode(huffTree, encodeTable, io.reader(), pw);


      // Output 3 : Decode the huffman code into Raw string.
      int[][] decodeTable = huffTree.createDecodeTable(maxLen);
      BufferedReader reader = new BufferedReader(new StringReader(huffmanCode));
      String decodedOutput = decodeHuffmanCode(huffTree, decodeTable, reader, maxLen, pw);


      // Output 4 :
      if (decodedOutput.equals(origString)) {
        pw.println("TRUE");
      } else {
        pw.println("FALSE");
      }

      // Output 5 : Generate Tree Graph
      genTreeGraph(huffTree, pw);


    }

    pw.close();
  }

  private static void printCodeScheme(HuffTree huffTree, PrintWriter printer) {
    final String[] space = new String[1];
    huffTree.preorder(node -> {
      if (node instanceof LeafNode) {
        switch(((LeafNode)node).character()) {
          case '\n':
            printer.printf("%s=%s; ", "\\n", ((LeafNode)node).code());
            break;
          case ' ':
            space[0] = ((LeafNode)node).code();
            break;
          default:
            printer.printf("%c=%s; ", (char) ((LeafNode)node).character(), ((LeafNode)node).code());
            break;
        }
      }
    });
    if (space[0] != null) {
      printer.printf(" =%s;\n", space[0]);
    } else {
      printer.println();
    }
  }

  private static String getRawString(BufferedReader reader) throws IOException {
    StringBuilder sb = new StringBuilder("");

    int ch;
    do {

      ch = reader.read(); // read char
      // Read only ASCII codes [0x00...0x7F], we will ignore the '#' character.
      if ('\u0000' <= ch && ch < '\u007F' && ch != '\u0023') {
        sb.append((char) ch);
      }

    } while (ch != -1 && ch != '\u0023');

    return sb.toString();
  }

  private static String encodeHuffmanCode(HuffTree huffTree, String[] encodeTable, BufferedReader reader, PrintWriter printer) throws IOException {
    StringBuilder sb = new StringBuilder("");

    int ch;
    do {

      ch = reader.read(); // read char
      // Read only ASCII codes [0x00...0x7F], we will ignore the '#' character.
      if ('\u0000' <= ch && ch < '\u007F' && ch != '\u0023') {
        sb.append(encodeTable[ch]);
      }

    } while (ch != -1 && ch != '\u0023');
    String huffmanCode = sb.toString();
    printer.println(huffmanCode);

    return huffmanCode;
  }

  private static String decodeHuffmanCode(HuffTree huffTree, int[][] decodeTable, BufferedReader reader, int maxCodeLen, PrintWriter printer) throws IOException {
    StringBuilder sb = new StringBuilder("");

    char[] buffer = new char[maxCodeLen];
    int off = 0;
    int len = maxCodeLen;

    while (reader.read(buffer, off, len) != -1) {
      String code = String.valueOf(buffer);

      int[] decodeScheme = decodeTable[Integer.parseInt(code, 2)];
      char ch = (char) decodeScheme[0];
      printer.print(ch);
      sb.append(ch);
      int valid = decodeScheme[1];

      for (int i = valid; i < buffer.length; i++) {
        buffer[i - valid] = buffer[i];
      }
      off = buffer.length - valid;
      len = valid;
    }
    printer.println();

    return sb.toString();
  }

  private static void genTreeGraph(HuffTree huffTree, PrintWriter printer) {
    printer.println();
    huffTree.levelOrder(level -> nodes -> {
      int k = 1;
      if (level != 0) {
        printer.printf("        | ");
        int l = 0;
        for (int i = 0; i < nodes.length; i++) {
          HuffNode node = nodes[i];
          if (node instanceof IntlNode) {
            if (l % 2 == 0) printer.printf("    |-----");
            else printer.printf("----|     ");
            l++;

            for (int j = 0; j < node.countLeaf() - 1; j++) {
              if (i % 2 == k % 2 || i == nodes.length - 1) printer.printf("          ");
              else printer.printf("----------");
            }

          } else if (node instanceof LeafNode) {
            if (l % 2 == 0) printer.printf("    |-----");
            else printer.printf("----|     ");
            l++;
          } else {
            printer.printf("    -     ");
            k++;
          }
        }
        printer.println();
      }
      printer.printf("Level%2d | ", level);
      k = 1;
      for (int i = 0; i < nodes.length; i++) {
        HuffNode node = nodes[i];
        if(node instanceof IntlNode) {

          printer.printf("  [%3d]   ", node.weight);
          for (int j = 0; j < node.countLeaf() - 1; j++) {
            if (i % 2 == k % 2 || i == nodes.length - 1) printer.printf("          ");
            else printer.printf("          ");
          }

        } else if (node instanceof LeafNode) {
          printer.printf("[%03d:%3d] ", ((LeafNode)node).character(), node.weight);
        } else {
          printer.printf("    -     ");
          k++;
        }
      }
      printer.println();
    });
    printer.println();
  }
}
