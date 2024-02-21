package mediaRentalManager;

public class Media implements Comparable<Media>{
	private String title;
	private int copiesAvailable;
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setAvailability(int num) {
		this.copiesAvailable = num;
	}
	
	public int getAvailability() {
		return copiesAvailable;
	}
	
	public int compareTo(Media media) {
		return this.title.compareTo(media.title);
	}
}
