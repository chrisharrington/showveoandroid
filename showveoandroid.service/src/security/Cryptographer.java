package security;

import service.security.ICryptographer;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Encrypts and decrypts values.
 */
public class Cryptographer implements ICryptographer {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Encrypts a value using an SHA1 hash.
	 * @param plaintext The string to hash.
	 * @return The hashed string.
	 */
	public String sha256hash(String plaintext) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] sha1hash = new byte[40];
			md.update(plaintext.getBytes("ISO646-US"), 0, plaintext.length());
			sha1hash = md.digest();
		return convertToHex(sha1hash);
		} catch (NoSuchAlgorithmException e) {
			return "";
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	//------------------------------------------------------------------------------------------------------------------
	//	Private Methods

	/**
	 * Converts a byte array to a hex string.
	 * @param data The array to convert.
	 * @return The resulting hex string.
	 */
	private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
		for (byte aData : data) {
			int halfbyte = (aData >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = aData & 0x0F;
			} while (two_halfs++ < 1);
		}
        return buf.toString();
    }

}
