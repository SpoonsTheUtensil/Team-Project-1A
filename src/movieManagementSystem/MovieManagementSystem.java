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
	
	// Opening Java modules
	static Scanner userInput = new Scanner(System.in);
	static FileOutputStream outputFile;
	static PrintWriter writer;
	
	// Main Loop
	public static void main(String[] args) throws IOException, ParseException {
		
		// Initialization for variables needed
		boolean runProgram = true;
		int optionChoice = 0;
		
		FileInputStream inputFile;
		// Output file and writer declared global for ease of use but for an unknown reason to use
		// didn't like being initialized there as well
		outputFile = new FileOutputStream("output.txt");
		writer = new PrintWriter(outputFile);
		
		// ArrayList for each separate movie list
		ArrayList<Movie> moviesShowing = new ArrayList<>();
		ArrayList<Movie> moviesComing = new ArrayList<>();

		// Asking the use which input file they'd like to test
		System.out.println("What is the name of the file you'd like to test? (.txt file no need to add extension)");
		String testCaseFile = userInput.next();
		
		/**
		 * Input File Format
		 * {Movie Name},{Received Date},{Movie Description},{Released Date},{Movie Status}
		 * Dates are broken down further in the format like so ({Year}-{Month}-{Day})
		 * Example Input Movie
		 * Iron Man 5 13 2018 A cool super hero movie 5 20 2018 received
		 */

		// Handle exception if the file that was inputed was not found
		// Continues to ask until a correct file is found
		File f = new File(testCaseFile + ".txt");
		while (!f.exists()) {
			System.out.println("File does not exist. Please try again.");
			testCaseFile = userInput.next();
			f = new File(testCaseFile + ".txt");
		}
		
		// Start of the Scanner looking through the file
		inputFile = new FileInputStream(f);
		Scanner fileReader = new Scanner(inputFile).useDelimiter(",");
		
		// Scanner reading through the input file populating the appropriate
		// movie properties until there is nothing else to read through
		while (fileReader.hasNext()) {
			Movie newMovie = new Movie();
			newMovie.setMovieTitle(fileReader.next());
			String recievedInput = fileReader.next();
			SimpleDateFormat recievedToFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date recievedDate = recievedToFormat.parse(recievedInput);
			newMovie.setMovieRecieveDate(recievedDate);
			newMovie.setMovieDesc(fileReader.next());
			String releasedInput = fileReader.next();
			SimpleDateFormat releasedToFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date releasedDate = releasedToFormat.parse(releasedInput);
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

		// Program options loop continuous until user decides to exit
		while (runProgram) {
			printMenu();
			optionChoice = userInput.next().charAt(0);
			
			switch (optionChoice) {
			case '1': // Displays movies coming to theaters and movies already showing in theaters
				displayMovies(moviesShowing, moviesComing);
				break;
			case '2': // Allows the user to add a movie to the coming list of movies
				addMovie(moviesComing);
				break;
			case '3': // Allows the user to edit a movies release date or description
				editMovie(moviesComing);
				break;
			case '4': // Based upon a release date given it displays the movies showing
				showMovies(moviesComing, moviesShowing);
				break;
			case '5': // Based upon a given release date outputs the amount of movies showing before that date
				System.out.print(numOfComingMovies(moviesComing) + " movies are showing before the given date.");
				break;
			case '6': // Saves the movies coming and showing to an output file
				save(moviesComing, moviesShowing);
				break;
			case '7': // Exits the program
				userInput.close();
				System.exit(0);
			default: // Default to let the user know that any other option given here is invalid and to choose a valid one given
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
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyy-MM-dd");
		
		//Display "showing" movies
		System.out.println("| Movies Showing    Release Date |");
		for (Movie movie : showing) {
			String movieDate = dateFormatter.format(movie.getMovieRelease());
			System.out.printf("| %-20s%s |\n", movie, movieDate);
		}
		
		System.out.println();
		
		//Display "coming" movies
		System.out.println("| Movies Coming     Release Date |");
		for (Movie movie : coming) {
			String movieDate = dateFormatter.format(movie.getMovieRelease());
			System.out.printf("| %-20s%s |\n", movie, movieDate);
		}
	}
	
	/**
	 * Allows the user to add a movie to the "coming" list of movies
	 * @throws ParseException
	 */
	public static void addMovie(ArrayList<Movie> coming) throws ParseException {
		
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
			SimpleDateFormat recievedToFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date recievedDate = recievedToFormat.parse(recieveDateInput);
			newMovie.setMovieRecieveDate(recievedDate);
			
			//Add "throws exception" if received date is invalid
			System.out.println("What is the movie's description?");
			String newMovieDesc = userInput.nextLine();
			newMovie.setMovieDesc(newMovieDesc);
				
			System.out.println("What is the release date of the movie? Ex. (yyyy-MM-dd}");
			String releaseDateInput = userInput.nextLine();
			SimpleDateFormat releaseToFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date releasedDate = releaseToFormat.parse(releaseDateInput);
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
	 * Allows the user to edit a movies release date or description
	 * @throws ParseException
	 */
	public static void editMovie(ArrayList<Movie> coming) throws ParseException {
		
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
						SimpleDateFormat releasedToFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date newReleasedDate = releasedToFormat.parse(releasedInput);
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
	 * @throws ParseException
	 */
	public static void showMovies(ArrayList<Movie> coming, ArrayList<Movie> showing) throws ParseException {
		
        //Get date for changing coming movies to showing
        System.out.println("Enter a new showing date: (format: yyyy-MM-dd)");
        userInput.nextLine();
        String newDate = userInput.nextLine();
        SimpleDateFormat newDateToFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date newReleasedDate = newDateToFormat.parse(newDate);
        boolean noDate = false;

        for (Movie movie : new ArrayList<Movie>(coming)) {
            if (newReleasedDate.equals(movie.getMovieRelease())) {
                coming.remove(movie);
                if (showing.contains(movie)) {
                    System.out.print("Movie is already Showing");
                }
                else {
                    noDate = true;
                    showing.add(movie);
                    System.out.println("Movies on date are now showing!");
                }
            }
        }
        if (!noDate) {
            System.out.println("No movies on given release date. Try again!");
        }
    }
	
	/**
	 * Get the number of movies "coming" with a release date earlier then a given date
	 * @throws ParseException 
	 */
	public static int numOfComingMovies(ArrayList<Movie> comingMovies) throws ParseException{
		
		userInput.nextLine();
		System.out.println("Enter a release date (yyyy-MM-dd): ");
		String releasedInput = userInput.nextLine();
		SimpleDateFormat releasedToFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date userDate = releasedToFormat.parse(releasedInput);
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
