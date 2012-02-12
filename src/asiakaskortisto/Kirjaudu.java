/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import asiakaskortisto.Kayttajatiedot.Rooli;
import java.io.IOException;
import java.util.HashMap;
import tiedostonkasittelija.KayttajatiedostonKasittelija;

/**
 *
 * @author Kati
 * Käyttäjän käyttäjätunnuksen ja salasanan tarkistus
 */
public class Kirjaudu {

    /**
     * Pyydetään käyttäjältä käyttäjätunnus ja salasana ja tarkastetaan
     * täsmäävätkö ne salasanatiedostossa oleviin
     */
    private String tiedostonNimi = "hys.hys";
    
    private KayttajatiedostonKasittelija salatiedosto;
    
    private HashMap<String, Kayttajatiedot> kayttajalista = new HashMap<String, Kayttajatiedot>();

    public Kirjaudu() throws IOException {
        salatiedosto = new KayttajatiedostonKasittelija(this);
        kayttajalista = salatiedosto.lueTiedostosta();
    }

    public String getTiedostonNimi() {
        return tiedostonNimi;
    }

    
    public Rooli tarkistaKirjautuminen(Kayttajatiedot kirjautuja) {
        /**
         * Tarkistetaan löytyvätkö annettu käyttäjätunnus & salasanapari
         * tiedostosta
         */
        for (String kayttajatunnus : kayttajalista.keySet()) {
            Kayttajatiedot k = kayttajalista.get(kirjautuja.getKayttajaTunnus());
            if (kirjautuja.getSalasana().equals(k.getSalasana())) {
                return (Rooli) k.getRooli();
            }
    }
        return null;
    }
    
    public void tulostaSalasanalista() {
        for (String kayttajatunnus : kayttajalista.keySet()) {
            System.out.println(kayttajatunnus);
        }
    }
}