package se.johannesdahlgren.adventofcode2021;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day6 {

  private List<LanternFish> currentLanternFishes = new ArrayList<>();

  public Day6(String inputFile) {
    List<Integer> initialInternalTimers = FileUtil.readCsvFileToIntList(inputFile);
    for (Integer initialInternalTimer : initialInternalTimers) {
      LanternFish lanternFish = new LanternFish(initialInternalTimer);
      currentLanternFishes.add(lanternFish);
    }
  }

  public long spawnLanternFish(int daysToSimulate) {
    for (int i = 0; i < daysToSimulate; i++) {
      List<LanternFish> lanternFishesAtEndOfDay = new ArrayList<>(currentLanternFishes);
      for (LanternFish lanternFish : currentLanternFishes) {
        Optional<LanternFish> spawnedLanternFish = lanternFish.age();
        spawnedLanternFish.ifPresent(lanternFishesAtEndOfDay::add);
      }
      currentLanternFishes = lanternFishesAtEndOfDay;
    }
    return currentLanternFishes.size();
  }
}
