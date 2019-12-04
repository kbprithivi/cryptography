package affine_filter;

import java.util.Scanner;
import java.math.*; 
public class affine_decrypt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String ascii1 = "abcdefghijklmnopqrstuvwxyz0123456789 ";  // Character set of Cipher
		char[] asciiString = ascii1.toCharArray();
		int Alphabet_Length=ascii1.length();					//Length of character set

		int Decrypted_Msg = 0;

		String Encrypted_Msg1="5T45RT3TDVDKTVQD6VMMKO5BDCVMTBDO7D4 TDAO88OKR7SDBRSTM4DAACHW0IZHEZCFCWWWXCEHWWXTBYVFVFEXAZGCUAAAXUIXAZZUX0YYYYWGHVZIVVFDOC4VR7TBDKR4 D VM DAL7U4RO7DM VHYF";
		char[] Encrypted_Msg=Encrypted_Msg1.toCharArray();
		int length=Encrypted_Msg1.length();

		System.out.println(length);


		if (Encrypted_Msg1.matches("[a-zA-Z0-9 ]+"))           // conditions for input only Alphabets , numbers and space allowed.
		{


			int Key_1,Key_2;
			Scanner Key1 = new Scanner(System. in); //first key Input
			System. out. println("Enter a Key: ");
			Key_1 = Key1. nextInt();
			System.out.println("the key1:" +Key_1);
			int MI=Key_1 %Alphabet_Length;
			//	System.out.println("MI:"+MI);




			Scanner Key2 = new Scanner(System. in);    //Second Key Input
			System. out. println("Enter a key");
			Key_2 = Key2. nextInt();
			System.out.println("the key2:" +Key_2);

			for(int i=0;i<length;i++)
			{

				for (int j=0;j<Alphabet_Length;j++)
				{
					Encrypted_Msg[i]=Character.toLowerCase(Encrypted_Msg[i]);

					//if(asciiString[j]equalsIgnoreCase(s_in[i]) 
					if(Encrypted_Msg[i]==asciiString[j]) // compares input String with Character Set
					{

						//	System.out.println("loc:"+j);

						for (int x = 0; x <= Alphabet_Length; x++) 
						{
							int flag=(MI*x)%Alphabet_Length;
							if (flag==1)
							{
								//  System.out.println(x);
								int a_inv= x;

								Decrypted_Msg= ((j-Key_2)*a_inv);

								if(Decrypted_Msg<0)
								{
									Decrypted_Msg=(Decrypted_Msg%37)+37;
									System.out.print(asciiString[Decrypted_Msg]);

								}

								else 
								{
									Decrypted_Msg=Decrypted_Msg%37;
									System.out.print(asciiString[Decrypted_Msg]);
								}




							}
						}
					}
				}

			}

		}




	}


}