package com.universite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Evaluation {

    public static HashMap<String, HashMap<String, ArrayList<Double>>> mapMatriculeMatiereListeNotes = new HashMap<>();
    public static HashMap<String, HashMap<String, Double>> mapMatriculeMatiereMoyenne = new HashMap<>();
    public static HashMap<String, HashMap<String, ArrayList<HashMap<String, Double>>>> mapClassMatiereListeMatriculeMoyenne = new HashMap<>();
    public static HashMap<String, HashMap<String, String>> mapMatriculeMatiereRang = new HashMap<>();


    Evaluation(HashMap<String, HashMap<String, ArrayList<Double>>> mapMatriculeMatiereListeNotes){
        this.mapMatriculeMatiereListeNotes = mapMatriculeMatiereListeNotes;
    }




    //GESTIONNAIRE DES CLASSES
    public static void gestionnaireEvaluations() {
        System.out.println("\n         0. Liste des notes");
        System.out.println("         1. Ajouter des notes");
        System.out.println("         2. Calculer Moyennes");
        System.out.println("         3. Faire Classement");
        System.out.println("         4. Menu Principal");
        switch (Outil.faireChoix()) {
            case 0:
                listeNotesEtudiants();gestionnaireEvaluations();break;
            case 1:
              ajtNoteEtdiantparMatre();gestionnaireEvaluations();break;
            case 2:
              moyenneEtudiantParMatiere();gestionnaireEvaluations();break;
            case 3:
                rangparmatiere();gestionnaireEvaluations();break;
            default:
                Universite.menu();
        }
    }



    /*
    | Afficher les notes
    */

    public static void listeNotesEtudiants(){
        if(!mapMatriculeMatiereListeNotes.isEmpty()){
            mapMatriculeMatiereListeNotes.forEach((matricule,matiereListeNotes)->{
                Etudiant etudiant = Etudiant.getEtudiantParMatricule(matricule);
                System.out.println("|--------------------------------------------------------- ");
                System.out.println("| Nom et Prenoms  :  "+etudiant.nom +" "+etudiant.prenoms);
                System.out.println("| Niveau          :  "+etudiant.niveau);
                System.out.println("| Classe          :  "+etudiant.clsse);
                System.out.println("| Matrule         :  "+etudiant.getMatricule());
                System.out.println("|--------------------------------------------------------- \n");
                matiereListeNotes.forEach((matiere,listeNotes)->{
                System.out.println("|                 : "+matiere+": "+listeNotes);
                });
                System.out.println("\n");
            });
        }
    }










    /*
    |Ajouter les notes d'évaluation par matière
    */
    public static void ajtNoteEtdiantparMatre(){

        Scanner clavier = new Scanner(System.in);



        // Verification si Matières disponibles avant ajout Etudiant
        if(Matiere.listMatieres.isEmpty()){
            System.out.print("Impossible d'ajouter des notes. Veuillez d'abord Ajouter les matières [Voir Menu principal]");
            Universite.menu();
            return;
        }
        System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  ");
        System.out.println("Liste des matières: "+ Matiere.listeDesignationMatiere());
        System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  ");
        System.out.print("Entrez la matiere: ");String matiere = clavier.nextLine();
        //Retour si Mati_re innexistante
        if(!Outil.listMatieresContientmatiere(Matiere.listMatieres,matiere)){
            System.out.print("Choix incorrect, Cette matiEre n'existe pas!\n");
            ajtNoteEtdiantparMatre();
            return;
        }
        System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  ");
        System.out.println("Liste des matières: "+ Etudiant.listeMatriculeEtudiant());
        System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  ");

        System.out.print("Entrez le matricule de l'Etudiant: ");String matricule = clavier.nextLine();
        //Si Etudiant existe
        if(Etudiant.etudiantExiste(matricule)){
            System.out.println("-----   Notes de l'Etudiant en "+matiere+"       ------");
            System.out.println("|......................................................|");
            System.out.println("|---------->[Terminer: (T) Continuer: (A) ]<-----------|");
            System.out.println("|......................................................|");
            // si liste des map matriculeMatiereNote est vide
            ArrayList <Double> listeNotes = new ArrayList<>();
            if(mapMatriculeMatiereListeNotes.isEmpty()){

                HashMap<String, ArrayList<Double>> mapMatierelisteNote = new HashMap<>();
                ajoutNotes(matricule,matiere,listeNotes,mapMatierelisteNote);

                //Si Etudiant dejà évalué
            }else if(Outil.mapContientKey(mapMatriculeMatiereListeNotes,matricule)){
                ajoutNotes(matricule,matiere,listeNotes,mapMatriculeMatiereListeNotes.get(matricule));
            }else{
                //Si etudiant pas encore évalué
                HashMap<String, ArrayList<Double>> mapMatierelisteNote = new HashMap<>();
                ajoutNotes(matricule,matiere,listeNotes,mapMatierelisteNote);
            }

            }

        else{
            System.out.println("Etudiant innexistant");
            ajtNoteEtdiantparMatre();
        }


        }






//Ajouter evaluation a un etudiant selon son matricule
  public static void ajoutNotes(
          String matricule,
          String matiere,
          ArrayList <Double> listeNotes,
          HashMap<String, ArrayList<Double>> mapMatierelisteNote
  ){
      Scanner clavier = new Scanner(System.in);
      int compteurDeNote =1;
      String note;
      //Ajout des note en mode illimmitée
      do{
          System.out.print("Note "+compteurDeNote+": ");note = clavier.nextLine();

          if (Outil.estConvertibleEnDouble(note)) {
              if(Double.valueOf(note)>20 || Double.valueOf(note)<-20 )
              {
                  System.out.println("Les notes extrEmes autorisEe sont [-20 et 20].\n");
              }else{
                  //TODO mettre a jour logique si Etudiant déjà évalué
                  listeNotes.add(Double.valueOf(note));
                  mapMatierelisteNote.put(matiere,listeNotes);
                  compteurDeNote++;
                  mapMatriculeMatiereListeNotes.put(matricule,mapMatierelisteNote);
              }

          }else if(note.charAt(0)=='T'){
              System.out.println("Termine avec succes");
              gestionnaireEvaluations();

          }else if(note.charAt(0)=='A'){
              ajtNoteEtdiantparMatre();
              return;
          }
      }while(note.charAt(0)!='T');
  }


    /*
    |Calcul la moyenne de tous les étudiants par matiere
    */
    public static void moyenneEtudiantParMatiere(){

        //mapMatiereMatriculEtudiantMoyenne
        if(!mapMatriculeMatiereListeNotes.isEmpty()){
            mapMatriculeMatiereListeNotes.forEach((matricule,mapmatierenotre)->{
                HashMap<String,Double> mapmatiereMoyenne = new HashMap<>();
                mapmatierenotre.forEach((matiere, notes) -> {

                    Double total = 0.0;
                    for (Double elmt : notes) {
                        total += elmt;
                    }
                    Double moyenne = total / (Integer) notes.size();
                    mapmatiereMoyenne.put(matiere, moyenne);
                    mapMatriculeMatiereMoyenne.put(matricule, mapmatiereMoyenne);
                });
            }

            );
            clssMatreMatriMoy();

        }else{
            System.out.println("""
                -------------------
                Oups !
                Aucune Evaluation disponible!
                Veuillez les ajouter depuis: option 1
                --------------------""");
          }


    }


    /*
    |Moyenne Etudiant par classe
    */

    public static void clssMatreMatriMoy(){
        //vider mapClassMatiereListeMatriculeMoyenne avant de faire nouveau calcul
        mapClassMatiereListeMatriculeMoyenne.clear();



       HashMap<String, ArrayList<HashMap<String, Double>>> mapMatiereListeMatriculeMoyenne = new HashMap<>();
        if(!mapMatriculeMatiereMoyenne.isEmpty()){

            mapMatriculeMatiereMoyenne.forEach((matricl,mapmatieremoyenne)->{
                String classe = Etudiant.getEtudiantParMatricule(matricl).clsse;

             if(!mapClassMatiereListeMatriculeMoyenne.containsKey(classe)){
                 HashMap<String, ArrayList<HashMap<String, Double>>> mapMatiereListeMatriculeMoyenne2 = new HashMap<>();
                 mapClassMatiereListeMatriculeMoyenne.put(classe,mapMatiereListeMatriculeMoyenne2);
                 mapmatieremoyenne.forEach((matiere,moyenne)->{
                     HashMap<String, Double> matriculeMoyenne = new HashMap<>();
                     matriculeMoyenne.put(matricl,moyenne);

                     if(!mapMatiereListeMatriculeMoyenne.containsKey(matiere)){
                         ArrayList<HashMap<String, Double>> listMatriculeMoyenne = new ArrayList<>();
                         listMatriculeMoyenne.add(matriculeMoyenne);
                         mapClassMatiereListeMatriculeMoyenne.get(classe).putIfAbsent(matiere,listMatriculeMoyenne);
                     }else{
                         mapMatiereListeMatriculeMoyenne.get(matiere).add(matriculeMoyenne);
                         mapClassMatiereListeMatriculeMoyenne.putIfAbsent(classe,mapMatiereListeMatriculeMoyenne);
                     }
                 });

             }else{
                    mapmatieremoyenne.forEach((matiere,moyenne)->{
                        HashMap<String, Double> matriculeMoyenne = new HashMap<>();
                        matriculeMoyenne.put(matricl,moyenne);
                     if(!mapMatiereListeMatriculeMoyenne.containsKey(matiere)){
                         mapClassMatiereListeMatriculeMoyenne.get(classe).get(matiere).add(matriculeMoyenne);
                     }else{
                         mapMatiereListeMatriculeMoyenne.get(matiere).add(matriculeMoyenne);
                         mapClassMatiereListeMatriculeMoyenne.put(classe,mapMatiereListeMatriculeMoyenne);
                     }
                 });
             }


            });
            System.out.println("""
                ..................
                Moyennes calculE avec succEs. Vous pouvez Proceder au classements!
                ..................""");
            //System.out.println("mapClassMatiereListeMatriculeMoyenne :"+mapClassMatiereListeMatriculeMoyenne);
        }else{
            System.out.println("""
                -------------------
                Oups !
                Aucune note d'Evaluation n'a encore EtE ajoutEE.
                Veuillez ajouter des notes: option 2
                --------------------""");
        }


    }






    /*
    |rang Etudiant
    */
public static void rangparmatiere(){

    if(!mapClassMatiereListeMatriculeMoyenne.isEmpty()){
        mapClassMatiereListeMatriculeMoyenne.forEach((classe,MatiereListeMatriculeMoyenne)->{

            MatiereListeMatriculeMoyenne.forEach((Matiere,ListeMatriculeMoyenne)->{
                //   AtomicInteger x= new AtomicInteger();

                ListeMatriculeMoyenne.forEach((MatriculeMoyenne_x)->{
                    //      x.getAndIncrement();
                    final int[] rang_x = {1};
                    MatriculeMoyenne_x.values().forEach((vx)->{
                        ListeMatriculeMoyenne.forEach((MatriculeMoyenne_y)->{
                            MatriculeMoyenne_y.values().forEach((vy)->{
                                rang_x[0] =  vx>vy? rang_x[0]:rang_x[0]+1;
                            });
                        });
                        //HashMap<String,String> car {JAVA=Moyenne_Range} EX: {JAVA=10.5_2}
                        HashMap<String,String> MatiereRang = new HashMap<>();
                        MatiereRang.put(Matiere,vx+"_"+(rang_x[0]-1));

                        MatriculeMoyenne_x.keySet().forEach((k)->{
                            if(!mapMatriculeMatiereRang.containsKey(k)){
                                mapMatriculeMatiereRang.putIfAbsent(k,MatiereRang);
                            }else{
                                mapMatriculeMatiereRang.get(k).put(Matiere,vx+"_"+(rang_x[0]-1));
                            }

                        });

                    });

                });
            });

        });
        System.out.println("""
                ..................
                Classement effectué avec succes. Vous pouvez imprimer les bullettins!
                ..................""");
    }else{
        System.out.println("""
                -------------------
                Oups !
                Veuillez proceder d'abord au calcul des moyennes.
                --------------------""");
    }


  // System.out.println("mapMatriculeMatiereRang "+mapMatriculeMatiereRang);
}






}
