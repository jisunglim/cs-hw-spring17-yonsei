package edu.yonsei.csi2103.algorithm;

import edu.yonsei.csi2103.utils.Direction;

/**
 *
 *
 * Created by jaylim on 19/03/2017.
 */
public class Recursive implements Algorithm {

  public int solution(final int N, final int[][][] A, final int[][][] CACHE) {
    if (A[N-1][N-1][N-1] == 1) {
      return 0; // cut off the trivial case
    }
    return sol(0, 0, 0, N, A, CACHE);
  }


  private static int sol(int i, int j, int k, final int N, final int[][][] A, final int[][][] CACHE) {


    // Illegal state - Out of bound condition
    if (i >= N || i < 0 || j >= N || j < 0 || k >= N || k < 0)
      return 0; // implies that we do not count impossible route.

    // Blocked by 1
    if (A[i][j][k] == 1)
      return 0; // implies that we do not count impossible route.

    // From here, every position satisfies two conditions :
    // 1. A[i][j][k] = 0            (Unblocked)
    // 2. i, j, k in [0, ..., N-1]  (Valid position)
    if (CACHE[i][j][k] != 0) {
      return CACHE[i][j][k];
    }

    // count itself
    int sol = 1;

    // Temporary lock
    A[i][j][k] = 1;

    int sub = Integer.MAX_VALUE; // Find shortest path.
    for (Direction d : Direction.values()) {

      int di = i + d.i;
      int dj = j + d.j;
      int dk = k + d.k;

      int local = sol(di, dj, dk, N, A, CACHE);
      if (local != 0) // If possible route, take it into account.
        sub = Math.min(sub, local);
    }

    // Release lock
    A[i][j][k] = 0;

    if (sub != Integer.MAX_VALUE) { // If there exists at least one possible route.
      sol += sub;
    } else {
      return 0; // implies that we do not count impossible route.
    }

    return sol;
  }

}
