package se.johannesdahlgren.adventofcode2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Day9 {

  private final int[][] heightMap;

  public Day9(String inputFileName) {
    heightMap = FileUtil.readFileToInteger2DArray(inputFileName);
  }

  public long getRiskLevelsSum() {
    List<Integer> lowPoints = new ArrayList<>();
    for (int row = 0; row < heightMap[0].length; row++) {
      for (int col = 0; col < heightMap.length; col++) {
        int point = heightMap[col][row];
        Map<Point, Integer> neighbors = getNeighbors(col, row);
//        System.out.printf("Col: %s, Row: %s, Current: %s, Neighbors: %s%n", col, row, point, neighbors);
        Integer lowestNeighbor = neighbors.values().stream().min(Integer::compareTo).orElse(-1);
        if (point < lowestNeighbor) {
          lowPoints.add(point);
        }

      }
    }
//    System.out.println(lowPoints);
    return lowPoints.stream()
        .map(lowPoint -> lowPoint + 1)
        .reduce(Integer::sum)
        .orElse(0);
  }

  public long getBasinSizes() {
    Map<Point, Integer> basins = new HashMap<>();
    for (int row = 0; row < heightMap[0].length; row++) {
      for (int col = 0; col < heightMap.length; col++) {
        int point = heightMap[col][row];
        Map<Point, Integer> neighbors = getNeighbors(col, row);
        Integer lowestNeighbor = neighbors.values().stream().min(Integer::compareTo).orElse(-1);
        if (point < lowestNeighbor) {
          Set<Point> visitedPoints = new HashSet<>();
          visitedPoints.add(new Point(col, row));
          int basinSize = getBasinSize(col, row, visitedPoints);
          basins.put(new Point(col, row), basinSize);
        }

      }
    }
    return basins.values().stream().sorted().skip(basins.values().size() - 3).reduce((a, b) -> a * b).orElse(0);
  }

  private int getBasinSize(int currentColumn, int currentRow, Set<Point> visitedPoints) {
    Map<Point, Integer> neighbors = getNeighbors(currentColumn, currentRow);
    Map<Point, Integer> basinNeighbors = neighbors.entrySet().stream()
        .filter(entry -> entry.getValue() != 9)
        .filter(entry -> !visitedPoints.contains(entry.getKey()))
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    if (basinNeighbors.size() != 0) {
      for (Point point : basinNeighbors.keySet()) {
        visitedPoints.add(point);
        getBasinSize(point.col(), point.row(), visitedPoints);
      }
      return visitedPoints.size();
    } else {
      return 0;
    }
  }

  private Map<Point, Integer> getNeighbors(int currentColumn, int currentRow) {
    Map<Point, Integer> neighbors = new HashMap<>();
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        int rowNeighbor = currentRow + i;
        int columnNeighbor = currentColumn + j;
        boolean insideRowBounds = rowNeighbor >= 0 && rowNeighbor < heightMap[currentRow].length;
        boolean insideColumnBounds = columnNeighbor >= 0 && columnNeighbor < heightMap.length;
        boolean isCurrentPos = rowNeighbor == currentRow && columnNeighbor == currentColumn;
        boolean isDiagonal = Math.abs(i + j) != 1;
        if (insideRowBounds && insideColumnBounds && !isCurrentPos && !isDiagonal) {
          neighbors.put(new Point(columnNeighbor, rowNeighbor), heightMap[columnNeighbor][rowNeighbor]);
        }
      }
    }
    return neighbors;
  }

  private record Point(int col, int row) {

  }
}
