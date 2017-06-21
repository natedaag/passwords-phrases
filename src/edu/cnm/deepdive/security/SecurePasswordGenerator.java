package edu.cnm.deepdive.security;

import java.security.SecureRandom;

/**
 * Invokes superclass  constructor in a manner that creates a cryptographically secure
 * password.
 * 
 * @author natedaag
 */
public class SecurePasswordGenerator extends PasswordGenerator {

	/**
	 * Invokes superclass constructor.  
	 */
	public SecurePasswordGenerator() {
		super();
		rng = new SecureRandom();

	}

	/**
	 * Invokes {@link PasswordGenerator#PasswordGenerator(int, int) superclass constructor}
	 * 
	 * @param minLength
	 * @param maxLength
	 */
	public SecurePasswordGenerator(int minLength, int maxLength) {
		super(minLength, maxLength);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Invokes {@link PasswordGenerator#PasswordGenerator(int, int, boolean, 
	 * boolean, boolean, boolean, boolean) constructor}
	 * 
	 * @param minLength
	 * @param maxLength
	 * @param includeUpperCase
	 * @param includeLowerCase
	 * @param includeNumbers
	 * @param includePunctuation
	 * @param excludeAmbiguous
	 */
	public SecurePasswordGenerator(int minLength, int maxLength, boolean includeUpperCase, boolean includeLowerCase,
			boolean includeNumbers, boolean includePunctuation, boolean excludeAmbiguous) {
		super(minLength, maxLength, includeUpperCase, includeLowerCase, includeNumbers, includePunctuation,
				excludeAmbiguous);
		// TODO Auto-generated constructor stub
	}

}
