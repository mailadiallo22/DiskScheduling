package diskScheduler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Draw extends JPanel 
{
	
//--------------------declare arrays of x and why coordinates ----------------//
	int[] x;
	int[] y, y1, y2;
	int[] tempX;
	int[] temp1X;
	int[] temp2X;

	public Draw(int[] x)
	{
		setPreferredSize(new Dimension(520, 350));   //Set the size of the graph  
		this.x = x;
		
//---------------y-coordinates as the length of x----------//
		int temp = 0;
		y = new int[x.length];
		y1 = new int[x.length];
		y2 = new int[x.length];

//--------------Calculate/set y-coordinates ------------------//
		for (int i = 0; i < x.length; i++) 
		{
			temp += 300 / x.length;
			y[i] = temp;
			y1[i] = (int) (temp + .5);
			y2[i] = (int) (temp - .5);
		}

		tempX = new int[x.length];
		temp1X = new int[x.length];
		temp2X = new int[x.length];

		int size = 500;

		System.out.println();
		for (int i = 0; i < x.length; i++) 
		{
			tempX[i] = (x[i] * size) / 200;
			temp1X[i] = (int) (tempX[i] + .5);
			temp2X[i] = (int) (tempX[i] + .5);
		}
	}

	@Override
	protected void paintComponent(Graphics g2) 
	{
		
//-----------make the graph two dimensional x and y-------//
		
		super.paintComponent(g2);
		
		Graphics2D g = (Graphics2D) g2;
		g.setColor(Color.GRAY);

//------------Loop through to assign height and width of the graph-------------// 
		
		for (int i = 0; i < this.getSize().getHeight(); i = i + 15) 
		{
			g.drawLine(0, i, this.getSize().width, i);
		}

		for (int i = 0; i < this.getSize().width; i = i + 15) 
		{
			g.drawLine(i, 0, i, this.getSize().height);
		}

//-------------//set the color of the line connecting the points and then draw the x-point and x-point and number of points---------//
		
		g.setColor(Color.red); 
		g.drawPolyline(tempX, y, x.length);
		g.drawPolyline(temp1X, y1, x.length);
		g.drawPolyline(temp2X, y2, x.length);

		Font f = new Font("Vardana", Font.BOLD, 14);
		g.setFont(f);
		
//---------Now draw the points on the line and place the value next to the point to indicate the which value is at that point---------//
		
		for (int i = 0; i < temp1X.length; i++) 
		{
			g.setColor(Color.red);
			g.fillOval(temp1X[i] - 5, y[i] - 5, 10, 10);
			g.setColor(Color.BLUE); //set the value color to blue
			g.drawString(" " + x[i], temp1X[i], y[i]);

		}
	}
}
