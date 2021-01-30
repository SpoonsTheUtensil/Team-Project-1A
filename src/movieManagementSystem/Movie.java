package movieManagementSystem;

public class Movie {

	// Enumeration structure to have constants for movies' status
	enum Status
	{
		RECEIVED,
		RELEASED
	}
	
	// Initial variable declaration
	String movie_name;
	String movie_description;
	Date movie_releaseDate;
	Date movie_receiveDate;
	Status movie_status;
	
	// Default constructor
	public Movie() {}
	
	// Constructor allowing for some variable to be initialized with the object
	public Movie(String name, String description) {
		movie_name = name;
		movie_description = description;
	}

	// Setter method for changing the movies' name to something new
	public void setMovieTitle(String title) {
		movie_name = title;
	}
	
	// Setter method for changing the movies' description to something new
	public void setMovieDesc(String desc) {
		movie_description = desc;
	}
	
	// Setter method for changing the movies' release date to something new
	public void setMovieReleaseDate(Date date) {
		movie_releaseDate = date;
	}
	
	// Setter method for changing the movies' received date to something new
	public void setMovieRecieveDate(Date date) {
		movie_receiveDate = date;
	}
	
	// Setter method for changing the movies' status
	public void setMovieStatus(Status status) {
		movie_status = status;
	}
	

	// Getter method for obtaining the movies' name
	public String getMovieTitle() {
		return movie_name;
	}
	
	// Getter method for obtaining the movies' description
	public String getMovieDesc() {
		return movie_description;
	}
	
	// Getter method for obtaining the movies' released date
	public Date getMovieRelease() {
		return movie_releaseDate;
	}
	
	// Getter method for obtaining the movies' received date
	public Date getMovieReceive() {
		return movie_receiveDate;
	}
	
	// Getter method for obtaining the movies' current status
	public Status getMovieStatus() {
		return movie_status;
	}
	
	// Overrides Object.toString to print the movies' name
	public String toString() {
		return movie_name.trim();
	}
}
