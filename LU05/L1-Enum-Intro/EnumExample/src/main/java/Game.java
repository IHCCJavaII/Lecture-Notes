public class Game {
    private int score;
    private int health;

    private String title;

//    we don't want to do this
//    private String Difficulty;

    private Difficulty difficulty;

    public Game(int score, int health, String title, Difficulty difficulty) {
        this.score = score;
        this.health = health;
        this.title = title;
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return title + ": " +
                "\n\tScore: " + score +
                "\n\tHealth: " + health +
                "\n\tDifficulty: " + difficulty.displayName;
    }
}
