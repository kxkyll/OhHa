/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostonkasittelija;

import asiakaskortisto.Asiakastiedot;
import asiakaskortisto.Asiakastiedot.Tila;
import asiakaskortisto.Asiakastoiminnot;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Kati
 * Tiedostonkäsittelijä hoitaa asiakastietojen lukemisen 
 *  tiedostosta listalle asiakaskortiston käynnistyksen yhteydessä, 
 *  sekä tallentaa asiakastiedot tiedostoon asiakaskoristoa suljettaessa
 */

public class AsiakastiedostonKasittelija {

    /** tiedostonKayttaja on tiedostonkäsittelijää kutsuva olio */
    private Object tiedostonKayttaja;
    /** tiedostonNimi on käsiteltävän tiedoston nimi */
    private String tiedostonNimi;
    private ArrayList<String> tiedostonRivit;
    private HashMap<String, Asiakastiedot> asiakaslista;

    public AsiakastiedostonKasittelija(Asiakastoiminnot tiedostonKayttaja) throws IOException {
        this.tiedostonKayttaja = tiedostonKayttaja;
        tiedostonNimi = tiedostonKayttaja.getTiedostonNimi();
        
    }
        

    public HashMap<String,Asiakastiedot> lueAsiakkaatTiedostosta() throws IOException {
        /** Ohjelman alussa luetaan asiakastiedot tiedostosta rivi kerrallaan 
          * ja talletetaan ne ArrayList listalle nimeltä tiedostostaLuetut */
        File tiedosto = new File(tiedostonNimi);
        tiedostonRivit = new ArrayList<String>();
        try {
            Scanner tiedostolukija = new Scanner(tiedosto);
            while (tiedostolukija.hasNextLine()) {
                tiedostonRivit.add(tiedostolukija.nextLine());
            }
            tiedostolukija.close();
        } catch (Exception tiedostoPuuttuu) {
            System.out.println("Luodaan tyhjä tiedosto");
            luoTiedosto();
        }
        return laitaAsiakkaatListalle();
       
    }

    private HashMap<String,Asiakastiedot> laitaAsiakkaatListalle() {
        asiakaslista = new HashMap<String, Asiakastiedot>();
        for (String luettu : tiedostonRivit) {
            Asiakastiedot uusiAsiakas = luoAsiakas(luettu);
            //System.out.println("uusi asiakas: " + uusiAsiakas);
            if (uusiAsiakas != null) {
                asiakaslista.put(uusiAsiakas.getAsiakasNimi(), uusiAsiakas);
            }
            //System.out.println("asiakaslista: " + asiakaslista);
        }
        return asiakaslista;
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
            String tila = taulukko[7];
            return new Asiakastiedot(asiakasNumero, asiakasNimi, katuosoite, 
                    postiosoite, puhelinnumero, yhteyshenkilo, 
                    asiakkaaksitulopvm, Tila.valueOf(tila));
        }
        return null;
    }
 
    public void kirjoitaAsiakkaatTiedostoon(HashMap<String,Asiakastiedot> lista) throws IOException {
        tiedostonRivit.clear();
        for (String asiakasNimi : lista.keySet()) {
            Asiakastiedot a = lista.get(asiakasNimi);
            tiedostonRivit.add(a.getAsiakasNumero() + ";" + a.getAsiakasNimi()
                    + ";" + a.getKatuOsoite() + ";" + a.getPostiOsoite()
                    + ";" + a.getPuhelinnumero() + ";" + a.getYhteyshenkilo()
                    + ";" + a.getAsiakkaaksitulopvm() + ";" + a.getTila().toString());

        }
        kirjoitaTiedostoon();


    }
    
    public void kirjoitaTiedostoon() throws IOException {
        /** Ohjelman lopuksi kirjoitetaan asiakastiedot tiedostoon*/
        File tiedosto = new File(tiedostonNimi);
        FileWriter kirjoittaja = new FileWriter(tiedosto);

        for (String rivi : tiedostonRivit) {
            kirjoittaja.write(rivi + "\n");
        }
        kirjoittaja.close();
    }

    public void luoTiedosto() throws IOException {
        /** Jos tiedostoa ei ole  olemassa, luodaan tyhjä tiedosto */
        FileWriter kirjoittaja = null;
        try {
            File tiedosto = new File(tiedostonNimi);
            kirjoittaja = new FileWriter(tiedosto);

        } catch (Exception luominenEpaonnistui) {
            System.out.println("Tyhjän tiedoston luominen epäonnistui");
        } finally {
            if (kirjoittaja != null) {
                kirjoittaja.close();
            }
        }

    }

   
}