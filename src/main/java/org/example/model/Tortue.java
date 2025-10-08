package org.example.model;

/**
 * Classe qui représente une tortue : lente mais régulière
 * Implémente la sealed interface Animal
 */
public final class Tortue implements Animal {
    /**
     * Attribut Nom de l'animal
     */
    private final String nom;

    /**
     * Constructeur
     * @param nom Nom de l'animal
     */
    public Tortue(String nom) {
        this.nom = nom;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getEmoji() {
        return "\uD83D\uDC22";
    }

    @Override
    public int getVitesseMin() {
        return 300;
    }

    @Override
    public int getVitesseMax() {
        return 500;
    }

    @Override
    public int getPasMin() {
        return 1;  // Avance peu
    }

    @Override
    public int getPasMax() {
        return 2;
    }
}