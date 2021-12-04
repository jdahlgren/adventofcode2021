package se.johannesdahlgren.adventofcode2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Day4 {

  private final BingoGame bingoGame;

  public Day4(String input) {
    List<String> lines = FileUtil.readFileToStringList(input);
    List<Integer> drawnNumbers = Arrays.stream(lines.get(0).split(","))
        .map(Integer::valueOf)
        .toList();
    List<BingoBoard> bingoBoards = setupBingoBoards(lines);
    bingoGame = new BingoGame(drawnNumbers, bingoBoards);
  }

  public int playBingo() {
    for (Integer calledNumber : bingoGame.calledNumbers()) {
      markNumberOnBoards(calledNumber);
      Optional<BingoBoard> winnerBoard = getWinnerBoard(calledNumber);
      if (winnerBoard.isPresent()) {
        return calculateBingoScore(winnerBoard.get(), calledNumber);
      }
    }
    return 0;
  }

  public int playBingoLastWinningBoard() {
    for (Integer calledNumber : bingoGame.calledNumbers()) {
      markNumberOnBoards(calledNumber);
      Optional<BingoBoard> winnerBoard = getWinnerBoard(calledNumber);
      if (winnerBoard.isPresent() && bingoGame.hasAllBoardsWon()) {
        return calculateBingoScore(winnerBoard.get(), calledNumber);
      }
    }
    return 0;
  }

  private int calculateBingoScore(BingoBoard winnerBoard, Integer lastCalledNumber) {
    Integer sumOfUnmarkedNumbers = winnerBoard.getBingoNumberGrid().stream()
        .flatMap(Collection::stream)
        .filter(Predicate.not(BingoNumber::isMarked))
        .map(BingoNumber::getNumber)
        .reduce(Integer::sum)
        .orElse(0);
    return sumOfUnmarkedNumbers * lastCalledNumber;
  }

  private Optional<BingoBoard> getWinnerBoard(Integer calledNumber) {
    for (BingoBoard bingoBoard : bingoGame.bingoBoards()) {
      if (bingoBoard.hasBingo() && bingoBoard.getCallingNumberWhenBingo() == calledNumber) {
        return Optional.of(bingoBoard);
      }
    }
    return Optional.empty();
  }

  private void markNumberOnBoards(Integer calledNumber) {
    for (BingoBoard bingoBoard : bingoGame.bingoBoards()) {
      bingoBoard.markNumber(calledNumber);
    }
  }

  private List<BingoBoard> setupBingoBoards(List<String> lines) {
    List<BingoBoard> bingoBoards = new ArrayList<>();

    List<List<BingoNumber>> bingoGrid = new ArrayList<>();
    for (int i = 2; i < lines.size(); i++) {
      String line = lines.get(i);
      if (line.isBlank()) {
        bingoBoards.add(new BingoBoard(bingoGrid));
        bingoGrid = new ArrayList<>();
        continue;
      }

      List<BingoNumber> boardRow = Arrays.stream(line.split(" "))
          .filter(Predicate.not(String::isBlank))
          .map(Integer::valueOf)
          .map(BingoNumber::new)
          .toList();
      bingoGrid.add(boardRow);
    }
    bingoBoards.add(new BingoBoard(bingoGrid));
    return bingoBoards;
  }
}
