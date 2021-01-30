package movieManagementSystem;

public class Date {
	
	// Initial variable declaration
	int month, day;
	long year;
	
	// Default Constructor
	public Date() {}
	
	// Constructor allowing for some variable to be initialized with the object
	public Date(int newMonth, int newDay, long newYear) {
		month = newMonth;
		day = newDay;
		year = newYear;
	}
	
	// Setter method for changing the month to a new month
	public void changeMonth(int newMonth) {
		month = newMonth;
	}
	
	// Setter method for changing the day to a new day
	public void changeDay(int newDay) {
		day = newDay;
	}
	
	// Setter method for changing the year to a new year
	public void changeYear(int newYear) {
		year = newYear;
	}
	
	// Setter method for changing the date to a new one entirely
	public void changeDate(int newMonth, int newDay, int newYear) {
		month = newMonth;
		day = newDay;
		year = newYear;
	}
	
	// Getter method for obtaining the month
	public int getMonth() {
		return month;
	}
	
	// Getter method for obtaining the day
	public int getDay() {
		return day;
	}
	
	// Getter method for obtaining the year
	public long getYear() {
		return year;
	}
	
	// Overrides Object.toString to print the full date
	public String toString() {
		return getMonth() + "/" + 
				getDay() + "/" + 
				getYear();
	}
}
