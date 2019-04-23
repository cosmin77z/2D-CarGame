
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;
import javax.swing.border.Border;
 
 
public class CCarCrash extends JFrame implements ActionListener
{
	private JTextField Option,Square,Direction,Digital1,minCount,secCount;
    private JLabel OptionLabel, SquareLabel, DirectionLabel,DigitalTimer;
    private JButton jBExit, jBOption1, jBOption2, jBOption3, jBAct, jBReset, jBRun, jBCompas;
    private JPanel jPMain, jPSide, jPBottom, jPControl;
    private JSlider jBSlider;
    private JMenuBar topMenuBar; //declare menu
    private JMenu fileMenu, editMenu, searchMenu, helpMenu; //declare sub-menus
    private JMenuItem exitItem, helpItem, aboutItem;
    
    private Icon ActIcon, RunIcon, ResetIcon, n_btn, pressed_btn, space,wall_horiz, person, sand, whiteBlock, wall_vert, wall_stangasus, wall_stangajos, wall_dreaptasus, wall_dreaptajos, compas_n, compas_s, compas_w, compas_e, car_left, car_right, car_up, car_down, up_btn, down_btn, left_btn, right_btn;
    
    private int nOpt1=1;
    private int nOpt2=17;
    private String nOpt3="E";
    private String dir = "E";
    private int ticks = 0, runTicks =0;
    private int act=0, run=0;
    private Timer timer, runTimer;
    int runTime = 0;
    int option=1; //default option is 1
    int sliderOption = 1;
    private int timerStarted = 0;  // 0 - not started               1 - running            2 - finished
    private JButton[] button = new JButton[208];
    private JButton[] jBDirection = new JButton[9];
    int[] movingArea = {17,18,19,20,21,22,23,24,25,26,27,28,29,30,33,34,49,50,65,66,81,82,97,98,113,114,129,130,145,146,161,162,177,178, 179,180,181,182,183,184,185,186,187,188,189,190,45,46,61,62,77,78,93,94,109,110,125,126,141,142,157,158,173,174};
    
    
    //array for act() - option 1
    int[] actArray1 = {18,19,20,21,22,23,24,25,26,27,28,29,45,61,77,93,109,125,141,157,173,189,188,187,186,185,184,183,182,181,180,179,178,177};
    //car direction for array1
    int[] facing_array1 = {1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3};
    
    
    //array for act() - option 2
    int[] actArray2 = {18,19,20,21,22,23,24,25,26,27,28,29,30,46,62,61,77,93,109,125,126,142,158,157,173,189,188,187,186,185,184,183,182,181,180,179,178};
  //car direction for array2
    int[] facing_array2 = {1,1,1,1,1,1,1,1,1,1,1,1,2,2,3,2,2,2,2,1,2,2,3,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3};
    
    
    
    
    int[] wallArray = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,32,48,64,80,96,112,128,144,160,176,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207};
    int[] objects = {65,114,177,45,94,141,174};
    
    private int CarPos = 17;
    
    
 
    public static void main (String[] args)
    {
    	CCarCrash frame = new CCarCrash();
        frame.setSize(810, 650);
        frame.createGUI();
        frame.setResizable(false);
        frame.setTitle("CCarCrash – Car Race Application");
        frame.setLocationRelativeTo(null);
        frame.show();
    }
 
    private void createGUI()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout() );
        
        menuSetup();
       
        try	
        {
        	ActIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("step.png")));
        	RunIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("run.png")));
        	ResetIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("reset.png")));
        	n_btn = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("btn.png")));
        	pressed_btn = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("pressed_btn.png")));
        	space = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("space.png")));
        	wall_horiz = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("wall-horiz.png")));
        	wall_vert = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("wall-vert.png")));
        	wall_stangasus = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("wall-NW.png")));
        	wall_stangajos = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("wall-SW.png")));
        	wall_dreaptasus = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("wall-NE.png")));
        	wall_dreaptajos = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("wall-SE.png")));
        	
        	//control buttons
        	up_btn = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("up.png")));
        	down_btn = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("down.png")));
        	left_btn = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("left.png")));
        	right_btn = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("right.png")));
        	
        	//compass
        	compas_n = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("north.jpg")));
        	compas_s = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("south.jpg")));
        	compas_w = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("west.jpg")));
        	compas_e = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("east.jpg")));
        	
        	//car
        	car_right = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("car-e.png")));
        	car_left = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("car-w.png")));
        	car_up = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("car-n.png")));
        	car_down = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("car-s.png")));
        	whiteBlock = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("sandstone.jpg")));
        	person = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("person.png")));
        	sand = new ImageIcon(Toolkit.getDefaultToolkit().createImage(CCarCrash.class.getResource("sand.jpg")));

        }
        catch (Exception e)
        {
            System.err.println(e);
        
        }
        
        /*Panels*/
        jPMain = new JPanel();
        jPMain.setPreferredSize(new Dimension(580, 540));
        jPMain.setBackground(Color.GRAY);
        jPMain.setLayout(new GridLayout(13,16));
        window.add(jPMain);
        for(int a=0; a<=207;a++)
        {
        	button[a] = new JButton("");
        	if(a >= 1 && a <=14) button[a].setIcon(wall_horiz);
        	else if (a >= 36 && a <= 43) button[a].setIcon(wall_horiz);
        	else if (a >= 164 && a <= 171) button[a].setIcon(wall_horiz);
        	else if (a >= 193 && a <= 206) button[a].setIcon(wall_horiz);
        	else if(a == 51 ||  a == 67 ||  a == 83 ||  a == 99 ||  a == 115 ||  a == 131 ||  a == 147) button[a].setIcon(wall_vert);
        	else if(a == 60 ||  a == 76 ||  a == 92 ||  a == 108 ||  a == 124 ||  a == 140 ||  a == 156) button[a].setIcon(wall_vert);
        	else if(a == 16 ||  a == 32 ||  a == 48 ||  a == 64 ||  a == 80 ||  a == 96 ||  a == 112 ||  a == 128 ||  a == 144  ||  a == 160  ||  a == 176) button[a].setIcon(wall_vert);
        	else if(a == 31 ||  a == 47 ||  a == 63 ||  a == 79 ||  a == 95 ||  a == 111 ||  a == 127 ||  a == 143 ||  a == 159  ||  a == 175  ||  a == 191) button[a].setIcon(wall_vert);
        	else if(a == 0) button[a].setIcon(wall_stangasus);
        	else if(a == 15) button[a].setIcon(wall_dreaptasus);
        	else if(a == 192) button[a].setIcon(wall_stangajos);
        	else if(a == 207) button[a].setIcon(wall_dreaptajos);
        	else if(a == 35) button[a].setIcon(wall_stangasus);
        	else if(a == 44) button[a].setIcon(wall_dreaptasus);
        	else if(a == 163) button[a].setIcon(wall_stangajos);
        	else if(a == 172) button[a].setIcon(wall_dreaptajos);
        	else if(a == 177 && option == 1) button[a].setIcon(whiteBlock);
        	else button[a].setIcon(space);
        	button[a].setBorderPainted(false);
            jPMain.add(button[a]);
        }
        
        jPSide = new JPanel();
        jPSide.setPreferredSize(new Dimension(200, 540));
        window.add(jPSide);
        
        jPBottom = new JPanel();
        jPBottom.setPreferredSize(new Dimension(810, 70));
        //jPBottom.setBackground(Color.BLUE);
        window.add(jPBottom);
        
        /*Labels+TextFields*/
        
        JPanel jPFields = new JPanel();
        jPFields.setPreferredSize(new Dimension(180, 80));
        jPFields.setLayout(new GridLayout(3,1));
        jPSide.add(jPFields);
        
        OptionLabel = new JLabel("Option: ");
        jPFields.add(OptionLabel);
        Option = new JTextField(""+ nOpt1);
        jPFields.add(Option);
        
        
        SquareLabel = new JLabel("Square: ");
        jPFields.add(SquareLabel);
        Square = new JTextField(""+ CarPos);
        jPFields.add(Square);
        
        
        DirectionLabel = new JLabel("Direction: ");
        jPFields.add(DirectionLabel);
        Direction = new JTextField(""+ nOpt3);
        jPFields.add(Direction);
        
        //Controlling Keys
        
        jPControl = new JPanel();
        jPControl.setPreferredSize(new Dimension(180, 180));
        jPControl.setLayout(new GridLayout(3,3));
        window.add(jPControl);
        
        for(int btn=0;btn<=8;btn++)
        {
        	jBDirection[btn] = new JButton("");
            jBDirection[btn].setPreferredSize(new Dimension(50, 30));
            	if(btn % 2 == 0)
            	{
            		jBDirection[btn].setIcon(pressed_btn);
            	}
            	else if(btn % 2 != 0)
            	{
					jBDirection[btn].addActionListener(new MoveClass());
            	}
            jPSide.add(jBDirection[btn]);
            
            if(btn == 1) jBDirection[btn].setIcon(up_btn);
            else if(btn == 3) jBDirection[btn].setIcon(left_btn);
            else if(btn == 5) jBDirection[btn].setIcon(right_btn);
            else if(btn == 7) jBDirection[btn].setIcon(down_btn);
        }
        /*Digital Timer*/
        DigitalTimer = new JLabel("DIGITAL TIMER");
        jPSide.add(DigitalTimer);
        
        
        JPanel jPTimer = new JPanel();
        jPTimer.setPreferredSize(new Dimension(150, 25));
        jPTimer.setLayout(new GridLayout(1,3));
        jPSide.add(jPTimer);
        
        Digital1 = new JTextField("00");
        Digital1.setPreferredSize(new Dimension(30,20));
        Digital1.setBackground(Color.BLACK);
        Digital1.setForeground(Color.WHITE);
        jPTimer.add(Digital1);
        
        minCount = new JTextField("00");
        minCount.setPreferredSize(new Dimension(30,20));
        minCount.setBackground(Color.BLACK);
        minCount.setForeground(Color.WHITE);
        jPTimer.add(minCount);
        
        secCount = new JTextField("00");
        secCount.setPreferredSize(new Dimension(30,20));
        secCount.setBackground(Color.BLACK);
        secCount.setForeground(Color.WHITE);
        jPTimer.add(secCount);
 
        /*Buttons*/
        
        //Right Side Panel Buttons
        JPanel jSpace = new JPanel(); jSpace.setPreferredSize(new Dimension(180, 90)); jPSide.add(jSpace);
        
        JPanel jPOptions = new JPanel();
        jPOptions.setPreferredSize(new Dimension(180, 80));
        jPOptions.setLayout(new GridLayout(2,2,5,5));
        jPSide.add(jPOptions);
        
        jBOption1 = new JButton("Option 1");
        jBOption1.setPreferredSize(new Dimension(85, 25));
        jPOptions.add(jBOption1);
        jBOption1.setBackground(Color.WHITE);
        jBOption1.addActionListener(this);
        
        jBOption2 = new JButton("Option 2");
        jBOption2.setPreferredSize(new Dimension(85, 25));
        jPOptions.add(jBOption2);
        jBOption2.setBackground(Color.WHITE);
        jBOption2.addActionListener(this);
        
        jBOption3 = new JButton("Option 3");
        jBOption3.setPreferredSize(new Dimension(85, 25));
        jPOptions.add(jBOption3);
        jBOption3.setBackground(Color.WHITE);
        jBOption3.addActionListener(this);
        
        jBExit = new JButton("Exit");
        jBExit.setPreferredSize(new Dimension(85, 25));
        jPOptions.add(jBExit);
        jBExit.setBackground(Color.WHITE);
        jBExit.addActionListener(this);
        
        //Bottom Panel Buttons
        jBAct = new JButton("Act");
        jBAct.setPreferredSize(new Dimension(85, 25));
        jBAct.setIcon(ActIcon);
        jBAct.setBackground(Color.WHITE);
        jBAct.addActionListener(this);
        jPBottom.add(jBAct);
        
        jBRun = new JButton("Run");
        jBRun.setPreferredSize(new Dimension(85, 25));
        jPBottom.add(jBRun);
        jBRun.setBackground(Color.WHITE);
        jBRun.setIcon(RunIcon);
        jBRun.addActionListener(this);
               
        jBReset = new JButton("Reset");
        jBReset.setPreferredSize(new Dimension(100, 25));
        jBReset.setBackground(Color.WHITE);
        jPBottom.add(jBReset);
        jBReset.setIcon(ResetIcon);
        jBReset.addActionListener(this);
        
        jBCompas = new JButton("");
        jBCompas.setPreferredSize(new Dimension(85, 85));
        jBCompas.setIcon(compas_e);
        //jBCompas.setBorderPainted(false);
        jBCompas.addActionListener(this);
        jPSide.add(jBCompas);
        
        //spacer
        JPanel jSpace2 = new JPanel(); jSpace2.setPreferredSize(new Dimension(150, 20)); jPBottom.add(jSpace2);
        //slider
        
        JLabel SpeedLabel = new JLabel("Speed: ");
        jPBottom.add(SpeedLabel);
        jBSlider = new JSlider(JSlider.HORIZONTAL, 1000, 3000, 1000);  
        jPBottom.add(jBSlider);
        
        
        timer = new Timer(1000, this); 
        runTimer = new Timer(1000, new MoveClass()); 
    
    }
    
    public void actionPerformed(ActionEvent event)
    {
    	Object source = event.getSource();
    	if(source == jBOption1)
    	{
    		option1();
    	}
    	if(source == jBOption2)
    	{
    		option2();
    	}
    	if(source == jBOption3)
    	{
    		option3();
    	}
    	if(source == jBExit)
    	{
    		System.exit(0);
    	}
    	if(source == jBRun)
    	{
    		run();
    	}
    	if(source == jBAct)
    	{
    		act();
    	}
    	if(source == jBReset)
    	{
    		reset();
    	}
    	if(timerStarted == 1)
    	{
    		minCount.setText("0" + Integer.toString(ticks / 60));
        	secCount.setText("0" + Integer.toString( ticks % 60));
            ticks = ticks + 1;
    	}
    	if(runTime == 1) {
    		act();
    	}
    }
    //option 1 button method + actions
    private void option1()
    {
    	Option.setText("1");
    	option = 1;
    	reset();
    	for(int nr: objects)
		{
			button[nr].setIcon(space);
		}
    	
    }
    //option 2 button method + actions
    private void option2()
    {
    	Option.setText("2");
    	option = 2;
    	reset();
    	for(int nr: objects)
		{
    		if(nr % 2 == 0) button[nr].setIcon(whiteBlock);
    		else if(nr % 2 != 0) button[nr].setIcon(sand);
			
		}
    }
    //option 3 button method + actions
    private void option3()
    {
    	Option.setText("3");
    	option = 3;
    }
    
    //run button action
    
    private void run()
    {
    	if(runTime == 0)
		{
			runTime = 1;
			runTimer.start();
		}
    	else {runTime=0;}
    	if(timerStarted == 0)
		{
			timer.start();
			timerStarted=1;
		}
    	else {timerStarted=0;ticks=0;}
    }
    
    //act button action (one movement - a block - at a time)
    
    int arrayPosition=0,maxVar=0;
    int facing = 1; // 1 = east       2 = south      3 = west
    private void act()
    {
    	if(option == 1) {facing = facing_array1[arrayPosition];maxVar=32;}
    	else if(option == 2) {facing = facing_array2[arrayPosition];maxVar=36;}
    	if(arrayPosition < maxVar)
    	{
    		button[CarPos].setIcon(space);
    		arrayPosition++;
        	if(facing == 1) 
        	{
        		if(option == 1) button[actArray1[arrayPosition]].setIcon(car_right); 
        		else if(option == 2) button[actArray2[arrayPosition]].setIcon(car_right); 
        		jBCompas.setIcon(compas_e);
        		Direction.setText("E");
        	}
        	else if(facing == 2)
        	{
        		if(option == 1) button[actArray1[arrayPosition]].setIcon(car_down); 
        		else if(option == 2) button[actArray2[arrayPosition]].setIcon(car_down); 
        		jBCompas.setIcon(compas_s);
        		Direction.setText("S");
        	}
        	else if(facing == 3) 
        	{
        		if(option == 1) button[actArray1[arrayPosition]].setIcon(car_left); 
        		else if(option == 2) button[actArray2[arrayPosition]].setIcon(car_left); 
        		jBCompas.setIcon(compas_w);
        		Direction.setText("W");
        	}
        	if(option == 1) CarPos = actArray1[arrayPosition];
    		else if(option == 2) CarPos = actArray2[arrayPosition]; 
        	Square.setText(""+CarPos); // updates the block that the car is on (right side panel)
        	
        	
    	}
    }
    
    // reset button action
    
    private void reset()
    {
    	timer.stop();
    	button[CarPos].setIcon(space);
    	CarPos = 17;
    	button[CarPos].setIcon(car_right);
    	ticks = 0;
    	score=0; //reset the score
    	code=0; //reset the wall detector
    	timerStarted=0; //reset the timer
    	facing = 1; //reset the direction of the car
        wall = false; //set the wall to false
        arrayPosition = 0;
    }
    
    public static int randomNumber(int min, int max){
        int x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }
   
    private void gameOver()
    {
    	JOptionPane.showMessageDialog(null, "Game Over! Your score was: " + score);
    	score=0; //reset the score
    	code=0; //reset the wall detector
    	timerStarted=0; //reset the timer
    	ticks=0; 
    	button[CarPos].setIcon(space);
    	CarPos=17; //reset car position
    	button[CarPos].setIcon(car_right);
        arrayPosition = 0; //reset array position (for Act and Run buttons)
        facing = 1; //reset the direction of the car
        wall = false; //set the wall to false
        blocked = false; //set the blocked by obstacle to false
    }
    boolean blocked = false;
    private int score = 0;
    private int code = 0;
	boolean wall = false;
	private void move(String dir)
    {
    	if(dir == "right")
    	{
    		for(int s: movingArea)
    		{
    			if(s == CarPos+1) code = 1;
    		}
    		for(int a: objects)
    		{
    			if(a == CarPos+1 && option == 2) blocked = true;
    		}
    		for(int w: wallArray)
    		{
    			if(w == CarPos+1) wall = true;
    		}
    		if(code == 1 && !blocked)
    		{
    			button[CarPos].setIcon(space);
    			CarPos++; score++;
    			button[CarPos].setIcon(car_right);
    			code = 0;
    			Square.setText(""+CarPos);
    			Direction.setText("E");
    			jBCompas.setIcon(compas_e);
    		}
    		if(wall || blocked) gameOver();
    	}
    	if(dir == "left")
    	{
    		for(int s: movingArea)
    		{
    			if(s == CarPos-1) code = 1;
    		}
    		for(int a: objects)
    		{
    			if(a == CarPos-1 && option == 2) blocked = true;
    		}
    		for(int w: wallArray)
    		{
    			if(w == CarPos-1) wall = true;
    		}
    		if(code == 1 && !blocked)
    		{
    			button[CarPos].setIcon(space);
    			CarPos--; score++;
    			button[CarPos].setIcon(car_left);
    			code = 0;
    			Square.setText(""+CarPos);
    			Direction.setText("W");
    			jBCompas.setIcon(compas_w);
    		}
    		if(wall || blocked) gameOver();
    	}
    	if(dir == "down")
    	{
    		for(int s: movingArea)
    		{
    			if(s == CarPos+16) code = 1;
    		}
    		for(int a: objects)
    		{
    			if(a == CarPos+16 && option == 2) blocked = true;
    		}
    		for(int w: wallArray)
    		{
    			if(w == CarPos+16) wall = true;
    		}
    		if(code == 1 && !blocked)
    		{
    			button[CarPos].setIcon(space);
    			CarPos+=16; score++;
    			button[CarPos].setIcon(car_down);
    			code = 0;
    			Square.setText(""+CarPos);
    			Direction.setText("S");
    			jBCompas.setIcon(compas_s);
    		}
    		if(wall || blocked) gameOver();
    	}
    	if(dir == "up")
    	{
    		for(int s: movingArea)
    		{
    			if(s == CarPos-16) code = 1;
    		}
    		for(int a: objects)
    		{
    			if(a == CarPos-16 && option == 2) blocked = true;
    		}
    		for(int w: wallArray)
    		{
    			if(w == CarPos-16) wall = true;
    		}
    		if(code == 1 && !blocked)
    		{
    			button[CarPos].setIcon(space);
    			CarPos-=16; score++;
    			button[CarPos].setIcon(car_up);
    			code = 0;
    			Square.setText(""+CarPos);
    			Direction.setText("N");
    			jBCompas.setIcon(compas_n);
    		}
    		if(wall || blocked) gameOver();
    	}
     }
    class MoveClass implements ActionListener
    {

		public void actionPerformed(ActionEvent arg0) {
			Object b = arg0.getSource();
			if(b == jBDirection[1])
			{
				move("up");
			}
			if(b == jBDirection[3])
			{
				move("left");
			}
			if(b == jBDirection[5])
			{
				move("right");
			}
			if(b == jBDirection[7])
			{
				move("down");
			}
		}
    }
    
    //menu setup
    public void menuSetup()
    {
    	topMenuBar = new JMenuBar();
    	setJMenuBar(topMenuBar);
    	
    	//file button
    	fileMenu = new JMenu("Scenario");     
        exitItem = new JMenuItem("Exit"); 
        fileMenu.add(exitItem);           
        exitItem.addActionListener(this); 
        topMenuBar.add(fileMenu);         

        //edit button
        editMenu = new JMenu("Edit");               
        topMenuBar.add(editMenu );

        //search button
        searchMenu = new JMenu("Controls");
        topMenuBar.add(searchMenu);

        //help button
        helpMenu = new JMenu("Help"); 
        
        helpItem = new JMenuItem("Help Topics");
        helpMenu.add(helpItem);
        helpItem.addActionListener(this);
        
        aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);
        aboutItem.addActionListener(this);
        topMenuBar.add(helpMenu);
    }
}