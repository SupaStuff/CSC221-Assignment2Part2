class Metrics {
	double contain=0;				//Carrys tot value
	double posbound=0;				//Contains positive bound
	double negbound=0;				//Contains negative bound
	double tally=0;					//Tallys for number with std dev range
	double getpercent(){return(tally / contain) * 100 ;}
	
	void verifyDistribution (double avg, double tot, double stddev, double dist) {	//takes avg, total, stddev, and # of iterations
		tally=0;												//Resets tally
		posbound = avg + (stddev * dist);						//Sets positive bounds																		 
		negbound = avg - (stddev * dist);						//Sets negative bounds										
		contain=tot;											//Sets contain value
	}
	
	void percent(double element){
		if(element >= negbound && element <= posbound){			//Checks to see if element falls into deviation
		tally++;												//tallys if it does
		}
	}
}