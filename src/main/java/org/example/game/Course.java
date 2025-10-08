package org.example.game;

import org.example.model.Animal;
import org.example.thread.Coureur;
import org.example.util.ClearConsole;
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

        try {
            Thread.sleep(2000); // Pause avant le départ
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Départ simultané de tous les coureurs
        System.out.println("\n🚦 3... 2... 1... PARTEZ ! 🚦\n");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (Coureur coureur : coureurs) {
            coureur.start();
        }

        // Boucle d'affichage en temps réel
        while (!courseTerminee.get()) {
            try {
                Thread.sleep(200); // Rafraîchissement toutes les 200ms
                ClearConsole.clear();
                afficherEtatCourse();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        // Attendre la fin de tous les threads
        for (Coureur coureur : coureurs) {
            try {
                coureur.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        ClearConsole.clear();
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
     * Affiche l'état actuel de la course (appelé périodiquement)
     */
    private void afficherEtatCourse() {
        System.out.println("═".repeat(60));
        System.out.println("      🏁 COURSE EN DIRECT 🏁");
        System.out.println(STR."""
            \{"═".repeat(60)}""");

        for (Coureur coureur : coureurs) {
            Animal animal = coureur.getAnimal();
            int pos = coureur.getPosition();

            StringBuilder ligne = new StringBuilder();
            ligne.append(String.format("%-8s : ", animal.getNom()));

            // Afficher la piste
            for (int i = 0; i < distanceTotale; i++) {
                if (i == pos) {
                    ligne.append(animal.getEmoji());
                } else if (i == distanceTotale - 1) {
                    ligne.append("🏁"); // Ligne d'arrivée
                } else {
                    ligne.append("-");
                }
            }

            ligne.append(String.format(" [%d/%d]", pos, distanceTotale));
            System.out.println(ligne);
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