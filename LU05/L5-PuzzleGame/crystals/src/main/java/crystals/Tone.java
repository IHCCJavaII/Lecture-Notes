package crystals;

public enum Tone {
    PURPLE("#8e44ad", 0.75),
    GREEN("#27ae60", 0.84),
    RED("#e74c3c", 0.94),
    BLUE("#2980b9", 1.06),
    YELLOW("#f1c40f", 1.19);

    private final String hexColor;
    private final double playbackRate;

    Tone(String hexColor, double playbackRate) {
        this.hexColor = hexColor;
        this.playbackRate = playbackRate;
    }

    public String getHexColor() {
        return hexColor;
    }

    public double getPlaybackRate() {
        return playbackRate;
    }
}
