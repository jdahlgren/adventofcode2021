package se.johannesdahlgren.adventofcode2021;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class Day22Test {

  @Test
  void part1Example1() {
    Day22 day22 = new Day22("day22Example1.txt");
    long numberIfCubesInOnState = day22.rebootInnerReactor(50);
    assertThat(numberIfCubesInOnState, Is.is(39L));
  }

  @Test
  void part1Example2() {
    Day22 day22 = new Day22("day22Example2.txt");
    long numberIfCubesInOnState = day22.rebootInnerReactor(50);
    assertThat(numberIfCubesInOnState, Is.is(590784L));
  }

  @Test
  void part1() {
    Day22 day22 = new Day22("day22.txt");
    long numberIfCubesInOnState = day22.rebootInnerReactor(50);
    assertThat(numberIfCubesInOnState, Is.is(546724L));
  }
}