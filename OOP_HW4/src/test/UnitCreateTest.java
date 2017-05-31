package test;

import units.Attribute;
import units.Monster;

/**
 * Created by jaylim on 20/05/2017.
 */
public class UnitCreateTest {
  public static void main(String[] args) {
    Monster monster = Monster.builder().id(3).attr(Attribute.get("w"))
        .hp(79).strength(44).agility(87)
        .a(0.3).b(0.7)
        .build();

    System.out.println(monster);
  }
}
