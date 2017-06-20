package src.algorithms;

public class Common {
	
	/**
	 * Converts a string with hex values to a byte array.
	 * @param s The string with hex values.
	 * @return The byte array.
	 */
	public static byte[] hexStringToByteArray(String s) throws Exception {
		if (!s.matches("[0-9A-Fa-f]+")){
			throw new Exception("Hex string contains invalid characters.");
		}			
		
		int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}

	/**
	 * Converts a byte array to a string with its hex representation.
	 * @param b The byte array to be converted.
	 * @return The string with hex values.
	 */
	public static String byteArrayToHexString(byte[] b){
		return javax.xml.bind.DatatypeConverter.printHexBinary(b);
	}
}
