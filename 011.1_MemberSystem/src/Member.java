
public class Member {
	private String name, iD, phone;
	private Birthday birthday;
	private int num;
	
	public Member() {
		this.birthday = new Birthday();
	}
	public Member(int num, String name, String iD, int y, int m, int d, String phone) {
		this.name = "Name undefined";
		
		if (name != null)
			this.name = name;
		this.iD = idCheck(iD);
		this.birthday = new Birthday(y, m, d);
		this.phone = phone;
		if (num > 0 && num < 100)
			this.num = num;
		else
			this.num = 0;
		
	}
	//Methods allow other to get value of members.
	public String getName() {
		return this.name;
	}
	public String getId() {
		return this.iD;
	}
	public String getPhone() {
		return this.phone;
	}
	public String getBirthday() {
		return this.birthday.toString();
	}
	public int getNum() {
		return this.num;
	}
	
	//Methods allow other to set value of members.
	public boolean setNum(int i) {
		if (i > 0 && i < 100) {
			this.num = i;
			return true;
		}
		else
			return false;
	}
	
	public boolean setNum(String i) {
		int iInt = Integer.parseInt(i);
		return (setNum(iInt));
	}
	public boolean setName(String nameInput) {
		if (nameInput != null) {
			this.name = nameInput;
			return true;
		}
		return false;
	}
	
	public void setID(String idInput) {
		this.iD = idCheck(idInput);
	}
	
	public void setPhone(String phoneInput) {
		this.phone = phoneInput;
	}
	
	public void setBirthday(int y, int m, int d) {
		this.birthday.setBirthday(y, m, d);
	}
	
	public void setBirthday(String input) {
		String[] temp = input.split("/");
		int y = Integer.parseInt(temp[0]);
		int m = Integer.parseInt(temp[1]);
		int d = Integer.parseInt(temp[2]);
		this.setBirthday(y, m, d);
	}
	
	public static String idCheck(String input) {
		//Transform the first character into integer to check if it's character.
		int a = (int)input.charAt(0);
		//If first character is lower case, trans into upper case.
		if (a >= 97 && a <= 122)
			a -= 32;
		
		if (input.length() != 10)
			return "A123456789";
		else if (Character.digit(input.charAt(1), 2 + 1) == -1)
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
					numbers[i] = Character.digit(input.charAt(i), 10);
				
				int temp = num1, temptest;
				for (int i = 1; i < 9; i++) {
					temp += numbers[i] * (9 - i);
				}
				temp = 10 - (temp % 10); 
				if (temp == 10 && numbers[9] == 0)
					return input;
				else if(temp == numbers[9])
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
		transInt[26] = 33;
		
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
	
	@Override
	public String toString() {
		String output;
		output = String.format("%03d\t%3s\t%10s\t%s\t%10s", this.num, this.name, this.iD, this.birthday, this.phone);	
		return output;
	}
	
	public static String printTitle() {
		String s = String.format("%s\t%s\t%s\t%s\t%10s", "編號", "姓名", "身分證字號", "生日", "電話");
		return s;
	}

}
