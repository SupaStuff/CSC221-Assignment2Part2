import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;


//Draws histogram of Simulation object. 

// NOTE: 	Position (0,0) is at the top left corner of the screen.	
@SuppressWarnings("serial")
public class Histogram extends JPanel{
	final int TOP_MARGIN = 20;
	final int BOTTOM_MARGIN = 20;
	final int LEFT_MARGIN = 20;
	final int RIGHT_MARGIN = 20;
	
	// Declarations of instance variables:
	private int numberOfBins;
	private double binSize;
	private int[] binsArray; 	//frequency of random numbers 
	private double min;			//binArray lower bound
	private int maxYValue; 	 	//Y-axis goes from 0 to maxYValue
	
	// constructor
	public Histogram(Simulation s) {
		setBackground(Color.WHITE);
		// Set values of instance variables ...
		this.numberOfBins = s.getNumberOfBins();  
		this.binSize = s.getBinSize();
		this.binsArray = s.makeBins();
		this.min = s.getArrayLowerBound();
		this.maxYValue = getMaxFrequency(this.binsArray);
	}
	
	//returns largest frequency in binsArray
	private static int getMaxFrequency(int [] bin) {
		int max = 0;	
		for (int value : bin) 
			if (value > max)
				max = value;
		return max;   //NOTE: returns 0 if binsArray is empty
	}
	
	// paintConponent draws the histogram
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawXAxis(g);
		drawYAxis(g);
		drawBins(g);
		drawXLabels(g);
		drawYLabels(g);
	}
	// drawXAxis Draws the x-axis
	private void drawXAxis(Graphics g) {
		int x1 = LEFT_MARGIN;
		int y1 = getHeight() - BOTTOM_MARGIN;
		int x2 = getWidth() - RIGHT_MARGIN;
		int y2 = y1;
		g.drawLine(x1, y1, x2, y2);
	}
	
	private void drawYAxis(Graphics g) {
		int x1 = LEFT_MARGIN;
		int y1 = getHeight() - BOTTOM_MARGIN;
		int x2 = x1;
		int y2 = TOP_MARGIN;
		g.drawLine(x1, y1, x2, y2);
		}
		// drawBins draws the bins
	private void drawBins(Graphics g) {
		
		final int barWidth = this.getBarWidth(); 
		int xPosition = this.LEFT_MARGIN;  //x position of our bars, set initially to origin 
		for(int value : this.binsArray){
			int barHeight = this.getBarHeight(value);		//get value's bar height
			int yPosition = this.getBarYPosition(barHeight);

			//draws rectangle
			g.setColor(Color.GRAY);
			g.fillRect(xPosition, yPosition, barWidth, barHeight);
			//draw a border around the rectangle
			g.setColor(Color.WHITE);
			g.drawRect(xPosition, yPosition, barWidth, barHeight);
			xPosition += barWidth; //move to next bar's x-position
		}
	}
		// drawXLabels draws the labels along the x-axis
	private void drawXLabels(Graphics g) {
		g.setColor(Color.BLACK);
		final int barWidth = this.getBarWidth();
		DecimalFormat formatter = new DecimalFormat();
		formatter.setMinimumFractionDigits(2);
		formatter.setMaximumFractionDigits(2);

		 // Sample code (which you may or may not choose to use)
		double labelVal = min;
		String label = formatter.format(labelVal);
		int x = LEFT_MARGIN;
		int y = getHeight() - BOTTOM_MARGIN + 12;
		for (int b : binsArray) {
			g.drawString(label, x-12, y);
			x += barWidth;
			labelVal += binSize;
			label = formatter.format(labelVal);
		}
		g.drawString(label, x-12, y);
	}
		// drawYLabels draws the labels along the y-axis,
		// i.e., draws the count of the bins on top of the bins
	private void drawYLabels(Graphics g) {
		g.setColor(Color.BLUE);
		final int barWidth = this.getBarWidth();
		//instantiate a formatter to format our labels
		DecimalFormat formatter = new DecimalFormat("#,###");
		int xPosition = this.LEFT_MARGIN + 10;
		int yPosition;
		String label;
		//iterate through each value in binsArray
		for (int value : this.binsArray){
			//create the formatted label
			label = formatter.format(value);
			//calculate the y position to place the label
			yPosition = this.getBarYPosition(this.getBarHeight(value)) - 5;
			//place the label at the correct position (x, y)
			g.drawString(label, xPosition, yPosition);
			//shift over to the next bar's position (if any)
			xPosition += barWidth;
		}
	}
	
	// takes y-value and returns the height of it's bar 
	// Objects are drawn in terms of pixels. We have to scale values to draw them correctly.
	private int getBarHeight(int yValue){
		int lengthOfYAxis = this.getHeight() - this.TOP_MARGIN - this.BOTTOM_MARGIN;
		return (int)(((double)yValue / this.maxYValue) * lengthOfYAxis);  
		//NOTE: Must cast (yValue / this.maxYValue) into double first 
		//		or it will return 0.
	}
	
	
	//NOTE: ORIGIN OF DRAWING SURFACE IS AT TOP LEFT CORNER.
	//		If you do g.drawRect(0,0,50,50), the square of side length 50 will be drawn
	//		at the top left corner.
	// 		This function gets the bar's y-position taking that into account.
	private int getBarYPosition(int barHeight){
		return this.getHeight() - this.BOTTOM_MARGIN - barHeight;
	}
	
	//returns every bar's width. Expects the JPanel width to be set
	//NOTE: barWidth is used a lot but we have to be careful when we initialize it.
	//		When we initialize a Histogram object, the object has width 0 and height 0.
	//		The size is set after we call: visuals.setSize(1200, 800) in the MonteCarlo class.
	private int getBarWidth()
	{
		return ((getWidth() - LEFT_MARGIN - RIGHT_MARGIN) / numberOfBins);
	}
}
	
