/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import asiakaskortisto.Kayttaja.Rooli;
import java.io.IOException;
import kayttoliittyma.Tekstiliittyma;

/**
 * Asiakaskortisto toimii pääohjelmana ohjaten käyttöliittymältä tulevia
 * valintoja eteenpäin
 *
 * @author Kati
 */
public class Asiakaskortisto {

    public static void main(String[] args) throws IOException {
        /**
         * Asiakaskortiston pääohjelma
         */

        Tekstiliittyma teksti = new Tekstiliittyma();
        Asiakaslogiikka logiikka = new Asiakaslogiikka();
        Kirjaudu kirjaudu = new Kirjaudu();
        int kirjautumisLaskuri = 0;
        Rooli kirjautujanrooli = null;

        while (kirjautumisLaskuri < 3) {

            kirjautujanrooli = kirjaudu.tarkistaKirjautuminen(teksti.kysyKirjautumisTiedot());
            if (kirjautujanrooli != null) {
                //System.out.println("kirjautuminen ok, kirjautujanrooli ei ole null");
                break;
            } else {
                System.out.println("Antamasi kirjautumistiedot eivät ole oikein");
                kirjautumisLaskuri++;
            }
        }
        if (kirjautujanrooli == null) {
            System.out.println("Kirjautuminen ei onnistunut");
            System.exit(0);
        }

        logiikka.suorita();

        }

    }
