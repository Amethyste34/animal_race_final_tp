package org.example.thread;

import org.example.model.Animal;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread représentant un animal en course
 */
public class Coureur extends Thread {

    /**
     * Attributs de la classe Coureur
     */
    private final Animal animal;
    private final int distanceTotale;
    private final AtomicInteger position;
    private final AtomicBoolean courseTerminee;
    private final Random random;
    private long tempsDepart;
    private long tempsFin;

    /**
     * Constructeur
     *
     * @param animal
     * @param distanceTotale
     * @param courseTerminee
     */
    public Coureur(Animal animal, int distanceTotale, AtomicBoolean courseTerminee) {
        this.animal = animal;
        this.distanceTotale = distanceTotale;
        this.position = new AtomicInteger(0);
        this.courseTerminee = courseTerminee;
        this.random = new Random();
        this.setName(STR."Thread-\{animal.getNom()}");
    }

    @Override
    public void run() {
        tempsDepart = System.currentTimeMillis();

        while (!courseTerminee.get() && position.get() < distanceTotale) {
            try {
                // Pause aléatoire simulant la vitesse variable
                int pause = random.nextInt(
                        animal.getVitesseMin(),
                        animal.getVitesseMax() + 1
                );
                Thread.sleep(pause);

                // Avancement aléatoire
                int avancement = random.nextInt(
                        animal.getPasMin(),
                        animal.getPasMax() + 1
                );

                int nouvellePosition = Math.min(
                        position.addAndGet(avancement),
                        distanceTotale
                );

                // Vérification de victoire (synchronisé)
                if (nouvellePosition >= distanceTotale && !courseTerminee.get()) {
                    synchronized (courseTerminee) {
                        if (!courseTerminee.get()) {
                            courseTerminee.set(true);
                            tempsFin = System.currentTimeMillis();
                            declarerVictoire();
                        }
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Méthode pour déclarer le vainqueur
     */
    private void declarerVictoire() {
        System.out.println(STR."""
            \{"=".repeat(60)}""");
        System.out.println(STR."\uD83C\uDFC6 VICTOIRE ! \{animal.getEmoji()} \{animal.getNom()} a gagné ! \uD83C\uDFC6");
        System.out.println(STR."Temps : \{tempsFin - tempsDepart} ms");
        System.out.println(STR."""
            \{"=".repeat(60)}""");
    }

    /**
     * Getter
     * @return position
     */
    public int getPosition() {
        return position.get();
    }

    /**
     * Getter
     * @return animal
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Getter
     * @return temps de départ de la course
     */
    public long getTempsDepart() {
        return tempsDepart;
    }

    /**
     * Getter
     * @return Temps de fin de la course
     */
    public long getTempsFin() {
        return tempsFin;
    }

    /**
     * Calsul du temps de course
     * @return durée de la course
     */
    public long getTempsCourse() {
        if (tempsFin > 0) {
            return tempsFin - tempsDepart;
        }
        return System.currentTimeMillis() - tempsDepart;
    }
}