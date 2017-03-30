
//to get order of operations loop through looking for * then for / then for + and - evaluateing when the operation is found

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
import java.util.Stack;

/**
 * Created by ToubeauShawnD on 3/16/2017.
 */
public class Main extends Application{

    private TextField textField = new TextField();
    private ArrayList<Integer> nums = new ArrayList<>(); //individual button presses
    private ArrayList<Double> fullNums = new ArrayList<>(); //after user input is put together
    private ArrayList<String> operations = new ArrayList<>();//operations

    private Stack<String> input = new Stack<>();
    private Stack<String> operationStack = new Stack<>();
    private Stack<String> operandStack = new Stack<>();

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
            Exp ans = calculateNew(fullNums,operations);
            double temp= ans.getNums().get(0);
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
            double x = retNums(nums);
            nums.clear();
            setTextSqrt(x);
            fullNums.add(Math.sqrt(x));
        }
     }
     //combines all button presses up till operation into one number
     public double retNums(ArrayList<Integer> digits){
        double ret = 0;
        int place = 0;
        for(int i=digits.size()-1; i>=0; i--){
            ret += digits.get(i)*Math.pow(10,place);
            place++;
        }
        return ret;
     }

        //returns calculated answer as only element in either arraylist
     public Exp calculateNew(ArrayList<Double> numbers, ArrayList<String> operations){
         Exp exp = new Exp(numbers, operations);
         double temp=0;
         for(int i=0; i<operations.size(); i++){
             if(operations.get(i).equals("X")) {
                 temp = numbers.get(i) * numbers.get(i + 1);
                 exp.adjust(temp,i);
             }else if(operations.get(i).equals("/")){
                 temp = numbers.get(i)*numbers.get(i+1);
                 exp.adjust(temp,i);
             }
         }
         for(int i=0; i<operations.size(); i++){
             if(operations.get(i).equals("+")){
                 temp = numbers.get(i)+numbers.get(i+1);
                 exp.adjust(temp,i);
             }
             else if(operations.get(i).equals("-")){
                 temp = numbers.get(i)-numbers.get(i+1);
                 exp.adjust(temp,i);
             }
         }
         return exp;
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

     public void setTextSqrt(double x){
         System.out.print(x);
         StringBuilder newDisplay = new StringBuilder(textField.getText());
         StringBuilder s = new StringBuilder(Double.toString(x));
         s.delete(s.length()-2,s.length());
         int i = newDisplay.indexOf(s.toString());
         newDisplay.insert(i+s.length(),")");
         newDisplay.insert(i,"Sqrt(");
         textField.setText(newDisplay.toString());
     }



}
/*
   //NEEDS ORDER OF OPERATIONS
     public double calculate(ArrayList<Double> numbers, ArrayList<String> operations){
         double ret=numbers.get(0);
         //makeDecimals(fullNums, operations);
         for(int i=0; i<operations.size(); i++) {
             if (operations.get(i).equals("X")) {
                 ret *= numbers.get(i + 1);
             } else if (operations.get(i).equals("/")) {
                 ret /= numbers.get(i + 1);
             }
         }
         for(int i=0; i<operations.size(); i++) {
             if (operations.get(i).equals("+")) {
                 ret += numbers.get(i + 1);
             } else if (operations.get(i).equals("-")) {
                 ret -= numbers.get(i + 1);
             }
         }
         return ret;
     }
 */