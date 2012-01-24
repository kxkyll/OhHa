/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

import java.util.Scanner;

/**
 *
 * @author kxkyllon
 */
class Tekstiliittyma {
    //* Asiakaskortiston tektipohjainen valikko
    static Scanner lukija = new Scanner(System.in);
    
    public int tulostaValikko() {
        System.out.println();
        System.out.println("Asiakaskortisto");
        System.out.println("1 - Asiakastoiminnot");
        //System.out.println("2 - Työtehtävätoiminnot");
        //System.out.println("3 - Laskutustoiminnot");
        System.out.println("0 - Lopetus");
        System.out.print("Anna valintasi: ");
        return Integer.parseInt(lukija.nextLine());
    }
    
    public int tulostaAsiakasValikko() {
        System.out.println();
        System.out.println("Asiakastoiminnot");
        System.out.println("1 - Listaa asiakkaat");
        System.out.println("2 - Hae asiakas");
        System.out.println("3 - Lisää asiakas");
        System.out.println("4 - Poista asiakas");
        System.out.println("5 - Muuta asiakastietoja");
        System.out.println("0 - Paluu päävalikkoon");
        System.out.print("Anna valintasi: ");
        return Integer.parseInt(lukija.nextLine());
    }
    
    
}
