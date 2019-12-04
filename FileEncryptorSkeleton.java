import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Program to encrypt and decrypt files using a key derived from a password by PBKDF2
 * and AES-128
 */

/**
 * @author les
 * @version $Revision: 1.1 $
 */
public class FileEncryptorSkeleton {

	private static final String progName = "FileEncryptor";
	private static final int bufSize = 128;

	/**
	 * @param args
	 * @param initVector 
	 */
	public static void main(String[] args) {

		BufferedInputStream in = null;			// A buffered input stream to read from
		BufferedOutputStream out = null;		// And a buffered output stream to write to
		//SecretKeyFactory kf = null;				// Something to create a key for us
		KeySpec ks = null;						// This is how we specify what kind of key we want it to generate
		byte[] salt = new byte[20];				// Some salt for use with PBKDF2, only not very salty
		SecretKey key = null;					// The key that it generates
		//Cipher cipher = null;					// The cipher that will do the real work
		//SecretKeySpec keyspec = null;			// How we pass the key to the Cipher
		int bytesRead = 0;						// Number of bytes read into the input file buffer

		// First, check the user has provided all the required arguments, and if they haven't, tell them then exit
		if(args.length != 4) {
			printUsageMessage(); System.exit(1);
		}

		// Open the input file
		try {
			in = new BufferedInputStream(new FileInputStream(args[1]));
		} catch (FileNotFoundException e) {
			printErrorMessage("Unable to open input file: " + args[1], null);
			System.exit(1);
		}

		// And then the output file
		try {
			out = new BufferedOutputStream(new FileOutputStream(args[2]));
		} catch (FileNotFoundException e) {
			printErrorMessage("Unable to open output file: " + args[2], e);
			System.exit(1);
		}

		// Create a PBKDF2 secret key factory

		SecretKeyFactory factory=null;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



		// Set up a KeySpec for password-based key generation of a 128-bit key


		KeySpec keyspec = new PBEKeySpec(args[3].toCharArray(), salt, 4096, 128);
		System.out.println(args[2]);

		try {
			key = factory.generateSecret(keyspec);
		} catch (InvalidKeySpecException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}


		// Now run the passphrase through PBKDF2 to get the key


		System.out.println(key.getClass().getName());
		System.out.println(Arrays.toString(key.getEncoded()));




		// Get the byte encoded key value as a byte array
		byte[] aeskey = key.getEncoded();

		System.out.println(aeskey[0]);
		Cipher cipherecb=null;
		Cipher ciphercbc=null;
		// Now generate a Cipher object for AES encryption in ECBC mode with PKCS #5 padding
		//Use ECB for the first task, then switch to CBC for versions 2 and 3
		try {

			//cipherecb = Cipher.getInstance("AES/ecb/PKCS5PADDING");
			ciphercbc= Cipher.getInstance("AES/CBC/PKCS5PADDING");

		} catch (NoSuchAlgorithmException e) {
			printErrorMessage("No Such Algorithm Exception when creating main cipher", e);
			System.exit(2);
		} catch (NoSuchPaddingException e) {
			printErrorMessage("No Such Padding Exception when creating main cipher",e);
			System.exit(2);
		}

		//	Set a variable to indicate whether we're in encrypt or decrypt mode, based upon args[0]
		int cipherMode = -1;
		char mode = Character.toLowerCase(args[0].charAt(0));
		switch (mode) {
		case 'e' : cipherMode = Cipher.ENCRYPT_MODE; break;
		case 'd' : cipherMode = Cipher.DECRYPT_MODE; break;
		default: printUsageMessage(); System.exit(1);
		}
		System.out.println(cipherMode);

		// Set up a secret key specification, based on the 16-byte (128-bit) AES key array previously generated
		// KeySpec keySpec = new KeySpec(aeskey.getBytes("UTF-8"), "AES");	

		SecretKeySpec secretKey = new SecretKeySpec(aeskey, "AES");
		System.out.println(secretKey);

		//        Now initialize the cipher in the right mode, with the keyspec and the ivspec
		
		
		//Random IV
		int ivSize = 16;
		byte[] ivRandom = new byte[ivSize];
		SecureRandom random = new SecureRandom();
		random.nextBytes(ivRandom);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(ivRandom); 
		
		// Constant iv
		String ivConstant="abcdefghijklmnop";
		AlgorithmParameterSpec param = new IvParameterSpec(ivConstant.getBytes());


		try {
			ciphercbc.init(cipherMode, secretKey,ivParameterSpec);  //CBC MODE
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidAlgorithmParameterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//			try {
//				cipherecb.init(cipherMode, secretKey);// ECB MODE 
//					} catch (InvalidKeyException e) {
//					printErrorMessage("Invalid Key Spec",e); System.exit(2);
//				}
//				
		// Set up some input and output byte array buffers
		byte[] inputBuffer = new byte[bufSize];
		byte[] outputBuffer = null;

		// "Prime the pump" - we've got to read something before we can encrypt it
		// and not encrypt anything if we read nothing.
		try {
			bytesRead = in.read(inputBuffer);
		} catch (IOException e) {
			printErrorMessage("Error reading input file " + args[1],e); System.exit(1);
		}

		// As long as we've read something, loop around encrypting, writing and reading
		// bytesRead will be zero if nothing was read, or -1 on EOF - treat them both the same
		while (bytesRead > 0) {

			// Now encrypt this block

			outputBuffer = ciphercbc.update(inputBuffer);
			// Write the generated block to file

			
			try {
				out.write(outputBuffer);
			} catch (IOException e) {
				printErrorMessage("Error writing to output file " + args[2],e); System.exit(1);
			}

			// And read in the next block of the file
			try {
				bytesRead = in.read(inputBuffer);
			} catch (IOException e) {
				printErrorMessage("Error reading input file " + args[1],e); System.exit(1);
			}
		}

		// Now do the final processing
		try {
			outputBuffer=ciphercbc.doFinal(); // Stores Encrypted value in output buffer 
		//	outputBuffer = cipher.update(outputBuffer);
			//		System.out.println("0"+outputBuffer[0]);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		// Write the final block of output
		try {
			out.write(outputBuffer);  // Writes the cipher in output buffer to a output file .
		} catch (IOException e) {
			printErrorMessage("Error on final write to output file " + args[2],e); System.exit(1);
		}

		// Close the output files
		try {
			in.close();
			out.close();
		} catch (IOException e) {
			printErrorMessage("Error closing file", e);
		}


		// If we were continuing beyond this point, we should really overwrite key material, drop KeySpecs, etc.
	}


	/**
	 * Print an error message on stderr, optionally picking up additional detail from
	 * a passed exception
	 * @param errMsg
	 * @param e
	 */
	private static void printErrorMessage(String errMsg, Exception e) {
		System.err.println(errMsg);
		if (e != null) 
			System.err.println(e.getMessage());
	}

	/**
	 * Print a usage message
	 */
	private static void printUsageMessage() {
		System.out.println(progName + " $Revision: 1.1 $: Usage: " + progName + " E/D infile outfile passphrase");
	}

}
