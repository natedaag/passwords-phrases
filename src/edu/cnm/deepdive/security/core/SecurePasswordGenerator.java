package edu.cnm.deepdive.security.core;

import java.security.NoSuchAlgorithmException;
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

	}
	
	/**
	 *     Uses secure random number generator, rather than the unsecure rng in super class.
	 */
	@Override
	protected void setupRng() {
		try {
		setRng(SecureRandom.getInstanceStrong());
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException(ex);
		}
	}

}
