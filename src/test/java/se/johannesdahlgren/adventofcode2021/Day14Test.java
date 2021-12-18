package se.johannesdahlgren.adventofcode2021;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class Day14Test {

  @Test
  void part1Example() {
    Day14 day14 = new Day14("day14Example.txt");
    long value = day14.mostCommonMinusLeastCommonElementAfter(10);
    assertThat(value, Is.is(1588L));
  }

  @Test
  void part1() {
    Day14 day14 = new Day14("day14.txt");
    long value = day14.mostCommonMinusLeastCommonElementAfter(10);
    assertThat(value, Is.is(2621L));
  }
}