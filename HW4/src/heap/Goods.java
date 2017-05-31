package heap;

/**
 * Created by jaylim on 03/05/2017.
 */
public class Goods implements Key{
  public int key;
  public String name;

  public Goods(int key, String name) {
    this.name = name;
    this.key = key;
  }

  @Override
  public String toString() {
    return String.format("%s:%2d", name, key);
  }

  @Override
  public int key() {
    return key;
  }
}
