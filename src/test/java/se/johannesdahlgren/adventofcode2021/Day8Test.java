package se.johannesdahlgren.adventofcode2021;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class Day8Test {

  @Test
  void part1Example(){
    Day8 day8 = new Day8("day8example.txt");
    int number = day8.getNumberOfTimesEasyNumbersAppear();
    assertThat(number, Is.is(26));
  }

  @Test
  void part1(){
    Day8 day8 = new Day8("day8.txt");
    int number = day8.getNumberOfTimesEasyNumbersAppear();
    assertThat(number, Is.is(397));
  }
}