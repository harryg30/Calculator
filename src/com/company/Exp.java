package com.company;

import java.util.ArrayList;

/**
 * Created by gordensteinh on 3/29/2017.
 */
public class Exp {
    ArrayList<Double> nums= new ArrayList<>();
    ArrayList<String> ops = new ArrayList<>();

    public Exp(ArrayList<Double> nums, ArrayList<String> ops){
        this.nums= nums;
        this.ops = ops;
    }
    public Exp(){

    }

    public void setNums(ArrayList<Double> nums){
        this.nums= nums;
    }

    public ArrayList<Double> getNums(){
        return nums;
    }

    public void setOps(ArrayList<String> ops) {
        this.ops = ops;
    }

    public ArrayList<String> getOps() {
        return ops;
    }
    public void adjust(double num, int index){
        nums.remove(index+1);
        ops.remove(index);
        nums.set(index, num);
    }


}
