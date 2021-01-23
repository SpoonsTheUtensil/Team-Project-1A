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
		boolean runProgram = true;
		int optionChoice = 0;

		System.out.println("What is the name of the file you'd like to test? (.txt file no need to add extension) ");
		String testCaseFile = userInput.next();
		
		FileInputStream inputFile1 = new FileInputStream(testCaseFile + ".txt");
		
		
		// ArrayList for each separate movie list
		ArrayList<Movie> moviesShowing = new ArrayList<>();
		ArrayList<Movie> moviesComing = new ArrayList<>();

		Movie movie = new Movie();
		
		while (runProgram) {
			System.out.println();
			printMenu();
			optionChoice = userInput.next().charAt(0);
			
			switch (optionChoice) {
			case '1':
				displayMovies();
				break;
			case '2':
				addMovie();
				break;
			case '3':
				editMovie(1);
				break;
			case '4':
				editMovie(2);
				break;
			case '5':
				showMovies();
				break;
			case '6':
				System.out.print(numOfComingMovies());
				break;
			case '7':
				save();
				break;
			case '8':
				System.exit(0);
			default:
				System.out.println("Invalid option. Please try again.");
				break;
			}
		}
	}
	
	/**
	 * Outputs a menu of actions the user can do
	 */
	public static void printMenu() {
		System.out.println("Select an option: ");
		System.out.println("1. Display movies");
		System.out.println("2. Add movie");
		System.out.println("3. Edit release dates");
		System.out.println("4. Edit movie description");
		System.out.println("5. Start showing movies in theaters");
		System.out.println("6. Number of movies before a date");
		System.out.println("7. Save");
		System.out.println("8. Exit");
	}
	
	/**
	 * Outputs a list of movies with coming and showing dates
	 */
	public static void displayMovies() {
		
	}
	
	/**
	 * Allows the user to add a movie to the "coming" list of movies
	 */
	public static void addMovie() {
		
	}
	
	/**
	 * Allows the user to edit a movie
	 */
	public static void editMovie(String movieName, int action, ArrayList<Movie> comingMovies) {
		
		switch (action) {
		case 1: // Editing release dates
			break;
		case 2: // Editing movie descriptions
			break;
		}
		
	}
	
	/**
	 * Start showing movies with a given release date
	 */
	public static void showMovies() {
		
	}
	
	/**
	 * Get the number of movies "coming" with a release date earlier then a given date
	 */
	public static int numOfComingMovies() {
		return 1;
	}
	
	/**
	 * Save the new changes to an output file
	 */
	public static void save() {
		
	}
}
