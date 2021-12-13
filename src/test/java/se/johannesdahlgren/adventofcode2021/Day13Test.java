package se.johannesdahlgren.adventofcode2021;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

class Day13Test {

  @Test
  void part1Example() {
    Day13 day13 = new Day13("day13Example.txt");
    long dotsAfterFold = day13.foldOnce();
    assertThat(dotsAfterFold, Is.is(17L));
  }

  @Test
  void part1() {
    Day13 day13 = new Day13("day13.txt");
    long dotsAfterFold = day13.foldOnce();
    assertThat(dotsAfterFold, Is.is(781L));
  }

  @Test
  void part2Example() {
    Day13 day13 = new Day13("day13Example.txt");
    String code = day13.foldAll();
    String expectedNumber = """
        #####
        #   #
        #   #
        #   #
        #####
        """;
    assertThat(code, Is.is(expectedNumber));
  }

  @Test
  void part2() {
    Day13 day13 = new Day13("day13.txt");
    String code = day13.foldAll();
    String expectedNumber = """
        ###  #### ###   ##   ##    ## ###  ###\s
        #  # #    #  # #  # #  #    # #  # #  #
        #  # ###  #  # #    #       # #  # ###\s
        ###  #    ###  #    # ##    # ###  #  #
        #    #    # #  #  # #  # #  # #    #  #
        #    #### #  #  ##   ###  ##  #    ###\s
        """;
    assertThat(code, Is.is(expectedNumber));
  }
}