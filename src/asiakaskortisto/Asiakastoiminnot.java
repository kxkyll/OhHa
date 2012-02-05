/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import tiedostonkasittelija.TiedostonKasittelija;

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
    private ArrayList<String> tiedostostaLuetut = new ArrayList<String>();
    private HashMap<String, Asiakastiedot> asiakaslista = new HashMap<String, Asiakastiedot>();
    TiedostonKasittelija tiedosto;
    private int suurinAsiakasnumero;

    public Asiakastoiminnot() throws IOException {
        tiedosto = new TiedostonKasittelija(this);
        tiedostostaLuetut = tiedosto.lataaAsiakkaatTiedostosta();
        System.out.println("tiedostostaluetut" + tiedostostaLuetut);
        laitaListalle();
        //todo: hae listalta suurin asiakasnumero ja sijoita se suurinAsiakasnumerokenttään
        suurinAsiakasnumero = 10000;
    }

    private void laitaListalle() {
        for (String luettu : tiedostostaLuetut) {
            Asiakastiedot uusiAsiakas = luoAsiakas(luettu);
            //System.out.println("uusi asiakas: " + uusiAsiakas);
            if (uusiAsiakas != null) {
                asiakaslista.put(uusiAsiakas.getAsiakasNimi(), uusiAsiakas);
            }
            //System.out.println("asiakaslista: " + asiakaslista);
        }
    }

    private Asiakastiedot luoAsiakas(String luettu) {
        /**
         * Luo tiedostoista luetuista asiakastiedoston jokaisesta rivistä
         * asiakastiedot olion
         */
        String taulukko[] = luettu.split(";");
        if (taulukko.length == 8) {
            String asiakasNumero = taulukko[0];
            String asiakasNimi = taulukko[1];
            String katuosoite = taulukko[2];
            String postiosoite = taulukko[3];
            String puhelinnumero = taulukko[4];
            String yhteyshenkilo = taulukko[5];
            String asiakkaaksitulopvm = taulukko[6];
            char tila = taulukko[7].charAt(0);
            return new Asiakastiedot(asiakasNumero, asiakasNimi, katuosoite, postiosoite, puhelinnumero, yhteyshenkilo, asiakkaaksitulopvm, tila);
        }
        return null;
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

    public void listaltaTiedostoon() throws IOException {
        tiedostostaLuetut.clear();
        for (String asiakasNimi : asiakaslista.keySet()) {
            Asiakastiedot a = asiakaslista.get(asiakasNimi);
            tiedostostaLuetut.add(a.getAsiakasNumero() + ";" + a.getAsiakasNimi()
                    + ";" + a.getKatuOsoite() + ";" + a.getPostiOsoite()
                    + ";" + a.getPuhelinnumero() + ";" + a.getYhteyshenkilo()
                    + ";" + a.getAsiakkaaksitulopvm() + ";" + a.getTila());

        }
        tiedosto.kirjoitaTiedostoon(tiedostostaLuetut);


    }
}
