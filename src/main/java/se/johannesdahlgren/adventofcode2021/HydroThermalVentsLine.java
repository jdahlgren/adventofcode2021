package se.johannesdahlgren.adventofcode2021;

public record HydroThermalVentsLine(int x1, int y1, int x2, int y2) {

  public boolean isHorizontal() {
    return this.x1() == this.x2();
  }

  public boolean isVertical() {
    return this.y1() == this.y2();
  }

  public boolean isDiagonal() {
    return !isHorizontal() && !isVertical();
  }
}
