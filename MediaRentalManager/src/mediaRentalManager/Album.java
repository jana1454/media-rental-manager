package mediaRentalManager;

public class Album extends Media{
	private String artist, songs;
	
	public Album(String title, int copiesAvailable, String artist, String songs) {
		setTitle(title);
		setAvailability(copiesAvailable);
		this.artist = artist;
		this.songs = songs;
	}
	
	public String toString() {
		String ans;
		
		ans = "Title: " + getTitle() + ", Copies Available: " + getAvailability() 
		+ ", Artist: " + artist + ", Songs: " + songs;
		
		return ans;
	}
	

	public String getArtist() {
		return artist;
	}

	public String getSongs() {
		return songs;
	}
	
	

}
