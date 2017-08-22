
public class IdNumber {
	char pre;
	int sexual;
	String num;
	
	public IdNumber(String input) {
		if (input.length() != 10) {
			this.pre = 'A';
			this.sexual = 1;
			this.num = "23456789";
		}
		else {
			this.pre = input.toUpperCase().charAt(1);
			if ((int)(input.charAt(2)) != 1 || (int)(input.charAt(2)) != 2)
					this.sexual = 1;
			else
				this.sexual = (int)(input.charAt(2));
			
		}
//		num = input.substring(3, 10);
	}
	
	@Override
	public String toString() {
		String a = new String();
		a = a + sexual + num;
		return a;
	}

}
