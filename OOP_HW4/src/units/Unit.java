package units;

import java.util.List;

/**
 * Created by jaylim on 21/05/2017.
 */
public interface Unit {

  /**
   * @return the type of the unit. (Monster or warrior)
   */
  UnitType type();

  /**
   * Return the full name of unit (m# for monsters, w# for warriors)
   */
  String fullId();

  int id();
  Attribute attribute();
  double hp();
  double strength();
  double agility();
  double a();
  double b();


  /**
   * Calculate turn delay.
   */
  double turnDelay();

  /**
   * Check whether the unit is dead or not
   */
  boolean isDead();

  /**
   * Attack one of the enemies determined by specific rule.
   */
  Unit attack(List<Unit> enemies);

  /**
   * Attacked behavior.
   */
  void attacked(double damege);

  /**
   * Create unit builder for specific class.
   */
  static <E extends Unit> UnitBuilder<E> builder(Class<E> klass) {
    if (klass.equals(Monster.class)) {
      return (Unit.UnitBuilder<E>) Monster.builder();
    } else /*if (klass.equals(Warrior.class))*/ {
      return (Unit.UnitBuilder<E>) Warrior.builder();
    }
  }

  /**
   * Field name generator.
   */
  static String fieldName(int idx) {
    switch(idx) {
      case 10: return "id value";
      case 11: return "attribute";

      case 0: return "hp value";
      case 1: return "strength value";
      case 2: return "agility value";
      case 3: return "alpha value";
      case 4: return "beta value";
      default: return "unknown";
    }
  }

  /**
   * Unit builder
   */
  abstract class UnitBuilder<E extends Unit> {
    int id;
    Attribute attr;

    double hp;
    double st;
    double ag;

    double a;
    double b;

    public UnitBuilder<E> id(int id) {
      this.id = id;
      return this;
    }

    public UnitBuilder<E> attr(Attribute attr) {
      this.attr = attr;
      return this;
    }

    public UnitBuilder<E> hp(double hp) {
      this.hp = hp;
      return this;
    }

    public UnitBuilder<E> strength(double st) {
      this.st = st;
      return this;
    }

    public UnitBuilder<E> agility(double ag) {
      this.ag = ag;
      return this;
    }

    public UnitBuilder<E> a(double a) {
      this.a = a;
      return this;
    }

    public UnitBuilder<E> b(double b) {
      this.b = b;
      return this;
    }

    public abstract E build();
  }

}
