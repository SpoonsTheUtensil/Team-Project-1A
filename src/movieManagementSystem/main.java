package movieManagementSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws IOException {
		
		Scanner userInput = new Scanner(System.in);

		System.out.println("What is the name of the file you'd like to test? (.txt file no need to add extension) ");
		String testCaseFile = userInput.next();
		
		FileInputStream inputFile1 = new FileInputStream(testCaseFile = ".txt");
		
		
		// ArrayList for each separate movie list
		ArrayList<Movie> moviesShowing = new ArrayList<>();
		ArrayList<Movie> moviesComing = new ArrayList<>();

		Movie movie = new Movie();
		
		
	}
	
	public static void printMenu() {
		System.out.println("Select an option: ");
		System.out.println("1. Display movies");
		System.out.println("2. Add movies");
		System.out.println("3. Edit release dates");
		System.out.println("4. Edit movie description");
		System.out.println("5. Start showing movies in theaters");
		System.out.println("6. Number of movies before a date");
		System.out.println("7. Save");
		System.out.println("8. Exit");
	}

}
