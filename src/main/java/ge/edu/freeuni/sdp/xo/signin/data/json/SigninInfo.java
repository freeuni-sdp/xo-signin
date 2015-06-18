package ge.edu.freeuni.sdp.xo.signin.data.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SigninInfo {

	@XmlElement
	private String email;

	@XmlElement
	private String username;

	@XmlElement
	private String password;

	/**
	 * @return E-mail address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            e-mail address to be set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return username to be returned
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            username to be set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password to be returned
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            password to be set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
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
		SigninInfo other = (SigninInfo) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "E-mail: " + email + ", ID: " + username + ", PWD: " + password;
	}

}
