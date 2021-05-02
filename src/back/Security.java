package back;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.MessageDigest;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Security {
	
	private static final String secret = "^&*#!QITRg786bQ%TR768q5t&*R6yo3q980&R)(@!78v(Q_)R&*FU()qwle78RU()i3uqw_()P:R*#;90e;yfshdyer8w9ew4r89lb67ew789obt6rtaw876boer4g5ae4rtw78bo693qr7f6ev7ws8i6f786eqv896rf8e9q7()*^FEO*W%^O&*F%^78v567V%78QW%VOF^*ewf78o9feovw768seWSFV^&O(*wef69o&V*wef(O*^V79oe8w&W^ORfu";
	private static final byte[] salt = secret.getBytes(StandardCharsets.UTF_8);
	private static final byte[] salt2 = new String("l3G6b0On").getBytes(StandardCharsets.UTF_8);
	private static final int iterationCount = 11878;
	private static final int keyLength = 128;
	
	public static final String hash(String target) {
		
		return get_SHA_512_SecurePassword(target);
		
	}
	
	public static void encryptFile(InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(secret, Cipher.ENCRYPT_MODE, is, os);
	}

	public static void decryptFile(InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(secret, Cipher.DECRYPT_MODE, is, os);
	}

	private static void encryptOrDecrypt(String key, int mode, InputStream is, OutputStream os) throws Throwable {

		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE

		if (mode == Cipher.ENCRYPT_MODE) {
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			doCopy(cis, os);
		} else if (mode == Cipher.DECRYPT_MODE) {
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(is, cos);
		}
	}

	public static void doCopy(InputStream is, OutputStream os) throws IOException {
		byte[] bytes = new byte[64];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			os.write(bytes, 0, numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}

	public static final String encryptText(String target) throws Exception {
		
		SecretKeySpec key = createSecretKey(secret.toCharArray(), salt2, iterationCount, keyLength);
		
		Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		pbeCipher.init(Cipher.ENCRYPT_MODE, key);
		AlgorithmParameters parameters = pbeCipher.getParameters();
		IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
		byte[] cryptoText = pbeCipher.doFinal(target.getBytes("UTF-8"));
		byte[] iv = ivParameterSpec.getIV();
		return base64Encode(iv) + ":" + base64Encode(cryptoText);
		
	}
	
	public static final String decryptText(String target) throws Exception {
		
		SecretKeySpec key = createSecretKey(secret.toCharArray(), salt2, iterationCount, keyLength);
		
		String iv = target.split(":")[0];
		String property = target.split(":")[1];
		Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
		return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
		
	}
	
	private static String get_SHA_512_SecurePassword(String target) {
		
		String generatedPassword = null;
		try {
			
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			byte[] bytes = md.digest(target.getBytes());
			StringBuilder sb = new StringBuilder();
			for(int x=0;x<bytes.length;x++) {
				sb.append(Integer.toString((bytes[x] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
			
		}catch(Exception ex) {
			
			ex.printStackTrace();
			
		}
		
		return generatedPassword;
		
	}
	
	private static final SecretKeySpec createSecretKey(char[] secret, byte[] salt2, int iterationCount, int keyLength) throws Exception {
		
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
		PBEKeySpec keySpec = new PBEKeySpec(secret, salt2, iterationCount, keyLength);
		SecretKey keyTmp = keyFactory.generateSecret(keySpec);
		return new SecretKeySpec(keyTmp.getEncoded(), "AES");
		
	}
	
	private static String base64Encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	private static byte[] base64Decode(String property) {
		return Base64.getDecoder().decode(property);
	}
	
	
}
