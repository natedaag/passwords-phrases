package edu.cnm.deepdive.security;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WordList {

	public static final int RECOMMENDED_PHRASE_LENGTH = 5;
	
	private static final String PROPERTIES_FILE = "resources/text.properties";

	private static String usageMessage = "";
	private static String errorMessage = "";
	private static String warningMessage = "";

	public static void main(String[] args) {
		try {
			loadResources();
			int phraseLength = (args.length > 0) ? Integer.parseInt(args[0]) : RECOMMENDED_PHRASE_LENGTH;
			if (phraseLength <= 0) {
				throw new IllegalArgumentException(errorMessage);
			} 
			else if (phraseLength < RECOMMENDED_PHRASE_LENGTH) {
				System.out.println(warningMessage);
			}
			} catch (NumberFormatException ex) {
			ex.printStackTrace();
			System.out.println(errorMessage);
			System.out.printf(usageMessage, WordList.class.getName());
			System.exit(1);
		} catch (IllegalArgumentException ex) {
			System.out.println(errorMessage);
			System.out.printf(usageMessage, WordList.class.getName());
			System.exit(1);
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}

	private static void loadResources() 
			throws IOException {
		Properties properties = new Properties();
		try (InputStream input = WordList.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
			properties.load(input);
			usageMessage = properties.getProperty("usage.message");
			errorMessage = properties.getProperty("error.message");
			warningMessage = properties.getProperty("warning.message");
		}
	}
}
