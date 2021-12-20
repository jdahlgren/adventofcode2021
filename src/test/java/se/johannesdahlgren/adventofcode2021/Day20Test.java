package se.johannesdahlgren.adventofcode2021;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class Day20Test {

  @Test
  void part1Example() {
    Day20 day20 = new Day20("day20Example.txt");
    long litPixels = day20.enhanceTrenchMap(2);
    assertThat(litPixels, Is.is(35L));
  }

  @Test
  void part1() {
    Day20 day20 = new Day20("day20.txt");
    long litPixels = day20.enhanceTrenchMap(2);
    assertThat(litPixels, Is.is(5395L));
  }

}