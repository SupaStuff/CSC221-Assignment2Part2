import java.util.ArrayList;

/*
 * Jean Pena
 */

public class Metrics {
	public static double verifyDistribution(ArrayList<Double> d, 
										  double xBar, 
										  double sigma, 
										  double stds)
	{
		//lower and upper bounds based on parameters
		//and counter for values in distribution
		double lowerB = xBar - (sigma * stds),
			   upperB = xBar + (sigma * stds);
		int inDistribution = 0;
		
		//for each Double in d, if it is in the distribution,
		//increment the counter
		for(Double u : d)
			if(u.doubleValue() >= lowerB && u.doubleValue() <= upperB)
				inDistribution++;
		
		//return percentage in distribution
		return inDistribution * 100.0 / d.size();
	}
}
