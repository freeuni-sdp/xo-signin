package ge.edu.freeuni.sdp.xo.signin.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SignupInfo {
	@XmlElement
	public String email;

	@XmlElement
	public String username;

	@XmlElement
	public String password;

	@Override
	public String toString() {
		return "E-mail: " + email + ", ID: " + username + ", PWD: " + password;
	}

}
