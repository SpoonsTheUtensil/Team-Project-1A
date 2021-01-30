package movieManagementSystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import movieManagementSystem.Movie.Status;

public class MovieManagementSystem {
	
	//Opening Java modules
	static Scanner userInput = new Scanner(System.in);
	//static FileOutputStream outputFile = new FileOutputStream("output.txt");
	//static PrintWriter writer = new PrintWriter(outputFile);
	
	public static void main(String[] args) throws IOException {
		
		boolean runProgram = true;
		int optionChoice = 0;
		
		FileInputStream inputFile;
		
		// ArrayList for each separate movie list
		ArrayList<Movie> moviesShowing = new ArrayList<>();
		ArrayList<Movie> moviesComing = new ArrayList<>();

		System.out.println("What is the name of the file you'd like to test? (.txt file no need to add extension)");
		String testCaseFile = userInput.next();
		
		/**
		 * Input File Format
		 * {Movie Name} {Recieved Date} {Movie Description} {Released Date} {Movie Status}
		 * Dates are broken down further in the format like so ({Month} {Day} {Year})
		 * Example Input Movie
		 * Iron Man 5 13 2018 A cool super hero movie 5 20 2018 recieved
		 */
		inputFile = new FileInputStream(testCaseFile + ".txt");
		
		Scanner fileReader = new Scanner(inputFile).useDelimiter(",");
		
		while (fileReader.hasNext()) {
			Movie newMovie = new Movie();
			newMovie.setMovieTitle(fileReader.next());
			int m = fileReader.nextInt();
			int d = fileReader.nextInt();
			long y = fileReader.nextLong();
			Date recievedDate = new Date(m, d, y);
			newMovie.setMovieRecieveDate(recievedDate);
			newMovie.setMovieDesc(fileReader.next());
			m = fileReader.nextInt();
			d = fileReader.nextInt();
			y = fileReader.nextLong();
			Date releasedDate = new Date(m, d, y);
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
			System.out.println();
			printMenu();
			optionChoice = userInput.next().charAt(0);
			
			switch (optionChoice) {
			case '1':
				displayMovies(moviesShowing, moviesComing);
				break;
			case '2':
				addMovie();
				break;
			case '3':
				editMovie(moviesComing);
				break;
			case '4':
				showMovies();
				break;
			case '5':
				System.out.print(numOfComingMovies(moviesComing));
				break;
			case '6':
				//save(moviesComing, moviesShowing);
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
		System.out.println("Select an option: ");
		System.out.println("1. Display movies");
		System.out.println("2. Add movie");
		System.out.println("3. Edit movie release date or decription");
		System.out.println("4. Start showing movies in theaters");
		System.out.println("5. Number of movies before a date");
		System.out.println("6. Save");
		System.out.println("7. Exit");
	}
	
	/**
	 * Outputs a list of movies with coming and showing dates
	 */
	public static void displayMovies(ArrayList<Movie> showing, ArrayList<Movie> coming) {
		//Display "showing" movies
		System.out.println("Movies being shown: ");
		for (Movie movie : showing) {
			System.out.println(movie);
		}
		
		//Display "coming" movies
		System.out.println("Movies coming soon: ");
		for (Movie movie : coming) {
			System.out.println(movie);
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
	public static void editMovie(ArrayList<Movie> coming) {
		
		System.out.println("What is the name of the movie you'd like to edit?");
		String movieName = userInput.nextLine();
		for (Movie movie : coming) {
			if (movie.getMovieTitle() == movieName) {
				System.out.println("Would you like to edit the release date(1) or description(2)?");
				int editAction = userInput.nextInt();
				switch (editAction) {
					case 1:
						System.out.println("Enter the new release date for " + movieName + "(MM/DD/YYYY");
						int releaseMonth = userInput.nextInt();
						int releaseDay = userInput.nextInt();
						long releaseYear = userInput.nextLong();
						Date newReleaseDate = new Date(releaseMonth, releaseDay, releaseYear);
						movie.setMovieReleaseDate(newReleaseDate);
						break;
					case 2:
						System.out.println("Enter the new description for " + movieName);
						String newDesc = userInput.next();
						movie.setMovieDesc(newDesc);
						break;
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
	 */
	public static int numOfComingMovies(ArrayList<Movie> comingMovies) {
		return comingMovies.size();
	}
	
	/**
	 * Save the new changes to an output file
	 */
	/*
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
	*/
}
