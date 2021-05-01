package back;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProfileManager {

	private static final File profiles = new File("profiles.txt");
	
	public static final void createProfilesFile() {
		
		try {
		
		if(!profiles.exists()) 
			profiles.createNewFile();
		
		}catch(Exception ex) {
			
			ex.printStackTrace();
			
		}
		
	}
	
	public static final boolean loadUser(String username, String password) {
		
		try {
		
		FileReader fr = new FileReader(profiles);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line=br.readLine())!=null) {
			
			String[] details = line.split("\\s");
			
			if(username.equals(details[0]) && password.equals(details[1])) {
				
				File personal = new File("vault/" + username + ".profile");
				
				if(personal.exists()) {
					
					Security.decrypt(new FileInputStream("vault/"+username+".profile"), new FileOutputStream("C:/Users/DUBLEF~1/AppData/Local/Temp/profile.txt"));
					
				}else {
					
					new File("C:/Users/DUBLEF~1/AppData/Local/Temp/profile.txt").createNewFile();
					
				}
				
				br.close();
				return true;
				
			}
			
		}
		br.close();
		
		}catch(Exception ex) {
			
			ex.printStackTrace();
			
			
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		return true;
		
	}
	
	public static void addUser(String Username, String Password) {
		
		try {
			
			FileWriter fw = new FileWriter(profiles, true);
			fw.append(Security.hash(Username) + Security.hash(Password) + "\n");
			fw.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
}
