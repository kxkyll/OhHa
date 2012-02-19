/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import java.io.IOException;
import java.util.*;
import tiedostonkasittelija.AsiakastiedostonKasittelija;

/**
 * Asiakaskortisto-ohjelman sovelluslogiikka
 *
 * @author Kati
 */
public class Asiakkaat {

    /**
     * Asiakastoiminnot
     */
    private String tiedostonNimi = "asiakastiedosto.txt";
    private HashMap<String, Asiakas> asiakaslista = new HashMap<String, Asiakas>();
    private ArrayList<Asiakas> asiakaslistaNumeroittain = new ArrayList<Asiakas>();
    //private HashMap<Integer,Asiakas> asikasNumerot = new HashMap<Integer,Asiakas>();
    AsiakastiedostonKasittelija tiedosto;
    private static int seuraavaAsiakasnumero;

    public Asiakkaat() throws IOException {
        tiedosto = new AsiakastiedostonKasittelija(this);
        asiakaslista = tiedosto.lueAsiakkaatTiedostosta();
        asiakkaatJarjestysListalle();
        seuraavaAsiakasnumero = haeSeuraavaAsiakasnumero();
    }

    public String getTiedostonNimi() {
        return tiedostonNimi;
    }

    public String listaaAsiakkaat() {
        /**
         * Muotoiltu asiakaslistaus
         */
        SortedSet<String> aakkostettu = new TreeSet<String>(asiakaslista.keySet());
        Iterator<String> it = aakkostettu.iterator();

        


        String listalla =
                "" + String.format("%-25s", "Asiakasnumero ")
                + String.format("%-25s", "Asiakkaan nimi ")
                + String.format("%-25s", "Katuosoite ")
                + String.format("%-25s", "Postiosoite ")
                + String.format("%-25s", "Puhelinnumero")
                + String.format("%-25s", "Yhteyshenkilo \n");

        // Tässä jokin ongelma, eka asiakasrivi siftaantuu noin 10 merkillä oikealle
        // muut tulostuvat oikein
        
        while (it.hasNext()) {
            
            listalla = listalla + asiakaslista.get(it.next()).toString();
        }
        
//        for (String asiakasNimi : asiakaslista.keySet()) {
//            listalla = listalla + asiakaslista.get(asiakasNimi).toString();
//        }

        return listalla;
    }

    public String lisaaAsiakas(Asiakas uusiAsiakas) {
        /**
         * Lisätään uusi asiakas
         */
        if (uusiAsiakas != null) {

            uusiAsiakas.setAsiakasNumero(Integer.toString(seuraavaAsiakasnumero));
            seuraavaAsiakasnumero++;
            asiakaslista.put(uusiAsiakas.getAsiakasNimi(), uusiAsiakas);
            asiakaslistaNumeroittain.add(uusiAsiakas);
            Collections.sort(asiakaslistaNumeroittain);
            return uusiAsiakas.getAsiakasNimi();
        }
        return null;
    }

    public String haeAsiakasNimella(String hakuehto) {
        String haettu = "Antamallasi nimellä ei löytynyt asiakasta";
        Asiakas a = asiakaslista.get(hakuehto);
        if (a != null) {
            haettu = a.toString();
        }

        return haettu;
    }

    private void asiakkaatJarjestysListalle() {
        for (String asiakasNimi : asiakaslista.keySet()) {
            asiakaslistaNumeroittain.add(asiakaslista.get(asiakasNimi));

        }
        Collections.sort(asiakaslistaNumeroittain);
    }

    public String haeAsiakasAsiakasnumerolla(String hakuehto) {
        String haku = "Antamallasi numerolla ei löytynyt asiakasta";
        for (Asiakas a : asiakaslistaNumeroittain) {
            if (a.getAsiakasNumero().equals(hakuehto)) {
                return a.toString();
            }
        }

        return haku;
    }

    public void asiakkaatTiedostoon() throws IOException {
        tiedosto.kirjoitaAsiakkaatTiedostoon(asiakaslista);
    }

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
