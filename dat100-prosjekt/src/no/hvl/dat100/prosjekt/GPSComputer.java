package no.hvl.dat100.prosjekt;

public class GPSComputer {
	
	public GPSComputer(GPSData gpsdata) {

		GPSDataConverter converter = new GPSDataConverter(gpsdata);
		converter.convert();

		this.times = converter.times;
		this.latitudes = converter.latitudes;
		this.longitudes = converter.longitudes;
		this.elevations = converter.elevations;
	}

	// tabeller for GPS datapunkter
	public int[] times;
	public double[] latitudes;
	public double[] longitudes;
	public double[] elevations;
	
	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0;

		// TODO
		// OPPGAVE - START

		// Hint: bruk distance-metoden fra GPSUtils.
		for(int i=0; i<latitudes.length-1; i++) {
		
			double pointDistance = GPSUtils.distance(latitudes[i], longitudes[i], latitudes[i+1], longitudes[i+1]);
			distance += pointDistance;
			
		// OPPGAVE - SLUTT

		}
		return distance;
		
	}
	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = elevations[0];

		// TODO
		// OPPGAVE - START
		for (int i = 0; i<elevations.length-1; i++) {
			
			double deltaElevations = elevations[i+1] - elevations[i];
			elevation += deltaElevations;

		// OPPGAVE - SLUTT
		}
		return elevation;
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {
		
		int totaltime = 0;
		
		// TODO 
		// OPPGAVE START
		
					
		totaltime = times[times.length-1] - times[0];
					
	// OPPGAVE SLUTT
				
		return totaltime;
	}
		
	// beregn gjennomsnitshastighets mellom hver av gps punktene
	public double[] speeds() {

		double[] speeds = new double[times.length-1];
		
		// TODO
		// OPPGAVE - START
		for (int i=0; i<speeds.length; i++) {
			
			int deltaTime = times[i+1]-times[i];
			speeds[i] = GPSUtils.speed(deltaTime, latitudes[i], longitudes[i], latitudes[i+1], longitudes[i+1]);
			
		}
		// OPPGAVE - SLUTT
		return speeds;
	}

	// beregn maximum hastighet km/t
	public double maxSpeed() {
		
		double[] speeds = speeds();
		double maxspeed = 0;
		// TODO
		// OPPGAVE - START
		
		maxspeed = speeds[0];
		for (double d: speeds) {
			
			if (d > maxspeed) {
				maxspeed = d;
			}
		}
		// OPPGAVE - SLUTT
		return maxspeed;
	}
	
	// beregn gjennomsnittshasitiget for hele turen km/t
	public double averageSpeed() {
		
		
		double[] speeds = speeds();
		double average = 0;
		
		int totaltime = totalTime();
		double distance = totalDistance();
		
		// TODO
		// OPPGAVE - START
		
		average = (distance/totaltime)*3.6;
		// OPPGAVE - SLUTT
						
		return average;
	}


	// conversion factor kph (km/t) to miles per hour (mph)
	public static double MS = 0.62;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal = 0;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;		
		double speedmph = speed * MS;

		// TODO
		// OPPGAVE START
		
		if (speedmph < 10) {
			met = 4.0;
		}
		else if (10<speedmph && speedmph<12) {
			met = 6.0;
		}
		else if (12<speedmph && speedmph<14) {
			met = 8.0;
		}
		else if (14<speedmph && speedmph<16) {
			met = 10.0;
		}
		else if (16<speedmph && speedmph<20) {
			met = 12.0;
		}
		else if (speedmph > 20) {
			met = 16.0;
		}
		
		kcal = met*weight*(secs/3600);
		
			
		
		// Energy Expended (kcal) = MET x Body Weight (kg) x Time (h)

		// OPPGAVE SLUTT
		
		return kcal;
	}

	public double totalKcal(double weight) {
		
		double [] speeds = speeds();
		double totalkcal = 0;
		double met = 0;
		

		// TODO
		// OPPGAVE - START
		for (int i=0; i<speeds.length; i++) {
			
			double speedmph = speeds[i]*MS;
			
			if (speedmph < 10) {
				met = 4.0;
			}
			else if (10<speedmph && speedmph<12) {
				met = 6.0;
			}
			else if (12<speedmph && speedmph<14) {
				met = 8.0;
			}
			else if (14<speedmph && speedmph<16) {
				met = 10.0;
			}
			else if (16<speedmph && speedmph<20) {
				met = 12.0;
			}
			else if (speedmph > 20) {
				met = 16.0;
			}
			
			double pointKcal = met*(times[i+1]-times[i])* weight;
			totalkcal =+ pointKcal;
		}
		
		
			
		
			
		// Hint: hent hastigheter i speeds tabellen og tider i timestabellen
		// disse er definer i toppen av klassen og lese automatisk inn
		
		// OPPGAVE - SLUTT
		
		return totalkcal;
	}
	
	private static double WEIGHT = 80.0;
	
	// skriv ut statistikk for turen
	public void print() {
		
		
		// TODO
		// OPPGAVE - START
		/*int totalTime = totalTime();
		double totalDistance = totalDistance();
		double totalElevation = totalElevation();
		double maxSpeed = maxSpeed();
		double averageSpeed = averageSpeed();
		double totalKcal = totalKcal(WEIGHT); 
		
		//TotalTime, Totaldistance, Totalelevation,Max speed, Average Speed, Energy
		String timeView = GPSUtils.printTime(totalTime);
		String.format("Total Time %-15.d", timeView);
		String.format("Total Distance %-15.f", totalDistance);
		String.format("Total Elevation %-15.f", totalElevation);
		String.format("Max Speed %-15.f", maxSpeed);
		String.format("Average Speed %-15.f", averageSpeed);
		String.format("Total Kcal %-15.f", totalKcal); */
		
		// OPPGAVE - SLUT
	}
	
	// ekstraoppgaver
	public void climbs() {
		
	}
	
	public void maxClimb() {
		
	}
}
