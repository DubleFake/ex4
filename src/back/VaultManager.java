package back;

import java.io.File;
import java.io.FileWriter;

public class VaultManager {

	public static void addRecord(String title, String username, String password, String url) {
		
		try {
			
			FileWriter fw = new FileWriter(new File("C:/Users/DUBLEF~1/AppData/Local/Temp/profile.txt"), true);
			fw.append(title + " " + Security.encryptText(username) + " " + Security.encryptText(password) + " " + url);
			fw.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
}
