package crystals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PuzzleTest {

    @Test
    void startsWithThreeRandomTonesAndZeroScore() {
        Puzzle puzzle = new Puzzle();

        assertEquals(3, puzzle.getTones().size());
        assertEquals(0, puzzle.getScore());
        assertTrue(puzzle.getTones().stream().allMatch(tone -> tone != null));
    }

    @Test
    void completingRoundAddsOneToneAndOnePoint() {
        Puzzle puzzle = new Puzzle();

        puzzle.completeRound();

        assertEquals(4, puzzle.getTones().size());
        assertEquals(1, puzzle.getScore());

        puzzle.completeRound();

        assertEquals(5, puzzle.getTones().size());
        assertEquals(2, puzzle.getScore());
    }

    @Test
    void toneSequenceCannotBeModifiedFromOutsidePuzzle() {
        Puzzle puzzle = new Puzzle();

        assertThrows(UnsupportedOperationException.class,
                () -> puzzle.getTones().clear());
    }
}
