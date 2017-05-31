package assignment_2.shape;

/**
 * Created by jaylim on 03/05/2017.
 */
public class ShapeFactory {


  public static Shape getShape(String[] input){

    if (input == null){
      return null;
    }

    // 1. Name
    String name = input[0].trim();
    String type = name.substring(0, 1);

    if (type.equals("c")) { // Circle
      // Radius r
      double rad = Double.parseDouble(input[1].trim());

      // Center point (x, y)
      String temp = input[2].trim();
      String[] center = temp.substring(1, temp.length() - 1).split(",");
      double cX = Double.parseDouble(center[0].trim());
      double cY = Double.parseDouble(center[1].trim());

      return new Circle(name, rad, cX, cY);

    } else if (type.equals("r")) {  // Rectangle
      // (h, w)
      double h = Double.parseDouble(input[1].trim());
      double w = Double.parseDouble(input[2].trim());

      // Center point (x, y)
      String temp = input[3].trim();
      String[] center = temp.substring(1, temp.length() - 1).split(",");
      double cX = Double.parseDouble(center[0].trim());
      double cY = Double.parseDouble(center[1].trim());

      return new Rectangle(name, h, w, cX, cY);

    } else if (type.equals("s")) { // Square
      // Side length s
      double s = Double.parseDouble(input[1].trim());

      // Center point (x, y)
      String temp = input[2].trim();
      String[] center = temp.substring(1, temp.length() - 1).split(",");
      double cX = Double.parseDouble(center[0].trim());
      double cY = Double.parseDouble(center[1].trim());

      return new Square(name, s, cX, cY);

    } else if (type.equals("t")) { // Triangle

      double[][] points = new double[3][2];
      for (int i = 0; i < 3; i++) {
        // Point P1(x, y)
        String temp = input[i+1].trim();
        String[] center = temp.substring(1, temp.length() - 1).split(",");
        points[i][0] = Double.parseDouble(center[0].trim());
        points[i][1] = Double.parseDouble(center[1].trim());
      }

      return new Triangle(name, points);

    } else {
      return null;
    }
  }
}


