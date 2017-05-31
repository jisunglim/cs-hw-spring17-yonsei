package units;

/**
 * Attributes.
 *
 * Created by jaylim on 20/05/2017.
 */
public enum Attribute {
  WATER("w"), FIRE("f"), EARTH("e"), NONE("");

  private String t;

  Attribute(String tag) {
    t = tag;
  }

  public static Attribute get(String tag) {
    if (tag.equals("w")) return WATER;
    if (tag.equals("f")) return FIRE;
    if (tag.equals("e")) return EARTH;
    if (tag.equals(""))  return NONE;
    else throw new EnumConstantNotPresentException(Attribute.class, tag);
  }

  /**
   * Compare two attribute type.
   *
   * @param other attribute
   *
   * @return if same return 0. superior return 1, inferior return -1.
   */
  public int compare(Attribute other) {

    // same level
    if (this.equals(other)) return 0;

    // normal is always inferior to other attribute
    if (this.equals(NONE)) return -1;
    if (other.equals(NONE)) return 1;

    if (this.equals(WATER) && other.equals(FIRE)) return 1;
    if (this.equals(FIRE) && other.equals(EARTH)) return 1;
    if (this.equals(EARTH) && other.equals(WATER)) return 1;

    return -1;
  }
}
