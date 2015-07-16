
import java.util.*;

public class Simulation {
	
	public void generateNormalRandomNumber(final ArrayList<Double> array,final int size){
		array.clear(); 								//clear the arrayList 
		Random generator = new Random();  			//init Random 
		for(int i = 0; i < size; i++)				//generate random number and add to array list
			array.add(generator.nextGaussian());
		
	}
	
	public final double getMin(final ArrayList<Double> array){
		return Collections.min(array);  			//return minimum value in an ArrayList
	}
	
	public final double getMax(final ArrayList<Double> array){
		return Collections.max(array); 				//return max value in ArrayList
	}	
	
	public final double getRange(final ArrayList<Double> array){
		return Collections.max(array) - Collections.min(array);  //subtract min value from max value
	}
	
	public int getBin(final double number, final double binSize, final double min, final double max){
		int index = -1;
		double binLowerBound = min; 		//start at the smallest bin's lower bound
		do{
			++index;						//increment index
			binLowerBound += binSize;		//add binSize to the binLowerBound to get the next bin's lower bound
		}while (binLowerBound <= number);	//if our number > bin/lowerBound, then number doesn't belong in the prior bin; repeat do statement
		if (number == max) --index; //if number == max, decrement index by 1 because the last bin's upper bound is inclusive 
		return index;	
	}
	
	public int[] makeBins(final ArrayList<Double> array, final int size){
		final double arrayLowerBound = Math.floor(getMin(array) * 10) / 10; //round min value down to nearest tenth
		final double arrayUpperBound = Math.ceil(getMax(array) * 10) / 10;  //round max value up to nearest tenth
		final double binSize = (arrayUpperBound-arrayLowerBound) / size;   //subtract arrayLowerBound from arrayUpperbound and divide by size to get each bin's size
		final int [] binsArray = new int[size]; //create bins to keep count
		for (Double value: array)  //get bin number of each value in the ArrayList and increment it in binsArray
			binsArray[getBin(value, binSize, arrayLowerBound, arrayUpperBound)]++; 
	
		return binsArray; //return array of counter
	}
	
}
