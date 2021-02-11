package nl.ghyze.util;

public class FpsCounter {

	long[] times;
	int index = 0;
	
	public FpsCounter(int sampleLength){
		times = new long[sampleLength];
		long nano = System.nanoTime()-1000000000;
		for (int i = 0; i < times.length; i++){
			times[i] = nano;
		}
	}
	
	public void tick(){
		times[index] = System.nanoTime();
		index ++;
		if (index >= times.length){
			index = 0;
		}
	}
	
	public long getFps() {
		int endIndex = index - 1;
		if (endIndex == -1) {
			endIndex = times.length - 1;
		}
		long fps = ((times.length-1) * 1000000000L)
				/ (times[endIndex] - times[index]);
		return fps;
	}
}
