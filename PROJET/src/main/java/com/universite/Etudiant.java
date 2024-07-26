package com.universite;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public  class Etudiant {
     String nom;
     String prenoms;
     String genre;
     String dateNaissance;
     String contacts;
     private String matricule;
     String niveau;
     String clsse;

    public static List<Etudiant> listEtudiant = new ArrayList<>();

   Etudiant(){}
   Etudiant(String nom, String prenoms, String genre, String dateNaissance, String contacts, String matricule, String niveau, String clsse){
        this.nom = nom;
        this.prenoms = prenoms;
        this.genre = genre;
        this.dateNaissance = dateNaissance;
        this.contacts = contacts;
        this.matricule = matricule;
        this.niveau = niveau;
        this.clsse = clsse;
    }

    public String getMatricule(){ return this.matricule;}

    public void setClsse(String clsse){this.clsse=clsse;}
    public String getClsse(){ return this.clsse;}



    //obtenir objet etudiant selon matricule
    public static Etudiant getEtudiantParMatricule(String matricule){
        ArrayList<Etudiant> tableauEtudiantTrouve = new ArrayList<>();
        if(!listEtudiant.isEmpty()){
            listEtudiant.forEach((v)->{ if(v.getMatricule().equals(matricule)){
                tableauEtudiantTrouve.add(v);
            }});
            if(!tableauEtudiantTrouve.isEmpty())return tableauEtudiantTrouve.get(0);
        }
        return tableauEtudiantTrouve.get(0);
    }




    //GESTIONNAIRE DES CLASSES
    public static void gestionnaireEtudiant() {
        System.out.println("\n         1. Liste des Etudiants");
        System.out.println("         2. Ajouter des Etudiants");
        System.out.println("         3. Menu Principal");
        switch (Outil.faireChoix()) {
            case 1:
                listeEstudiants();break;
            case 2:
                ajouterEtudiant();break;
            default:
                Universite.menu();break;
        }
    }



    //liste désignation matière
    public static ArrayList listeMatriculeEtudiant(){
        ArrayList<String> listeoutput = new ArrayList<>();
        if(!listEtudiant.isEmpty()){
            listEtudiant.forEach((objetEtudiant)->{
                listeoutput.add(objetEtudiant.getMatricule()+" ( "+objetEtudiant.nom+" "+objetEtudiant.prenoms +")");
            });
        }
        return listeoutput;
    }





    //Ajouter un nouvel Etudiant
    public static void ajouterEtudiant(){
        Scanner clavier = new Scanner(System.in);
        System.out.println("\n|...............   NOUVEL  ETUDIANT   ................|");
        int iteration = 1;
        String input;
        ArrayList<String> infoEtudiant = new ArrayList<>();
        Etudiant nouvelEtudiant = new Etudiant();

        // Verification si classe disponible avant ajout Etudiant
        if(Classe.listelasses.isEmpty()){
            System.out.print("Impossible d'ajouter un Etudiant. Veuillez d'abord créer les classes.");
            return;
        }
        //Faire si liste de classes disponibles
        do{
            System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  ");
            System.out.print("Liste des Classes: "+ Classe.listelasses.toString()+"\n");
            System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  ");

            System.out.print("Classe: ");nouvelEtudiant.clsse = clavier.nextLine();
            if(!Classe.listelasses.contains(nouvelEtudiant.clsse)){
                System.out.print("Choix incorrect, Cette classe n'existe pas!");
                ajouterEtudiant();
                return;
            }

            System.out.print("Nom: "); nouvelEtudiant.nom = clavier.nextLine();
            System.out.print("Prenoms: ");nouvelEtudiant.prenoms = clavier.nextLine();
            System.out.print("Genre: ");nouvelEtudiant.genre =  clavier.nextLine();
            System.out.print("DateNaissance: ");nouvelEtudiant.dateNaissance =  clavier.nextLine();
            System.out.print("Contacts: ");nouvelEtudiant.contacts =  clavier.nextLine();
            System.out.print("Matricule: ");nouvelEtudiant.matricule =  clavier.nextLine();
            System.out.print("Niveau: ");nouvelEtudiant.niveau = clavier.nextLine();
            listEtudiant.add(nouvelEtudiant);
            System.out.print("Terminer: (*) Continuer: (+) :"); input =  clavier.nextLine();
            if(input.equals("*")){
                gestionnaireEtudiant();
            }else if(input.equals("+")){
                ajouterEtudiant();
            }else{
                gestionnaireEtudiant();
            }

            //System.out.println("Voici la liste des Etudiants: "+Directions.listEtudiant);
        }while (input.equals("*"));

    }


// Modifier Etudiant
    public static void modifierEtudiant(){
        Scanner clavier = new Scanner(System.in);
        System.out.print("Entrez matricule: "); String matricule = clavier.nextLine();
        getInfoEtudiant(matricule);
        listEtudiant.forEach((etudiant)->{
            if(etudiant.matricule == matricule){
                System.out.print("Nom: "); etudiant.nom = clavier.nextLine();
            }
        });
        getInfoEtudiant(matricule);
    }


    /*
    |Rechercher un Etudiant
    */

    public static boolean etudiantExiste(String matricule ){
        AtomicInteger a= new AtomicInteger();
        listEtudiant.forEach((etudiant)->{
            if(etudiant.matricule.equals(matricule)){
                a.getAndIncrement();
            }
        });
       // System.out.println(a);
        if(a.intValue()==1){
            return true;
        }else{
            return false;
        }

    }



//obtenir info Etudiant par Matricule
public static void getInfoEtudiant(String matricule){
//    Scanner clavier = new Scanner(System.in);
//    System.out.print("Entrez matricule: "); String matricule = clavier.nextLine();
    listEtudiant.forEach((etudiant)->{
        if(etudiant.matricule == matricule){
            System.out.println(" Nom: "+etudiant.nom);
            System.out.println(" Prenoms: "+etudiant.prenoms);
            System.out.println(" Genre: "+etudiant.genre);
            System.out.println(" Date Naissance: "+etudiant.dateNaissance);
            System.out.println(" Contacts: "+etudiant.contacts);
            System.out.println(" Matricule: "+etudiant.matricule);
            System.out.println(" Niveau: "+etudiant.niveau);
            System.out.println(" Classe: "+etudiant.clsse);
        }
    });
}


public static void listeEstudiants(){
       if(listEtudiant.stream().count()!=0){
           int compteur =1;
           for(Etudiant etudiant:listEtudiant){
               System.out.println("-------------  Etudiant "+ compteur+"  ----------------" );
               System.out.println("Nom: "+etudiant.nom);
               System.out.println("Prenoms: "+etudiant.prenoms);
               System.out.println("Genre: "+etudiant.genre);
               System.out.println("DateNaissance: "+etudiant.dateNaissance);
               System.out.println("Contacts: "+etudiant.contacts);
               System.out.println("Matricule: "+etudiant.matricule);
               System.out.println("Niveau: "+etudiant.niveau);
               System.out.println("Classe: "+etudiant.clsse);
               compteur++;
           };
       }else{
           System.out.println("""
                -------------------
                Oups !
                Aucune Etudiant  n'a encore EtE ajoutE.
                Veuillez les ajouter depuis: option 2
                --------------------""");
       }
    gestionnaireEtudiant();

}


}
