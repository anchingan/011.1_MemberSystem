
import java.io.*;
import java.lang.*;
import java.util.Arrays;
import java.util.Scanner;

public class TestMemberSystem {
	//Used to store member data, [0]will be dummy.
	public static Member[] membersArray = new Member[50];
	//Record number of string lines read from file.
	public static int count = 0, input;
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) 
		throws IOException, FileNotFoundException{
		int status = 0; 
		
		System.out.print("載入資料檔名：");
		String fileName = scanner.next();
		readFile(fileName);
		while (status != -1) {
			switch(status) {
			//Input option and edit status.
			case 0:
				System.out.print("選項：1)檢視全部, 2)新增, 3)刪除, 4)修改, 5)篩選編號, -1) 結束? ");
				input = scanner.nextInt();
				if (input == -1)
					status = -1;
				else if (input > 0 && input < 6)
					status = input;
				else
					System.out.println("Input error!");
				break;
			//Show all data.
			case 1:
				showAll();
				status = 0;
				break;
			//Add new datum.
			case 2:
				add();
				status = 0;
				break;
			//Delete certain datum.	
			case 3:
				delete();
				status = 0;
				break;
			//Edit certain datum.
			case 4:
				edit();
				status = 0;
				break;
			//Out put selected data.
			case 5:
				int startIndex, endIndex;
				while (true) {
					System.out.print("輸入起始編號：");
					input = scanner.nextInt();
					if (input > 0 && input < count) {
						startIndex = input;
						break;
					}
					System.out.println("編號超出索引範圍！");
				}
				while (true) {
					System.out.print("輸入結束編號：");
					input = scanner.nextInt();
					if (input > 0 && input < count && input > startIndex) {
						endIndex = input;
						break;
					}
					System.out.println("編號超出索引範圍！");
				}
				
				System.out.print("輸出資料檔名：");
				fileName = scanner.next();
				outPut(fileName, startIndex, endIndex);
				status = 0;
				break;
			}
		}

		System.out.println("Programe terminate.");
	}
	
	public static Member readInToMember(String input) {
		String[] storeStrings = input.split("\t");
		Member returnMember = new Member();
		returnMember.setNum(storeStrings[0]);
		returnMember.setName(storeStrings[1]);
		returnMember.setID(storeStrings[2]);
		returnMember.setBirthday(storeStrings[3]);
		returnMember.setPhone(storeStrings[4]);
		return returnMember;
	}
	
	public static void readFile(String fileName) 
			throws IOException, FileNotFoundException{
		String[] strsOfFileIn = new String[50];
		//Create new object of BufferedReader to read file in.
		BufferedReader fileIn = new BufferedReader(new FileReader(fileName));
		String readStr;
		//Read content of file in into string[] and record how many data are in this file.
		while ((readStr = fileIn.readLine()) != null) {
			strsOfFileIn[count++] = readStr;
		}
		fileIn.close();
		//Transform read in strings into Member and stored into membersArray.
		for (int i = 1; i < count; i++) {
			//If number of data is more than array size, resize it.
			if (i > membersArray.length)
				membersArray = Arrays.copyOf(membersArray, count * 2);
			membersArray[i] = readInToMember(strsOfFileIn[i]);
			System.out.println(membersArray[i]);
		}
	}
	
	public static void outPut(String fileName, int startIndex, int endIndex) 
			throws IOException, FileNotFoundException {
		//Create an object to output file.
		BufferedWriter fileOut = new BufferedWriter(new FileWriter(fileName));
		fileOut.write(Member.printTitle());
		for (int i = startIndex; i <= endIndex; i++) {
			fileOut.newLine();
			fileOut.write(membersArray[i].toString());
		}
		fileOut.flush();
		fileOut.close();
		System.out.println("檔案輸出成功！");
	}
	
	public static void showAll() {
		System.out.println(Member.printTitle());
		for (int i = 0; i < 60; i++)
			System.out.print("=");
		System.out.println("");
		for (int i = 1; i < count; i++)
			System.out.println(membersArray[i]);
		System.out.println("");
	}
	
	public static void add() {
		int input, num, y, m, d;
		String name, iD, phone;
		
		while (true) {
			System.out.print("編號(介於0-100)：");
			input = scanner.nextInt();
			if (input < 0 || input > 100)
				System.out.println("錯誤！超出範圍！");
			else if (search(input) != -1)
				System.out.println("錯誤！編號重複！");
			else {
				num = input;
				break;
			}
		}
		System.out.print("姓名：");
		name = scanner.next();
		while (true) {
			System.out.print("身分證字號：");
			iD = scanner.next();
			String temp = Member.idCheck(iD);
			if (temp.equals(iD) == false)
				System.out.println("輸入錯誤！");
			else 
				break;
		}
		
		System.out.print("生日（年/月/日）：");
		y = scanner.nextInt();
		m = scanner.nextInt();
		d = scanner.nextInt();
		System.out.print("電話：");
		phone = scanner.next();
		if (count >= membersArray.length)
			membersArray = Arrays.copyOf(membersArray, count * 2);
		membersArray[count++] = new Member(num, name, iD, y, m, d, phone);
		System.out.println("會員新增成功!");
		sort();
	}
	
	public static int search(int target) {
		for (int i = 1; i < count; i++) {
			if (membersArray[i].getNum() == target)
				return i;
		}
		return -1;
	}
	
	public static void sort() {
		Member temp;
		for (int i = 1; i < count; i++) {
			for (int j = 1; j < count - i; j++) {
				if (membersArray[j].getNum() > membersArray[j + 1].getNum()) {
					temp = membersArray[j];
					membersArray[j] = membersArray[j + 1];
					membersArray[j + 1] = temp;
				}
			}
		}
	}
	
	public static void delete() {
		System.out.print("輸入編號：");
		input = scanner.nextInt();
		int find = search(input);
		if (find == -1)
			System.out.println("編號不存在！");
		else {
			count--;
			for (int i = input; i < count; i++)
				membersArray[i] = membersArray[i + 1];
			System.out.println("刪除成功！");
		}
	}
	
	public static void edit() {
		System.out.print("輸入編號：");
		input = scanner.nextInt();
		int find = search(input);
		if (find == -1)
			System.out.println("編號不存在！");
		else {
			int y, m, d;
			String name, iD, phone;
			
			System.out.print("姓名：");
			name = scanner.next();
			while (true) {
				System.out.print("身分證字號：");
				iD = scanner.next();
				String temp = Member.idCheck(iD);
				if (temp.equals(iD) == false)
					System.out.println("輸入錯誤！");
				else 
					break;
			}
			
			System.out.print("生日（年/月/日）：");
			y = scanner.nextInt();
			m = scanner.nextInt();
			d = scanner.nextInt();
			System.out.print("電話：");
			phone = scanner.next();
			membersArray[input].setName(name);
			membersArray[input].setID(iD);
			membersArray[input].setBirthday(y, m, d);
			membersArray[input].setPhone(phone);
			System.out.println("會員修改成功!");
		}
	}
	
}
