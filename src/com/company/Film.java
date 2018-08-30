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
        ArrayList<FilmLijst> titels = FilmLijst.getFilmLijsts();
        Collections.sort(titels);
        ArrayList<FilmLijst> releaseJaar = FilmLijst.getReleasejaar();
        ArrayList<FilmLijst> regisseur = FilmLijst.getRegisseur();


        do {
            FilmLijst.toonMenu();
            Scanner invoer = new Scanner(System.in);
            try {
                keuze = Integer.parseInt(invoer.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Geen geldige input " + ex.getMessage());
            }


            switch (keuze) {
                case 1:
                    filmsOpvragen(titels, invoer);
                    break;
                case 2:
                    FilmLijst.filmToevoegen(titels, invoer);
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
                    detailsOpvragen(titels, releaseJaar, invoer);
                    break;
                case 6:
                    FilmLijst.stopProgramma(keuze);
                    break;
            }
        } while (keuze <= 6 && keuze != 1);
    }


    private static void detailsOpvragen(ArrayList<FilmLijst> titels, ArrayList<FilmLijst> releaseJaar, Scanner invoer) {
        filmlijst(titels);
        System.out.println("Van welke film wilt u details zien? Geef het overeenkomstige nummer in.");
        int filmDetails = Integer.parseInt(invoer.nextLine());
        FilmLijst gekozenDetails = releaseJaar.get(filmDetails - 1);
        FilmLijst gekozenDetailsreg = FilmLijst.getRegisseur().get(filmDetails-1);
        System.out.printf("Het releasejaar van %s is: %s%n",filmDetails, gekozenDetails);
        System.out.printf("De regisseur van %s is: %s%n", filmDetails, gekozenDetailsreg );
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

    private static void filmsOpvragen(ArrayList<FilmLijst> titels, Scanner invoer) {
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
                    return;

                } else if (filmKeuze.equalsIgnoreCase("NEE")) {
                    System.out.printf("Einde programma");
                    return;
                }
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Geen geldige input: " + ex.getMessage());
            }
        }
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
    private String regisseur;
    private int releaseJaar;

    public FilmLijst(String titel) {
        this.titel = titel;

    }

    public FilmLijst() {

    }

    @Override
    public String toString() {
        return titel;
    }


    public String getTitel() {
        return titel;
    }

    @Override
    public int compareTo(FilmLijst o) {
        return this.titel.compareTo(o.titel);
    }

    public static void toonMenu() {
        System.out.println("Wat wilt u doen?");
        System.out.println("1. Filmlijst opvragen");
        System.out.println("2. Een film toevoegen. <Return> om te stoppen");
        System.out.println("3. Een film verwijderen. <Return om te stoppen");
        System.out.println("4. Een tekstbestand met films genereren");
        System.out.println("5. Details bekijken");
        System.out.println("6. Stoppen");
    }

    public static ArrayList<FilmLijst> getReleasejaar() {
        ArrayList<FilmLijst> releaseJaar = new ArrayList<>();
        releaseJaar.add(new FilmLijst("1942"));
        releaseJaar.add(new FilmLijst("2012"));
        releaseJaar.add(new FilmLijst("2007"));
        releaseJaar.add(new FilmLijst("2006"));
        releaseJaar.add(new FilmLijst("2004"));
        releaseJaar.add(new FilmLijst("2012"));
        releaseJaar.add(new FilmLijst("1943"));
        releaseJaar.add(new FilmLijst("1990"));
        return releaseJaar;
    }

    public static ArrayList<FilmLijst> getFilmLijsts() {
        ArrayList<FilmLijst> titels = new ArrayList<>();
        titels.add(new FilmLijst("Rise of the Guardians"));
        titels.add(new FilmLijst("K3 en het magische Medaillon"));
        titels.add(new FilmLijst("K3 en het ijsprinsesje"));
        titels.add(new FilmLijst("K3 en de kattenprins"));
        titels.add(new FilmLijst("K3 Bengeltjes"));
        titels.add(new FilmLijst("Schindler's List"));
        titels.add(new FilmLijst("Total Recall"));
        titels.add(new FilmLijst("Bambi"));
        return titels;
    }

    public static ArrayList<FilmLijst> getRegisseur() {
        ArrayList<FilmLijst>getRegisseur = new ArrayList<>();
        getRegisseur.add(new FilmLijst("David Hand"));
        getRegisseur.add(new FilmLijst("Bart van Leemputten"));
        getRegisseur.add(new FilmLijst("Matthias Temmermans"));
        getRegisseur.add(new FilmLijst("Indra Siera"));
        getRegisseur.add(new FilmLijst("Indra Siera"));
        getRegisseur.add(new FilmLijst("Peter Ramsey"));
        getRegisseur.add(new FilmLijst("Steven Spielberg"));
        getRegisseur.add(new FilmLijst("Paul Verhoeven"));
        return getRegisseur;
    }

    public static void filmToevoegen(ArrayList<FilmLijst> titels, Scanner invoer) {
        String titel;
        do {
            System.out.println("Geef een titel in");
            titel = invoer.nextLine();
            if (!titel.equals(""))
                titels.add(new FilmLijst(titel));
        } while (!titel.equals(""));

    }

    public static void stopProgramma(int keuze) {
        if (keuze >= 6) {
            System.out.println("Einde programma");
        }
        return;
    }
}