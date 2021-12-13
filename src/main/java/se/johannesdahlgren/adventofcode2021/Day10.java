package se.johannesdahlgren.adventofcode2021;

import java.util.List;
import java.util.Stack;
import lombok.Getter;

public class Day10 {

  private static final Character CHUNK_PARENTHESES_OPEN = '(';
  private static final Character CHUNK_PARENTHESES_CLOSE = ')';
  private static final int CHUNK_PARENTHESES_ERROR_SCORE = 3;

  private static final Character CHUNK_BRACKETS_OPEN = '[';
  private static final Character CHUNK_BRACKETS_CLOSE = ']';
  private static final int CHUNK_BRACKETS_ERROR_SCORE = 57;

  private static final Character CHUNK_CURLY_OPEN = '{';
  private static final Character CHUNK_CURLY_CLOSE = '}';
  private static final int CHUNK_CURLY_ERROR_SCORE = 1197;

  private static final Character CHUNK_DIAMOND_OPEN = '<';
  private static final Character CHUNK_DIAMOND_CLOSE = '>';
  private static final int CHUNK_DIAMOND_ERROR_SCORE = 25137;

  private static final List<Character> CHUNK_CLOSE = List.of(CHUNK_PARENTHESES_CLOSE, CHUNK_BRACKETS_CLOSE, CHUNK_CURLY_CLOSE, CHUNK_DIAMOND_CLOSE);

  private final List<String> navigationSystem;

  public Day10(String s) {
    navigationSystem = FileUtil.readFileToStringList(s);
  }

  public long calculateSyntaxErrorScore() {
    long totalScore = 0;
    for (String chunks : navigationSystem) {
      totalScore += getSyntaxErrorScoreForLine(chunks);
    }
    return totalScore;
  }

  private long getSyntaxErrorScoreForLine(String chunks) {
    Stack<Character> chunkChars = new Stack<>();
    for (char c : chunks.toCharArray()) {
      if (CHUNK_CLOSE.contains(c)) {
        Chunk chunk = Chunk.fromClose(c);
        if (chunk.open.equals(chunkChars.peek())) {
          chunkChars.pop();
        } else {
          return chunk.getErrorScore();
        }
      } else {
        chunkChars.push(c);
      }
    }
    // Incomplete Chunk, ignore
    return 0;
  }

  @Getter
  private enum Chunk {
    PARENTHESES(CHUNK_PARENTHESES_OPEN, CHUNK_PARENTHESES_CLOSE, CHUNK_PARENTHESES_ERROR_SCORE),
    BRACKETS(CHUNK_BRACKETS_OPEN, CHUNK_BRACKETS_CLOSE, CHUNK_BRACKETS_ERROR_SCORE),
    CURLY(CHUNK_CURLY_OPEN, CHUNK_CURLY_CLOSE, CHUNK_CURLY_ERROR_SCORE),
    DIAMOND(CHUNK_DIAMOND_OPEN, CHUNK_DIAMOND_CLOSE, CHUNK_DIAMOND_ERROR_SCORE);

    private final Character open;
    private final Character close;
    private final int errorScore;

    Chunk(Character open, Character close, int errorScore) {
      this.open = open;
      this.close = close;
      this.errorScore = errorScore;
    }

    static Chunk fromClose(Character close) {
      if (close.equals(CHUNK_PARENTHESES_CLOSE)) {
        return PARENTHESES;
      } else if (close.equals(CHUNK_BRACKETS_CLOSE)) {
        return BRACKETS;
      } else if (close.equals(CHUNK_CURLY_CLOSE)) {
        return CURLY;
      } else if (close.equals(CHUNK_DIAMOND_CLOSE)) {
        return DIAMOND;
      }
      throw new IllegalArgumentException("Not a valid closing char");
    }
  }
}
