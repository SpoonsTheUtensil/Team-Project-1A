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
	
	public Movie() {} // Default constructor
	
	public Movie(String name, String description) {
		movie_name = name;
		movie_description = description;
	}
	
	public void setMovieTitle(String title) {
		movie_name = title;
	}
	
	public void setMovieDesc(String desc) {
		movie_description = desc;
	}
	
	public void setMovieReleaseDate(LocalDate date) {
		movie_releaseDate = date;
	}
	
	public void setMovieRecieveDate(LocalDate date) {
		movie_receiveDate = date;
	}
	
	public void setMovieStats(Status status) {
		movie_status = status;
	}
	
	public String getMovieTitle() {
		return movie_name;
	}
	
	public String getMovieDesc() {
		return movie_description;
	}
	
	public LocalDate getMovieRelease() {
		return movie_releaseDate;
	}
	
	public LocalDate getMovieReceive() {
		return movie_receiveDate;
	}
	
	public Status getMovieStatus() {
		return movie_status;
	}
}
