/**
 * 
 */
package edu.cnm.deepdive.security;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * @author natedaag
 *
 */
public class PassphraseGenerator {
  
  public static final String DEFAULT_WORD_LIST = "resources/wordlist";
  public static final String DEFAULT_DELIMITER = " ";
  public static final int DEFAULT_LENGTH = 6;
  
  private String wordList = DEFAULT_WORD_LIST;
  private String delimiter = DEFAULT_DELIMITER;
  private int length = DEFAULT_LENGTH;
  private Random rng = null;
  private ArrayList<String> pool = null;
  
  
  
  /**
   * Instantiates a new passphrase generator with default field values.
   */
  public PassphraseGenerator() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   *    Creates pool of words from which the passphrase will be selected.
   */
  protected void setupPool() {
    ResourceBundle bundle = ResourceBundle.getBundle(wordList);
    pool = new ArrayList<>();
    Enumeration<String> keyEnum = bundle.getKeys();
    while ( keyEnum.hasMoreElements()) {
      String key = keyEnum.nextElement();
      String word = bundle.getString(key);
      pool.add(word);
    }
  }
  
 /**
  *     Generates random number that will link to words in the pool of words.
  */
  protected void setupRng() {
    rng = new SecureRandom();
  }
  
  /**
   *    Generates random passphrase, consisting of specified number of words.
   * @return    returns the string, which is the passphrase.
   */
  public String generate() {
    if (pool == null) {
      setupPool();
    }
    if (rng == null) {
      setupRng();
    }
    StringBuilder builder = new StringBuilder();
    String word = pool.get(rng.nextInt(pool.size()));
    builder.append(word);
    for (int i = 0; i < length - 1; i++) {
      word = pool.get(rng.nextInt(pool.size()));
      builder.append(delimiter);
      builder.append(word);
    }
    return builder.toString().trim();
  }
  
  /**
   *    Gets the list of words, from which a specified number will make the passphrase.
   * @return    The wordList that contains the word.
   */
  public String getWordList() {
    return wordList;
  }
  
  /**   
   *    Determines which wordList will be used.  
   * @param wordList    Default is English language, non-offensive words.
   */
  public void setWordList(String wordList) {
    this.wordList = wordList;
  }
  
  /**
   *    Gets the punctuation character that will be used between words, to mark the ends of the words.
   * @return    Default is the space. (" ").
   */
  public String getDelimiter() {
    return delimiter;
  }
  
  /**
   *    User sets this delimiter.
   * @param delimiter   Default is a space key. (" ")
   */
  public void setDelimiter(String delimiter) {
    this.delimiter = delimiter;
  }
  
  
  /**
   *    Gets the number of words that will go into the password.  
   * @return    Default is 5 words.
   */
  public int getLength() {
    return length;
  }
  
  /**
   *    Sets the length, in words, of how long the password will be.
   * @param length  Default is 5.
   */
  public void setLength(int length) {
    this.length = length;
  }

  /**
   * Gets the random number generator, which will select the numbers that are associated with the words in wordList.
   * @return the rng    Random numbers will be associated with words.
   */
  protected Random getRng() {
    return rng;
  }

  /**
   *    Sets the random number generator, which will select the numbers that are associated with words in wordList.
   * @param rng the rng to set
   */
  protected void setRng(Random rng) {
    this.rng = rng;
  
  }
  
} // end PassphraseGenerator
