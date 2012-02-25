/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import asiakaskortisto.Asiakas;
import asiakaskortisto.Asiakas.Tila;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import static org.junit.Assert.*;
import org.junit.*;
import tiedostonkasittelija.AsiakastiedostonKasittelija;

/**
 *
 * @author Kati
 */
public class AsiakastiedostonKasittelijaTest {

    File tiedosto;
    String tiedostonNimi;
//    String tyhjaTiedosto;
    FileWriter kirjoittaja;
    //  FileWriter tyhjankirjoittaja;
    HashMap<String, Asiakas> asiakkaat;
    AsiakastiedostonKasittelija akasittelija;

    public AsiakastiedostonKasittelijaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws IOException {

        tiedostonNimi = "testi.txt";
        tiedosto = new File(tiedostonNimi);
        kirjoittaja = new FileWriter(tiedosto);

        kirjoittaja.write("10999;Testi Oy;Testitie 3;12345 Uusimaa;111-2223334;Teppo Testi;19.02.2012;NORMAALI\n");
        kirjoittaja.write("11000;Vesti Oy;Vestitie 4;22345 Tusimaa;222-2223334;Veppo Vesti;19.02.2012;NORMAALI\n");

        kirjoittaja.close();

//        File tyhja = new File(tyhjaTiedosto);
//        tyhjaTiedosto = "tyhja.txt";
//        tyhjankirjoittaja = new FileWriter(tyhja);

    }

    @After
    public void tearDown() throws IOException {
        //kirjoittaja.close();
        //tiedosto.deleteOnExit();
        //    tyhjankirjoittaja.close();
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void testaaOnnistunutLuku() throws IOException {

        //kirjoittaja.write(11111;Ekalisatty;Ekakatu 6;98789 Ekamaki;989-656787;Anne Yrjo;01.02.2012;NORMAALI);
        //kirjoittaja.write(11112;Tokalisatty;Tokakatu 2;12789 Takamaki;121-115116;Janne Jaala;11.02.2012;NORMAALI);

        akasittelija = new AsiakastiedostonKasittelija(tiedostonNimi);
        asiakkaat = akasittelija.lueAsiakkaatTiedostosta();
        assertEquals(2, asiakkaat.size());
    }

    @Test
    public void testaaLukuTyhjastaTiedostosta() throws IOException {
        //kirjoittaja = new FileWriter(tiedostonNimi);
        kirjoittaja = new FileWriter(tiedosto);
        kirjoittaja.write("");
        kirjoittaja.close();
//        Scanner tiedostolukija = new Scanner(tiedostonNimi);
  Scanner tiedostolukija = new Scanner(tiedosto);
        assertFalse("tiedosto ei tyhjä", tiedostolukija.hasNext());

  akasittelija = new AsiakastiedostonKasittelija("tyhja.txt");
  //      akasittelija = new AsiakastiedostonKasittelija(tiedostonNimi);
        asiakkaat = akasittelija.lueAsiakkaatTiedostosta();
        assertEquals("asiakkaiden määrä ei nolla", 0, asiakkaat.size());
    }

    @Test
    public void testaaOnnistunutKirjoitus() throws IOException {
        //varmistetaan että tiedostoon tyhjä
        ArrayList<String> tiedostostaLuetut = new ArrayList<String>();
        kirjoittaja = new FileWriter(tiedosto);
        kirjoittaja.write("");
        kirjoittaja.close();

        Asiakas a = new Asiakas("", "Tee Oy", "Tie 3", "12345 Uusimaa", "989-12223334", "Tee Testi", "23.02.2012", Tila.NORMAALI);
        Asiakas b = new Asiakas("", "Aita", "Mutkatie 8 a 7", "67845 Uusimaa", "4540-123334", "Saimi Sippu", "03.10.2011", Tila.NORMAALI);
        Asiakas c = new Asiakas("", "Autola", "Sorareitti 6", "34567 Tuusula", "040-676334", "Matti Mikko", "13.01.2012", Tila.NORMAALI);
        asiakkaat = new HashMap<String, Asiakas>();
        asiakkaat.put(a.getAsiakasNumero(), a);
        asiakkaat.put(b.getAsiakasNumero(), b);
        asiakkaat.put(c.getAsiakasNumero(), c);
        akasittelija = new AsiakastiedostonKasittelija("testi.txt");
        akasittelija.kirjoitaAsiakkaatTiedostoon(asiakkaat);
        Scanner tiedostolukija = new Scanner(tiedosto);
        while (tiedostolukija.hasNextLine()) {
            tiedostostaLuetut.add(tiedostolukija.nextLine());
        }
        
        tiedostolukija.close();

        assertEquals("Tiedostoon kirjoitettujen rivien määrä ei täsmää",tiedostostaLuetut.size(), asiakkaat.size());
    }
}
