/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tiedostonkasittelija;

import asiakaskortisto.Asiakastoiminnot;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Kati
 */
public class TiedostonKasittelija {

    Object tiedostonKayttaja;
    String tiedostonNimi;

    public TiedostonKasittelija(Asiakastoiminnot tiedostonKayttaja) throws IOException {
        this.tiedostonKayttaja = tiedostonKayttaja;
        tiedostonNimi = tiedostonKayttaja.getTiedostonNimi();
    }


    public ArrayList<String> lataaAsiakkaatTiedostosta() throws IOException {
        File tiedosto = new File(tiedostonNimi);
        ArrayList<String> tiedostostaLuetut = new ArrayList<String>();
        try {
            Scanner tiedostolukija = new Scanner(tiedosto);
            while (tiedostolukija.hasNextLine()) {
                tiedostostaLuetut.add(tiedostolukija.nextLine());
            }
            tiedostolukija.close();
        } catch (Exception tiedostoPuuttuu) {
            System.out.println("Luodaan tyhjä tiedosto");
            luoTiedosto();
        }
        return tiedostostaLuetut;
    }

    public void kirjoitaTiedostoon(ArrayList<String> lista) throws IOException {
        File tiedosto = new File(tiedostonNimi);
        FileWriter kirjoittaja = new FileWriter(tiedosto);

        for (String rivi : lista) {
            kirjoittaja.write(rivi + "\n");
        }
        kirjoittaja.close();
    }

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