package org.example.util;

/**
 * Utilitaire pour nettoyer la console
 */
public class ClearConsole {

    public static void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Impossible de nettoyer la console : " + e.getMessage());
        }
    }
}