/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import asiakaskortisto.Asiakas;
import asiakaskortisto.Asiakas.Tila;
import asiakaskortisto.Asiakkaat;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.*;

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
        Asiakkaat as = new Asiakkaat(tiedostoNimi);
        assertEquals(2, as.getKaikkienAsiakkaidenMaara());

    }

    @Test
    public void kirjoitaAsiakkaat() throws IOException {
        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        as.lisaaAsiakas(new Asiakas("", "TestiAs1", "TestiAs1Os 1",
                "11122 Testikyla", "111-2222222", "Terttu Testaaja", "19-02-2012", Tila.NORMAALI));
        as.asiakkaatTiedostoon();

        assertEquals(1, as.getKaikkienAsiakkaidenMaara());

    }

    @Test
    public void haeAsiakasAsiakasNumerollaOK() throws IOException {

        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        String asiakasnumero = "10029";
        String haettu = as.haeAsiakasAsiakasnumerolla(asiakasnumero);
        String[] hakutaulu = haettu.split(" ");
        assertEquals(asiakasnumero, hakutaulu[0]);

    }

    @Test
    public void haeAsiakasOlioAsiakasNumerollaOK() throws IOException {

        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        String asiakasnumero = "10029";
        String haettu = "";
        Asiakas a = as.haeAsiakasOlioAsiakasnumerolla(asiakasnumero);
        if (a != null) {
            haettu = a.getAsiakasNumero();
        }

        assertEquals(asiakasnumero, haettu);

    }

    @Test
    public void haeAsiakasAsiakasNumerollaNOK() throws IOException {

        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        String asiakasnumero = "66666";
        String haettu = as.haeAsiakasAsiakasnumerolla(asiakasnumero);
        String[] hakutaulu = haettu.split(" ");
        assertNotSame(asiakasnumero, hakutaulu[0]);

    }

    @Test
    public void haeAsiakasAsiakasNumerollaNOK_hakuehtoTyhja() throws IOException {

        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        String asiakasnumero = "";
        String haettu = as.haeAsiakasAsiakasnumerolla(asiakasnumero);
        //Testi hajoaa, jos virheimoitusta muutetaan koodissa
        assertTrue(haettu.contains("Antamallasi"));

    }

    @Test
    public void haeAsiakasAsiakasNumerollaNOK_hakuehtoTekstia() throws IOException {

        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        String asiakasnumero = "abc";
        String haettu = as.haeAsiakasAsiakasnumerolla(asiakasnumero);
        String[] hakutaulu = haettu.split(" ");
        assertFalse(haettu.contains(asiakasnumero));
        
    }

    @Test
    public void haeAsiakasOlioAsiakasNumerollaNOK() throws IOException {
        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        String asiakasnumero = "66666";
        String haettu = "";
        Asiakas a = as.haeAsiakasOlioAsiakasnumerolla(asiakasnumero);
        if (a != null) {
            haettu = a.getAsiakasNumero();
        }
        //asiakas on null, koska tälläistä asiakasta ei ole, miten testataan
        assertNotSame(asiakasnumero, haettu);
    }
        @Test
    public void haeAsiakasNimellaOK() throws IOException {

        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        String nimi = "TestiAs1";
        String haettu = as.haeAsiakasNimella(nimi);
        assertTrue (haettu.contains(nimi));
    }

    @Test
    public void haeAsiakasOlioNimellaOK() throws IOException {

        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        String nimi = "TestiAs1";
        String haettu = "";
        Asiakas a = as.haeAsiakasOlioNimella(nimi);
        if (a != null) {
            haettu = a.getAsiakasNimi();
        }

        assertEquals(nimi, haettu);

    }

}