package units;

/**
 * Monster type unit
 *
 * Created by jaylim on 17/05/2017.
 */
public class Monster extends UnitImpl {

  public static final UnitType type = UnitType.MONSTER;

  @Override
  public UnitType type() {
    return type;
  }

  @Override
  public String fullId() {
    return "m" + id();
  }

  public Monster(UnitBuilder<Monster> builder) {
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
  public static UnitBuilder<Monster> builder() {
    return new UnitBuilder<Monster>(){

      @Override
      public Monster build() {
        return new Monster(this);
      }
    };
  }


}
