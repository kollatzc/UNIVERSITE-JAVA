package com.universite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Classe {

    static HashMap<String, HashMap> listeNoteclasse= new HashMap<>();
    static ArrayList<String> listelasses= new ArrayList<>();

    private String designation;


    Classe(String designation){
        this.designation = designation;

    }

//ajouter une classe
public static void ajouterClasse() {
    Scanner clavier = new Scanner(System.in);
    System.out.println("|..........................................................................|");
    System.out.println("|---------->[ Terminer: '0' Afficher: '1' Menu Principal: '2' ]<-----------|");
    System.out.println("...........................................................................");

    int compteurDeClasse = 1;
    String classe;

    //Ajout des note en mode illimmitée
    do {
        System.out.print("Classe " + compteurDeClasse + ": ");classe = clavier.nextLine();
        if (!Outil.estConvertibleEnInt(classe)) {
            compteurDeClasse++;
            listelasses.add(classe);
        }
    }while (!Outil.estConvertibleEnInt(classe)) ;

    if (classe.charAt(0) == '0') {
        System.out.println("Termine avec succes");
        gestionnaireClasse();
    } else if (classe.charAt(0) == '1') {
        getClasse();
        gestionnaireClasse();

    }
    else if (classe.charAt(0) == '2') {
        Universite.menu();
    }else{
        Universite.menu();
    }

}





//afficher liste des classes
public static void getClasse(){
        if(!listelasses.isEmpty()){
            System.out.println("La liste des classes est: "+ listelasses);
        }else{
            System.out.println("""
                -------------------
                Oups !
                La liste des classes est vide !! Veuillez en ajouter
                --------------------""");
    }

}


    //obtenir info class etudiant par matricule
    public static ArrayList getEtudiantParMatricule(String matricule){
        ArrayList<Etudiant> tableauEtudiantTrouve = new ArrayList<>();
        if(!Etudiant.listEtudiant.isEmpty()){
            Etudiant.listEtudiant.forEach((v)->{ if(v.getMatricule().equals(matricule)){
                tableauEtudiantTrouve.add(v);
                }});
            if(!tableauEtudiantTrouve.isEmpty())return tableauEtudiantTrouve;
        }
        return tableauEtudiantTrouve;
    }

    //GESTIONNAIRE DES CLASSES
    public static void gestionnaireClasse() {
        System.out.println("\n         1. Liste des classes");
        System.out.println("         2. Ajouter classe");
        System.out.println("         3. Menu Principal");
        switch (Outil.faireChoix()) {
            case 1:
                getClasse();
                gestionnaireClasse();
                break;
            case 2:
                ajouterClasse();break;
               // Classes.modifierClasse();break;
            default:
                Universite.menu();
        }
    }













}
