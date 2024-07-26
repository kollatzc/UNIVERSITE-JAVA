UNIVERSITE JAVA

# DESCRIPTION

Application Java pour gérer les notes des étudiants dans une école.
L'application permet l'administration des classes, des étudiants et des
matières, ainsi que l'enregistrement des notes, le calcul des moyennes,
l'établissement des classements et la génération des bulletins de notes.

# TECHNOLOGIES
	- JAVA
	- MAVEN
	- BATCH

# COMPILATION

	. Compilation et Création du JAR ( Facultatif car Jar déjà compilé )
		1.Se rassurer d'avoir Java 17
		2. **Compilation :   mvn compile
		3. **Création du JAR : mvn package


# UTILISATION

	1. Comment executer l'application?
		1. Tout simplement en Executant le fichier .\RUN.bat
		ou
		2.
		- ouvrir l'invite de commande à la racine du fichier PROJET/target/UNIVERSITE-1.0-SNAPSHOT.jar
		- faire: java -jar UNIVERSITE-1.0-SNAPSHOT.jar


	2. Comment se connecter
		- Login: Admin
		- Mot de passe: 1478
 Menu
------------------------------
|      MENU PRINCIPAL        |
|----------------------------|
| 0. DONNEES EXEMPLES        |
| 1. Gestion des classes     |
| 2. Gestion des Etudiants   |
| 3. Gestion des Matieres    |
| 4. Gestion des Evaluations |
| 5. Gestion des Bulletins   |
| 6. Quitter                 |
|----------------------------|


0. DONNEES EXEMPLES 

Le choix 0 permet de générer des données arbitraires pour une visite rapide de l'application.
les données générées sont: 

- Une liste de Classes, 
- Une liste de Matière, 
- Une liste d'étudiant
- Une liste de notes arbitraires pour chaque Etudiant


1. Gestion des classes
	- Afficher, Ajouter des classes

2. Gestion des Etudiants 
	- Afficher, Ajouter des Etudiants

3. Gestion des Matieres
	- Afficher, Ajouter des Etudiants

4. Gestion des Evaluations 
	- Afficher liste des notes
	- Ajouter des notes 
	- Calculer des moyennes
	- Faire des classements
5. Gestion des Bulletins 
	- Imprimer Bulletin particuler selon matricule Etudiant
	- Imprimer tous les bulletins
	- Les bulletins sont enregistrés dans le dossier "Bulletins"

6.Quitter
	- Fermer l'application


