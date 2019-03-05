package no.hvl.dat102;

import java.io.*;

import no.hib.dat102.adt.StabelADT;
import no.hib.dat102.tabell.TabellStabel;

public class Balansering {
	// Her opphever du kommentarsetning når du har fått lagt inn
	// nødvendig kode
	// SirkulaerStabel<Parentesinfo>stabel = new
	// SirkulaerStabel<Parentesinfo>();

	private boolean passer(char åpent, char lukket) {
		switch (åpent) {
		case '(':
			return (lukket == ')');
		case '[':
			return (lukket == ']');
		case '{':
			return (lukket == '}');
		default:
			return false;
		}
	}//
	
	private boolean erOpen(char c) {
		return (c == '(' || c == '[' || c == '{');
	}
	
	private boolean erLukket(char c) {
		return (c == ')' || c == ']' || c == '}');
	}

	public void foretaBalansering(String innDataStreng, int linjenr) {

		int lengde = innDataStreng.length();
		// Fyll ut
		
		StabelADT<Character> stabel = new TabellStabel<Character>();
		for(int i=0; i<lengde; i++) {
			char aktuell = innDataStreng.charAt(i);
			//Hvis det er en åpen så legger man det inn i stabelen
			if(erOpen(aktuell)){
				stabel.push(aktuell);
			}
			//Hvis det er en lukke må man først teste om stabelen er tom
			else if(erLukket(aktuell)) {
				if(!stabel.erTom()) {
					if(passer(stabel.peek(), aktuell)) {
						stabel.pop();
					}
					else 
						System.out.println("Advarsel: '" + stabel.peek() + "' og '" + aktuell + "' passer ikke sammen ");
				}
				else
					System.out.println("Lukkesymbol '" + aktuell + "' på linje " + linjenr + ". På tegn nr " + i + ", mangler tilsvarende åpnesymbol");
			}
		}
		if(!stabel.erTom()) {
			System.out.println("'" + stabel.peek() + "' mangler tilsvarende lukkesymbol");
		}

	}//

	public void lesFraFil(String filnavn) {
		FileReader tekstFilLeser = null;
		try {
			tekstFilLeser = new FileReader(filnavn);
		} catch (FileNotFoundException unntak) {
			System.out.println("Finner ike filen!");
			System.exit(-1);
		}

		BufferedReader tekstLeser = new BufferedReader(tekstFilLeser);
		String linje = null;
		int linjenr = 0;
		try {
			linje = tekstLeser.readLine();
			while (linje != null) {
				// kalle metode her!
				// Fyll ut
				foretaBalansering(linje, linjenr);
				linjenr++;
				linje = tekstLeser.readLine();

			} // while
		}

		catch (IOException unntak) {
			System.out.println("Feil ved innlesing!");
			System.exit(-1);
		}
		try {
			tekstFilLeser.close();
		} catch (IOException unntak) {
			System.out.println("Feil ved lukking av fil!");
		}

	}// metode

}// class
