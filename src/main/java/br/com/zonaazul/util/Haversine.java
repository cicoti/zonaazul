package br.com.zonaazul.util;
/**
 * Jason Winn
 * http://jasonwinn.org
 * Created July 10, 2013
 *
 * Description: Small class that provides approximate distance between
 * two points using the Haversine formula.
 *
 * Call in a static context:
 * Haversine.distance(47.6788206, -122.3271205,
 *                    47.6788206, -122.5271205)
 * --> 14.973190481586224 [km]
 * 
 * http://andrew.hedges.name/experiments/haversine/
 *
 */

public class Haversine {
    private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM
    
    
    /*public static void main(String args[]){
    	
    	System.out.println(distance(38.898556, -77.037852, 38.897147, -77.043934));
    	
    	//System.out.println(distance(38.898556, -77.037852, 38.897147, -77.043934));
    	//Resultado: 549.1557912038085
     	
    }*/

    public static double distance(double startLat, double startLong,
                                  double endLat, double endLong) {

        double dLat  = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat   = Math.toRadians(endLat);

        double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (EARTH_RADIUS * c) * 1000; // <-- d // resultado em metros
    }

    public static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }
}