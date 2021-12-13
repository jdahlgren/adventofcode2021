package se.johannesdahlgren.adventofcode2021;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class Day10Test {

  @Test
  void part1Example() {
    Day10 day10 = new Day10("day10Example.txt");
    long syntaxErrorScore = day10.calculateSyntaxErrorScore();
    assertThat(syntaxErrorScore, Is.is(26397L));
  }

  @Test
  void part1() {
    Day10 day10 = new Day10("day10.txt");
    long syntaxErrorScore = day10.calculateSyntaxErrorScore();
    assertThat(syntaxErrorScore, Is.is(290691L));
  }

  @Test
  void part2Example() {
    Day10 day10 = new Day10("day10Example.txt");
    long syntaxErrorScore = day10.calculateAutoCompleteScore();
    assertThat(syntaxErrorScore, Is.is(288957L));
  }

  @Test
  void part2() {
    Day10 day10 = new Day10("day10.txt");
    long syntaxErrorScore = day10.calculateAutoCompleteScore();
    assertThat(syntaxErrorScore, Is.is(2768166558L));
  }
}