/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostonkasittelija;

import asiakaskortisto.Kayttaja;
import asiakaskortisto.Kayttaja.Rooli;
import asiakaskortisto.Kirjaudu;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Kati Kayttajatiedostonkäsittelijä hoitaa käyttäjätietojen lukemisen
 * tiedostosta listalle asiakaskortistoon kirjautumisen yhteydessä, sekä
 * tallentaa käyttäjätiedot tiedostoon asiakaskortistoa suljettaessa
 */
public class KayttajatiedostonKasittelija {

    /**
     * tiedostonKayttaja on tiedostonkäsittelijää kutsuva olio
     */
    Object tiedostonKayttaja;
    /**
     * tiedostonNimi on käsiteltävän tiedoston nimi
     */
    private String tiedostonNimi;
    private ArrayList<String> tiedostonRivit;
    private HashMap<String, Kayttaja> kayttajalista;

    public KayttajatiedostonKasittelija(Kirjaudu kirjautumisKayttaja) {
        this.tiedostonKayttaja = kirjautumisKayttaja;
        tiedostonNimi = kirjautumisKayttaja.getTiedostonNimi();
    }

    /**
     * Ohjelman alussa luetaan käyttäjätiedot tiedostosta rivi kerrallaan ja
     * talletetaan ne listalle
     */
    public HashMap<String, Kayttaja> lueTiedostosta() throws IOException {
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
        return laitaKayttajatListalle();

    }

    /**
     * Luo tiedostoista luetuista käyttäjäriveistä listan
     */
    private HashMap<String, Kayttaja> laitaKayttajatListalle() {
        kayttajalista = new HashMap<String, Kayttaja>();
        for (String luettu : tiedostonRivit) {
            Kayttaja uusiKayttaja = luoKayttaja(luettu);

            if (uusiKayttaja != null) {
                kayttajalista.put(uusiKayttaja.getKayttajaTunnus(), uusiKayttaja);
            }

        }
        return kayttajalista;
    }

    /**
     * Luo tiedostoista luetuista käyttäjäriveistä jokaisesta rivistä
     * käyttäjäolion
     */
    private Kayttaja luoKayttaja(String luettu) {

        String taulukko[] = luettu.split(";");
        if (taulukko.length == 3) {
            String kayttajaTunnus = taulukko[0];
            String salasana = taulukko[1];
            String teksti = taulukko[2];

            return new Kayttaja(kayttajaTunnus, salasana, Rooli.valueOf(teksti));
        }
        return null;
    }

    /**
     * kirjoitetaan käyttäjän tiedot listalta riveittäin ja kutsutaan
     * tiedostoonkirjoitusta
     */
    public void kirjoitaKayttajatTiedostoon(HashMap<String, Kayttaja> lista) throws IOException {
        tiedostonRivit.clear();
        for (String kayttaja : lista.keySet()) {
            Kayttaja k = lista.get(kayttaja);
            tiedostonRivit.add(k.getKayttajaTunnus() + ";" + k.getSalasana()
                    + ";" + k.getRooli().toString());
        }
        kirjoitaTiedostoon();

    }

    /**
     * Kirjoitetaan käyttäjätunnukset tiedostoon
     */
    public void kirjoitaTiedostoon() throws IOException {
        File tiedosto = new File(tiedostonNimi);
        FileWriter kirjoittaja = new FileWriter(tiedosto);

        for (String rivi : tiedostonRivit) {
            kirjoittaja.write(rivi + "\n");
        }
        kirjoittaja.close();
    }

    /**
     * Jos tiedostoa ei ole olemassa, luodaan tyhjä tiedosto
     */
    public void luoTiedosto() throws IOException {
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