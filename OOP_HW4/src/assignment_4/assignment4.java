package assignment_4;

import exception.InvalidFormatException;
import exception.OutOfRangeException;
import exception.UnmatchedUnitCountException;
import units.*;
import utils.IOManager;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.util.*;

/**
 * Created by jaylim on 17/05/2017.
 */
public class assignment4 {

  private static final String INPUT_FILE_NAME = "OOP_HW4/rsc/input_attr.txt";
  private static final String OUTPUT_FILE_NAME = "OOP_HW4/out/output_attr.txt";

  @SuppressWarnings("ConstantConditions")
  public static void main(String[] args) throws IOException {

    // Class for managing IO buffer
    IOManager io = new IOManager(INPUT_FILE_NAME, OUTPUT_FILE_NAME, true);

    int N = -1;

    // Create arrays
    List<Monster> monsters = new ArrayList<>(5);
    List<Warrior> warriors = new ArrayList<>(5);

    int wNum, mNum;

    try {
      // 1. warrior count
      wNum = getUnitCount(UnitType.WARRIOR, io.reader(), io.scanner());

      // 2. get warrior
      assignment4.getUnits(wNum, warriors, Warrior.class, io.reader(), io.scanner());

      // 3. monster count
      mNum = getUnitCount(UnitType.MONSTER, io.reader(), io.scanner());

    } catch (UnmatchedUnitCountException e) {
      System.out.println(e.getMessage());
      io.writer().write("Warrior count value exception\n");
      io.clearResource();
      return;
    }

    try {

      // 4. get monsters
      assignment4.getUnits(mNum, monsters, Monster.class, io.reader(), io.scanner());

    } catch (UnmatchedUnitCountException e) {
      System.out.println(e.getMessage());
      io.writer().write("Monster count value exception\n");
      io.clearResource();
      return;
    }

    // Create priority queues to manage the monsters and warriors
    Queue<Warrior> wQ = new PriorityQueue<>((o1, o2) -> {
      if (o1.turnDelay() < o2.turnDelay()) return -1;
      if (o1.turnDelay() > o2.turnDelay()) return 1;
      return o1.id() - o2.id();
    });
    wQ.addAll(warriors);

    Queue<Monster> mQ = new PriorityQueue<>((o1, o2) -> {
      if (o1.turnDelay() < o2.turnDelay()) return -1;
      if (o1.turnDelay() > o2.turnDelay()) return 1;
      return o1.id() - o2.id();
    });
    mQ.addAll(monsters);

    // Start game
    int turn = 1;
    boolean warToMon = true;

    while (true) {

      // Attacker and defensor
      Unit attacker;
      Unit defensor;

      Queue<? extends Unit> attackers;
      Queue<? extends Unit> defensors;

      // Setting for each turn
      if (warToMon) {
        attackers = wQ;
        defensors = mQ;
      } else {
        attackers = mQ;
        defensors = wQ;
      }

      // peek attacker
      attacker = attackers.peek();
      if (attacker == null) return;

      // attack defensor
      defensor = attacker.attack(new LinkedList<>(new LinkedList<>(defensors)));

      // if dead, remove from queue
      if (defensor.isDead()) {
        defensors.remove(defensor);
      }

      // Turn description
      io.writer().write(String.format("Turn %d: %s -> %s\n", turn, attacker.fullId(), defensor.fullId()));

      // remain warriors
      wQ.forEach(w -> {
        try {
          io.writer().write(w.toString() + "\n");
        } catch (IOException e) {
          e.printStackTrace();
        }
      });

      // remain monsters
      mQ.forEach(m -> {
        try {
          io.writer().write(m.toString() + "\n");
        } catch (IOException e) {
          e.printStackTrace();
        }
      });

      // closing new line for each stage
      io.writer().newLine();

      // game is finished, print out winner.
      if (defensors.isEmpty()) {
        io.writer().write(String.format("Winner: %s\n", attacker.type().getName()));
        break;
      }

      turn++;
      warToMon = !warToMon;
    }

    // end program
    io.clearResource();
  }


  private static int getUnitCount(UnitType type, final BufferedReader reader, Scanner scanner) throws IOException, UnmatchedUnitCountException {
    int N;

    String readLine = reader.readLine();
    if (readLine == null) {
      throw new EOFException();
    }

    System.out.println(readLine);

    if (type.equals(UnitType.MONSTER) && readLine.trim().split(" ")[0].matches("w[1-5]")) {
      throw new UnmatchedUnitCountException();
    }

    do {
      try {

        // Invalid format exception
        if (!readLine.matches("-?[1-9][0-9]*")) {
          throw new InvalidFormatException(type);
        }

        // Parse int
        N = Integer.parseInt(readLine);

        // Out of range exception
        if (!(1 <= N && N <= 5)) {
          throw new OutOfRangeException(type);
        }

        break;  // Valid format

      } catch (InvalidFormatException | OutOfRangeException e) {
        System.out.println(e.getMessage());
      }
      System.out.print("Enter a count value: ");
      readLine = scanner.nextLine();
    } while (true);

    return N;
  }

  private static <E extends Unit> List<E> getUnits(int unitNum, List<E> unitList, Class<E> klass,
                                                       final BufferedReader reader, Scanner scanner) throws IOException, UnmatchedUnitCountException {

    for (int i = 0; i < unitNum; i++) {
      int cursor = 0;

      String readLine = reader.readLine();
      if (readLine == null) {
        throw new EOFException();
      }

      String[] tokens = readLine.trim().split(" ");
      if (tokens.length < 6) {
        throw new UnmatchedUnitCountException();
      }

      Unit.UnitBuilder<E> builder = Unit.builder(klass);

      int id = Integer.parseInt(tokens[cursor++].trim().substring(1));
      builder.id(id);

      Attribute attr;
      if (tokens.length == 7) {
        String token = tokens[cursor++].trim();

        do {
          try {
            try {
              attr = Attribute.get(token);
              break;
            } catch (EnumConstantNotPresentException e) {
              throw new InvalidFormatException(builder.build(), Unit.fieldName(11));
            }
          } catch (InvalidFormatException ee) {
            System.out.println(ee.getMessage());
          }

          System.out.printf("Enter a %s of %s: ", Unit.fieldName(11), builder.build().fullId());
          token = scanner.nextLine();
          token = token.equals("") ? "-" : token;
          token = token.equals("-") ? "" : token;
        } while (true);

      } else {
        attr = Attribute.NONE;
      }
      builder.attr(attr);

      int aNum = 5;
      double[] A = new double[aNum];
      for (int idx = 0; idx < aNum; idx++) {
        String token = tokens[cursor++].trim();

        do {
          try {
            try {
              A[idx] = Double.parseDouble(token);

              if (idx < 3 && !(0 <= A[idx] && A[idx] <= 100))
                throw new OutOfRangeException(builder.build(), Unit.fieldName(idx));
              else if (idx >= 3 && !(0 <= A[idx] && A[idx] <= 1))
                throw new OutOfRangeException(builder.build(), Unit.fieldName(idx));

              break;
            } catch (NumberFormatException e) {
              throw new InvalidFormatException(builder.build(), Unit.fieldName(idx));
            }
          } catch (InvalidFormatException | OutOfRangeException ee) {
            System.out.println(ee.getMessage());
          }

          System.out.printf("Enter a %s of %s: ", Unit.fieldName(idx), builder.build().fullId());
          token = scanner.nextLine();

        } while (true);
      }

      unitList.add(builder.hp(A[0]).strength(A[1]).agility(A[2]).a(A[3]).b(A[4]).build());
    }

    return unitList;
  }


}
