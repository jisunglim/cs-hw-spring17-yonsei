package units;

/**
 * Warrior type unit
 *
 * Created by jaylim on 17/05/2017.
 */
public class Warrior extends UnitImpl {

  public static final UnitType type = UnitType.WARRIOR;

  @Override
  public UnitType type() {
    return type;
  }

  @Override
  public String fullId() {
    return "w" + id();
  }

  public Warrior(UnitBuilder<Warrior> builder) {
    id = builder.id;
    attr = builder.attr;

    hp = builder.hp;
    st = builder.st;
    ag = builder.ag;

    a = builder.a;
    b = builder.b;
  }

  /**
   * Create unit builder
   */
  public static UnitBuilder<Warrior> builder() {
    return new UnitBuilder<Warrior>() {
      @Override
      public Warrior build() {
        return new Warrior(this);
      }
    };
  }
}
