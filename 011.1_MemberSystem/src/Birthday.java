
public class Birthday {
	private int year;
	private int month;
	private int day;
	
	public Birthday(int y, int m, int d) {
		if (checkInputDate(y, m, d) == true) {
			this.year = y;
			this.month = m;
			this.day = d;
		}
		else 
			this.year = this.month = this.day = 1;
	}
	
	public void setBirthday(int y, int m, int d) {
		if (checkInputDate(y, m, d) == true) {
			this.year = y;
			this.month = m;
			this.day = d;
		}
	}
	public static boolean checkInputDate(int y, int m, int d) {
		if (m < 1 || m > 12)
			return false;
		
		//Decide if input year is leap year.
		if (m == 2) {
			if ((y % 400 == 0) || (y % 4 == 0 && y % 100 != 0)){
			     if (d > 29 || d < 1)
			    	 	return false;
			}
			else{
			     if (d > 28 || d < 1)
			    	 	return false;
			}
		}
		//Decide if input date is legal.
		else if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
			if (d > 31 || d < 1)
				return false;
		}
		else {
			if (d > 30 || d < 1)
				return false;
		}
		
		return true;
	}
	
	public static boolean compareDate(Birthday a, Birthday b) {
		//Assume array a is earlier than b, if not, return false.
		if (a.year >= b.year) {
			if (a.year == b.year) {
				if (a.month >= b.month) {
					if (a.day > b.day)
						return false;
					else
						return true;
				}
				else 
					return false;
			}
			else 
				return false;
		}
		else
			return true;
	}
	
	public static int valueYear(Birthday a) {
		return a.year;
	}
	
	public static int valueMonth(Birthday a) {
		return a.month;
	}
	
	public static int valueDate(Birthday a) {
		return a.day;
	}
	
	@Override
	public String toString() {
		String a = String.format("%04d", this.year) + "/" + String.format("%02d",this.month) + "/" + String.format("%02d",this.day);
		return a;
	}

}
