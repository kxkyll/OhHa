/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;


/** Kayttäjän tieto tieto-olio
 *
 * @author Kati
 */
public class Kayttaja {
/** Käyttäjän käyttäjätunnuksen, salasanan ja roolin tiedot*/
    
   
    private String kayttajaTunnus;
    private String salasana;
    private Rooli rooli;

    public Kayttaja(String kayttajaTunnus, String salasana) {
        this.kayttajaTunnus = kayttajaTunnus;
        this.salasana = salasana;
    }

    public Kayttaja(String kayttajaTunnus, String salasana, Rooli rooli) {
        this.kayttajaTunnus = kayttajaTunnus;
        this.salasana = salasana;
        this.rooli = rooli;
    }

    
    
    
    public enum Rooli {
        TYONTEKIJA {
    @Override
    public String toString() {        return "TYONTEKIJA";    }}, 
        ESIMIES  {
    @Override
    public String toString() {        return "ESIMIES";    }}, 
        YLLAPITO {
    @Override
    public String toString() {        return "YLLAPITO";    }} };
    
    
    

    public String getKayttajaTunnus() {
        return kayttajaTunnus;
    }

    public Enum getRooli() {
        return rooli;
    }

    public String getSalasana() {
        return salasana;
    }

    public void setKayttajaTunnus(String kayttajaTunnus) {
        this.kayttajaTunnus = kayttajaTunnus;
    }

    public void setSalasana(String salasana) {
        this.salasana = salasana;
    }

    @Override
    public String toString() {
        return "" + this.getKayttajaTunnus() +" " + this.getSalasana() +" " +this.getRooli();
    }
   
}
