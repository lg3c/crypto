package src.algorithms;

import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.KeyGenerator;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AES
{
	public String plainText;
	public String cipherText;
	public String keyStr;
	public String ivStr;		
	
	private static final int keySize = 128;
	private static final int blockSize = 16;
	private String modeStr;	
	
	/**
	 * Constructor.
	 */
	public AES () {
		Security.addProvider(new BouncyCastleProvider());
		setMode(0);				
	}
	
	/**
	 * Sets the block cipher mode to use.
	 * @param _mode 0 for ECB, 1 for CBC, 2 for CTR.
	 */
	public void setMode(int _mode) {
		switch (_mode) {
		case 0:	// ECB			
			modeStr = "ECB";
			break;
		case 1:	// CBC			
			modeStr = "CBC";
			break;
		case 2:	// CTR
			modeStr = "CTR";
			break;
		}
	}
	
	/**
	 * Generates initialization vector (used in CBC and CTR modes).
	 * @throws Exception
	 */
	public void generateIv () throws Exception {
		SecureRandom randomSecureRandom = SecureRandom.getInstance("SHA1PRNG");
		byte[] iv = new byte[blockSize];
		randomSecureRandom.nextBytes(iv);
		ivStr = Common.byteArrayToHexString(iv);
	}
	
	/**
	 * Generates a 128 bit symmetric key.
	 * @throws Exception
	 */
	public void generateKey () throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "BC");
		keyGenerator.init(keySize);
		keyStr = Common.byteArrayToHexString(keyGenerator.generateKey().getEncoded());		
	}
	
	/**
	 * Encrypts the plain text.
	 * @throws Exception
	 */
	public void encrypt() throws Exception{		
		byte[] cipherBytes = null;
        switch (modeStr){
        case "ECB":
        	cipherBytes = AesEcb.encrypt(Common.hexStringToByteArray(plainText), 
        			Common.hexStringToByteArray(keyStr),
    				false);    		
        	break;
        case "CBC":
        	cipherBytes = AesCbc.encrypt(Common.hexStringToByteArray(plainText), 
        			Common.hexStringToByteArray(keyStr),
        			Common.hexStringToByteArray(ivStr),
    				false);   
        	break;
        case "CTR":
        	cipherBytes = AesCtr.encrypt(Common.hexStringToByteArray(plainText), 
        			Common.hexStringToByteArray(keyStr),
        			Common.hexStringToByteArray(ivStr),
    				false);  
        	break;
        }   
        
        cipherText = Common.byteArrayToHexString(cipherBytes);
	}
	
	/**
	 * Decrypts the cipher text.
	 * @throws Exception
	 */
	public void decrypt() throws Exception {
		byte[] plain = null;
        
        switch (modeStr){
        case "ECB":
        	plain = AesEcb.decrypt(Common.hexStringToByteArray(cipherText), 
    				Common.hexStringToByteArray(keyStr), 
    				false);
        	break;
        case "CBC":
        	plain = AesCbc.decrypt(Common.hexStringToByteArray(cipherText), 
    				Common.hexStringToByteArray(keyStr), 
    				Common.hexStringToByteArray(ivStr),
    				false);
        	break;
        case "CTR": // CTR
        	plain = AesCtr.decrypt(Common.hexStringToByteArray(cipherText), 
    				Common.hexStringToByteArray(keyStr), 
    				Common.hexStringToByteArray(ivStr),
    				false);
        	break;
        }
                       
		plainText = Common.byteArrayToHexString(plain);
	}	
}
