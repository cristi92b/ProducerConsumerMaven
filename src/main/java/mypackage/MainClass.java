package mypackage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;



public class MainClass {

	public static BlockingQueue<String> x;
	public static JFrame f;
	public static FrameCanvas y;
	public static Runnable xc;
	public static Thread a,b,c,d;

	public static void main(String[] args) {
		x = new ArrayBlockingQueue<String>(8);
		xc = new FrameController();
		a = new Producer();
		b = new Consumer(1);
		c = new Consumer(2);
		d = new Consumer(3);
		a.start();
		b.start();
		c.start();
		d.start();
		xc.run();
		
		
	}
	
	public static void shutDown()
	{
		a.interrupt();
		b.interrupt();
		c.interrupt();
		d.interrupt();
	}

}
