package client;

public class mainThread 
{
	static C c=new C();
	//flag用来标志子线程执行结束
	static boolean flag=false;
	
	public static void main(String []arg)
	{	
		Thread mythread = new MyThread(new ClientMain());
		mythread.start();
		
		//等待子线程执行结束
		while(!flag);
		System.out.println("子线程执行之后value的值是："+c.getvalue());	
	}	
 
	public static void callback()
		{
			System.out.println("子线程执行结束");	
			flag=true;
		}
}
 
 
class C
{
	private int value=0;
	public int getvalue()
	{
		return value;
	}
	public void setvalue(int v)
	{
		this.value=v;
	}
}
 
 
 
class MyThread extends Thread
{
	public MyThread(ClientMain clientMain)
	{
		this.clientMain=clientMain;
	}
	private ClientMain clientMain;
	@Override
	public void run() 
	{
		String[] arg = null;
		ClientMain.main(arg);			
		mainThread.callback();//很像C#的委托和事件
	}
	}
