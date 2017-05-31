package assignment_2.shape;

import assignment_2.Grid;

/**
 * Created by jaylim on 02/05/2017.
 */
public abstract class Shape {

  public String name;         // Name of the shape
  public ShapeType type;      // Type of the shape
  private Boundary boundary;  // Feasible boundary

  /** Constructor */
  public Shape(String name) {
    this.name = name;
  }

  /**
   * Method to evaluate the rectangle boundary of the shape object.
   *
   * @return the boundary of the shape object.
   */
  public abstract Boundary getBoundary();

  /**
   * Evaluate the boundary taking the grid size into account.
   *
   * @param grid where the shape posed on.
   * @return grid-specified boundary
   */
  public Boundary getBoundary(Grid grid) {
    if (boundary == null) {
      boundary = getBoundary();
    }
    return new Boundary(this.boundary, grid);
  }

  /**
   * Translate the shape with respect to specific target grid.
   * @param target The grid where the shape will be posed.
   * @return The grid-specified shape.
   */
  public abstract Shape translate(Grid target);

  /**
   * Check whether a grid point (x, y) is in the shape or not. (exclusive to the point 'on' the line).
   *
   * @param x X-coordinate of the grid point
   * @param y Y-coordinate of the grid point
   * @return true if the point (x, y) is in the shape, return false otherwise.
   */
  public abstract boolean contains(int x, int y);

  /**
   * Boundary class which contains (inclusive) upper and lower bound of the shape.
   */
  public static class Boundary {
    public int minX;
    public int maxX;
    public int minY;
    public int maxY;

    public Boundary(int minX, int maxX, int minY, int maxY) {
      this.minX = minX; this.maxX = maxX;
      this.minY = minY; this.maxY = maxY;
    }

    public Boundary(Boundary rawBound, Grid grid) {
      this.minX = boundaryCheck(rawBound.minX, 0, grid.lenX - 1);
      this.maxX = boundaryCheck(rawBound.maxX, 0, grid.lenX - 1);
      this.minY = boundaryCheck(rawBound.minY, 0, grid.lenY - 1);
      this.maxY = boundaryCheck(rawBound.maxY, 0, grid.lenY - 1);
    }

    /**
     * Evaluate the value with checking boundary condition.
     *
     * @param val float value.
     * @param lower bound
     * @param upper bound
     * @return
     */
    private static int boundaryCheck(int val, int lower, int upper) {
      if (upper < val) return upper;
      if (lower > val) return lower;
      return val;
    }
  }

  /**
   * The type of the shapes.
   */
  public enum ShapeType {
    TRIANGLE, CIRCLE, RECTANGLE, SQUARE
  }

}
