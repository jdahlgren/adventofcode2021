package se.johannesdahlgren.adventofcode2021;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

public class Day11 {

  private final List<Octopus> octopuses = new ArrayList<>();

  public Day11(String inputFileName) {
    int[][] octopusEnergyLevel = FileUtil.readFileToInteger2DArray(inputFileName);
    for (int i = 0; i < octopusEnergyLevel.length; i++) {
      for (int j = 0; j < octopusEnergyLevel[0].length; j++) {
        octopuses.add(new Octopus(i, j, octopusEnergyLevel[i][j], false));
      }
    }
  }

  public long countFlashes(int steps) {
    AtomicLong flashes = new AtomicLong();
    printOctopusEnergyLevels();
    for (int i = 0; i < steps; i++) {
      doOneStep();
      octopuses.stream()
          .filter(Octopus::isHasFlashed)
          .forEach(octopus -> {
                octopus.reset();
                flashes.getAndIncrement();
              }
          );
      printOctopusEnergyLevels();
    }
    return flashes.get();
  }

  public int firstStepForAllToFlash() {
    int step = 0;
    boolean didAllFlash = false;
    while (!didAllFlash) {
      doOneStep();
      didAllFlash = octopuses.stream().allMatch(Octopus::isHasFlashed);
      octopuses.stream()
          .filter(Octopus::isHasFlashed)
          .forEach(Octopus::reset
          );
      step++;
    }
    return step;
  }

  private void doOneStep() {
    octopuses.forEach(octopus -> octopus.energy += 1);
    octopuses.stream()
        .filter(Octopus::willFlash)
        .forEach(this::flash);
  }

  private void flash(Octopus octopus) {
    if (octopus.isHasFlashed()) {
      return;
    }
    octopus.hasFlashed = true;
    List<Octopus> adjacentOctopuses = getAdjacentOctopuses(octopus);
    for (Octopus adjacentOctopus : adjacentOctopuses) {
      adjacentOctopus.energy += 1;
      if (adjacentOctopus.willFlash()) {
        flash(adjacentOctopus);
      }
    }
  }

  private List<Octopus> getAdjacentOctopuses(Octopus octopus) {
    List<Octopus> allNeighbors = new ArrayList<>();
    for (int i = octopus.getY() - 1; i <= octopus.getY() + 1; i++) {
      for (int j = octopus.getX() - 1; j <= octopus.getX() + 1; j++) {
        int finalI = i;
        int finalJ = j;
        List<Octopus> neighbors = octopuses.stream()
            .filter(o -> o.getX() == finalJ && o.getY() == finalI && !o.equals(octopus))
            .toList();
        allNeighbors.addAll(neighbors);
      }
    }
    return allNeighbors;
  }

  private void printOctopusEnergyLevels() {
    int maxX = octopuses.stream().map(Octopus::getX).max(Integer::compareTo).orElse(0);
    int maxY = octopuses.stream().map(Octopus::getY).max(Integer::compareTo).orElse(0);
    for (int i = 0; i <= maxY; i++) {
      for (int j = 0; j <= maxX; j++) {
        int finalI = i;
        int finalJ = j;
        octopuses.stream().filter(o -> o.getX() == finalJ && o.getY() == finalI).forEach(o -> System.out.print(o.getEnergy()));
      }
      System.out.println();
    }
    System.out.println();
  }


  @AllArgsConstructor
  @Getter
  @EqualsAndHashCode
  private static final class Octopus {

    private final int x;
    private final int y;
    private int energy;
    private boolean hasFlashed;

    public boolean willFlash() {
      return energy > 9;
    }

    public void reset() {
      energy = 0;
      hasFlashed = false;
    }
  }
}
