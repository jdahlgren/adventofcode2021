package se.johannesdahlgren.adventofcode2021;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

  private static Path getPath(String inputFile) {
    return Paths.get("src/main/resources/" + inputFile);
  }
}
