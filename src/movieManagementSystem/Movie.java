package movieManagementSystem;

import java.util.Date;

public class Movie {

	enum Status
	{
		RECEIVED,
		RELEASED
	}
	
	static String movie_name;
	static String movie_description;
	static Date movie_releaseDate;
	static Date movie_receiveDate;
	static Status movie_status;
	
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
	
	public void setMovieReleaseDate(Date date) {
		movie_releaseDate = date;
	}
	
	public void setMovieRecieveDate(Date date) {
		movie_receiveDate = date;
	}
	
	public void setMovieStats(Status status) {
		movie_status = status;
	}
	
	public static String getMovieTitle() {
		return movie_name;
	}
	
	public static String getMovieDesc() {
		return movie_description;
	}
	
	public static Date getMovieRelease() {
		return movie_releaseDate;
	}
	
	public static Date getMovieReceive() {
		return movie_receiveDate;
	}
	
	public static Status getMovieStatus() {
		return movie_status;
	}
}
