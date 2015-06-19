package ge.edu.freeuni.sdp.xo.signin.data.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmailInfo {

	@XmlElement
	private String email;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		EmailInfo other = (EmailInfo) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "{\"email\" : \"" + email + "\"}";
	}

}
