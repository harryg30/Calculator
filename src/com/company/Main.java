package com.company;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ToubeauShawnD on 3/16/2017.
 */
public class Main extends Application{

    private TextField textField = new TextField();

    @Override
    public void start(Stage primarystage) throws Exception {
        List<String> buttons = Arrays.asList("7","8","9","DEL","CLEAR","4","5","6","^","Sqrt","1","2","3","+","-",".","0","=","X","/");

        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(30,20,30,20));
        pane.setHgap(5);
        pane.setVgap(5);

        textField.setEditable(false);
        textField.setAlignment(Pos.CENTER);
        textField.setMinSize(420, 40);

        pane.getChildren().add(textField);

        for (String button: buttons) {
            Button b = new Button(button);
            b.setMinSize(80,80);
            pane.getChildren().add(b);
            //b.setOnAction(e -> doSomething(b.getText()));
        }

        Scene scene = new Scene(pane,536,500);
        primarystage.setScene(scene);
        primarystage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
