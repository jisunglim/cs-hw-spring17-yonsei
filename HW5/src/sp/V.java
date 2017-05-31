package sp;

import heap.Key;

/**
 * Vertex class
 *
 * int id, vertex number; this number is invariant.
 * int d, distance; known (explored) distance from source to this vertex.
 * int i, information; estimated distance from this vertex to destination.
 */
public class V implements Key {
  public final int id;
  public int d;
  public int i;

  public V(int vertexNum, int distance, int information) {
    id = vertexNum;
    d = distance;
    i = information;
  }

  @Override
  public int id() {
    return id;
  }

  @Override
  public int key() {
    if (d >= 0 && i >= 0 && d + i < 0) return Integer.MAX_VALUE;
    return d + i;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    return id == ((V) o).id;
  }

  @Override
  public int hashCode() {
    return id;
  }

  @Override
  public String toString() {
    return String.format("id:%2d[ %4s + %4s = %4s] ",
        id,
        d == Integer.MAX_VALUE ? "M" : d, i,
        key() == Integer.MAX_VALUE ? "M" : key());
  }
}