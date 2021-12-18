package se.johannesdahlgren.adventofcode2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {

  private String polymerTemplate;
  private final Map<String, String> rules = new HashMap<>();

  public Day14(String inputFile) {
    List<String> fileOutput = FileUtil.readFileToStringList(inputFile);
    polymerTemplate = fileOutput.get(0);
    for (String line : fileOutput) {
      if (line.contains("->")) {
        String[] split = line.split(" -> ");
        rules.put(split[0], split[1]);
      }
    }
  }

  public long mostCommonMinusLeastCommonElementAfter(int steps) {
    for (int i = 0; i < steps; i++) {
      StringBuilder polymer = new StringBuilder();
      for (int j = 0; j < polymerTemplate.length() - 1; j++) {
        String pair = polymerTemplate.substring(j, j + 2);
        polymer
            .append(pair.charAt(0))
            .append(rules.get(pair));
      }
      polymer.append(polymerTemplate.charAt(polymerTemplate.length() - 1));
      polymerTemplate = polymer.toString();
    }
    Map<Character, Integer> result = new HashMap<>();

    for (char c : polymerTemplate.toCharArray()) {
      result.put(c, result.containsKey(c) ? result.get(c) + 1 : 1);
    }
    return result.values().stream().max(Integer::compareTo).orElse(0) - result.values().stream().min(Integer::compareTo).orElse(0);
  }
}
