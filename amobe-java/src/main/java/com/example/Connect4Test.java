package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Connect4Test {

    @Test
    void testTablaInicializalas() {
        Connect4 game = new Connect4();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                assertEquals('.', game.getTabla()[i][j]);
            }
        }
    }

    @Test
    void testKorongLetesz_ValidColumn() {
        Connect4 game = new Connect4();
        assertTrue(game.korongLetesz(0, 'P'));
        assertTrue(game.korongLetesz(6, 'P'));
    }

    @Test
    void testKorongLetesz_InvalidColumn() {
        Connect4 game = new Connect4();
        assertFalse(game.korongLetesz(-1, 'P'));
        assertFalse(game.korongLetesz(7, 'P'));
    }

    @Test
    void testKorongLetesz_ColumnFull() {
        Connect4 game = new Connect4();
        for (int i = 0; i < 6; i++) {
            assertTrue(game.korongLetesz(0, 'P'));
        }
        assertFalse(game.korongLetesz(0, 'P'));
    }

    @Test
    void testEllenorizNyeres_Vizszintes() {
        Connect4 game = new Connect4();
        game.korongLetesz(0, 'P');
        game.korongLetesz(1, 'P');
        game.korongLetesz(2, 'P');
        game.korongLetesz(3, 'P');
        assertTrue(game.ellenorizNyeres('P'));
    }

    @Test
    void testEllenorizNyeres_Fuggoleges() {
        Connect4 game = new Connect4();
        for (int i = 0; i < 4; i++) {
            game.korongLetesz(0, 'P');
        }
        assertTrue(game.ellenorizNyeres('P'));
    }

    @Test
    void testEllenorizNyeres_Atlos() {
        Connect4 game = new Connect4();
        game.korongLetesz(0, 'P');

        game.korongLetesz(1, 'S');
        game.korongLetesz(1, 'P');

        game.korongLetesz(2, 'S');
        game.korongLetesz(2, 'S');
        game.korongLetesz(2, 'P');

        game.korongLetesz(3, 'S');
        game.korongLetesz(3, 'S');
        game.korongLetesz(3, 'S');
        game.korongLetesz(3, 'P');

        assertTrue(game.ellenorizNyeres('P'));
    }
    
    @Test
    void testEllenorizNyeres_Atlos_MasikIrany() {
        Connect4 game = new Connect4();

        game.korongLetesz(0, 'S');
        game.korongLetesz(0, 'S');
        game.korongLetesz(0, 'S');
        game.korongLetesz(0, 'P'); 

        game.korongLetesz(1, 'S');
        game.korongLetesz(1, 'S');
        game.korongLetesz(1, 'P');

        game.korongLetesz(2, 'S');
        game.korongLetesz(2, 'P');

        game.korongLetesz(3, 'P');

        assertTrue(game.ellenorizNyeres('P'));
    }

    @Test
    void testTeleAVanATabla() {
        Connect4 game = new Connect4();
        for (int col = 0; col < 7; col++) {
            for (int row = 0; row < 6; row++) {
                game.korongLetesz(col, 'P');
            }
        }
        assertTrue(game.teleAVanATabla());
    }

    @Test
    void testAiLepes() {
        Connect4 game = new Connect4();
        int oszlop = game.aiLepes();
        assertTrue(oszlop >= 0 && oszlop < 7);
        assertEquals('S', game.getTabla()[5][oszlop]);
    }
}
