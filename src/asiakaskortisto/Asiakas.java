/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

/**
 * Asiakkaan tieto-olio sisältää kaikki asiakkaasta talletettavat tiedot
 *
 * @author Kati
 */
public class Asiakas implements Comparable<Asiakas> {

    /**
     * Asiakasnumero generoidaan asiakasta luotaessa
     */
    private String asiakasNumero;
    /**
     * Asiakasnimi on käyttäjän antama nimi asiakkaalle
     */
    private String asiakasNimi;
    /**
     * Katuosoite on käyttäjän antama asiakkaan osoite
     */
    private String katuOsoite;
    /**
     * Postiosoite on käyttäjän antama asiakkaan postiosoite
     */
    private String postiOsoite;
    /**
     * Puhelinnumero on käyttäjän antama asiakkaan puhelinnumero
     */
    private String puhelinnumero;
    /**
     * Yhteyshenkilö on käyttäjän antama asiakkaan yhteyshenkilö
     */
    private String yhteyshenkilo;
    /**
     * Asiakkaaksitulopvm generoidaan asiakasta luotaessa
     */
    private String asiakkaaksitulopvm;
    /**
     * Tila on joko Normaali tai Arkistoitu, Arkistoitu tarkoittaa että asiakas
     * ei ole aktiivinen asiakas
     */
    private Tila tila; // Normaali, Arkistoitu

   /** Asiakkaan tilatieto on joko Normaali tai Arkistoitu 
    * Normaali on aktiivinen asiakas
    * Arkistoitu on entinen asiakas
    */
    public enum Tila {

        NORMAALI {

            @Override
            public String toString() {
                return "NORMAALI";
            }
        },
        ARKISTOITU {

            @Override
            public String toString() {
                return "ARKISTOITU";
            }
        }
    };

    public Asiakas(
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
        this.tila = tila;
    }

    public void setAsiakasNumero(String asiakasNumero) {
        this.asiakasNumero = asiakasNumero;
    }

    public void setTilaArkistoiduksi() {
        this.tila = Tila.ARKISTOITU;
    }

    public void setKatuOsoite(String katuOsoite) {
        this.katuOsoite = katuOsoite;
    }

    public void setPostiOsoite(String postiOsoite) {
        this.postiOsoite = postiOsoite;
    }

    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }

    public void setYhteyshenkilo(String yhteyshenkilo) {
        this.yhteyshenkilo = yhteyshenkilo;
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

    /**
     * palautetaan asiakkaan tiedot muotoiltuna merkkijonona
     */
    @Override
    public String toString() {
        return "" + String.format("%-25s", asiakasNumero)
                + String.format("%-25s", asiakasNimi)
                + String.format("%-25s", katuOsoite)
                + String.format("%-25s", postiOsoite)
                + String.format("%-25s", puhelinnumero)
                + String.format("%-25s", yhteyshenkilo) + "\n";
    }

    /**
         * Asiakasolioiden järjestäminen nousevaan asiakasnumerojärjestykseen
         * tehdään compareTo metodin avulla
         */
    @Override
    public int compareTo(Asiakas toinen) {
        
        if (this.getAsiakasNumero().equals(toinen.getAsiakasNumero())) {
            return this.getAsiakasNumero().compareTo(toinen.getAsiakasNumero());
        } else {
            return this.getAsiakasNimi().compareTo(toinen.getAsiakasNimi());
        }
    }
}
