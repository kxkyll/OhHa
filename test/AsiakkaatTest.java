/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import asiakaskortisto.Asiakas;
import asiakaskortisto.Asiakas.Tila;
import asiakaskortisto.Asiakkaat;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.*;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author Kati
 */
public class AsiakkaatTest {
        
    String tiedostoNimi;
    FileWriter kirjoittaja;
    
    public AsiakkaatTest() {
        
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() throws IOException {
        tiedostoNimi = "asiakastesti.txt";
        kirjoittaja = new FileWriter(tiedostoNimi);
        
    }
    
    @After
    public void tearDown() throws IOException {
        kirjoittaja.close();
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void lueAsiakkaat() throws IOException {
        kirjoittaja.write("10099;Testi1Asiakas;Testikatu 1;00220 Testila;101-11223344;Terttu Testi;10.02.2012;NORMAALI");
        kirjoittaja.write("10100;Testi2Asiakas;Testikatu 2;00330 Testila;101-11223355;Teppo Testi;11.02.2012;NORMAALI");
        Asiakkaat as = new Asiakkaat (tiedostoNimi);
        assertEquals(2, as.getKaikkienAsiakkaidenMaara());
                
    }
    @Test
    public void kirjoitaAsiakkaat() throws IOException {
        Asiakkaat as = new Asiakkaat ("kirjoitustesti.txt");
        as.lisaaAsiakas(new Asiakas ("", "TestiAs1", "TestiAs1Os 1",
                "11122 Testikyla", "111-2222222", "Terttu Testaaja", "19-02-2012", Tila.NORMAALI));
        as.asiakkaatTiedostoon();

        assertEquals(1, as.getKaikkienAsiakkaidenMaara());
                
    }
}
