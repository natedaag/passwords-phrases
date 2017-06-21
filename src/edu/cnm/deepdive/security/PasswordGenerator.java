/*
 * PasswordGenerator.java
 */

package edu.cnm.deepdive.security;

import java.util.Random;

/**
 * Implementation of a <em>simple</em> password generator. This includes support
 * for elementary character-set-based rules(optional inclusion of specified sets
 * and exclusion of a small number of ambiguous characters),, but not (yet)
 * regular expressions.
 * 
 * @author natedaag
 * @version 1.0
 */
public class PasswordGenerator {

	/**
	 * Punctuation characters that may optionally be included in the password.
	 */
	public static final String PUNCTUATION = "!@#$%&*,.";
	/**
	 * Ambiguous character that may optionally be excluded from the password.
	 */
	public static final String AMBIGUOUS = "[O1]";
	private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWERCASE = UPPERCASE.toLowerCase();
	private static final String NUMBERS = "0123456789";
	/** Generates random number to pick password. */
	protected Random rng = new Random();

	private char[] pool = null;
	private int minLength = 6;
	private int maxLength = 12;
	private boolean includeUpperCase = true;
	private boolean includeLowerCase = true;
	private boolean includeNumbers = true;
	private boolean includePunctuation = false;
	private boolean excludeAmbiguous = true;

	/**
	 * Instantiate PasswordGenerator class for testing password generation.
	 * 
	 * @param args
	 *            Command-line parameters for password generation options.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	} // end main method

	/**
	 * Initialize with default values.
	 */
	public PasswordGenerator() {
		rng = new Random();
	}

	/**
	 * Initialize generator for password of length in the range specified.
	 * 
	 * @param minLength
	 *            Minimum length of password to be generated.
	 * @param maxLength
	 *            Maximum length of password to be generated.
	 */
	public PasswordGenerator(int minLength, int maxLength) {
		this();
		this.minLength = minLength;
		this.maxLength = maxLength;
	}

	/**
	 * Initialize generator with specialized options.
	 * 
	 * @param minLength				Minimum length of password to be generated
	 * @param maxLength				Maximum length of password to be generated
	 * @param includeUpperCase 		Include upper-case characters in pool.
	 * @param includeLowerCase		Include lower-case characters in pool.
	 * @param includeNumbers		Include numbers in pool.
	 * @param includePunctuation	Include a set of non-alphanumeric characters.
	 * @param excludeAmbiguous		Exclude ambiguous characters. This option is applied
	 *            					<em>after</em> inclusion of all selected character 
	 *            					sets.
	 */
	public PasswordGenerator(int minLength, int maxLength, boolean includeUpperCase, boolean includeLowerCase,
			boolean includeNumbers, boolean includePunctuation, boolean excludeAmbiguous) {
		this(minLength, maxLength);
		this.includeUpperCase = includeUpperCase;
		this.includeLowerCase = includeLowerCase;
		this.includeNumbers = includeNumbers;
		this.includePunctuation = includePunctuation;
		this.excludeAmbiguous = excludeAmbiguous;
	}

	/**
	 * Return maximum password length.
	 * 
	 * @return maximum password length.
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * Sets the maximum password length.
	 * 
	 * @param maxLength		the maximum length to set
	 */
	protected void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * Return the minimum password length.
	 * 
	 * @return minimum password length.
	 */
	public int getMinLength() {
		return minLength;
	}

	/**
	 * Sets the minimum password length.
	 * 
	 * @param 		minimum password length.
	 */
	protected void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	private void setupPool() {
		if (pool == null) {
			StringBuilder builder = new StringBuilder();
			if (includeLowerCase) {
				builder.append(LOWERCASE);
			}
			if (includeUpperCase) {
				builder.append(UPPERCASE);
			}
			if (includeNumbers) {
				builder.append(NUMBERS);
			}
			if (includePunctuation) {
				builder.append(PUNCTUATION);
			}
			String work = builder.toString();
			if (excludeAmbiguous) {
				work.replaceAll(AMBIGUOUS, "");
			}
			pool = work.toCharArray();
		}
	}

	/**
	 * Generates the characters that will comprise the code.
	 * 
	 * @return		password.
	 */
	public String generate() {
		setupPool();
		int passwordLength = minLength + rng.nextInt(maxLength - minLength + 1);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < passwordLength; i++) {
			char selection = pool[rng.nextInt(pool.length)];
			builder.append(selection);
		}
		return builder.toString();
	}

} // end PasswordGenerator