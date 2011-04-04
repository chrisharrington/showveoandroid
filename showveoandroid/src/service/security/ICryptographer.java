package service.security;

/**
 * Defines a class used to encrypt and decrypt values.
 */
public interface ICryptographer {

	//------------------------------------------------------------------------------------------------------------------
	//	Public Methods

	/**
	 * Encrypts a value using an SHA1 hash.
	 * @param plaintext The string to hash.
	 * @return The hashed string.
	 */
	String sha256hash(String plaintext);

}
