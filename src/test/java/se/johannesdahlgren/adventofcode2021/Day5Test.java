package se.johannesdahlgren.adventofcode2021;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;

class Day5Test {

  @Test
  void part1Example() {
    Day5 day5 = new Day5("day5example.txt");
    var overlap = day5.getNumberOfPointsWithOverlap(2);
    assertThat(overlap, is(5L));
  }

  @Test
  void part1() {
    Day5 day5 = new Day5("day5.txt");
    var overlap = day5.getNumberOfPointsWithOverlap(2);
    assertThat(overlap, is(6572L));
  }

  @Test
  void part2Example() {
    Day5 day5 = new Day5("day5example.txt");
    var overlap = day5.getNumberOfPointsWithOverlapWithDiagonalLines(2);
    assertThat(overlap, is(12L));
  }

  @Test
  void part2() {
    Day5 day5 = new Day5("day5.txt");
    var overlap = day5.getNumberOfPointsWithOverlapWithDiagonalLines(2);
    assertThat(overlap, is(21466L));
  }
}