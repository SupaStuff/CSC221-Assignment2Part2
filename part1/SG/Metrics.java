import java.util.ArrayList;
public class Metrics {
	// This class initiates a static method verifyDistribution to verify that resultant ran_array follows gaussian distribution
	public static double verifyDistribution(ArrayList<Double> rand_array, double mean, double std_dev, double n_std_dev){
		int c = 0;//counter used to get the total number of elements that fall within certain standard deviation
		double left_value = mean - (std_dev*n_std_dev);// how far left can the element be...
		double right_value = mean + (std_dev*n_std_dev);// how far right can the element be...
		for (double element : rand_array){//enhanced for loop to return each element from rand_array
			if(element>=left_value && element<= right_value){//if the element lies within the two extremes iterate counter by 1
				c++;
			}
		}
		double percentage = (((double)c/rand_array.size())*100);//casting double to avoid int c return 0
		return percentage;//returns double 
	}
}