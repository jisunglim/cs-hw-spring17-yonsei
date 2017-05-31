package edu.yonsei.csi2103.utils;

/**
 * Created by jaylim on 24/03/2017.
 */
public enum Direction {
  DOWN     ( 1, 0, 0),
  FORWARD  ( 0, 1, 0),
  RIGHT    ( 0, 0, 1),
  UP       (-1, 0, 0),
  BACKWARD ( 0,-1, 0),
  LEFT     ( 0, 0,-1);

  public int i;
  public int j;
  public int k;

  Direction(int i, int j, int k) {
    this.i = i;
    this.j = j;
    this.k = k;
  }
}
