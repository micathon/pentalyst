package config;

import iconst.IConst;

public class Config implements IConst {

	private boolean debug = false;
	private boolean fileout = false;
	private boolean valid = false;
	private char sepch;
	private boolean unchanged = false;
	private boolean isDirty = false;
	private boolean unitTestFlag = false;
	private boolean runTestFlag = false;
	private boolean doCmdPrompt = false;
	private String configFileName = "config.txt";
	private String configFullFileName;
	private char dchar = 'd';
	private char fchar = 'f';
	private char uchar = 'u';
	private char rchar = 'r';
	private char xchar = 'x';
	private char pchar = 'p';

	public Config(String filePath, String fileName, String sepWord) {
		sepWord = configRtn(fileName, sepWord);
		if (!valid) {
			return;
		}
		if (doCmdPrompt) {
			return;
		}
		if (!isDirty) {
			return;
		}
		// save sepWord to text file...
	}

	public String configRtn(String fileName, String sepWord) {
		char ch;
		int dpos;
		int fpos;
		int upos;
		int rpos;
		int switchCount = 0;
		
		if (sepWord == "") {
			sepWord = getSepWord();
		}
		ch = sepWord.charAt(0);
		if (ch != sepch) {
			return "";
		}
		sepWord = sepWord.substring(1);
		if (sepWord.length() == 0) {
			useDefaults();
			valid = true;
			return "";
		}
		ch = sepWord.charAt(0);
		if (sepWord.length() > 1) { }
		else if (ch == xchar) {
			sepWord = cancelUnitTest();
			return sepWord;
		}
		else if (ch == pchar) {
			displayCmdPrompt();
			return "";  // must detect 'p' in calling routine
		}
		dpos = sepWord.indexOf("" + dchar);
		fpos = sepWord.indexOf("" + fchar);
		upos = sepWord.indexOf("" + uchar);
		rpos = sepWord.indexOf("" + rchar);
		if ((upos >= 0) && (rpos >= 0)) {
			return "";
		}
		if (sepWord.length() > 3) {
			return "";
		}
		switchCount += (dpos >= 0) ? 1 : 0; 
		switchCount += (fpos >= 0) ? 1 : 0; 
		switchCount += (upos >= 0) ? 1 : 0; 
		switchCount += (rpos >= 0) ? 1 : 0;
		if (switchCount != sepWord.length()) {
			return "";
		}
		if (dpos >= 0) {
			debug = true;
		}
		if (fpos >= 0) {
			fileout = true;
		}
		if (upos >= 0) {
			unitTestFlag = true;
		}
		if (rpos >= 0) {
			runTestFlag = true;
		}
		isDirty = true;
		valid = true;
		return sepWord;
	}
	
	public Config() {
		useDefaults();
	}
	
	private void useDefaults() {
		// use defaults
		isDirty = true;
	}
	
	private String getSepWord() {
		// read sepword from text file
		// prepend sepch
		String sepWord;

		sepWord = getRawSepWord();  // get from text file
		return "" + sepch + sepWord;
	}
	
	private String getRawSepWord() {
		// read sepword from text file...
		return "";
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public boolean isDebug() {
		return debug;
	}
	
	public boolean isFileOut() {
		return fileout;
	}
	
	public boolean isUnitTest() {
		return unitTestFlag;
	}
	
	public boolean isRunTest() {
		return runTestFlag;
	}
	
	private String cancelUnitTest() {
		String sepWord;
		int dpos;
		int fpos;

		sepWord = getRawSepWord();
		dpos = sepWord.indexOf("" + dchar);
		fpos = sepWord.indexOf("" + fchar);
		if (dpos >= 0) {
			debug = true;
		}
		if (fpos >= 0) {
			fileout = true;
		}
		isDirty = true;
		return "";  // must return d/f sepWord, no u/r...
	}
	
	private void displayCmdPrompt() {
		doCmdPrompt = true;
		valid = true;
	}

	public boolean isCmdPrompt() {
		return doCmdPrompt;
	}
	
	public void displayHelpInfo() {
		System.out.println("Usage:");
		System.out.println("mvn compile exec:java [arg]");
		System.out.println("");
		System.out.println("arg:");
		System.out.println("\"-Dexec.args=pny-cmd\"");
		System.out.println("");
		System.out.println("pny-cmd:");
		System.out.println("[file name w/o ext][.switches]");
		System.out.println("");
		System.out.println("switches:");
		// display rest of help info...
	}

}
