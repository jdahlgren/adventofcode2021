package se.johannesdahlgren.adventofcode2021;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class Day4Test {

  @Test
  void part1Example() {
   Day4 day4 = new Day4("day4example.txt");
   var score = day4.playBingo();
   assertThat(score, Is.is(4512));
  }

  @Test
  void part1() {
    Day4 day4 = new Day4("day4.txt");
    var score = day4.playBingo();
    assertThat(score, Is.is(44736));
  }

  @Test
  void part2Example() {
    Day4 day4 = new Day4("day4example.txt");
    var score = day4.playBingoLastWinningBoard();
    assertThat(score, Is.is(1924));
  }

  @Test
  void part2() {
    Day4 day4 = new Day4("day4.txt");
    var score = day4.playBingoLastWinningBoard();
    assertThat(score, Is.is(1827));
  }
}