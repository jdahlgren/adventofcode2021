package se.johannesdahlgren.adventofcode2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day8 {

  private final Map<String, String> inputOutput;
  private static final int NUMBER_OF_SIGNALS_FOR_ONE = 2;
  private static final int NUMBER_OF_SIGNALS_FOR_FOUR = 4;
  private static final int NUMBER_OF_SIGNALS_FOR_SEVEN = 3;
  private static final int NUMBER_OF_SIGNALS_FOR_EIGHT = 7;
  private static final int NUMBER_OF_SIGNALS_FOR_2_3_5 = 5;
  private static final int NUMBER_OF_SIGNALS_FOR_0_6_9 = 6;

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

  public int getNumberOutput() {
    int sumOfAllOutputs = 0;
    for (Entry<String, String> output : inputOutput.entrySet()) {
      String[] keys = output.getKey().split(" ");
      String[] values = output.getValue().split(" ");

      Map<String, String> signalMapping = new HashMap<>();
      List<String> remainingMappings = new ArrayList<>();
      for (String digit : keys) {
        String sortedDigit = sortString(digit);
        switch (sortedDigit.length()) {
          case NUMBER_OF_SIGNALS_FOR_ONE -> {
            signalMapping.put(sortedDigit, "1");
            signalMapping.put("1", sortedDigit);
          }
          case NUMBER_OF_SIGNALS_FOR_SEVEN -> {
            signalMapping.put(sortedDigit, "7");
            signalMapping.put("7", sortedDigit);
          }
          case NUMBER_OF_SIGNALS_FOR_FOUR -> {
            signalMapping.put(sortedDigit, "4");
            signalMapping.put("4", sortedDigit);
          }
          case NUMBER_OF_SIGNALS_FOR_EIGHT -> {
            signalMapping.put(sortedDigit, "8");
            signalMapping.put("8", sortedDigit);
          }
          case NUMBER_OF_SIGNALS_FOR_2_3_5, NUMBER_OF_SIGNALS_FOR_0_6_9 -> remainingMappings.add(sortedDigit);
        }
      }

      String eight = signalMapping.get("8");
      String four = signalMapping.get("4");

      StringBuilder twoSegment = new StringBuilder();
      for (char eightChars : eight.toCharArray()) {
        if (four.indexOf(eightChars) == -1) {
          twoSegment.append(eightChars);
        }
      }

      for (String remainingMapping : remainingMappings) {
        if (remainingMapping.length() == NUMBER_OF_SIGNALS_FOR_2_3_5 && containsAll(remainingMapping, signalMapping.get("1").toCharArray())) {
          signalMapping.put(remainingMapping, "3");
          signalMapping.put("3", remainingMapping);
        } else if (remainingMapping.length() == NUMBER_OF_SIGNALS_FOR_2_3_5 && containsAll(remainingMapping, twoSegment.toString().toCharArray())) {
          signalMapping.put(remainingMapping, "2");
          signalMapping.put("2", remainingMapping);
        } else if (remainingMapping.length() == NUMBER_OF_SIGNALS_FOR_2_3_5) {
          signalMapping.put(remainingMapping, "5");
          signalMapping.put("5", remainingMapping);
        } else if (remainingMapping.length() == NUMBER_OF_SIGNALS_FOR_0_6_9 && !containsAll(remainingMapping, signalMapping.get("1").toCharArray())) {
          signalMapping.put(remainingMapping, "6");
          signalMapping.put("6", remainingMapping);
        } else if (remainingMapping.length() == NUMBER_OF_SIGNALS_FOR_0_6_9 && containsAll(remainingMapping, signalMapping.get("4").toCharArray())) {
          signalMapping.put(remainingMapping, "9");
          signalMapping.put("9", remainingMapping);
        } else if (remainingMapping.length() == NUMBER_OF_SIGNALS_FOR_0_6_9) {
          signalMapping.put(remainingMapping, "0");
          signalMapping.put("0", remainingMapping);
        }
      }

      StringBuilder sb = new StringBuilder();
      for (String value : values) {
        String sortedValue = sortString(value);
        sb.append(signalMapping.get(sortedValue));
      }
      String outputValue = sb.toString();
//      System.out.println(outputValue);
      sumOfAllOutputs += Integer.parseInt(outputValue);
    }
    return sumOfAllOutputs;
  }

  private String sortString(String digit) {
    char[] chars = digit.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }

  boolean containsAll(String remainingMapping, char[] chars) {
    for (char c : chars) {
      boolean contains = remainingMapping.indexOf(c) != -1;
      if (!contains) {
        return false;
      }
    }
    return true;
  }
}
