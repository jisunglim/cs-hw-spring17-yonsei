package huffman;

/**
 * Created by jaylim on 07/04/2017.
 */
public class IntlNode extends HuffNode {
  private HuffNode left;
  private HuffNode right;

  IntlNode (HuffNode l, HuffNode r) {
    super(l.weight + r.weight);
    left = l;
    right = r;
  }

  public HuffNode left() {
    return left;
  }

  public HuffNode right() {
    return right;
  }
}