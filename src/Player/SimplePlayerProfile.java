package player;

import java.awt.Image;

public class SimplePlayerProfile {
	String name;
	int rank;
	Image profilePic;
	public SimplePlayerProfile(String name, int rank/*, Image profpic*/) {
		this.name = name;
	}
	
	public String getUsername() {
		return name;
	}
	
	public int getrank() {
		return rank;
	}
	
	public String toString() {
		return name + "," + rank;
	}
}
