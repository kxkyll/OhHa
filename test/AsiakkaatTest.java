/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import asiakaskortisto.Asiakas;
import asiakaskortisto.Asiakas.Tila;
import asiakaskortisto.Asiakkaat;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
    public void lisaaAsiakasOK() throws IOException {
        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        int asiakasmaara = as.getKaikkienAsiakkaidenMaara();
        System.out.println("asiakasmaara ennen " + asiakasmaara);
        as.lisaaAsiakas(new Asiakas("", "TestiAs1", "TestiAs1Os 1",
                "11122 Testikyla", "111-2222222", "Terttu Testaaja", "19-02-2012", Tila.NORMAALI));
        System.out.println("asiakasmaara jälkeen " + as.getKaikkienAsiakkaidenMaara());
        assertEquals(asiakasmaara + 1, as.getKaikkienAsiakkaidenMaara());

    }

    @Test
    public void haeAsiakasAsiakasNumerollaOK() throws IOException {

        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        as.lisaaAsiakas(new Asiakas("", "TestiAs9", "TestiAs1Os 1",
                "11122 Testikyla", "111-2222222", "Terttu Testaaja", "19-02-2012", Tila.NORMAALI));
        Asiakas haettuAsiakas = as.haeAsiakasOlioNimella("TestiAs9");
        String asiakasnumero = haettuAsiakas.getAsiakasNumero();
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
            System.out.println("a ei null");
            haettu = a.getAsiakasNumero();
        }
        System.out.println(asiakasnumero + " " + haettu);
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
        assertTrue(haettu.contains(nimi));
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

    @Test
    public void muutaAsiakasOK() throws IOException {

        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        String nimi = "TestiAs1";
        String uusiPuhelin = "888-222333";
        String muutettuPuhelin = "";
        Asiakas vanha = as.haeAsiakasOlioNimella(nimi);
        if (vanha != null) {
            
            Asiakas uusi = new Asiakas(vanha.getAsiakasNumero(), vanha.getAsiakasNimi(), "", "", uusiPuhelin, "", "", Tila.NORMAALI);
            as.muutaAsiakas(vanha, uusi);
            Asiakas muutettuAsiakas = as.haeAsiakasOlioNimella(nimi);
            
            if (muutettuAsiakas != null) {
                 muutettuPuhelin = muutettuAsiakas.getPuhelinnumero();
            }
        }
        assertEquals(uusiPuhelin, muutettuPuhelin);
    }
    
     @Test
    public void muutaAsiakasNOK() throws IOException {

        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        String nimi = "TestiAs1";
        String uusiAsiakasnumero = "77777";
        String muutettuAsiakasnumero = "";
        Asiakas vanha = as.haeAsiakasOlioNimella(nimi);
        if (vanha != null) {
            
            Asiakas uusi = new Asiakas(uusiAsiakasnumero, vanha.getAsiakasNimi(), "", "", "", "", "", Tila.NORMAALI);
            as.muutaAsiakas(vanha, uusi);
            Asiakas muutettuAsiakas = as.haeAsiakasOlioNimella(nimi);
            
            if (muutettuAsiakas != null) {
                 muutettuAsiakasnumero = muutettuAsiakas.getAsiakasNumero();
            }
        }
        assertNotSame(uusiAsiakasnumero, muutettuAsiakasnumero);
        
    }
    
     @Test
    public void tarkistaNumerojarjestys() throws IOException {

        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        ArrayList<Asiakas> asiakaslistaNumeroittain = as.getAsiakaslistaNumeroittain();
        Boolean ok = true;
        int edellinen = 0;
        for (Asiakas a: asiakaslistaNumeroittain) {
         if (edellinen > Integer.parseInt(a.getAsiakasNumero())){
             ok = false;
        }
         edellinen = Integer.parseInt(a.getAsiakasNumero());
        }
        assertEquals(true, ok);
       
     }
     
     @Test
    public void poistaAsiakasOK() throws IOException {

        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        String nimi = "TestiAs1";
        
        Asiakas vanha = as.haeAsiakasOlioNimella(nimi);
        String poistettu = as.poistaAsiakas(vanha.getAsiakasNumero());
        
        assertTrue(poistettu.contains(vanha.getAsiakasNumero()));
    }
     
       @Test
    public void poistaAsiakasNOK() throws IOException {
        Asiakkaat as = new Asiakkaat("kirjoitustesti.txt");
        String numero = "66666";
        String poistettu = as.poistaAsiakas(numero);
        assertFalse(poistettu.contains(numero));
        
    }
     
}