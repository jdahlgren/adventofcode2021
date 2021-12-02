package se.johannesdahlgren.adventofcode2021;

import java.util.List;

public class Day2 {

  public int calculatePosition(String input) {
    List<String> plannedCourse = FileUtil.readFileToStringList(input);
    int horizontalPos = 0;
    int depth = 0;
    for (String commandUnit : plannedCourse) {
      String[] split = commandUnit.split(" ");
      String command = split[0];
      int unit = Integer.parseInt(split[1]);
      switch (command) {
        case "forward" -> horizontalPos += unit;
        case "down" -> depth += unit;
        case "up" -> depth -= unit;
      }
    }
    return horizontalPos * depth;
  }

  public int calculatePositionWithAim(String input) {
    List<String> plannedCourse = FileUtil.readFileToStringList(input);
    int horizontalPos = 0;
    int depth = 0;
    int aim = 0;
    for (String commandUnit : plannedCourse) {
      String[] split = commandUnit.split(" ");
      String command = split[0];
      int unit = Integer.parseInt(split[1]);
      switch (command) {
        case "forward" -> {
          horizontalPos += unit;
          depth += aim * unit;
        }
        case "down" -> aim += unit;
        case "up" -> aim -= unit;
      }
    }
    return horizontalPos * depth;
  }
}
