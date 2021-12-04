package se.johannesdahlgren.adventofcode2021;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Day3 {

  private final List<List<Integer>> diagnosticReport;

  public Day3(String input) {
    diagnosticReport = FileUtil.readFileToInteger2DList(input);
  }

  public int getPowerConsumption() {
    StringBuilder gammaRateBuilder = new StringBuilder();
    for (int i = 0; i < diagnosticReport.get(0).size(); i++) {
      int onCount = 0;
      for (List<Integer> ints : diagnosticReport) {
        if (ints.get(i) == 1) {
          onCount++;
        }
      }

      int significantBit = 0;
      if (onCount > diagnosticReport.size() / 2F) {
        significantBit = 1;
      }

      gammaRateBuilder.append(significantBit);
    }

    int gammaRate = Integer.parseInt(gammaRateBuilder.toString(), 2);
    int epsilonRate = Integer.parseInt(invertByteString(gammaRateBuilder.toString()), 2);

    return gammaRate * epsilonRate;
  }

  public int getLifeSupportRating() {

    String oxygenGeneratorRatingString = calculateRating(this::getMostCommonValue);
    String co2ScrubberRatingString = calculateRating(this::getLeastCommonValue);

    int oxygenGeneratorRating = Integer.parseInt(oxygenGeneratorRatingString, 2);
    int co2ScrubberRating = Integer.parseInt(co2ScrubberRatingString, 2);

    return oxygenGeneratorRating * co2ScrubberRating;
  }

  private String calculateRating(Function< List<Integer>, Integer> bitCriteria) {
    List<List<Integer>> currentReport = diagnosticReport;

    for (int i = 0; i < currentReport.get(0).size(); i++) {
      List<Integer> column = new ArrayList<>();
      for (List<Integer> row : currentReport) {
        column.add(row.get(i));
      }

      int valueSelectedByBitCriteria = bitCriteria.apply(column);
      currentReport = discardNonMatching(currentReport, valueSelectedByBitCriteria, i);
      if (currentReport.size() == 1) {
        break;
      }
    }

    StringBuilder stringBuilder = new StringBuilder();
    currentReport.get(0).forEach(stringBuilder::append);
    return stringBuilder.toString();
  }

  private List<List<Integer>> discardNonMatching(List<List<Integer>> currentReport, int matchingValue, int currentColumn) {
    List<List<Integer>> nextReport = new ArrayList<>();
    for (List<Integer> row : currentReport) {
      if (row.get(currentColumn) == matchingValue) {
        nextReport.add(row);
      }
    }
    return nextReport;
  }

  private int getMostCommonValue(List<Integer> ints) {
    double count = ints.stream().filter(i -> i == 1).count();
    int size = ints.size();
    if (count > size / 2F) {
      return 1;
    } else if (count < size / 2F) {
      return 0;
    } else {
      return 1;
    }
  }

  private int getLeastCommonValue(List<Integer> ints) {
    double count = ints.stream().filter(i -> i == 1).count();
    int size = ints.size();
    if (count > size / 2F) {
      return 0;
    } else if (count < size / 2F) {
      return 1;
    } else {
      return 0;
    }
  }

  private String invertByteString(String input) {
    StringBuilder invertedString = new StringBuilder();
    for (int i = 0; i < input.length(); i++) {
      invertedString.append(flipBit(input.charAt(i)));
    }
    return invertedString.toString();
  }

  private int flipBit(int character) {
    return Character.getNumericValue(character ^ 1);
  }
}
