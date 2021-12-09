package se.johannesdahlgren.adventofcode2021;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class Day9Test {

  @Test
  void part1Example() {
    Day9 day9 = new Day9("day9example.txt");
    var riskLevel = day9.getRiskLevelsSum();
    assertThat(riskLevel, Is.is(15L));
  }

  @Test
  void part1() {
    Day9 day9 = new Day9("day9.txt");
    var riskLevel = day9.getRiskLevelsSum();
    assertThat(riskLevel, Is.is(468L));
  }

  @Test
  void part2Example() {
    Day9 day9 = new Day9("day9example.txt");
    var riskLevel = day9.getBasinSizes();
    assertThat(riskLevel, Is.is(1134L));
  }

  @Test
  void part2() {
    Day9 day9 = new Day9("day9.txt");
    var riskLevel = day9.getBasinSizes();
    assertThat(riskLevel, Is.is(1280496L));
  }

}