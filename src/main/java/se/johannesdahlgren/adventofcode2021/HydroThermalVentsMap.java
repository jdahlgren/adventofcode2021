package se.johannesdahlgren.adventofcode2021;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public final class HydroThermalVentsMap {

  private final List<HydroThermalVentsLine> hydroThermalVentsLines;
  private final int[][] hydrothermalVentsMap;

  public HydroThermalVentsMap(List<HydroThermalVentsLine> hydroThermalVentsLines) {
    this.hydroThermalVentsLines = hydroThermalVentsLines;
    int maxX = getMaxX(hydroThermalVentsLines);
    int maxY = getMaxY(hydroThermalVentsLines);
    hydrothermalVentsMap = new int[maxX + 1][maxY + 1];
  }

  public void drawHorizontalAndVerticalLines() {
    for (HydroThermalVentsLine hydroThermalVentsLine : hydroThermalVentsLines) {
      if (hydroThermalVentsLine.isHorizontal()) {
        drawHorizontalLine(hydroThermalVentsLine);
      }
      if (hydroThermalVentsLine.isVertical()) {
        drawVerticalLine(hydroThermalVentsLine);
      }
    }
  }

  public void drawDiagonalLines() {
    for (HydroThermalVentsLine hydroThermalVentsLine : hydroThermalVentsLines) {
      if (hydroThermalVentsLine.isDiagonal()) {
        drawDiagonalLine(hydroThermalVentsLine);
      }
    }
  }

  public long calculateOverlappingLines(int atLeastNumberOfOverlaps) {
    return Arrays.stream(hydrothermalVentsMap)
        .flatMapToInt(Arrays::stream)
        .filter(point -> point >= atLeastNumberOfOverlaps)
        .count();
  }

  public void printMap() {
    for (int[] rows : hydrothermalVentsMap) {
      for (int point : rows) {
        System.out.print(point);
      }
      System.out.println();
    }
  }

  private void drawVerticalLine(HydroThermalVentsLine hydroThermalVentsLine) {
    if (hydroThermalVentsLine.x1() < hydroThermalVentsLine.x2()) {
      IntStream.rangeClosed(hydroThermalVentsLine.x1(), hydroThermalVentsLine.x2())
          .forEach(x -> hydrothermalVentsMap[hydroThermalVentsLine.y1()][x] += 1);
    } else {
      IntStream.rangeClosed(hydroThermalVentsLine.x2(), hydroThermalVentsLine.x1())
          .forEach(x -> hydrothermalVentsMap[hydroThermalVentsLine.y1()][x] += 1);
    }
  }

  private void drawHorizontalLine(HydroThermalVentsLine hydroThermalVentsLine) {
    if (hydroThermalVentsLine.y1() < hydroThermalVentsLine.y2()) {
      IntStream.rangeClosed(hydroThermalVentsLine.y1(), hydroThermalVentsLine.y2())
          .forEach(y -> hydrothermalVentsMap[y][hydroThermalVentsLine.x1()] += 1);
    } else {
      IntStream.rangeClosed(hydroThermalVentsLine.y2(), hydroThermalVentsLine.y1())
          .forEach(y -> hydrothermalVentsMap[y][hydroThermalVentsLine.x1()] += 1);
    }
  }

  private void drawDiagonalLine(HydroThermalVentsLine hydroThermalVentsLine) {
    if (hydroThermalVentsLine.x1() < hydroThermalVentsLine.x2()) {
      drawDiagonalLeftToRight(hydroThermalVentsLine);
    } else {
      drawDiagonalRightToLeft(hydroThermalVentsLine);
    }
  }

  private void drawDiagonalLeftToRight(HydroThermalVentsLine hydroThermalVentsLine) {
    int steps = hydroThermalVentsLine.x2() - hydroThermalVentsLine.x1();
    if (hydroThermalVentsLine.y1() < hydroThermalVentsLine.y2()) {
      for (int i = 0; i <= steps; i++) {
        hydrothermalVentsMap[hydroThermalVentsLine.y1() + i][hydroThermalVentsLine.x1() + i] += 1;
      }
    } else {
      for (int i = 0; i <= steps; i++) {
        hydrothermalVentsMap[hydroThermalVentsLine.y1() - i][hydroThermalVentsLine.x1() + i] += 1;
      }
    }
  }

  private void drawDiagonalRightToLeft(HydroThermalVentsLine hydroThermalVentsLine) {
    int steps = hydroThermalVentsLine.x1() - hydroThermalVentsLine.x2();
    if (hydroThermalVentsLine.y1() < hydroThermalVentsLine.y2()) {
      for (int i = 0; i <= steps; i++) {
        hydrothermalVentsMap[hydroThermalVentsLine.y1() + i][hydroThermalVentsLine.x1() - i] += 1;
      }
    } else {
      for (int i = 0; i <= steps; i++) {
        hydrothermalVentsMap[hydroThermalVentsLine.y1() - i][hydroThermalVentsLine.x1() - i] += 1;
      }
    }
  }

  private int getMaxX(List<HydroThermalVentsLine> hydroThermalVentsLines) {
    int maxX = 0;
    for (HydroThermalVentsLine hydroThermalVentsLine : hydroThermalVentsLines) {
      if (hydroThermalVentsLine.x1() > maxX) {
        maxX = hydroThermalVentsLine.x1();
      }
      if (hydroThermalVentsLine.x2() > maxX) {
        maxX = hydroThermalVentsLine.x2();
      }
    }
    return maxX;
  }

  private int getMaxY(List<HydroThermalVentsLine> hydroThermalVentsLines) {
    int maxY = 0;
    for (HydroThermalVentsLine hydroThermalVentsLine : hydroThermalVentsLines) {
      if (hydroThermalVentsLine.y1() > maxY) {
        maxY = hydroThermalVentsLine.y1();
      }
      if (hydroThermalVentsLine.y2() > maxY) {
        maxY = hydroThermalVentsLine.y2();
      }
    }
    return maxY;
  }
}
