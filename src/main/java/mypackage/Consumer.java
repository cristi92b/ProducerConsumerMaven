package mypackage;

import java.security.Timestamp;
import java.lang.*;

public class Consumer extends Thread {

	private String str;
	private int waittime;
	private int threadNo;
	private Boolean timed;
	
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
			//System.out.println("epsilon");
			synchronized(MainClass.x)
			{
				//System.out.println("alpha");
				if(!MainClass.x.isEmpty())
				{
					try {
						str=MainClass.x.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Message consumed: " + str + " by consumer ID=" + this.getId() + " (Waittime=" + this.waittime + ")");
					synchronized(FrameController.log)
					{
						try{
							FrameController.log.add(new FrameTask(threadNo));
						}
						catch(Exception e)
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
	
	public Consumer(int waittime) {
		if(waittime<4)
		{
			this.threadNo=waittime;
			this.waittime=0;
			this.timed=false;
		}
		else{
			this.waittime=waittime;
			this.timed=true;
		}
		printMessage();
	}
	
	public Consumer() {
		this.threadNo=1;
		this.waittime=0;
		this.timed=false;
		printMessage();
	}

	public Consumer(Runnable target) {
		super(target);
		printMessage();
	}

	public Consumer(String name) {
		super(name);
		printMessage();
	}

	public Consumer(ThreadGroup group, Runnable target) {
		super(group, target);
		printMessage();
	}

	public Consumer(ThreadGroup group, String name) {
		super(group, name);
		printMessage();
	}

	public Consumer(Runnable target, String name) {
		super(target, name);
		printMessage();
	}

	public Consumer(ThreadGroup group, Runnable target, String name) {
		super(group, target, name);
		printMessage();
	}

	public Consumer(ThreadGroup group, Runnable target, String name,
			long stackSize) {
		super(group, target, name, stackSize);
		printMessage();
	}
	
	private void printMessage()
	{
		System.out.println("Consumer thread (ID=" + this.getId() + ") ready to start");
	}

}
