package exception;

import units.Unit;
import units.UnitImpl;
import units.UnitType;

/**
 * Occurs when the specific value is out of range.
 *
 * Created by jaylim on 18/05/2017.
 */
public class OutOfRangeException extends UnitCreationException {
  /** Constructor */
  public OutOfRangeException(Unit unit, String value) {
    super("is out of range, try again.", unit, value);
  }

  public OutOfRangeException(UnitType type) {
    super("is out of range, try again.", type);
  }
}
