/**
 * 
 */
package edu.cnm.deepdive.security;

import java.util.HashMap;
import java.util.ResourceBundle;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

/**
 * Create an Options class to store the options for our command line interface.
 * 
 * @author natedaag		
 */
public class Options {
	
	private static final String OPTIONS_DESCRIPTION_BUNDLE = "resources/options";
	
	private static final String HELP_OPTION_KEY = "help.option";
	private static final String LENGTH_OPTION_KEY = "length.option";
	private static final String	DELIMITER_OPTION_KEY = "delimiter.option";
	private static final String	LIST_OPTION_KEY = "word-list.option";
	private static final String	MODE_OPTION_KEY = "password-mode.option";
	private static final String	UPPER_OPTION_KEY = "exclude-upper.option";
	private static final String	LOWER_OPTION_KEY = "exclude-lower.option";
	private static final String	DIGITS_OPTION_KEY = "exclude-digits.option";
	private static final String	PUNCTUATION_OPTION_KEY = "exclude-punctuation.option";
	private static final String	AMBIGUOUS_OPTION_KEY = "include-ambiguous.option";
	
	
	static HashMap<String, Object> getOptions(String[] args) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(OPTIONS_DESCRIPTION_BUNDLE);
			
			Option helpOption = Option.builder("?").longOpt("help")
												   .hasArg(false)
												   .desc(bundle.getString(HELP_OPTION_KEY))
												   .build();
			Option excludeUpperOption = Option.builder("b").longOpt("exclude-upper")
																	.hasArg(false)
																	.desc(bundle.getString(UPPER_OPTION_KEY))
																	.build();
					Option excludeLowerOption = Option.builder("s").longOpt("exclude-lower")
																   .hasArg(false)
																   .desc(bundle.getString(LOWER_OPTION_KEY))
																   .build();
					Option excludeDigits = Option.builder("n").longOpt("exclude-digits")
															  .hasArg(false)
															  .desc(bundle.getString(DIGITS_OPTION_KEY))
															  .build();
					Option excludePunctuation = Option.builder("p").longOpt("exclude-punctuation")
																   .hasArg(false)
																   .desc(bundle.getString(PUNCTUATION_OPTION_KEY))
																   .build();
					Option includeAmbiguous	= Option.builder("a").longOpt("include-ambiguous")
																 .hasArg(false)
																 .desc(bundle.getString(AMBIGUOUS_OPTION_KEY))
																 .build();
			Option lengthOption = Option.builder("L").argName("length")
								 				   			 .hasArg()
								 							 .longOpt("length")
								 							 .numberOfArgs(1)
								 							 .type(Number.class)
								 							 .desc(bundle.getString(LENGTH_OPTION_KEY))
								 							 .build();
		Option delimiterOption = Option.builder("d").argName("delimiter")
												   .hasArg()
												   .longOpt("delimiter")
												   .numberOfArgs(1)
												   .optionalArg(true)
												   .type(String.class)
												   .desc(bundle.getString(DELIMITER_OPTION_KEY))
												   .build();
		Option wordListOption = Option.builder("w").argName("path-to-list-field")
												   .hasArg()
												   .longOpt("word-list")
												   .numberOfArgs(1)
												   .type(String.class)
												   .desc(bundle.getString(LIST_OPTION_KEY))
												   .build();
		Option modeOption = Option.builder("m").hasArg(false)											   .hasArg()
											   .longOpt("password-mode")
											   .desc(bundle.getString(MODE_OPTION_KEY))
											   .build();
		org.apache.commons.cli.Options opts 
		= new org.apache.commons.cli.Options().addOption(lengthOption)
									.addOption(delimiterOption)
									.addOption(wordListOption)
									.addOption(excludeUpperOption)
									.addOption(excludeLowerOption)
									.addOption(excludeDigits)
									.addOption(excludePunctuation)
									.addOption(includeAmbiguous)
									.addOption(modeOption);
		DefaultParser parser = new DefaultParser();
		HashMap<String, Object> map = new HashMap<>();
		CommandLine cmdLine = parser.parse(opts, args);
		for (Option option : cmdLine.getOptions()) {
			String opt = option.getOpt();
		map.put(opt, cmdLine.getParsedOptionValue(opt));
		}
		return map;
		} catch (ParseException ex) { 
			// TODO handle this exception with a usage display			
			return null;
					}
		
	} // end getOptions

} // end class Options
