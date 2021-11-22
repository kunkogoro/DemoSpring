package com.axonactive.demo_spring.dojo.warmNumber;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WarmNumberTest {

    @Test
    void testIsWarmNumber() {
        WarmNumber w = new WarmNumber();
        assertTrue(w.isWarmNumber(20));
        assertFalse(w.isWarmNumber(10));
        assertFalse(w.isWarmNumber(2));
    }

    @Test
    void getListWarmNumber() {
        WarmNumber w = new WarmNumber();
//        assertTrue(w.getListWarmNumber(20).containsAll(List.of( 12, 20)));
//        List<Integer> emptyList = new ArrayList<>();
//        assertTrue(w.getListWarmNumber(5).containsAll(emptyList));
//        assertFalse(w.getListWarmNumber(20).containsAll(List.of(5, 6, 12, 20)));
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(6);
        list1.add(12);
        list1.add(20);
        assertEquals(list1, w.getListWarmNumber(20));
        assertEquals(list2, w.getListWarmNumber(5));
    }

}
