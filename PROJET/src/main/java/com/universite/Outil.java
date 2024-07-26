package com.universite;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

public class Outil {


    /*
    |Vérifier si valeur entrée au clavier est un double
    */
    static boolean estConvertibleEnDouble(String chaine){
        try{
            Double.valueOf(chaine);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    /*
    |Vérifier si valeur entrée au clavier est un double
    */
    static boolean estConvertibleEnInt(String chaine){
        try{
            Integer.valueOf(chaine);
            return true;
        }catch(Exception e){
            return false;
        }
    }


    /*
    |Faire choix Menu
    */
    public static int faireChoix(){
        Scanner Clavier = new Scanner(System.in);
        System.out.print("Veuillez choisir: ");String choix=Clavier.next();
        if(Outil.estConvertibleEnInt(choix)){
            return Integer.valueOf(choix);
        }else{
            return faireChoix();
            // System.out.print("Veuillez choisir: ");choix=Clavier.next();

        }

    }

    /*
    |Faire choix Menu
    */
    public static void enCoursConception(){
        System.out.print("En cours de conception");
        Universite.menu();
    }

    public static boolean mapContientKey(HashMap map,String key){
        AtomicInteger x= new AtomicInteger();
        map.forEach((k,v)->{ if(k.equals(key)){  x.getAndIncrement(); }});
        if(x.intValue()==1){
            return true;
        }else{
            return false;
        }
    }

    public static boolean listMatieresContientmatiere(ArrayList<Matiere> list, String matiere){
        AtomicInteger x= new AtomicInteger();
        list.forEach((v)->{ if(v.getdesignation().equals(matiere)){  x.getAndIncrement(); }});
        if(x.intValue()==1){
            return true;
        }else{
            return false;
        }
    }



    public static void  seederDonnees(){
        seedAjoutClasses();
        seedAjoutMatieres();
        seedAjoutEtudiants();
        seedAjoutnotes();
        System.out.println("""
                ..................
                Remplissage[Classes, MatiEre, Etudiant, Notes] terminE avec succEs
                ..................""");
    }




    //SEMENCE
    public static void seedAjoutClasses(){
        Classe.listelasses.add("L1_A1");
        Classe.listelasses.add("L1_A2");
        Classe.listelasses.add("L1_B1");
        Classe.listelasses.add("L1_B2");

    }
    public static void seedAjoutMatieres(){
        Matiere martiere01 = new Matiere("ALGO",1);
        Matiere martiere02 = new Matiere("LANG_C",1);
        Matiere martiere03 = new Matiere("JAVA",2);
        Matiere martiere04 = new Matiere("DATA",3);
        Matiere martiere05 = new Matiere("RESEAU",2);
        Matiere martiere06 = new Matiere();martiere06.setdesignation("PHP"); martiere06.setCoeficient(1);

        Matiere.listMatieres.add(martiere01);
        Matiere.listMatieres.add(martiere02);
        Matiere.listMatieres.add(martiere03);
        Matiere.listMatieres.add(martiere04);
        Matiere.listMatieres.add(martiere05);
        Matiere.listMatieres.add(martiere06);
    }

    public static void seedAjoutEtudiants(){
        Etudiant etudiants01 = new Etudiant("AKO","AKO","M","10-10-2000","0101","AA","L1","L1_A1");
        Etudiant etudiants02 = new Etudiant("BABA","ANO","M","11-09-1996","0202","BB","L1","L1_A1");
        Etudiant etudiants03 = new Etudiant("MAKEBA","TATOU","F","18-03-2000","0303","CC","L1","L1_A1");
        Etudiant etudiants04 = new Etudiant("OLA","AMZI","F","31-12-2001","0404","DD","L1","L1_B1");
        Etudiant etudiants05 = new Etudiant("LISA","NOBO","M","27-07-2010","0505","EE","L1","L1_B2");

        Etudiant.listEtudiant.add(etudiants01);
        Etudiant.listEtudiant.add(etudiants02);
        Etudiant.listEtudiant.add(etudiants03);
        Etudiant.listEtudiant.add(etudiants04);
        Etudiant.listEtudiant.add(etudiants05);
    }

//Remplissage automatique de notes pour les étudiants    
    public static void  seedAjoutnotes(){
		for(Etudiant etudiant: Etudiant.listEtudiant){
		
		    HashMap<String, ArrayList<Double>> mapMatierelisteNote = new HashMap<>();
		    for(Matiere matiere: Matiere.listMatieres){
		        // instance de nombre Aléatoire
		        Random rand = new Random();
		        //liste temporaire pour nombre de note
		        ArrayList <Double> listeNotes = new ArrayList<>();
		        for(int i=0;i<4;i++){
		            Double note = Double.valueOf(""+rand.nextInt(3,20));
		            listeNotes.add(note);
		        }
		        mapMatierelisteNote.put(matiere.getdesignation(),listeNotes);
		
		    }
		    Evaluation.mapMatriculeMatiereListeNotes.put(etudiant.getMatricule(),mapMatierelisteNote);
		}



    }




//Somme de liste Double
    public static Double sommeListeDouble(ArrayList<Double> listeDoubl){
        Double somme =0.0;
        for(Double num:listeDoubl){
            somme+=num;
        }
        return somme;
    }


    //Somme de liste Double
    public static int sommeListeInteger(ArrayList<Integer> listeInt){
        Integer somme =0;
        for(Integer num:listeInt){
            somme+=num;
        }
        return somme;
    }

    //Decision final redouble ou Admis
    public static String estAdmisOuRedouble(Double moyenne){
        if(moyenne>=10){
            return "Admis";
        }
    return "Redouble";

    }




    /*
    Créateur de fichier

     */
    public static File fichierBulletin(String nomFihier){

        File f = new File(nomFihier);
        try{
            f.createNewFile();

        }catch (Exception e){
            System.out.println(e);
        }
        return f;
    }

    /*
    Remplissage de fichier

     */
public static  void remplissageFichier(String nomFichierSansExt, ArrayList<String> tableauContenu){

    File bullt = fichierBulletin(nomFichierSansExt+".txt");
    String content = "";

        for(String line:tableauContenu){
            content+=line;
        }
        try {
        Files.writeString(Path.of(bullt.getPath()),content);
            System.out.println("""
                        ----------------------------
                        Bulletins imprimEs avec succEs.
                        Route: ./Bulletins
                        ----------------------------
                        """);
    }catch (Exception e){
        System.out.println("Erreur d'impression du bulletin");
    }

}

    /*
    Création de Repertoire-----
     */
    public static void environnementInitial(){
        File d = new File("Bulletins");
        if(!d.exists()){
            d.mkdir();
        }
    }

}
