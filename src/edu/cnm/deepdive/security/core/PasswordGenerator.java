/*
 * PasswordGenerator.java
 */

package edu.cnm.deepdive.security.core;

import java.util.Random;

/**
 * Implementation of a password generator. This includes support
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
	public static final int DEFAULT_PASSWORD_LENGTH = 12;
		
	/**
	 * Ambiguous character that may optionally be excluded from the password.
	 */
	public static final String AMBIGUOUS = "[O1]";
	private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWERCASE = UPPERCASE.toLowerCase();
	private static final String NUMBERS = "0123456789";
	
	/** Generates random number to pick password. */
	
	private Random rng = null;
	private char[] pool = null;
	private int minLength = DEFAULT_PASSWORD_LENGTH;
	private int maxLength = DEFAULT_PASSWORD_LENGTH;
	private boolean upperCaseIncluded = true;
	private boolean lowerCaseIncluded = true;
	private boolean numbersIncluded = true;
	
	private boolean punctuationIncluded = true;
	private boolean ambiguousExcluded = true;
	private String delimiter = "";

	/**
	 * Initialize with default values.
	 */
	public PasswordGenerator() {
		setRng(new Random());
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
	public void setMaxLength(int maxLength) {
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
	 * @param minLength	minimum password length.
	 */
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	private void setupPool() {

			StringBuilder builder = new StringBuilder();
			if (isLowerCaseIncluded()) {
				builder.append(LOWERCASE);
			}
			if (isUpperCaseIncluded()) {
				builder.append(UPPERCASE);
			}
			if (isNumbersIncluded()) {
				builder.append(NUMBERS);
			}
			if (isPunctuationIncluded()) {
				builder.append(PUNCTUATION);
			}
			String work = builder.toString();
			if (isAmbiguousExcluded()) {
				work.replaceAll(AMBIGUOUS, "");
			}
			pool = work.toCharArray();
		}

	/**
	 *     Sets up the random number generator, which is associated with the words in wordList.
	 */
	protected void setupRng() {
		if (rng == null) {
			rng = new Random();
		}
	}

	/**
	 * Generates the characters that will comprise the password.
	 * 
	 * @return		password.
	 */
	public String generate() {
		setupPool();
		setupRng();
		int passwordLength = minLength + getRng().nextInt(maxLength - minLength + 1);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < passwordLength; i++) {
			char selection = pool[getRng().nextInt(pool.length)];
			builder.append(selection);
		}
		return builder.toString();
	}

	/**
	 *     Option to exclude upper case letters.
	 * @return     the upperCaseIncluded - Default setting, included.
	 */
	public boolean isUpperCaseIncluded() {
		return upperCaseIncluded;
	}

	/**
	 *     Sets the upper case included option.
	 * @param upperCaseIncluded the upperCaseIncluded to set
	 */
	public void setUpperCaseIncluded(boolean upperCaseIncluded) {
		this.upperCaseIncluded = upperCaseIncluded;
	}

	/**
	 * @return the lowerCaseIncluded   Option to exclude lower case letters from password.
	 */
	public boolean isLowerCaseIncluded() {
		return lowerCaseIncluded;
	}

	/**
	 * @param lowerCaseIncluded    Option to exclude lower case letters from password.
	 */
	public void setLowerCaseIncluded(boolean lowerCaseIncluded) {
		this.lowerCaseIncluded = lowerCaseIncluded;
	}

	/**
	 * @return the numbersIncluded  Option to exclude numbers from password.
	 */
	public boolean isNumbersIncluded() {
		return numbersIncluded;
	}

	/**
	 * @param numbersIncluded  Option to exclude numbers from password.
	 */
	public void setNumbersIncluded(boolean numbersIncluded) {
		this.numbersIncluded = numbersIncluded;
	}

	/**
	 * Option to exclude punctuation characters from password.
	 * @return the punctuationIncluded     Certain characters are restricted/not allowed.
	 */
	public boolean isPunctuationIncluded() {
		return punctuationIncluded;
	}

	/**
	 * @param punctuationIncluded  Sets option to include punctuation.
	 */
	public void setPunctuationIncluded(boolean punctuationIncluded) {
		this.punctuationIncluded = punctuationIncluded;
	}

	/**
	 * Option to include ambiguous characters. 
	 * @return the ambiguousExcluded   Default is excluded.
	 */
	public boolean isAmbiguousExcluded() {
		return ambiguousExcluded;
	}

	/**
	 * @param ambiguousExcluded the ambiguousExcluded to set
	 */
	public void setAmbiguousExcluded(boolean ambiguousExcluded) {
		this.ambiguousExcluded = ambiguousExcluded;
	}

	/**
	 *     Gets the character that user would like to delimit the words in the passphrase.
	 * @return the delimiter   Default is space (" ").
	 */
	public String getDelimiter() {
		return delimiter;
	}

	/**
	 * @param delimiter the delimiter to set   Default is space(" ").
	 */
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	/**
	 * @return the rng     Random number that will be associated with words in wordList.
	 */
	protected Random getRng() {
		return rng;
	}

	/**
	 * @param rng the rng to set   Sets the random number generator to generate numbers that will
	 *                             be associated with words in wordList.
	 */
	protected void setRng(Random rng) {
		this.rng = rng;
	}

} // end PasswordGenerator