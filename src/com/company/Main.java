package com.company;

import com.sun.xml.internal.bind.v2.TODO;
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
    private ArrayList<Integer> nums = new ArrayList<Integer>(); //individual button presses
    private ArrayList<Double> fullNums = new ArrayList<Double>(); //after user input is put together
    private ArrayList<String> operations = new ArrayList<String>();//operations

    @Override
    public void start(Stage primarystage) throws Exception {
        List<String> buttons = Arrays.asList("7","8","9","DEL","CLEAR","4","5","6","^","Sqrt","1","2","3","+","-",".","0","=","X","/");

        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(30,20,30,20));
        pane.setHgap(5);
        pane.setVgap(5);

        //textField.setEditable(false);
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

    //handles the button presses
    public void doSomething(String s){

        if(s.equals("0")||s.equals("1")||s.equals("2")||s.equals("3")||s.equals("4")||s.equals("5")||s.equals("6")||s.equals("7")||
                s.equals("8")||s.equals("9")){
           nums.add(Integer.parseInt(s));
            textField.setText(textField.getText()+s);
        }
        else if(s.equals("+")||s.equals("-")||s.equals("/")||s.equals("X")){
            if(nums.size()!=0){
                fullNums.add(retNums(nums));
        }
            nums.clear();
            operations.add(s);
            textField.setText(textField.getText()+s);
        }
        else if(s.equals("=")){
            fullNums.add(retNums(nums));
            double temp = calculate(fullNums,operations);
            textField.setText(Double.toString(temp));
            nums.clear();
            fullNums.clear();
            operations.clear();
            fullNums.add(temp);
        }
        else if(s.equals("CLEAR")){
            nums.clear();
            fullNums.clear();
            operations.clear();
            textField.clear();
        }
        else if(s.equals("DEL")){
           textField.setText("You can't do that");
        }
        else if(s.equals(".")){

            fullNums.add(retNums(nums));
            nums.clear();
            operations.add(s);
            textField.setText(textField.getText()+s);
        }
        else if(s.equals("Sqrt")){
            fullNums.add(retNums(nums));
            nums.clear();
            operations.add(s);

        }
     }
     //combuines all button presses up till operation into one number
     public double retNums(ArrayList<Integer> digits){
        double ret = 0;
        int place = 0;
        for(int i=digits.size()-1; i>=0; i--){
            ret += digits.get(i)*Math.pow(10,place);
            place++;
        }
        return ret;
     }

        //returns calculated answer
        //NEEDS ORDER OF OPERATIONS
     public double calculate(ArrayList<Double> numbers, ArrayList<String> operations){
         double ret=numbers.get(0);
         makeDecimals(fullNums, operations);
         for(int i=0; i<operations.size(); i++){
             if(operations.get(i).equals("+")){
                 ret += numbers.get(i+1);
             }else if(operations.get(i).equals("-")){
                 ret -= numbers.get(i+1);
             }else if(operations.get(i).equals("X")){
                 ret *= numbers.get(i+1);
             }else if(operations.get(i).equals("/")){
                 ret /= numbers.get(i+1);
             }
         }
         return ret;
     }

     //DOESNT WORK RIGHT YET
     public void makeDecimals(ArrayList<Double> numbers, ArrayList<String> operations){
         double decimal;
         double ret;
         for(int i=0; i<operations.size(); i++){
             if(operations.get(i).equals(".")) {
                 decimal = numbers.get(i+1);
                 while (decimal > 1) {
                     decimal *= 0.1;
                 }
                 decimal+=numbers.get(i);
                 fullNums.remove(i);
                 fullNums.remove(i-1);
                 operations.remove(i);
                 fullNums.add(i-1, decimal);


             }
         }


     }



}
