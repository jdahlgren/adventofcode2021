package se.johannesdahlgren.adventofcode2021;

import java.util.List;

public class Day1 {

  public int calculateNumberOfDepthIncreases(String inputFile) {
    List<Integer> depthReadings = FileUtil.readFileToIntList(inputFile);
    int numberOfDepthIncreases = 0;
    int currentDepth = Integer.MAX_VALUE;
    for (Integer depthReading : depthReadings) {
      if (depthReading > currentDepth) {
        numberOfDepthIncreases++;
      }
      currentDepth = depthReading;
    }
    return numberOfDepthIncreases;
  }
}
