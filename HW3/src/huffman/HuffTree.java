package huffman;

import heap.Heap;
import queue.ArrayQueue;
import queue.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by jaylim on 07/04/2017.
 */
public class HuffTree {

  /** Root node of huffman tree */
  private HuffNode root;

  /** Constructor */
  public HuffTree(HuffNode rt) {
    root = rt;
  }

  /** HuffTree Builder using Heap */
  public static HuffTree build(Heap<HuffNode> builder, int[] data) {

    for (int i = 0; i < data.length; i++) {     // Read frequency table, and enqueue only valid values
      if (data[i] == 0) continue;
      HuffNode temp = new LeafNode(i, data[i]);
      builder.enqueue(temp);
    }

    while (builder.length() > 1) {              // Repeat 2 Dequeue + 1 Enqueue until only one element left.
      HuffNode n1 = builder.dequeue();
      HuffNode n2 = builder.dequeue();

      builder.enqueue(new IntlNode(n1, n2));
    }

    return new HuffTree(builder.dequeue());     // Return the Well-built Huffman Tree
  }

  /** Consumer API to act on every node in pre-order. */
  public void preorder(Consumer<HuffNode> action) {
    preorder(action, root);
  }

  public void preorder(Consumer<HuffNode> action, HuffNode node) {
    action.accept(node);
    if (node instanceof IntlNode) {
      preorder(action, ((IntlNode) node).left());
      preorder(action, ((IntlNode) node).right());
    }
  }

  /**
   * two arguments consumer to act on every node in level-order.
   * This method is designed to draw tree graph, hence it enqueue
   * placeholders in some positions to make tree can be spread in
   * minimum.
   */
  public void levelOrder(Function<Integer, Consumer<HuffNode[]>> func) {
    Queue<HuffNode> queue = new ArrayQueue<>(100);    // Level order use Queue data structure
    queue.enqueue(root);

    HuffNode cur = root;    // Set current node root, it will be updated for each node in each level.
    int level = 0;          // The root (level 0)
    int num = 1;            // The number of node in level 0 (root)
    boolean done = false;   // To check last level

    while (!done) {
      done = true;
      HuffNode[] nodes = new HuffNode[num];

      int leafNum = 0;
      for (int i = 0; i < num; i++) {
        nodes[i] = cur = queue.dequeue();
        if (cur instanceof IntlNode) {
          queue.enqueue(((IntlNode) cur).left());
          queue.enqueue(((IntlNode) cur).right());
          done = false;
        } else {
          queue.enqueue(null);      // Here the placeholder to ensure the space below Leaf node
          leafNum++;
        }
      }

      func.apply(level++).accept(nodes);  // Do some work with level and nodes in the level.
      num = num * 2 - leafNum;            // Update the number of nodes to be read for each level.
    }
  }

  public int encoding() {
    // return the maximum length of huffman code bits
    return encoding(root, new StringBuilder(""), 0L) - 1;
  }

  public int encoding(HuffNode node, StringBuilder sb, long code) {

    if (node instanceof LeafNode) {
      ((LeafNode) node).setCode(sb.toString()); // Assigning huffman code into character
      return 1;
    }

    if (node instanceof IntlNode) {
      // Down to left
      sb.append(0);
      int l = encoding(((IntlNode) node).left(), sb, code);
      sb.deleteCharAt(sb.length()-1);

      // Down to right
      sb.append(1);
      int r = encoding(((IntlNode) node).right(), sb, code);
      sb.deleteCharAt(sb.length()-1);

      // Calculate max height
      return 1 + Math.max(l, r);
    }

    // For default case
    return 0;
  }

  public String[] createEncodeTable() {
    // The max length of encode table is 128 (length of the ASCII code)
    String[] encodeTable = new String [128];
    this.preorder(node -> {
      if (node instanceof LeafNode) {
        // fill the corresponding huffman code bits into the table
        encodeTable[((LeafNode) node).character()] = ((LeafNode) node).code();
      }
    });

    return encodeTable;
  }

  public int[][] createDecodeTable(int maxCodeLen) {
    // The max length of decode lookup table is 2^(max len of code)
    int[][] decodeTable = new int[(int) Math.pow(2, maxCodeLen)][2];
    this.preorder(node -> {
      if (node instanceof LeafNode) {
        // get code
        String code = ((LeafNode) node).code();
        int gap = maxCodeLen - code.length();

        // extend with 0 bits [Start]
        StringBuilder sb = new StringBuilder(code);
        for (int i = 0; i < gap; i++) {
          sb.append("0");
        }
        String start = sb.toString();
        int iStart = Integer.parseInt(start,2);

        // extends with 1 bits [End]
        sb = new StringBuilder(code);
        for (int i = 0; i < gap; i++) {
          sb.append("1");
        }
        String end = sb.toString();
        int iEnd = Integer.parseInt(end,2);

        // fill the corresponding character and valid length of the code into the table
        for (int i = iStart; i <= iEnd; i++) {
          decodeTable[i][0] = ((LeafNode) node).character();
          decodeTable[i][1] = code.length();
        }
      }
    });
    return decodeTable;
  }


}