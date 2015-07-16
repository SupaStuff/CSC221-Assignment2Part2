import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MonteCarlo {

	public static void main(String[] args) throws IOException {
		Simulation ourGenerator = new Simulation();  //init Simulation 
		MonteCarlo mc = new MonteCarlo();   		// init MonteCarlo
		ArrayList<Double> randomNumberArray = new ArrayList<Double>(); //init ArrayList for random numbers
		
		
		ourGenerator.generateNormalRandomNumber(randomNumberArray, 100000); //generate array of 100,000 random numbers
		int [] binsArray = ourGenerator.makeBins(randomNumberArray, 11);    //create array of 11 bins and puts random numbers from randomNumberArray into corresponding bins 
		
		mc.writeArrayToFile(binsArray, "Gauss.txt");  //write binsArray to file "Gauss.txt"
		
		mc.printVerifyDistribution(randomNumberArray, 0, 1.0, 1.0); //print verify distribution with values 0,1,1
		mc.printVerifyDistribution(randomNumberArray, 0, 1.0, 2.0); //print verify distribution with values 0,1,2
		mc.printVerifyDistribution(randomNumberArray, 0, 1.0, 3.0); //print verify distribution with values 0,1,3
	
	}
	
	
	
	//takes an array as input and writes the array to a file
	public void writeArrayToFile(final int[] array, final String fileName){
		try {
			FileWriter fw = new FileWriter(fileName);	//opens file for writing 
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (int value : array){
				bw.write(Integer.toString(value)); //for each value in array, write to file
				bw.newLine();   //write new line
			}
			bw.close(); //close file
		}
		catch(IOException e){
			System.out.println(e.toString());  //prints error message if fails  
		}
	}
	
	
	//prints verifyDistribution nicely 
	public void printVerifyDistribution(final ArrayList<Double> array, final double mean, final double sdev, final double fromMean){
		final double percent = Metrics.verifyDistribution(array, mean, sdev, fromMean);  //get percentage 
		System.out.printf("(%2.2f, %2.2f, %2.2f) = %2.2f%%\n", mean,sdev,fromMean,percent); //prints nicely
	}

}
