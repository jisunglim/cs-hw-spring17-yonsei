package units;

/**
 * Created by jaylim on 20/05/2017.
 *
 * Type of unit.
 */
public enum UnitType {
  MONSTER("Monster"), WARRIOR("Warrior");

  private String name;

  UnitType(String typeName) {
    this.name = typeName;
  }

  /**
   * @return unit type name
   */
  public String getName() {
    return name;
  }
}
