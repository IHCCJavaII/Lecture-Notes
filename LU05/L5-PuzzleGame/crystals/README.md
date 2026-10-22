# Crystal Cave Game

This is a simad says puzzle game.
Each button plays back a sound.
The game plays a sequence of sounds, and the player must repeat the sequence.

![Example Screenshot](screenshot.png)

## Creating a New Game

```bash
mvn archetype:generate -DarchetypeGroupId=org.openjfx -DarchetypeArtifactId=javafx-archetype-simple
```

- Build the [`tone.java`](src/main/java/crystals/Tone.java) enum to store the 5 different tones.
- Build the [`Puzzle.java`](src/main/java/crystals/Puzzle.java) class to store the sequence of tones.

## Adding Tests
