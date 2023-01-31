import java.io.BufferedReader;
import java.io.FileReader;

public class fileReader {
	public static String getJsonInfo(String filename) {
		String jsontxt = "";
		
		try {
		BufferedReader bufferedreader = new BufferedReader(new FileReader(filename));
		
		String line;
		
		while((line = bufferedreader.readLine()) != null) {
			jsontxt += line + "\n";
		}
			bufferedreader.close();
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return jsontxt;
	}


public static void main(String[] args) {
		String strJson = getJsonInfo("C:\\Users\\Bryant Tran\\git\\groupproject-avengers\\src\\data.json");
		System.out.println(strJson);
	}
	
}