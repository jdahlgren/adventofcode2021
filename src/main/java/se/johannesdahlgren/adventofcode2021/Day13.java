package se.johannesdahlgren.adventofcode2021;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day13 {

  private final List<String> instructions;
  private final int xMax;
  private final int yMax;
  private Set<Coord> randomDots;

  public Day13(String s) {
    List<String> transparentPaper = FileUtil.readFileToStringList(s);
    instructions = transparentPaper.stream().filter(line -> line.contains("fold along")).toList();
    randomDots = transparentPaper.stream().filter(line -> !line.contains("fold along")).filter(line -> !line.isBlank()).map(this::toCoord)
        .collect(Collectors.toSet());
    xMax = randomDots.stream().map(Coord::x).max(Comparator.naturalOrder()).orElse(0);
    yMax = randomDots.stream().map(Coord::y).max(Comparator.naturalOrder()).orElse(0);
  }

  public long foldOnce() {
    String firstInstruction = instructions.get(0);
    doAFold(firstInstruction);
    return randomDots.size();
  }

  private void doAFold(String firstInstruction) {
    Matcher matcher = Pattern.compile("([yx])=(\\d+)").matcher(firstInstruction);
    if (!matcher.find()) {
      return;
    }

    boolean isHorizontal = "y".equals(matcher.group(1));
    int foldSize = Integer.parseInt(matcher.group(2));
    fold(isHorizontal, foldSize);
  }

  private void fold(boolean isHorizontal, int foldSize) {
    Set<Coord> dotsAfterFold = new HashSet<>();

    for (Coord randomDot : randomDots) {
      if (isHorizontal && randomDot.y() > foldSize) {
        int newY = yMax - randomDot.y();
        dotsAfterFold.add(new Coord(randomDot.x(), newY));
      } else if (!isHorizontal && randomDot.x() > foldSize) {
        int newX = xMax - randomDot.x();
        dotsAfterFold.add(new Coord(newX, randomDot.y()));
      } else {
        dotsAfterFold.add(randomDot);
      }

    }
    randomDots = dotsAfterFold;
  }

  private Coord toCoord(String line) {
    String[] split = line.split(",");
    return new Coord(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
  }

  private record Coord(int x, int y) {

  }

}
