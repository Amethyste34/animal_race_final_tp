package org.example.model;

/**
 * Classe qui représsente un cheval : équilibré et rapide
 * Implémente la sealed interface Animal
 */
public final class Cheval implements Animal{

    /**
     * Attribut Nom de l'animal
     */
    private final String nom;

    /**
     * Contructeur
     * @param nom
     */
    public  Cheval(String nom) {
        this.nom = nom;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getEmoji() {
        return "\uD83D\uDC0E";
    }

    @Override
    public int getVitesseMin() {
        return 150;
    }

    @Override
    public int getVitesseMax() {
        return 300;
    }

    @Override
    public int getPasMin() {
        return 2;
    }

    @Override
    public int getPasMax() {
        return 3;
    }
}