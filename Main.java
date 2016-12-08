package diskScheduler;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


                                        //------------GROUP MEMBERS-----------// 
                                        //                                    //
                                        // 			  MOHAMMED DIALLO         // 
                                        //            SUFIYAN CAMARA          // 
                                        //                                    //
                                        //                                    //
                                        //------------------------------------//


//@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener 
{
	private JPanel cylnValue;
	private JPanel gPanel;
	private JPanel bPanel;
	private JComboBox comboBox;
	private JButton inputButton;
	private JButton ranButton;
	//private JButton subButton;
	private JButton drawButton;
	private JButton clearButton;

	int startingPoint = 64;
	int start2finish = 0;
    
	String[] schedlNames = { "FCFS", "SSTF" }; //Names of the algorithms 
	private JLabel head;
	int[] diskPosstion;
	int selected;

	private JTextField[] iTextField;
	private JScrollPane jSPane;

	public int numOfCyn = 25;// maximum available Cylinders

	public Main() 
	{
		super("Disk Scheduling: SSTF & FCFS");    //title of the JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //Close the window when the close icon is clicked
		setSize(new Dimension(600, 650));   // Set the size of the window
		setLocationRelativeTo(null);
		//setBackground(Color.BLACK);
		setVisible(true);
		//addWindowListener(new ExitListener());
		setLayout(new FlowLayout());
		init();
	}

	public void init() 
	{
		//-------Create the panels and add them to the main frame-------//
		
		gPanel = new JPanel();
		gPanel.setPreferredSize(new Dimension(600, 400));

		cylnValue = new JPanel();
		cylnValue.setBorder(new TitledBorder("Cylinders"));
		cylnValue.setPreferredSize(new Dimension(250, 200));
		cylnValue.setBackground(Color.cyan);

		bPanel = new JPanel();
		bPanel.setPreferredSize(new Dimension(280, 200));
		bPanel.setBorder(new TitledBorder("Buttons"));
		bPanel.setBackground(Color.ORANGE);
        
		head = new JLabel("Head Possition: ");

		add(cylnValue);
		add(bPanel);
		add(gPanel);
		

		setButtonPanel();
	}

	public void setInputPanel() 
	{
	    // Start with empty Cylinders 
		cylnValue.removeAll();

		JPanel valuePanel = new JPanel();
		
		//Prompt the user to enter number of cylinders
		numOfCyn = Integer.parseInt(JOptionPane.showInputDialog(this,      
				"Enter the number of Cylinder: "));
		valuePanel.setLayout(new GridLayout(numOfCyn, 2, 10, 10));         //specify the layout of the panel
		valuePanel.setBackground(Color.cyan);
		iTextField = new JTextField[numOfCyn];

		//Loop through and display the number of cylinders inputed.
		for (int i = 0; i < iTextField.length; i++) 
		{
			JLabel label = new JLabel("Cylinder: " + (i + 1));
			label.setFont(new Font("Vardana", Font.BOLD, 14));

			iTextField[i] = new JTextField(2);
			iTextField[i].setHorizontalAlignment(JTextField.CENTER);

			valuePanel.add(label);
			valuePanel.add(iTextField[i]);
		}

		jSPane = new JScrollPane();        //JScrollPane to be added to the cyln# panel to scroll if need
		jSPane.setPreferredSize(new java.awt.Dimension(200, 150));

		jSPane.setViewportView(valuePanel);      //Be able to see the info when scrolled
		jSPane.validate();
		cylnValue.add(jSPane);   // add the values found on the JSPane
		cylnValue.revalidate();
	}

	public void setButtonPanel() 
	{
		//Define the buttons to be displayed in the panel 
		bPanel.setLayout(new GridLayout(2, 3));
		
		inputButton = new JButton("INPUT");
		ranButton = new JButton("GENERATE");
		drawButton = new JButton("DRAW");
		clearButton = new JButton("CLEAR");
		
		//Add The Listener to the Buttons
		inputButton.addActionListener(this);
		ranButton.addActionListener(this);
		drawButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		bPanel.add(inputButton);
		bPanel.add(ranButton);
		bPanel.add(drawButton);
		bPanel.add(clearButton);

		comboBox = new JComboBox(schedlNames);
		bPanel.add(comboBox);
		bPanel.revalidate();
	}

	public void setGraphPanel(JPanel panel) 
	{
		gPanel.setLayout(new FlowLayout());   //add the graph panel to the frame
		gPanel.removeAll();
		gPanel.add(panel);

		head.setFont(new Font("Vardana", Font.BOLD, 14));
		head.setForeground(Color.BLUE);
		//head to display the outcome
		head.setText("Scheduler: " + comboBox.getSelectedItem()
				+ " Head Starting point: " + startingPoint +  ","+ " Total Head Movement of " + start2finish + " tracks.");
		head.setForeground(Color.BLACK);
		gPanel.add(head);    //Add head to the graph panel
		gPanel.repaint();
		gPanel.revalidate();
	}

	@Override 
	 //specify what each button does when clicked by adding action command to them
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equalsIgnoreCase("input")) 
		{
			setInputPanel();
		} 
		else if (e.getActionCommand().equalsIgnoreCase("generate")) 
		{
			for (int i = 0; i < numOfCyn; i++) 
			{
				long random = (long) (Math.random() * 200 + 1);   //To randomly generate numbers in the input field
				iTextField[i].setText("" + random);
			}
		} 
		else if (e.getActionCommand().equalsIgnoreCase("clear")) 
		{
			for (int i = 0; i < numOfCyn; i++) 
			{
				iTextField[i].setText(" ");
			}
		}
		else if (e.getActionCommand().equalsIgnoreCase("draw"))
		{
			selected = comboBox.getSelectedIndex();
			diskPosstion = new int[numOfCyn +1];   //set the position of the disk to number of cylinder plus 1(as stating point)

			for (int i = 0; i < numOfCyn; i++)   //loop through to get the value in the input field to plot the graph
			{
				diskPosstion[i + 1] = Integer.parseInt(iTextField[i].getText());
			}
			startingPoint = Integer.parseInt(JOptionPane.showInputDialog(this,
					"Enter starting point: "));
			diskPosstion[0] = startingPoint;   //Set the first position of the disk to be the starting point on the graph

			if (selected == 0)   //if FCFS id selected
			{
				setGraphPanel(new Draw(diskPosstion));
				
				int rest = 0;
				for (int i = 0; i < numOfCyn; i++) 
				{
					rest += Math.abs(diskPosstion[i] - diskPosstion[i+1]);
				}
				System.out.println(rest);
				head.setText("Scheduler: " + comboBox.getSelectedItem()
				+ " Head Starting point: " + startingPoint+ ","+ " Total Head Movement of " + rest + " tracks.");
				
			 } 
			 else if (selected == 1) //SSFT is selected, rearrenge the cylinders in the order of nearest one to the starting point
			 {

				int[] ssft = new SSTF().getSftf(diskPosstion, startingPoint);
				int[] temp = new int[ssft.length - 1];

				for (int i = 0; i < ssft.length - 1; i++) 
				{
					temp[i] = ssft[i];
				}
				
				for (int i = 0; i < temp.length; i++) 
				{
					start2finish = Math.abs(ssft[i] - startingPoint);  //calculate the total head movements

				}
				System.out.println(start2finish);
			
				setGraphPanel(new Draw(temp)); //After getting the values, provoke the Draw class to draw the graph
			  } 		
			
			
		}
	}
	public static void main(String[] args) throws ClassNotFoundException
	{
		new Main();
	}
}

