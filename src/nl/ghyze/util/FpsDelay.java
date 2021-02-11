package nl.ghyze.util;


public class FpsDelay {

	private long timePerFrame;
	
	private long lastTime = System.nanoTime();
	
	public FpsDelay(int maxFPS){
		this.timePerFrame = 1000000000 / maxFPS;
	}
	
	public void delay() {
		while (System.nanoTime() <= (lastTime + timePerFrame)){
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		lastTime = System.nanoTime();
	}

}
