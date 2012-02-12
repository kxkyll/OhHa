/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/** Asiakkaan tieto-olio
 *
 * @author Kati
 */
public class Asiakastiedot {
/** Asiakkaasta talletettavat tiedot*/
    
   
    private String asiakasNumero;
    private String asiakasNimi;
    private String katuOsoite;
    private String postiOsoite;
    private String puhelinnumero;
    private String yhteyshenkilo;
    private String asiakkaaksitulopvm;
    private Tila tila; // N normaali, A arkistoitu
    
    public enum Tila {
        NORMAALI {
    @Override
    public String toString() {        return "NORMAALI";    }}, 
        ARKISTOITU  {
    @Override
    public String toString() {        return "ARKISTOITU";    }}
    };
    
    
//    Calendar tamaPaiva = Calendar.getInstance();
//    SimpleDateFormat formatter = new SimpleDateFormat ("dd.MM.yyyy");


    public Asiakastiedot(
            String asiakasnumero, String nimi, String katuosoite, 
            String postiosoite, String puhelin,
            String yhteyshenkilo, String pvm, Tila tila) {
        this.asiakasNumero = asiakasnumero;
        this.asiakasNimi = nimi;
        this.katuOsoite = katuosoite;
        this.postiOsoite = postiosoite;
        this.puhelinnumero = puhelin;
        this.yhteyshenkilo = yhteyshenkilo;
        this.asiakkaaksitulopvm = pvm;
        //this.asiakkaaksitulopvm = formatter.format(tamaPaiva.getTime());
        this.tila = tila;
    }

    public void setAsiakasNumero(String asiakasNumero) {
        this.asiakasNumero = asiakasNumero;
    }
    
    
    public String getAsiakasNumero() {
        return asiakasNumero;
    }

    public String getAsiakkaaksitulopvm() {
        return asiakkaaksitulopvm;
    }

    public String getKatuOsoite() {
        return katuOsoite;
    }

    public String getPostiOsoite() {
        return postiOsoite;
    }

    public String getPuhelinnumero() {
        return puhelinnumero;
    }

    public Tila getTila() {
        return tila;
    }

    public String getYhteyshenkilo() {
        return yhteyshenkilo;
    }

    
    public String getAsiakasNimi() {
        return asiakasNimi;
    }
        

    @Override
    public String toString() {
      return "" + String.format("%-25s", asiakasNumero)
                + String.format("%-25s", asiakasNimi)
                + String.format("%-25s", katuOsoite)
                + String.format("%-25s", postiOsoite)
                + String.format("%-25s", puhelinnumero)
                + String.format("%-25s", yhteyshenkilo) + "\n";
    }
    
}
