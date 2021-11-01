import java.util.HashSet;
import java.util.Set;

public class Collection {
	private Set<Fruit> set;

	public Collection () {
		set = new HashSet <Fruit>();
		
	}
	
	public void addFruit(Fruit fruit) {
		set.add(fruit);
	}
	
	public void clearFruit(Fruit fruit) {
		set.clear();
	}
	
}

