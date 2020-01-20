package dockerdemo.example.dockerdemo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable{
	int homeNumber;
	String city;
	String state;
	
	public Address() {
		super();
	}

	public Address(int homeNumber, String city, String state) {
		super();
		this.homeNumber = homeNumber;
		this.city = city;
		this.state = state;
	}
	
	public int getHomeNumber() {
		return homeNumber;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	
}
