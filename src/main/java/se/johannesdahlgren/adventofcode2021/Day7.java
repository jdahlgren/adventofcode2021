package se.johannesdahlgren.adventofcode2021;

import java.util.List;

public class Day7 {

  private final List<Integer> horizontalPosition;

  public Day7(String inputFileName) {
    horizontalPosition = FileUtil.readCsvFileToIntList(inputFileName);
  }

  public long cheapestAlign() {
    int maxPos = horizontalPosition.stream().max(Integer::compareTo).orElse(0);
    long minFuel = Long.MAX_VALUE;
    for (int i = 0; i <= maxPos; i++) {
//      System.out.print("Going to pos: " + i);
      long currentFuel = 0;
      for (Integer pos : horizontalPosition) {
        currentFuel += Math.abs(pos - i);
      }
//      System.out.println(". Fuel required: " + currentFuel);
      if (currentFuel < minFuel) {
        minFuel = currentFuel;
      }
    }
    return minFuel;
  }
}
