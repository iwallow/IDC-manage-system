package jcy.bishe.tools;

public class MyThread extends Thread {
	public int time1=10000;
	public void run(){
		try {
			sleep(time1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
