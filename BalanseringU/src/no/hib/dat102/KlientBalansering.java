package no.hib.dat102;

import no.hvl.dat102.Balansering;

public class KlientBalansering{
     public static void main(String[] args){
    	 
        final String filnavn = "C:\\Users\\magnu_000\\git\\dat102\\BalanseringU\\src\\no\\hvl\\dat102\\parantesLinje";
        //Leser inn en tekst fra fil
        Balansering balansering = new Balansering();
        balansering.lesFraFil(filnavn);
        
 }//main

}//class
