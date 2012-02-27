/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import java.io.IOException;
import kayttoliittyma.Tekstiliittyma;

/**
 * Asiakaslogiikka on tekstipohjaista käyttöliittymää ohjaava olio jonka
 * toiminta perustuu käyttäjän valintoihin
 *
 * @author Kati
 */
public class Asiakaslogiikka {

    Asiakkaat asiakkaat;
    Tekstiliittyma teksti;

    /**
     * Metodi suorita luo asiakkaat-olion ja käynnistää tekstipohjaisen
     * käyttöliittymän Metodi suorita ohjaa käyttäjän tekemien valintojen
     * perusteella pyyntöjä eteenpäin asiakas-olion metodeille
     */
    void suorita() throws IOException {

        asiakkaat = new Asiakkaat();
        teksti = new Tekstiliittyma();


        boolean lopetus = false;
        while (!lopetus) {
            int valinta = teksti.tulostaValikko();
            switch (valinta) {
                case 0: //Lopetus
                    System.out.println("Ohjelma suljetaan");
                    /**
                     * Lopuksi kirjoitetaan asiakkaat tiedostoon
                     */
                    asiakkaat.asiakkaatTiedostoon();
                    lopetus = true;
                    break;
                case 1: //Asiakastoiminnot
                    kasitteleAsiakasValinta(teksti.tulostaAsiakasValikko(), asiakkaat, teksti);
                    break;
                default: //Listaa valikko
                    valinta = teksti.tulostaValikko();
            }
        }

    }

    /**
     * Ohjaa käyttäjän valinnan perusteella pyynnön eteenpäin asiakastoiminnolle
     */
    private static void kasitteleAsiakasValinta(int asiakasValinta, Asiakkaat asiakkaat, Tekstiliittyma teksti) {

        switch (asiakasValinta) {
            case 0: // Palaa päävalikkoon
                System.out.println("Palataan päävalikkoon");
                return;
            case 1: // Listaa asiakkaat
                System.out.println("Asiakaslistaus");
                tulostaAsiakaslista(asiakkaat.listaaAsiakkaat());
                break;
            case 2: // Hae asiakas
                // Kysy hakuehto ja hae
                int hakutapa = teksti.kysyHakutapa();
                if (hakutapa == 1) {
                    System.out.println(asiakkaat.haeAsiakasAsiakasnumerolla(teksti.kysyAsiakasnumero()));
                }
                if (hakutapa == 2) {
                    System.out.println(asiakkaat.haeAsiakasNimella(teksti.kysyAsiakkaanNimi()));
                }
                break;
            case 3: // Lisää asiakas

                asiakkaat.lisaaAsiakas(teksti.kysyAsiakastiedot());
                System.out.println("Asiakas lisätty");
                break;
            case 4: // Poista asiakas 
                // merkitse asiakas arkistoiduksi
                //System.out.println("Siirry poistonäytölle");
                System.out.println(asiakkaat.poistaAsiakas(teksti.kysyPoistettavaAsiakas()));

                break;
            case 5: // Muuta asiakastietoja
                System.out.println("Siirry muutosnäytölle");
                Asiakas vanha = asiakkaat.haeAsiakasOlioAsiakasnumerolla(teksti.kysyAsiakasnumero());
                //Asiakas vanha = asiakkaat.haeMuutettavaAsiakasnumerolla(teksti.kysyAsiakasnumero());
                System.out.println(vanha.toString());
                Asiakas muutettu = teksti.kysyMuutettavaAsiakas();
                System.out.println(asiakkaat.muutaAsiakas(vanha, muutettu));
                // kysy hakuehto ja hae muutettavaksi
                break;
            default:
                return;
        }

    }

    /**
     * Metodi tulostaa asiakaslistan
     */
    private static void tulostaAsiakaslista(String asiakasLista) {

        System.out.println(asiakasLista);
    }
}
