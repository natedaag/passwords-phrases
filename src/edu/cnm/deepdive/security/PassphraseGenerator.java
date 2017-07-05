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
   * Instantiates a new passphrase generator with default field values
   */
  public PassphraseGenerator() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * 
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
  * 
  */
  protected void setupRng() {
    rng = new SecureRandom();
  }
  
  /**
   * 
   * @return
   */
  public String generate() {
    if (pool == null) {
      setupPool();
    }
    if (rng == null) {
      setupRng();
    }
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < length; i++) {
      String word = pool.get(rng.nextInt(pool.size()));
      builder.append(word);
      builder.append(delimiter);
    }
    return builder.toString().trim();
  }
  
  /**
   * 
   * @return
   */
  public String getWordList() {
    return wordList;
  }
  
  /**
   * 
   * @param wordList
   */
  public void setWordList(String wordList) {
    this.wordList = wordList;
  }
  
  /**
   * 
   * @return
   */
  public String getDelimiter() {
    return delimiter;
  }
  
  /**
   * 
   * @param delimiter
   */
  public void setDelimiter(String delimiter) {
    this.delimiter = delimiter;
  }
  
  
  /**
   * 
   * @return
   */
  public int getLength() {
    return length;
  }
  
  /**
   * 
   * @param length
   */
  public void setLength(int length) {
    this.length = length;
  }

  /**
   * @return the rng
   */
  protected Random getRng() {
    return rng;
  }

  /**
   * @param rng the rng to set
   */
  protected void setRng(Random rng) {
    this.rng = rng;
  
  }
  
} // end PassphraseGenerator
