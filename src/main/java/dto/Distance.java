package dto;

public class Distance {
	
	public double calculDistance(String latitude1, String longitude1, String latitude2, String longitude2) {
		int rayonTerre = 6371; 
		
		double longitude1Radians = DegresEnRadians(Double.parseDouble(longitude1));
		double latitude1Radians = DegresEnRadians(Double.parseDouble(latitude1));
		double longitude2Radians = DegresEnRadians(Double.parseDouble(longitude2));
		double latitude2Radians = DegresEnRadians(Double.parseDouble(latitude2));
		
		double differenceLongitudes = (longitude2Radians - longitude1Radians)/2;
		double differenceLatitudes = (latitude2Radians - latitude1Radians)/2;
		
		double a = (Math.sin(differenceLatitudes) * Math.sin(differenceLatitudes)) + Math.cos(latitude1Radians) * Math.cos(latitude2Radians) * (Math.sin(differenceLongitudes) * Math.sin(differenceLongitudes));
		double distance = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return (distance * rayonTerre);
	}

	double DegresEnRadians(double coord) {
		return coord * Math.PI / 180;
	}
}
