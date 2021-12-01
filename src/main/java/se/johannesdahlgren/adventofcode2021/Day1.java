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

  public int calculateNumberOfDepthIncreasesSlidingWindow3(String inputFile) {
    List<Integer> depthReadings = FileUtil.readFileToIntList(inputFile);
    int numberOfDepthIncreases = 0;
    int previousSlidingDepth = Integer.MAX_VALUE;
    for (int i = 0; i < depthReadings.size() - 2; i++) {
      int depthReadingSliding = depthReadings.get(i) + depthReadings.get(i + 1) + depthReadings.get(i + 2);
      if (depthReadingSliding > previousSlidingDepth) {
        numberOfDepthIncreases++;
      }
      previousSlidingDepth = depthReadingSliding;
    }
    return numberOfDepthIncreases;
  }
}
