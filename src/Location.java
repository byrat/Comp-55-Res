
public class Location {
	private int xAxis;
	private int yAxis;
	
	
	
public Location (int xLocation, int yLocation) {
	this.xAxis = xLocation;
	this.yAxis = yLocation;
	
	
}

public int getXAxis() {
	return xAxis;
}

public int getYAxis() {
	return yAxis;
}

public void setXAxis(int xAxis) {
	this.xAxis = xAxis;
}

public void setYAxis(int yAxis) {
	this.yAxis = yAxis;
}



public static void main (String [] args) {
	Location one = new Location(3, 4);
	Location two = new Location (1,6);
	two.setXAxis(two.getXAxis()+1);
	two.setYAxis(two.getYAxis()-1);
	
	System.out.println("First Test: X-axis:" + one.getXAxis() + ",Y-Axis:" + one.getYAxis());
	
	System.out.println("Second Test: X-axis:" + two.getXAxis() + ",Y-Axis:" + two.getYAxis());

	 
}



}
