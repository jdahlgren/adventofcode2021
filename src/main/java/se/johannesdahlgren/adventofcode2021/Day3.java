package se.johannesdahlgren.adventofcode2021;

import java.util.ArrayList;
import java.util.List;

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
