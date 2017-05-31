package edu.yonsei.csi2102;

import java.util.Scanner;

/**
 *
 * Created by jaylim on 19/03/2017.
 */
public class Main {

  public static void main(String[] args) {

    // Declare and instantiate scanner object having system input stream.
    Scanner scanner = new Scanner(System.in);

    // Get input value of r
    System.out.print("r = ");
    String rawR = scanner.nextLine().trim();

    // Parse input into double type
    double r = Double.parseDouble(rawR);

    // Get input value of x and y
    System.out.print("(x,y) = ");
    String rawStr = scanner.nextLine();
    String[] posStr = rawStr.trim().substring(1, rawStr.length() - 1).split(",");

    // Parse inputs into double type
    double cX = Double.parseDouble(posStr[0].trim());
    double cY = Double.parseDouble(posStr[1].trim());

    // Find the minimum lattice including the circle
    double minX = Math.ceil(cX-r);
    double maxX = Math.floor(cX+r);

    double minY = Math.ceil(cY-r);
    double maxY = Math.floor(cY+r);

    // Check every point whether it is in the circle or not
    int cnt = 0;
    for (double x = minX; x <= maxX; x += 1.0) {
      for (double y = minY; y <= maxY; y += 1.0) {
        if (norm2sqr(x,y,cX,cY) < r*r) {
          cnt++;
        }
      }
    }

    // print out the number of points in the circle
    System.out.printf("Counts: %d\n", cnt);
  }

  private static double norm2sqr(double x1, double y1, double x2, double y2) {
    return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
  }
}
