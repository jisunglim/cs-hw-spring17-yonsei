package edu.yonsei.csi2103.algorithm;

import edu.yonsei.csi2103.stack.ArrayStack;
import edu.yonsei.csi2103.stack.Stack;
import edu.yonsei.csi2103.utils.Direction;


/**
 *
 *
 * Created by jaylim on 17/03/2017.
 */
public class StackBased implements Algorithm {

  public int solution(final int N, final int[][][] A, final int[][][] CACHE) {
    if (A[N-1][N-1][N-1] == 1) {
      return 0; // Cut off the trivial case
    }
    return sol(N, A, CACHE);
  }

  private static int sol(final int N, final int[][][] A, final int[][][] CACHE) {

    int sol = Integer.MAX_VALUE; // Global solution which is minimum.
    int cnt = 0;                 // The length of path at current position
    Stack<Position> stack = new ArrayStack<>(6 * N * N * N);
    stack.push(new Position(0, 0, 0));

    while(!stack.isEmpty()) {
      Position p = stack.peek();

      // check temporary lock
      switch (A[p.i][p.j][p.k]) {
        case 0 :
          // count itself
          cnt++;

          // set temporary lock
          A[p.i][p.j][p.k] = 2;

          int cache;
          if ((cache = CACHE[p.i][p.j][p.k]) != 0) {
            sol = Math.min(sol, cnt + (cache - 1));
            break;
          }

          // Push all possible path
          for (Direction d : Direction.values()) {
            Position next = new Position(p, d);

            if (!outOfRange(next, N) && !isBlocked(next, A)) {
              stack.push(next);
            }
          }
          break;

        case 2 :
          // undo count
          cnt--;
          // pop from stack
          p = stack.pop();
          // release temporary lock
          A[p.i][p.j][p.k] = 0;
          break;
      }

      // System.out.printf("P[%2d][%2d][%2d] : %3d[%3d]\n", p.i, p.j, p.k, cnt, sol);
    }

    return sol == Integer.MAX_VALUE ? 0 : sol;
  }

  private static boolean outOfRange(Position p, int N) {
    return !(0 <= p.i && p.i < N &&
             0 <= p.j && p.j < N &&
             0 <= p.k && p.k < N);
  }

  private static boolean isBlocked(Position p, int[][][] A) {
    return 0 != A[p.i][p.j][p.k];
  }

  private static class Position {
    int i;
    int j;
    int k;

    Position(int i, int j, int k) {
      this.i = i;
      this.j = j;
      this.k = k;
    }

    Position(Position p, Direction d) {
      this.i = p.i + d.i;
      this.j = p.j + d.j;
      this.k = p.k + d.k;
    }
  }

}

