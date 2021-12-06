package se.johannesdahlgren.adventofcode2021;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class Day6Test {

  @Test
  void part1Example1() {
    Day6 day6 = new Day6("day6example.txt");
    long numberOfLanternFishAfterDays = day6.spawnLanternFish(18);
    assertThat(numberOfLanternFishAfterDays, Is.is(26L));
  }

  @Test
  void part1Example2() {
    Day6 day6 = new Day6("day6example.txt");
    long numberOfLanternFishAfterDays = day6.spawnLanternFish(80);
    assertThat(numberOfLanternFishAfterDays, Is.is(5934L));
  }

  @Test
  void part1() {
    Day6 day6 = new Day6("day6.txt");
    long numberOfLanternFishAfterDays = day6.spawnLanternFish(80);
    assertThat(numberOfLanternFishAfterDays, Is.is(373378L));
  }

}