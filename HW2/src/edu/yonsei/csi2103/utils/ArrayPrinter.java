package edu.yonsei.csi2103.utils;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by jaylim on 17/03/2017.
 */
public class ArrayPrinter {

  public static void print1dArray(int N, int[] A) {
    print1dArray(N, A, "");
  }

  public static void print1dArray(int N, int[] A, String indent) {
    System.out.print(indent);
    for(int i = 0; i < N; i++) {
      System.out.printf("%2d ", A[i]);
    }
    System.out.println();
  }

  public static void print2dArray(int N, int[] A) {
    System.out.println("[");
    for (int i = 0; i < N; i++) {
      System.out.print("  ");
      for (int j = 0; j < N; j++) {
        System.out.printf("%2d ", A[i*N + j]);
      }
      System.out.println();
    }
    System.out.println("]");
  }

  public static void print2dArray(int N, int[][] A, String indent) {
    System.out.println(indent + "[");
    for (int i = 0; i < N; i++) {
      print1dArray(N, A[i], indent + "  ");
    }
    System.out.println(indent + "]");
  }

  public static void print2dArray(int N, int[][] A) {
    print2dArray(N, A, "");
  }

  public static void print3dArray(int N, int[][][] A) {
    System.out.println("[");
    for (int i = 0; i < N; i++) {
      print2dArray(N, A[i], "  ");
    }
    System.out.println("]");

  }
  
  ///////

  public static void print1dArray(PrintWriter pWriter, int N, int[] A) {
    print1dArray(pWriter, N, A, "");
  }

  public static void print1dArray(PrintWriter pWriter, int N, int[] A, String indent) {
    pWriter.print(indent);
    for(int i = 0; i < N; i++) {
      pWriter.printf("%2d ", A[i]);
    }
    pWriter.println();
  }

  public static void print2dArray(PrintWriter pWriter, int N, int[] A) {
    pWriter.println("[");
    for (int i = 0; i < N; i++) {
      pWriter.print("  ");
      for (int j = 0; j < N; j++) {
        pWriter.printf("%2d ", A[i*N + j]);
      }
      pWriter.println();
    }
    pWriter.println("]");
  }

  public static void print2dArray(PrintWriter pWriter, int N, int[][] A, String indent) {
    pWriter.println(indent + "[");
    for (int i = 0; i < N; i++) {
      print1dArray(pWriter, N, A[i], indent + "  ");
    }
    pWriter.println(indent + "]");
  }

  public static void print2dArray(PrintWriter pWriter, int N, int[][] A) {
    print2dArray(pWriter, N, A, "");
  }

  public static void print3dArray(PrintWriter pWriter, int N, int[][][] A) {
    pWriter.println("[");
    for (int i = 0; i < N; i++) {
      print2dArray(pWriter, N, A[i], "  ");
    }
    pWriter.println("]");

  }
}
