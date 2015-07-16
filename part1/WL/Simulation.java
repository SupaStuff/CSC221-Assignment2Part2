import java.util.ArrayList; 								//Allows array list to be a type
import java.util.Random;									//Allows use of nextGaussian

class Simulation {
	double max;												//Public variable max
	double min;												//Public variable min
	double range;											//Public variable range
	double total;											//Public variable total
	double binsize;											//Public variable # of bins made
	double getbinsize(){return binsize;}
	double mean;											//Public variable mean
	ArrayList<Double> temparr;								//Public ArrayList
	double[] binarr;										//Array that holds bin
	double getmean(){return mean;}							//Function that returns mean
	double gettotal(){return total;}						//Function that returns total
	
	void generateNormalRandomNumbers(int num){				//Chose not to use ArrayList since we declare # of items we want 	
		Random rando = new Random();						//Declaring Random Class							
		temparr = new ArrayList<>();						//Declaring new ArrayList
		for (int i = 0; i < num; i++){											
			temparr.add(rando.nextGaussian());				//Sets Gaussian values into the ArrayList
		}
	
		total = num;										//Total is # given by user
		min = temparr.get(0);								//Set min as 1st value in ArrayList
		max = temparr.get(0);								//Set max as 1st value in ArrayList
		
		for(int i = 0;  i < num; i++){
			if(temparr.get(i) < min) {min = temparr.get(i);}	//If value at index < min, value at index is new min
			if(temparr.get(i) > max) {max = temparr.get(i);}	//If value at index > max, value at index is new max
		}
		
		range = (max - min);								//Range is (max-min)/total
		double temp=0;										//Local variable to help calculate mean
		
		for (int i=0;i<total;i++){							//Helps calculate mean
		temp+=temparr.get(i);
		}
		mean=temp/total;									//Sets mean
		}

	void call(){			//Purely optional, used to view binarr
		System.out.println(" ");
		for(int i = 0; i < binsize; i++){						
			System.out.println(binarr[i]);
		}	
		System.out.println("");
	}
	
	void makeBins(int take) {
		double value;							//placeholder value to hold value from getbin
		double hold;							//used to increment elements in array
		binsize = take;							//set binsize
		binarr= new double[11];					//declare size of array to be 11 same as bin
		for (int i = 0; i < total; i++){											
			value=getBin(temparr.get(i));										//Loops through Arraylist and feeds # to get bin
			if (value==0)			{hold=binarr[0]; 	 binarr[0]=hold+1;}		//If getbin is 0 increment 0
			else if (value==1)		{hold=binarr[1];	 binarr[1]=hold+1;}		//If getbin is 1 increment 1		
			else if (value==2)		{hold=binarr[2];	 binarr[2]=hold+1;}		//If getbin is 2 increment 2
			else if (value==3)		{hold=binarr[3];	 binarr[3]=hold+1;}		//If getbin is 3 increment 3
			else if (value==4)		{hold=binarr[4];	 binarr[4]=hold+1;}		//If getbin is 4 increment 4
			else if (value==5)		{hold=binarr[5];	 binarr[5]=hold+1;}		//If getbin is 5 increment 5
			else if (value==6)		{hold=binarr[6];	 binarr[6]=hold+1;}		//If getbin is 6 increment 6
			else if (value==7)		{hold=binarr[7];	 binarr[7]=hold+1;}		//If getbin is 7 increment 7
			else if (value==8)		{hold=binarr[8];	 binarr[8]=hold+1;}		//If getbin is 8 increment 8
			else if (value==9)		{hold=binarr[9];	 binarr[9]=hold+1;}		//If getbin is 9 increment 9
			else if (value==10)		{hold=binarr[10];	 binarr[10]=hold+1;}	//If getbin is 10 increment 10
		}
	}

	double getBin(double check){				//Given number from the original array checks what bin it goes to
		double answer=0;						//Determines what bin check will go to. 
		double iterate=(range / binsize);		//Variable for number between each iteration
		if(check >= min && check < (min + (1 * iterate)))							{answer = 0;}		//case 1:	between min & min + iterate;
		else if(check >= (min + (1 * iterate)) && check < (min + (2 * iterate))) 	{answer = 1;}		//case 2:	between min + iterate & min + 2x iterate
		else if(check >= (min + (2 * iterate)) && check < (min + (3 * iterate))) 	{answer = 2;}		//case 3:	between min + 2x iterate & min + 3x iterate
		else if(check >= (min + (3 * iterate)) && check < (min + (4 * iterate))) 	{answer = 3;}		//case 4:	between min + 3x iterate & min + 4x iterate
		else if(check >= (min + (4 * iterate)) && check < (min + (5 * iterate))) 	{answer = 4;}		//case 5:	between min + 4x iterate & min + 5x iterate
		else if(check >= (min + (5 * iterate)) && check < (min + (6 * iterate))) 	{answer = 5;}		//case 5:	between min + 5x iterate & min + 6x iterate
		else if(check >= (min + (6 * iterate)) && check < (min + (7 * iterate))) 	{answer = 6;}		//case 6:	between min + 6x iterate & min + 7x iterate;
		else if(check >= (min + (7 * iterate)) && check < (min + (8 * iterate))) 	{answer = 7;}		//case 7:	between min + 7x iterate & min + 8x iterate;
		else if(check >= (min + (8 * iterate)) && check < (min + (9 * iterate))) 	{answer = 8;}		//case 8:	between min + 8x iterate & min + 9x iterate;
		else if(check >= (min + (9 * iterate)) && check < (min + (10 * iterate)))	{answer = 9;}		//case 9:	between min + 9x iterate & min + 10x iterate;
		else if(check >= (min + (10 * iterate)) && check <= max) 					{answer = 10;}		//case 10:	between min + 9x range & max;
		return answer;
	}
}