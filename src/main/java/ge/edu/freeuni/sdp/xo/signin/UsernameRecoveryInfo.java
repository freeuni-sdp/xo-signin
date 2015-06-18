package ge.edu.freeuni.sdp.xo.signin;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsernameRecoveryInfo {
	@XmlElement
	public String email;

	@Override
	public String toString() {
		return "E-mail: " + email;
	}

}
