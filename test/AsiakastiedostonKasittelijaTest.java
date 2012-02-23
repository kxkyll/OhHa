/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import asiakaskortisto.Asiakas;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import org.junit.*;
import tiedostonkasittelija.AsiakastiedostonKasittelija;

/**
 *
 * @author Kati
 */
public class AsiakastiedostonKasittelijaTest {

    String tiedostonNimi;
    String tyhjaTiedosto;
    FileWriter kirjoittaja;
    FileWriter tyhjankirjoittaja;

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
        tyhjaTiedosto = "tyhja.txt";
        File tiedosto = new File(tiedostonNimi);
        File tyhja = new File(tyhjaTiedosto);
        tyhjankirjoittaja = new FileWriter(tyhja);

        kirjoittaja = new FileWriter(tiedosto);

        kirjoittaja.write("10999;Testi Oy;Testitie 3;12345 Uusimaa;111-2223334;Teppo Testi;19.02.2012;NORMAALI\n");
        kirjoittaja.write("11000;Vesti Oy;Vestitie 4;22345 Tusimaa;222-2223334;Veppo Vesti;19.02.2012;NORMAALI\n");

        //kirjoittaja.close();

    }

    @After
    public void tearDown() throws IOException {
        kirjoittaja.close();
        tyhjankirjoittaja.close();
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //

    @Test
    public void testaaOnnistunutLuku() throws IOException {
//        System.out.println("seuraavaksi kirjoitus");
//        kirjoittaja.write("10999;Testi Oy;Testitie 3;12345 Uusimaa;111-2223334;Teppo Testi;19.02.2012;NORMAALI\n");
//        kirjoittaja.write("11000;Vesti Oy;Vestitie 4;22345 Tusimaa;222-2223334;Veppo Vesti;19.02.2012;NORMAALI\n");

        AsiakastiedostonKasittelija akasittelija = new AsiakastiedostonKasittelija(tiedostonNimi);
        HashMap<String, Asiakas> asiakkaat = akasittelija.lueAsiakkaatTiedostosta();
        System.out.println("asiakkaat: ");
        for (String a: asiakkaat.keySet() ) {
            System.out.println(asiakkaat.get(a));
        }
        assertEquals(2, asiakkaat.size());
    }

    @Test
    public void testaaLukuTyhjastaTiedostosta() throws IOException {
        tyhjankirjoittaja.write("");
        AsiakastiedostonKasittelija akasittelija = new AsiakastiedostonKasittelija(tyhjaTiedosto);
        HashMap<String, Asiakas> luetutAsiakkaat = akasittelija.lueAsiakkaatTiedostosta();
        assertEquals(0, luetutAsiakkaat.size());
    }
}
