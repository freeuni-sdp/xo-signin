package ge.edu.freeuni.sdp.xo.signin;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Token {

	@XmlElement
	public String token;

}
