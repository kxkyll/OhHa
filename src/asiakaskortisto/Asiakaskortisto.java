/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import java.io.IOException;
import kayttoliittyma.Tekstiliittyma;

/** Asiakaskortisto toimii pääohjelmana ohjaten käyttöliittymältä 
 *  tulevia valintoja eteenpäin
 * @author Kati
 */
public class Asiakaskortisto {

    public static void main(String[] args) throws IOException {
        /** Asiakaskortiston pääohjelma */
        Asiakastoiminnot asiakas = new Asiakastoiminnot();
        Kirjaudu kirjaudu = new Kirjaudu();
        Tekstiliittyma teksti = new Tekstiliittyma();
        

        boolean lopetus = false;
        while (!lopetus) {
            int valinta = teksti.tulostaValikko();
            switch (valinta) {
                case 0: //Lopetus
                    System.out.println("Ohjelma suljetaan");
                    //todo: kirjoitetaan asiakkaat tiedostoon
                    lopetus = true;
                    break;
                case 1: //Asiakastoiminnot
                    kasitteleAsiakasValinta(teksti.tulostaAsiakasValikko(), asiakas, teksti);
                    break;
                default: //Listaa valikko
                    valinta = teksti.tulostaValikko();
            }
        }

    }

    private static void kasitteleAsiakasValinta(int asiakasValinta, Asiakastoiminnot asiakas, Tekstiliittyma teksti) {
        /** Ohjaa käyttäjän valinnan perusteella pyynnön eteenpäin asiakastoiminnolle*/
        switch (asiakasValinta) {
            case 0: // Palaa päävalikkoon
                System.out.println("Palataan päävalikkoon");
                return;
            case 1: // Listaa asiakkaat
                System.out.println("Asiakaslistaus");
                tulostaAsiakaslista(asiakas.listaaAsiakkaat());
                break;
            case 2: // Hae asiakas
                // todo kysy hakuehto ja hae
                break;
            case 3: // Lisää asiakas
                
                asiakas.lisaaAsiakas(teksti.kysyAsiakastiedot());
                System.out.println("Asiakas lisätty");
               //asiakas.haeAsiakas();
                
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
        /** tulostetaan asiakaslista */
        System.out.println(asiakasLista);
    }
}
