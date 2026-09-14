package tictactoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    Square[][] board = new Square[3][3];

    @Override
    public void start(Stage stage) {
        GridPane boardGrid = new GridPane();
        boardGrid.setId("board");
        boardGrid.setPadding(new Insets(20));
        boardGrid.setHgap(5);
        boardGrid.setVgap(5);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Square square = new Square();
                square.setId("square-" + row + "-" + col);
                board[row][col] = square;
                boardGrid.add(square, col, row);
            }
        }

        Scene scene = new Scene(boardGrid, 340, 340);
        stage.setTitle("Tic-Tac-Toe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}