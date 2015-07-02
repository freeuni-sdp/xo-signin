package ge.edu.freeuni.sdp.xo.signin.data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Util {

	/**
	 * Hashes given text with SHA encryption algorithm.
	 * 
	 * @param email
	 *            Any String.
	 * @return Returns hashed text.
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateToken(String email, String username) throws NoSuchAlgorithmException {
		MessageDigest md;
		md = MessageDigest.getInstance("SHA");
		md.update((email + username).getBytes());
		return hexToString(md.digest());
	}

	/*
	 * Given a byte[] array, produces a hex String, such as "234a6f". with 2
	 * chars for each byte in the array. (provided code)
	 */
	private static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff; // remove higher bits, sign
			if (val < 16)
				buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}

}