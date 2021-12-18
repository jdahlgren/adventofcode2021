package se.johannesdahlgren.adventofcode2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {

  Map<String, List<String>> caveMap = new HashMap<>();

  public Day12(String inputFileName) {
    List<String> caveMapList = FileUtil.readFileToStringList(inputFileName);
    for (String path : caveMapList) {
      String[] split = path.split("-");
      if (caveMap.containsKey(split[0])) {
        caveMap.get(split[0]).add(split[1]);
      } else {
        ArrayList<String> cavePaths = new ArrayList<>();
        cavePaths.add(split[1]);
        if (!split[0].equals("end")) {
          caveMap.put(split[0], cavePaths);
        }
      }
      if (caveMap.containsKey(split[1])) {
        caveMap.get(split[1]).add(split[0]);
      } else {
        ArrayList<String> cavePaths = new ArrayList<>();
        cavePaths.add(split[0]);
        if (!split[1].equals("end")) {
          caveMap.put(split[1], cavePaths);
        }
      }
    }
  }

  public int countPaths() {
    List<String> visitedSmallCaves = new ArrayList<>();
    return traverse("start", visitedSmallCaves);
  }

  private int traverse(String current, List<String> visitedSmallCaves) {
    int visits = 0;
    if (current.equals("end")) {
      return 1;
    }
    if (visitedSmallCaves.contains(current)) {
      return 0;
    }

    if (isLowerCase(current)) {
      visitedSmallCaves.add(current);
    }

    if (caveMap.containsKey(current)) {
      for (String s : caveMap.get(current)) {
//        System.out.print(s);
        visits += traverse(s, visitedSmallCaves);
      }
//      System.out.println();
    }
    visitedSmallCaves.remove(current);

    return visits;
  }

  private boolean isLowerCase(String path) {
    for (char c : path.toCharArray()) {
      if (Character.isUpperCase(c)) {
        return false;
      }
    }
    return true;
  }
}
