# 🏁 Course d'Animaux Multithreadée

Projet Java 21 simulant une course entre plusieurs animaux, chacun géré par un thread indépendant.

## 📋 Prérequis

- **Java 21** installé et configuré
- **Variable JAVA_HOME** correctement définie
- **Terminal** : PowerShell, CMD ou Windows Terminal (tous compatibles avec les emojis via `chcp 65001`)

## 🚀 Lancement du programme

### Méthode 1 : Avec le fichier run.bat (RECOMMANDÉ)

Le fichier `run.bat` compile et lance automatiquement le programme.

#### 🎯 Depuis VSCode (le plus simple) :
1. Ouvrez le projet dans VSCode
2. Ouvrez un terminal (Terminal > New Terminal)
    - **PowerShell** ou **CMD** fonctionnent tous les deux
3. Tapez la commande :
   ```powershell
   .\run.bat
   ```
4. Le programme se compile et se lance automatiquement ! 🎉

#### Depuis l'Explorateur Windows :
1. Ouvrez le dossier du projet dans l'Explorateur Windows
2. Double-cliquez sur `run.bat`
3. Une fenêtre CMD s'ouvre avec le programme

#### Depuis CMD directement :
1. Ouvrez l'invite de commandes (CMD)
2. Naviguez vers le dossier du projet :
   ```cmd
   cd chemin\vers\animal_race_final_tp
   ```
3. Exécutez :
   ```cmd
   run.bat
   ```

**Note** : Le fichier `run.bat` configure automatiquement l'encodage UTF-8 (`chcp 65001`) pour afficher correctement les emojis.

### Méthode 2 : Compilation et lancement manuels

Si vous préférez compiler et lancer manuellement :

```cmd
chcp 65001
javac --enable-preview --release 21 -d target/classes src/main/java/org/example/*.java src/main/java/org/example/model/*.java src/main/java/org/example/thread/*.java src/main/java/org/example/game/*.java src/main/java/org/example/util/*.java
java --enable-preview -cp target/classes org.example.Main
```

**Note importante** : La commande `chcp 65001` est nécessaire pour afficher correctement les emojis dans le terminal Windows.

### Méthode 3 : Depuis IntelliJ IDEA

1. Ouvrez le projet dans IntelliJ IDEA
2. Clic droit sur `Main.java` dans `src/main/java/org/example/`
3. Sélectionnez "Run 'Main.main()'"

## 📁 Structure du projet

```
animal_race_final_tp/
├── pom.xml                          # Configuration Maven
├── run.bat                          # Script de compilation et lancement
├── README.md                        # Ce fichier
└── src/main/java/org/example/
    ├── Main.java                    # Point d'entrée du programme
    ├── model/
    │   ├── Animal.java             # Interface sealed
    │   ├── Tortue.java             # Animal lent et régulier
    │   ├── Lapin.java              # Animal rapide mais irrégulier
    │   └── Cheval.java             # Animal équilibré
    ├── thread/
    │   └── Coureur.java            # Thread représentant un coureur
    ├── game/
    │   └── Course.java             # Gestion de la course
    └── util/
        └── ClearConsole.java       # Utilitaire pour nettoyer la console
```

## 🎮 Fonctionnement du jeu

1. **Départ simultané** : Tous les animaux partent en même temps
2. **Vitesses variables** : Chaque animal avance à son propre rythme avec des pauses aléatoires
3. **Affichage temps réel** : La console affiche la progression de chaque concurrent
4. **Première arrivée** : Le premier animal à franchir la ligne d'arrivée gagne
5. **Classement final** : Affichage des positions et temps de tous les participants

## 🔧 Concepts techniques implémentés

- **Multithreading** : Chaque animal est un thread indépendant
- **Synchronisation** : Utilisation d'`AtomicBoolean` pour éviter les doubles victoires
- **Sealed classes** : Interface `Animal` restreinte aux 3 implémentations (Java 21)
- **String Templates** : Utilisation de la syntaxe `STR.` (preview feature Java 21)
- **Variables atomiques** : `AtomicInteger` pour la gestion thread-safe des positions
- **Stream API** : Tri et traitement du classement final

## 🐛 Problèmes connus

### Les emojis s'affichent comme des points d'interrogation (?)

**Solution** : Assurez-vous que le terminal utilise l'encodage UTF-8.
- Le fichier `run.bat` configure automatiquement l'encodage avec `chcp 65001`
- Si vous lancez manuellement, tapez d'abord `chcp 65001` avant d'exécuter le programme
- **Compatible avec** : CMD, PowerShell et Windows Terminal

### Erreur "string templates are a preview feature"

**Solution** : Les String Templates nécessitent l'option `--enable-preview` avec Java 21. Le fichier `run.bat` l'inclut automatiquement.

## 👥 Participants de la course

- 🐢 **Tortue** : Lente mais régulière (vitesse 300-500ms, avance de 1-2 pas)
- 🐇 **Lapin** : Rapide mais irrégulier (vitesse 100-600ms, avance de 1-4 pas)
- 🐎 **Cheval** : Équilibré (vitesse 150-300ms, avance de 2-3 pas)

## 🎓 Auteur

Projet réalisé dans le cadre d'un TP sur le multithreading en Java.

## 📝 Notes

- Distance de course configurée : 50 unités
- Rafraîchissement de l'affichage : toutes les 200ms
- Les threads utilisent `Thread.sleep()` pour simuler des vitesses variables