package com.tut;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.annotations.Generated;

@Entity(name = "Student_Address")
public class Address 
{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "address_Id")
	private int addressId;
	
	@Column(length = 50 , name = "Street")
	private String street;
	
	@Column(length = 100 , name = "City")
	private String city;
	
	@Column(name = "is_open")
	private boolean isOpen;
	
	@Transient			//do not create column in the database table
	private double x;

	@Column(name = "added_date")
	@Temporal(TemporalType.DATE)		//.TIME //DATETIME
	private Date addedDate;
	
	@Lob								//BLOB
	private byte [] image;








	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", street=" + street + ", city=" + city + ", isOpen=" + isOpen
				+ ", x=" + x + ", addedDate=" + addedDate + ", image=" + Arrays.toString(image) + "]";
	}
	
	
	
	

}
