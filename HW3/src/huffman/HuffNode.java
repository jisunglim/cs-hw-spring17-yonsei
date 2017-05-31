package huffman;

/**
 * Created by jaylim on 07/04/2017.
 */
public class HuffNode implements Comparable<HuffNode> {
  public int weight;

  public HuffNode (int w) {
    weight = w;
  }

  @Override
  public int compareTo(HuffNode other) {
    return (other.weight - weight);
  }

  public int countLeaf() {
    return countLeaf(this);
  }

  private int countLeaf(HuffNode cur) {
    if (cur instanceof IntlNode) {
      return countLeaf(((IntlNode)cur).left()) + countLeaf(((IntlNode)cur).right());
    } else {
      return 1;
    }
  }

}