package mediaRentalManager;

import java.util.ArrayList;
import java.util.Collections;

public class MediaRentalManager implements MediaRentalManagerInt{
	ArrayList<Customer> allCustomers = new ArrayList<>();
	ArrayList<Media> allMedia = new ArrayList<>();

	public void addCustomer(String name, String address, String plan) {
		Customer customer = new Customer(name, address, plan);
		allCustomers.add(customer);
	}

	public void addMovie(String title, int copiesAvailable, String rating) {
		Movie movie = new Movie(title, copiesAvailable, rating);
		allMedia.add(movie);
	}

	public void addAlbum(String title, int copiesAvailable, String artist, String songs) {
		Album album = new Album(title, copiesAvailable, artist, songs);
		allMedia.add(album);
		
	}

	public void setLimitedPlanLimit(int value) {
		Customer.setLimit(value);
		
	}

	public String getAllCustomersInfo() {
		Collections.sort(allCustomers);
		String info = "***** Customers' Information *****\n";
		for(int i =0; i < allCustomers.size(); i++) {
			info += allCustomers.get(i);
			info += "\n";
		}
		return info;
	}

	public String getAllMediaInfo() {
		Collections.sort(allMedia);
		String info = "***** Media Information *****\n";
		for(int i =0; i < allMedia.size(); i++) {
			info += allMedia.get(i);
			info += "\n";
		}
		return info;
	}

	public boolean addToQueue(String customerName, String mediaTitle) {
		for(int i = 0; i < allCustomers.size(); i++) {
			if(allCustomers.get(i).getName().equals(customerName)){
				for(int k = 0; k < allCustomers.get(i).getQueue().size(); k++) {
					if(allCustomers.get(i).getQueue().get(k).equals(mediaTitle)) {
						return false;
					}
				}
				allCustomers.get(i).getQueue().add(mediaTitle);
				return true;
			}
		}
		return false;
	}

	public boolean removeFromQueue(String customerName, String mediaTitle) {
		for(int i = 0; i < allCustomers.size(); i++) {
			if(allCustomers.get(i).getName().equals(customerName)){
				for(int k = 0; k < allCustomers.get(i).getQueue().size(); k++) {
					if(allCustomers.get(i).getQueue().get(k).equals(mediaTitle)) {
						allCustomers.get(i).getQueue().remove(mediaTitle);
						return true;
					}
				}
			}
		}
		return false;
	}

	public String processRequests() {
		Collections.sort(allCustomers);
		String report = "";
		for(int i = 0; i < allCustomers.size(); i++) {
			Customer current = allCustomers.get(i);
			if(current.getPlan().equals("LIMITED")) {
				for(int k = 0; k < current.getQueueNum(); k++) {
					String currentMedia = current.getQueue().get(k);
					for(int j = 0; j < allMedia.size(); j++) {
						if(allMedia.get(j).getTitle().equals(currentMedia)) {
							if(current.getRentedNum() < Customer.getLimit() && allMedia.get(j).getAvailability() > 0) {
								allMedia.get(j).setAvailability(allMedia.get(j).getAvailability() - 1);
								report += "Sending " + currentMedia + " to " + current.getName() + "\n";
								current.getRented().add(currentMedia);
								current.getQueue().remove(k);
								k--;
							}
						}
					}
				}
			}else {
				for(int k = 0; k < current.getQueueNum(); k++) {
					String currentMedia = current.getQueue().get(k);
					for(int j = 0; j < allMedia.size(); j++) {
						if(allMedia.get(j).getTitle().equals(currentMedia)) {
							if(allMedia.get(j).getAvailability() > 0) {
								allMedia.get(j).setAvailability(allMedia.get(j).getAvailability() - 1);
								report += "Sending " + currentMedia + " to " + current.getName() + "\n";
								current.getRented().add(currentMedia);
								current.getQueue().remove(k);
								k--;
							}
						}
					}
				}
			}
		}
		
		return report;
	}

	public boolean returnMedia(String customerName, String mediaTitle) {
		for(int i = 0; i < allCustomers.size(); i++) {
			if(allCustomers.get(i).getName().equals(customerName)) {
				for(int k = 0; k < allCustomers.get(i).getRented().size(); k++) {
					if(allCustomers.get(i).getRented().get(k).equals(mediaTitle)) {
						allCustomers.get(i).getRented().remove(k);
					}
				}
				for(int j = 0; j < allMedia.size(); j++) {
					if(allMedia.get(j).getTitle().equals(mediaTitle)) {
						allMedia.get(j).setAvailability(allMedia.get(j).getAvailability() + 1);
						return true;
					}
				}
			}
		}
		return false;
	}

	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {
		ArrayList<String> matches = new ArrayList<>();
		Collections.sort(allMedia);
		if(title == null && rating == null && artist == null && songs == null) {
			for(int i = 0; i < allMedia.size(); i++) {
				matches.add(allMedia.get(i).getTitle());
			}
		}else if(rating == null && artist == null && songs == null) {
			for(int i = 0; i < allMedia.size(); i++) {
				if(allMedia.get(i).getTitle().equals(title)) {
					matches.add(allMedia.get(i).getTitle());
				}
			}
		}else if(title == null && artist == null && songs == null) {
			for(int i = 0; i < allMedia.size(); i++) {
				if(allMedia.get(i) instanceof Movie) {
					if(((Movie)allMedia.get(i)).getRating().equals(rating)) {
						matches.add(allMedia.get(i).getTitle());
					}
				}
			}
			
		}else if(title == null && rating == null && songs == null) {
			for(int i = 0; i < allMedia.size(); i++) {
				if(allMedia.get(i) instanceof Album) {
					if(((Album)allMedia.get(i)).getArtist().equals(artist)) {
						matches.add(allMedia.get(i).getTitle());
					}
				}
			}
		}else if(title == null && rating == null && artist == null) {
			for(int i = 0; i < allMedia.size(); i++) {
				if(allMedia.get(i) instanceof Album) {
					if(((Album)allMedia.get(i)).getSongs().contains(songs)) {  //(Album)allMedia.get(i)).getSongs().indexOf(songs) != -1
						matches.add(allMedia.get(i).getTitle());
					}
				}
			}
		}
		return matches;
	}

}
