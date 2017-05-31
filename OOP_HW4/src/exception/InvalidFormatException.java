package exception;

import units.Unit;
import units.UnitImpl;
import units.UnitType;

/**
 * Occurs when the specific value is marformed.
 *
 * Created by jaylim on 18/05/2017.
 */
public class InvalidFormatException extends UnitCreationException {
  /** Constructor */
  public InvalidFormatException(Unit unit, String value) {
    super("is invalid, try again.", unit, value);
  }

  public InvalidFormatException(UnitType type) {
    super("is invalid, try again.", type);
  }

}
