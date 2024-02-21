package mediaRentalManager;

public class Movie extends Media{
	private String rating;
	
	public Movie(String title, int copiesAvailable, String rating) {
		setAvailability(copiesAvailable);
		this.rating = rating;
		setTitle(title);
	}
	
	public String toString() {
		String ans;
		
		ans = "Title: " + getTitle() + ", Copies Available: " + getAvailability() 
		+ ", Rating: " + rating;
		
		return ans;
	}

	public String getRating() {
		return rating;
	}
	
	

}
