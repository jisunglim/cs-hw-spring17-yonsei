package assignment_2.shape;

import assignment_2.Grid;

/**
 * Created by jaylim on 03/05/2017.
 */
public class Square extends Rectangle {
  public Square(String name, double side, double centerX, double centerY) {
    super(name, side, side, centerX, centerY);

    type = ShapeType.SQUARE;
  }

  @Override
  public Square translate(Grid target) {
    double cX = this.cX + target.zeroX;
    double cY = this.cY + target.zeroY;
    return new Square(name, h, cX, cY);
  }

  @Override
  public String toString() {
    return String.format("Square %s [s=%6.3f, (x=%7.3f, y=%7.3f)]\n", name, h, cX, cY);
  }
}
