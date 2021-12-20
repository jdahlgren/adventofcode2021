package se.johannesdahlgren.adventofcode2021;

import java.util.List;

public class Day20 {

  private final String algorithm;
  private char[][] inputImage;
  private int x;

  public Day20(String inputFileName) {
    List<String> lines = FileUtil.readFileToStringList(inputFileName);
    algorithm = lines.get(0);
    inputImage = FileUtil.linesToString2DArray(lines.subList(2, lines.size()));
  }

  public long enhanceTrenchMap(int numberOfEnhances) {
    for (x = 0; x < numberOfEnhances; x++) {
      char[][] infiniteMap = new char[inputImage[0].length + 4][inputImage.length + 4];
      for (int i = 0; i < infiniteMap[0].length; i++) {
        for (int j = 0; j < infiniteMap.length; j++) {
          if (i >= 2 && j >= 2 && i <= infiniteMap[0].length - 3 && j <= infiniteMap.length - 3) {
            infiniteMap[j][i] = inputImage[j - 2][i - 2];
          }
        }
      }
      char[][] nextMap = new char[infiniteMap[0].length][infiniteMap.length];
      for (int i = 0; i < infiniteMap[0].length; i++) {
        for (int j = 0; j < infiniteMap.length; j++) {
          String above =
              "" + getPixelOrDefault(infiniteMap, i - 1, j - 1) + getPixelOrDefault(infiniteMap, i - 1, j) + getPixelOrDefault(infiniteMap, i - 1,
                  j + 1);
          String mid =
              "" + getPixelOrDefault(infiniteMap, i, j - 1) + getPixelOrDefault(infiniteMap, i, j) + getPixelOrDefault(infiniteMap, i, j + 1);
          String below =
              "" + getPixelOrDefault(infiniteMap, i + 1, j - 1) + getPixelOrDefault(infiniteMap, i + 1, j) + getPixelOrDefault(infiniteMap, i + 1,
                  j + 1);
          String pixelString = above + mid + below;
          String binaryString = pixelString.replace('.', '0').replace('#', '1');
          int decimalNumber = Integer.parseInt(binaryString, 2);
          char newPixel = algorithm.charAt(decimalNumber);
          nextMap[i][j] = newPixel;
        }
      }

      inputImage = nextMap;
    }

    long litPixels = 0;
    for (int i = 1; i < inputImage[0].length - 2; i++) {
      for (int j = 1; j < inputImage.length - 2; j++) {
        if (inputImage[i][j] == '#') {
          litPixels++;
        }
      }
    }
    return litPixels;
  }

  private char getPixelOrDefault(char[][] currentMap, int i, int j) {
    char c = x % 2 == 0 ? '.' : algorithm.charAt(0);
    try {
      return currentMap[i][j] != 0 ? currentMap[i][j] : c;
    } catch (ArrayIndexOutOfBoundsException e) {
      return c;
    }
  }
}
