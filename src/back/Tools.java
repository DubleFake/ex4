package back;

import java.security.SecureRandom;

public class Tools {

	public static final String stringBuilder(char[] s) {
		
		StringBuilder sb = new StringBuilder();
		for(int x = 0;x<s.length;x++)
			sb.append(s[x]);
		return sb.toString();
	}
	
	public static final String generateRandomPassword() {
		
		 final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&*()_+/*-,<.>?;:'[}]{";
		 
		 SecureRandom random = new SecureRandom();
	     StringBuilder sb = new StringBuilder();
	     
	        for (int i = 0; i < 32; i++)
	        {
	            int randomIndex = random.nextInt(chars.length());
	            sb.append(chars.charAt(randomIndex));
	        }
	        
	        return sb.toString();
		
	}
	
	public static final String hidePassword(String password) {
		
		StringBuilder hidden = new StringBuilder();
		for(int x = 0;x<password.length();x++)
			hidden.append("*");
		return hidden.toString();
		
	}
	
}
