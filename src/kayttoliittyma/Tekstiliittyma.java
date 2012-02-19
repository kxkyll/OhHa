/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import asiakaskortisto.Asiakas;
import asiakaskortisto.Asiakas.Tila;
import asiakaskortisto.Kayttaja;
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

    public Asiakas kysyAsiakastiedot() {
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
        String postinumero = "";
        while (postinumero.equals("")) {
            System.out.print("Anna asiakkaan postinumero: ");

            postinumero = lukija.nextLine();
        }
        String toimipaikka = "";
        while (toimipaikka.equals("")) {
            System.out.print("Anna asiakkaan postitoimipaikka: ");
            toimipaikka = lukija.nextLine();
        }
        String puhelin = "";
        while (puhelin.equals("")) {
            System.out.print("Anna puhelinnumero:");
            puhelin = lukija.nextLine();
        }
        String yhteyshenkilo = "";
        while (yhteyshenkilo.equals("")) {
            System.out.print("Anna yhteyshenkilön nimi:");
            yhteyshenkilo = lukija.nextLine();
        }
        String asiakkaaksituloPvm = formatter.format(tamaPaiva.getTime());

        return new Asiakas(
                "", nimi, katuosoite + " " + talonnumero,
                postinumero + " " + toimipaikka, puhelin, yhteyshenkilo, asiakkaaksituloPvm, Tila.NORMAALI);
    }

    public int kysyHakutapa() {
        System.out.println("Haetaanko asiakasnumerolla vai asiakkaan nimellä ?");
        int hakutapa = 0;
        while (hakutapa != 1 && hakutapa != 2) {
            System.out.println("1 - Hae asiakasnumerolla");
            System.out.println("2 - Hae asiakkaan nimellä");
            System.out.print("Anna valintasi: ");
            hakutapa = Integer.parseInt(lukija.nextLine());
        }
        return hakutapa;

    }
    
    public String kysyAsiakasnumero() {
        String asiakasnumero = "";
        while (asiakasnumero.equals("")) {
            System.out.print("Anna asiakasnumero:");
            asiakasnumero =lukija.nextLine();
        }
        return asiakasnumero;

    }
    
     public String kysyAsiakkaanNimi() {
        String asiakasnimi = "";
        while (asiakasnimi.equals("")) {
            System.out.print("Anna asiakkaan nimi:");
            asiakasnimi =lukija.nextLine();
        }
        return asiakasnimi;

    }

    public Kayttaja kysyKirjautumisTiedot() {
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
        return new Kayttaja(kayttajatunnus, salasana);
    }
}
