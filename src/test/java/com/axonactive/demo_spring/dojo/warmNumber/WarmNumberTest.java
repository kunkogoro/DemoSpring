package com.axonactive.demo_spring.dojo.warmNumber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WarmNumberTest {

    @Test
    void testIsWarmNumber(){
        WarmNumber w = new WarmNumber();

        assertEquals(true,w.isWarmNumber(20));
        assertEquals(false,w.isWarmNumber(10));

    }

}
