package se.johannesdahlgren.adventofcode2021;

import java.util.List;

public record BingoGame(List<Integer> calledNumbers, List<BingoBoard> bingoBoards) {

  public boolean hasAllBoardsWon() {
    return bingoBoards.stream()
        .allMatch(BingoBoard::hasBingo);
  }
}
