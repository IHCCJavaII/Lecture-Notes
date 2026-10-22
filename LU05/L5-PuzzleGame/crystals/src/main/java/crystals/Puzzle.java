package crystals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Puzzle {
    private final List<Tone> tones = new ArrayList<>();
    private final Random random = new Random();
    private int score;

    public Puzzle() {
        // Puzzle starts with 3 random tones
        for (int i = 0; i < 3; i++) {
            addRandomTone();
        }
    }

    public List<Tone> getTones() {
        // TODO return an unmodifiable list of tones
        // return tones;
        return Collections.unmodifiableList(tones);
    }

    public int getScore() {
        return score;
    }

    public void completeRound() {
        score++;
        addRandomTone();
    }

    private void addRandomTone() {
        // DON'T DO THIS
        // String[] tones = {"PURPLE", "GREEN", "RED", "BLUE", "YELLOW"};

        // Available tones
        Tone[] availableTones = Tone.values();
        // Add one new random tone to the sequence
        tones.add(availableTones[random.nextInt(availableTones.length)]);
    }
}
