package assignment_2;

import assignment_2.shape.Shape;
import assignment_2.shape.ShapeFactory;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jaylim on 31/03/2017.
 */
public class Main {

  /* I/O File path */
  private static final String INPUT_FILE_NAME = "OOP_HW2/rsc/input.txt";
  private static final String OUTPUT_FILE_NAME = "OOP_HW2/out/output.txt";


  public static void main(String[] args) throws Exception {

    // Class for managing IO buffer
    IOManager<Integer, Map<String, Shape>> io = new IOManager<Integer, Map<String, Shape>>(INPUT_FILE_NAME, OUTPUT_FILE_NAME) {
      @Override
      public Map<String, Shape> readInput(Integer N) throws IOException {

        // Create String-Circle map, where the key is the name of the circle, and the value will be Circle objects.
        Map<String, Shape> shapeMap = new Hashtable<>(N);

        for (int i = 0; i < N; i++) {

          // Read input line by line and split each line into three parts.
          String[] str = reader().readLine().trim().split(" ");

          Shape s = ShapeFactory.getShape(str);
          shapeMap.put(s.name, s);
        }

        return shapeMap;
      }
    };



    String firstLine; // First line of each test case
    // For each case, read first line. Check nullity and format validity.
    if ((firstLine = io.reader().readLine()) == null || !firstLine.matches("[1-9][0-9]*")) {
      throw new Exception();
    }

    // 1. Get size of circles and read inputs to configure the name-circle map.
    final int N = Integer.parseInt(firstLine);
    Map<String, Shape> shapeMap = io.readInput(N);

    // 2. Initialize grid and calculator module
    final Grid grid = new Grid(10, -10, 10, -10);
    final Calculator calc = new Calculator(grid);

    io.reader().readLine();   // Skip one line

    AtomicInteger count = new AtomicInteger(0);

    // 3.read each lines and calculate the number of points in the circles
    io.reader().lines().forEach(equation -> {
      String[] eq = equation.trim().split(" ");

      // Init grid
      calc.setGridEmpty();

      // Get first operand circle and union on the empty grid, which is equivalent to union with empty set.
      Shape s1 = shapeMap.get(eq[0].trim());
      calc.union(s1);

      // Read each operator-operation pair sequentially
      // i*2+1: operator, i*2+2: operand
      for (int i = 0; i < eq.length / 2; i++) {

        // Get operand circle
        Shape s = shapeMap.get(eq[i*2+2].trim());
        // Get operator and operate corresponding operation.
        switch (eq[i*2 + 1]) {
          case "/":
            calc.intersection(s);
            break;
          case "*":
            calc.union(s);
            break;
        }
      }
      try {
        io.writer().write(String.format("%s: %d\n", equation, calc.count()));

//        // For test; printing out grid result.
//        PrintWriter pw = new PrintWriter(String.format("OOP_HW2/out/test/equation%d.txt", count.incrementAndGet()));
//        calc.printOut(pw);
//        pw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

    });

    // release IO resources
    io.clearResource();
  }

}
