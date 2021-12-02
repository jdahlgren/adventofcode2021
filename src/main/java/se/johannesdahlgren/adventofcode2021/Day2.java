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
      if ("forward".equals(command)) {
        horizontalPos += unit;
      } else if ("down".equals(command)) {
        depth += unit;
      } else if ("up".equals(command)) {
        depth -= unit;
      }
    }
    return horizontalPos * depth;
  }
}
