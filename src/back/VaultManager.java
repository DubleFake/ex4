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
			Security.encryptFile(new FileInputStream(personal), new FileOutputStream("vault/"+username+".profile"));
			personal.delete();
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (Throwable e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static void setUsername(String s) {
		
		username = s;
		
	}
	
	public static void editEntry(String entry, String changed) {
		
		try {
		
		List<String> result = new ArrayList<>();
		FileReader fr = new FileReader(personal);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line=br.readLine())!=null) {
			
			String []details = line.split("\\s+");
			String record = details[0] + " " + details[1] + " " +Tools.hidePassword(Security.decryptText(details[2])) + " " + details[3];
			details = entry.split("\\s+");
			String target = details[0] + " " + details[1] + " " + details[2] + " " + details[3];
			
			if(record.equals(target)) {
				
				String[] newData = changed.split("\\s+");
				result.add(newData[0] + " " + newData[1] + " " + Security.encryptText(newData[2]) + " " + newData[3] + "\n");
				
			}else 
				result.add(line + "\n");
			
		}
		br.close();
		
		FileWriter fw = new FileWriter(personal);
		personal.delete();
		personal.createNewFile();
		for(String s: result) {
			
			fw.append(s);
			
		}
		
		fw.close();
		
		}catch(Exception ex) {
			
			ex.printStackTrace();
			
		}
		
	}
	
	public static void deleteEntry(String entry) {
		
		try {
			
			List<String> result = new ArrayList<>();
			FileReader fr = new FileReader(personal);
			BufferedReader br = new BufferedReader(fr);
			String line;

			String []s = entry.split("\\s+");
			String target = s[0] + " " + s[1] + " " + s[2] + " " + s[3];
			while((line=br.readLine())!=null) {
				
				s = line.split("\\s+");
				String record = s[0] + " " + s[1] + " " +Tools.hidePassword(Security.decryptText(s[2])) + " " + s[3];

				
				if(!record.equals(target))
					result.add(s[0] + " " + s[1] + " " + s[2] +" "+ s[3]+ "\n");
			}
			br.close();
			
			FileWriter fw = new FileWriter(personal);
			personal.delete();
			personal.createNewFile();
			for(String p: result) {
				
				fw.append(p);
				
			}
			
			fw.close();
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
			
		}
		
	}
	
}
