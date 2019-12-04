package affine_filter;

public class Affine_Decrypt_Customised {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String ascii1 = "abcdefghijklmnopqrstuvwxyz0123456789 ";  // Character set of Cipher
		char[] asciiString = ascii1.toCharArray();
		int Alphabet_Length=ascii1.length();					//Length of character set

		int Decrypted_Msg = 0;
			//input String to be decrypted
		String Encrypted_Msg1="5T45RT3TDVDKTVQD6VMMKO5BDCVMTBDO7D4 TDAO88OKR7SDBRSTM4DAACHW0IZHEZCFCWWWXCEHWWXTBYVFVFEXAZGCUAAAXUIXAZZUX0YYYYWGHVZIVVFDOC4VR7TBDKR4 D VM DAL7U4RO7DM VHYF";
		char[] Encrypted_Msg=Encrypted_Msg1.toCharArray();
		int length=Encrypted_Msg1.length();

		//System.out.println(length); // Length of the Encrypted msg.


		if (Encrypted_Msg1.matches("[a-zA-Z0-9 ]+"))           // conditions for input only Alphabets , numbers and space allowed.
		{


			int Key_1,Key_2=21;  //one of the Key is known 
			System.out.println("Outputs for variable key1 and constant Key2");
			
			for(Key_1=1;Key_1<38;Key_1++)  // loop for key iteration
			{
				int MI=Key_1%Alphabet_Length; 
				System.out.println("When Key1="+Key_1+ "and Key2="+Key_2  );
			for(int i=0;i<length;i++)   // iteration for each character of encrypted msg
			{

				for (int j=0;j<Alphabet_Length;j++) 
				{
					Encrypted_Msg[i]=Character.toLowerCase(Encrypted_Msg[i]);

					 
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

System.out.println();
			}
System.out.println("Outputs for constant key 1 and variable key2");  
			
int Key3=21,Key4;

for(Key4=0;Key4<38;Key4++)  // loop for key iteration
{
	System.out.println ("when Key1:"+Key3+ "And Key2:"+Key4);
	int MI=Key3%Alphabet_Length;

for(int i=0;i<length;i++) // iteration for each character of encrypted msg
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

					Decrypted_Msg= ((j-Key4)*a_inv);

					if(Decrypted_Msg<0)
					{
						//System.out.println(Decrypted_Msg);	
						Decrypted_Msg=(Decrypted_Msg%37);
						if(Decrypted_Msg<0)
						{
							Decrypted_Msg=(Decrypted_Msg+37);
							System.out.print(asciiString[Decrypted_Msg]);
						}
						else
							
						
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
System.out.println();
}


		}

	}


}
