/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import java.util.HashMap;

/**
 *
 * @author kxkyllon
 */
public class Asiakastoiminnot {
    //* Asiakastoiminnot
    private String tiedostonNimi = "asiakastiedosto.txt";
    private HashMap<String, Asiakastiedot> asiakaslista = new HashMap <String, Asiakastiedot>();

    public String getTiedostonNimi() {
       return tiedostonNimi;
    }
    
    
}
