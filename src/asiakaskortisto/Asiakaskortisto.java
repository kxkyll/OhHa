/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import asiakaskortisto.Kayttaja.Rooli;
import java.io.IOException;
import kayttoliittyma.KirjautumisUI;
import kayttoliittyma.Tekstiliittyma;

/**
 * Asiakaskortisto toimii asiakaskortisto-ohjelman pääohjelmana ohjaten 
 * käyttäjää kirjautumaan ja mikäli kirjautuminen onnistuu ohjaa 
 * kontrollin asiakaslogiikka luokalle
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
        
        

//        while (kirjautumisLaskuri < 3) {
//
//            kirjautujanrooli = kirjaudu.tarkistaKirjautuminen(teksti.kysyKirjautumisTiedot());
//            if (kirjautujanrooli != null) {
//                break;
//            } else {
//                System.out.println("Antamasi kirjautumistiedot eivät ole oikein");
//                kirjautumisLaskuri++;
//            }
//        }
//        if (kirjautujanrooli == null) {
//            System.out.println("Kirjautuminen ei onnistunut");
//            System.exit(0);
//        }
//
//        logiikka.suorita();
        KirjautumisUI gkirjaudu = new KirjautumisUI();
        gkirjaudu.kaynnisty();

        }

    }
