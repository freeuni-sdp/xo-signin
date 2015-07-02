package ge.edu.freeuni.sdp.xo.signin.data.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserInfo {

	@XmlElement
	private String email;

	@XmlElement
	private String username;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		UserInfo other = (UserInfo) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "{\"email\" : \"" + email + "\", \"username\" : \"" + username + "\"}";
	}

}
