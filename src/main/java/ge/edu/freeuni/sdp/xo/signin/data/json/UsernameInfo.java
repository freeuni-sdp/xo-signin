package ge.edu.freeuni.sdp.xo.signin.data.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsernameInfo {

	@XmlElement
	private String username;

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
		UsernameInfo other = (UsernameInfo) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{\"username\" : \"" + username + "\"}";
	}

}
