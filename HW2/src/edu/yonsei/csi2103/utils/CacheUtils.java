package edu.yonsei.csi2103.utils;

/**
 * Created by jaylim on 24/03/2017.
 */
public class CacheUtils {


  public static int[][][] genCache(final int N) {
    final int[][][] CACHE = new int[N][N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        for (int k = 0; k < N; k++) {
          CACHE[i][j][k] = 0;
        }
      }
    }
    return CACHE;
  }


  public static void minCaching(final int N, final int[][][] A, final int[][][] CACHE) {
    for (int i = N - 1; i >= 0; i--) {
      for (int j = N - 1; j >= 0; j--) {
        for (int k = N - 1; k >= 0; k--) {
          CACHE[i][j][k] = findGeoShortest(i, j, k, N, A, CACHE);
        }
      }
    }
  }

  private static int findGeoShortest(final int i, final int j, final int k, final int N, final int A[][][], final int CACHE[][][]) {
    if (i >= N || i < 0 || j >= N || j < 0 || k >= N || k < 0)
      return 0; // implies that we do not count impossible route.

    // Blocked by 1
    if (A[i][j][k] == 1)
      return 0; // implies that we do not count impossible route.

    // Reach the destination (N-1, N-1, N-1)
    if (i == N-1 && j == N-1 && k == N-1)
      return 1;

    // Condition
    // 1. A[i][j][k] = 0
    // 2. valid position
    int sol = 1;

    // Reach the destination (N-1, N-1, N-1)
    if (i == N-1 && j == N-1 && k == N-1)
      return 1;

    int pSol = 0;
    for (Direction d : shortestDirection) {

      int di = i + d.i;
      int dj = j + d.j;
      int dk = k + d.k;

      if (di >= N || di < 0 || dj >= N || dj < 0 || dk >= N || dk < 0)
        continue;

      int cSol;
      if ((cSol = CACHE[di][dj][dk]) != 0) {
        pSol = cSol;
        break;
      }
    }

    if (pSol != 0) {
      sol += pSol;
    }

    return sol == 1 ? 0 : sol;
  }

  private static Direction[] shortestDirection = {
      Direction.DOWN, Direction.FORWARD, Direction.RIGHT
  };
}
