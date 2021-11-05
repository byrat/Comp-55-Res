
public enum Fruit {
		STRAWBERRY(""), 
		BANANA(""),
		MELON(""),
		PINEAPPLE(""),
		;
		
		private final String imageFilePath;
		
		Fruit(String i) {
			this.imageFilePath = i;
		}
		
		public String getImage() {
			return imageFilePath;
		}
		public void onCollison(){
			
		}
	}



