import java.util.Scanner;
import java.util.Arrays;
import java.nio.charset.StandardCharsets;

public class Caeser_Decrypt {

	public static void main(String[] args) {
		String ascii1 = "abcdefghijklmnopqrstuvwxyz";  // Character set of Cipher
		char[] asciiString = ascii1.toCharArray();
		int Alphabet_Length=ascii1.length();					//Length of character set


		String s;
		int Decrypted_Msg = 0;

		Scanner Msg = new Scanner(System. in);     // Input Message to be encrypted
		System.out.println("Enter a String:");
		s = Msg. nextLine();
		char[] s_in = s.toCharArray();
		if (s.matches("[a-zA-Z]+"))           // conditions for input only Alphabets allowed.
		{
			int length = s_in.length; // Length of the input String.
			int Key_1;
			for (Key_1=0;Key_1<26;  Key_1++)
			{ 
				System.out.println("Key Value:"+Key_1);
				for(int i=0;i<length;i++)
				{
					for (int j=0;j<Alphabet_Length;j++)
					{
						s_in[i]=Character.toLowerCase(s_in[i]);
						if(s_in[i]==asciiString[j]) // compares input String with Character Set
						{
							//System.out.println(j);
							int value= j-Key_1;
							//System.out.println("Key :"+value);
							if (value<0)
							{
								Decrypted_Msg = ((value)+26); // Decryption
								//System.out.print(Decrypted_Msg);
								System.out.print(asciiString[Decrypted_Msg]);
							}
							else
							{
								Decrypted_Msg = value;
								System.out.print(asciiString[Decrypted_Msg]);
							}

						}

					}


				}
				System.out.println();
			}
		}
		else
		{
			System.out.println("only Aphabets are allowed");

			System.exit(1);
		}	


	}

	


}
