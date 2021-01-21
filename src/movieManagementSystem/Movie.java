package movieManagementSystem;

import java.time.LocalDate;

public class Movie {

	enum Status
	{
		RECEIVED,
		RELEASED
	}
	
	String movie_name, movie_description;
	LocalDate movie_releaseDate, movie_receiveDate;
	Status movie_status;
	
	public Movie() {
		
		movie_name = "";
		movie_description = "";
		movie_releaseDate = null;
		movie_receiveDate = null;
		movie_status = null;
		
	}
	
	public Movie(String name, String description) {
		
	}
	
}
