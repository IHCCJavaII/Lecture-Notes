package crystals;

import java.util.EnumMap;
import java.util.Map;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {

    private final Label status = new Label();
    private final Label scoreLabel = new Label("Score: 0");
    private final Button startButton = new Button("Start");
    // TODO remove the need for this, just enable/disable the buttons when needed
    private final Map<Tone, Button> buttonsByTone = new EnumMap<>(Tone.class);
    private final PauseTransition highlightTimer = new PauseTransition(Duration.millis(350));
    private AudioClip crystalSound;
    private Puzzle puzzle;
    private int inputIndex;

    @Override
    public void start(Stage stage) {
        // Load the crystal sound effect
        crystalSound = new AudioClip(getClass().getResource("/crystal.wav").toExternalForm());

        // Create the 5 tone buttons
        HBox toneButtons = new HBox(12);
        for (Tone tone : Tone.values()) {
            Button button = new Button(tone.name());
            button.setPrefSize(125, 125);
            button.setWrapText(true);
            button.setStyle("-fx-background-color: " + tone.getHexColor()
                    + "; -fx-text-fill: white;");
            button.setOnAction(event -> handleToneInput(tone));
            button.setDisable(true);
            toneButtons.getChildren().add(button);
            buttonsByTone.put(tone, button);
        }

        // When uer clicks the start button, start a new game
        startButton.setOnAction(event -> startGame());
        VBox root = new VBox(24, scoreLabel, startButton, toneButtons, status);

        stage.setTitle("Crystals Puzzle");
        stage.setScene(new Scene(root, 800, 300));
        stage.show();
    }

    private void startGame() {
        // create 3 random tones
        puzzle = new Puzzle();
        scoreLabel.setText("Score: 0");
        startButton.setDisable(true);
        status.setText("Watch the sequence.");
        // Play the sequence of tones
        playSequence(0);
    }

    // TODO: We can't just loop through the tones because we need to wait
    // for the sound to finish playing.
    private void playSequence(int sequenceIndex) {
        // So the user can't interrupt
        setToneButtonsDisabled(true);

        // Why do we need this?
        if (sequenceIndex == puzzle.getTones().size()) {
            inputIndex = 0;
            setToneButtonsDisabled(false);
            status.setText("Your turn: repeat the sequence.");
            return;
        }

        playTone(puzzle.getTones().get(sequenceIndex));
        PauseTransition pause = new PauseTransition(Duration.millis(650));
        pause.setOnFinished(event -> playSequence(sequenceIndex + 1));
        pause.play();
    }

    private void handleToneInput(Tone tone) {
        playTone(tone);

        // Check if the user clicked the correct tone in the sequence
        if (tone != puzzle.getTones().get(inputIndex)) {
            setToneButtonsDisabled(true);
            startButton.setDisable(false);
            status.setText("Game over! Final score: " + puzzle.getScore()
                    + ". Press Start to play again.");
            return;
        }

        inputIndex++;
        // If the user has completed the sequence
        if (inputIndex == puzzle.getTones().size()) {
            setToneButtonsDisabled(true);
            puzzle.completeRound();
            scoreLabel.setText("Score: " + puzzle.getScore());
            status.setText("Correct! Watch the longer sequence.");
            PauseTransition pause = new PauseTransition(Duration.millis(700));
            pause.setOnFinished(event -> playSequence(0));
            pause.play();
        }
    }

    private void setToneButtonsDisabled(boolean disabled) {
        buttonsByTone.values().forEach(button -> button.setDisable(disabled));
    }

    private void playTone(Tone tone) {
        // TODO simplify the highlight code, just use a simple style class or something
        highlightTimer.stop();
        buttonsByTone.values().forEach(button -> button.setEffect(null));
        Button playingButton = buttonsByTone.get(tone);
        playingButton.setEffect(new DropShadow(24, Color.WHITE));
        highlightTimer.setOnFinished(event -> playingButton.setEffect(null));
        highlightTimer.playFromStart();

        crystalSound.stop();
        crystalSound.setRate(tone.getPlaybackRate());
        crystalSound.play();
    }

    public static void main(String[] args) {
        launch();
    }

}