package movieManagementSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class main {
	
	//Opening Java modules
	static Scanner userInput = new Scanner(System.in);
	static FileOutputStream outputFile1 = new FileOutputStream("output1.txt");
	static PrintWriter writer = new PrintWriter(outputFile1);	

	public static void main(String[] args) throws IOException {
		
		
		boolean runProgram = true;
		int optionChoice = 0;

		System.out.println("What is the name of the file you'd like to test? (.txt file no need to add extension) ");
		String testCaseFile = userInput.next();
		
		//Opening input stream
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
				System.out.println("Please enter the movie to edit: ");
				String movieName = userInput.next();
				boolean coming = moviesComing.contains(movieName);
				
				if (coming) {
					System.out.println("Enter the new release date for " + movieName);
					String newRelease = userInput.next();
					
					
				}
				
				editMovie(movieName, 1, moviesComing);
				break;
			case '4':
				editMovie(2);
				break;
			case '5':
				showMovies();
				break;
			case '6':
				System.out.print(numOfComingMovies(moviesComing));
				break;
			case '7':
				save(moviesComing, moviesShowing);
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
	public static void displayMovies(ArrayList<Movie> showing, ArrayList<Movie> coming) {
		//Display "showing" movies
		System.out.println("Movies being shown: ");
		for (Movie movie : showing) {
			System.out.println(Movie.getMovieTitle());
		}
		
		//Display "coming" movies
		System.out.println("Movies coming soon: ");
		for (Movie movie : coming) {
			System.out.println(Movie.getMovieTitle());
		}
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
			System.out.println("Enter the new release date for " + movieName + " (dd/MM/yyyy");
			int releaseMonth = userInput.nextInt();
			int releaseDay = userInput.nextInt();
			int releaseYear = userInput.nextInt();
			Date newDate = Date(releaseMonth, releaseDay, releaseYear);
			movieName.setMovieReleaseDate(newDate);
			
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
	public static int numOfComingMovies(ArrayList<Movie> comingMovies) {
		return comingMovies.size();
	}
	
	/**
	 * Save the new changes to an output file
	 */
	public static void save(ArrayList<Movie> comingMovies, ArrayList<Movie> showingMovies) {
		writer.println("Movies coming to theaters: ");
		writer.println("---------------------------");
		//Print coming movies to output file
		for (int i = 0; i < comingMovies.size(); i++ ) {
			writer.println(comingMovies.get(i));
		}
		
		//Print a space between lists
		writer.println("\n");
		
		writer.println("Movies showing in theaters: ");
		writer.println("----------------------------");
		//Print showing movies to output file
		for (int i = 0; i < showingMovies.size(); i++) {
			writer.println(showingMovies.get(i));
		}
	}
	
	//Closing Java modules
	writer.close();
	outputFile1.close();
	scanner.close()
	inputFile1.close();
}
