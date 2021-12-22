package se.johannesdahlgren.adventofcode2021;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class Day21Test {

  @Test
  void part1Example() {
    String input = """
        Player 1 starting position: 4
        Player 2 starting position: 8
        """;
    Day21 day21 = new Day21(input);
    long losingScoreTimesNumberOfRolls = day21.playDiracDice();
    assertThat(losingScoreTimesNumberOfRolls, Is.is(739785L));
  }

  @Test
  void part1() {
    String input = """
        Player 1 starting position: 3
        Player 2 starting position: 7
        """;
    Day21 day21 = new Day21(input);
    long losingScoreTimesNumberOfRolls = day21.playDiracDice();
    assertThat(losingScoreTimesNumberOfRolls, Is.is(1006866L));
  }
}