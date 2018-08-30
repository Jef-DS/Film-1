package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.*;

public class Film {

    public static void main(String[] args) throws IOException {

        int keuze = 0;
        ArrayList<FilmLijst> titels = new ArrayList<>();
        titels.add(new FilmLijst("Rise of the Guardians"));
        titels.add(new FilmLijst("K3 en het magische Medaillon"));
        titels.add(new FilmLijst("K3 en het ijsprinsesje"));
        titels.add(new FilmLijst("K3 en de kattenprins"));
        titels.add(new FilmLijst("K3 Bengeltjes"));
        titels.add(new FilmLijst("Schindler's List"));
        titels.add(new FilmLijst("Total Recall"));
        titels.add(new FilmLijst("Bambi"));
        Collections.sort(titels);
        FilmLijst filmOpvragen = new FilmLijst("");

        do {
            toonMenu();
            Scanner invoer = new Scanner(System.in);
            try {
                keuze = Integer.parseInt(invoer.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Geen geldige input " + ex.getMessage());
            }


            switch (keuze) {
                case 1:
                    filmsOpvragen(titels, invoer, filmOpvragen);
                    break;
                case 2:
                    filmToevoegen(titels, invoer);
                    break;
                case 3:
                    filmsVerwijderen(titels, invoer);
                    break;
                case 4:
                    try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("tekst.txt")))) {
                        for (int i = 0; i < titels.size(); i++) {
                            writer.printf("%d. %s %n", i + 1, titels.get(i));

                        }
                    }
                    break;

                case 5:
                    stopProgramma(keuze);
                    break;
            }

        } while (keuze < 4 && keuze != 1);

    }


    private static void stopProgramma(int keuze) {
        if (keuze >= 5) {
            System.out.println("Einde programma");
        }
        return;
    }


    private static void filmsVerwijderen(ArrayList<FilmLijst> titels, Scanner invoer) {
        String titelverWijderen;
        filmlijst(titels);
        Iterator<FilmLijst> filmLijstIterator = titels.iterator();
        System.out.println("Geef een titel in");
        titelverWijderen = invoer.nextLine();
        while (filmLijstIterator.hasNext()) {
            FilmLijst f = filmLijstIterator.next();
            if (titelverWijderen.equals(f.getTitel())) {
                filmLijstIterator.remove();
            }
        }
        for (FilmLijst film : titels) {
            System.out.println(film);
        }
        do {
            System.out.println("Geef een titel in");
            titelverWijderen = invoer.nextLine();
            if (!titelverWijderen.equals("")) {
                titels.remove(new FilmLijst(titelverWijderen));
            }
        } while (!titelverWijderen.equals(""));
    }

    private static void filmToevoegen(ArrayList<FilmLijst> titels, Scanner invoer) {
        String titel;
        do {
            System.out.println("Geef een titel in");
            titel = invoer.nextLine();
            if (!titel.equals(""))
                titels.add(new FilmLijst(titel));
        } while (!titel.equals(""));
    }


    private static void filmsOpvragen(ArrayList<FilmLijst> titels, Scanner invoer, FilmLijst filmOpvragen) {
        filmOpvragen.getTitel();
        filmlijst(titels);
        System.out.println("Wilt u een film bekijken? Geef ja of nee in");
        String filmKeuze = invoer.nextLine();
        String filmKeuzeCaps = filmKeuze.toUpperCase();
        int filmSelecteren;
        while (filmKeuze.equalsIgnoreCase("Ja") || filmKeuze.equalsIgnoreCase("Nee")) {
            try {
                if (filmKeuze.equalsIgnoreCase("JA")) {
                    System.out.println("Welke film wilt u bekijken? Geef de overeenkomstige nummer van de filmlijst in");
                    filmSelecteren = Integer.parseInt(invoer.nextLine());
                    FilmLijst gekozenTitel = titels.get(filmSelecteren - 1);
                    System.out.printf("De gekozen film is \"%s.\"", gekozenTitel);

                } else if (filmKeuze.equalsIgnoreCase("NEE")) {
                    System.out.printf("Einde programma");
                    return;
                }
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Geen geldige input: " + ex.getMessage());
            }
        }
    }


    private static void toonMenu() {
        System.out.println("Wat wilt u doen?");
        System.out.println("1. Filmlijst opvragen");
        System.out.println("2. Een film toevoegen. <Return> om te stoppen");
        System.out.println("3. Een film verwijderen. <Return om te stoppen");
        System.out.println("4. Een tekstbestand met films genereren");
        System.out.println("5. Stoppen");
    }


    private static void filmlijst(ArrayList<FilmLijst> titels) {
        System.out.println("Selecteer uw film");
        System.out.println("Filmlijst: ");
        for (int i = 0; i < titels.size(); i++) {
            System.out.printf("%d. %s %n", i + 1, titels.get(i));
        }
    }
}


class FilmLijst implements Comparable<FilmLijst> {
    private String titel;

    @Override
    public String toString() {
        return titel;
    }

    public FilmLijst(String titel) {
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