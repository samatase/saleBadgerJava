package app.saleBadger.model;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import app.saleBadger.authentication.UserAuthentication;
import app.saleBadger.model.constraints.EmailIsValid;

@Document(collection = "users")
public class User {

	@Id
	private String username;
	@Size(min = 6, message = "{user.wrong.password}")
	private String password;
	@NotBlank(message = "{user.wrong.firstname}")
	private String firstName;
	@NotBlank(message = "{user.wrong.lastname}")
	private String lastName;
	@EmailIsValid
	private String email;
	@NotNull(message = "{user.wrong.contact}")
	@Valid
	private Contact contact;
	private Date dateCreated;
	private Date dateModified;
	private String role;
	
//	@DBRef
//	private List<Product> products;
	
	public User() {
	}

	public User(String username, String password, String email, String role,
			String firstName, String lastName, Contact contact) {
		super();
		this.username = username;
		this.setPassword(password);
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.contact = contact;
		this.dateCreated = new Date();
		this.dateModified = new Date();
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = UserAuthentication
				.getSaltedHashPassword(password);
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}
	
	public String getRole(){
		return role;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDateCreated() {
		this.dateCreated = new Date();
	}
	
	public void updateDateModified() {
		this.dateModified = new Date();
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
