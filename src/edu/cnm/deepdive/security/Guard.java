/**
 * Guard.java
 */
package edu.cnm.deepdive.security;

import java.util.HashMap;

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
 		String artifact = generateArtifact(map);
		emitArtifact(artifact);
	} // end main method



	static String generateArtifact(HashMap<String, Object> map) {
		if (map.containsKey("m")) {
			PasswordGenerator gen = new SecurePasswordGenerator();
			// TODO set fields for all specified options.
		}
		return null; // FIXME
	}

	static void emitArtifact(String artifact) {
		// TODO make this smarter
		System.out.println(artifact);
	} // end emitArtifact

} // end class











