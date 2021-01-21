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

}
