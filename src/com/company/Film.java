package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Film {

    public static void main(String[] args) {
	// write your code here
        int keuze;
        HashSet <FilmLijst> titels = new HashSet<>();
        do {
            System.out.println("Wat wilt u doen?");
            System.out.println("1. Filmlijst opvragen");
            System.out.println("2. Een film toevoegen. <Return> om te stoppen" );
            System.out.println("3. Stoppen");

            Scanner invoer = new Scanner(System.in);
            keuze = Integer.parseInt(invoer.nextLine());

            switch (keuze){
                case 1: FilmLijst filmOpvragen = new FilmLijst("");
                    filmOpvragen.getTitel();
                    filmlijst(titels);
                    break;
                case 2: //FilmLijst filmToevoegen = new FilmLijst("");
                    String titel;
                    do {
                        System.out.println("Geef een titel in");
                        titel = invoer.nextLine();
                        if (!titel.equals (""))
                            titels.add(new FilmLijst(titel));
                    }while (!titel.equals(""));
                    break;
                case 4:

                case 3:
                    if (keuze >= 3 ){
                        System.out.println("Einde programma");
                        break;
                    }

            }

        }while (keuze < 3 );

    }

    private static void filmlijst(HashSet<FilmLijst> titels) {
        System.out.println("Selecteer uw film");
        //String filmKeuze = invoer.nextLine();

        titels.add(new FilmLijst("Rise of the Guardians"));
        titels.add(new FilmLijst("K3 en het magische Medaillon"));
        titels.add(new FilmLijst("K3 en het ijsprinsesje"));
        titels.add(new FilmLijst("K3 en de kattenprins"));
        titels.add(new FilmLijst("K3 Bengeltjes"));
        titels.add(new FilmLijst("Schindler's List"));
        titels.add(new FilmLijst("Total Recall"));
        titels.add(new FilmLijst("Bambi"));
        ArrayList<FilmLijst> filmKeuzeSort = new ArrayList<>(titels);

        Collections.sort(filmKeuzeSort);
        System.out.println("Filmlijst: ");
        for (int i = 0; i < filmKeuzeSort.size() ; i++) {
            System.out.printf("%d %s", i+1, filmKeuzeSort.get(i));//(filmKeuzeSort.get(i+1));

        }
    }
}
class FilmLijst implements Comparable<FilmLijst>{
    private String titel;

    @Override
    public String toString() {
        return String.format(titel);
    }

    public FilmLijst(String titel){
        this.titel = titel;


    }

    public String getTitel() {
        return titel;
    }


    @Override
    public int compareTo(FilmLijst o) {
        return this.titel.compareTo(o.titel);
    }
}
