package back;

public class Tools {

	public static final String stringBuilder(char[] s) {
		
		StringBuilder sb = new StringBuilder();
		for(int x = 0;x<s.length;x++)
			sb.append(s[x]);
		return sb.toString();
	}
	
}
