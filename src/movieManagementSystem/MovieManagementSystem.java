package movieManagementSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import movieManagementSystem.Movie.Status;

public class MovieManagementSystem {
	
	//Opening Java modules
	static Scanner userInput = new Scanner(System.in);
	static FileOutputStream outputFile;
	static PrintWriter writer;
	
	public static void main(String[] args) throws IOException, ParseException{
		
		boolean runProgram = true;
		int optionChoice = 0;
		
		FileInputStream inputFile;
		outputFile = new FileOutputStream("output.txt");
		writer = new PrintWriter(outputFile);
		
		// ArrayList for each separate movie list
		ArrayList<Movie> moviesShowing = new ArrayList<>();
		ArrayList<Movie> moviesComing = new ArrayList<>();

		System.out.println("What is the name of the file you'd like to test? (.txt file no need to add extension)");
		String testCaseFile = userInput.next();
		
		/**
		 * Input File Format
		 * {Movie Name} {Received Date} {Movie Description} {Released Date} {Movie Status}
		 * Dates are broken down further in the format like so ({Month} {Day} {Year})
		 * Example Input Movie
		 * Iron Man 5 13 2018 A cool super hero movie 5 20 2018 received
		 */

		File f = new File(testCaseFile + ".txt");
		
		while (!f.exists()) {
			System.out.println("File does not exist. Please try again.");
			testCaseFile = userInput.next();
			f = new File(testCaseFile + ".txt");
		}
		
		inputFile = new FileInputStream(f);
		
		Scanner fileReader = new Scanner(inputFile).useDelimiter(",");
		
		while (fileReader.hasNext()) {
			Movie newMovie = new Movie();
			newMovie.setMovieTitle(fileReader.next());
			String recievedInput = fileReader.next();
			SimpleDateFormat recievedSimplified = new SimpleDateFormat("yyyy-MM-dd");
			Date recievedDate = recievedSimplified.parse(recievedInput);
			newMovie.setMovieRecieveDate(recievedDate);
			newMovie.setMovieDesc(fileReader.next());
			String releasedInput = fileReader.next();
			SimpleDateFormat releasedSimplified = new SimpleDateFormat("yyyy-MM-dd");
			Date releasedDate = releasedSimplified.parse(releasedInput);
			newMovie.setMovieReleaseDate(releasedDate);
			String status = fileReader.nextLine().replace(",", "");
			switch (status) {
				case "recieved":
					newMovie.setMovieStatus(Status.RECEIVED);
					moviesComing.add(newMovie);
					break;
				case "released":
					newMovie.setMovieStatus(Status.RELEASED);
					moviesShowing.add(newMovie);
					break;
			}
		}
		
		fileReader.close();

		while (runProgram) {
			printMenu();
			optionChoice = userInput.next().charAt(0);
			
			switch (optionChoice) {
			case '1':
				displayMovies(moviesShowing, moviesComing);
				break;
			case '2':
				addMovie(moviesComing);
				break;
			case '3':
				editMovie(moviesComing);
				break;
			case '4':
				showMovies();
				break;
			case '5':
				System.out.print(numOfComingMovies(moviesComing) + " movies are showing before the given date.");
				break;
			case '6':
				save(moviesComing, moviesShowing);
				break;
			case '7':
				userInput.close();
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
		System.out.println("\n#   -----MENU----- ");
		System.out.println("1 - Display movies"
				+ "\n2 - Add movie"
				+ "\n3 - Edit movie release date or decription"
				+ "\n4 - Start showing movies in theaters"
				+ "\n5 - Number of movies before a date"
				+ "\n6 - Save"
				+ "\n7 - Exit");
	}
	
	/**
	 * Outputs a list of movies with coming and showing dates
	 */
	public static void displayMovies(ArrayList<Movie> showing, ArrayList<Movie> coming) {
		//Display "showing" movies
		System.out.println("\nMovies being shown: ");
		for (Movie movie : showing) {
			System.out.println("-" + movie);
		}
		
		//Display "coming" movies
		System.out.println("\nMovies coming soon: ");
		for (Movie movie : coming) {
			System.out.println("-" + movie);
		}
	}
	
	/**
	 * Allows the user to add a movie to the "coming" list of movies
	 */
	public static void addMovie(ArrayList<Movie> coming) throws ParseException{
		userInput.nextLine();
		System.out.println("What's the name of the movie you would like to add?");
		String movieName = userInput.nextLine();
		boolean movieExists = false;
		
		for (Movie movie : coming) {
			if (movieName.equals(movie.getMovieTitle())) {
				movieExists = true;
				break;
			}
			
			}
		if (!movieExists) {
			Movie newMovie = new Movie();
			newMovie.setMovieTitle(movieName);
			
			//Add exception if movie is already in the list
			//userInput.nextLine();
			System.out.println("What is the recieving date of the movie? Ex.(yyyy-MM-dd)");
			String recieveDateInput = userInput.nextLine();
			SimpleDateFormat recievedSimplified = new SimpleDateFormat("yyyy-MM-dd");
			Date recievedDate = recievedSimplified.parse(recieveDateInput);
			newMovie.setMovieRecieveDate(recievedDate);
			
			//Add "throws exception" if received date is invalid
			System.out.println("What is the movie's description?");
			String newMovieDesc = userInput.nextLine();
			newMovie.setMovieDesc(newMovieDesc);
				
			System.out.println("What is the release date of the movie? Ex. (yyyy-MM-dd");
			String releaseDateInput = userInput.nextLine();
			SimpleDateFormat releaseSimplified = new SimpleDateFormat("yyyy-MM-dd");
			Date releasedDate = releaseSimplified.parse(releaseDateInput);
			newMovie.setMovieReleaseDate(releasedDate);
				
			//Add exception if movie's release date is earlier than or equal to the receive date
			if (releasedDate.before(recievedDate) || (releasedDate.equals(recievedDate))) {
				System.out.print("Released date cannot be before recieived date.");
				return;
			}
				
			//Add "throws exception" if released date is invalid or earlier than received date
			System.out.println("Is the movie recieved enter (1) | Is the movie released enter (2)");
			int addMovieAction = userInput.nextInt();
			switch (addMovieAction) {
				case 1:
					newMovie.setMovieStatus(Status.RECEIVED);
					coming.add(newMovie);
					return;
				case 2:
					newMovie.setMovieStatus(Status.RELEASED);
					coming.add(newMovie);
					return;
				}
			}
		else {
			System.out.println("'" + movieName + "'" + " already exists.");
		}
	}
	
	/**
	 * Allows the user to edit a movie
	 */
	public static void editMovie(ArrayList<Movie> coming) throws ParseException{
		
		System.out.println("What is the name of the movie you'd like to edit?");
		String movieName = userInput.next();
		for (Movie movie : coming) {
			if (movie.getMovieTitle().equals(movieName)) {
				System.out.println("To edit the release date enter (1) | To edit the description enter (2)");
				int editAction = userInput.nextInt();
				switch (editAction) {
					case 1:
						userInput.nextLine();
						System.out.println("Enter the new release date (yyyy-MM-dd): ");
						String releasedInput = userInput.nextLine();
						SimpleDateFormat releasedSimplified = new SimpleDateFormat("yyyy-MM-dd");
						Date newReleasedDate = releasedSimplified.parse(releasedInput);
						System.out.print(movie.getMovieRelease());
						movie.setMovieReleaseDate(newReleasedDate);
						System.out.print(movie.getMovieRelease());
						return;
					case 2:
						userInput.nextLine();
						System.out.print("Enter the new description for " + movieName);
						String newDesc = userInput.nextLine();
						movie.setMovieDesc(newDesc);
						return;
				}
			}
			else {
				System.out.println("The movie is not in the coming list!");
				break;
			}
		}
	}
	
	/**
	 * Start showing movies with a given release date
	 */
	public static void showMovies() {
		
	}
	
	/**
	 * Get the number of movies "coming" with a release date earlier then a given date
	 * @throws ParseException 
	 */
	public static int numOfComingMovies(ArrayList<Movie> comingMovies) throws ParseException{ 
		userInput.nextLine();
		System.out.println("Enter a release date (YYYY-MM-DD): ");
		String releasedInput = userInput.nextLine();
		SimpleDateFormat releasedSimplified = new SimpleDateFormat("yyyy-MM-dd");
		Date userDate = releasedSimplified.parse(releasedInput);
		
		int numMovies = 0;
	   
		for (Movie movie : comingMovies) {
			
			if (movie.getMovieRelease().before(userDate)) {
				numMovies++;
			}
		}
		
		
		return numMovies;
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
		writer.close();
	}
}
