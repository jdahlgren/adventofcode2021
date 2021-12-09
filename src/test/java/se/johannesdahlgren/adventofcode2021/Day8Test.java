package se.johannesdahlgren.adventofcode2021;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class Day8Test {

  @Test
  void part1Example() {
    Day8 day8 = new Day8("day8example.txt");
    int number = day8.getNumberOfTimesEasyNumbersAppear();
    assertThat(number, Is.is(26));
  }

  @Test
  void part1() {
    Day8 day8 = new Day8("day8.txt");
    int number = day8.getNumberOfTimesEasyNumbersAppear();
    assertThat(number, Is.is(397));
  }

  @Test
  void part2Example() {
    Day8 day8 = new Day8("day8example2.txt");
    int number = day8.getNumberOutput();
    assertThat(number, Is.is(5353));
  }

  @Test
  void part2Example2() {
    Day8 day8 = new Day8("day8example.txt");
    int number = day8.getNumberOutput();
    assertThat(number, Is.is(61229));
  }

  @Test
  void part2() {
    Day8 day8 = new Day8("day8.txt");
    int number = day8.getNumberOutput();
    assertThat(number, Is.is(1027422));
  }
}