import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;

public class MonteCarlo {

	public static void main(String[] args) throws IOException {
		Simulation ourGenerator = new Simulation(100000, 11);  //init Simulation with 100000 random numbers and 11 bins
		
		// NOTE:	Number of bins can be changed by calling: ourGenerator.changeNumberOfBins(new size)
		//			New set of random numbers can be created by calling: ourGenerator.generateNormalRandomNumber(new size)
		
		MonteCarlo mc = new MonteCarlo();   		// init. MonteCarlo
		
		int [] binsArray = ourGenerator.makeBins();    //create array of 11 bins and puts random numbers from randomNumberArray into corresponding bins 
		
		mc.writeArrayToFile(binsArray, "Gauss.txt");  //write binsArray to file "Gauss.txt"
		
		mc.printVerifyDistribution(ourGenerator.getRandomNumberArray(), 0, 1.0, 1.0); //print verify distribution with values 0,1,1
		mc.printVerifyDistribution(ourGenerator.getRandomNumberArray(), 0, 1.0, 2.0); //print verify distribution with values 0,1,2
		mc.printVerifyDistribution(ourGenerator.getRandomNumberArray(), 0, 1.0, 3.0); //print verify distribution with values 0,1,3
		
		Histogram h = new Histogram(ourGenerator);
		JFrame visuals = new JFrame();
		visuals.setTitle("CSc 221 Histogram");
		visuals.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		visuals.add(h);
		visuals.setSize(1200, 800);
		visuals.setVisible(true);
		
	}
	
	
	
	//takes an array as input and writes the array to a file
	public void writeArrayToFile(final int[] array, final String fileName){

			FileWriter fw = new FileWriter(fileName);	//opens file for writing 
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (int value : array){
				bw.write(Integer.toString(value)); //for each value in array, write to file
				bw.newLine();   //write new line
			}
			bw.close(); //close file
		}
	
	
	//prints verifyDistribution nicely 
	public void printVerifyDistribution(final ArrayList<Double> array, final double mean, final double sdev, final double fromMean){
		final double percent = Metrics.verifyDistribution(array, mean, sdev, fromMean);  //get percentage 
		System.out.printf("(%2.2f, %2.2f, %2.2f) = %2.2f%%\n", mean,sdev,fromMean,percent); //prints nicely
	}

}
