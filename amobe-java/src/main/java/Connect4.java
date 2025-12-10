package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connect4 {

    private final char[][] tabla;
    public static final int ROWS = 6;
    public static final int COLS = 7;
    
    private static final Logger logger = Logger.getLogger(Connect4.class.getName());

    public Connect4() {
        tabla = new char[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                tabla[i][j] = '.';
            }
        }
    }

    public char[][] getTabla() {
        return tabla;
    }

    public void validalOszlop(int oszlop) throws InvalidStepException {
        if (oszlop < 0 || oszlop >= COLS) {
            throw new InvalidStepException("A megadott oszlop (" + oszlop + ") nem létezik!");
        }
        if (tabla[0][oszlop] != '.') {
            throw new InvalidStepException("Az oszlop (" + oszlop + ") megtelt!");
        }
    }

    public boolean korongLetesz(int oszlop, char jatekos) {
        if (oszlop < 0 || oszlop >= COLS) {
            logger.log(Level.WARNING, "Érvénytelen oszlop index próbálkozás: {0}", oszlop);
            return false;
        }

        for (int i = ROWS - 1; i >= 0; i--) {
            if (tabla[i][oszlop] == '.') {
                tabla[i][oszlop] = jatekos;
                return true;
            }
        }
        
        logger.log(Level.INFO, "A(z) {0}. oszlop tele van, sikertelen lépés.", oszlop);
        return false;
    }

    public boolean ellenorizNyeres(char jatekos) {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (tabla[i][j] == jatekos && tabla[i][j + 1] == jatekos &&
                        tabla[i][j + 2] == jatekos && tabla[i][j + 3] == jatekos) {
                    return true;
                }
            }
        }

        for (int j = 0; j < COLS; j++) {
            for (int i = 0; i < ROWS - 3; i++) {
                if (tabla[i][j] == jatekos && tabla[i + 1][j] == jatekos &&
                        tabla[i + 2][j] == jatekos && tabla[i + 3][j] == jatekos) {
                    return true;
                }
            }
        }

        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLS - 3; j++) {
                if (tabla[i][j] == jatekos && tabla[i + 1][j + 1] == jatekos &&
                        tabla[i + 2][j + 2] == jatekos && tabla[i + 3][j + 3] == jatekos) {
                    return true;
                }
            }
        }

        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 3; j < COLS; j++) {
                if (tabla[i][j] == jatekos && tabla[i + 1][j - 1] == jatekos &&
                        tabla[i + 2][j - 2] == jatekos && tabla[i + 3][j - 3] == jatekos) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean teleAVanATabla() {
        for (int j = 0; j < COLS; j++) {
            if (tabla[0][j] == '.') {
                return false;
            }
        }
        return true;
    }

    public int aiLepes() {
        for (int j = 0; j < COLS; j++) {
            if (korongLetesz(j, 'S')) {
                return j;
            }
        }
        return -1;
    }

    public void jatekMentese(String fajlnev) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fajlnev))) {
            writer.println("{");
            writer.println("  \"status\": \"active\",");
            writer.println("  \"tabla\": [");
            for (int i = 0; i < ROWS; i++) {
                writer.print("    [\"");
                for (int j = 0; j < COLS; j++) {
                    writer.print(tabla[i][j]);
                    if (j < COLS - 1) writer.print("\", \"");
                }
                writer.print("\"]");
                if (i < ROWS - 1) writer.println(","); else writer.println();
            }
            writer.println("  ]");
            writer.println("}");
            logger.info("Játékállás sikeresen mentve: " + fajlnev);
            System.out.println("Játék mentve a " + fajlnev + " fájlba.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Hiba a mentés során: {0}", e.getMessage());
        }
    }
}
