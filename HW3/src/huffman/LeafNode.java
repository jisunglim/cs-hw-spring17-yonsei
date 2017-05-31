package huffman;

/**
 * Created by jaylim on 07/04/2017.
 */
public class LeafNode extends HuffNode {
  final private int character;
  private String code;

  LeafNode (int ch, int frequency) {
    super(frequency);
    character = ch;
  }

  public int character() {
    return character;
  }

  public String code() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return String.format("%c[%d]: %d\n", (char) character, character, weight);
  }
}