package units;

import java.util.List;

/**
 * Created by jaylim on 17/05/2017.
 */
public abstract class UnitImpl implements Unit {
  /** UnitImpl ID   */ protected int id;
  /** Attribute */ protected Attribute attr;

  /** HP       */ protected double hp;
  /** Strength */ protected double st;
  /** Agility  */ protected double ag;

  /** alpha */ protected double a;
  /** beta  */ protected double b;

  @Override
  public int id() {
    return id;
  }

  @Override
  public Attribute attribute() {
    return attr;
  }

  @Override
  public double hp() {
    return hp;
  }

  @Override
  public double strength() {
    return st;
  }

  @Override
  public double agility() {
    return ag;
  }

  @Override
  public double a() {
    return a;
  }

  @Override
  public double b() {
    return b;
  }


  // Behavior


  @Override
  public boolean isDead() {
    return hp <= 0;
  }

  @Override
  public double turnDelay() {
    return hp / (st * ag);
  }

  @Override
  public Unit attack(List<Unit> enemies) {

    // Find target
    Unit target = enemies.get(0);
    for (int i = 1; i < enemies.size(); i ++) {
      Unit temp = enemies.get(i);
      if (temp.hp() > target.hp()) {
        target = temp;
      }
    }

    attack(target);

    st = st * 0.9;
    ag = ag * 0.85;

    return target;
  }

  /**
   * Attack helper, it takes account the attribute superiority.
   */
  private void attack(Unit target) {
    if (type().equals(target.type())) return;

    int t = this.attribute().compare(target.attribute());

    if (this.attribute().equals(target.attribute())) {
      target.attacked(damege());
    } else if (target.attribute().equals(Attribute.NONE)) {
      target.attacked(damege() * 1.1);
    } else if (this.attribute().equals(Attribute.NONE)) {
      target.attacked(damege() * 0.9);
    } else {
      if (t > 1) {
        target.attacked(damege() * 1.2);
      } else /* if (t < 1) */ {
        target.attacked(damege() * 0.85);
      }
    }
  }

  @Override
  public void attacked(double damege) {
    hp -= damege;
  }

  private double damege() {
    return st * a + ag * b;
  }

  @Override
  public String toString() {
    return String.format("%s: %.2f %.2f %.2f", fullId(), hp(), strength(), agility());
  }
}


