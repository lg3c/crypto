package src.algorithms;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class DSA {
	
	final static int keySize = 1024;
	
	public String publicKey;
	public String privateKey;		
	public String message;
	public String digest;	
	public String signature;	
	
	private String algorithm;
	private byte[] digestBytes;
	
	/**
	 * Constructor.
	 */
	public DSA () {
		Security.addProvider(new BouncyCastleProvider());
		setMode(0);
	}
	
	/**
	 * Sets the hash function to use to generate the digest to be signed. 
	 * @param _mode 0 for SHA-256, 1 for SHA1.
	 */
	public void setMode(int _mode) {
		switch (_mode) {
		case 0:			
			algorithm = "SHA-256";
			break;
		case 1:			
			algorithm = "SHA1";
			break;
		}
	}
	
	/**
	 * Generates the DSA keys (public and private).
	 * @throws Exception
	 */
	public void generateKeys() throws Exception {									
		// generate DSA public/private key pair
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA", "BC");
		keyPairGenerator.initialize(keySize);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();			
		
		// save keys' hex values to class strings 
		publicKey = Common.byteArrayToHexString(keyPair.getPublic().getEncoded());			
		privateKey = Common.byteArrayToHexString(keyPair.getPrivate().getEncoded());					
	}
	
	/**
	 * Signs the message using DSA private key.
	 * @throws Exception
	 */
	public void sign () throws Exception {						
		// generate private key from hex string	    		    	
    	PrivateKey privKey = KeyFactory.
    			getInstance("DSA", "BC").
    			generatePrivate(
    					new PKCS8EncodedKeySpec( // standard format for private key
    							Common.hexStringToByteArray(privateKey)
    							)
    					);
		
    	// generates the message's digest
    	generateDigest();    	
    	
    	// signs the digest using the private key
		Signature dsa = Signature.getInstance("NONEwithDSA", "BC");		
		dsa.initSign(privKey);
		dsa.update(digestBytes);	
		signature = Common.byteArrayToHexString(dsa.sign());				
	}	
	
	/**
	 * Verifies the DSA signature of the message using the public key.
	 * @return True if signature was correctly verified; false, otherwise.
	 * @throws Exception
	 */
	public boolean verify () throws Exception {
		// generate public key from hex string
		PublicKey pubKey = KeyFactory.
    			getInstance("DSA", "BC").
    			generatePublic(
    					new X509EncodedKeySpec( // standard format for public key
    							Common.hexStringToByteArray(publicKey)
    							)
    					);   
		
		// generates the message's digest
		generateDigest();
		
		// verifies the digest's signature using the public key
		Signature dsa = Signature.getInstance("NONEwithDSA", "BC");
		dsa.initVerify(pubKey);		
		dsa.update(digestBytes);		
		return dsa.verify(Common.hexStringToByteArray(signature));
	}
	
	/**
	 * Generates the digest for the message and updates the class variables.
	 * @throws Exception
	 */
	private void generateDigest () throws Exception {
		digestBytes = MessageDigest.getInstance(algorithm, "BC").digest(message.getBytes());		
		digest = Common.byteArrayToHexString(digestBytes);
	}	
}
