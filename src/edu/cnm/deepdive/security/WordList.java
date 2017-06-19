package edu.cnm.deepdive.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

public class WordList {

	public static final int RECOMMENDED_PHRASE_LENGTH = 5;

	public static final String WORD_LIST_FILE = "resources/eff_large_wordlist.txt";

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
			} // end if
			else if (phraseLength < RECOMMENDED_PHRASE_LENGTH) {
				System.out.println(warningMessage);
			} // end else-if
			String[] wordList = getWordList(WORD_LIST_FILE);
			String[] selectedWords = getRandomWords(phraseLength, wordList);
			System.out.println(getJoinedString(selectedWords));
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
		} // end catch IOE
	} // end main method

	private static void loadResources() throws IOException {
		Properties properties = new Properties();
		try (InputStream input = WordList.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
			properties.load(input);
			usageMessage = properties.getProperty("usage.message");
			errorMessage = properties.getProperty("error.message");
			warningMessage = properties.getProperty("warning.message");
		}
	}

	public static String[] getWordList(String listPath) throws IOException {
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(WordList.class.getClassLoader().getResourceAsStream(listPath)))) {
			ArrayList<String> words = new ArrayList<>();
			for (String line = reader.readLine(); line != null; line = reader.readLine()) {
				words.add(line.split("\\s+")[1]);
			}
			return words.toArray(new String[] {});
		}
	}

	public static String[] getRandomWords(int numWords, String[] wordList) {
		String[] selection = new String [numWords];
		Random rng = new Random();
		for (int i = 0; i < selection.length; i++) {
			int selectedPosition = rng.nextInt(wordList.length);
			selection[i] = wordList[selectedPosition];
		}
		return selection;
		}	
	private static String getJoinedString(String[] source) {
		StringBuilder builder = new StringBuilder();
		for (String item : source) {
			builder.append(item);
			builder.append(" ");
			
		}
		return builder.toString().trim();
	}

}
