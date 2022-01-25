
public class WaterStation {
	private int samples;
	private double totalConcentration, max, min;

	public WaterStation() {
		samples = 0;
		totalConcentration = 0;
		max = 0;
		min = 0;
	}
	public void sampleLead (double concentration) {
		if (concentration > max || max == 0) {
			max = concentration;
		}
		
		if(concentration <= 0.0) {
			System.out.println("Not possible");}
		else {
			totalConcentration += concentration;
			samples += 1;
			if (concentration < min || min == 0) {
				min = concentration;
				}
			}	
		}
		
	public int numberSamples () {
		return samples;
	}
	public double getMaximum(){
			return max;
	}
	
	public double getMinimum() {
			return min;}

	public double getAverage() {
		double averageConcentration = (totalConcentration / samples);
		return (averageConcentration);
	}
}
