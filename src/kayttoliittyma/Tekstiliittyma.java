/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import asiakaskortisto.Asiakastiedot;
import asiakaskortisto.Asiakastiedot.Tila;
import asiakaskortisto.Kayttajatiedot;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Tekstipohjainen käyttöliittymä Asiakaskortisto-ohjelmaan
 *
 * @author Kati
 */
public class Tekstiliittyma {

    /**
     * Asiakaskortiston tektipohjainen valikko
     */
    static Scanner lukija = new Scanner(System.in);

    public int tulostaValikko() {
        /**
         * Tulostetaan päävalikko
         */
        System.out.println();
        System.out.println("Asiakaskortisto");
        System.out.println("1 - Asiakastoiminnot");
        //System.out.println("2 - Työtehtävätoiminnot");
        //System.out.println("3 - Laskutustoiminnot");
        System.out.println("0 - Lopetus");
        System.out.print("Anna valintasi: ");
        return Integer.parseInt(lukija.nextLine());
    }

    public int tulostaAsiakasValikko() {
        /**
         * Tulostetaan asiakasvalikko
         */
        System.out.println();
        System.out.println("Asiakastoiminnot");
        System.out.println("1 - Listaa asiakkaat");
        System.out.println("2 - Hae asiakas");
        System.out.println("3 - Lisää asiakas");
        System.out.println("4 - Poista asiakas");
        System.out.println("5 - Muuta asiakastietoja");
        System.out.println("0 - Paluu päävalikkoon");
        System.out.print("Anna valintasi: ");
        return Integer.parseInt(lukija.nextLine());
    }

    public Asiakastiedot kysyAsiakastiedot() {
        /**
         * Kysytään asiakastiedot uutta asiakasta lisättäessä
         */
        Calendar tamaPaiva = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");


        System.out.println("Asiakastoiminnot");
        System.out.println("Lisää asiakas");
        System.out.println("");
        String nimi = "";
        while (nimi.equals("")) {
            System.out.print("Anna asiakkaan nimi: ");
            nimi = lukija.nextLine();
        }
        String katuosoite = "";
        while (katuosoite.equals("")) {
            System.out.print("Anna asiakkaan katuosoite: ");
            katuosoite = lukija.nextLine();
        }
        String talonnumero = "";
        while (talonnumero.equals("")) {
            System.out.print("Anna asiakkaan talonnumero: ");
            talonnumero = lukija.nextLine();
        }
        System.out.print("Anna asiakkaan postinumero: ");
        String postinumero = lukija.nextLine();
        System.out.print("Anna asiakkaan postitoimipaikka: ");
        String toimipaikka = lukija.nextLine();
        System.out.print("Anna puhelinnumero:");
        String puhelin = lukija.nextLine();
        System.out.print("Anna yhteyshenkilön nimi:");
        String yhteyshenkilo = lukija.nextLine();
        String asiakkaaksituloPvm = formatter.format(tamaPaiva.getTime());

        return new Asiakastiedot("", nimi, katuosoite + " " + talonnumero,
                postinumero + " " + toimipaikka, puhelin, yhteyshenkilo, asiakkaaksituloPvm, Tila.NORMAALI);
    }

    public Kayttajatiedot kysyKirjautumisTiedot() {
        String kayttajatunnus = "";
        String salasana = "";
        while (kayttajatunnus.equals("")) {
            System.out.print("Anna käyttäjätunnus: ");
            kayttajatunnus = lukija.nextLine();
        }
        while (salasana.equals("")) {
            System.out.print("Anna salasana: ");
            salasana = lukija.nextLine();
        }
        return new Kayttajatiedot(kayttajatunnus, salasana);
    }
}
