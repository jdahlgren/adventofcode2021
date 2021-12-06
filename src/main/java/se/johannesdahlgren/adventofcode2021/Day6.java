package se.johannesdahlgren.adventofcode2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day6 {

  private final List<Integer> initialInternalTimers;

  public Day6(String inputFile) {
    initialInternalTimers = FileUtil.readCsvFileToIntList(inputFile);
  }

  public long spawnLanternFishSmarter(int daysToSimulate) {
    Map<Integer, Long> timerCount = initialInternalTimers.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    for (int i = 0; i < daysToSimulate; i++) {
      Map<Integer, Long> nextTimerCount = new HashMap<>();

      for (Entry<Integer, Long> entry : timerCount.entrySet()) {
        if (entry.getKey() == 0) {
          nextTimerCount.put(6, entry.getValue());
          nextTimerCount.put(8, entry.getValue());
        } else if (entry.getKey() == 7 && timerCount.containsKey(0)) {
          nextTimerCount.put(entry.getKey() - 1, timerCount.get(0) + entry.getValue());
        } else {
          nextTimerCount.put(entry.getKey() - 1, entry.getValue());
        }
      }
      timerCount = nextTimerCount;
    }
    return timerCount.values().stream()
        .reduce(Long::sum)
        .orElse(0L);
  }

}
