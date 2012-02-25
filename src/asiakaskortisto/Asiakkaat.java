/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import asiakaskortisto.Asiakas.Tila;
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
        int maara = 0;
        for (String asiakasNimi : asiakaslista.keySet()) {
            maara++;
        }
        //System.out.println("Määrä: " + maara);
        return maara;
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
                +String.format("%-25s", "Yhteyshenkilo") +"\n";


        while (it.hasNext()) {
            Asiakas a = asiakaslista.get(it.next());
            if (a.getTila().equals(Tila.NORMAALI)) {
                listalla = listalla + a.toString();
            }
        }

        
        return listalla;
    }

    public String lisaaAsiakas(Asiakas uusiAsiakas) {
        /**
         * Lisätään uusi asiakas
         */
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

    public Asiakas haeMuutettavaAsiakasnumerolla(String hakuehto) {

        for (Asiakas a : asiakaslistaNumeroittain) {
            if (a.getAsiakasNumero().equals(hakuehto)) {
                return a;
            }
        }

        return null;
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

    public Asiakas haeAsiakasOlioAsiakasnumerolla(String hakuehto) {

        for (Asiakas a : asiakaslistaNumeroittain) {
            if (a.getAsiakasNumero().equals(hakuehto)) {
                return a;
            }
        }
        return null;
    }

    public String haeAsiakasNimella(String hakuehto) {
        String haettu = "Antamallasi nimellä ei löytynyt asiakasta";
        if (asiakaslista.containsKey(hakuehto)) {
            haettu = asiakaslista.get(hakuehto).toString();
        }
        return haettu;

    }

    public Asiakas haeAsiakasOlioNimella(String hakuehto) {
        Asiakas a = null;
        a = asiakaslista.get(hakuehto);
        return a;
    }

    private void asiakkaatJarjestysListalle() {
        for (String asiakasNimi : asiakaslista.keySet()) {
            asiakaslistaNumeroittain.add(asiakaslista.get(asiakasNimi));

        }
        Collections.sort(asiakaslistaNumeroittain);
    }

//    public String haeAsiakasAsiakasnumerolla(String hakuehto) {
//        String haku = "Antamallasi numerolla ei löytynyt asiakasta";
//        for (Asiakas a : asiakaslistaNumeroittain) {
//            if (a.getAsiakasNumero().equals(hakuehto)) {
//                return a.toString();
//            }
//        }
//
//        return haku;
//    }
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
