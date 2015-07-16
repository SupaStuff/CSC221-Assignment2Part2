import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;


	public class MonteCarlo {
		
		public static void main(String[] args) throws IOException {
					
			ArrayList<Double> arr = Simulation.getRandomNumbers();
			
			//Calling the returned values for the # of random numbers within Bin1 -11. 
			ArrayList<Integer> arr2 = Simulation.makeBins();


			//Prints Out # of random numbers within each bin.
			System.out.println("Gaussian Distribution or Random Numbers:");
			for (int i = 0; i <= 10; i++) {
				System.out.println(arr2.get(i));
			}
			
			//Calculates the percentage of numbers within a number of standard deviations from 1, 2, and 3.
			ArrayList<Double> arr4 = Metrics.verifyDistribution();
			
			for (int i=1; i<=3; i++ ) {
				double percentOfNumbersBetween = (100*(arr4.get(i)));
				System.out.println("(" + percentOfNumbersBetween + ")" + "of Z score " + i);	
				}
			PrintStream myconsole = new PrintStream(new File("Gauss.txt"));
			System.setOut(myconsole);
			double A = arr4.get(1)*100;
			double B = arr4.get(2)*100;
			double C = arr4.get(3)*100;
			
			myconsole.print("Gaussian Distribution or Random Numbers:" + " " + arr2.get(0) + " " + arr2.get(1) + " " + arr2.get(2) + " " + arr2.get(3) + " " + arr2.get(4) + " " + arr2.get(5) + " " + arr2.get(6) + " " + arr2.get(7) + " " + arr2.get(8) + " " + arr2.get(9) + " " + arr2.get(10) + "(" + A + ")" + "of Z score 1" + " (" + B + ")" + "of Z score 2" + "(" + C + ") of Z score 3");
			
		
		}
		
	public static class Simulation {
			
			//Generating random Gaussian numbers for normal distribution.
			public static ArrayList<Double>  getRandomNumbers() {
				
				//Generates a new and empty array	
					ArrayList<Double> array = new ArrayList<Double>();
					int i;
				
					//Adds random numbers to array, growing it.
					for ( i = 0; i < 100000; i++) {
					Random randomGenerator = new Random();
					double n = randomGenerator.nextGaussian();
					      array.add(n);	}			
						
			//returns array
			return (array);
		}
			//Counts the number of random numbers within each bin, 
			//starting from the first which starts at a closed interval of -1 and ending at 1. Counts end-points twice as they belong either bin.
			public static ArrayList<Integer> makeBins() {
				ArrayList<Double> arr = Simulation.getRandomNumbers();
				int counter1 = 0;
				int counter2 = 0;
				int counter3 = 0;
				int counter4 = 0;
				int counter5 = 0;
				int counter6 = 0;
				int counter7 = 0;
				int counter8 = 0;
				int counter9 = 0;
				int counter10 = 0;
				int counter11 = 0;
				
				//Generates a new and empty array				
				ArrayList<Integer> arr2 = new ArrayList<Integer>();
				
				double a = 1;
				double x = 5.5;
				
				//setting range for each bin, the random numbers that were generated were placed in there respective bins. 
				//The amount of numbers per bin were counted and the total was added to the new array.
				for (int i = 0; i< arr.size(); i++) {
							if (arr.get(i) <= (-5.5+(2*a)) && arr.get(i)>= (-5.5)) {
								counter1++;}
								} arr2.add(counter1);
				for (int i = 0; i< arr.size(); i++) {
					if (arr.get(i) <= (-x+(2*a)) && arr.get(i)>= (-x+(1*a))) {
						counter2++;}
						} arr2.add(counter2);
				for (int i = 0; i< arr.size(); i++) {
					if (arr.get(i) <= (-x+(3*a)) && arr.get(i)>= (-x+(2*a))) {
						counter3++;}
						} arr2.add(counter3);
				for (int i = 0; i< arr.size(); i++) {
					if (arr.get(i) <= (-x+(4*a)) && arr.get(i)>= (-x+(3*a))) {
						counter4++;}
						} arr2.add(counter4);
				for (int i = 0; i< arr.size(); i++) {
					if (arr.get(i) <= (-x+(5*a)) && arr.get(i)>= (-x+(4*a))) {
						counter5++;}
						}arr2.add(counter5);
				for (int i = 0; i< arr.size(); i++) {
					if (arr.get(i) <= (-x+(6*a)) && arr.get(i)>= (-x+(5*a))) {
						counter6++;}
						} arr2.add(counter6);
				for (int i = 0; i< arr.size(); i++) {
					if (arr.get(i) <= (-x+(7*a)) && arr.get(i)>= (-x+(6*a))) {
						counter7++;}
						} arr2.add(counter7);
				for (int i = 0; i< arr.size(); i++) {
					if (arr.get(i) <= (-x+(8*a)) && arr.get(i)>= (-x+(7*a))) {
						counter8++;}
						}arr2.add(counter8);
				for (int i = 0; i< arr.size(); i++) {
					if (arr.get(i) <= (-x+(9*a)) && arr.get(i)>= (-x+(8*a))) {
						counter9++;}
						} arr2.add(counter9);
				for (int i = 0; i< arr.size(); i++) {
					if (arr.get(i) <= (-x+(10*a)) && arr.get(i)>= (-x+(9*a))) {
						counter10++;}
						} arr2.add(counter10);
				for (int i = 0; i< arr.size(); i++) {
					if (arr.get(i) <= (-x+(11*a)) && arr.get(i)>= (-x+(10*a))) {
						counter11++;}
						}arr2.add(counter11);
				
						//returns the array of amount of numbers per bin.
						return arr2; 
			}
		}
			public static class Metrics {
				
				// Used for calculating the standard deviations and the amount of random numbers within each.
				public static ArrayList<Double> verifyDistribution() {
									
					// calling the returned amounts of random variables in each bin.
				ArrayList<Integer> arr3 = Simulation.makeBins();
			
					//Calculating the total amount of random variables.
				int total = arr3.get(0) + arr3.get(1) + arr3.get(2) + arr3.get(3) + arr3.get(4) + arr3.get(5) + arr3.get(6) + arr3.get(7) + arr3.get(8) +arr3.get(9) + arr3.get(10);
					
				
				//gives the ratio of number amount of random variables within each standard deviation.
					//denoted as n* (and number of standard deviations)				
					double n1 = (arr3.get(0) + arr3.get(1) + arr3.get(2) + arr3.get(3) + arr3.get(4) + arr3.get(5) + arr3.get(6) + arr3.get(7) + arr3.get(8) +arr3.get(9) + arr3.get(10));
					double n2 = (arr3.get(1) + arr3.get(2) + arr3.get(3) + arr3.get(4) + arr3.get(5) + arr3.get(6) + arr3.get(7) + arr3.get(8) +arr3.get(9));
					double n3 = (arr3.get(2) + arr3.get(3) + arr3.get(4) + arr3.get(5) + arr3.get(6) + arr3.get(7) + arr3.get(8));
					double n4 = (arr3.get(3) + arr3.get(4) + arr3.get(5) + arr3.get(6) + arr3.get(7));
					double n5 = (arr3.get(4) + arr3.get(5) + arr3.get(6));
					double n6 = (arr3.get(5));
					
					n1 = n1/total;
					n2 = n2/total;
					n3 = n3/total;
					n4 = n4/total;
					n5 = n5/total;
					n6 = n6/total;
					
					
					ArrayList<Double> arr4 = new ArrayList<Double>();
					//adds each ratio as an element in the new ArrayList.
					arr4.add(n6);
					arr4.add(n5);
					arr4.add(n4);
					arr4.add(n3);
					arr4.add(n2);
					arr4.add(n1);
					
					//returns the ArrayList.
				return (arr4);
				}

			}
			
		}
			

		
		



