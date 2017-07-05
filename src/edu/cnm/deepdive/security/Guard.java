/**
 * Guard.java
 */
package edu.cnm.deepdive.security;

import java.util.HashMap;
import java.util.Map;

/**
 * Program to generate random passwords and passphrases.
 * 
 * Generation uses a cryptographically secure random number generator to select
 * words from a list of characters from a pool.
 * 
 * @author natedaag *
 */
public class Guard {

	/**
	 * Parse command-line arguments using Apache Commons CLI library, and then
	 * instantiate and invoke the appropriate classes and methods to generate
	 * the requested artifact.
	 * 
	 * @param args
	 *            Command line arguments specifying generation options.
	 */
	public static void main(String[] args) {
		HashMap<String, Object> map = Options.getOptions(args);
		if (map != null) {
		  String artifact = generateArtifact(map);
		  emitArtifact(artifact);
		} // end if
 	} // end main method

	static String generateArtifact(HashMap<String, Object> map) {
		if (map.containsKey("m")) {
			PasswordGenerator gen = new SecurePasswordGenerator();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
			  switch (entry.getKey()) {
			    case "L":
			      int length = ((Number) entry.getValue()).intValue();
			      gen.setMinLength(length);
			      gen.setMaxLength(length);
			      break;
			      case "a":
			        gen.setAmbiguousExcluded(false);
			        break;
			      case "b":
			        gen.setUpperCaseIncluded(false);
			        break;
			      case "s":
			        gen.setLowerCaseIncluded(false);
			        break;
			      case "p":
			        gen.setPunctuationIncluded(false);
			        break;
			        default:
			          // do nothing
			          break;
			  }
			}
			return gen.generate();
			} else {
			  PassphraseGenerator gen = new PassphraseGenerator();
			  for (Map.Entry<String, Object> entry : map.entrySet()) {
			    switch (entry.getKey()) {
			      case "L":
			        int length = ((Number) entry.getValue()).intValue();
			        gen.setLength(length);
			        break;
			      case "d":
			        String delimiter = (String) entry.getValue();
			        gen.setDelimiter(delimiter);
			        break;
			      case "w":
			        String wordListFile = (String) entry.getValue();
			        gen.setWordList(wordListFile);;
			        break;
			    }
			  }
			  return gen.generate();  
			}
	}

	static void emitArtifact(String artifact) {
		System.out.println(artifact);
	} // end emitArtifact

} // end class











