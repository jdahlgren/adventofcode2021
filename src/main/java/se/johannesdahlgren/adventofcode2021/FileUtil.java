package se.johannesdahlgren.adventofcode2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;

public class FileUtil {

  @SneakyThrows
  public static List<Integer> readFileToIntList(String inputFile) {
    Path path = getPath(inputFile);
    return Files.readAllLines(path).stream().map(Integer::valueOf).toList();
  }

  @SneakyThrows
  public static List<String> readFileToStringList(String inputFile) {
    Path path = getPath(inputFile);
    return Files.readAllLines(path);
  }

  @SneakyThrows
  public static List<List<Integer>> readFileToInteger2DList(String inputFile) {
    Path path = getPath(inputFile);
    List<String> lines = Files.readAllLines(path);
    List<List<Integer>> twoDimensionalIntList = new ArrayList<>();
    for (String line : lines) {
      List<Integer> row = new ArrayList<>();
      for (int j = 0; j < line.length(); j++) {
        int rowValue = Character.getNumericValue(line.charAt(j));
        row.add(rowValue);
//        System.out.print(rowValue);
      }
      twoDimensionalIntList.add(row);
//      System.out.println();
    }
    return twoDimensionalIntList;
  }

  private static Path getPath(String inputFile) {
    return Paths.get("src/main/resources/" + inputFile);
  }
}
