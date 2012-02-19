/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class AsiakastiedostonKasittelijaTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    private File tiedosto;
    
    
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
        tiedosto  = folder.newFile("testi.txt");
        BufferedWriter out = new BufferedWriter(new FileWriter(tiedosto));
        
        out.write("10999;Testi Oy;Testitie 3;12345 Uusimaa;111-2223334;Teppo Testi;19.02.2012;NORMAALI");
        out.write("11000;Vesti Oy;Vestitie 4;22345 Tusimaa;222-2223334;Veppo Vesti;19.02.2012;NORMAALI");
       out.close();
        System.out.println(tiedosto);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void lueAsiakkaat() throws IOException {
        Asiakkaat as = new Asiakkaat ("testi.txt");
        assertEquals(2, as.getKaikkienAsiakkaidenMaara());
                
    }
}
