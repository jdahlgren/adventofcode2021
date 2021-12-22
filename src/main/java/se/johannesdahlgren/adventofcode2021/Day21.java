package se.johannesdahlgren.adventofcode2021;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class Day21 {


  List<Player> players = new ArrayList<>();

  public Day21(String input) {
    Pattern pattern = Pattern.compile("position: (\\d+)");
    Matcher matcher = pattern.matcher(input);
    while (matcher.find()) {
      int startPos = Integer.parseInt(matcher.group(1));
      players.add(new Player(0, startPos));
    }
  }

  public long playDiracDice() {
    DeterministicDice deterministicDice = new DeterministicDice(100);
    int turn = 0;
    int rollsPerTurn = 3;
    int totalRolls = 0;
    while (players.stream().noneMatch(p -> p.score >= 1000)) {
      int rollSum = deterministicDice.rollDice(rollsPerTurn);
      int newPos = (players.get(turn).pos + rollSum - 1) % 10 + 1;
      players.get(turn).pos = newPos;
      players.get(turn).score += newPos;
      turn = (turn + 1) % 2;
      totalRolls += rollsPerTurn;
    }
    Integer minScore = players.stream().map(Player::getScore).min(Integer::compareTo).orElse(0);
    return (long) minScore * totalRolls;
  }

  @AllArgsConstructor
  @Getter
  static final class Player {

    private int score;
    private int pos;
  }

  static final class DeterministicDice {

    private final List<Integer> allDiceRolls;
    private int currentIndex = 0;

    DeterministicDice(int totalRolls) {
      allDiceRolls = IntStream.rangeClosed(1, totalRolls).boxed().toList();
    }

    private int rollDice(int rolls) {
      int rollSum = 0;
      for (int j = currentIndex; j < currentIndex + rolls; j++) {
        rollSum += allDiceRolls.get(j % 100);
      }
      currentIndex = (currentIndex + rolls ) % 100;
      return rollSum;
    }

  }
}
