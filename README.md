# Connect 4 (Négy a sorban) - Java Implementáció

Ez a projekt a klasszikus **Connect 4** (Négy a sorban) játék logikájának Java nyelvű megvalósítása. A kód tartalmazza a játékszabályok kezelését, a tábla állapotának nyilvántartását, valamint egy átfogó JUnit tesztkészletet a működés ellenőrzésére.

## 📋 Funkciók

A `Connect4` osztály a következő képességekkel rendelkezik:
* **Játéktábla kezelése:** Szabványos 6 soros és 7 oszlopos tábla inicializálása.
* **Korong lehelyezése:** Gravitáció szimulálása – a korong mindig az oszlop legalsó üres helyére esik.
* **Érvényesség ellenőrzése:** Figyeli, hogy a választott oszlop létezik-e, és nincs-e tele.
* **Győzelem ellenőrzése:** Képes detektálni a nyerést mind a négy irányban:
    * Vízszintes
    * Függőleges
    * Átlós (bal fentről jobb le)
    * Átlós (bal lentről jobb fel)
* **Döntetlen figyelése:** Jelzi, ha a tábla betelt, de nincs nyertes.
* **Alapvető AI:** Tartalmaz egy egyszerű gépi lépés logikát (`aiLepes`), amely megkeresi az első érvényes lépést.

## 🛠️ Technológia

* **Nyelv:** Java (JDK 8 vagy újabb)
* **Tesztelés:** JUnit 5 (Jupiter)

## 🚀 Használat

A játék logikája a `com.example` csomagban található. Példa a `Connect4` osztály használatára egy Java alkalmazásban:

```java
import com.example.Connect4;

public class Main {
    public static void main(String[] args) {
        Connect4 game = new Connect4();

        // Játékos ('P') lépése a 3. oszlopba
        boolean sikeresLepes = game.korongLetesz(3, 'P');

        if (sikeresLepes) {
            System.out.println("Sikeres lépés!");
        }

        // Ellenőrzés, hogy nyert-e a játékos
        if (game.ellenorizNyeres('P')) {
            System.out.println("A játékos nyert!");
        }
        
        // Tábla állapotának lekérése
        char[][] tabla = game.getTabla();
    }
}
