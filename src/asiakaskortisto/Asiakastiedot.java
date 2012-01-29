/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import java.util.Date;

/** Asiakkaan tieto-olio
 *
 * @author Kati
 */
public class Asiakastiedot {
/** Asiakkaasta talletettavat tiedot*/
    private static int suurinasiakasnumero = 10000;
    //* Asiakkaan tiedot tallettavsa olio
    private String asiakasNumero;
    private String nimi;
    private String kadunNimi;
    private String talonNumero;
    private String postinumero;
    private String postitoimipaikka;
    private String puhelinnumero;
    private String yhteyshenkilo;
    private Date asiakkaaksitulopvm;
    private char tila; // N normaali, A arkistoitu

    public Asiakastiedot(
            String numero, String nimi, String katuosoite, String talonnumero,
            String postinumero, String toimipaikka, String puhelin,
            String yhteyshenkilo, Object object, char tila) {
        this.asiakasNumero = Integer.toString(suurinasiakasnumero++);
        this.nimi = nimi;
        this.kadunNimi = katuosoite;
        this.talonNumero = talonnumero;
        this.postinumero = postinumero;
        this.postitoimipaikka = toimipaikka;
        this.puhelinnumero = puhelin;
        this.yhteyshenkilo = yhteyshenkilo;
        this.asiakkaaksitulopvm = null;
        this.tila = tila;

    }

    public String getAsiakasNumero() {
        return asiakasNumero;
    }

    public String getNimi() {
        return nimi;
    }

    @Override
    public String toString() {
        return "" + String.format("%-10s", asiakasNumero)
                + String.format("%-30s", nimi)
                + String.format("%-30s", kadunNimi)
                + String.format("%-5s", talonNumero)
                + String.format("%-10s", postinumero)
                + String.format("%-15s", postitoimipaikka)
                + String.format("%-15s", puhelinnumero)
                + String.format("%-30s", yhteyshenkilo) + "\n";
    }
}
