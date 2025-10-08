package org.example.model;

/**
 * Classe qui représente un lapin : rapide mais irrégulier
 * Implémente la sealed interface Animal
 */
public final class Lapin implements Animal {

    /**
     * Attribut Nom de l'animal
     */
    private final String nom;

    /**
     * Constructeur
     * @param nom
     */
    public Lapin(String nom) {
        this.nom = nom;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public String getEmoji() {
        return "\uD83D\uDC07";
    }

    @Override
    public int getVitesseMin() {
        return 100;
    }

    @Override
    public int getVitesseMax() {
        return 600;  // Rapide
    }

    @Override
    public int getPasMin() {
        return 1;
    }

    @Override
    public int getPasMax() {
        return 4;  // Peut faire de grands bonds
    }
}