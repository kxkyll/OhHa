/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import asiakaskortisto.Kayttaja;
import asiakaskortisto.Kayttaja.Rooli;
import asiakaskortisto.Kirjaudu;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import org.junit.*;

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
    public void testaaOkTyontekija() throws IOException {
        Kirjaudu kirjaudu = new Kirjaudu();
        Kayttaja k = new Kayttaja("kati", "sala");

        assertEquals(Rooli.TYONTEKIJA, kirjaudu.tarkistaKirjautuminen(k));


    }

    @Test
    public void testaaOkEsimies() throws IOException {
        Kirjaudu kirjaudu = new Kirjaudu();
        Kayttaja k = new Kayttaja("piia", "pomo");

        assertEquals(Rooli.ESIMIES, kirjaudu.tarkistaKirjautuminen(k));
    }

    @Test
    public void testaaOkYllapitO() throws IOException {
        Kirjaudu kirjaudu = new Kirjaudu();
        Kayttaja k = new Kayttaja("gnut", "g7F9");

        assertEquals(Rooli.YLLAPITO, kirjaudu.tarkistaKirjautuminen(k));


    }

    @Test
    public void testaaEiOkRooli() throws IOException {
        Kirjaudu kirjaudu = new Kirjaudu();
        Kayttaja k = new Kayttaja("sakke", "yYi4", Rooli.TYONTEKIJA);

        assertNotSame(Rooli.ESIMIES, kirjaudu.tarkistaKirjautuminen(k));
    }

    @Test
    public void testaaEiOkSalasana() throws IOException {
        Kirjaudu kirjaudu = new Kirjaudu();
        Kayttaja k = new Kayttaja("kati", "s");

        assertEquals(null, kirjaudu.tarkistaKirjautuminen(k));
    }

    @Test
    public void testaaEiOkKayttajaTunnus() throws IOException {
        Kirjaudu kirjaudu = new Kirjaudu();
        Kayttaja k = new Kayttaja("ville", "sala");
   
        assertEquals(null, kirjaudu.tarkistaKirjautuminen(k));

    }
}
