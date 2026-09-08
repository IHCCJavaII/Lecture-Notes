package ImageChanger;

public class Coffee {
    private static final int MAX_INDEX = 3;
    private int index = 1;

    public String getImageString() {
        return "/coffie-" + index + ".png";
    }

    public void moveLeft() {
        if (index > 1) {
            index--;
        }
    }

    public void moveRight() {
        if (index < MAX_INDEX) {
            index++;
        }
    }
}
