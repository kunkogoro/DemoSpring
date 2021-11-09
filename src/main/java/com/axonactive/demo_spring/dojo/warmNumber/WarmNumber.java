package com.axonactive.demo_spring.dojo.warmNumber;

public class WarmNumber {

    public boolean isWarmNumber(int warmNumber){

        for (int i = 1; i < warmNumber; i++) {

        if(warmNumber%i == 0){
            if (warmNumber/i == i+1){
                return true;
            }
        }
        }

        return false;
    }

}
