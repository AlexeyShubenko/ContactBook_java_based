package com.test.javaproject.mvc.domains;

import com.test.javaproject.mvc.dto.ContactDto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="contact")
public class Contact implements Serializable{

	@Id
	@Column(name="contactid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long contactId;

	@Column(name="firstname", nullable=false)
	private String firstName;

	@Column(name="lastname", nullable=false)
	private String lastName;

	@Column(name="middlename", nullable=false)
	private String middleName;

	@Column(name="mob_phone_number", nullable=false)
	private String mobPhoneNumber;
	
	@Column(name="home_phone_number")
	private String homePhoneNumber;
	
	@Column(name="address")
	private String address;

	@Column(name="email")
	private String email;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	public Contact(){}
	
	public Contact(Long contactId, String firstName, String lastName, String middleName, String mobPhoneNumber,
				   String homePhoneNumber, String address, String email) {
		super();
		this.contactId = contactId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.mobPhoneNumber = mobPhoneNumber;
		this.homePhoneNumber = homePhoneNumber;
		this.address = address;
		this.email = email;
	}

	public static class Builder{

		private Contact contact = new Contact();

		public Builder setContactId(ContactDto contactDto){
			contactDto.setContactId(contactDto.getContactId());
			return this;
		}

		public Builder setFirstName(ContactDto contactDto){
			contact.setFirstName(contactDto.getFirstName());
			return this;
		}

		public Builder setLastName(ContactDto contactDto){
			contact.setLastName(contactDto.getLastName());
			return this;
		}

		public Builder setMiddleName(ContactDto contactDto){
			contact.setMiddleName(contactDto.getMiddleName());
			return this;
		}

		public Builder setMobPhoneNumber(ContactDto contactDto){
			contact.setMobPhoneNumber(contactDto.getMobPhoneNumber());
			return this;
		}

		public Builder setHomePhoneNumber(ContactDto contactDto){
			contact.setHomePhoneNumber(contactDto.getHomePhoneNumber());
			return this;
		}

		public Builder setEmail(ContactDto contactDto){
			contact.setAddress(contactDto.getAddress());
			return this;
		}

		public Builder setAddress(ContactDto contactDto){
			contact.setEmail(contactDto.getEmail());
			return this;
		}

		public Contact build(){
			return contact;
		}

	}

	public Long getContactId() {
		return contactId;
	}	
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastname) {
		this.lastName = lastname;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middlename) {
		this.middleName = middlename;
	}
	public String getMobPhoneNumber() {
		return mobPhoneNumber;
	}
	public void setMobPhoneNumber(String mobPhoneNumber) {
		this.mobPhoneNumber = mobPhoneNumber;
	}
	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}
	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Contact{" +
				"contactId=" + contactId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", middleName='" + middleName + '\'' +
				", mobPhoneNumber='" + mobPhoneNumber + '\'' +
				", homePhoneNumber='" + homePhoneNumber + '\'' +
				", address='" + address + '\'' +
				", email='" + email + '\'' +
				", user=" + user.getUserId() +
				'}';
	}
}
