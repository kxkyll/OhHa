/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import java.util.Scanner;

/**
 *
 * @author Kati
 */

public class Kirjaudu {
    /** Pyydetään käyttäjältä käyttäjätunnus ja salasana ja tarkastetaan 
      * täsmäävätkö ne salasanatiedostossa oleviin */
    
    String kayttaja;
    String salasana;
        static Scanner lukija = new Scanner(System.in);

    public Boolean Kirjaudu() {
        System.out.println("Anna kayttajatunnus: ");
        kayttaja = lukija.nextLine();
        System.out.println("Anna salasana: ");
        salasana = lukija.nextLine();
        return tarkistaKirjautuminen (kayttaja, salasana);
    }

    private Boolean tarkistaKirjautuminen(String kayttaja, String salasana) {
        /** Tarkistetaan löytyvätkö annettu käyttäjätunnus & salasanapari tiedostosta */
        return true;
    }
    
    
    
}
