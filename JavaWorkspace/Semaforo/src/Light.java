
public class Light {
	int state;
	
	public Light() {
		state = 0;
		
	}
	
	public void setState(int val) {
		state = val;
		
		if (val == 0) {
			System.out.println("Green -> " + val);
			
		}
		else if (val == 1) {
			System.out.println("Yellow -> " + val);
			
		}
		else if (val == 2) {
			System.out.println("Red -> " + val);
			
		}
		else {
			System.out.println(val + " is invalid ");
		}
	}
}