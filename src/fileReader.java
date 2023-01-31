import java.io.BufferedReader;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;




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
		//System.out.println(strJson);
		
		try {
			JSONParser parser = new JSONParser();
			Object object = parser.parse(strJson);
			JSONObject mainObject = (JSONObject) object;
			
			//Enemy// 
			String enemyXpos = (String) mainObject.get("enemyXpos");
			System.out.println(enemyXpos);
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}