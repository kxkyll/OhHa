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
 *
 * @author Kati Käyttäjän käyttäjätunnuksen ja salasanan tarkistus
 */
public class Kirjaudu {

    /**
     * Pyydetään käyttäjältä käyttäjätunnus ja salasana ja tarkastetaan
     * täsmäävätkö ne salasanatiedostossa oleviin
     */
    private String tiedostonNimi = "hys.hys";
    private KayttajatiedostonKasittelija salatiedosto;
    private HashMap<String, Kayttaja> kayttajalista = new HashMap<String, Kayttaja>();

    public Kirjaudu() throws IOException {
        salatiedosto = new KayttajatiedostonKasittelija(this);
        kayttajalista = salatiedosto.lueTiedostosta();
    }

    public String getTiedostonNimi() {
        return tiedostonNimi;
    }

    public Rooli tarkistaKirjautuminen(Kayttaja kirjautuja) {
        /**
         * Tarkistetaan löytyvätkö annettu käyttäjätunnus & salasanapari
         * tiedostosta
         */
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

    public void tulostaSalasanalista() {
        for (String kayttajatunnus : kayttajalista.keySet()) {
            System.out.println(kayttajatunnus);
        }
    }
}