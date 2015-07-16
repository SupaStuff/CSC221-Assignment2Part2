import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Simulation {
	public void generateNormalRandomNumbers(ArrayList<Double> rand_array, int size){//generates random numbers and store onto an array.
		final Random rand = new Random();
		for(int i=0; i<size; i++){
			rand_array.add(rand.nextGaussian());//using gaussian distribution so as to get random numbers with mean and standard deviation as 0.0 and 1.0 respectively
		}
	}
//This method takes the rand_array as parameter and returns a bin_array containing a count of elements lying between certain ranges
	public int[] makeBins(ArrayList<Double> rand_array, int bin_count){
		double max = Collections.max(rand_array);//get max number from rand_array
		double min = Collections.min(rand_array);//get min number from rand_array
		int[] bin_array = new int[bin_count]; //initialize bin_array with a size of bin_count
		double range = (max - min)/bin_count;//calculates the range in which the elements will be stored
		for (double element : rand_array) {
			bin_array[getBin(element, max, min, range)]++;//call method getBin that returns an index at which the element should be in and iterates the value of bin_array accordingly on that index. initially bin_array has all elements as 0 
		}
		return bin_array;
	}
//This method takes an element from rand_array as parameter and returns the index of bin_array where the element lies
	private int getBin(double element, double max, double min, double range){
		int index = 0;
		for(double i=min; i<=max-range; i=i+range){//iterate i from min value "to max-range", each iteration is by a value of "i+range"
			// it goes till max-range and not max because in the of loop we use extreme points from i to "i+range" i.e when i reaches "max-range" "i+range" reaches max
			if(element < i+ range && element >= i && element!=max){//all values other than max are checked if they lie b/w the set range if they do then break through the loop and return index
				break;
			}
			else
			if(element == max && ((Math.abs(element-(i+range))) <= 0.0000001)){//when the element is maximum and the difference b/w element and range is almost equal then break through the loop and return index
				break;
			}//i+range occasionally is not exactly max but a rounded value of it hence instead of equating it with max a uncertainty error is set  
			else{
				index++;
			}
		}
		return index;
	}
}