package src.algorithms;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RSA {
	
	final static int keySize = 512;
	
	public String publicKey;
	public String privateKey;	
	public String message;
	public String encrypted;
	
	/**
	 * Constructor.
	 */
	public RSA () {
		Security.addProvider(new BouncyCastleProvider());
	}
	
	/**
	 * Generates the RSA keys (public and private).
	 * @throws Exception
	 */
	public void generateKeys() throws Exception {						
		// generate RSA public/private key pair
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA", "BC");
		keyPairGenerator.initialize(keySize);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();			
		
		// save keys' hex values to class strings 
		publicKey = Common.byteArrayToHexString(keyPair.getPublic().getEncoded());			
		privateKey = Common.byteArrayToHexString(keyPair.getPrivate().getEncoded());					
	}
	
	/**
	 * Encrypts the message using the RSA public key.
	 * @throws Exception
	 */
	public void encrypt() throws Exception {			    		    		    
    	// generate public key from hex string
		PublicKey pubKey = KeyFactory.
    			getInstance("RSA", "BC").
    			generatePublic(
    					new X509EncodedKeySpec( // standard format for public key
    							Common.hexStringToByteArray(publicKey)
    							)
    					);    	
		
		// encrypt plain text message using public key
    	Cipher cipher = Cipher.getInstance("RSA", "BC");
    	cipher.init(Cipher.ENCRYPT_MODE, pubKey);
    	byte[] encryptedBytes = cipher.doFinal(message.getBytes());
    	
    	encrypted = Common.byteArrayToHexString(encryptedBytes);		    			
	}
	
	/**
	 * Decrypts the message using RSA private key.
	 * @throws Exception
	 */
	public void decrypt() throws Exception {		
		// generate private key from hex string	    		    	
    	PrivateKey privKey = KeyFactory.
    			getInstance("RSA", "BC").
    			generatePrivate(
    					new PKCS8EncodedKeySpec( // standard format for private key
    							Common.hexStringToByteArray(privateKey)
    							)
    					);
    	
    	// decrypt from encrypted message using private key
    	Cipher cipher = Cipher.getInstance("RSA", "BC");
    	cipher.init(Cipher.DECRYPT_MODE, privKey);
    	byte[] plainBytes = cipher.doFinal(Common.hexStringToByteArray(encrypted));
    	
    	message = new String(plainBytes);		
	}
}
