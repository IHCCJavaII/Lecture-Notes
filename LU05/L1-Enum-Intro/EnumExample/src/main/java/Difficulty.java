public enum Difficulty {
    EASY("Normal"),
    NORMAL("Heroic"),
    HARD("Legendary");

    final String displayName;

    Difficulty(String displayNam) {
        this.displayName = displayNam;
    }
}
