package login.model;

public class Utente {
	
	private String firstname;
	private String lastname;
	private String city;
	private String birth;
	private String gender;
	private String mail;
	private String role;
	private String username;
	private String password;
	
	public Utente() {
		
	}
	
	public Utente(String firstname, String lastname, String city, String birth, String gender, String mail, String role, String username, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.birth = birth;
		this.setGender(gender);
		this.mail = mail;
		this.role = role;
		this.username = username;
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
