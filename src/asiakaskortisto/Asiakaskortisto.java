/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asiakaskortisto;

/**
 *
 * @author kxkyllon
 */
public class Asiakaskortisto {

    public static void main(String[] args) {
        //* Asiakaskortiston pääohjelma
        Asiakas asiakas = new Asiakas();
        Tekstiliittyma teksti = new Tekstiliittyma();


        boolean lopetus = false;
        while (!lopetus) {
            int valinta = teksti.tulostaValikko();
            switch (valinta) {
                case 0: //Lopetus
                    System.out.println("Ohjelma suljetaan");
                    //todo: kirjoitetaan asiakkaat tiedostoon
                    lopetus = true;
                    break;
                case 1: //Asiakastoiminnot
                    kasitteleAsiakasValinta(teksti.tulostaAsiakasValikko(), asiakas, teksti);
                    break;
                default: //Listaa valikko
                    valinta = teksti.tulostaValikko();
            }
        }

    }

    private static void kasitteleAsiakasValinta(int asiakasValinta, Asiakas asiakas, Tekstiliittyma teksti) {
        switch (asiakasValinta) {
            case 0: // Palaa päävalikkoon
                System.out.println("Palataan päävalikkoon");
                return;
            case 1: // Listaa asiakkaat
                System.out.println("Asiakaslistaus");
                //todo hae asiakaslistaus
                break;
            case 3: // Hae asiakas
                System.out.println("Siirry hakunäytölle");
                // todo kysy hakuehto ja hae
                break;
            case 4: // Poista asiakas 
                System.out.println("Siirry poistonäytölle");
                // ==merkitse asiakas arkistoiduksi
                break;
            case 5: // Muuta asiakastietoja
                System.out.println("Siirry muutosnäytölle");
                // kysy hakuehto ja hae muutettavaksi
                break;
            default:
                return;
        }

    }
}
