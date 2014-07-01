package mypackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class RectangleElement {
	public static final Font font = new Font("Serif", Font.PLAIN, 32);
	private int x;
	private int y;
	private int width;
	private int height;
	private String str;
	private Color textColor;
	private Color rectangleColor;
	//private Graphics2D g2d;


	
	public RectangleElement(int x,int y,int width,int height,String str,Color textColor,Color rectangleColor) { //,Graphics2D g2d
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.str=str;
		this.textColor=textColor;
		this.rectangleColor=rectangleColor;
		//this.g2d=g2d;
	}
	
	public RectangleElement(RectangleElement rectangle) { //,Graphics2D g2d
		this.x=rectangle.x;
		this.y=rectangle.y;
		this.width=rectangle.width;
		this.height=rectangle.height;
		this.str=rectangle.str;
		this.textColor=rectangle.textColor;
		this.rectangleColor=rectangle.rectangleColor;
		//this.g2d=g2d;
	}
	
	public void moveUp()
	{
		y--;
	}
	
	public void moveDown()
	{
		y++;
	}
	
	public void moveRight()
	{
		x++;
	}
	
	public void moveLeft()
	{
		x--;
	}
	
	public void moveUp(int val)
	{
		y-=val;
	}
	
	public void moveDown(int val)
	{
		y+=val;
	}
	
	public void moveRight(int val)
	{
		x+=val;
	}
	
	public void moveLeft(int val)
	{
		x-=val;
	}
	
	public void draw(Graphics2D g2d)
	{
		g2d.setFont(font);
		g2d.setColor(rectangleColor);
		g2d.fillRect(x,y,width,height);
		g2d.setColor(textColor);
		g2d.drawString(str, x+10, y+40);
	}

}
