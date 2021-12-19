package se.johannesdahlgren.adventofcode2021;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class Day17Test {

  @Test
  void part1Example() {
    Day17 day17 = new Day17("target area: x=20..30, y=-10..-5");
    int maxY = day17.findMaxY();
    assertThat(maxY, Is.is(45));
  }

  @Test
  void part1() {
    Day17 day17 = new Day17("target area: x=287..309, y=-76..-48");
    int maxY = day17.findMaxY();
    assertThat(maxY, Is.is(2850));
  }

  @Test
  void part2Example() {
    Day17 day17 = new Day17("target area: x=20..30, y=-10..-5");
    int numberOfHits = day17.howManyInitialVelocitiesHits();
    assertThat(numberOfHits, Is.is(112));
  }

  @Test
  void part2() {
    Day17 day17 = new Day17("target area: x=287..309, y=-76..-48");
    int maxY = day17.howManyInitialVelocitiesHits();
    assertThat(maxY, Is.is(1117));
  }
}