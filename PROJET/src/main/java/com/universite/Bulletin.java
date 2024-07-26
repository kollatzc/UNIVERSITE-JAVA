package com.universite;

import java.util.ArrayList;
import java.util.Scanner;


public class Bulletin {

    //GESTIONNAIRE DES CLASSES
    public static void gestionnaireBulletins() {
        System.out.println("\n         1. Imprimer Bulletin particuler");
        System.out.println("         2. Imprimer tous les bulletins");
        System.out.println("         3. Menu Principal");
        switch (Outil.faireChoix()) {
            case 1:
                imprimBultEtudiant();gestionnaireBulletins();break;
            case 2:
                imprimBultTousEtudiants();gestionnaireBulletins();break;
            default:
                Universite.menu();
        }
    }















    public static void imprimBultEtudiant(){
        Scanner clavier = new Scanner(System.in);
        System.out.print("\nEntrez le matricule de l'Etudiant: ");String matriculeEtudiant = clavier.nextLine();
        ArrayList<String> tableauContenu = new ArrayList<>();


        if(!Evaluation.mapMatriculeMatiereRang.isEmpty()){
            Evaluation.mapMatriculeMatiereRang.forEach((matricule, matieremoyenne_rang)->{
                if(matricule.equals(matriculeEtudiant)){
                    ArrayList<Double> lstemoyenne = new ArrayList<>();
                    ArrayList<Integer> lstecoeft = new ArrayList<>();
                    Etudiant etudiantTrouve = Etudiant.getEtudiantParMatricule(matriculeEtudiant);
//                    System.out.println("["+matriculeEtudiant+"]");
//                    System.out.println("|-----------------------------------------------------|");
//                    System.out.println("  Nom     : "+etudiantTrouve.nom);
//                    System.out.println("  Prenoms : "+etudiantTrouve.prenoms);
//                    System.out.println("  Niveau  : "+etudiantTrouve.niveau);
//                    System.out.println("  Classe  : "+etudiantTrouve.clsse);
//                    System.out.println("|---------Matiere-----------Moyenne(coef)-----------Rang");
                    tableauContenu.add("|-----------------------------------------------------|\n");
                    tableauContenu.add("  Nom     : "+etudiantTrouve.nom+"\n");
                    tableauContenu.add("  Prenoms : "+etudiantTrouve.prenoms+"\n");
                    tableauContenu.add("  Niveau  : "+etudiantTrouve.niveau+"\n");
                    tableauContenu.add("  Classe  : "+etudiantTrouve.clsse+"\n");
                    tableauContenu.add("|---------Matiere-----------Moyenne(coef)-----------Rang\n");

                    matieremoyenne_rang.forEach((matiere,moyenne_rang)->{
                        Double moyenneMatiere =  Double.valueOf(moyenne_rang.split("_")[0]);
                        Integer coef = Matiere.getCoefFromMatiere(matiere, false);
                        //collection des moyennes
                        lstemoyenne.add(moyenneMatiere*coef);
                        lstecoeft.add(coef);
                        //----------
                        tableauContenu.add("|%15s ".formatted(matiere));
                        tableauContenu.add("          %6s x %d     ".formatted(moyenne_rang.split("_")[0],coef));
                        tableauContenu.add("%10s  \n".formatted(moyenne_rang.split("_")[1]));

//                        System.out.printf("|%15s ",matiere);
//                        System.out.printf("          %6s x %d     ",moyenne_rang.split("_")[0].substring(0,4),coef);
//                        System.out.printf("%10s  \n",moyenne_rang.split("_")[1]);

                    });
                    //Moyenne GENERALE
                    Double moyenneGenerale = Outil.sommeListeDouble(lstemoyenne)/ Outil.sommeListeInteger(lstecoeft);
//                    System.out.println("|-----------------------------------------------------|");
//                    System.out.printf("%15s ","Moyenne G.: ");
//                    System.out.printf("         (%.2f:%d)  : %.2f   \n",Outils.sommeListeDouble(lstemoyenne),Outils.sommeListeInteger(lstecoeft),moyenneGenerale);
//                    System.out.printf("%15s ","Decision: ");
//                    System.out.printf("         %6s \n",Outils.estAdmisOuRedouble(moyenneGenerale));
//                    System.out.println("|-----------------------------------------------------|\n\n");
                    tableauContenu.add("\n|-----------------------------------------------------|\n");
                    tableauContenu.add("%15s ".formatted("Moyenne G.: "));
                    tableauContenu.add("         (%.2f:%d)  : %.2f   \n".formatted(Outil.sommeListeDouble(lstemoyenne), Outil.sommeListeInteger(lstecoeft),moyenneGenerale));
                    tableauContenu.add("%15s ".formatted("Decision: "));
                    tableauContenu.add("         %6s \n".formatted(Outil.estAdmisOuRedouble(moyenneGenerale)));
                    tableauContenu.add("|-----------------------------------------------------|\n\n");

                    Outil.remplissageFichier("Bulletins/"+etudiantTrouve.niveau+"_bulletin_"+etudiantTrouve.nom+"_"+etudiantTrouve.prenoms,tableauContenu);

                }
            });

        }else{
            System.out.println("""
                -------------------
                Oups !
                Veuillez proceder d'abord au calcul des moyennes.
                puis au classement
                --------------------""");

        }
    }



    public static void imprimBultTousEtudiants(){
        if(!Evaluation.mapMatriculeMatiereRang.isEmpty()){
            Evaluation.mapMatriculeMatiereRang.forEach((matricule, matieremoyenne_rang)->{
                ArrayList<Double> lstemoyenne = new ArrayList<>();
                ArrayList<Integer> lstecoeft = new ArrayList<>();
                Etudiant etudiantTrouve = Etudiant.getEtudiantParMatricule(matricule);

                ArrayList<String> tableauContenu = new ArrayList<>();
                tableauContenu.add("|-----------------------------------------------------|\n");
                tableauContenu.add("  Nom     : "+etudiantTrouve.nom+"\n");
                tableauContenu.add("  Prenoms : "+etudiantTrouve.prenoms+"\n");
                tableauContenu.add("  Niveau  : "+etudiantTrouve.niveau+"\n");
                tableauContenu.add("  Classe  : "+etudiantTrouve.clsse+"\n");
                tableauContenu.add("|---------Matiere-----------Moyenne(coef)-----------Rang\n");

//                System.out.println("|-----------------------------------------------------|");
//                System.out.println("  Nom     : "+etudiantTrouve.nom);
//                System.out.println("  Prenoms : "+etudiantTrouve.prenoms);
//                System.out.println("  Niveau  : "+etudiantTrouve.niveau);
//                System.out.println("  Classe  : "+etudiantTrouve.clsse);
//                System.out.println("|---------Matiere-----------Moyenne(coef)-----------Rang");

                matieremoyenne_rang.forEach((matiere,moyenne_rang)->{
                    Double moyenneMatiere =  Double.valueOf(moyenne_rang.split("_")[0]);
                    Integer coef = Matiere.getCoefFromMatiere(matiere, false);
                    //collection des moyennes
                    lstemoyenne.add(moyenneMatiere*coef);
                    lstecoeft.add(coef);
                    //----------
//                    System.out.printf("|%15s ",matiere);
//                    System.out.printf("          %6s x %d     ",moyenne_rang.split("_")[0].substring(0,4),coef);
//                    System.out.printf("%10s  \n",moyenne_rang.split("_")[1]);

                    tableauContenu.add("|%15s ".formatted(matiere));
                    tableauContenu.add("          %6s x %d     ".formatted(moyenne_rang.split("_")[0],coef));
                    tableauContenu.add("%10s  \n".formatted(moyenne_rang.split("_")[1]));

                });
                //Moyenne GENERALE
                Double moyenneGenerale = Outil.sommeListeDouble(lstemoyenne)/ Outil.sommeListeInteger(lstecoeft);

//                System.out.println("|-----------------------------------------------------|");
//                System.out.printf("%15s ","Moyenne G.: ");
//                System.out.printf("         (%.2f:%d)  : %.2f   \n",Outils.sommeListeDouble(lstemoyenne),Outils.sommeListeInteger(lstecoeft),moyenneGenerale);
//                System.out.printf("%15s ","Decision: ");
//                System.out.printf("         %6s \n",Outils.estAdmisOuRedouble(moyenneGenerale));
//                System.out.println("|-----------------------------------------------------|\n\n");

                tableauContenu.add("\n|-----------------------------------------------------|\n");
                tableauContenu.add("%15s ".formatted("Moyenne G.: "));
                tableauContenu.add("         (%.2f:%d)  : %.2f   \n".formatted(Outil.sommeListeDouble(lstemoyenne), Outil.sommeListeInteger(lstecoeft),moyenneGenerale));
                tableauContenu.add("%15s ".formatted("Decision: "));
                tableauContenu.add("         %6s \n".formatted(Outil.estAdmisOuRedouble(moyenneGenerale)));
                tableauContenu.add("|-----------------------------------------------------|\n\n");
                Outil.remplissageFichier("Bulletins/"+etudiantTrouve.niveau+"_bulletin_"+etudiantTrouve.nom+"_"+etudiantTrouve.prenoms,tableauContenu);

            });

        }else{
            System.out.println("""
                -------------------
                Oups !
                Veuillez proceder d'abord au calcul des moyennes.
                puis au classement
                --------------------""");

        }
    }




    }
