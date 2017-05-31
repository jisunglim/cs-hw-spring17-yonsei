package exception;

import units.Unit;
import units.UnitImpl;
import units.UnitType;

/**
 * Exceptions likely to occur when parsing the unit creation inputs.
 *
 * Created by jaylim on 21/05/2017.
 */
public abstract class UnitCreationException extends RuntimeException {

  /** Field */
  String unitType;

  String unitId;
  String valueName;

  protected UnitCreationException(String message, Unit unit, String value) {
    super(message);
    unitType = unit.type().getName();
    unitId = unit.fullId();
    valueName = value;
  }

  protected UnitCreationException(String message, UnitType type) {
    super(message);
    unitType = type.getName();
  }

  public String getSystemMessage() {
    return super.getMessage();
  }

  @Override
  public String getMessage() {
    if (unitType == null) return getSystemMessage();

    StringBuilder sb = new StringBuilder(unitType);
    sb.append(" unit ");

    if (unitId == null) {
      sb.append("count value");
    } else {
      sb.append(unitId).append(": ").append(valueName);
    }
    sb.append(" ").append(getSystemMessage());
    return sb.toString();
  }
}
