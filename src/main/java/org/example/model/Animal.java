package org.example.model;

/**
 * Interface sealed pour les animaux participants à la course.
 * Utilise les sealed classes de Java 21 pour restreindre les implémentations.
 */
public sealed interface Animal permits Tortue, Lapin, Cheval {

    /**
     * @return Le nom de l'animal
     */
    String getNom();

    /**
     * @return L'émoji représentant l'animal
     */
    String getEmoji();

    /**
     * @return La vitesse minimale de l'animal (en ms de pause)
     */
    int getVitesseMin();

    /**
     * @return La vitesse maximale de l'animal (en ms de pause)
     */
    int getVitesseMax();

    /**
     * @return Le nombre de pas minimum par déplacement
     */
    int getPasMin();

    /**
     * @return Le nombre de pas maximum par déplacement
     */
    int getPasMax();
}