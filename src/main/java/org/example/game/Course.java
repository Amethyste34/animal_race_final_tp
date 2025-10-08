package org.example.game;

import org.example.model.Animal;
import org.example.thread.Coureur;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Classe qui gère le déroulement d'une course
 */
public class Course {

    // Attributs de la classe
    private final List<Coureur> coureurs;
    private final int distanceTotale;
    private final AtomicBoolean courseTerminee;

    /**
     * Constructeur
     * @param distanceTotale de la course
     */
    public Course(int distanceTotale) {
        this.distanceTotale = distanceTotale;
        this.coureurs = new ArrayList<>();
        this.courseTerminee = new AtomicBoolean(false);
    }

    /**
     * Ajoute un animal à la course
     * @param animal
     */
    public void ajouterParticipant(Animal animal) {
        Coureur coureur = new Coureur(animal, distanceTotale, courseTerminee);
        coureurs.add(coureur);
    }

    /**
     * Méthode qui lance la course
     */
    public void demarrer() {
        afficherIntroduction();

        // Départ simultané de tous les coureurs
        System.out.println("\n🚦 3... 2... 1... PARTEZ ! 🚦\n");

        for (Coureur coureur : coureurs) {
            coureur.start();
        }

        // Attendre la fin de tous les threads
        for (Coureur coureur : coureurs) {
            try {
                coureur.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        afficherClassement();
    }

    /**
     * Méthode pour afficher les informations de début de course
     */
    private void afficherIntroduction() {
        System.out.println(STR."""
            \{"═".repeat(60)}""");
        System.out.println("      🏁 COURSE D'ANIMAUX - ÉDITION MULTITHREADÉE 🏁");
        System.out.println("═".repeat(60));
        System.out.println("\n📋 Participants :");

        for (Coureur coureur : coureurs) {
            Animal animal = coureur.getAnimal();
            System.out.printf("   %s %s\n", animal.getEmoji(), animal.getNom());
        }

        System.out.println(STR."""
            \uD83D\uDCCF Distance à parcourir : \{distanceTotale} unités""");
        System.out.println();
    }

    /**
     * Méthode pour afficher le classement
     */
    private void afficherClassement() {
        System.out.println(STR."""
            \{"═".repeat(60)}""");
        System.out.println("      📊 CLASSEMENT FINAL");
        System.out.println(STR."""
            \{"═".repeat(60)}""");

        // Trier les coureurs par position (décroissante) puis par temps (croissant)
        List<Coureur> classement = coureurs.stream()
                .sorted(Comparator
                        .comparingInt(Coureur::getPosition).reversed()
                        .thenComparingLong(Coureur::getTempsCourse))
                .toList();

        String[] medailles = {"🥇", "🥈", "🥉"};

        for (int i = 0; i < classement.size(); i++) {
            Coureur coureur = classement.get(i);
            Animal animal = coureur.getAnimal();
            String medaille = i < medailles.length ? medailles[i] : "  ";

            System.out.printf("%s %d. %s %s - Position: %d/%d - Temps: %d ms\n",
                    medaille,
                    i + 1,
                    animal.getEmoji(),
                    animal.getNom(),
                    coureur.getPosition(),
                    distanceTotale,
                    coureur.getTempsCourse());
        }

        System.out.println(STR."""
            \{"═".repeat(60)}""");
    }

    /**
     * Getter
     * @return La liste des coureurs
     */
    public List<Coureur> getCoureurs() {
        return new ArrayList<>(coureurs);
    }
}