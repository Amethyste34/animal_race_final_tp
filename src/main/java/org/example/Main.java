package org.example;

import org.example.game.Course;
import org.example.model.Cheval;
import org.example.model.Lapin;
import org.example.model.Tortue;

/**
 * Point d'entrée du programme de course d'animaux
 */
public class Main {

    private static final int DISTANCE_COURSE = 50;

    static void main(String[] args) {
        // Création de la course
        Course course = new Course(DISTANCE_COURSE);

        // Ajout des participants
        course.ajouterParticipant(new Tortue("Tortue"));
        course.ajouterParticipant(new Lapin("Lapin"));
        course.ajouterParticipant(new Cheval("Cheval"));

        // Démarrage de la course
        course.demarrer();

        System.out.println("\n✨ Merci d'avoir participé ! ✨\n");
    }
}