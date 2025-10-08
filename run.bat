@echo off
chcp 65001 > nul
echo Compilation...
javac --enable-preview --release 21 -d target/classes src/main/java/org/example/*.java src/main/java/org/example/model/*.java src/main/java/org/example/thread/*.java src/main/java/org/example/game/*.java src/main/java/org/example/util/*.java
echo Lancement...
java --enable-preview -cp target/classes org.example.Main
pause