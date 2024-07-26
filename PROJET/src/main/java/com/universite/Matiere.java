package com.universite;

import java.util.ArrayList;
import java.util.Scanner;

public class Matiere {

    private String designation;
    private int coeficient;
    public static ArrayList<Matiere> listMatieres = new ArrayList<>();
    Matiere(){};
    Matiere(String designation, int coeficient){
        this.designation = designation;
        this.coeficient = coeficient;
    }

    public void setCoeficient(int coef){this.coeficient=coef;}
    public int getCoeficient(){ return this.coeficient;}
    public void setdesignation(String designation){this.designation=designation;}
    public String getdesignation(){ return this.designation;}



    //GESTIONNAIRE DES CLASSES
    public static void gestionnaireMatieres() {
        System.out.println("\n         1. Liste des Matieres");
        System.out.println("         2. Ajouter des Matieres");
        System.out.println("         3. Menu Principal");
        switch (Outil.faireChoix()) {
            case 1:
                listeDesMatieres();gestionnaireMatieres();break;
            case 2:
                ajouterMatiere();gestionnaireMatieres();break;
            case 3:
                Universite.menu();break;
            // Classes.modifierClasse();break;
            default:return;
        }
    }


//liste désignation matière
public static ArrayList listeDesignationMatiere(){
        ArrayList<String> listeoutput = new ArrayList<>();
    if(!listMatieres.isEmpty()){
        listMatieres.forEach((objetMatiere)->{
            listeoutput.add(objetMatiere.designation+" (coef "+objetMatiere.coeficient+")");
        });
    }
    return listeoutput;
}





//Ajouter matiere
    public static void ajouterMatiere(){
        Scanner clavier = new Scanner(System.in);
        System.out.println("\n-----------  Nouvel Matiere  -----------\n");
        String input;
        do{
        System.out.print("Designation: "); String designation = clavier.nextLine();
        System.out.print("Coeficient: ");int coef = clavier.nextInt();
        listMatieres.add(new Matiere( designation, coef));
        System.out.print("Terminer: (*) Continuer: (+) :"); input =  clavier.nextLine();
        if(input.equals("*")){
            gestionnaireMatieres();
        }else if(input.equals("+")){
            ajouterMatiere();
        }else{
            gestionnaireMatieres();
        }
        }while (input.equals("*"));


        //return ;
    }



    //Obtenir la liste des matières
    public static void listeDesMatieres(){
        if(listMatieres.stream().count()!=0){
            for(Matiere matiere:listMatieres){
                System.out.println("|- "+matiere.designation +" (Coef "+matiere.coeficient+")");
            };

        }else{
            System.out.println("""
                -------------------
                Oups !
                Aucune matiere  n'a encore EtE ajoutEE.
                Veuillez les ajouter  : option 2
                --------------------""");
        }

    }




    //Obtenir coeficient selon la matiere
    public static int getCoefFromMatiere(String matiere,Boolean obligatoire){
        String matre = matiere;
        if(obligatoire){
            Scanner clavier = new Scanner(System.in);
            System.out.println("Matiere : "); matre =  clavier.nextLine();
        }
        for(Matiere mat:listMatieres){
            if(mat.designation.equals(matre) ){
                return mat.coeficient;
            }
        };

        //coef par defaut
        return 1;
    }


}
