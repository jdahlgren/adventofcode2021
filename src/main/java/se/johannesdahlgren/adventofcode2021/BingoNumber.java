package se.johannesdahlgren.adventofcode2021;

import lombok.Getter;
import lombok.Setter;

@Getter
public final class BingoNumber {

  private final int number;
  @Setter
  private boolean isMarked;

  public BingoNumber(int number) {
    this.number = number;
  }
}
