package com.universite;

import java.util.Scanner;

public class Gestion {

    private static final String login="Admin";
    private static String password="1478";


    public static boolean connexion(){




        Scanner clavier = new Scanner(System.in);
        System.out.print("\nLogin: "); String log = clavier.nextLine();
        System.out.print("Mot de passe: "); String passwd = clavier.nextLine();
        return log.equals(login) && passwd.equals(password) ;
    }
}
