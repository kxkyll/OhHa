/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.*;
import tiedostonkasittelija.AsiakastiedostonKasittelija;

/**
 *
 * @author Kati
 */
public class AsiakastiedostonKasittelijaTest {
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
        String tiedostonNimi = "testi.txt";
        File tiedosto = new File(tiedostonNimi);
        
                
        FileWriter kirjoittaja = new FileWriter(tiedosto);

        kirjoittaja.write("10999;Testi Oy;Testitie 3;12345 Uusimaa;111-2223334;Teppo Testi;19.02.2012;NORMAALI\n");
        kirjoittaja.write("11000;Vesti Oy;Vestitie 4;22345 Tusimaa;222-2223334;Veppo Vesti;19.02.2012;NORMAALI\n");
                    
        kirjoittaja.close();
        
    }
    
    @After
    public void tearDown() throws IOException {
        String tiedostonNimi = "testi.txt";
        File tiedosto = new File(tiedostonNimi);
        
                
        FileWriter kirjoittaja = new FileWriter(tiedosto);

        kirjoittaja.write("testitiedosto\n");
                            
        kirjoittaja.close();
        akasittelija = new AsiakastiedostonKasittelija(tiedostonNimi);
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testaaOnnistunutLuku() {}
    
    
    
}
