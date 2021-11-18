package com.axonactive.demo_spring.dojo.tennisGame;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TennisGameTest {

    @Test
    void testIsLoveAllWhenCreateTennisGame() {
        assertEquals("Thanh love:love Vien", new TennisGame("Thanh", "Vien").showResult());
        assertEquals("Huy love:love Ha", new TennisGame("Huy", "Ha").showResult());
        assertEquals("Ha love:love Vien", new TennisGame("Ha", "Vien").showResult());
    }

    @Test
    void testGainScore(){
        TennisGame tennisGame =  new TennisGame("Ha", "Vien");
        tennisGame.player1Score();
        assertEquals(1,tennisGame.getPlayer1Score());
        tennisGame.player1Score();
        assertEquals(2,tennisGame.getPlayer1Score());
        tennisGame.player1Score();
        assertEquals(3,tennisGame.getPlayer1Score());
    }
    @Test
    void testIsEndGame(){
        TennisGame tennisGame =  new TennisGame("Ha", "Vien");
        tennisGame.player1Score();
        tennisGame.player1Score();
        assertFalse(tennisGame.isEndGame());
        tennisGame.player1Score();
        tennisGame.player1Score();
        assertTrue(tennisGame.isEndGame());
        TennisGame tennisGame1 =  new TennisGame("Vien", "Thanh");
        tennisGame1.player2Score();
        tennisGame1.player2Score();

        tennisGame1.player1Score();
        tennisGame1.player1Score();
        tennisGame1.player1Score();
        tennisGame1.player1Score();

        tennisGame1.player2Score();
        tennisGame1.player2Score();
        tennisGame1.player2Score();
        tennisGame1.player2Score();
        assertTrue(tennisGame1.isEndGame());
    }
    @Test
    void testGetWinner(){
        TennisGame tennisGame =  new TennisGame("Ha", "Vien");
        tennisGame.player1Score();
        tennisGame.player1Score();
        tennisGame.player1Score();
        tennisGame.player1Score();
        assertEquals("Ha",tennisGame.getWinner());
    }
    @Test
    void testIsDeuce(){
        TennisGame tennisGame =  new TennisGame("Ha", "Vien");
        tennisGame.player1Score();
        tennisGame.player1Score();

        tennisGame.player2Score();

        tennisGame.player1Score();

        tennisGame.player2Score();
        tennisGame.player2Score();

        tennisGame.player1Score();

        tennisGame.player2Score();
        assertTrue(tennisGame.isDuece());
    }
}
