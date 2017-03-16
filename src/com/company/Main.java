package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by ToubeauShawnD on 3/16/2017.
 */
public class Main extends Application{

    @Override
    public void start(Stage primarystage) throws Exception {
        GridPane grid = new GridPane();
        //
        Scene scene = new Scene(grid, 500, 500);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                Button btn = new Button();
                btn.setText("yes");
                btn.setMaxWidth(20);
                btn.setMaxHeight(10);

                GridPane.setRowIndex(btn, i);
                GridPane.setColumnIndex(btn, j);
                grid.getChildren().add(new Button("YES"));
            }
        }
        primarystage.setScene(scene);
        primarystage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
