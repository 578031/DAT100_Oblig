package no.hvl.dat100.prosjekt;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;

public class ShowSpeed extends EasyGraphics {
	
	private static int[] times;
	private static double[] latitudes;
	private static double[] longitudes;
	private static double[] elevations;
		
	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t
	
	private static GPSComputer gpscomputer; 
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		
		GPSData gpsdata = GPSDataReaderWriter.readGPSFile(filename);
		 
		gpscomputer = new GPSComputer(gpsdata);
		
		times = gpscomputer.times;
		latitudes = gpscomputer.latitudes;
		longitudes = gpscomputer.longitudes;
		elevations = gpscomputer.elevations;
	}
	
	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = times.length-1; // number of data points
		
		makeWindow("Speed profile", 2*MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}
	
	public void showSpeedProfile(int ybase, int N) {
		
		System.out.println("Angi tidsskalering i tegnevinduet ...");
		int timescaling = Integer.parseInt(getText("Tidsskalering"));
		
		// hent hastigheter på de ulike deler av ruten
		double[] speeds = gpscomputer.speeds();
		
		// TODO:
		
		// OPPGAVE - START		
		for (int i=0; i<N; i++) {
			int x1 = timescaling;
			int start = x1*i;
			int y1 = ybase-(int)speeds[i];
			int x2 = timescaling-1;
			int y2 = (int)speeds[i];
			
			setColor(0,0,255);
			drawRectangle(start,y1,x2,y2);	
		}
		
		double average = 0;
		double distance = 0;
		int totaltime = 0;
		
		for(int i=0; i<latitudes.length-1; i++) {
					
			double pointDistance = GPSUtils.distance(latitudes[i], longitudes[i], latitudes[i+1], longitudes[i+1]);
			distance += pointDistance;	
				
		}	
				
		totaltime = times[times.length-1] - times[0];
				
		average = (distance/totaltime)*3.6;
		int averageInt = (int)average;
		
		setColor(0,255,0);
		fillRectangle(0,ybase-averageInt,500, 2 );
		
		
		// OPPGAVE - SLUTT
	}
}
