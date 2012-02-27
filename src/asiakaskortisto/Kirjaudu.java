/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import asiakaskortisto.Kayttaja.Rooli;
import java.io.IOException;
import java.util.HashMap;
import tiedostonkasittelija.KayttajatiedostonKasittelija;
/**
     * Luokka Kirjaudu tarkistaa täsmäävätkö käyttäjän antama käyttäjätunnus ja
     * salasana ohjelman tallentamiin vastinkappaleisiin
     * Metodi käyttää tiedostonlukemiseen Käyttäjätiedostonkäsittelijäoliota
     * 
     * * @author Kati 
     */

public class Kirjaudu {
/**
 *
  * Käyttäjän käyttäjätunnuksen ja salasanan tarkistus
 */
    
    private String tiedostonNimi = "hys.hys";
    private KayttajatiedostonKasittelija salatiedosto;
    private HashMap<String, Kayttaja> kayttajalista = new HashMap<String, Kayttaja>();

    /** Konstruktori pyytää käyttäjätiedostonkäsittelijää lukemaan käyttjätiedot salsanatiedostosta*/
    public Kirjaudu() throws IOException {
        salatiedosto = new KayttajatiedostonKasittelija(this);
        kayttajalista = salatiedosto.lueTiedostosta();
    }

    public String getTiedostonNimi() {
        return tiedostonNimi;
    }

    /**
         * Tarkistetaan löytyvätkö annettu käyttäjätunnus & salasanapari
         * tiedostosta
         */
    public Rooli tarkistaKirjautuminen(Kayttaja kirjautuja) {
        
        if (kayttajalista.isEmpty()) {
            return null;
        }
        for (String kayttajatunnus : kayttajalista.keySet()) {

            Kayttaja k = kayttajalista.get(kirjautuja.getKayttajaTunnus());
            if (k == null) { // jos käyttäjätunnusta ei ole listalla
                return null;
            }
            if (kirjautuja.getSalasana().equals(k.getSalasana())) {
                return (Rooli) k.getRooli();

            }
        }
        return null;

    }
//    public void tulostaSalasanalista() {
//        for (String kayttajatunnus : kayttajalista.keySet()) {
//            System.out.println(kayttajatunnus);
//        }
//    }
}