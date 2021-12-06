package se.johannesdahlgren.adventofcode2021;

import java.util.Optional;

public class LanternFish {

  private static final int FIRST_CYCLE_ADDITION = 2;
  private static final int INTERNAL_TIMER_RESET = 6;
  private int internalTimer;

  public LanternFish(int initialInternalTimer) {
    internalTimer = initialInternalTimer;
  }

  public Optional<LanternFish> age() {
    internalTimer--;
    if (internalTimer < 0) {
      internalTimer = INTERNAL_TIMER_RESET;
      return Optional.of(new LanternFish(INTERNAL_TIMER_RESET + FIRST_CYCLE_ADDITION));
    }
    return Optional.empty();
  }
}
