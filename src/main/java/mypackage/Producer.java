package mypackage;

import java.util.Random;

import javax.swing.JFrame;


public class Producer extends Thread {
	
	public static final String[]  mesaje = { "A","B","C","D","E","F","G","H","I","J","K","L" }; 
	private int waittime;
	private Boolean timed;
	public int number;
	
	public Random rand = new Random();
	
	public void run()
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true)
		{
			//System.out.println("gamma");
			synchronized(MainClass.x)
			{
				//System.out.println("beta");
				if(MainClass.x.remainingCapacity()!=0)
				{
						number = rand.nextInt(10);
						MainClass.x.add(mesaje[number]);
						System.out.println("Message produced: " + mesaje[number] + " by producer ID=" + this.getId());
						synchronized(FrameController.log)
						{
							try{
								FrameController.log.add(new FrameTask(mesaje[number]));
							}
							catch (Exception e)
							{
								System.out.println("Queue Full");
								//this.interrupt();
								this.stop();
							}
						}
						
				}
			}
			if(timed)
			{
				try {
					Thread.sleep(waittime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	
	public Producer() {
		this.waittime = 0;
		this.timed=false;
		printMessage();
	}
	
	public Producer(int waittime) {
		this.waittime = waittime;
		this.timed=true;
		printMessage();
	}

	public Producer(Runnable arg0) {
		super(arg0);
		printMessage();
	}

	public Producer(String arg0) {
		super(arg0);
		printMessage();
	}

	public Producer(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		printMessage();
	}

	public Producer(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		printMessage();
	}

	public Producer(Runnable arg0, String arg1) {
		super(arg0, arg1);
		printMessage();
	}

	public Producer(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		printMessage();
	}

	public Producer(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		printMessage();
	}
	
	private void printMessage()
	{
		System.out.println("Producer thread (ID=" + this.getId() + ") ready to start");
	}

}
