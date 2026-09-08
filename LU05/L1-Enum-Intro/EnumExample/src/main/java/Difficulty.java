public enum Difficulty {
    EASY("Recruit"),
    NORMAL("Regular"),
    HARD("Harneded");

    final String displayName;

    Difficulty(String displayNam){
        this.displayName = displayNam;
    }
}
