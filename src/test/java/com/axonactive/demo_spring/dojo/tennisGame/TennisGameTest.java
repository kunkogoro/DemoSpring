package com.axonactive.demo_spring.dojo.tennisGame;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TennisGameTest {

    @Test
    void testIsLoveAllWhenCreateTennisGame() {
        assertEquals("Thanh love:love Vien", new TennisGame("Thanh", "Vien").showResult());
        assertEquals("Huy love:love Ha", new TennisGame("Huy", "Ha").showResult());
        assertEquals("Ha love:love Vien", new TennisGame("Ha", "Vien").showResult());
    }

    @Test
    void testEatScore(){
        TennisGame tennisGame =  new TennisGame("Ha", "Vien");
        tennisGame.player1Score();
        assertEquals(1,tennisGame.getPlayer1Score());
        tennisGame.player1Score();
        assertEquals(2,tennisGame.getPlayer1Score());
        tennisGame.player1Score();
        assertEquals(3,tennisGame.getPlayer1Score());
    }

}
