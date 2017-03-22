package com.company;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ToubeauShawnD on 3/16/2017.
 */
public class Main extends Application{

    private TextField textField = new TextField();
    static ArrayList<Integer> nums = new ArrayList<Integer>(); //individual button presses
    static ArrayList<Double> fullNums = new ArrayList<Double>(); //after user input is put together
    static ArrayList<String> operations = new ArrayList<String>();//operations

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
            b.setOnAction(e -> doSomething(b.getText()));
        }

        Scene scene = new Scene(pane,536,500);
        primarystage.setScene(scene);
        primarystage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public static void doSomething(String s){

        if(s.equals("0")||s.equals("1")||s.equals("2")||s.equals("3")||s.equals("4")||s.equals("5")||s.equals("6")||s.equals("7")||
                s.equals("8")||s.equals("9")){
           nums.add(Integer.parseInt(s));
        }
        else if(s.equals("+")||s.equals("-")||s.equals("/")||s.equals("X")){
            fullNums.add(retNums(nums));
            nums.clear();
        }
        else if(s.equals("=")){

        }
     }
     //combuines all button presses up till operation into one number
     public static double retNums(ArrayList<Integer> digits){
        double ret = 0;
        int place = 0;
        for(int i=digits.size()-1; i>=0; i--){
            ret += digits.get(i)*Math.pow(10,place);
            place++;
        }
        return ret;
     }
}
