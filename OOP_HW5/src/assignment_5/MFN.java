package assignment_5;

import javax.naming.OperationNotSupportedException;
import java.security.InvalidParameterException;

/**
 * Created by jaylim on 31/05/2017.
 */
public class MFN {

  private final Double[] cache = new Double[1000];
  private final Operator op;

  public MFN(double x0, double x1, double x2, String op) {
    cache[0] = x0;
    cache[1] = x1;
    cache[2] = x2;

    this.op = Operator.get(op);
  }

  public double get(int n) throws InvalidParameterException, OperationNotSupportedException {
    if (n <  0) throw new InvalidParameterException("n must be a non-negative integer.");
    if (cache[n] != null) return cache[n];

    double ret;
    switch (op) {
      case ADD: ret = get(n-1) + get(n-2) + get(n-3); break;
      case MUL: ret = get(n-1) * get(n-2) * get(n-3); break;
      default: throw new OperationNotSupportedException(String.format("This sequence does not support such operator, %s.", op.getLiteral()));
    }

    return cache[n] = ret;
  }

  enum Operator {
    ADD("+"), MUL("*");

    private String literal;

    Operator(String str) {
      literal = str;
    }

    public String getLiteral() {
      return literal;
    }

    public static Operator get(String str) {
      if (str.equals(ADD.getLiteral())) return ADD;
      if (str.equals(MUL.getLiteral())) return MUL;
      return null;
    }
  }
}
