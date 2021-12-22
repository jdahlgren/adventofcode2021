package se.johannesdahlgren.adventofcode2021;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

public class Day22 {

  private final List<String> lines;

  public Day22(String inputFilename) {
    lines = FileUtil.readFileToStringList(inputFilename);
  }

  public long rebootInnerReactor(int cubePosRegion) {
    List<Cube> reactor = setupReactor(cubePosRegion);
    Pattern pattern = Pattern.compile("(on|off) x=(-?\\d+)..(-?\\d+),y=(-?\\d+)..(-?\\d+),z=(-?\\d+)..(-?\\d+)");
    for (String line : lines) {
      Matcher matcher = pattern.matcher(line);
      if (!matcher.find()) {
        System.out.println("Bad regex");
        return 0;
      }
      boolean setOn = matcher.group(1).equals("on");
      List<Long> xRange = LongStream.rangeClosed(Long.parseLong(matcher.group(2)), Long.parseLong(matcher.group(3)))
          .filter(i -> i <= cubePosRegion).filter(i -> i >= -cubePosRegion).boxed().toList();
      List<Long> yRange = LongStream.rangeClosed(Long.parseLong(matcher.group(4)), Long.parseLong(matcher.group(5)))
          .filter(i -> i <= cubePosRegion).filter(i -> i >= -cubePosRegion).boxed().toList();
      List<Long> zRange = LongStream.rangeClosed(Long.parseLong(matcher.group(6)), Long.parseLong(matcher.group(7)))
          .filter(i -> i <= cubePosRegion).filter(i -> i >= -cubePosRegion).boxed().toList();
      reactor.stream()
          .filter(cube -> xRange.contains(cube.x) && yRange.contains(cube.y) && zRange.contains(cube.z))
          .forEach(cube -> cube.isOn = setOn);

    }
    return reactor.stream().filter(cube -> cube.isOn).count();
  }

  private List<Cube> setupReactor(int cubePosRegion) {
    List<Cube> innerReactor = new ArrayList<>();
    for (int i = -cubePosRegion; i <= cubePosRegion; i++) {
      for (int j = -cubePosRegion; j <= cubePosRegion; j++) {
        for (int k = -cubePosRegion; k <= cubePosRegion; k++) {
          innerReactor.add(new Cube(i, j, k));
        }
      }
    }
    return innerReactor;
  }

  static final class Cube {

    private final long x;
    private final long y;
    private final long z;
    private boolean isOn;

    Cube(long x, long y, long z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }
  }
}
