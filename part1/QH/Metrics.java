import java.util.*;

public class Metrics {
	
	static public double verifyDistribution(final ArrayList<Double> array, final double mean, final double standDev, final double sdFromMean){
		final double minValue = mean - (standDev * sdFromMean); //lower bound of standard deviations from mean
		final double maxValue = mean + (standDev * sdFromMean); //upper bound of standard deviations from mean
		int counter = 0;  //count numbers 
		for(Double value: array) //for values in ArrayList, if falls in between bounds, increment counter 
			if (value >= minValue && value <= maxValue)
				++counter;
			
		return ((float)counter/array.size()) * 100; //return percent of total 
	}
	
}
