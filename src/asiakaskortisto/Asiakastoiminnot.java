/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import java.io.IOException;
import java.util.HashMap;
import tiedostonkasittelija.AsiakastiedostonKasittelija;

/**
 * Asiakaskortisto-ohjelman sovelluslogiikka
 *
 * @author Kati
 */
public class Asiakastoiminnot {

    /**
     * Asiakastoiminnot
     */
    private String tiedostonNimi = "asiakastiedosto.txt";
    private HashMap<String, Asiakastiedot> asiakaslista = new HashMap<String, Asiakastiedot>();
    AsiakastiedostonKasittelija tiedosto;
    private int suurinAsiakasnumero;

    public Asiakastoiminnot() throws IOException {
        tiedosto = new AsiakastiedostonKasittelija(this);
        asiakaslista = tiedosto.lueAsiakkaatTiedostosta();
        //todo: hae listalta suurin asiakasnumero ja sijoita se suurinAsiakasnumerokenttään
        suurinAsiakasnumero = 10000;
    }

    public String getTiedostonNimi() {
        return tiedostonNimi;
    }

    public String listaaAsiakkaat() {
        /**
         * Muotoiltu asiakaslistaus
         */
        String listalla =
                "" + String.format("%-25s", "Asiakasnumero ")
                + String.format("%-25s", "Asiakkaan nimi ")
                + String.format("%-25s", "Katuosoite ")
                + String.format("%-25s", "Postiosoite ")
                + String.format("%-25s", "Puhelinnumero")
                + String.format("%-25s", "Yhteyshenkilo \n");

        // Tässä jokin ongelma, eka asiakasrivi siftaantuu noin 10 merkillä oikealle
        // muut tulostuvat oikein
        for (String asiakasNimi : asiakaslista.keySet()) {
            listalla = listalla + asiakaslista.get(asiakasNimi).toString();
        }

        return listalla;
    }

    public String lisaaAsiakas(Asiakastiedot uusiAsiakas) {
        /**
         * Lisätään uusi asiakas
         */
        if (uusiAsiakas != null) {
            uusiAsiakas.setAsiakasNumero(Integer.toString(suurinAsiakasnumero++));
            asiakaslista.put(uusiAsiakas.getAsiakasNimi(), uusiAsiakas);
            return uusiAsiakas.getAsiakasNimi();
        }
        return null;
    }

    public void asiakkaatTiedostoon() throws IOException {
        tiedosto.kirjoitaAsiakkaatTiedostoon(asiakaslista);
    }
}
