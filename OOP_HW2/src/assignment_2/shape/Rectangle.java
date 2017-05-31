package assignment_2.shape;

import assignment_2.Grid;

/**
 * Created by jaylim on 03/05/2017.
 */
public class Rectangle extends Shape {
  public final double h;    // height of the rectangle
  public final double w;    // width of the rectangle
  public final double cX;   // center x
  public final double cY;   // center y

  public Rectangle(String name, double height, double width, double centerX, double centerY) {
    super(name);

    h = height;
    w = width;
    cX = centerX;
    cY = centerY;

    type = ShapeType.RECTANGLE;
  }

  @Override
  public String toString() {
    return String.format("Rectangle %s [h=%6.3f, w=%6.3f, (x=%7.3f, y=%7.3f)]\n", name, h, w, cX, cY);
  }

  @Override
  public Boundary getBoundary() {
    int minX = (int) Math.ceil(cX - w/2);
    int maxX = (int) Math.floor(cX + w/2);
    int minY = (int) Math.ceil(cY - h/2);
    int maxY = (int) Math.floor(cY + h/2);

    return new Boundary(minX, maxX, minY, maxY);
  }

  @Override
  public boolean contains(int x, int y) {
    return (Math.floor(cX - w/2) < x && x < Math.ceil(cX + w/2))
        && (Math.floor(cY - h/2) < y && y < Math.ceil(cY + h/2));
  }

  @Override
  public Rectangle translate(Grid target) {
    double cX = this.cX + target.zeroX;
    double cY = this.cY + target.zeroY;
    return new Rectangle(name, h, w, cX, cY);
  }
}
