package exception;

/**
 * Exception occur when the unit count and the number of unit desription lines are unmatched.
 *
 * Created by jaylim on 18/05/2017.
 */
public class UnmatchedUnitCountException extends Exception {
  /** Field */
  private Integer unmatchedCountValue = null;
  private Integer unitNumber = null;

  /** Constructor */
  public UnmatchedUnitCountException() {
    super("Count value and units are unmatched.");
  }

  public UnmatchedUnitCountException(int countValue) {
    this();
    unmatchedCountValue = countValue;
  }

  public UnmatchedUnitCountException(int countValue, int unitNum) {
    this(countValue);
    unitNumber = unitNum;
  }

  @Override
  public String getMessage() {
    if (unmatchedCountValue == null)
      return super.getMessage();
    else
      return getFullMessage();
  }

  private String getFullMessage() {
    if (unmatchedCountValue == null) {
      return super.getMessage();
    }

    StringBuilder sb = new StringBuilder();
    sb.append("Count value [");
    sb.append(unmatchedCountValue);
    sb.append("] ");

    sb.append("and units ");
    if (unitNumber != null) {
      sb.append("[");
      sb.append(unitNumber);
      sb.append("] ");
    }
    sb.append("are unmatched.");

    return sb.toString();
  }
}
