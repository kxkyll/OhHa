/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Kati
 */
public class AsiakastiedostonKasittelijaTest {
    
    public AsiakastiedostonKasittelijaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        String tiedostonNimi = "testi1.txt";
        File tiedosto = new File(tiedostonNimi);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
