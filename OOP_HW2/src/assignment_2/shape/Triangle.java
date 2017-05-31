package assignment_2.shape;


import assignment_2.Grid;

/**
 * Created by jaylim on 03/05/2017.
 */
public class Triangle extends Shape {
  public final double p1X, p1Y;
  public final double p2X, p2Y;
  public final double p3X, p3Y;

  public Triangle(String name,
                  double p1X, double p1Y,
                  double p2X, double p2Y,
                  double p3X, double p3Y) {
    super(name);

    this.p1X = p1X; this.p1Y = p1Y;
    this.p2X = p2X; this.p2Y = p2Y;
    this.p3X = p3X; this.p3Y = p3Y;

    type = ShapeType.TRIANGLE;
  }

  public Triangle(String name, double[][] points) {
    this(name,
        points[0][0], points[0][1],
        points[1][0], points[1][1],
        points[2][0], points[2][1]);
  }

  @Override
  public Boundary getBoundary() {
    int minX = (int) Math.ceil(min(p1X, p2X, p3X));
    int maxX = (int) Math.floor(max(p1X, p2X, p3X));
    int minY = (int) Math.ceil(min(p1Y, p2Y, p3Y));
    int maxY = (int) Math.floor(max(p1Y, p2Y, p3Y));

    return new Boundary(minX, maxX, minY, maxY);
  }

  @Override
  public boolean contains(int x, int y) {
    // Here I used Cramer's rule.

    // 1. Vector Representation
    // find two vector u := (u1, u2), v := (v1, v2)

    double u1 = p2X - p1X;
    double u2 = p2Y - p1Y;
    double v1 = p3X - p1X;
    double v2 = p3Y - p1Y;

    // 2. Evaluate the determinant and check vanishing determinant case
    double detA = u1 * v2 - v1 * u2;
    if (detA == 0.0) {
      return false; // det(A) = 0; Three points are on a single line.
    }

    // 3. Find c := (c1, c2) from given point (x, y)
    double c1 = x - p1X;
    double c2 = y - p1Y;

    // 4. Claim:
    // Let au + bv = c, if a + b < 1 and a > 0, b > 0,
    // then c vector is in the triangle which is determined by u and v.

    // By Cramer's rule
    //    a := detAv/detA
    //    b := detAu/detA

    // evaluate partial determinant
    double detAu = c1 * v2 - v1 * c2;
    double detAv = u1 * c2 - c1 * u2;

    // Not directly evaluate the coefficient a, b just compare it.

    // Handel the negative case
    if (detA < 0.0) {
      detA = - detA;
      detAu = - detAu;
      detAv = - detAv;
    }

    double t = detAu + detAv;
    return t < detA && detAu > 0 && detAv > 0;
  }

  @Override
  public Triangle translate(Grid target) {
    double p1X = this.p1X + target.zeroX;
    double p1Y = this.p1Y + target.zeroY;
    double p2X = this.p2X + target.zeroX;
    double p2Y = this.p2Y + target.zeroY;
    double p3X = this.p3X + target.zeroX;
    double p3Y = this.p3Y + target.zeroY;
    
    return new Triangle(name, p1X, p1Y, p2X, p2Y, p3X, p3Y);
  }

  @Override
  public String toString() {
    return String.format("Triangle %s [p1=(%6.3f, %6.3f), p2=(%6.3f, %6.3f), p3=(%6.3f, %6.3f)]\n",
        name, p1X, p1Y, p2X, p2Y, p3X, p3Y);
  }

  public static double min(double a, double b, double c) {
    return a < b ? (a < c ? a : c) : (b < c ? b : c);
  }

  public static double max(double a, double b, double c) {
    return a > b ? (a > c ? a : c) : (b > c ? b : c);
  }
}
