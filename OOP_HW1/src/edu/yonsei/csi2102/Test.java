package edu.yonsei.csi2102;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jaylim on 24/03/2017.
 */
public class Test {
  public static void main(String[] args) {
    InputStreamReader isr = new InputStreamReader(System.in);
    try {
      while(true) {
        int a = isr.read();
        if (a == -1)
          break;
        System.out.println((char) a);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
