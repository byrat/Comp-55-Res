
public enum Direction {
	EAST, WEST, SOUTH, NORTH;
	
	public String toString() {
		switch(this) {
			case EAST: return "east";
			case WEST: return "west";
			case SOUTH: return "south";
			case NORTH: return "north";
		}
		return "n/a";
	}
}
