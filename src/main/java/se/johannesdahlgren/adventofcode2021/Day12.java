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
      caveMap.computeIfAbsent(split[0], s -> new ArrayList<>()).add(split[1]);
      caveMap.computeIfAbsent(split[1], s -> new ArrayList<>()).add(split[0]);
    }
  }

  public int countPaths() {
    List<String> visitedSmallCaves = new ArrayList<>();
    return traverse("start", visitedSmallCaves);
  }

  private int traverse(String current, List<String> visitedSmallCaves) {
    if (current.equals("end")) {
      return 1;
    }
    if (visitedSmallCaves.contains(current)) {
      return 0;
    }

    if (current.equals(current.toLowerCase())) {
      visitedSmallCaves.add(current);
    }

    int visits = 0;
    if (caveMap.containsKey(current)) {
      for (String s : caveMap.get(current)) {
//        System.out.print(s);
        ArrayList<String> visitedByThisCave = new ArrayList<>(visitedSmallCaves);
        visits += traverse(s, visitedByThisCave);
      }
//      System.out.println();
    }

    return visits;
  }
}
