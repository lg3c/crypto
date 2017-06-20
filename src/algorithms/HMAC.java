package src.algorithms;

import java.security.Security;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class HMAC {

	public String key;
	public String message;
	public String mac;
	
	private String algorithm;	
	
	public HMAC() {
		Security.addProvider(new BouncyCastleProvider());
		setMode(0);
	}
	
	/**
	 * Sets the HMAC hash function to use. 
	 * @param _mode 0 for SHA-256, 1 for SHA1.
	 */
	public void setMode(int _mode) {
		switch (_mode) {
		case 0:			
			algorithm = "Hmac-SHA256";
			break;
		case 1:			
			algorithm = "Hmac-SHA1";
			break;
		}
	}

	/**
	 * Generates a key for the HMAC.
	 * @throws Exception
	 */
	public void generateKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm, "BC");
		key = Common.byteArrayToHexString(keyGenerator.generateKey().getEncoded());
	}
	
	/**
	 * Computes the HMAC of a message.
	 * @throws Exception
	 */
	public void compute() throws Exception {		
		SecretKeySpec hmacKey = new SecretKeySpec(Common.hexStringToByteArray(key), algorithm);		
		Mac hmac = Mac.getInstance(algorithm, "BC");
		hmac.init(hmacKey);
		mac = Common.byteArrayToHexString(hmac.doFinal(message.getBytes()));
	}	
}
