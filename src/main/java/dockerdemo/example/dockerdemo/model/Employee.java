package dockerdemo.example.dockerdemo.model;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import dockerdemo.example.dockerdemo.util.CustomizeAddressSerializer;

@Entity // we shoud have a key
//@JsonPropertyOrder({"address", "name"})
public class Employee implements Serializable {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	@Embedded
	@JsonSerialize(using = CustomizeAddressSerializer.class)
	@JsonDeserialize
	private Address address;

	public Employee() {
		super();
	}

	public Employee(String name, Address address) {
		super();
		this.name = name;
		this.address = address;
	}
	
	

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}



