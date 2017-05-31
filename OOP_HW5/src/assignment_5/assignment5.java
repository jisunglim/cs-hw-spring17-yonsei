package assignment_5;

import utils.IOManager;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.security.InvalidParameterException;

/**
 * Created by jaylim on 31/05/2017.
 */
public class assignment5 {

  private static final String INPUT = "/Users/jaylim/Developers/java/hw_assignment/OOP_HW5/rsc/input.txt";
  private static final String OUTPUT = "/Users/jaylim/Developers/java/hw_assignment/OOP_HW5/out/output.txt";

  public static void main(String[] args) throws IOException {
    IOManager<Double> io = new IOManager<Double>(INPUT, OUTPUT) {

      @Override
      public Double readInput() throws IOException {
        String line;
        if ((line = reader().readLine()) == null || line.equals(""))
          return null;

        String[] tokens = line.trim().split(" ");

        final double x0 = Double.parseDouble(tokens[0]);
        final double x1 = Double.parseDouble(tokens[1]);
        final double x2 = Double.parseDouble(tokens[2]);
        final String op = tokens[3];
        MFN mfn = new MFN(x0, x1, x2, op);

        final int n = Integer.parseInt(tokens[4]);

        try {
          return mfn.get(n);
        } catch (OperationNotSupportedException | InvalidParameterException e) {
          e.printStackTrace();
        }
        return null;
      }
    };

    Double result;
    while ((result = io.readInput()) != null) {
      double c = Math.pow(10, 10);
      if (result < -c || c < result) {
        io.writer().write("out of bound\n");
      } else {
        io.writer().write(String.format("%.0f\n",result));
      }
    }

    io.clearResource();
  }
}
