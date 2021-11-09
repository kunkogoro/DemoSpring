package com.axonactive.demo_spring.dojo.warmNumber;

import java.util.ArrayList;
import java.util.List;

public class WarmNumber {

    public boolean isWarmNumber(int warmNumber) {
        if (warmNumber < 3) return false;
        for (int i = 1; i < warmNumber / 2; i++)
            if (i * (i + 1) == warmNumber)
                return true;
        return false;
    }

    public List<Integer> getListWarmNumber(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 3; i <= n; i++)
            if (isWarmNumber(i)) result.add(i);
        return result;
    }

}
