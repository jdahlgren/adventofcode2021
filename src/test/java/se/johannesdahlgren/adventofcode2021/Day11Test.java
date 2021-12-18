package se.johannesdahlgren.adventofcode2021;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class Day11Test {

  @Test
  void part1Example1() {
    Day11 day11 = new Day11("day11Example1.txt");
    long flashes = day11.countFlashes(2);
    assertThat(flashes, Is.is(9L));
  }

  @Test
  void part1Example2() {
    Day11 day11 = new Day11("day11Example2.txt");
    long flashes = day11.countFlashes(100);
    assertThat(flashes, Is.is(1656L));
  }

  @Test
  void part1() {
    Day11 day11 = new Day11("day11.txt");
    long flashes = day11.countFlashes(100);
    assertThat(flashes, Is.is(1773L));
  }
}