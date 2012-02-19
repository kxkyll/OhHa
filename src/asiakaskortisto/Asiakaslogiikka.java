/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import java.io.IOException;
import kayttoliittyma.Tekstiliittyma;

/**
 *
 * @author Kati
 */
public class Asiakaslogiikka {

    Asiakkaat asiakkaat;
    Tekstiliittyma teksti;

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

    
       

    private static void kasitteleAsiakasValinta(int asiakasValinta, Asiakkaat asiakas, Tekstiliittyma teksti) {
        /**
         * Ohjaa käyttäjän valinnan perusteella pyynnön eteenpäin
         * asiakastoiminnolle
         */
        switch (asiakasValinta) {
            case 0: // Palaa päävalikkoon
                System.out.println("Palataan päävalikkoon");
                return;
            case 1: // Listaa asiakkaat
                System.out.println("Asiakaslistaus");
                tulostaAsiakaslista(asiakas.listaaAsiakkaat());
                break;
            case 2: // Hae asiakas
                // Kysy hakuehto ja hae
                int hakutapa = teksti.kysyHakutapa();
                if (hakutapa == 1) {
                    System.out.println(asiakas.haeAsiakasAsiakasnumerolla(teksti.kysyAsiakasnumero()));
                } 
                if (hakutapa == 2) {
                    System.out.println(asiakas.haeAsiakasNimella(teksti.kysyAsiakkaanNimi()));
                }
                break;
            case 3: // Lisää asiakas

                asiakas.lisaaAsiakas(teksti.kysyAsiakastiedot());
                System.out.println("Asiakas lisätty");
                break;
            case 4: // Poista asiakas 
                System.out.println("Siirry poistonäytölle");
                // ==merkitse asiakas arkistoiduksi
                break;
            case 5: // Muuta asiakastietoja
                System.out.println("Siirry muutosnäytölle");
                // kysy hakuehto ja hae muutettavaksi
                break;
            default:
                return;
        }

    }

    private static void tulostaAsiakaslista(String asiakasLista) {
        /**
         * tulostetaan asiakaslista
         */
        System.out.println(asiakasLista);
    }
}
