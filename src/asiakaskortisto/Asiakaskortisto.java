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
 * käyttäjää kirjautumaan ja mikäli kirjautuminen onnistuu ohjaa kontrollin
 * asiakaslogiikka luokalle
 *
 * @author Kati
 */
public class Asiakaskortisto {

    /**
     * Asiakaskortiston pääohjelma Asiakaskortistoa voidaan käyttää joko
     * tekstipohjaisen käyttöliittymän avulla tai graafisen käyttöliittymän
     * avulla
     */
    public static void main(String[] args) throws IOException {

        Tekstiliittyma teksti = new Tekstiliittyma();
        Asiakaslogiikka logiikka = new Asiakaslogiikka();
        Kirjaudu kirjaudu = new Kirjaudu();
        int kirjautumisLaskuri = 0;
        Rooli kirjautujanrooli = null;



//---------------------------------tekstikayttoliittyma-------------------------        
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
//        if (kirjautujanrooli == Rooli.ESIMIES) {
//            logiikka.suorita();
//        }
//        if (kirjautujanrooli == Rooli.TYONTEKIJA) {
//            logiikka.suorita();
//        }
//        if (kirjautujanrooli == Rooli.YLLAPITO) {
//            logiikka.suorita();
//        }
//---------------------------------tekstikayttoliittyma loppu-------------------        

//---------------------------------graafinenkayttoliittyma----------------------        
        KirjautumisUI gkirjaudu = new KirjautumisUI();
        gkirjaudu.kaynnisty();

//---------------------------------tekstikayttoliittyma loppu-------------------        
    }
}
