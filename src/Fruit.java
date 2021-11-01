public enum Fruit {
		STRAWBERRY(""), 
		BANANA(""),
		MELON(""),
		;
		
		private final String imageFilePath;
		
		Fruit(String i) {
			this.imageFilePath = i;
		}
		
		public String getImage() {
			return imageFilePath;
		}
	}

