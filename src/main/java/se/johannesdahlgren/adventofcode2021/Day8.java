package se.johannesdahlgren.adventofcode2021;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day8 {

  private final Map<String, String> inputOutput;
  private static final int NUMBER_OF_SIGNALS_FOR_ONE = 2;
  private static final int NUMBER_OF_SIGNALS_FOR_FOUR = 4;
  private static final int NUMBER_OF_SIGNALS_FOR_SEVEN = 3;
  private static final int NUMBER_OF_SIGNALS_FOR_EIGHT = 7;

  public Day8(String inputFileName) {
    List<String> input = FileUtil.readFileToStringList(inputFileName);
    inputOutput = input.stream().map(line -> line.split(" \\| ")).collect(Collectors.toMap(strings -> strings[0], strings -> strings[1]));
  }

  public int getNumberOfTimesEasyNumbersAppear() {
    AtomicInteger numberOfEasyNumbers = new AtomicInteger();
    for (String output : inputOutput.values()) {
      String[] split = output.split(" ");
      for (String digit : split) {

        switch (digit.length()) {
          case NUMBER_OF_SIGNALS_FOR_ONE, NUMBER_OF_SIGNALS_FOR_FOUR,
              NUMBER_OF_SIGNALS_FOR_SEVEN, NUMBER_OF_SIGNALS_FOR_EIGHT -> numberOfEasyNumbers.getAndIncrement();
        }
      }
    }

    return numberOfEasyNumbers.get();
  }
}
