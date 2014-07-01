package mypackage;

public class FrameTask {

	private Boolean taskType; // false - insert, true - remove
	private String str; // pentru insert;
	//private int last; // pentru insert (0..7)
	private int consumer; //1,2 sau 3 - pentru remove
	
	public FrameTask(int consumer) {
		taskType=true;
		this.consumer=consumer;
		this.str="4";
		//this.last=0;
	}
	public FrameTask(String str) {
		taskType=false;
		//this.last=last;
		this.str=str;
		this.consumer=0;
	}
	
	public Boolean getType()
	{
		return taskType;
	}
	
	public String getString()
	{
		return str;
	}
	/*
	public int getLast()
	{
		return last;
	}
	*/
	public int getConsumer()
	{
		return consumer;
	}
	
	public void execute()
	{
		
	}

}
