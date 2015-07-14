
import java.util.*;

public class Simulation {
	
	//reference of ArrayList can't be changed but elements are mutable
	private final ArrayList<Double> randomNumberArray = new ArrayList<Double>(); 
	private int numberOfBins;
	
	public Simulation(final int numberOfRandomNumbers, final int numberOfBins){
		this.generateNormalRandomNumber(numberOfRandomNumbers);  //generates our array
		this.changeNumberOfBins(numberOfBins); //sets number Of Bins
	}
	
	//can change the elements of randomNumberArray but calling this 
	public void generateNormalRandomNumber(final int numberOfRandomNumbers){
		this.randomNumberArray.clear();								//clear the arrayList 
		Random generator = new Random();  							//init Random 
		for(int i = 0; i < numberOfRandomNumbers; i++)				//generate random number and add to array list
			this.randomNumberArray.add(generator.nextGaussian());
	}
	
	public int getNumberOfBins() {
		return numberOfBins;
	}
	
	//can change the number of bins 
	public void changeNumberOfBins(int numberOfBins) {
		this.numberOfBins = numberOfBins;  
	}
	
	//gives access to our array 
	public ArrayList<Double> getRandomNumberArray(){
		return this.randomNumberArray;
	}
	
	public final double getMin(){
		return Collections.min(this.randomNumberArray);  			//return minimum value in ArrayList
	}
	
	public final double getMax(){
		return Collections.max(this.randomNumberArray); 				//return max value in ArrayList
	}	
	
	public final double getRange(){
		return Collections.max(this.randomNumberArray) - Collections.min(this.randomNumberArray);  //subtract min value from max value
	}
	
	//round min value down to nearest tenth
		public double getArrayLowerBound(){
			return Math.floor(this.getMin() * 10) / 10; 
		}
		
		//round max value up to nearest tenth
		public double getArrayUpperBound(){
			return Math.ceil(getMax() * 10) / 10;
		}
	
	//returns our bin's size
		public double getBinSize(){
			final double arrayLowerBound = this.getArrayLowerBound(); //round min value down to nearest tenth
			final double arrayUpperBound = this.getArrayUpperBound();  //round max value up to nearest tenth
			return (arrayUpperBound-arrayLowerBound) / this.numberOfBins;   //subtract arrayLowerBound from arrayUpperbound and divide by size to get each bin's size
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
	
	public int[] makeBins(){
		final double arrayLowerBound = this.getArrayLowerBound(); //round min value down to nearest tenth
		final double arrayUpperBound = this.getArrayUpperBound();  //round max value up to nearest tenth
		final double binSize = this.getBinSize();
		final int [] binsArray = new int[this.numberOfBins]; //create bins to keep count
		for (Double value: this.randomNumberArray)  //get bin number of each value in the ArrayList and increment it in binsArray
			binsArray[getBin(value, binSize, arrayLowerBound, arrayUpperBound)]++;
		return binsArray; //return array of counter
	}
}
