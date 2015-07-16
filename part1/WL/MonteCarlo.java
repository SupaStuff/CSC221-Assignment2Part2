//By Wyatt Li

import java.io.File;										//Bottom three ports enables printing output to a text file	
import java.io.FileWriter;									
import java.io.IOException;
import java.text.DecimalFormat;								//Allows to use DecimalFormat

public class MonteCarlo {
	public static void main(String[] args){	
		
		DecimalFormat round = new DecimalFormat("0.000");		//Allows use of DecimalFormat class sets to 3 decimal places
		Simulation create= new Simulation();					//Allows to call Simulation class
		Metrics check = new Metrics();							//Allows to call Metrics class
		create.generateNormalRandomNumbers(100000);				//Creates Arraylist with 100000 random generated numbers
		create.makeBins(11);									//Creates array with 11 elements
		create.call();											//Used to print out binarr and show normal distribution
		
		check.verifyDistribution(create.getmean(),create.gettotal(),1.0,1.0);		//Takes mean, total, std, std dist
		for (int i = 0; i < create.gettotal(); i++){								//Goes through array list elements
			check.percent(create.temparr.get(i));									//Passes element into percent function	
		}
		System.out.println(round.format(check.getpercent())+ "%");					//Prints out Percentage of numbers in std dist
		
		check.verifyDistribution(create.getmean(),create.gettotal(),1.0,2.0);		//Takes mean, total, std, std dist
		for (int i = 0; i < create.gettotal(); i++){								//Goes through array list elements
			check.percent(create.temparr.get(i));									//Passes element into percent function
		}
		System.out.println(round.format(check.getpercent())+ "%");					//Prints out Percentage of numbers in std dist
		
		check.verifyDistribution(create.getmean(),create.gettotal(),1.0,3.0);		//Takes mean, total, std, std dist
		for (int i = 0; i < create.gettotal(); i++){								//Goes through array list elements
			check.percent(create.temparr.get(i));									//Passes element into percent function
		}
		System.out.println(round.format(check.getpercent())+ "%");					//Prints out Percentage of numbers in std dist
		
		try{													//Try & Catch method
			File gau = new File("src/Gauss.txt");				//Call file location in src folder
	        if (!gau.exists()){
	            	gau.createNewFile();						//If doesn't exist make new one
	            }
	        FileWriter wr = new FileWriter(gau);
	        double out;											//Variable used to hold and print elements
	        for (int i = 0; i<create.getbinsize(); i++){		//Call functions = to number of bins in this case 11
	        	out =create.binarr[i];
	            wr.write(out+System.getProperty( "line.separator" ));	//outputs to txt file
	        }               
	        wr.close();
	    }
	    catch (IOException e){
	    	e.printStackTrace();
	    } 
	}
}

