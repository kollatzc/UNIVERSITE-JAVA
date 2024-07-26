package com.universite;


public class Universite {
	
    static boolean quit = true;
    
    
    // Entrée Zéro de la classe
    static{
        //Création du dossier Bulletin
          Outil.environnementInitial();

        //Bannière
        System.out.println(
                """
                ------------------------------
                |      UNIVERSITE JAVA       |
                |----------------------------|
                |                            |
                |     CENTRE DE GESTION      |
                |                            |
                |----------------------------|
                
                """);
    }
    public static void main(String[] args) {
    	//Variable argss initialisée à "default"
        String[] argss = {"default"};
        
        if(Gestion.connexion()){
            while(quit){
                menu();
            }
        }else{
            System.out.println("Login ou Mot de passe invalide");
            main(argss);
        }

    }



public static void menu(){
    System.out.println(
            """
            
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
            """);
    switch (Outil.faireChoix()){
        case 0:
            Outil.seederDonnees();menu();break;
        case 1:
            Classe.gestionnaireClasse();break;
        case 2:
            Etudiant.gestionnaireEtudiant();break;
        case 3:
            Matiere.gestionnaireMatieres();break;
        case 4:
            Evaluation.gestionnaireEvaluations();break;
        case 5:
            Bulletin.gestionnaireBulletins();break;
        case 6:quit=false;break;
        default:return;

    }



}






}

