package back;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class VaultManager {

	private static String username;
	private static final File personal = new File("C:/Users/DUBLEF~1/AppData/Local/Temp/profile.txt");
	
	public static void addRecord(String title, String username, String password, String url) {
		
		try {
			
			FileWriter fw = new FileWriter(personal, true);
			fw.append(title + " " + username + " " + Security.encryptText(password) + " " + url + "\n");
			fw.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static List<String> loadProfile(){
		
		List<String> result = new ArrayList<>();
		
		try {
		
		FileReader fr = new FileReader(personal);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line=br.readLine())!=null) {
			
			String[] details = line.split("\\s");
			result.add(details[0] + "                    " + details[1] + "                    " + Security.decryptText(details[2]) + "                    " + details[3]);
		}
		br.close();
		}catch(Exception ex) {
			
			ex.printStackTrace();
			
		}
		
		return result;
		
	}
	public static void saveProfile() {
		
		try {
			Security.encryptFile(new FileInputStream("C:/Users/DUBLEF~1/AppData/Local/Temp/profile.txt"), new FileOutputStream("vault/"+Security.hash(username)+".profile"));
			//personal.delete();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (Throwable e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void setUsername(String s) {
		
		username = s;
		
	}
	
}
