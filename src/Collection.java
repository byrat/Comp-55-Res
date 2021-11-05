import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import acm.graphics.GImage;

public class Collection extends GraphicsPane{
	
	boolean [] FruitLog = new boolean[4];
	/*STRAWBERRY(""), 
	BANANA(""),
	MELON(""),
	PINEAPPLE(""),*/
	
	public void UpdateCollection(int x) {
		 FruitLog[x] = true;
		 
		
	}
	
	ArrayList <GImage> Fruits = new ArrayList<GImage>(); 
	Collection(){
		GImage banana = new GImage("media/fruits/banana.png" , 300 , 300);
		
		GImage melon = new GImage("media/fruits/melon.png" , 350 , 350);
			
		GImage pineapple = new GImage("media/fruits/melon.png", 400, 400);
		
		GImage strawberry = new GImage("media/fruits/strawberry.png" , 500, 500);
		
		Fruits.add(banana);
		Fruits.add(melon);
		Fruits.add(pineapple);
		Fruits.add(strawberry);
		
	}
	
	
	
	

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		
	}
	
}

