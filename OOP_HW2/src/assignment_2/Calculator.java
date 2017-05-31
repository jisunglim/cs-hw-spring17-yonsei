package assignment_2;

import assignment_2.shape.Shape;

import java.io.PrintWriter;

/**
 *
 * Created by jaylim on 31/03/2017.
 */
public class Calculator {

  private Grid grid;

  public Calculator(Grid grid) {
    this.grid = grid;
  }

  /**
   * Initialize the grid to be empty.
   */
  public void setGridEmpty() {
    for (int x = 0; x < grid.lenX; x++) {
      for (int y = 0; y < grid.lenY; y++) {
        grid.XY[x][y] = 0;
      }
    }
  }

  /**
   * Union operation on the grid.
   *
   * @param orig The primitive shape object.
   */
  public void union(Shape orig) {

    // translate circle
    Shape trans = orig.translate(grid);

    // find feasible area
    Shape.Boundary b = trans.getBoundary(grid);

    // if the grid point is in circle, mark up.
    for (int x = b.minX; x <= b.maxX; x++) {
      for (int y = b.minY; y <= b.maxY; y++) {
        if (trans.contains(x, y)) {
          grid.XY[x][y] |= 1;
        }
      }
    }
  }

  /**
   * Intersection operation on the grid.
   *
   * @param orig The primitive shape object.
   */
  public void intersection(Shape orig) {
    // translate circle
    Shape trans = orig.translate(grid);

    // if the grid point is in circle, mark up.
    for (int x = 0; x < grid.lenX; x++) {
      for (int y = 0; y < grid.lenY; y++) {
        if (trans.contains(x, y)) {
          grid.XY[x][y] &= 1;
        } else {
          grid.XY[x][y] &= 0;
        }
      }
    }
  }

  /**
   * Count every activated grid point.
   * @return the number of activated points on the grid.
   */
  public int count() {
    int sum = 0;
    for (int x = 0; x < grid.lenX; x++) {
      for (int y = 0; y < grid.lenY; y++) {
        if(grid.XY[x][y] == 1) sum++;
      }
    }
    return sum;
  }

  public void printOut() {
    for (int y = grid.lenY - 1; y >= 0; y--) {
      for (int x = 0; x < grid.lenX; x++) {
        System.out.printf("%d", grid.XY[x][y]);
      }
      System.out.println();
    }
    System.out.println();
  }

  public void printOut(PrintWriter pw) {
    for (int y = grid.lenY - 1; y >= 0; y--) {
      for (int x = 0; x < grid.lenX; x++) {
        pw.printf("%d ", grid.XY[x][y]);
      }
      pw.println();
    }
    pw.println();
  }

}
