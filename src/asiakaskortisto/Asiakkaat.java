/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;
/**
 * Asiakaskortisto-ohjelman Asiakastoiminnot
 *
 * @author Kati
 */

import asiakaskortisto.Asiakas.Tila;
import java.io.IOException;
import java.util.*;
import tiedostonkasittelija.AsiakastiedostonKasittelija;


/**
     * luokka Asiakkaat sisältää kaikki Asiakasolioihin kohdistuvat metodit.
     * Luokka kokoaa kaikki luodut asiakasoliot listalle.
     */
public class Asiakkaat {

    
    private String tiedostonNimi = "asiakastiedosto.txt";
    private HashMap<String, Asiakas> asiakaslista = new HashMap<String, Asiakas>();
    private ArrayList<Asiakas> asiakaslistaNumeroittain = new ArrayList<Asiakas>();
    
    AsiakastiedostonKasittelija tiedosto;
    private static int seuraavaAsiakasnumero;

    public Asiakkaat() throws IOException {
        tiedosto = new AsiakastiedostonKasittelija(this);
        asiakaslista = tiedosto.lueAsiakkaatTiedostosta();
        asiakkaatJarjestysListalle();
        seuraavaAsiakasnumero = haeSeuraavaAsiakasnumero();
    }

    public Asiakkaat(String asiakastiedosto) throws IOException {
        this.tiedostonNimi = asiakastiedosto;
        tiedosto = new AsiakastiedostonKasittelija(this);
        asiakaslista = tiedosto.lueAsiakkaatTiedostosta();
        asiakkaatJarjestysListalle();
        seuraavaAsiakasnumero = haeSeuraavaAsiakasnumero();
    }

    public String getTiedostonNimi() {
        return tiedostonNimi;
    }

    public ArrayList<Asiakas> getAsiakaslistaNumeroittain() {
        return asiakaslistaNumeroittain;
    }

    public int getSeuraavaAsiakasnumero() {
        return seuraavaAsiakasnumero;
    }

    public int getKaikkienAsiakkaidenMaara() {
        /**
         * Metodi palauttaa kaikkien asiakkaitten määrän
         */
        int maara = 0;
        for (String asiakasNimi : asiakaslista.keySet()) {
            maara++;
        }
       
        return maara;
    }
        /**
         * Metodi listaaAsiakkaat palauttaa muotoillun asiakaslistauksen
         * asiakkaista, joiden tila on normaali.
         * Arkistoituja asiakkaita ei näytetä.
         */
    public String listaaAsiakkaat() {
        SortedSet<String> aakkostettu = new TreeSet<String>(asiakaslista.keySet());
        Iterator<String> it = aakkostettu.iterator();

        String listalla =
                "" + String.format("%-25s", "Asiakasnumero ")
                + String.format("%-25s", "Asiakkaan nimi ")
                + String.format("%-25s", "Katuosoite ")
                + String.format("%-25s", "Postiosoite ")
                + String.format("%-25s", "Puhelinnumero")
                + String.format("%-25s", "Yhteyshenkilo") + "\n";


        while (it.hasNext()) {
            Asiakas a = asiakaslista.get(it.next());
            if (a.getTila().equals(Tila.NORMAALI)) {
                listalla = listalla + a.toString();
            }
        }


        return listalla;
    }
    
        /**
         * Metodi lisaaAsiakas lisää käyttäjältä kysyttyjen tietojen mukaisen
         * uuden asiakasolion listalle.
         */
    public String lisaaAsiakas(Asiakas uusiAsiakas) {
        if (uusiAsiakas != null) {
            if (uusiAsiakas.getAsiakasNumero().isEmpty()) {
                uusiAsiakas.setAsiakasNumero(Integer.toString(seuraavaAsiakasnumero));
            }
            seuraavaAsiakasnumero++;
            asiakaslista.put(uusiAsiakas.getAsiakasNimi(), uusiAsiakas);
            asiakaslistaNumeroittain.add(uusiAsiakas);
            Collections.sort(asiakaslistaNumeroittain);
            return uusiAsiakas.toString();
            //return uusiAsiakas.getAsiakasNimi();
        }
        return null;
    }

        /**
         * Metodi poistaAsiakas muuttaa annetun asiakasnumeron perustella
         * asiakkaan tilatiedon arkistoiduksi. Tällöin asiakasta ei enää näytetä
         * listauksessa. Jos poistettavaa asiakasta ei löydy, palautetaan
         * merkkijono "Poistettavaa asiakasta ei löytynyt";
         */
    public String poistaAsiakas(String poistettava) {
    
        String viesti = "Poistettavaa asiakasta ei löytynyt";
        for (Asiakas a : asiakaslistaNumeroittain) {
            if (a.getAsiakasNumero().equals(poistettava)) {
                a.setTilaArkistoiduksi();
                return a.toString();
            }
        }
        return viesti;
    }

    /**
         * Metodi muutaAsiakas muuttaa käyttäjän antamien tietojen perusteella,
         * asiakkaan katuosoitetta, postiosoitetta, puhelinnumeroa tai
         * yhteyshenkilöä Muita tietoja ei muuteta, vaan ne pysyvät samoina
         */
    public String muutaAsiakas(Asiakas vanha, Asiakas muutettu) {
        
        Asiakas a = asiakaslista.get(vanha.getAsiakasNimi());
        if (a != null) {
            asiakaslista.remove(a.getAsiakasNimi());
            asiakaslistaNumeroittain.remove(a);
        }

        if (muutettu.getKatuOsoite().length() > 0) {
            a.setKatuOsoite(muutettu.getKatuOsoite());
        }
        if (muutettu.getPostiOsoite().length() > 0) {
            a.setPostiOsoite(muutettu.getPostiOsoite());
        }
        if (muutettu.getPuhelinnumero().length() > 0) {
            a.setPuhelinnumero(muutettu.getPuhelinnumero());
        }
        if (muutettu.getYhteyshenkilo().length() > 0) {
            a.setYhteyshenkilo(muutettu.getYhteyshenkilo());
        }
        asiakaslista.put(a.getAsiakasNimi(), a);
        asiakaslistaNumeroittain.add(a);
        Collections.sort(asiakaslistaNumeroittain);
        return a.toString();
    }

   
    public String haeAsiakasAsiakasnumerolla(String hakuehto) {
        String haettu = "Antamallasi asiakasnumerolla ei löytynyt asiakasta";
        for (Asiakas a : asiakaslistaNumeroittain) {
            if (a.getAsiakasNumero().equals(hakuehto)) {
                return a.toString();
            }
        }

        return haettu;
    }

            /**
         * Metodi haeAsiakasOlioAsiakasnumerolla hakee asiakasnumeron
         * perusteella asiakasolion ja palauttaa sen kutsuvalle metodille. Jos
         * asiakasta ei löydy palautetaan null-arvo
         */
    public Asiakas haeAsiakasOlioAsiakasnumerolla(String hakuehto) {
        for (Asiakas a : asiakaslistaNumeroittain) {
            if (a.getAsiakasNumero().equals(hakuehto)) {
                return a;
            }
        }
        return null;
    }

            /**
         * Metodi haeAsiakasNimellä hakee asiakasnimen perusteella asiakkaan
         * tiedot ja palauttaa merkkijonon joka sisältää asiakkaan tiedot Jos
         * asiakasta ei löydy, palautetaan merkkijono "Antamallasi nimellä ei
         * löytynyt asiakasta"
         */
    public String haeAsiakasNimella(String hakuehto) {
        String haettu = "Antamallasi nimellä ei löytynyt asiakasta";
        if (asiakaslista.containsKey(hakuehto)) {
            haettu = asiakaslista.get(hakuehto).toString();
        }
        return haettu;

    }

            /**
         * Metodi haeAsiakasOlioNimellä hakee asiakasnimen perusteella
         * asiakasolion ja paluttaa asiakasolion Jos asiakasta ei löydy
         * palautetaan tyhjä asiakasolio (null)
         */
    public Asiakas haeAsiakasOlioNimella(String hakuehto) {
        Asiakas a = null;
        a = asiakaslista.get(hakuehto);
        return a;
    }

            /**
         * Metodi asiakkaatJarjestyslistalle laittaa tiedostostaluetut asiakkaat
         * listalle asiakaslistaNumeroittain sekä järjestää ne
         * asiakasnumeroittain nousevaan järjestykseen
         */
    private void asiakkaatJarjestysListalle() {
        for (String asiakasNimi : asiakaslista.keySet()) {
            asiakaslistaNumeroittain.add(asiakaslista.get(asiakasNimi));

        }
        Collections.sort(asiakaslistaNumeroittain);
    }

            /**
         * Metodi asiakkaatTiedostoon pyytää tiedostonkäsittelijää kirjoittamaan
         * asiakaslistan tiedostoon. Metodia kutsutaan kun ohjelman suoritus
         * lopetetaan.
         */
    public void asiakkaatTiedostoon() throws IOException {
        tiedosto.kirjoitaAsiakkaatTiedostoon(asiakaslista);
    }

            /**
         * Asiakasnumerot generoidaan automaattisesti perustuen olemassaolevien
         * asiakkaitten asiakasnumeroihin. Pienin mahdollinen asiakasnumero on
         * 10000 Metodi haeSeuraavaAsiakasnumero etsii suurimman käytössäolevan
         * asiakasnumeron ja palauttaa yhtä suuremman arvon.
         *
         */
    private int haeSeuraavaAsiakasnumero() {
        int suurin = Integer.MIN_VALUE;
        if (asiakaslistaNumeroittain.isEmpty()) {
            return 10000;
        }
        for (Asiakas a : asiakaslistaNumeroittain) {
            if (Integer.valueOf(a.getAsiakasNumero()) > suurin) {
                suurin = Integer.valueOf(a.getAsiakasNumero());
            }
        }
        return suurin + 1;
    }
}
