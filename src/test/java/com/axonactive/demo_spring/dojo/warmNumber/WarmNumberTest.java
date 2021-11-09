package com.axonactive.demo_spring.dojo.warmNumber;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
        assertEquals(List.of(6, 12, 20), w.getListWarmNumber(20));
        assertEquals(List.of(), w.getListWarmNumber(5));
    }

}
