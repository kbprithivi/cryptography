package affine_filter;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
//import java.util.Scanner;

public class affine_encrypt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		String ascii1 = "abcdefghijklmnopqrstuvwxyz0123456789 ";  // Character set of Cipher
		char[] asciiString = ascii1.toCharArray();
		int Alphabet_Length=ascii1.length();					//Length of character set


		String s;
		int Encrypted_Msg = 0;

		Scanner Msg = new Scanner(System. in);     // Input Message to be encrypted
		System. out. println("Enter a string");
		s = Msg. nextLine();
		char[] s_in = s.toCharArray();
		if (s.matches("[a-zA-Z0-9 ]+"))           // conditions for input only Alphabets , numbers and space allowed.
		{
			
			
			int length = s_in.length; // Length of the input String.
			//System. out. println(length);		
			int Key_1,Key_2;
			Scanner Key1 = new Scanner(System. in); //first key Input
			System. out. println("Enter a Key: ");
			Key_1 = Key1. nextInt();
			System.out.println("the key1:" +Key_1);

			Scanner Key2 = new Scanner(System. in);    //Second Key Input
			System. out. println("Enter a key");
			Key_2 = Msg. nextInt();
			System.out.println("the key2:" +Key_2);

			for(int i=0;i<length;i++)
			{

				for (int j=0;j<Alphabet_Length;j++)
				{
					//	System.out.println(asciiString[j]);
				//s_in[i]=equalsIgnoreCase(s_in[i]);
					s_in[i]=Character.toLowerCase(s_in[i]);
					
					//if(asciiString[j]equalsIgnoreCase(s_in[i]) 
					if(s_in[i]==asciiString[j]) // compares input String with Character Set
					{

						Encrypted_Msg = ((((((Key_1* j)-'A')%37)+Key_2)+'A')%37); // Encryption
						System.out.print(asciiString[Encrypted_Msg]);



					}

				}

			}

		}
		else
		{
			System.out.println("No Special Characters allowed");

			System.exit(1);
		}


	}

	private static char equalsIgnoreCase(char c) {
		// TODO Auto-generated method stub
		return 0;
	}


}
