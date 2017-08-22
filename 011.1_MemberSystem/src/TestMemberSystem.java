
import java.io.*;
import java.lang.*;
import java.util.Scanner;

public class TestMemberSystem {

	public static void main(String[] args) {
//		Member[] membersArray = new Member[50];
//		
//		
//		membersArray[0] = new Member(1,"黃獻鋒", "F227518590", 1988, 2, 3,"0225342312");
//		membersArray[1] = new Member(5,"黃獻鋒", "A128099248", 1990, 3, 5,"0222312");
//		for (int i = 0; i < 2; i++)
//		System.out.println(membersArray[i]);
		
		String a = "F227518590";
		String b = idCheck(a);
		System.out.println(a);
		System.out.println(b);


	}
	
	private static String idCheck(String input) {
		//Transform the first character into integer to check if it's character.
		int a = (int)input.charAt(0);
		//If first character is lower case, trans into upper case.
		if (a >= 97 && a <= 122)
			a -= 32;
		
		if (input.length() != 10)
			return "A123456789";
		else if ((int)(input.charAt(1)) != 1 || (int)(input.charAt(1)) != 2)
			return "A123456789";
		//Check first character.
		else if (a >= 65 && a <= 90) {
			int num1 = charTrans(input.charAt(0));
			if (num1 == -1)
				return "A123456789";
			else {
				//Check if id is legal.
				int[] numbers = new int[10];
				numbers[0] = num1;
				for (int i = 1; i < 10; i++) 
					numbers[i] = charTrans(input.charAt(i));
				
				int temp = num1;
				for (int i = 1; i < 9; i++) {
					temp += numbers[i] * (9 - i);
				}
				if (temp == numbers[9])
					return input;
			}
		}
		return "A123456789";
	}
	private static int charTrans(char first) {
		int[] transInt = new int[27];

		for (int i = 1; i < 9; i++) 
			transInt[i] = 9 + i;
		transInt[9] = 34;
		for (int i = 10; i < 15; i++)
			transInt[i] = i + 8;
		transInt[15] = 35;
		for (int i = 16; i < 23; i++)
			transInt[i] = i + 7;
		transInt[23] = 32;
		transInt[24] = 30;
		transInt[25] = 31;
		transInt[25] = 32;
		
		//Make table of number of each character.
		for (int i = 1; i < 27; i++) 
			transInt[i] = transInt[i] / 10 + transInt[i] % 10 * 9;
		
		char[] alphabet = new char[27];
		for (int i = 1; i < 27; i++) {
			alphabet[i] = (char)(i + 64);
		}
		
		for (int i = 1; i < 27; i++) {
			if (alphabet[i] == first)
				return transInt[i];
		}
		
		return -1;
	}
	
}
