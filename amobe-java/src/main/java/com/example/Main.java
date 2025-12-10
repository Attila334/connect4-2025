package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connect4 game = new Connect4();
        Scanner scanner = new Scanner(System.in);
        char aktualisJatekos = 'P';
        boolean jatekFut = true;

        System.out.println("=== CONNECT 4 JÁTÉK ===");
        System.out.println("Parancsok: 0-6 (lépés), save (mentés), exit (kilépés)");

        while (jatekFut) {
            megjelenitTabla(game.getTabla());

            if (aktualisJatekos == 'P') {
                System.out.print("Te jössz (P): ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                
                if (input.equalsIgnoreCase("save")) {
                    game.jatekMentese("jatekallas.json");
                    continue;
                }

                int oszlop;
                try {
                    oszlop = Integer.parseInt(input);
                    game.validalOszlop(oszlop);
                } catch (NumberFormatException e) {
                    System.out.println("Hiba: Kérlek számot adj meg!");
                    continue;
                } catch (InvalidStepException e) {
                    System.out.println("Hiba: " + e.getMessage());
                    continue;
                }

                if (!game.korongLetesz(oszlop, 'P')) {
                    System.out.println("Sikertelen lépés.");
                    continue;
                }

            } else {
                System.out.println("A Gép (S) gondolkodik...");
                int gepiOszlop = game.aiLepes();
                if (gepiOszlop == -1) {
                    System.out.println("A gép nem tud lépni. Döntetlen?");
                    jatekFut = false;
                } else {
                    System.out.println("A gép ide lépett: " + gepiOszlop);
                }
            }
            
            if (game.ellenorizNyeres(aktualisJatekos)) {
                megjelenitTabla(game.getTabla());
                System.out.println("GRATULÁLOK! Nyertes: " + (aktualisJatekos == 'P' ? "Játékos" : "Gép"));
                jatekFut = false;
            } else if (game.teleAVanATabla()) {
                megjelenitTabla(game.getTabla());
                System.out.println("DÖNTETLEN! A tábla megtelt.");
                jatekFut = false;
            }
            
            if (jatekFut) {
                aktualisJatekos = (aktualisJatekos == 'P') ? 'S' : 'P';
            }
        }
        scanner.close();
    }

    private static void megjelenitTabla(char[][] tabla) {
        System.out.println("\n 0 1 2 3 4 5 6");
        System.out.println("---------------");
        for (int i = 0; i < Connect4.ROWS; i++) {
            System.out.print("|");
            for (int j = 0; j < Connect4.COLS; j++) {
                System.out.print(tabla[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("---------------");
    }
}
