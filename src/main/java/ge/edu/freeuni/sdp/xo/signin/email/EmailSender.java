package ge.edu.freeuni.sdp.xo.signin.email;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

import ge.edu.freeuni.sdp.xo.signin.data.json.SigninInfo;
import ge.edu.freeuni.sdp.xo.signin.data.json.Token;

public class EmailSender {
	private static final String FROM = "noreply@xo-signin.herokuapp.com";

	public EmailSender() {
	}

	public static void sendActivationEmail(SigninInfo signinInfo, Token token) {
		SendGrid sendgrid = new SendGrid(SendGridInfo.USERNAME, SendGridInfo.PASSWORD);

		SendGrid.Email email = new SendGrid.Email();
		email.addTo(signinInfo.getEmail());
		email.setFrom(FROM);
		email.setSubject("Account Confirmation");
		email.setText("Follow this link to connfirm account: " + "http://xo-webui.herokuapp.com/confirm_account?token="
				+ token.getToken() + "&username=" + signinInfo.getUsername() + "&email=" + signinInfo.getEmail());

		try {
			sendgrid.send(email);
		} catch (SendGridException e) {
			System.err.println(e);
		}
	}

	public static void sendUserInfoEmail(SigninInfo signinInfo) {
		SendGrid sendgrid = new SendGrid(SendGridInfo.USERNAME, SendGridInfo.PASSWORD);

		SendGrid.Email email = new SendGrid.Email();
		email.addTo(signinInfo.getEmail());
		email.setFrom(FROM);
		email.setSubject("Account Information");
		email.setText("Username: " + signinInfo.getUsername() + "\nPassword: " + signinInfo.getPassword());

		try {
			sendgrid.send(email);
		} catch (SendGridException e) {
			System.err.println(e);
		}
	}

	public static void sendUsernameEmail(SigninInfo signinInfo) {
		SendGrid sendgrid = new SendGrid(SendGridInfo.USERNAME, SendGridInfo.PASSWORD);

		SendGrid.Email email = new SendGrid.Email();
		email.addTo(signinInfo.getEmail());
		email.setFrom(FROM);
		email.setSubject("Email Recovery");
		email.setText("Username: " + signinInfo.getUsername());

		try {
			sendgrid.send(email);
		} catch (SendGridException e) {
			System.err.println(e);
		}
	}

}
