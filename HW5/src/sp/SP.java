package sp;

import heap.Heap;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

/**
 * Shortest Path argorithm
 */
public class SP {
  public int[][] A;
  public int N;

  public int[] D;         // D: Distance
  public int[] I;         // I: Information
  public int[] F;         // F: Flight (or Air) Distance
  public boolean[][] P;   // P: Path
  public Heap<V> Q;

  public int src;
  public int dst;

  public SP(int n, int s, int d) {

    /* Data depending on the problem */
    this.N = n;   // The number of nodes
    this.src = s; // The source vertex.
    this.dst = d; // The destination vertex.

    this.A = new int[N][N];   // The graph represented by an adjacent matrix
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        A[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
      }
    }

    this.F = new int[N];      // The flight distance, which plays a role of estimated information.
  }

  /**
   * Between two execution of algorithm, it must be called
   */
  public void init() {
    this.D = new int[N];
    this.I = new int[N];
    this.P = new boolean[N][N];
  }

  public void Astar() {
    thisAlgorithm = Algorithm.Astar;
    init();

    System.out.println("[Astar] ");
    I = F;
    run();
  }

  public void Dijkstra() {
    thisAlgorithm = Algorithm.Dijkstra;
    init();

    System.out.println("[Dijkstra] ");
    I = new int[N];
    run();

  }

  public void run() {


    // Init D
    System.arraycopy(A[src], 0, D, 0, N);

    // Put every vertices except src into priority queue
    V[] vArray = new V[N + 1];
    this.Q = new Heap<>(vArray, 0, N);

    for (int i = 0; i < N; i++) {
      if (i == src) continue;
      V v = new V(i, D[i], I[i]);
      Q.enqueue(v);
    }


    // Init path info
    for (int d = 0; d < N; d++) {
      if (D[d] != Integer.MAX_VALUE) {
        P[src][d] = true;
      }
    }
    int cnt = 0;


    System.out.printf("[iter:%03d]: %s [%5s]", ++cnt, new V(0, D[0] ,I[0]), D[dst] == Integer.MAX_VALUE ? "M" : D[dst]);
    System.out.print(" || D: ");
    Arrays.stream(D).forEach(dd -> System.out.printf(" %4s ", dd == Integer.MAX_VALUE ? "M" : dd));
    System.out.printf("|| %s\n", Q);
    do {


      // Exploration
      V cur = Q.dequeue();


      System.out.printf("[iter:%03d]: %s [%5s]", ++cnt, cur, D[dst] == Integer.MAX_VALUE ? "M" : D[dst]);
      System.out.print(" || D: ");
      Arrays.stream(D).forEach(dd -> System.out.printf(" %4s ", dd == Integer.MAX_VALUE ? "M" : dd));
      System.out.printf("|| %s\n", Q);
      // System.out.println();

//      System.out.println(D[dst]);

      // Shortest Path Tree
      for (int d = 0; d < N; d++) {
        // edge relaxation
        int t = D[d] - boundedSum(cur.d, A[cur.id][d]);
        if (t > 0) {
          // Update distance
          D[d] = boundedSum(cur.d, A[cur.id][d]);

          // Update Path
          for (int i = 0; i < P.length; i++)
            P[i][d] = false;
          P[cur.id][d] = true;

          // Update Queue
          Q.enqueue(new V(d, D[d], I[d]));

        }
        if (t == 0) P[cur.id][d] = true;

      }

    } while (!Q.isEmpty());

  }

  /**
   * Summation helper, this program expresses infinite number (or big M)
   * using an {@link Integer#MAX_VALUE}. Hence, adding two numbers may
   * cause overflow so that the result will have undesirable value.
   *
   * This summation helper detect overflow (or underflow) and, if any, it
   * return the maximum (or minimum) value respectively
   *
   * @param i first operand of sum
   * @param j second operand of sum
   * @return bounded sum of i and j.
   */
  private static int boundedSum(int i, int j) {
    int sum = i + j;
    if (i >= 0 && j >=0 && sum < 0) {
      sum = Integer.MAX_VALUE;
    }
    if (i <= 0 && j <= 0 && sum > 0) {
      sum = Integer.MIN_VALUE;
    }

    return sum;
  }

  private Algorithm thisAlgorithm;

  enum Algorithm {
    Dijkstra, Astar
  }

  /** Output Manager */
  private Output output;

  public Output out() {
    return (output == null) ? output = new Output() : output;
  }

  public class Output {
    ////*** output writer ***////

    public void result(BufferedWriter bw) throws IOException {
      trackingPath("", src, bw);
    }

    private void trackingPath(String output, int curr, BufferedWriter bw) throws IOException {
      if (curr == dst) {
        bw.write(String.format("%s %d\n", output, D[dst]));
        return;
      }

      for (int next = 0; next < N; next++) {
        if (P[curr][next] && curr != next) {
          trackingPath(String.format("%s %d", output, next), next, bw);
        }
      }
    }

    ////*** Print out result on console ***////

    public void printoutResult() {
      // printAdjMatrix();
      printD();
      trackingPath();
      System.out.println();
    }

    private void printAdjMatrix() {
      System.out.print("+");
      for (int i = 0; i < A.length; i++) {
        System.out.print("----+");
      }
      System.out.println();

      for (int i = 0; i < A.length; i++) {
        System.out.print("|");
        for (int j = 0; j < A[i].length; j++) {
          if (A[i][j] == Integer.MAX_VALUE)
            System.out.printf("   M|");
          else if (A[i][j] == 0)
            System.out.printf("    |");
          else
            System.out.printf("%4d|", A[i][j]);
        }
        System.out.println();

        System.out.print("+");
        for (int j = 0; j < A.length; j++) {
          System.out.print("----+");
        }
        System.out.println();
      }
    }

    private void printD() {
      System.out.print("+");
      for (int j = 0; j < D.length; j++) {
        System.out.print("----+");
      }
      System.out.println();
      System.out.print("|");
      for (int i = 0; i < D.length; i++) {
        if (D[i] == Integer.MAX_VALUE || D[i] == 0)
          System.out.printf("    |");
        else
          System.out.printf("%4d|", D[i]);
      }
      System.out.println();
      System.out.print("+");
      for (int j = 0; j < D.length; j++) {
        System.out.print("----+");
      }
      System.out.println();
    }

    private void trackingPath() {
      trackingPath("", src);
    }

    private void trackingPath(String output, int curr) {
      if (curr == dst) {
        System.out.println((String.format("%s %d\n", output, D[dst])));
        return;
      }

      for (int next = 0; next < N; next++) {
        if (P[curr][next] && curr != next) {
          trackingPath(String.format("%s %d", output, next), next);
        }
      }
    }
  }
}
