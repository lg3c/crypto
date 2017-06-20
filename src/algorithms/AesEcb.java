package src.algorithms;

public class AesEcb extends AesSuper {
	
	public static byte[] encrypt(byte[] in, byte[] key, boolean padding) {		
		Nb = 4;
		Nk = key.length/4;
		Nr = Nk + 6;		
		
		int i, 
			totalLength = in.length;
		
		byte[] paddingBytes = new byte[1];
		if (padding) {
			int paddingLenght = 0;			
			paddingLenght = 16 - in.length % 16;
			paddingBytes = new byte[paddingLenght];
			paddingBytes[0] = (byte) 0x80;
			for (i = 1; i < paddingLenght; i++)
				paddingBytes[i] = 0;
			totalLength += paddingLenght;
		}
		
		byte[] tmp = new byte[totalLength];		
		byte[] block = new byte[16];									
		
		w = generateSubkeys(key);
		
		int count = 0;

		for (i = 0; i < totalLength; i++) {
			if (i > 0 && i % 16 == 0) {
				block = encryptBlock(block);
				System.arraycopy(block, 0, tmp, i - 16, block.length);
			}
			if (i < in.length)
				block[i % 16] = in[i];
			else if (padding) {														
				block[i % 16] = paddingBytes[count % 16];
				count++;
			}
		}
		if(block.length == 16){
			block = encryptBlock(block);
			System.arraycopy(block, 0, tmp, i - 16, block.length);
		}
		
		return tmp;
	}
	
	public static byte[] decrypt(byte[] in, byte[] key, boolean padding) {
		int i;
		byte[] tmp = new byte[in.length];
		byte[] block = new byte[16];		
		
		Nb = 4;
		Nk = key.length/4;
		Nr = Nk + 6;
		w = generateSubkeys(key);

		for (i = 0; i < in.length; i++) {
			if (i > 0 && i % 16 == 0) {
				block = decryptBlock(block);
				System.arraycopy(block, 0, tmp, i - 16, block.length);
			}
			if (i < in.length)
				block[i % 16] = in[i];
		}
		block = decryptBlock(block);
		System.arraycopy(block, 0, tmp, i - 16, block.length);		

		return padding ? deletePadding(tmp) : tmp;
	}			
}

