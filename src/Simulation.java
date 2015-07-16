import java.util.ArrayList;
import java.util.Random;


//Simulation's methods do not require instantiation of a Simulation object,
//so they will all be static methods.
public class Simulation {
	//some instance variables for instantiation
	private ArrayList<Double> randomNumberArray; 
	private int numberOfBins;
	
	public Simulation(final int numberOfRandomNumbers, final int numberOfBins){
		this.randomNumberArray = new ArrayList<Double>();
		generateNormalRandomNumbers(this.randomNumberArray, numberOfRandomNumbers);
		this.numberOfBins = numberOfBins;
	}
	
	public static void generateNormalRandomNumbers(ArrayList<Double> d, int size)
	{
		//random number generator seeded with milliseconds since epoch
		Random randie = new Random(System.currentTimeMillis());
		//fill ArrayList with as many random numbers indicated by size
		for(int i=0; i<size; i++) d.add(new Double(randie.nextGaussian()));
	}
	public static int[] makeBins(ArrayList<Double> d, int numbins)
	{
		//create an array to count numbers appearing in each bin
		int[] bins = new int[numbins];
		//initialize bins
		for(int i=0; i<numbins; i++) bins[i] = 0;
		
		//get the min of d
		double min = getMin(d);
		//calculate the size of each bin
		double size = 0;
		
		//divide by 0 is bad. Better safe than sorry
		if(numbins > 0) size = getRange(d)/numbins;
		
		//for each double, increment the count of the bin it belongs in
		for(Double u : d) bins[getBin(u.doubleValue(), size, min, numbins)]++;
		
		return bins;
	}
	//These 5 methods do what they say they do
	public int getNumberOfBins()
	{
		return this.numberOfBins;
	}
	public ArrayList<Double> getArrayList()
	{
		return this.randomNumberArray;
	}
	//handle our own data by using the
	//previously defined static methods
	public double getBinSize()
	{
		return getRange(this.randomNumberArray)/this.numberOfBins;
	}

	public int[] makeBins()
	{
		return makeBins(this.randomNumberArray,this.numberOfBins);
	}
	
	public double getArrayLowerBound()
	{
		return getMin(this.randomNumberArray);
	}
	
	//I see no use for the next few methods outside of makeBins(),
	//so they are private
	
	//returns the index of the bins array that num belongs in
	//this can be found by rounding down the range from min to num
	//divided by the size of each bin.
	private static int getBin(double num, double size, double min, double numbins)
	{
		int binIndex = 0;
		//divide by 0 is bad. Prevention > debugging
		if(size > 0) binIndex = (int)((num-min)/size);
		//sometimes, (num-min)/size = numbins + 0.000000001...
		if(binIndex == numbins) binIndex--;
		return binIndex;
	}
	
	private static double getMin(ArrayList<Double> d)
	{
		//Double has a max value! o_0
		//it's better than trying to access the 0th
		//Double in an empty arrayList
		double min = Double.MAX_VALUE;
		//for each Double in d, replace min
		//if it is less than the previous min
		for(Double u : d)
		{
			if(u.doubleValue() < min) min = u.doubleValue();
		}
		//if d was empty, this is returning Double.MAX_VALUE
		return min;
	}
	private static double getMax(ArrayList<Double> d)
	{
		//Double has a min value! o_0
		//it's better than trying to access the 0th
		//Double in an empty arrayList
		double max = Double.MIN_VALUE;
		//for each Double in d, replace max
		//if it is greater than the previous max
		for(Double u : d)
		{
			if(u.doubleValue() > max) max = u.doubleValue();
		}
		//if d was empty, this is returning Double.MIN_VALUE
		return max;
	}
	private static double getRange(ArrayList<Double> d)
	{
		//range = max - min
		return (getMax(d) - getMin(d));
	}
}