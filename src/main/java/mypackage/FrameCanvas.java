package mypackage;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;




public class FrameCanvas extends JPanel {

	public Graphics2D g2d;
	
	private Timer timer;
	
	private Boolean inserting;
	private Boolean removing;
	private Boolean relocating;
	private Boolean ready;
	
	private int i;
	private int consumer;
	private int last;
	private static Color blue = new Color(0, 49, 83);
	private static Color red = new Color(255, 0, 0);
	private static Color green = new Color(0, 200, 0);
	private static Color black = new Color(0, 0, 0);
	private static Color white = new Color(255, 255, 255);
	private static Font font = new Font("Serif", Font.PLAIN, 32);
	private RectangleElement t = new RectangleElement(100,100,100,100,"qwerty",black,blue);
	private static RectangleElement producer = new RectangleElement(10,490,160,60,"Producator",blue,green);
	private static RectangleElement consumer1 = new RectangleElement(570,40,210,60,"Consumator #1",blue,green);
	private static RectangleElement consumer2 = new RectangleElement(570,190,210,60,"Consumator #2",blue,green);
	private static RectangleElement consumer3 = new RectangleElement(570,340,210,60,"Consumator #3",blue,green);
	private static RectangleElement consumer1a = new RectangleElement(570,100,210,80,"",blue,blue);
	private static RectangleElement consumer2a = new RectangleElement(570,250,210,80,"",blue,blue);
	private static RectangleElement consumer3a = new RectangleElement(570,400,210,80,"1",blue,blue);
	
	private static RectangleElement temp = new RectangleElement(180,490,40,60,"A",black,green);
	private static RectangleElement temp2 = new RectangleElement(280,30,40,60,"A",black,red);

	private List<RectangleElement> list;
	
	public void paint(Graphics g)
	{
		g2d=(Graphics2D)g;
		//drawRectangle(100,100,100,100,white,black,"text");

		this.drawBlankSpace();
		//t.draw(g2d);
		producer.draw(g2d);
		consumer1.draw(g2d);
		consumer2.draw(g2d);
		consumer3.draw(g2d);
		consumer1a.draw(g2d);
		consumer2a.draw(g2d);
		consumer3a.draw(g2d);
		if(inserting==true)
		{
			insert(g2d);
		}
		if(removing==true)
		{
			remove(g2d);
		}
		if(relocating==true)
		{
			relocate(g2d);
		}
		for(RectangleElement j : list)
		{
			j.draw(g2d);
		}
		//temp.draw(g2d);
		//temp2.draw(g2d);
		//t1.draw(g2d);
		//t2.draw(g2d);
		//t3.draw(g2d);
		//t4.draw(g2d);
		//t5.draw(g2d);
		//t6.draw(g2d);
		//t7.draw(g2d);
		//t8.draw(g2d);
		
		
		//t.moveRight();
		/*
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setFont(font);
	    
	    g2.setColor(blue);
	    
	    g2.fillRect(30, 60, 140, 70);
	    g2.fillRect(490, 60, 190, 70);
	    g2.fillRect(490, 210, 190, 70);
	    g2.fillRect(490, 360, 190, 70);
	    
	    g2.setColor(black);
	    
	    g2.drawString("Producer", 40, 100);
	    g2.drawString("Consumer #1", 500, 100);
	    g2.drawString("Consumer #2", 500, 250);
	    g2.drawString("Consumer #3", 500, 400);
	    */
	}
	
	private void remove(Graphics2D g2d) //consumer = 1 , 2 or 3 
	{
		temp2.draw(g2d);
		if(i<100)
		{
			temp2.moveRight();
		}
		else if(i>=100 && i<100+80+150*(consumer-1))
		{
			temp2.moveDown();
		}
		else if(i>=100+80+150*(consumer-1) && i < 100+80+150*(consumer-1)+300 )
		{
			temp2.moveRight();
		}
		else //i==70
		{
			removing=false;
			launchRelocate();
			return;
		}
		i++;
	}
	
	private void insert(Graphics2D g2d)
	{
		temp.draw(g2d);
		if(i<100)
		{
			temp.moveRight();
		}
		else if(i>=100 && i<100+460-last*65)
		{
			temp.moveUp();
		}
		else //i==70
		{
			inserting=false;
			ready=true;
			list.add(new RectangleElement(temp));
			//FrameController.mutex.unlock();
		}
		i++;
		
	}
	
	private void relocate(Graphics2D g2d)
	{
		if(i>=65)
		{
			relocating=false;
			ready=true;
			//FrameController.mutex.unlock();
		}
		else
		{
			for (RectangleElement j : list)
			{
				j.moveUp();
			}
			i++;
		}
	}
	
	public void launchRemove(int consumer)
	{
		if(ready)
		{
			//FrameController.mutex.lock();
			try{
				temp2 = new RectangleElement(list.get(0));//new RectangleElement(280,30,40,60,str,black,red);
				list.remove(0);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			removing=true;
			i=0;
			this.consumer=consumer;
			ready=false;
		}
	}
	
	public void launchInsert(String str,int last)
	{
		if(ready)
		{
			//FrameController.mutex.lock();
			ready=false;
			temp = new RectangleElement(180,490,40,60,str,black,green);
			inserting=true;
			i=0;
			this.last=last;
		}
	}
	
	private void launchRelocate()
	{
		relocating=true;
		i=0;
	}
	
	private void drawQueue()
	{
		Iterator<String> i = MainClass.x.iterator();
		int j = 0;
		String s;
		if(!i.hasNext())
			System.out.println("no");
		while(i.hasNext())
		{
			s=i.next();
			drawRectangle(40,40+j,40,40,white,black,s);
			j=j+20;
		}
	}
	
	private void drawRectangle(int x,int y,int width,int height,Color textColor,Color rectangleColor,String text)
	{
		g2d.setFont(font);
		g2d.setColor(rectangleColor);
		g2d.fillRect(x,y,width,height);
		g2d.setColor(textColor);
		g2d.drawString(text, x+10, y+40);
	}
	
	private void drawBlankSpace()
	{
		g2d.setColor(white);
		g2d.fillRect(0,0,800,600);
	}
	
	private void tick()
	{
		this.repaint();
	}
	
	public FrameCanvas() {
		list = new ArrayList<RectangleElement>();
		inserting = false;
		removing = false;
		relocating = false;
		ready=true;
		ActionListener timerHandler = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		};
		timer = new Timer(5,timerHandler);
		timer.start(); 
	}
	
	public Boolean getStatus()
	{
		return !ready;
	}
	
	
/*
	public FrameCanvas(LayoutManager arg0) {
		super(arg0);

	}

	public FrameCanvas(boolean arg0) {
		super(arg0);

	}

	public FrameCanvas(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);

	}
*/
}
