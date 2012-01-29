/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import java.util.HashMap;

/** Asiakaskortisto-ohjelman sovelluslogiikka
 *
 * @author Kati
 */
public class Asiakastoiminnot {
    /**  Asiakastoiminnot */
    private String tiedostonNimi = "asiakastiedosto.txt";
    private HashMap<String, Asiakastiedot> asiakaslista = new HashMap <String, Asiakastiedot>();

    public String getTiedostonNimi() {
       return tiedostonNimi;
    }
    
    

    public String listaaAsiakkaat() {
        /**Muotoiltu asiakaslistaus*/
        String listalla =
                "" + String.format("%-10s", "Asiakasnumero ")
                + String.format("%-30s", "Nimi ")
                + String.format("%-30s", "Katu ")
                + String.format("%-5s", "Talo ")
                + String.format("%-10s", "Postinumero ")
                + String.format("%-15s", "Toimipaikka ")
                + String.format("%-15s", "Puhelinnumero")
                + String.format("%-30s", "Yhteyshenkilo \n\n ");
                

        for (String asiakasNimi : asiakaslista.keySet()) {
            listalla = listalla + asiakaslista.get(asiakasNimi).toString();
        }

        return listalla;
    }

    String lisaaAsiakas(Asiakastiedot uusiAsiakas) {
        /**Lisätään uusi asiakas */
        asiakaslista.put(uusiAsiakas.getNimi(), uusiAsiakas);
        return uusiAsiakas.getNimi();
    }
    
    
}
