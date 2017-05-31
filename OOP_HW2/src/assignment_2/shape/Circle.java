package assignment_2.shape;

import assignment_2.Grid;

/**
 * Circle class has fields: radius, and x, y value for its center.
 *
 * Created by jaylim on 31/03/2017.
 */
public class Circle extends Shape {
  public final double r;
  public final double cX;
  public final double cY;

  /**
   *
   * @param name Radius of a circle
   * @param radius Radius of a circle
   * @param centerX x-coordinate of a center of a circle
   * @param centerY y-coordinate of a center of a circle
   */
  public Circle(String name, double radius, double centerX, double centerY) {
    super(name);

    r = radius;
    cX = centerX;
    cY = centerY;

    type = ShapeType.CIRCLE;
  }

  @Override
  public String toString() {
    return String.format("Circle %s [r=%6.3f, (x=%7.3f, y=%7.3f)]\n", name, r, cX, cY);
  }

  @Override
  public Boundary getBoundary() {
    int minX = (int) Math.ceil(cX - r);
    int maxX = (int) Math.floor(cX + r);
    int minY = (int) Math.ceil(cY - r);
    int maxY = (int) Math.floor(cY + r);

    return new Boundary(minX, maxX, minY, maxY);
  }

  @Override
  public boolean contains(int x, int y) {
    return norm2sqr(x, y, cX, cY) < r * r;
  }

  @Override
  public Circle translate(Grid target) {
    double cX = this.cX + target.zeroX;
    double cY = this.cY + target.zeroY;
    return new Circle(name, r, cX, cY);
  }

  /** Evaluate the square root. */
  private static double norm2sqr(double x1, double y1, double x2, double y2) {
    return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
  }
}
