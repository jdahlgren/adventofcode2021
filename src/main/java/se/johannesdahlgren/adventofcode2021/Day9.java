package se.johannesdahlgren.adventofcode2021;

import java.util.ArrayList;
import java.util.List;

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
        List<Integer> neighbors = getNeighbors(col, row);
//        System.out.printf("Col: %s, Row: %s, Current: %s, Neighbors: %s%n", col, row, point, neighbors);
        Integer lowestNeighbor = neighbors.stream().min(Integer::compareTo).orElse(-1);
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

  private List<Integer> getNeighbors(int currentColumn, int currentRow) {
    List<Integer> neighbors = new ArrayList<>();
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        int rowNeighbor = currentRow + i;
        int columnNeighbor = currentColumn + j;
        boolean insideRowBounds = rowNeighbor >= 0 && rowNeighbor < heightMap[currentRow].length;
        boolean insideColumnBounds = columnNeighbor >= 0 && columnNeighbor < heightMap.length;
        boolean isCurrentPos = rowNeighbor == currentRow && columnNeighbor == currentColumn;
        boolean isDiagonal = Math.abs(i + j) != 1;
        if (insideRowBounds && insideColumnBounds && !isCurrentPos && !isDiagonal) {
          neighbors.add(heightMap[columnNeighbor][rowNeighbor]);
        }
      }
    }
    return neighbors;
  }
}
