package se.johannesdahlgren.adventofcode2021;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day17 {

  private int xMin = 0;
  private int xMax = 0;
  private int yMin = 0;
  private int yMax = 0;

  public Day17(String input) {
    Pattern pattern = Pattern.compile("target area: x=(\\d+)..(\\d+), y=(-?\\d+)..(-?\\d+)");
    Matcher matcher = pattern.matcher(input);
    boolean isFound = matcher.find();
    if (isFound) {
      xMin = Integer.parseInt(matcher.group(1));
      xMax = Integer.parseInt(matcher.group(2));
      yMin = Integer.parseInt(matcher.group(3));
      yMax = Integer.parseInt(matcher.group(4));
    }
  }

  public int findMaxY() {
    int probeYMax = Integer.MIN_VALUE;
    for (int i = 0; i <= xMax; i++) {
      // An estimated guess for y velocity
      for (int j = -100; j < 100; j++) {

        Probe probe = new Probe(i, j);
        boolean hitTarget = false;
        while (probe.xPos <= xMax && probe.yPos >= yMin) {
          if (xMin <= probe.xPos && probe.yPos <= yMax) {
            hitTarget = true;
            break;
          }
          probe.doStep();
        }
        if (hitTarget && probe.yMax > probeYMax) {
          probeYMax = probe.yMax;
//          System.out.printf("Initial velocity: %s,%s", i, j);
//          System.out.println();
        }
      }
    }

    return probeYMax;
  }

  public int howManyInitialVelocitiesHits() {
    int numberOfProbesThatHit = 0;
    for (int i = 0; i <= xMax; i++) {
      // An estimated guess for y velocity
      for (int j = -100; j < 100; j++) {

        Probe probe = new Probe(i, j);
        while (probe.xPos <= xMax && probe.yPos >= yMin) {
          if (xMin <= probe.xPos && probe.yPos <= yMax) {
            numberOfProbesThatHit++;
            break;
          }
          probe.doStep();
        }
      }
    }

    return numberOfProbesThatHit;
  }

  private static class Probe {

    int xPos = 0;
    int yPos = 0;
    int xVelocity;
    int yVelocity;
    int yMax = Integer.MIN_VALUE;

    Probe(int xVelocity, int yVelocity) {
      this.xVelocity = xVelocity;
      this.yVelocity = yVelocity;
    }

    private void doStep() {
      xPos += xVelocity;
      yPos += yVelocity;
      if (xVelocity > 0) {
        xVelocity--;
      } else if (xVelocity < 0) {
        xVelocity++;
      }
      yVelocity--;

      if (yPos > yMax) {
        yMax = yPos;
      }
    }
  }
}
