package tictactoe;

import javafx.scene.control.Button;

public class Square extends Button {

    private static String currentPlayer = "X";
    private String value;

    public Square() {
        this.value = "";

        setText(value);
        setPrefSize(100, 100);
        setFocusTraversable(false);
        setStyle(
                "-fx-font-size: 32px; -fx-font-weight: bold; -fx-background-color: white; -fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 0;");

        setOnAction(event -> handleSquareClick());
    }

    public void handleSquareClick() {
        System.out.println("Square clicked: " + this);
        // TODO switch X to O and O to X when a square is clicked
    }
}
