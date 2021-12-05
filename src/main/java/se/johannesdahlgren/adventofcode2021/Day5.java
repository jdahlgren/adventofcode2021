package se.johannesdahlgren.adventofcode2021;

import java.util.Arrays;
import java.util.List;

public class Day5 {

  private HydroThermalVentsMap hydroThermalVentsMap;

  public Day5(String input) {
    List<String> lines = FileUtil.readFileToStringList(input);
    setupCoords(lines);
  }

  public long getNumberOfPointsWithOverlap(int atLeastNumberOfOverlaps) {
    hydroThermalVentsMap.drawHorizontalAndVerticalLines();
//    hydroThermalVentsMap.printMap();

    return hydroThermalVentsMap.calculateOverlappingLines(atLeastNumberOfOverlaps);
  }

  private void setupCoords(List<String> lines) {
    List<HydroThermalVentsLine> hydroThermalVentsLines = lines.stream()
        .map(line -> line.split(" -> "))
        .map(this::createLine)
        .toList();
    hydroThermalVentsMap = new HydroThermalVentsMap(hydroThermalVentsLines);
  }

  private HydroThermalVentsLine createLine(String[] coords) {
    List<Integer> from = Arrays.stream(coords[0].split(",")).map(Integer::parseInt).toList();
    List<Integer> to = Arrays.stream(coords[1].split(",")).map(Integer::parseInt).toList();
    return new HydroThermalVentsLine(from.get(0), from.get(1), to.get(0), to.get(1));
  }
}
