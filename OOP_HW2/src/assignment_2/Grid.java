package assignment_2;

/**
 * Grid class has 2 dimensional integer array. Each i-th row j-th column entry of grid corresponds to the point (i, j).
 *
 * Created by jaylim on 31/03/2017.
 */
public class Grid {

  private static final int LEN = 2 * 100 + 1;
  // -100 ~ -1 :: [0...99]
  // 0         :: [100]
  // 1 ~ 100   :: [101...200]

  public final int[][] XY;

  public final int lenX;
  public final int lenY;

  public final int zeroX;
  public final int zeroY;

  public Grid() {
    XY = new int[LEN][LEN];
    zeroX = zeroY = 100;
    lenX = lenY = LEN;
  }

  public Grid(int xMax, int xMin, int yMax, int yMin) {
    int xLen = xMax - xMin + 1;
    int yLen = yMax - yMin + 1;

    XY = new int[xLen][yLen];

    zeroX = -xMin;
    zeroY = -yMin;

    lenX = xLen;
    lenY = yLen;
  }

}
