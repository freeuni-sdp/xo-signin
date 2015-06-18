package ge.edu.freeuni.sdp.xo.signin.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserInfo {
	@XmlElement
	public String email;

	@XmlElement
	public String username;

	@Override
	public String toString() {
		return "E-mail: " + email + ", ID: " + username;
	}

}
