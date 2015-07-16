import java.util.ArrayList;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
public class MonteCarlo {
	//This class includes the main method that calls from two classes Metrics and Simulation, 
	//and uses their method verifyDistribution, MakeBins, generateNormalRandomNumbers respectively  
	public static void main(String[] args) {
		ArrayList<Double> rand_array = new ArrayList<Double>();//creating arraylist rand_array to store random gaussian numbers
		Simulation sim1 = new Simulation();//creating a new object sim1 from class Simulation
		sim1.generateNormalRandomNumbers(rand_array,100000);//calling method from class Simulation to generate random numbers and store in rand_array
		try {
			PrintStream out = new PrintStream(new FileOutputStream("Gauss.txt"));//creating output file and naming it gauss.txt
		    for (int element : sim1.makeBins(rand_array, 11)) {//enhanced for loop calling method makebins that returns a array hence allowing us to use the enhanced for loop
					System.out.println(element);//output each element onto the console
					out.println(element);//output each element onto the text file
			}
		    out.close();//closing the temporary parameter out after using it to write onto the text file
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Percentage of numbers in our list have values between -1.0 and 1.0 : " + Metrics.verifyDistribution(rand_array,0.0,1.0,1.0) + "%");//calling static method that return a double and printing it onto the console
		System.out.println("Percentage of numbers in our list have values between -2.0 and 2.0 : " + Metrics.verifyDistribution(rand_array,0.0,1.0,2.0) + "%");
		System.out.println("Percentage of numbers in our list have values between -3.0 and 3.0 : " + Metrics.verifyDistribution(rand_array,0.0,1.0,3.0) + "%");
	}
}
