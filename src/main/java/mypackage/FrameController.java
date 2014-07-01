package mypackage;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FrameController extends JFrame implements Runnable {

	private FrameCanvas panel;
	public static BlockingQueue<FrameTask> log;
	private FrameTask y;
	private int lastIndex;
	public static Lock mutex = new ReentrantLock(true);
	public static Lock mutex2 = new ReentrantLock(true);
	
	public FrameController() throws HeadlessException {
		log = new ArrayBlockingQueue<FrameTask>(40);
		panel=new FrameCanvas();
		lastIndex=0;
		this.getContentPane().add(panel);
		this.setSize(800,600);
	}

	public FrameController(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public FrameController(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	public FrameController(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		this.setVisible(true);
		while(true)
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			synchronized(this.log)
			{
				if(!log.isEmpty())
				{
					//System.out.println("Take");
					try {
						y=log.take();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					//System.out.println("continue");
					continue;
				}
			//mutex2.unlock();
			}
			//lock mutex
			//mutex.lock();
			while(panel.getStatus())
			{
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(!y.getType()) //insert
			{
				panel.launchInsert(y.getString(),lastIndex);
				lastIndex++;
			}
			else //remove
			{
				panel.launchRemove(y.getConsumer());
				lastIndex--;
			}
			//mutex.unlock();
			//unlock mutex
		}
		
		/*
		panel.launchInsert("F",0);
		while(panel.getStatus());
		panel.launchInsert("Q",1);
		while(panel.getStatus());
		panel.launchRemove(1);
		while(panel.getStatus());
		panel.launchInsert("A",1);
		*/

	}

}
