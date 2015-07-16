import java.io.FileNotFoundException;
import java.io.PrintWriter;
//import java.util.ArrayList;

import javax.swing.JFrame;


public class MonteCarlo {
	public static void main(String[] args) {
		//doesn't really make sense to make this a class constant,
		//but at least to be able to change it in the future, here
		//are constants for the number of bins and size of list
		final int SIZE = 100000, NUM_BINS = 11;
		
		//instantiates a Simulation.
		//gen will have Gaussian random numbers in an ArrayList
		Simulation gen = new Simulation(SIZE, NUM_BINS);
		//get histogram data
		int[] bins = gen.makeBins();
	
		//initialize a PrintWriter so it doesn't complain about
		//uninitialized w
		PrintWriter w = null;

		//because it's possible to not find the file
		//we are creating
		try {
			w = new PrintWriter("Gauss.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		//print the count of each bin to Gauss.txt
		for(int bin : bins)
			w.println(bin);

		//close Gauss.txt
		w.close();
		
		//verify distribution as the assignment asks.
		//i = {1.0, 2.0, 3.0}
		//just a printf() with a function call to
		//Metrics.verifyDistribution() as a parameter.
		for(int i=1; i<=3; i++)
			System.out.printf("For a distribution with\n"
					+ "\tmean = %.1f\n"
					+ "\tstandard_deviation = %.1f\n"
					+ "\t%.1f standard deviations\n"
					+ "%.2f%% of the the set is in the distribution\n\n",
							  0.0, 1.0, (double)i,
							  Metrics.verifyDistribution(gen.getArrayList(), 0.0, 1.0, i));
		
		
		
		Histogram h = new Histogram(gen);
		JFrame visuals = new JFrame();
		visuals.setTitle("CSc 221 Histogram");
		visuals.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		visuals.add(h);
		visuals.setSize(1200, 800);
		visuals.setVisible(true);
		
	}
	
	

}
