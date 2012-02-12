/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import asiakaskortisto.Kayttajatiedot;
import asiakaskortisto.Kayttajatiedot.Rooli;
import asiakaskortisto.Kirjaudu;
import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Kati
 */
public class KirjauduTest {
    
    public KirjauduTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        
//        Kirjaudu kirjaudu = new Kirjaudu();
//        Kayttajatiedot kati = new Kayttajatiedot ("kati","sala");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void testaaOKKirjautuminen() throws IOException {
        Kirjaudu kirjaudu = new Kirjaudu();
        Kayttajatiedot k = new Kayttajatiedot ("kati","sala");
              
        assertEquals(Rooli.TYONTEKIJA, kirjaudu.tarkistaKirjautuminen(k));
    
    
    }
    
//    @Test
//    public void testaaNOKKirjautuminen() throws IOException {
//        Kirjaudu kirjaudu = new Kirjaudu();
//        Kayttajatiedot v = new Kayttajatiedot ("ville","vilu");
//              
//        assertEquals(null, kirjaudu.tarkistaKirjautuminen(v));
//    
//    
//    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
