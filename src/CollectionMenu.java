import java.awt.Color;

import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class CollectionMenu extends GraphicsPane {
	
	private MainApplication program;
	
	boolean [] log = new boolean [4];
	
	//Initializing some variables for the fruits and their descriptions.
	private GImage CurrText = null;
	private GImage LOCKED;
	
	private GImage BANANA;
	private GImage BANANA_D;
	
	private GImage MELON;
	private GImage MELON_D;
	
	private GImage PINEAPPLE;
	private GImage PINEAPPLE_D;
	
	private GImage STRAWBERRY;
	private GImage STRAWBERRY_D;
	
	
	private GImage COLLECT;
	private GImage BACK;
	
	private int OPTION;
	
	public CollectionMenu (MainApplication app) {
		super();
		program = app;
		COLLECT = new GImage("media/fruits/collect.png", 500 , 100);
		COLLECT.sendToBack();
		
		//Banana as a button so when users press on it's description pops up.
		BANANA = new GImage ("media/fruits/banana_b.png" , 350 ,190);
		BANANA_D = new GImage("media/fruits/banana_d.png" , 350 , 500);
		
		
		//Same as banana
		MELON = new GImage("media/fruits/watermelon_b.png" , 1000 , 220);
		MELON_D = new GImage("media/fruits/melon_d.png", 440 , 500);
		
		//Same as banana
		PINEAPPLE = new GImage("media/fruits/pineapple_b.png" , 650 , 200);
		PINEAPPLE_D = new GImage("media/fruits/pineapple_d.png" , 340 , 500);
		
		
		//Same as banana
		STRAWBERRY = new GImage("media/fruits/strawberry_b.png" , 10 , 150);
		STRAWBERRY_D = new GImage("media/fruits/strawberry_d.png" , 340 , 500);
		
		//Image for the locked fruits
		LOCKED = new GImage ("media/fruits/Locked.gif", 300 ,300);
		LOCKED.setVisible(false);
		
		
		//Image for the background
		BACK = new GImage ("media/MenuScreen/BACKGROUND.jpg");
		BACK.sendToBack();
		BACK.setVisible(true);
		
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(BACK);
		program.add(COLLECT);
		program.add(BANANA);
		program.add(MELON);
		program.add(PINEAPPLE);
		program.add(STRAWBERRY);
		program.add(LOCKED);
		log[0] = true;
		
		
		
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(BACK);
		program.remove(COLLECT);
		program.remove(BANANA);
		program.remove(MELON);
		program.remove(PINEAPPLE);
		program.remove(STRAWBERRY);
		program.remove(LOCKED);
		
		
		
		if(CurrText.equals(null)) {
			program.remove(CurrText);
		}
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(program.getElementAt(e.getX(), e.getY()) == null){
			return;
		}
		GObject obj = program.getElementAt(e.getX(), e.getY());
//		if (obj == rect) {
//			program.switchToSome();
//		}
		if(obj == null) {
			return;
		}
		if(CurrText == null) {
			if(obj.equals(BANANA)) {
				CurrText = BANANA_D;
				program.add(CurrText);
				return;
			}
			else if (obj.equals(MELON)) {
				CurrText = MELON_D;
				program.add(CurrText);
				return;
			}
			else if (obj.equals(PINEAPPLE)) {
				CurrText = PINEAPPLE_D;
				program.add(CurrText);
				return;
			}
			else if (obj.equals(STRAWBERRY))
				CurrText = STRAWBERRY_D;
				program.add(CurrText);
				return;
		}
		if (obj.equals(BANANA)) {
			if(log[0]) {
				if(CurrText.equals(LOCKED)) {
					program.remove(CurrText);
					CurrText = null;
					return;
				}
				else {
					program.remove(CurrText);
					program.add(LOCKED);
					CurrText =  LOCKED;
					return;
				}
			}
			if(CurrText.equals(BANANA_D)) {
				program.remove(BANANA_D);
				CurrText = null;
			}
			else {
				program.remove(CurrText);
				program.add(BANANA_D);
				CurrText = BANANA_D;
			}
		}
		if (obj.equals(MELON)) {
			if(log[0]) {
				if(CurrText.equals(LOCKED)) {
					program.remove(CurrText);
					CurrText = null;
					return;
				}
				else {
					program.remove(CurrText);
					program.add(LOCKED);
					CurrText =  LOCKED;
					return;
				}
			}
			if(CurrText.equals(MELON_D)) {
				program.remove(MELON_D);
				CurrText = null;
			}
			else {
				program.remove(CurrText);
				program.add(MELON_D);
				CurrText = MELON_D;
			}
	}

		
		if (obj.equals(PINEAPPLE)) {
			if(log[0]) {
				if(CurrText.equals(LOCKED)) {
					program.remove(CurrText);
					CurrText = null;
					return;
				}
				else {
					program.remove(CurrText);
					program.add(LOCKED);
					CurrText =  LOCKED;
					return;
				}
			}
			if(CurrText.equals(PINEAPPLE_D)) {
				program.remove(PINEAPPLE_D);
				CurrText = null;
			}
			else {
				program.remove(CurrText);
				program.add(PINEAPPLE_D);
				CurrText = PINEAPPLE_D;
			}
		}
		
		
	
		if (obj.equals(STRAWBERRY)) {
			if(log[0]) {
				if(CurrText.equals(LOCKED)) {
					program.remove(CurrText);
					CurrText = null;
					return;
				}
				else {
					program.remove(CurrText);
					program.add(LOCKED);
					CurrText =  LOCKED;
					return;
				}
			}
			if(CurrText.equals(STRAWBERRY_D)) {
				program.remove(STRAWBERRY_D);
				CurrText = null;
			}
			else {
				program.remove(CurrText);
				program.add(STRAWBERRY_D);
				CurrText = STRAWBERRY_D;
			}
		}
		
		
		
	}
	public void setOption(int option) {
		this.OPTION = option;
	}
	
	public int getOption() {
		return OPTION;
	}
	

}
