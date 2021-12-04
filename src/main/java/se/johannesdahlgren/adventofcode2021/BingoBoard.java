package se.johannesdahlgren.adventofcode2021;

import java.util.Collection;
import java.util.List;
import lombok.Getter;

@Getter
public final class BingoBoard {

  private final List<List<BingoNumber>> bingoNumberGrid;

  public BingoBoard(List<List<BingoNumber>> bingoNumberGrid) {
    this.bingoNumberGrid = bingoNumberGrid;
  }

  public boolean hasBingo() {
    return checkRowBingo() || checkColumnBingo();
  }

  public void markNumber(Integer calledNumber) {
    bingoNumberGrid.stream()
        .flatMap(Collection::stream)
        .filter(bingoNumber -> bingoNumber.getNumber() == calledNumber)
        .forEach(bingoNumber -> bingoNumber.setMarked(true));
  }

  private boolean checkRowBingo() {
    return bingoNumberGrid.stream()
        .anyMatch(row -> row.stream().allMatch(BingoNumber::isMarked));
  }

  private boolean checkColumnBingo() {
    for (int i = 0; i < bingoNumberGrid.get(0).size(); i++) {
      int numberOfMarkedInColumn = 0;
      for (List<BingoNumber> bingoNumberRow : bingoNumberGrid) {
        if (bingoNumberRow.get(i).isMarked()) {
          numberOfMarkedInColumn++;
        }
        if (numberOfMarkedInColumn == bingoNumberGrid.size()) {
          return true;
        }
      }
    }
    return false;
  }
}
